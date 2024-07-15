package com.silverhorse.topfivecolors.ui.shared;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.silverhorse.topfivecolors.model.RGBColor;

import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<RGBColor>> dominantColors = new MutableLiveData<>();

    public void setDominantColors(List<RGBColor> colors) {
        dominantColors.setValue(colors);
    }

    public LiveData<List<RGBColor>> getDominantColors() {
        return dominantColors;
    }
}
