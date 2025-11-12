package me.combimagnetron.sunscreen.neo.element.tree;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

public record ProcessedElementMetadata(boolean hasRelativeProperties, int depth, @NotNull Vec2i totalSize) {

    public boolean isBranch() {
        return depth > 0;
    }

}
