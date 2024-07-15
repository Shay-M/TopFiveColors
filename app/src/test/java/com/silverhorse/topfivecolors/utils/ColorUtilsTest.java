package com.silverhorse.topfivecolors.utils;

import static org.junit.Assert.assertEquals;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.silverhorse.topfivecolors.model.RGBColor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class ColorUtilsTest {

    @Test
    public void getDominantColors() {
        // Create a Bitmap for testing
        final Bitmap bitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
        bitmap.setPixel(0, 0, Color.RED);
        bitmap.setPixel(1, 0, Color.GREEN);
        bitmap.setPixel(0, 1, Color.BLUE);
        bitmap.setPixel(1, 1, Color.RED);

        final List<RGBColor> dominantColors = ColorUtils.getDominantColors(bitmap, 2);

        assertEquals(2, dominantColors.size());
        final RGBColor testColorRed = new RGBColor(Color.RED);
        assertEquals(testColorRed, dominantColors.get(0));
        final RGBColor testColorGreen = new RGBColor(Color.GREEN);
        assertEquals(testColorGreen, dominantColors.get(1));
    }

    @Test
    public void testGetDominantColors_EmptyBitmap() {
        final Bitmap emptyBitmap = null;
        final List<RGBColor> dominantColors = ColorUtils.getDominantColors(emptyBitmap, 3);
        assertEquals(0, dominantColors.size());
    }

    @Test
    public void testGetDominantColors_SingleColorBitmap() {
        final Bitmap singleColorBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        singleColorBitmap.eraseColor(Color.RED);
        final List<RGBColor> dominantColors = ColorUtils.getDominantColors(singleColorBitmap, 1);
        assertEquals(1, dominantColors.size());
        assertEquals(new RGBColor(Color.RED), dominantColors.get(0));
    }
}
