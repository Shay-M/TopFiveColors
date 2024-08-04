package com.silverhorse.topfivecolors.ui.camera;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.silverhorse.topfivecolors.imageprocessing.BitmapUtils;
import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.List;

public class CameraViewModel extends ViewModel {
    private final MutableLiveData<List<ColorPercentage>> dominantColors = new MutableLiveData<>();

    public LiveData<List<ColorPercentage>> getDominantColors() {
        return dominantColors;
    }

    public void processImage(final Bitmap bitmap) {
        final List<ColorPercentage> colors = BitmapUtils.getDominantColors(bitmap);
        dominantColors.postValue(colors);

//        final List<ColorPercentage> colors = generateRandomColors();
//        dominantColors.postValue(colors);

    }

/*    private List<ColorPercentage> generateRandomColors() {
        List<ColorPercentage> colors = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            float percentage = random.nextFloat() * 100;
            colors.add(new ColorPercentage(color, percentage));
        }

        return colors;
    }*/
}
