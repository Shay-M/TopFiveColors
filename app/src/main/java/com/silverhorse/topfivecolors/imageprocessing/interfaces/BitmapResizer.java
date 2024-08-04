package com.silverhorse.topfivecolors.imageprocessing.interfaces;

import android.graphics.Bitmap;

public interface BitmapResizer {
    Bitmap resize(final Bitmap bitmap, final int scaleFactor);
}
