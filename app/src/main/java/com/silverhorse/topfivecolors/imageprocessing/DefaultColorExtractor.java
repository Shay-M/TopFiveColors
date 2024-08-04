package com.silverhorse.topfivecolors.imageprocessing;

import com.silverhorse.topfivecolors.imageprocessing.interfaces.ColorExtractor;
import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultColorExtractor implements ColorExtractor {
    @Override
    public List<ColorPercentage> extractDominantColors(final Map<Integer, Integer> colorCountMap, final int numberOfColors, final int totalPixels) {
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
}