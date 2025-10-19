package me.combimagnetron.sunscreen.neo.graphic.color;

import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Color extends ColorLike {

    /**
     * @param color the label color to convert to a color
     * @return TextColor converted to a Color
     */
    static @NotNull Color of(@NotNull TextColor color) {
        return SimpleArgbColor.of(color);
    }

    static @Nullable Color hex(@NotNull String hexColor) {
        return switch (hexColor.length()) {
            case 6 -> rgbHex(hexColor);
            case 8 -> argbHex(hexColor);
            default -> null;
        };
    }

    static @NotNull Color rgbHex(@NotNull String hexColor) {
        return new SimpleArgbColor(
                Integer.valueOf(hexColor.substring(0, 2), 16),
                Integer.valueOf(hexColor.substring(2, 4), 16),
                Integer.valueOf(hexColor.substring(4, 6), 16),
                255);
    }


    static @NotNull Color argbHex(@NotNull String hexColor) {
        return new SimpleArgbColor(
                Integer.valueOf(hexColor.substring(2, 4), 16),
                Integer.valueOf(hexColor.substring(4, 6), 16),
                Integer.valueOf(hexColor.substring(6, 8), 16),
                Integer.valueOf(hexColor.substring(0, 2), 16));
    }

    /**
     * @param red int value between 0 and 255, representing the red value of the color
     * @param green int value between 0 and 255, representing the green value of the color
     * @param blue int value between 0 and 255, representing the blue value of the color
     * @param alpha int value between 0 and 255, representing the alpha value of the color
     * @return a new color with the given red, green, blue, and alpha values
     */
    static @NotNull Color of(int red, int green, int blue, int alpha) {
        return SimpleArgbColor.of(red, green, blue, alpha);
    }

    /**
     * @param red int value between 0 and 255, representing the red value of the color
     * @param green int value between 0 and 255, representing the green value of the color
     * @param blue int value between 0 and 255, representing the blue value of the color
     * @return a new color with the given red, green, and blue values
     */
    static @NotNull Color of(int red, int green, int blue) {
        return SimpleArgbColor.of(red, green, blue);
    }

    /**
     * @param rgb int value representing the red, green, and blue values of the color
     * @return a new color with the given red, green, and blue values
     */
    static @NotNull Color of(int rgb) {
        return SimpleArgbColor.of(rgb);
    }

    /**
     * @param rgb int value representing the red, green, and blue values of the color
     * @param alpha int value between 0 and 255, representing the alpha value of the color
     * @return a new color with the given red, green, blue, and alpha values
     */
    static @NotNull Color of(int rgb, int alpha) {
        return SimpleArgbColor.of(rgb, alpha);
    }


}
