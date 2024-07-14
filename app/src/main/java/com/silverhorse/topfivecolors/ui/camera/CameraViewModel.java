package com.silverhorse.topfivecolors.ui.camera;

import android.graphics.Bitmap;
import android.service.controls.ControlsProviderService;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.silverhorse.topfivecolors.model.RGBColor;
import com.silverhorse.topfivecolors.utils.ImageUtils;

import java.util.List;

public class CameraViewModel extends ViewModel {
    private MutableLiveData<List<RGBColor>> dominantColors = new MutableLiveData<>();

    public LiveData<List<RGBColor>> getDominantColors() {
        return dominantColors;
    }

    public void processImage(final Bitmap bitmap) {
        Log.d(ControlsProviderService.TAG, "processImage: ");
        final List<RGBColor> colors = ImageUtils.getDominantColors(bitmap);
        dominantColors.postValue(colors);
    }
}
