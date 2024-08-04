package com.silverhorse.topfivecolors.imageprocessing.interfaces;

import android.graphics.Bitmap;

import java.util.Map;

public interface ColorCounter {
    Map<Integer, Integer> countColors(final Bitmap bitmap, final int startX, final int startY, final int endX, final int endY, final int bucketSize);
}