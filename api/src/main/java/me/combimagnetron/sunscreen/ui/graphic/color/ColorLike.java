package me.combimagnetron.sunscreen.ui.graphic.color;

import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ColorLike {

    /**
     * @return the red value of this color
     */
    int red();

    /**
     * @return the green value of this color
     */
    int green();

    /**
     * @return the blue value of this color
     */
    int blue();

    /**
     * @return the alpha value of this color
     */
    int alpha();

    /**
     * @return the rgb representation of this color
     */
    default int rgb() {
        return (red() << 16) | (green() << 8) | blue();
    }

    default int rgba() {
        return (int) (((long)alpha() << 24) | (red() << 16) | (green() << 8) | blue());
    }
    /**
     * @return the text color representation of this color
     */
    @NotNull TextColor textColor();

    /**
     * @param color the text color to convert to a color
     * @return TextColor converted to a Color
     */
    static @NotNull ColorLike of(@NotNull TextColor color) {
        return SimpleArgbColor.of(color);
    }

    static @Nullable ColorLike hex(@NotNull String hexColor) {
        return switch (hexColor.length()) {
            case 6 -> rgbHex(hexColor);
            case 8 -> argbHex(hexColor);
            default -> null;
        };
    }

    static @NotNull ColorLike rgbHex(@NotNull String hexColor) {
        return new SimpleArgbColor(
                Integer.valueOf(hexColor.substring(0, 2), 16),
                Integer.valueOf(hexColor.substring(2, 4), 16),
                Integer.valueOf(hexColor.substring(4, 6), 16),
                255);
    }


    static @NotNull ColorLike argbHex(@NotNull String hexColor) {
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
    static @NotNull ColorLike of(int red, int green, int blue, int alpha) {
        return SimpleArgbColor.of(red, green, blue, alpha);
    }

    /**
     * @param red int value between 0 and 255, representing the red value of the color
     * @param green int value between 0 and 255, representing the green value of the color
     * @param blue int value between 0 and 255, representing the blue value of the color
     * @return a new color with the given red, green, and blue values
     */
    static @NotNull ColorLike of(int red, int green, int blue) {
        return SimpleArgbColor.of(red, green, blue);
    }

    /**
     * @param rgb int value representing the red, green, and blue values of the color
     * @return a new color with the given red, green, and blue values
     */
    static @NotNull ColorLike of(int rgb) {
        return SimpleArgbColor.of(rgb);
    }

    /**
     * @param rgb int value representing the red, green, and blue values of the color
     * @param alpha int value between 0 and 255, representing the alpha value of the color
     * @return a new color with the given red, green, blue, and alpha values
     */
    static @NotNull ColorLike of(int rgb, int alpha) {
        return SimpleArgbColor.of(rgb, alpha);
    }

    record SimpleArgbColor(int red, int green, int blue, int alpha) implements ColorLike {

        public static @NotNull ColorLike of(int red, int green, int blue, int alpha) {
            return new SimpleArgbColor(red, green, blue, alpha);
        }

        public static @NotNull ColorLike of(int red, int green, int blue) {
            return new SimpleArgbColor(red, green, blue, 255);
        }

        public static @NotNull ColorLike of(int rgb) {
            return new SimpleArgbColor((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF, 255);
        }

        public static @NotNull ColorLike of(int rgb, int alpha) {
            return new SimpleArgbColor((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF, alpha);
        }

        public static @NotNull ColorLike of(@NotNull TextColor color) {
            return new SimpleArgbColor(color.red(), color.green(), color.blue(), 255);
        }

        @Override
        public @NotNull TextColor textColor() {
            return TextColor.color(red, green, blue);
        }
    }


}
