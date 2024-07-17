/*package com.silverhorse.topfivecolors.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.ArrayList;
import java.util.List;

public class PixelExtractor {

    public static List<float[]> extractPixels(final Bitmap bitmap, final int startX, final int startY, final int endX, final int endY) {
        final List<float[]> pixels = new ArrayList<>();
        for (int y = startY; y < endY; ++y) {
            for (int x = startX; x < endX; ++x) {
                final int color = bitmap.getPixel(x, y);
                pixels.add(new float[]{Color.red(color), Color.green(color), Color.blue(color)});
            }
        }
        return pixels;
    }
}*/


package com.silverhorse.topfivecolors.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PixelExtractor {
    public static List<ColorPercentage> extractPixels(final Bitmap bitmap, final int startX, final int startY, final int endX, final int endY) {
        // Extract pixels from the bitmap
        final int[] pixels = new int[endX * endY];
        bitmap.getPixels(pixels, 0, endX, startX, startY, endX, endY);

        // Convert pixel colors to float arrays
        final List<float[]> pixelList = Collections.synchronizedList(new ArrayList<>());
        for (int color : pixels) {
            pixelList.add(new float[]{Color.red(color), Color.green(color), Color.blue(color)});
        }

        // Perform K-means clustering on the combined pixel data
        final List<float[]> centroids = KMeansCluster.kMeans(pixelList, 5, 8);

        // Calculate color percentages
        return KMeansCluster.calculateColorPercentages(centroids, pixels);

    }

}
