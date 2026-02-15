package me.combimagnetron.sunscreen.neo.render.engine.cache;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import it.unimi.dsi.fastutil.ints.*;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import me.combimagnetron.passport.util.math.Vec3f;
import me.combimagnetron.sunscreen.neo.render.engine.grid.RenderChunk;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class RenderCache {
    private final static int MIN_MAP_ID = Integer.MAX_VALUE - 500;
    private final AtomicInteger mapIdCounter = new AtomicInteger(MIN_MAP_ID);
    private final Table<Float, Vec3f, Integer> scaleToIdByPositionTable = HashBasedTable.create();
    private final Int2ObjectMap<RenderChunk> idToChunkMap = new Int2ObjectArrayMap<>();
    private final Object2IntMap<RenderChunk> invertedIdToChunkMap = new Object2IntArrayMap<>();
    private final Int2IntMap idToHashcodeMap = new Int2IntArrayMap();

    public void add(@NotNull RenderChunk renderChunk, int mapId) {
        scaleToIdByPositionTable.put(renderChunk.scale(), renderChunk.position(), mapId);
        put(mapId, renderChunk);
        idToHashcodeMap.put(mapId, renderChunk.contentHash());
    }

    public int next(@NotNull RenderChunk renderChunk) {
        int id = mapIdCounter.getAndIncrement();
        add(renderChunk, id);
        return id;
    }

    public void remove(float scale) {
        Collection<Integer> ids = scaleToIdByPositionTable.row(scale).values();
        for (int id : ids) {
            remove(id);
        }
        scaleToIdByPositionTable.row(scale).clear();
    }

    public boolean changed(@NotNull RenderChunk renderChunk, int mapId) {
        return renderChunk.contentHash() != idToHashcodeMap.get(mapId);
    }

    public void update(@NotNull RenderChunk renderChunk, int mapId) {
        remove(mapId);
        add(renderChunk, mapId);
    }

    public Integer byPosAndScale(float scale, @NotNull Vec3f vec3f) {
        return scaleToIdByPositionTable.get(scale, vec3f);
    }

    public @NotNull Collection<Float> scales() {
        return scaleToIdByPositionTable.rowKeySet();
    }

    public @NotNull Collection<Integer> idsByScale(@NotNull Float scale) {
        System.out.println("id scale hello hello why ho");
        return scaleToIdByPositionTable.row(scale).values();
    }

    public boolean isEmpty() {
        return scaleToIdByPositionTable.isEmpty();
    }

    public @Nullable RenderChunk get(int mapId) {
        return idToChunkMap.get(mapId);
    }

    public Integer id(@Nullable RenderChunk chunk) {
        return invertedIdToChunkMap.getInt(chunk);
    }

    private void put(int id, @NotNull RenderChunk chunk) {
        idToChunkMap.put(id, chunk);
        invertedIdToChunkMap.put(chunk, id);
    }

    private void remove(int id) {
        RenderChunk chunk = idToChunkMap.remove(id);
        if (chunk != null) invertedIdToChunkMap.removeInt(chunk);
        idToHashcodeMap.remove(id);
    }

    public @Nullable RenderChunk get(float scale, @NotNull Vec3f position) {
        Integer id = scaleToIdByPositionTable.get(scale, position);
        if (id == null) return null;
        return idToChunkMap.get((int) id);
    }

}
