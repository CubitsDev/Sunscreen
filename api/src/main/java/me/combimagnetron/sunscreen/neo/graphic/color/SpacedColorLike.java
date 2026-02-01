package me.combimagnetron.sunscreen.neo.graphic.color;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SpacedColorLike extends ColorLike {

    @Nullable ColorLike at(@NotNull Vec2i vec2i);

    @NotNull Vec2i size();

    boolean in(@NotNull Vec2i vec2i);

}
