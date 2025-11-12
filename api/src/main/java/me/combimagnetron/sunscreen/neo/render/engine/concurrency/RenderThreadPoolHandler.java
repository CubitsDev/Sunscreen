package me.combimagnetron.sunscreen.neo.render.engine.concurrency;

import me.combimagnetron.passport.util.math.Vec2f;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.render.engine.encode.MapEncoder;
import me.combimagnetron.sunscreen.neo.render.engine.grid.RenderChunk;
import me.combimagnetron.sunscreen.neo.render.engine.grid.RenderGrid;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class RenderThreadPoolHandler {
    private final static ExecutorService EXECUTOR_SERVICE = Executors.newVirtualThreadPerTaskExecutor();

    public @NotNull Set<byte[]> renderGridVirtual(@NotNull RenderGrid renderGrid) {
        final Vec2i gridSize = renderGrid.size();
        final int arraySize = gridSize.x() * gridSize.y();
        byte[][] bytes = new byte[arraySize][];
        for (int i = 0; i < renderGrid.chunks().length; i++) {
            int finalI = i;
            EXECUTOR_SERVICE.submit(() -> {
                final RenderChunk chunk = renderGrid.chunks()[finalI];
                byte[] data = new MapEncoder(chunk).bytes().toByteArray();
                if (chunk.position() == null) throw new IllegalStateException("State cannot be null when encoding RenderChunk.");
                bytes[position(chunk.position())] = data;
            });
        }
        return Arrays.stream(bytes).collect(Collectors.toSet());
    }

    private static int position(@NotNull Vec2f position) {
        return position.xi() * position.yi();
    }


}
