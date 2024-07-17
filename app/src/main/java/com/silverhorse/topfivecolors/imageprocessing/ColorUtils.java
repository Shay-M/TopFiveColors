package com.silverhorse.topfivecolors.imageprocessing;

import android.graphics.Color;

public class ColorUtils {
    public static int roundToNearestBucket(final int value, final int bucketSize) {
        return (value / bucketSize) * bucketSize;
    }

    public static int getBucketedColor(final int color, final int bucketSize) {
        int r = roundToNearestBucket(Color.red(color), bucketSize);
        int g = roundToNearestBucket(Color.green(color), bucketSize);
        int b = roundToNearestBucket(Color.blue(color), bucketSize);
        return Color.rgb(r, g, b);
    }
}