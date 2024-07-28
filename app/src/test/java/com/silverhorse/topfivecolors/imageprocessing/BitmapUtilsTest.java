package com.silverhorse.topfivecolors.imageprocessing;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.silverhorse.topfivecolors.model.ColorPercentage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 30)
public class BitmapUtilsTest {



    @Test
    public void getDominantColors_emptyBitmap() {
        final Bitmap emptyBitmap = null;
        final List<ColorPercentage> dominantColors = BitmapUtils.getDominantColors(emptyBitmap);
        assertEquals(0, dominantColors.size());
    }

    @Test
    public void getDominantColors_singleColorBitmap() {
        final Bitmap singleColorBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        singleColorBitmap.eraseColor(Color.RED);
        final List<ColorPercentage> dominantColors = BitmapUtils.getDominantColors(singleColorBitmap,1);
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
        final List<ColorPercentage> dominantColors = BitmapUtils.getDominantColors(bitmap,1);
        assertEquals(2, dominantColors.size());
        List<Integer> colors = dominantColors.stream()
                .map(ColorPercentage::color).toList();

        assertTrue(colors.contains(Color.RED));
        assertTrue(colors.contains(Color.GREEN));

    }
}
