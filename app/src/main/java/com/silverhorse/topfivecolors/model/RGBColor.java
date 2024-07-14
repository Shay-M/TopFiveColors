package com.silverhorse.topfivecolors.model;

import java.util.Objects;

public class RGBColor {
    private int red;
    private int green;
    private int blue;

    public RGBColor(final int red, final int green, final int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(final int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(final int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(final int blue) {
        this.blue = blue;
    }

    public String RGBString() {
        return "R:" + red +
                " G:" + green +
                " B:" + blue;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RGBColor rgbColor = (RGBColor) o;
        return red == rgbColor.red &&
                green == rgbColor.green &&
                blue == rgbColor.blue;
    }

    // Override hashCode() method to match equals()
    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }
}
