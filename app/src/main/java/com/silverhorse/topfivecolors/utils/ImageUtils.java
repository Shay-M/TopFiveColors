package com.silverhorse.topfivecolors.utils;

import android.graphics.Bitmap;
import android.util.Log;

import com.silverhorse.topfivecolors.model.RGBColor;

import java.util.List;

public class ImageUtils {
    public static List<RGBColor> getDominantColors(final Bitmap bitmap) {
        Log.d("TAG", "getDominantColors: " + bitmap);
        return ColorUtils.getDominantColors(bitmap,5);
//        return ColorUtils.getDominantColorsKmeans(bitmap,5);
    }
}
