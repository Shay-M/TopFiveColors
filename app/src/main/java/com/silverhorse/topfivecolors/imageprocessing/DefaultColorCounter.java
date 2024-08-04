package com.silverhorse.topfivecolors.imageprocessing;

import android.graphics.Bitmap;

import com.silverhorse.topfivecolors.imageprocessing.interfaces.ColorCounter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultColorCounter implements ColorCounter {
    @Override
    public Map<Integer, Integer> countColors(final Bitmap bitmap, final int startX, final int startY, final int endX, final int endY, final int bucketSize) {
        final Map<Integer, Integer> colorCountMap = new ConcurrentHashMap<>();
        for (int y = startY; y < endY; ++y) {
            for (int x = startX; x < endX; ++x) {
                int pixel = bitmap.getPixel(x, y);
                int bucketedColor = ColorUtils.getBucketedColor(pixel, bucketSize);
                colorCountMap.merge(bucketedColor, 1, Integer::sum);
            }
        }
        return colorCountMap;
    }
}