package me.combimagnetron.sunscreen.neo.graphic.shape;

import me.combimagnetron.sunscreen.neo.graphic.color.ColorLike;
import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public interface Shape {

    @NotNull Vec2i squareSize();

    @NotNull BitSet shape();

    static @NotNull Rectangle rectangle(@NotNull Vec2i size) {
        return new Rectangle(size);
    }

}
