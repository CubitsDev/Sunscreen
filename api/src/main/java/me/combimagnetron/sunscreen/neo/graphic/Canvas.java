package me.combimagnetron.sunscreen.neo.graphic;

import me.combimagnetron.sunscreen.neo.graphic.modifier.GraphicModifier;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public class Canvas implements GraphicLike<Canvas> {

    @Override
    public @NotNull Canvas modifier(@NotNull GraphicModifier modifier) {
        return null;
    }

    @Override
    public @NotNull BufferedImage image() {
        return null;
    }

    @Override
    public @NotNull BufferedColorSpace bufferedColorSpace() {
        return null;
    }

    public @NotNull Canvas text(Component text) {
        return this;
    }



}
