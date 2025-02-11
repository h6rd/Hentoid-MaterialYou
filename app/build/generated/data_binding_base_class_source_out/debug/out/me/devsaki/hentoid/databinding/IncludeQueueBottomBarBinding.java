// Generated by view binder compiler. Do not edit!
package me.devsaki.hentoid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Barrier;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import me.devsaki.hentoid.R;
import me.devsaki.hentoid.views.CircularProgressView;

public final class IncludeQueueBottomBarBinding implements ViewBinding {
  @NonNull
  private final View rootView;

  @NonNull
  public final View backgroundBottomBar;

  @NonNull
  public final ImageButton btnPause;

  @NonNull
  public final ImageButton btnStart;

  @NonNull
  public final Barrier horizontalBarrier;

  @NonNull
  public final CircularProgressView queueDownloadPreparationProgressBar;

  @NonNull
  public final TextView queueInfo;

  @NonNull
  public final TextView queueStatus;

  @NonNull
  public final Barrier verticalBarrier;

  private IncludeQueueBottomBarBinding(@NonNull View rootView, @NonNull View backgroundBottomBar,
      @NonNull ImageButton btnPause, @NonNull ImageButton btnStart,
      @NonNull Barrier horizontalBarrier,
      @NonNull CircularProgressView queueDownloadPreparationProgressBar,
      @NonNull TextView queueInfo, @NonNull TextView queueStatus,
      @NonNull Barrier verticalBarrier) {
    this.rootView = rootView;
    this.backgroundBottomBar = backgroundBottomBar;
    this.btnPause = btnPause;
    this.btnStart = btnStart;
    this.horizontalBarrier = horizontalBarrier;
    this.queueDownloadPreparationProgressBar = queueDownloadPreparationProgressBar;
    this.queueInfo = queueInfo;
    this.queueStatus = queueStatus;
    this.verticalBarrier = verticalBarrier;
  }

  @Override
  @NonNull
  public View getRoot() {
    return rootView;
  }

  @NonNull
  public static IncludeQueueBottomBarBinding inflate(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    if (parent == null) {
      throw new NullPointerException("parent");
    }
    inflater.inflate(R.layout.include_queue_bottom_bar, parent);
    return bind(parent);
  }

  @NonNull
  public static IncludeQueueBottomBarBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.background_bottom_bar;
      View backgroundBottomBar = ViewBindings.findChildViewById(rootView, id);
      if (backgroundBottomBar == null) {
        break missingId;
      }

      id = R.id.btnPause;
      ImageButton btnPause = ViewBindings.findChildViewById(rootView, id);
      if (btnPause == null) {
        break missingId;
      }

      id = R.id.btnStart;
      ImageButton btnStart = ViewBindings.findChildViewById(rootView, id);
      if (btnStart == null) {
        break missingId;
      }

      id = R.id.horizontal_barrier;
      Barrier horizontalBarrier = ViewBindings.findChildViewById(rootView, id);
      if (horizontalBarrier == null) {
        break missingId;
      }

      id = R.id.queueDownloadPreparationProgressBar;
      CircularProgressView queueDownloadPreparationProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (queueDownloadPreparationProgressBar == null) {
        break missingId;
      }

      id = R.id.queueInfo;
      TextView queueInfo = ViewBindings.findChildViewById(rootView, id);
      if (queueInfo == null) {
        break missingId;
      }

      id = R.id.queueStatus;
      TextView queueStatus = ViewBindings.findChildViewById(rootView, id);
      if (queueStatus == null) {
        break missingId;
      }

      id = R.id.vertical_barrier;
      Barrier verticalBarrier = ViewBindings.findChildViewById(rootView, id);
      if (verticalBarrier == null) {
        break missingId;
      }

      return new IncludeQueueBottomBarBinding(rootView, backgroundBottomBar, btnPause, btnStart,
          horizontalBarrier, queueDownloadPreparationProgressBar, queueInfo, queueStatus,
          verticalBarrier);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
