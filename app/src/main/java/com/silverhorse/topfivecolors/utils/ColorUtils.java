package com.silverhorse.topfivecolors.utils;

public class ColorUtils {
    /**
     * Converts an integer color value to its RGB string representation.
     *
     * @param color The integer color value.
     * @return A string representing the RGB values in the format "R:x G:y B:z".
     */
    public static String RGBString(final int color) {
        final int red = (color >> 16) & 0xFF;
        final int green = (color >> 8) & 0xFF;
        final int blue = color & 0xFF;

        return "R:" + red + " G:" + green + " B:" + blue;
    }
}
