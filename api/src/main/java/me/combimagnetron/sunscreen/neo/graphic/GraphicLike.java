package me.combimagnetron.sunscreen.neo.graphic;

import me.combimagnetron.sunscreen.neo.graphic.modifier.GraphicModifier;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public interface GraphicLike<G extends GraphicLike<G>> {

    @NotNull <M> G modifier(@NotNull GraphicModifier<M> modifier);

    @NotNull BufferedColorSpace bufferedColorSpace();

}
