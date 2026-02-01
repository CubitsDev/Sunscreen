package me.combimagnetron.sunscreen.neo.render.engine.grid;

import me.combimagnetron.passport.util.math.Vec2f;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.passport.util.math.Vec3f;
import me.combimagnetron.sunscreen.neo.property.Position;
import me.combimagnetron.sunscreen.neo.render.engine.encode.MapEncoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class GridLayer {
    private final Map<Vec3f, RenderChunk> chunks = new HashMap<>();
    private final float scale;

    public GridLayer(float scale) {
        this.scale = scale;
    }

    public @NotNull GridLayer chunk(@NotNull Vec3f pos, @NotNull RenderChunk chunk) {
        chunks.put(pos, chunk);
        return this;
    }

    public @Nullable RenderChunk at(@NotNull Vec2f pos) {
        return chunks.get(pos);
    }

    public float scale() {
        return scale;
    }

    public @NotNull RenderChunk @NotNull [] chunks() {
        return chunks.values().toArray(new RenderChunk[0]);
    }

    public @NotNull Collection<EncodedRenderChunk> encodedChunks() {
        final List<EncodedRenderChunk> chunkList = new ArrayList<>();
        for (Map.Entry<Vec3f, RenderChunk> entry : chunks.entrySet()) {
            Vec3f position = entry.getKey();
            RenderChunk chunk = entry.getValue();
            if (chunk instanceof EncodedRenderChunk encodedRenderChunk) {
                chunkList.add(encodedRenderChunk);
            } else if (chunk instanceof ProcessedRenderChunk processedRenderChunk) {
                chunkList.add(process(processedRenderChunk));
            }
        }
        return chunkList;
    }

    private @NotNull EncodedRenderChunk process(ProcessedRenderChunk chunk) {
        byte[] data = new MapEncoder(chunk).bytes().toByteArray();
        return new EncodedRenderChunk(data, chunk.position(), chunk.scale(), chunk.bufferedColorSpace());
    }

}
