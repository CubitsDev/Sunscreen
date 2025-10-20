package me.combimagnetron.sunscreen.neo.graphic.color;

import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

record SimpleArgbColor(int red, int green, int blue, int alpha) implements Color {
    static final SimpleArgbColor NONE = new SimpleArgbColor(0, 0, 0, 0);

    @Override
    public @NotNull TextColor textColor() {
        return TextColor.color(red, green, blue);
    }

}