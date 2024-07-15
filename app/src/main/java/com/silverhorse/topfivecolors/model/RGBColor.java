package com.silverhorse.topfivecolors.model;

import java.util.Objects;

public class RGBColor {
    private int color;

    public RGBColor(final int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(final int color) {
        this.color = color;
    }

    public int getRed() {
        return (color >> 16) & 0xFF;
    }

    public int getGreen() {
        return (color >> 8) & 0xFF;
    }

    public int getBlue() {
        return color & 0xFF;
    }

    public String RGBString() {
        return "R:" + getRed() +
                " G:" + getGreen() +
                " B:" + getBlue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RGBColor rgbColor = (RGBColor) o;
        return color == rgbColor.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}