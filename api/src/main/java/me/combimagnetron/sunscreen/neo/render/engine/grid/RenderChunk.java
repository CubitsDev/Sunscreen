package me.combimagnetron.sunscreen.neo.render.engine.grid;

import me.combimagnetron.passport.util.math.Vec3f;
import me.combimagnetron.sunscreen.neo.graphic.BufferedColorSpace;
import org.jetbrains.annotations.NotNull;

public interface RenderChunk {

    @NotNull BufferedColorSpace bufferedColorSpace();

    @NotNull Vec3f position();

    float scale();

    int contentHash();

}
