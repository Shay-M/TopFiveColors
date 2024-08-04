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

import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.lifecycle.LiveData;

import java.text.DecimalFormat;

public class BindingAdapters {
    private static final String ANDROID_PROGRESS = "android:progress";

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

    @BindingAdapter({"android:background", "android:percentageText"})
    public static void setPercentageText(TextView view, Integer color, Float percentage) {
        if (color != null) {
            view.setBackgroundColor(color);

            int textColor = isColorDark(color) ? Color.WHITE : Color.BLACK;
            view.setTextColor(textColor);
        }

        if (percentage != null) {
            final DecimalFormat decimalFormat = new DecimalFormat("#.##%");
            final String formattedPercentage = decimalFormat.format(percentage / 100);
            view.setText(formattedPercentage);
        }
    }

    private static boolean isColorDark(final int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }
/*
    @BindingAdapter("progress")
    public static void setProgress(final SeekBar seekBar, final int progress) {
        if (seekBar.getProgress() != progress) {
            seekBar.setProgress(progress);
        }
    }

    @InverseBindingAdapter(attribute = "progress", event = "progressAttrChanged")
    public static int getProgress(final SeekBar seekBar) {
        return seekBar.getProgress();
    }

   @BindingAdapter(ANDROID_PROGRESS)
    public static void setProgressAttrChanged(final SeekBar seekBar, final InverseBindingListener listener) {
        if (listener != null) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                    if (fromUser) {
                        listener.onChange();
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    Log.d("TAG22", "onStartTrackingTouch: ");
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Log.d("TAG22", "onStartTrackingTouch: ");
                }
            });

        }
    }*/

    /*@BindingAdapter(ANDROID_PROGRESS)
    public static void setSeekbarProgress(SeekBar seekBar, int progress) {
        Log.d("TAG22", "setSeekbarProgress: 1");

        try {
            seekBar.setProgress(progress);
        } catch (Resources.NotFoundException nfe) {
            nfe.printStackTrace();
        }
    }

    @InverseBindingAdapter(attribute = ANDROID_PROGRESS)
    public static int getSeekbarProgress(SeekBar seekBar) {
        Log.d("TAG22", "getSeekbarProgress: 2");

        try {
            return seekBar.getProgress();
        } catch (Resources.NotFoundException nfe) {
            nfe.printStackTrace();
            return 0;
        }
    }*/
}
