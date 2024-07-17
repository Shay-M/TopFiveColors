package com.silverhorse.topfivecolors.utils;

import android.graphics.Color;

import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KMeansCluster {

    public static List<float[]> kMeans(final List<float[]> data, final int k, final int maxIterations) {
        final List<float[]> centroids = initializeCentroids(data, k);
        final List<List<float[]>> clusters = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            clusters.add(new ArrayList<>());
        }

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Assign points to nearest centroid
            for (float[] point : data) {
                int closestCentroid = getClosestCentroid(point, centroids);
                clusters.get(closestCentroid).add(point);
            }

            // Update centroids
            for (int i = 0; i < k; ++i) {
                centroids.set(i, calculateCentroid(clusters.get(i)));
                clusters.get(i).clear();
            }
        }

        return centroids;
    }

    private static List<float[]> initializeCentroids(final List<float[]> data, final int k) {
        final List<float[]> centroids = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < k; ++i) {
            centroids.add(data.get((int) (Math.random() * data.size())));
        }
        return centroids;
    }

    private static int getClosestCentroid(final float[] point, final List<float[]> centroids) {
        int closestCentroid = 0;
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < centroids.size(); ++i) {
            double distance = calculateDistance(point, centroids.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                closestCentroid = i;
            }
        }
        return closestCentroid;
    }

    private static double calculateDistance(final float[] point1, final float[] point2) {
        double sum = 0;
        for (int i = 0; i < point1.length; ++i) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }
        return Math.sqrt(sum);
    }

    private static float[] calculateCentroid(final List<float[]> cluster) {
        final float[] centroid = new float[3];
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

    public static List<ColorPercentage> calculateColorPercentages(final List<float[]> centroids, final int[] pixels) {
        final Map<Integer, Integer> colorCounts = new ConcurrentHashMap<>();
        for (int color : pixels) {
            float[] colorArray = new float[]{Color.red(color), Color.green(color), Color.blue(color)};
            int closestCentroid = getClosestCentroid(colorArray, centroids);
            int centroidColor = Color.rgb((int) centroids.get(closestCentroid)[0], (int) centroids.get(closestCentroid)[1], (int) centroids.get(closestCentroid)[2]);
            colorCounts.merge(centroidColor, 1, Integer::sum);
        }

        final List<ColorPercentage> colorPercentages = Collections.synchronizedList(new ArrayList<>());
        final int totalPixels = pixels.length;
        for (Map.Entry<Integer, Integer> entry : colorCounts.entrySet()) {
            int color = entry.getKey();
            int count = entry.getValue();
            float percentage = (count / (float) totalPixels) * 100;
            colorPercentages.add(new ColorPercentage(color, percentage));
        }

        return colorPercentages;
    }
}
