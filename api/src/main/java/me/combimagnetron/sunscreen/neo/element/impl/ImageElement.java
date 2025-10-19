package me.combimagnetron.sunscreen.neo.element.impl;

import me.combimagnetron.sunscreen.neo.element.GenericModernElement;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.util.data.Identifier;
import org.jetbrains.annotations.NotNull;

public class ImageElement<G extends GraphicLike<G>> extends GenericModernElement<ImageElement<G>> {

    public ImageElement(@NotNull Identifier identifier, @NotNull G graphicLike) {
        super(identifier);
    }

}
