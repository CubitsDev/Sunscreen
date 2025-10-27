package me.combimagnetron.sunscreen.neo.element.impl;

import me.combimagnetron.sunscreen.neo.element.GenericModernElement;
import me.combimagnetron.sunscreen.neo.element.ModernElement;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.RenderAction;
import me.combimagnetron.sunscreen.neo.render.engine.pass.RenderPass;
import org.jetbrains.annotations.NotNull;

public class ImageElement<G extends GraphicLike<G>> extends GenericModernElement<ImageElement<G>> {
    private final G graphic;

    public ImageElement(@NotNull Identifier identifier, @NotNull G graphicLike) {
        super(identifier);
        this.graphic = graphicLike;
    }

    @Override
    public @NotNull RenderPass<ImageElement<G>, G> render(@NotNull Size property) {
        return RenderPass.pass(RenderPass.Origin.origin(this), RenderAction.simple(graphic));
    }
}
