package com.silverhorse.topfivecolors.utils;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

class ColorCounter implements Callable<Map<Integer, Integer>> {
    private final Bitmap bitmap;
    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;

    ColorCounter(Bitmap bitmap, int startX, int startY, int endX, int endY) {
        this.bitmap = bitmap;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public Map<Integer, Integer> call() {
        Map<Integer, Integer> colorCountMap = new HashMap<>();

        for (int y = startY; y < endY; ++y) {
            for (int x = startX; x < endX; ++x) {
                int pixel = bitmap.getPixel(x, y);
//                if (Color.alpha(pixel) != 255) continue; // Skip transparent pixels
                Log.d("TAGG", "call: "+ pixel);
                colorCountMap.merge(pixel, 1, Integer::sum);
            }
        }
//        Log.d("TAG", "Counted colors from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
        return colorCountMap;
    }
}