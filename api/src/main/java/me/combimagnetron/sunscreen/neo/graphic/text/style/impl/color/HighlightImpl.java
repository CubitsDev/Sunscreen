package me.combimagnetron.sunscreen.neo.graphic.text.style.impl.color;

import me.combimagnetron.sunscreen.neo.graphic.color.Color;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public record HighlightImpl(@NotNull Color color) implements Highlight {

    @Override
    public int red() {
        return color.red();
    }

    @Override
    public int green() {
        return color.green();
    }

    @Override
    public int blue() {
        return color.blue();
    }

    @Override
    public int alpha() {
        return color.alpha();
    }

    @Override
    public @NotNull TextColor textColor() {
        return null;
    }

}
