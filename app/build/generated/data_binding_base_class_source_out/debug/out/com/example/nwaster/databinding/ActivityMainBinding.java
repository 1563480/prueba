// Generated by view binder compiler. Do not edit!
package com.example.nwaster.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.nwaster.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView addProduct;

  @NonNull
  public final EditText emailEditText;

  @NonNull
  public final TextView googleButton;

  @NonNull
  public final EditText passwordEditText;

  @NonNull
  public final ImageView passwordIcon;

  @NonNull
  public final TextView register;

  @NonNull
  public final ImageView usernameIcon;

  private ActivityMainBinding(@NonNull RelativeLayout rootView, @NonNull TextView addProduct,
      @NonNull EditText emailEditText, @NonNull TextView googleButton,
      @NonNull EditText passwordEditText, @NonNull ImageView passwordIcon,
      @NonNull TextView register, @NonNull ImageView usernameIcon) {
    this.rootView = rootView;
    this.addProduct = addProduct;
    this.emailEditText = emailEditText;
    this.googleButton = googleButton;
    this.passwordEditText = passwordEditText;
    this.passwordIcon = passwordIcon;
    this.register = register;
    this.usernameIcon = usernameIcon;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addProduct;
      TextView addProduct = ViewBindings.findChildViewById(rootView, id);
      if (addProduct == null) {
        break missingId;
      }

      id = R.id.emailEditText;
      EditText emailEditText = ViewBindings.findChildViewById(rootView, id);
      if (emailEditText == null) {
        break missingId;
      }

      id = R.id.googleButton;
      TextView googleButton = ViewBindings.findChildViewById(rootView, id);
      if (googleButton == null) {
        break missingId;
      }

      id = R.id.passwordEditText;
      EditText passwordEditText = ViewBindings.findChildViewById(rootView, id);
      if (passwordEditText == null) {
        break missingId;
      }

      id = R.id.password_icon;
      ImageView passwordIcon = ViewBindings.findChildViewById(rootView, id);
      if (passwordIcon == null) {
        break missingId;
      }

      id = R.id.register;
      TextView register = ViewBindings.findChildViewById(rootView, id);
      if (register == null) {
        break missingId;
      }

      id = R.id.username_icon;
      ImageView usernameIcon = ViewBindings.findChildViewById(rootView, id);
      if (usernameIcon == null) {
        break missingId;
      }

      return new ActivityMainBinding((RelativeLayout) rootView, addProduct, emailEditText,
          googleButton, passwordEditText, passwordIcon, register, usernameIcon);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
