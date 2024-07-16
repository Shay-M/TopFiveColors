package com.silverhorse.topfivecolors.ui.fivecolors;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;

public class BindingAdapters {

    @BindingAdapter("android:background")
    public static void setBackgroundColor(TextView view, LiveData<Integer> color) {
        if (color != null && color.getValue() != null) {
            view.setBackgroundColor(color.getValue());
        }
    }

    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView view, LiveData<Integer> color) {
        if (color != null && color.getValue() != null) {
            view.setTextColor(color.getValue());
        }
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, LiveData<String> text) {
        if (text != null && text.getValue() != null) {
            view.setText(text.getValue());
        }
    }
}
