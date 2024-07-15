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


    public static List<RGBColor> getRandomColors(int numberOfColors) {
        List<RGBColor> randomColors = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfColors; i++) {
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            int color = android.graphics.Color.rgb(red, green, blue); // Convert RGB to color integer
            RGBColor rgbColor = new RGBColor(color);
            randomColors.add(rgbColor);
        }

        return randomColors;
    }


    public static List<RGBColor> getDominantColors2(Bitmap bitmap, int numberOfColors) {
        if (bitmap == null) {
            return Collections.emptyList();
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int pixelCount = width * height;
        int[] pixels = new int[pixelCount];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        // Initialize K-means with random centroids
        List<Integer> centroids = initializeCentroids(numberOfColors);

        boolean centroidsChanged;
        do {
            List<List<Integer>> clusters = new ArrayList<>();
            for (int i = 0; i < numberOfColors; i++) {
                clusters.add(new ArrayList<>());
            }

            // Assign pixels to clusters
            for (int pixel : pixels) {
                int closestCentroidIndex = findClosestCentroid(pixel, centroids);
                clusters.get(closestCentroidIndex).add(pixel);
            }

            // Calculate new centroids and check if they have changed
            centroidsChanged = false;
            for (int i = 0; i < numberOfColors; i++) {
                if (!clusters.get(i).isEmpty()) {
                    int newCentroid = calculateMean(clusters.get(i));
                    if (newCentroid != centroids.get(i)) {
                        centroids.set(i, newCentroid);
                        centroidsChanged = true;
                    }
                }
            }
        } while (centroidsChanged);

        // Convert centroids to RGBColor objects and return them
        List<RGBColor> dominantColors = new ArrayList<>();
        for (int centroid : centroids) {
            dominantColors.add(new RGBColor(centroid));
        }

        return dominantColors;
    }

    private static List<Integer> initializeCentroids(int numberOfColors) {
        List<Integer> centroids = new ArrayList<>();
        for (int i = 0; i < numberOfColors; i++) {
            centroids.add(Color.rgb((int) (Math.random() * 256),
                    (int) (Math.random() * 256),
                    (int) (Math.random() * 256)));
        }
        return centroids;
    }

    private static int findClosestCentroid(int pixel, List<Integer> centroids) {
        int minDistance = Integer.MAX_VALUE;
        int closestCentroidIndex = 0;
        for (int i = 0; i < centroids.size(); i++) {
            int distance = calculateDistance(pixel, centroids.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                closestCentroidIndex = i;
            }
        }
        return closestCentroidIndex;
    }

    private static int calculateDistance(int color1, int color2) {
        int red1 = Color.red(color1);
        int green1 = Color.green(color1);
        int blue1 = Color.blue(color1);
        int red2 = Color.red(color2);
        int green2 = Color.green(color2);
        int blue2 = Color.blue(color2);
        return (red1 - red2) * (red1 - red2) + (green1 - green2) * (green1 - green2) + (blue1 - blue2) * (blue1 - blue2);
    }

    private static int calculateMean(List<Integer> cluster) {
        if (cluster.isEmpty()) {
            return 0;
        }
        int sumRed = 0;
        int sumGreen = 0;
        int sumBlue = 0;
        for (int pixel : cluster) {
            sumRed += Color.red(pixel);
            sumGreen += Color.green(pixel);
            sumBlue += Color.blue(pixel);
        }
        int meanRed = sumRed / cluster.size();
        int meanGreen = sumGreen / cluster.size();
        int meanBlue = sumBlue / cluster.size();
        return Color.rgb(meanRed, meanGreen, meanBlue);
    }
}



