package me.combimagnetron.sunscreen.neo.graphic;

import me.combimagnetron.sunscreen.neo.graphic.color.ColorLike;
import me.combimagnetron.sunscreen.neo.graphic.modifier.GraphicModifier;
import me.combimagnetron.sunscreen.neo.property.Position;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.util.math.Vec2i;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public record Canvas(BufferedColorSpace bufferedColorSpace) implements GraphicLike<Canvas> {

    public Canvas(Vec2i size) {
        this(new BufferedColorSpace(size));
    }

    public static Canvas empty(Vec2i size) {
        return new Canvas(size);
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

    public @NotNull Canvas fill(@NotNull Position position, @NotNull Size size, @NotNull ColorLike colorLike) {
        Vec2i sizeVec = size.vec2i();
        Vec2i start = position.vec2i();
        if (sizeVec == null || start == null) return this;
        Vec2i end = start.add(sizeVec);
        bufferedColorSpace.fill(start, end, colorLike);
        return this;
    }

    public @NotNull Canvas color(@NotNull Vec2i position, @NotNull ColorLike colorLike) {
        bufferedColorSpace.color(position, colorLike);
        return this;
    }

    public @NotNull Canvas erase(@NotNull Vec2i position) {
        bufferedColorSpace.erase(position);
        return this;
    }

    private static void renderChildren(BufferedColorSpace bufferedColorSpace, Component component) {
        for (Component child : component.children()) {
            //render text
            renderChildren(bufferedColorSpace, child);
        }
    }

}
