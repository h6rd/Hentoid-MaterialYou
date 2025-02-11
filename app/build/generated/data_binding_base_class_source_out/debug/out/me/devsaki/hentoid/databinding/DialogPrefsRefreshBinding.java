// Generated by view binder compiler. Do not edit!
package me.devsaki.hentoid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import me.devsaki.hentoid.R;

public final class DialogPrefsRefreshBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton actionButton;

  @NonNull
  public final TextView refreshCaption;

  @NonNull
  public final View refreshDivider;

  @NonNull
  public final RadioGroup refreshLocation;

  @NonNull
  public final RadioButton refreshLocationExternal;

  @NonNull
  public final Group refreshLocationGroup;

  @NonNull
  public final RadioButton refreshLocationInternal;

  @NonNull
  public final TextView refreshOptions;

  @NonNull
  public final Group refreshOptionsGroup;

  @NonNull
  public final TextView refreshOptionsRemove;

  @NonNull
  public final CheckBox refreshOptionsRemove1;

  @NonNull
  public final CheckBox refreshOptionsRemove2;

  @NonNull
  public final TextView refreshOptionsRemoveOr1;

  @NonNull
  public final CheckBox refreshOptionsRemovePlaceholders;

  @NonNull
  public final CheckBox refreshOptionsRename;

  private DialogPrefsRefreshBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton actionButton, @NonNull TextView refreshCaption,
      @NonNull View refreshDivider, @NonNull RadioGroup refreshLocation,
      @NonNull RadioButton refreshLocationExternal, @NonNull Group refreshLocationGroup,
      @NonNull RadioButton refreshLocationInternal, @NonNull TextView refreshOptions,
      @NonNull Group refreshOptionsGroup, @NonNull TextView refreshOptionsRemove,
      @NonNull CheckBox refreshOptionsRemove1, @NonNull CheckBox refreshOptionsRemove2,
      @NonNull TextView refreshOptionsRemoveOr1, @NonNull CheckBox refreshOptionsRemovePlaceholders,
      @NonNull CheckBox refreshOptionsRename) {
    this.rootView = rootView;
    this.actionButton = actionButton;
    this.refreshCaption = refreshCaption;
    this.refreshDivider = refreshDivider;
    this.refreshLocation = refreshLocation;
    this.refreshLocationExternal = refreshLocationExternal;
    this.refreshLocationGroup = refreshLocationGroup;
    this.refreshLocationInternal = refreshLocationInternal;
    this.refreshOptions = refreshOptions;
    this.refreshOptionsGroup = refreshOptionsGroup;
    this.refreshOptionsRemove = refreshOptionsRemove;
    this.refreshOptionsRemove1 = refreshOptionsRemove1;
    this.refreshOptionsRemove2 = refreshOptionsRemove2;
    this.refreshOptionsRemoveOr1 = refreshOptionsRemoveOr1;
    this.refreshOptionsRemovePlaceholders = refreshOptionsRemovePlaceholders;
    this.refreshOptionsRename = refreshOptionsRename;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogPrefsRefreshBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogPrefsRefreshBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_prefs_refresh, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogPrefsRefreshBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.action_button;
      MaterialButton actionButton = ViewBindings.findChildViewById(rootView, id);
      if (actionButton == null) {
        break missingId;
      }

      id = R.id.refresh_caption;
      TextView refreshCaption = ViewBindings.findChildViewById(rootView, id);
      if (refreshCaption == null) {
        break missingId;
      }

      id = R.id.refresh_divider;
      View refreshDivider = ViewBindings.findChildViewById(rootView, id);
      if (refreshDivider == null) {
        break missingId;
      }

      id = R.id.refresh_location;
      RadioGroup refreshLocation = ViewBindings.findChildViewById(rootView, id);
      if (refreshLocation == null) {
        break missingId;
      }

      id = R.id.refresh_location_external;
      RadioButton refreshLocationExternal = ViewBindings.findChildViewById(rootView, id);
      if (refreshLocationExternal == null) {
        break missingId;
      }

      id = R.id.refresh_location_group;
      Group refreshLocationGroup = ViewBindings.findChildViewById(rootView, id);
      if (refreshLocationGroup == null) {
        break missingId;
      }

      id = R.id.refresh_location_internal;
      RadioButton refreshLocationInternal = ViewBindings.findChildViewById(rootView, id);
      if (refreshLocationInternal == null) {
        break missingId;
      }

      id = R.id.refresh_options;
      TextView refreshOptions = ViewBindings.findChildViewById(rootView, id);
      if (refreshOptions == null) {
        break missingId;
      }

      id = R.id.refresh_options_group;
      Group refreshOptionsGroup = ViewBindings.findChildViewById(rootView, id);
      if (refreshOptionsGroup == null) {
        break missingId;
      }

      id = R.id.refresh_options_remove;
      TextView refreshOptionsRemove = ViewBindings.findChildViewById(rootView, id);
      if (refreshOptionsRemove == null) {
        break missingId;
      }

      id = R.id.refresh_options_remove_1;
      CheckBox refreshOptionsRemove1 = ViewBindings.findChildViewById(rootView, id);
      if (refreshOptionsRemove1 == null) {
        break missingId;
      }

      id = R.id.refresh_options_remove_2;
      CheckBox refreshOptionsRemove2 = ViewBindings.findChildViewById(rootView, id);
      if (refreshOptionsRemove2 == null) {
        break missingId;
      }

      id = R.id.refresh_options_remove_or_1;
      TextView refreshOptionsRemoveOr1 = ViewBindings.findChildViewById(rootView, id);
      if (refreshOptionsRemoveOr1 == null) {
        break missingId;
      }

      id = R.id.refresh_options_remove_placeholders;
      CheckBox refreshOptionsRemovePlaceholders = ViewBindings.findChildViewById(rootView, id);
      if (refreshOptionsRemovePlaceholders == null) {
        break missingId;
      }

      id = R.id.refresh_options_rename;
      CheckBox refreshOptionsRename = ViewBindings.findChildViewById(rootView, id);
      if (refreshOptionsRename == null) {
        break missingId;
      }

      return new DialogPrefsRefreshBinding((ConstraintLayout) rootView, actionButton,
          refreshCaption, refreshDivider, refreshLocation, refreshLocationExternal,
          refreshLocationGroup, refreshLocationInternal, refreshOptions, refreshOptionsGroup,
          refreshOptionsRemove, refreshOptionsRemove1, refreshOptionsRemove2,
          refreshOptionsRemoveOr1, refreshOptionsRemovePlaceholders, refreshOptionsRename);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
