package me.combimagnetron.sunscreen.neo.element.impl;

import me.combimagnetron.sunscreen.neo.element.GenericModernElement;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.loader.Component;
import me.combimagnetron.sunscreen.util.data.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class ImageElement<G extends GraphicLike<G>> extends GenericModernElement<ImageElement<G>> {

    public ImageElement(@NotNull Identifier identifier, @NotNull G graphicLike) {
        super(identifier);
    }

}
