package com.silverhorse.topfivecolors.imageprocessing.interfaces;

import com.silverhorse.topfivecolors.model.ColorPercentage;

import java.util.List;
import java.util.Map;

public interface ColorExtractor {
    List<ColorPercentage> extractDominantColors(final Map<Integer, Integer> colorCountMap, final int numberOfColors, final int totalPixels);
}