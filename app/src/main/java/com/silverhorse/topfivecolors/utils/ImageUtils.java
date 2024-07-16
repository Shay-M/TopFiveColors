/*package com.silverhorse.topfivecolors.utils;

import android.graphics.Bitmap;
import android.util.Log;

import com.silverhorse.topfivecolors.model.RGBColor;

import java.util.List;

public class ImageUtils {
    public static List<RGBColor> getDominantColors(final Bitmap bitmap) {
        Log.d("TAG", "getDominantColors: " + bitmap);
//        return ColorUtils.getRandomColors(5);
        return ColorUtils.getDominantColors(bitmap,5);
    }
}*//*
 */
/*



package com.silverhorse.topfivecolors.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import com.silverhorse.topfivecolors.model.RGBColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImageUtils {
    private static final String TAG = "ImageUtils";

    public static List<RGBColor> getDominantColors(final Bitmap bitmap) {
        if (bitmap == null) {
            Log.d(TAG, "Bitmap is null");
            return Collections.emptyList();
        }

        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final int quarterWidth = width / 2;
        final int quarterHeight = height / 2;

        // Create thread pool
        final ExecutorService executor = Executors.newFixedThreadPool(4);
        final List<Future<Map<Integer, Integer>>> futures = new ArrayList<>();

        // Divide the image into 4 parts and submit tasks for each part
        futures.add(executor.submit(() -> countColors(bitmap, 0, 0, quarterWidth, quarterHeight)));
        futures.add(executor.submit(() -> countColors(bitmap, quarterWidth, 0, width, quarterHeight)));
        futures.add(executor.submit(() -> countColors(bitmap, 0, quarterHeight, quarterWidth, height)));
        futures.add(executor.submit(() -> countColors(bitmap, quarterWidth, quarterHeight, width, height)));


        // Combine color counts from all futures
        final Map<Integer, Integer> colorCountMap = new HashMap<>();
        for (Future<Map<Integer, Integer>> future : futures) {
            try {
                combineColorCounts(colorCountMap, future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        // Convert color counts to RGBColor objects
        final List<RGBColor> dominantColors = extractDominantColors(colorCountMap, 5);

        return dominantColors;
    }

    private static void combineColorCounts(final Map<Integer, Integer> mainMap, final Map<Integer, Integer> partialMap) {
        for (Map.Entry<Integer, Integer> entry : partialMap.entrySet()) {
            mainMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }

    private static List<RGBColor> extractDominantColors(Map<Integer, Integer> colorCountMap, int numberOfColors) {
        List<Map.Entry<Integer, Integer>> sortedColors = new ArrayList<>(colorCountMap.entrySet());
        sortedColors.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        List<RGBColor> dominantColors = new ArrayList<>();
        for (int i = 0; i < numberOfColors && i < sortedColors.size(); i++) {
            int color = sortedColors.get(i).getKey();
            dominantColors.add(new RGBColor(color));
        }

        return dominantColors;
    }

    private static Map<Integer, Integer> countColors(final Bitmap bitmap, final int startX, final int startY, final int endX, final int endY) {
        Map<Integer, Integer> colorCountMap = new HashMap<>();
        for (int y = startY; y < endY; ++y) {
            for (int x = startX; x < endX; ++x) {
                final int pixel = bitmap.getPixel(x, y);
                final int color = Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel));
                colorCountMap.merge(color, 1, Integer::sum);
            }
        }

        return colorCountMap;
    }
}

*/
/*
package com.silverhorse.topfivecolors.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImageUtils {
    private static final String TAG = "ImageUtils";

    public static List<ColorPercentage> getDominantColors(final Bitmap bitmap) {
        if (bitmap == null) {
            Log.d(TAG, "Bitmap is null");
            return Collections.emptyList();
        }

        // Reduce the size of the bitmap to speed up the process
        final Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 4, bitmap.getHeight() / 4, false);

        final int width = resizedBitmap.getWidth();
        final int height = resizedBitmap.getHeight();
        final int quarterWidth = width / 2;
        final int quarterHeight = height / 2;

        // Create thread pool
        final ExecutorService executor = Executors.newFixedThreadPool(4);
        final List<Future<Map<Integer, Integer>>> futures = new ArrayList<>();

        // Divide the image into 4 parts and submit tasks for each part
        futures.add(executor.submit(() -> countColors(resizedBitmap, 0, 0, quarterWidth, quarterHeight)));
        futures.add(executor.submit(() -> countColors(resizedBitmap, quarterWidth, 0, width, quarterHeight)));
        futures.add(executor.submit(() -> countColors(resizedBitmap, 0, quarterHeight, quarterWidth, height)));
        futures.add(executor.submit(() -> countColors(resizedBitmap, quarterWidth, quarterHeight, width, height)));

        // Combine color counts from all futures
        final Map<Integer, Integer> colorCountMap = new HashMap<>();
        for (Future<Map<Integer, Integer>> future : futures) {
            try {
                combineColorCounts(colorCountMap, future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        // Extract dominant colors with percentages
        final List<ColorPercentage> dominantColors = extractDominantColors(colorCountMap, 5, width * height);

        return dominantColors;
    }

    private static void combineColorCounts(final Map<Integer, Integer> mainMap, final Map<Integer, Integer> partialMap) {
        for (Map.Entry<Integer, Integer> entry : partialMap.entrySet()) {
            mainMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }

    private static List<ColorPercentage> extractDominantColors(Map<Integer, Integer> colorCountMap, int numberOfColors, int totalPixels) {
        List<Map.Entry<Integer, Integer>> sortedColors = new ArrayList<>(colorCountMap.entrySet());
        sortedColors.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        List<ColorPercentage> dominantColors = new ArrayList<>();
        for (int i = 0; i < numberOfColors && i < sortedColors.size(); ++i) {
            int color = sortedColors.get(i).getKey();
            int count = sortedColors.get(i).getValue();
            float percentage = (count / (float) totalPixels) * 100;
            dominantColors.add(new ColorPercentage(color, percentage));
        }

        return dominantColors;
    }

    private static Map<Integer, Integer> countColors(final Bitmap bitmap, final int startX, final int startY, final int endX, final int endY) {
        Map<Integer, Integer> colorCountMap = new HashMap<>();
        for (int y = startY; y < endY; ++y) {
            for (int x = startX; x < endX; ++x) {
                final int pixel = bitmap.getPixel(x, y);
                final int color = Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel));
                colorCountMap.merge(color, 1, Integer::sum);
            }
        }

        return colorCountMap;
    }
}*/

package com.silverhorse.topfivecolors.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageUtils {
    private static final String TAG = "ImageUtils";

    public static List<ColorPercentage> getDominantColors(final Bitmap bitmap) {
        if (bitmap == null) {
            Log.d(TAG, "Bitmap is null");
            return Collections.emptyList();
        }

        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();

        // Extract pixels from the bitmap
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        // Convert pixel colors to float arrays
        List<float[]> pixelList = new ArrayList<>();
        for (int color : pixels) {
            pixelList.add(new float[]{Color.red(color), Color.green(color), Color.blue(color)});
        }

        // Perform K-means clustering
        List<float[]> centroids = kMeans(pixelList, 5, 10);

        // Calculate color percentages
        return calculateColorPercentages(centroids, pixels);
    }

    private static List<float[]> kMeans(List<float[]> data, int k, int maxIterations) {
        List<float[]> centroids = initializeCentroids(data, k);
        List<List<float[]>> clusters = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            clusters.add(new ArrayList<>());
        }

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Assign points to nearest centroid
            for (float[] point : data) {
                int closestCentroid = getClosestCentroid(point, centroids);
                clusters.get(closestCentroid).add(point);
            }

            // Update centroids
            for (int i = 0; i < k; i++) {
                centroids.set(i, calculateCentroid(clusters.get(i)));
                clusters.get(i).clear();
            }
        }

        return centroids;
    }

    private static List<float[]> initializeCentroids(List<float[]> data, int k) {
        List<float[]> centroids = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            centroids.add(data.get((int) (Math.random() * data.size())));
        }
        return centroids;
    }

    private static int getClosestCentroid(float[] point, List<float[]> centroids) {
        int closestCentroid = 0;
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < centroids.size(); i++) {
            double distance = calculateDistance(point, centroids.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                closestCentroid = i;
            }
        }
        return closestCentroid;
    }

    private static double calculateDistance(float[] point1, float[] point2) {
        double sum = 0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }
        return Math.sqrt(sum);
    }

    private static float[] calculateCentroid(List<float[]> cluster) {
        float[] centroid = new float[3];
        for (float[] point : cluster) {
            centroid[0] += point[0];
            centroid[1] += point[1];
            centroid[2] += point[2];
        }
        centroid[0] /= cluster.size();
        centroid[1] /= cluster.size();
        centroid[2] /= cluster.size();
        return centroid;
    }

    private static List<ColorPercentage> calculateColorPercentages(List<float[]> centroids, int[] pixels) {
        Map<Integer, Integer> colorCounts = new HashMap<>();
        for (int color : pixels) {
            float[] colorArray = new float[]{Color.red(color), Color.green(color), Color.blue(color)};
            int closestCentroid = getClosestCentroid(colorArray, centroids);
            int centroidColor = Color.rgb((int) centroids.get(closestCentroid)[0], (int) centroids.get(closestCentroid)[1], (int) centroids.get(closestCentroid)[2]);
            colorCounts.merge(centroidColor, 1, Integer::sum);
        }

        List<ColorPercentage> colorPercentages = new ArrayList<>();
        int totalPixels = pixels.length;
        for (Map.Entry<Integer, Integer> entry : colorCounts.entrySet()) {
            int color = entry.getKey();
            int count = entry.getValue();
            float percentage = (count / (float) totalPixels) * 100;
            colorPercentages.add(new ColorPercentage(color, percentage));
        }

        return colorPercentages;
    }
}
