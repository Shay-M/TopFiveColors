package com.silverhorse.topfivecolors.ui.camera;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.silverhorse.topfivecolors.imageprocessing.BitmapUtils;
import com.silverhorse.topfivecolors.imageprocessing.DefaultBitmapResizer;
import com.silverhorse.topfivecolors.imageprocessing.DefaultColorCounter;
import com.silverhorse.topfivecolors.imageprocessing.DefaultColorExtractor;
import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.List;

public class CameraViewModel extends ViewModel {
    private final MutableLiveData<List<ColorPercentage>> dominantColors = new MutableLiveData<>();
    private final BitmapUtils bitmapUtils;

    public CameraViewModel() {
        bitmapUtils = new BitmapUtils(new DefaultBitmapResizer(), new DefaultColorCounter(), new DefaultColorExtractor());
    }

    public LiveData<List<ColorPercentage>> getDominantColors() {
        return dominantColors;
    }

    public void processImage(final Bitmap bitmap) {
        final List<ColorPercentage> colors = bitmapUtils.getDominantColors(bitmap);
        dominantColors.postValue(colors);
    }
}
