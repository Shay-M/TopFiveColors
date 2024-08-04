package com.silverhorse.topfivecolors.imageprocessing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.silverhorse.topfivecolors.imageprocessing.interfaces.BitmapResizer;
import com.silverhorse.topfivecolors.imageprocessing.interfaces.ColorCounter;
import com.silverhorse.topfivecolors.imageprocessing.interfaces.ColorExtractor;
import com.silverhorse.topfivecolors.model.ColorPercentage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class BitmapUtilsTest {

    private BitmapUtils bitmapUtils;

    @Before
    public void setUp() {
        BitmapResizer bitmapResizer = new DefaultBitmapResizer();
        ColorCounter colorCounter = new DefaultColorCounter();
        ColorExtractor colorExtractor = new DefaultColorExtractor();

        bitmapUtils = new BitmapUtils(bitmapResizer, colorCounter, colorExtractor);
    }


    @Test
    public void getDominantColors_emptyBitmap() {

        final Bitmap emptyBitmap = null;
        final List<ColorPercentage> dominantColors = bitmapUtils.getDominantColors(emptyBitmap);
        assertEquals(0, dominantColors.size());
    }

    @Test
    public void getDominantColors_singleColorBitmap() {
        final Bitmap singleColorBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        singleColorBitmap.eraseColor(Color.RED);
        final List<ColorPercentage> dominantColors = bitmapUtils.getDominantColors(singleColorBitmap,1);
        assertEquals(1, dominantColors.size());
        assertEquals(Color.RED, dominantColors.get(0).color());
    }

    @Test
    public void getDominantColors_variedBitmap() {
        final Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 100; y++) {
                bitmap.setPixel(x, y, Color.RED);
            }
        }
        for (int x = 50; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                bitmap.setPixel(x, y, Color.GREEN);
            }
        }
        final List<ColorPercentage> dominantColors = bitmapUtils.getDominantColors(bitmap,1);
        assertEquals(2, dominantColors.size());
        List<Integer> colors = dominantColors.stream()
                .map(ColorPercentage::color).toList();

        assertTrue(colors.contains(Color.RED));
        assertTrue(colors.contains(Color.GREEN));

    }
}
