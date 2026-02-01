package me.combimagnetron.sunscreen.neo.graphic.text.style.impl.color;

import me.combimagnetron.sunscreen.neo.graphic.color.Color;
import me.combimagnetron.sunscreen.neo.graphic.color.ColorLike;
import me.combimagnetron.sunscreen.neo.graphic.text.style.Style;
import org.jetbrains.annotations.NotNull;

public interface Highlight extends ColorLike, Style<Highlight> {

    static @NotNull Highlight highlight(@NotNull Color color) {
        return new HighlightImpl(color);
    }

}
