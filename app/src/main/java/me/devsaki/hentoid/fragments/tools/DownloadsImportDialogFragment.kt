package me.devsaki.hentoid.fragments.tools

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.skydoves.powermenu.PowerMenuItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import me.devsaki.hentoid.R
import me.devsaki.hentoid.core.Consts
import me.devsaki.hentoid.databinding.DialogQueueDownloadsImportBinding
import me.devsaki.hentoid.enums.Site
import me.devsaki.hentoid.events.ProcessEvent
import me.devsaki.hentoid.events.ServiceDestroyedEvent
import me.devsaki.hentoid.notification.import_.ImportNotificationChannel
import me.devsaki.hentoid.util.ImportHelper
import me.devsaki.hentoid.util.ImportHelper.PickFileContract
import me.devsaki.hentoid.util.Preferences
import me.devsaki.hentoid.util.StringHelper
import me.devsaki.hentoid.util.file.FileHelper
import me.devsaki.hentoid.widget.AddQueueMenu
import me.devsaki.hentoid.workers.DownloadsImportWorker
import me.devsaki.hentoid.workers.data.DownloadsImportData
import org.apache.commons.lang3.tuple.ImmutablePair
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber
import java.io.InputStreamReader

/**
 * Dialog for the downloads list import feature
 */
class DownloadsImportDialogFragment : DialogFragment() {

    private var _binding: DialogQueueDownloadsImportBinding? = null
    private val binding get() = _binding!!

    private var isServiceGracefulClose = false

    // Disposable for RxJava
    private var importDisposable = Disposables.empty()


    private val pickFile = registerForActivityResult(
        PickFileContract()
    ) { result: ImmutablePair<Int, Uri> ->
        onFilePickerResult(
            result.left,
            result.right
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedState: Bundle?
    ): View {
        _binding = DialogQueueDownloadsImportBinding.inflate(inflater, container, false)
        EventBus.getDefault().register(this)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
        _binding = null
    }

    override fun onViewCreated(rootView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(rootView, savedInstanceState)

        binding.importSelectFileBtn.setOnClickListener { pickFile.launch(0) }
    }

    private fun onFilePickerResult(resultCode: Int, uri: Uri) {
        when (resultCode) {
            ImportHelper.PickerResult.OK -> {
                // File selected
                val doc = DocumentFile.fromSingleUri(requireContext(), uri) ?: return
                binding.importSelectFileBtn.visibility = View.GONE
                checkFile(doc)
            }
            ImportHelper.PickerResult.KO_CANCELED -> Snackbar.make(
                binding.root,
                R.string.import_canceled,
                BaseTransientBottomBar.LENGTH_LONG
            ).show()
            ImportHelper.PickerResult.KO_NO_URI -> Snackbar.make(
                binding.root,
                R.string.import_invalid,
                BaseTransientBottomBar.LENGTH_LONG
            ).show()
            ImportHelper.PickerResult.KO_OTHER -> Snackbar.make(
                binding.root,
                R.string.import_other,
                BaseTransientBottomBar.LENGTH_LONG
            ).show()
            else -> {}
        }
    }

    private fun checkFile(file: DocumentFile) {
        // Display indeterminate progress bar while file is being deserialized
        binding.importProgressText.setText(R.string.checking_file)
        binding.importProgressBar.isIndeterminate = true
        binding.importProgressText.visibility = View.VISIBLE
        binding.importProgressBar.visibility = View.VISIBLE
        importDisposable = Single.fromCallable {
            readFile(requireContext(), file)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { c: List<String> ->
                    onFileRead(
                        c,
                        file
                    )
                }
            ) { t: Throwable? ->
                Timber.w(t)
                val fileName = StringHelper.protect(file.name)
                binding.importProgressText.text = resources.getString(
                    R.string.import_file_invalid,
                    fileName
                )
                binding.importProgressBar.visibility = View.INVISIBLE
            }
    }

    private fun onFileRead(
        downloadsList: List<String>,
        jsonFile: DocumentFile
    ) {
        importDisposable.dispose()
        binding.importProgressText.visibility = View.GONE
        binding.importProgressBar.visibility = View.GONE
        if (downloadsList.isEmpty()) {
            binding.importFileInvalidText.text =
                resources.getString(R.string.import_file_invalid, jsonFile.name)
            binding.importFileInvalidText.visibility = View.VISIBLE
        } else {
            binding.importSelectFileBtn.visibility = View.GONE
            binding.importFileInvalidText.visibility = View.GONE
            binding.importFileValidText.text = resources.getQuantityString(
                R.plurals.import_downloads_found,
                downloadsList.size,
                downloadsList.size
            )
            binding.importFileValidText.visibility = View.VISIBLE
            binding.importRunBtn.visibility = View.VISIBLE
            binding.importRunBtn.isEnabled = true
            binding.importRunBtn.setOnClickListener {
                askRun(
                    jsonFile.uri
                )
            }
        }
    }

    private fun askRun(fileUri: Uri) {
        val queuePosition = Preferences.getQueueNewDownloadPosition()
        if (queuePosition == Preferences.Constant.QUEUE_NEW_DOWNLOADS_POSITION_ASK) {
            AddQueueMenu.show(
                requireContext(),
                binding.root,
                this
            ) { position: Int, _: PowerMenuItem? ->
                runImport(
                    fileUri,
                    if (0 == position) Preferences.Constant.QUEUE_NEW_DOWNLOADS_POSITION_TOP else Preferences.Constant.QUEUE_NEW_DOWNLOADS_POSITION_BOTTOM
                )
            }
        } else {
            runImport(fileUri, queuePosition)
        }
    }

    private fun runImport(
        fileUri: Uri,
        queuePosition: Int
    ) {
        binding.importRunBtn.visibility = View.GONE
        binding.importStreamed.isEnabled = false
        isCancelable = false
        val builder = DownloadsImportData.Builder()
        builder.setFileUri(fileUri)
        builder.setQueuePosition(queuePosition)
        builder.setImportAsStreamed(binding.importStreamed.isChecked)
        ImportNotificationChannel.init(requireContext())
        binding.importProgressText.setText(R.string.starting_import)
        binding.importProgressBar.isIndeterminate = true
        binding.importProgressText.visibility = View.VISIBLE
        binding.importProgressBar.visibility = View.VISIBLE
        val workManager = WorkManager.getInstance(requireContext())
        workManager.enqueueUniqueWork(
            R.id.downloads_import_service.toString(),
            ExistingWorkPolicy.APPEND_OR_REPLACE,
            OneTimeWorkRequest.Builder(DownloadsImportWorker::class.java).setInputData(builder.data)
                .addTag(Consts.WORK_CLOSEABLE).build()
        )
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onImportEvent(event: ProcessEvent) {
        if (event.processId != R.id.import_downloads || isServiceGracefulClose) return
        if (ProcessEvent.EventType.PROGRESS == event.eventType) {
            val progress = event.elementsOK + event.elementsKO
            val itemTxt = resources.getQuantityString(R.plurals.item, progress)
            binding.importProgressText.text =
                resources.getString(
                    R.string.generic_progress,
                    progress,
                    event.elementsTotal,
                    itemTxt
                )
            binding.importProgressBar.max = event.elementsTotal
            binding.importProgressBar.progress = progress
            binding.importProgressBar.isIndeterminate = false
        } else if (ProcessEvent.EventType.COMPLETE == event.eventType) {
            importDisposable.dispose()
            isServiceGracefulClose = true
            binding.importProgressBar.progress = event.elementsTotal
            binding.importProgressText.text =
                resources.getQuantityString(
                    R.plurals.import_result,
                    event.elementsOK,
                    event.elementsOK,
                    event.elementsTotal
                )
            // Dismiss after 3s, for the user to be able to see the ending message
            Handler(Looper.getMainLooper()).postDelayed({ dismissAllowingStateLoss() }, 2500)
        }
    }

    /**
     * Service destroyed event handler
     *
     * @param event Broadcasted event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onServiceDestroyed(event: ServiceDestroyedEvent) {
        if (event.service != R.id.downloads_import_service) return
        if (!isServiceGracefulClose) {
            Snackbar.make(
                binding.root,
                R.string.import_unexpected,
                BaseTransientBottomBar.LENGTH_LONG
            ).show()
            Handler(Looper.getMainLooper()).postDelayed({ dismissAllowingStateLoss() }, 3000)
        }
    }

    companion object {
        fun invoke(fragmentManager: FragmentManager) {
            val fragment = DownloadsImportDialogFragment()
            fragment.show(fragmentManager, null)
        }

        fun readFile(context: Context, file: DocumentFile): List<String> {
            var lines: List<String>
            FileHelper.getInputStream(context, file).use { inputStream ->
                InputStreamReader(inputStream).use {
                    lines = it.readLines()
                }
            }
            return lines
                .map { s -> s.trim().lowercase() }
                .filterNot { s -> s.trim().isEmpty() }
                .filter { s ->
                    StringHelper.isNumeric(s) ||
                            (s.startsWith("http")
                                    && Site.searchByUrl(s) != Site.NONE
                                    )
                }
        }
    }
}