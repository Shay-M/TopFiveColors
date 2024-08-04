package com.silverhorse.topfivecolors.ui.camera;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.silverhorse.topfivecolors.imageprocessing.BitmapUtils;
import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CameraViewModel extends ViewModel {
    private final MutableLiveData<List<ColorPercentage>> dominantColors = new MutableLiveData<>();

    public LiveData<List<ColorPercentage>> getDominantColors() {
        return dominantColors;
    }

    public void processImage(final Bitmap bitmap) {
        final List<ColorPercentage> colors = BitmapUtils.getDominantColors(bitmap);
        dominantColors.postValue(colors);

    }

}
