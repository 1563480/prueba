// Generated by view binder compiler. Do not edit!
package com.example.nwaster.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.nwaster.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityStockBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView addStock;

  @NonNull
  public final ListView stockProducts;

  private ActivityStockBinding(@NonNull ConstraintLayout rootView, @NonNull TextView addStock,
      @NonNull ListView stockProducts) {
    this.rootView = rootView;
    this.addStock = addStock;
    this.stockProducts = stockProducts;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityStockBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityStockBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_stock, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityStockBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addStock;
      TextView addStock = ViewBindings.findChildViewById(rootView, id);
      if (addStock == null) {
        break missingId;
      }

      id = R.id.stockProducts;
      ListView stockProducts = ViewBindings.findChildViewById(rootView, id);
      if (stockProducts == null) {
        break missingId;
      }

      return new ActivityStockBinding((ConstraintLayout) rootView, addStock, stockProducts);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}