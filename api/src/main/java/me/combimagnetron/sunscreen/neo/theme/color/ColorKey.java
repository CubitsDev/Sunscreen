package me.combimagnetron.sunscreen.neo.theme.color;

import me.combimagnetron.sunscreen.neo.graphic.color.ColorLike;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public record ColorKey(@NotNull String id) implements ColorLike {

    static @NotNull ColorKey colorKey(@NotNull String id) {
        return new ColorKey(id);
    }

    @Override
    public int red() {
        return 0;
    }

    @Override
    public int green() {
        return 0;
    }

    @Override
    public int blue() {
        return 0;
    }

    @Override
    public int alpha() {
        return 0;
    }

    @Override
    public @NotNull TextColor textColor() {
        return null;
    }
}
