package com.silverhorse.topfivecolors.ui.fivecolors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class InfoColorsViewModel extends ViewModel {

    private final Map<Integer, MutableLiveData<Integer>> colorMap = new HashMap<>();
    private final Map<Integer, MutableLiveData<Float>> percentageMap = new HashMap<>();
    private final Map<Integer, MutableLiveData<String>> colorTextMap = new HashMap<>();


    public InfoColorsViewModel() {
        for (int i = 1; i <= 5; i++) {
            colorMap.put(i, new MutableLiveData<>());
            percentageMap.put(i, new MutableLiveData<>());
            colorTextMap.put(i, new MutableLiveData<>());
        }
    }

    public LiveData<Integer> getColor(int index) {
        return colorMap.get(index);
    }

    public LiveData<Float> getPercentage(int index) {
        return percentageMap.get(index);
    }

    public LiveData<String> getColorText(int index) {
        return colorTextMap.get(index);
    }

    public void setColor(int index, int color) {
        if (colorMap.containsKey(index)) {
            colorMap.get(index).setValue(color);
        }
    }

    public void setPercentage(final int index, final float percentage) {
        if (percentageMap.containsKey(index)) {
            percentageMap.get(index).setValue(percentage);
        }
    }

    public void setColorText(final int index, final String text) {
        if (colorTextMap.containsKey(index)) {
            colorTextMap.get(index).setValue(text);
        }
    }

    public LiveData<Integer> getColor1() {
        return colorMap.get(1);
    }

    public LiveData<Integer> getColor2() {
        return colorMap.get(2);
    }

    public LiveData<Integer> getColor3() {
        return colorMap.get(3);
    }

    public LiveData<Integer> getColor4() {
        return colorMap.get(4);
    }

    public LiveData<Integer> getColor5() {
        return colorMap.get(5);
    }

    public LiveData<Float> getPercentage1() {
        return percentageMap.get(1);
    }

    public LiveData<Float> getPercentage2() {
        return percentageMap.get(2);
    }

    public LiveData<Float> getPercentage3() {
        return percentageMap.get(3);
    }

    public LiveData<Float> getPercentage4() {
        return percentageMap.get(4);
    }

    public LiveData<Float> getPercentage5() {
        return percentageMap.get(5);
    }

    public LiveData<String> getColor1Text() {
        return colorTextMap.get(1);
    }

    public LiveData<String> getColor2Text() {
        return colorTextMap.get(2);
    }

    public LiveData<String> getColor3Text() {
        return colorTextMap.get(3);
    }

    public LiveData<String> getColor4Text() {
        return colorTextMap.get(4);
    }

    public LiveData<String> getColor5Text() {
        return colorTextMap.get(5);
    }


}

