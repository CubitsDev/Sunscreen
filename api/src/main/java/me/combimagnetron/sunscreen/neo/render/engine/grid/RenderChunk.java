package me.combimagnetron.sunscreen.neo.render.engine.grid;

import me.combimagnetron.passport.util.math.Vec2f;
import me.combimagnetron.sunscreen.neo.graphic.BufferedColorSpace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record RenderChunk(@NotNull BufferedColorSpace space, float scale, @Nullable Vec2f position) {

    public RenderChunk {
        //if (space.size().x() > 128 || space.size().y() > 128) throw new IllegalArgumentException("Space in RenderChunk cannot exceed 128x128. Contact the developers if this appears more than once.");
    }

}
