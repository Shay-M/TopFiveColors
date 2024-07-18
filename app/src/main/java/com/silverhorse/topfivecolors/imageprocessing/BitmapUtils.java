package com.silverhorse.topfivecolors.imageprocessing;

import android.graphics.Bitmap;
import android.util.Log;

import com.silverhorse.topfivecolors.model.ColorPercentage;

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
    private static final int BUCKET_SIZE = 32;
    private static final int SCALE_FACTOR = 4;
    private static final int NUMBER_OF_THREADS = 4;

    /**
     * Gets the dominant colors from the given bitmap.
     *
     * @param bitmap the bitmap to process
     * @return a list of dominant colors with their percentages
     */
    public static List<ColorPercentage> getDominantColors(final Bitmap bitmap) {
        if (bitmap == null) {
            Log.d(TAG, "Bitmap is null");
            return Collections.emptyList();
        }
        // Reduce the size of the bitmap to speed up the process
        final Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / SCALE_FACTOR, bitmap.getHeight() / SCALE_FACTOR, false);

        final int width = resizedBitmap.getWidth();
        final int height = resizedBitmap.getHeight();
        final int quarterWidth = width / 2;
        final int quarterHeight = height / 2;

        // Divide the image into 4 parts and submit tasks for each part
        final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        final List<Future<Map<Integer, Integer>>> futures = new ArrayList<>();
        futures.add(executor.submit(() -> countColors(resizedBitmap, 0, 0, quarterWidth, quarterHeight, BUCKET_SIZE)));
        futures.add(executor.submit(() -> countColors(resizedBitmap, quarterWidth, 0, width, quarterHeight, BUCKET_SIZE)));
        futures.add(executor.submit(() -> countColors(resizedBitmap, 0, quarterHeight, quarterWidth, height, BUCKET_SIZE)));
        futures.add(executor.submit(() -> countColors(resizedBitmap, quarterWidth, quarterHeight, width, height, BUCKET_SIZE)));

        // Combine color counts from all futures
        final Map<Integer, Integer> colorCountMap = new ConcurrentHashMap<>();
        for (Future<Map<Integer, Integer>> future : futures) {
            try {
                combineColorCounts(colorCountMap, future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        // Extract dominant colors with percentages
        return extractDominantColors(colorCountMap, 5, width * height);
    }

    private static void combineColorCounts(final Map<Integer, Integer> mainMap, final Map<Integer, Integer> partialMap) {
        for (Map.Entry<Integer, Integer> entry : partialMap.entrySet()) {
            mainMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }

    private static List<ColorPercentage> extractDominantColors(final Map<Integer, Integer> colorCountMap, final int numberOfColors, final int totalPixels) {
        final List<Map.Entry<Integer, Integer>> sortedColors = new ArrayList<>(colorCountMap.entrySet());
        sortedColors.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        final List<ColorPercentage> dominantColors = new ArrayList<>();
        for (int i = 0; i < numberOfColors && i < sortedColors.size(); ++i) {
            int color = sortedColors.get(i).getKey();
            int count = sortedColors.get(i).getValue();
            float percentage = (count / (float) totalPixels) * 100;
            dominantColors.add(new ColorPercentage(color, percentage));
        }

        return dominantColors;
    }

    private static Map<Integer, Integer> countColors(final Bitmap bitmap, final int startX, final int startY, final int endX, final int endY, final int bucketSize) {
        final Map<Integer, Integer> colorCountMap = new ConcurrentHashMap<>();
        for (int y = startY; y < endY; ++y) {
            for (int x = startX; x < endX; ++x) {
                final int pixel = bitmap.getPixel(x, y);
                final int bucketedColor = ColorUtils.getBucketedColor(pixel, bucketSize);
                colorCountMap.merge(bucketedColor, 1, Integer::sum);
            }
        }

        return colorCountMap;
    }
}
