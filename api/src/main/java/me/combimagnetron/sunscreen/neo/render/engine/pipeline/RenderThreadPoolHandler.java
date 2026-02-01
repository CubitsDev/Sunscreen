package me.combimagnetron.sunscreen.neo.render.engine.pipeline;

import me.combimagnetron.passport.util.math.Vec2f;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.MenuRoot;
import me.combimagnetron.sunscreen.neo.render.engine.MenuRendererMagicValues;
import me.combimagnetron.sunscreen.neo.render.engine.encode.MapEncoder;
import me.combimagnetron.sunscreen.neo.render.engine.grid.EncodedRenderChunk;
import me.combimagnetron.sunscreen.neo.render.engine.grid.ProcessedRenderChunk;
import me.combimagnetron.sunscreen.neo.render.engine.grid.RenderGrid;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.UUID;
import java.util.concurrent.*;

public class RenderThreadPoolHandler {
    private final static ScheduledExecutorService EXECUTOR_SERVICE =
            Executors.newScheduledThreadPool(1, Thread.ofVirtual().factory());


    /*TODO:
    fix deze achterlijke kanker zooi. haal dat zaagsel uit je hoere kop en hou hier rekening met de positioning shit vanaf center, kopieer dus de bullshit
    vanuit renderphase van send en stouw die hier in zodat ik daar alleen maar hoef te loopen door de array en te sturen.
            int xTiles = size.x() / 128;
            int yTiles = size.y() / 128;
            int totalTiles = xTiles * yTiles;
            PlatformProtocolIntermediate intermediate = SunscreenLibrary.library().intermediate();
            IntStream.range(0, totalTiles).parallel().mapToObj(i -> new TilePos(i, (i % xTiles) * 128, (i / xTiles) * 128
            )).forEach(tilePos -> {
                EncodedRenderChunk renderChunk = chunks[tilePos.index];
                intermediate.spawnAndFillItemFrame(user, )
            });

     */
    public static @NotNull EncodedRenderChunk @NotNull [] renderGridVirtual(@NotNull RenderGrid renderGrid) {
//        final Vec2i gridSize = renderGrid.size();
//        final int arraySize = gridSize.x() * gridSize.y();
//        EncodedRenderChunk[] chunks = new EncodedRenderChunk[arraySize];
//        for (int i = 0; i < renderGrid.chunks().length; i++) {
//            int finalI = i;
//            EXECUTOR_SERVICE.submit(() -> {
//                final ProcessedRenderChunk chunk = renderGrid.chunks()[finalI];
//                byte[] data = new MapEncoder(chunk).bytes().toByteArray();
//                chunks[position(chunk.position())] = new EncodedRenderChunk(data, chunk.position(), chunk.scale() + MenuRendererMagicValues.SCALE_ADDITION);
//            });
//        }
//        return chunks;
        return null;
    }

    public static @NotNull RenderPipeline start(@NotNull SunscreenUser<?> user, @NotNull MenuRoot menuRoot) {
        return new RenderPipeline(EXECUTOR_SERVICE, user, UUID.randomUUID(), menuRoot.elementLikes());
    }

    private static int position(@NotNull Vec2f position) {
        return position.xi() * position.yi();
    }

}
