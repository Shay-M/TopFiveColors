/*
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
*/
package com.silverhorse.topfivecolors.ui.fivecolors;

import android.graphics.Color;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;

public class BindingAdapters {

    @BindingAdapter("android:background")
    public static void setBackgroundColor(TextView view, Integer color) {
        if (color != null) {
            view.setBackgroundColor(color);
        }
    }

    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView view, Integer color) {
        if (color != null) {
            view.setTextColor(color);
        }
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, String text) {
        if (text != null) {
            view.setText(text);
        }
    }

/*    @BindingAdapter("android:percentageText")
    public static void setPercentageText(TextView view, LiveData<Float> percentageLiveData) {
        if (percentageLiveData != null && percentageLiveData.getValue() != null) {
            float percentage = percentageLiveData.getValue();
            String formattedPercentage = String.format("%.2f%%", percentage);
            view.setText(formattedPercentage);


        }
    }*/

    @BindingAdapter({"android:background", "android:percentageText"})
    public static void setPercentageText(TextView view, Integer color, Float percentage) {
        if (color != null) {
            view.setBackgroundColor(color);

            int textColor = isColorDark(color) ? Color.WHITE : Color.BLACK;
            view.setTextColor(textColor);
        }

        if (percentage != null) {
            String formattedPercentage = String.format("%.2f%%", percentage);
            view.setText(formattedPercentage);
        }
    }

    private static boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }
}
