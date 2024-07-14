package com.silverhorse.topfivecolors.utils;

import static org.junit.Assert.assertEquals;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import com.silverhorse.topfivecolors.R;
import com.silverhorse.topfivecolors.model.RGBColor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
//@Config(sdk = {28})
public class ColorUtilsTest {

    @Test
    public void getDominantColors() {
        // Create a Bitmap for testing
//        Bitmap bitmap = BitmapFactory.decodeResource(RuntimeEnvironment.application.getResources(), R.drawable.sample_image_1);
        final Bitmap bitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
        bitmap.setPixel(0, 0, Color.RED);
        bitmap.setPixel(1, 0, Color.GREEN);
        bitmap.setPixel(0, 1, Color.BLUE);
        bitmap.setPixel(1, 1, Color.RED);

        final List<RGBColor> dominantColors = ColorUtils.getDominantColors(bitmap, 2);

        assertEquals(2, dominantColors.size());
        final var testColorRed = new RGBColor(Color.red(Color.RED), Color.green(Color.RED), Color.blue(Color.RED));
        assertEquals(testColorRed, dominantColors.get(0));
        final var testColorGreen = new RGBColor(Color.red(Color.GREEN), Color.green(Color.GREEN), Color.blue(Color.GREEN));
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
        assertEquals(new RGBColor(Color.red(Color.RED), Color.green(Color.RED), Color.blue(Color.RED)), dominantColors.get(0));
    }


}


