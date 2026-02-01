package me.combimagnetron.sunscreen.neo.render;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

public record ScreenInfo(@NotNull Viewport viewport) {

    public @NotNull Vec2i screenSize() {
        return viewport.totalSize();
    }

}
