package com.silverhorse.topfivecolors.imageprocessing;

import android.graphics.Bitmap;
import android.util.Log;

import com.silverhorse.topfivecolors.imageprocessing.interfaces.BitmapResizer;
import com.silverhorse.topfivecolors.imageprocessing.interfaces.ColorCounter;
import com.silverhorse.topfivecolors.imageprocessing.interfaces.ColorExtractor;
import com.silverhorse.topfivecolors.model.ColorPercentage;
import com.silverhorse.topfivecolors.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BitmapUtils {
    private static final String TAG = "BitmapUtils";
    private static final int BUCKET_SIZE = 16;
    private static final int SCALE_FACTOR = 4;
    private static final int NUMBER_OF_THREADS = 4;

    private final BitmapResizer mBitmapResizer;
    private final ColorCounter mColorCounter;
    private final ColorExtractor mColorExtractor;

    public BitmapUtils(final BitmapResizer bitmapResizer, final ColorCounter colorCounter, final ColorExtractor colorExtractor) {
        mBitmapResizer = bitmapResizer;
        mColorCounter = colorCounter;
        mColorExtractor = colorExtractor;
    }

    public List<ColorPercentage> getDominantColors(final Bitmap bitmap, final int bucketSize) {
        if (bitmap == null || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            Log.d(TAG, "Bitmap is null or has invalid dimensions");
            return Collections.emptyList();
        }
        final Bitmap resizedBitmap = mBitmapResizer.resize(bitmap, SCALE_FACTOR);
        final Map<Integer, Integer> colorCountMap = processBitmap(resizedBitmap, bucketSize);
        return mColorExtractor.extractDominantColors(colorCountMap, Constants.NUMBER_OF_COLORS, resizedBitmap.getWidth() * resizedBitmap.getHeight());
    }

    public List<ColorPercentage> getDominantColors(final Bitmap bitmap) {
        return getDominantColors(bitmap, BUCKET_SIZE);
    }

    private Map<Integer, Integer> processBitmap(final Bitmap bitmap, final int bucketSize) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final int quarterWidth = width / 2;
        final int quarterHeight = height / 2;

        final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        final List<Future<Map<Integer, Integer>>> futures = new ArrayList<>();
        futures.add(executor.submit(() -> mColorCounter.countColors(bitmap, 0, 0, quarterWidth, quarterHeight, bucketSize)));
        futures.add(executor.submit(() -> mColorCounter.countColors(bitmap, quarterWidth, 0, width, quarterHeight, bucketSize)));
        futures.add(executor.submit(() -> mColorCounter.countColors(bitmap, 0, quarterHeight, quarterWidth, height, bucketSize)));
        futures.add(executor.submit(() -> mColorCounter.countColors(bitmap, quarterWidth, quarterHeight, width, height, bucketSize)));

        final Map<Integer, Integer> colorCountMap = new ConcurrentHashMap<>();
        for (Future<Map<Integer, Integer>> future : futures) {
            try {
                combineColorCounts(colorCountMap, future.get());
            } catch (InterruptedException | ExecutionException e) {
                Log.e(TAG, "Error processing color counts", e);
            }
        }
        executor.shutdown();
        return colorCountMap;
    }

    private void combineColorCounts(final Map<Integer, Integer> mainMap, final Map<Integer, Integer> partialMap) {
        for (Map.Entry<Integer, Integer> entry : partialMap.entrySet()) {
            mainMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }
}