package me.combimagnetron.sunscreen.neo.render;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

public record Viewport(@NotNull Vec2i totalSize, @NotNull Vec2i currentView, @NotNull Vec2i position) {

    public @NotNull Viewport withCurrentView(@NotNull Vec2i currentView) {
        return new Viewport(totalSize, currentView, position);
    }

}
