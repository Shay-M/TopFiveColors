package com.silverhorse.topfivecolors.ui.fivecolors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InfoColorsViewModel extends ViewModel {

    private final MutableLiveData<Integer> color1 = new MutableLiveData<>();
    private final MutableLiveData<Integer> color2 = new MutableLiveData<>();
    private final MutableLiveData<Integer> color3 = new MutableLiveData<>();
    private final MutableLiveData<Integer> color4 = new MutableLiveData<>();
    private final MutableLiveData<Integer> color5 = new MutableLiveData<>();

    private final MutableLiveData<String> text1 = new MutableLiveData<>();
    private final MutableLiveData<String> text2 = new MutableLiveData<>();
    private final MutableLiveData<String> text3 = new MutableLiveData<>();
    private final MutableLiveData<String> text4 = new MutableLiveData<>();
    private final MutableLiveData<String> text5 = new MutableLiveData<>();

    public LiveData<Integer> getColor1() {
        return color1;
    }

    public LiveData<Integer> getColor2() {
        return color2;
    }

    public LiveData<Integer> getColor3() {
        return color3;
    }

    public LiveData<Integer> getColor4() {
        return color4;
    }

    public LiveData<Integer> getColor5() {
        return color5;
    }

    public LiveData<String> getText1() {
        return text1;
    }

    public LiveData<String> getText2() {
        return text2;
    }

    public LiveData<String> getText3() {
        return text3;
    }

    public LiveData<String> getText4() {
        return text4;
    }

    public LiveData<String> getText5() {
        return text5;
    }

    public void setColor1(int color) {
        color1.setValue(color);
    }

    public void setColor2(int color) {
        color2.setValue(color);
    }

    public void setColor3(int color) {
        color3.setValue(color);
    }

    public void setColor4(int color) {
        color4.setValue(color);
    }

    public void setColor5(int color) {
        color5.setValue(color);
    }

    public void setText1(String text) {
        text1.setValue(text);
    }

    public void setText2(String text) {
        text2.setValue(text);
    }

    public void setText3(String text) {
        text3.setValue(text);
    }

    public void setText4(String text) {
        text4.setValue(text);
    }

    public void setText5(String text) {
        text5.setValue(text);
    }
}