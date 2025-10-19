package me.combimagnetron.sunscreen.neo.graphic.color;

import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

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
     * @return the label color representation of this color
     */
    @NotNull TextColor textColor();

}
