package com.silverhorse.topfivecolors.ui.shared;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<ColorPercentage>> dominantColors = new MutableLiveData<>();

    public void setDominantColors(List<ColorPercentage> colors) {
        dominantColors.setValue(colors);
    }

    public LiveData<List<ColorPercentage>> getDominantColors() {
        return dominantColors;
    }
}
