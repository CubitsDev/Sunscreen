package me.combimagnetron.sunscreen.neo.graphic;

import me.combimagnetron.sunscreen.neo.graphic.modifier.GraphicModifier;
import me.combimagnetron.sunscreen.util.math.Vec2i;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public class Canvas implements GraphicLike<Canvas> {
    private final BufferedColorSpace bufferedColorSpace;

    public Canvas(Vec2i size) {
        this.bufferedColorSpace = new BufferedColorSpace(size);
    }

    @Override
    public @NotNull <M> Canvas modifier(@NotNull GraphicModifier<M> modifier) {
        modifier.handler().apply(bufferedColorSpace, modifier.modifier(), modifier.context());
        return this;
    }

    @Override
    public @NotNull BufferedImage image() {
        return null;
    }

    @Override
    public @NotNull BufferedColorSpace bufferedColorSpace() {
        return bufferedColorSpace;
    }

    public @NotNull Canvas text(Component text) {
        renderChildren(bufferedColorSpace, text);
        return this;
    }

    private static void renderChildren(BufferedColorSpace bufferedColorSpace, Component component) {
        for (Component child : component.children()) {
            //render text
            renderChildren(bufferedColorSpace, child);
        }
    }

}
