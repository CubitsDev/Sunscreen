package me.combimagnetron.sunscreen.neo.graphic.text.style.impl.color;

import me.combimagnetron.sunscreen.neo.graphic.color.ColorLike;
import me.combimagnetron.sunscreen.neo.graphic.color.SpacedColorLike;
import me.combimagnetron.sunscreen.neo.graphic.text.style.Style;
import org.jetbrains.annotations.NotNull;

public interface TextColor extends SpacedColorLike, Style<TextColor> {

    static @NotNull TextColor color(@NotNull ColorLike colorLike) {
        return TextColorImpl.color(colorLike);
    }

    static @NotNull TextColor color(@NotNull Gradient gradient) {
        return TextColorImpl.color(gradient);
    }

}
