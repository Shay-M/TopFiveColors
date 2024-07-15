package com.silverhorse.topfivecolors.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.silverhorse.topfivecolors.model.RGBColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ColorUtils {
    /**
     * Returns a list of the top X dominant colors as RGBColor objects from the given Bitmap.
     *
     * @param bitmap         The Bitmap from which to extract dominant colors.
     * @param numberOfColors The number of dominant colors to retrieve.
     * @return A list of the top X dominant colors as RGBColor objects.
     */
    public static List<RGBColor> getDominantColors(final Bitmap bitmap, final int numberOfColors) {
        if (bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0) {
            return Collections.emptyList(); // Return an empty list
        }

        // Map to store color counts
        final Map<Integer, Integer> colorCountMap = new HashMap<>();

        // Iterate over all pixels in the bitmap and update color counts
        for (int y = 0; y < bitmap.getHeight(); ++y) {
            for (int x = 0; x < bitmap.getWidth(); ++x) {
                final int pixel = bitmap.getPixel(x, y);

                if (colorCountMap.containsKey(pixel)) {
                    colorCountMap.put(pixel, colorCountMap.get(pixel) + 1);
                } else {
                    colorCountMap.put(pixel, 1);
                }
            }
        }

        // Create a list of color entries and sort by pixel count in descending order
        final List<Map.Entry<Integer, Integer>> sortedColors = new ArrayList<>(colorCountMap.entrySet());

        // Sort in descending order by pixel count
        sortedColors.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        // Select the top X dominant colors and convert them to RGBColor objects
        List<RGBColor> dominantColors = new ArrayList<>();

        for (int i = 0; i < numberOfColors && i < sortedColors.size(); ++i) {
            int color = sortedColors.get(i).getKey();
            dominantColors.add(new RGBColor(color));
        }

        return dominantColors;
    }
}




