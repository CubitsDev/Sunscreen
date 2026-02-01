package me.combimagnetron.sunscreen.neo.render.engine.grid;

import me.combimagnetron.passport.util.math.Vec3f;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RenderGrid {
    private final Map<Float, GridLayer> layers = new HashMap<>();

    public static @NotNull RenderGrid empty() {
        return new RenderGrid();
    }

    public @NotNull RenderGrid addAt(@NotNull RenderChunk chunk, @NotNull Vec3f position, float scale) {
        GridLayer layer = layers.computeIfAbsent(scale, GridLayer::new);
        layer.chunk(position, chunk);
        return this;
    }

    public @NotNull Collection<GridLayer> layers() {
        return layers.values();
    }

    public @Nullable GridLayer byScale(float scale) {
        return layers.get(scale);
    }

    public @NotNull Map<Float, GridLayer> layerMap() {
        return layers;
    }

}
