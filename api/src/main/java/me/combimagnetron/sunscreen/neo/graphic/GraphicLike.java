package me.combimagnetron.sunscreen.neo.graphic;

import me.combimagnetron.sunscreen.neo.graphic.modifier.GraphicModifier;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public interface GraphicLike<G extends GraphicLike<G>> {

    @NotNull G modifier(@NotNull GraphicModifier modifier);

    @NotNull BufferedImage image();

    @NotNull BufferedColorSpace bufferedColorSpace();

}
