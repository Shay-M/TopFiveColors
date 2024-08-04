package com.silverhorse.topfivecolors.imageprocessing;

import android.graphics.Bitmap;
import android.util.Log;

import com.silverhorse.topfivecolors.imageprocessing.interfaces.BitmapResizer;

public class DefaultBitmapResizer implements BitmapResizer {
    private static final String TAG = "DefaultBitmapResizer";

    @Override
    public Bitmap resize(final Bitmap bitmap, final int scaleFactor) {
        final int scaledWidth = Math.max(5, bitmap.getWidth() / scaleFactor);
        final int scaledHeight = Math.max(5, bitmap.getHeight() / scaleFactor);
        final Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, false);
        Log.d(TAG, "Bitmap resized to: " + scaledWidth + "x" + scaledHeight);
        return resizedBitmap;
    }
}