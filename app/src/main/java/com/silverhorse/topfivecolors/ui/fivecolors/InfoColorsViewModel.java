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

    private final MutableLiveData<Float> percentage1 = new MutableLiveData<>();
    private final MutableLiveData<Float> percentage2 = new MutableLiveData<>();
    private final MutableLiveData<Float> percentage3 = new MutableLiveData<>();
    private final MutableLiveData<Float> percentage4 = new MutableLiveData<>();
    private final MutableLiveData<Float> percentage5 = new MutableLiveData<>();

    private final MutableLiveData<String> color1Text = new MutableLiveData<>();
    private final MutableLiveData<String> color2Text = new MutableLiveData<>();
    private final MutableLiveData<String> color3Text = new MutableLiveData<>();
    private final MutableLiveData<String> color4Text = new MutableLiveData<>();
    private final MutableLiveData<String> color5Text = new MutableLiveData<>();

    private final MutableLiveData<Integer> textColor1 = new MutableLiveData<>();
    private final MutableLiveData<Integer> textColor2 = new MutableLiveData<>();
    private final MutableLiveData<Integer> textColor3 = new MutableLiveData<>();
    private final MutableLiveData<Integer> textColor4 = new MutableLiveData<>();
    private final MutableLiveData<Integer> textColor5 = new MutableLiveData<>();

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

    public LiveData<Float> getPercentage1() {
        return percentage1;
    }

    public LiveData<Float> getPercentage2() {
        return percentage2;
    }

    public LiveData<Float> getPercentage3() {
        return percentage3;
    }

    public LiveData<Float> getPercentage4() {
        return percentage4;
    }

    public LiveData<Float> getPercentage5() {
        return percentage5;
    }

    public LiveData<String> getColor1Text() {
        return color1Text;
    }

    public LiveData<String> getColor2Text() {
        return color2Text;
    }

    public LiveData<String> getColor3Text() {
        return color3Text;
    }

    public LiveData<String> getColor4Text() {
        return color4Text;
    }

    public LiveData<String> getColor5Text() {
        return color5Text;
    }

    public LiveData<Integer> getTextColor1() {
        return textColor1;
    }

    public LiveData<Integer> getTextColor2() {
        return textColor2;
    }

    public LiveData<Integer> getTextColor3() {
        return textColor3;
    }

    public LiveData<Integer> getTextColor4() {
        return textColor4;
    }

    public LiveData<Integer> getTextColor5() {
        return textColor5;
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

    public void setPercentage1(float percentage) {
        percentage1.setValue(percentage);
    }

    public void setPercentage2(float percentage) {
        percentage2.setValue(percentage);
    }

    public void setPercentage3(float percentage) {
        percentage3.setValue(percentage);
    }

    public void setPercentage4(float percentage) {
        percentage4.setValue(percentage);
    }

    public void setPercentage5(float percentage) {
        percentage5.setValue(percentage);
    }

    public void setColor1Text(String text) {
        color1Text.setValue(text);
    }

    public void setColor2Text(String text) {
        color2Text.setValue(text);
    }

    public void setColor3Text(String text) {
        color3Text.setValue(text);
    }

    public void setColor4Text(String text) {
        color4Text.setValue(text);
    }

    public void setColor5Text(String text) {
        color5Text.setValue(text);
    }

    public void setTextColor1(int color) {
        textColor1.setValue(color);
    }

    public void setTextColor2(int color) {
        textColor2.setValue(color);
    }

    public void setTextColor3(int color) {
        textColor3.setValue(color);
    }

    public void setTextColor4(int color) {
        textColor4.setValue(color);
    }

    public void setTextColor5(int color) {
        textColor5.setValue(color);
    }
    

}
