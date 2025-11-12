package me.combimagnetron.sunscreen.neo.render;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

public record Viewport(@NotNull Vec2i totalSize, @NotNull Vec2i currentView) {
}
