package com.silverhorse.topfivecolors.ui.shared;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.silverhorse.topfivecolors.imageprocessing.BitmapUtils;
import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<ColorPercentage>> dominantColors = new MutableLiveData<>();
//    private final MutableLiveData<Integer> colorBucketSize = new MutableLiveData<>(30);


    public void setDominantColors(List<ColorPercentage> colors) {
        dominantColors.setValue(colors);
    }

    public LiveData<List<ColorPercentage>> getDominantColors() {
        return dominantColors;
    }

//    public void recalculateColors(final Bitmap bitmap, final int seekBarValue) {
//        final List<ColorPercentage> colors = BitmapUtils.getDominantColors(bitmap, seekBarValue);
//        dominantColors.postValue(colors);
//    }


}
