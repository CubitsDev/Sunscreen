package me.combimagnetron.sunscreen.neo.render.engine.context;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.loader.MenuComponent;
import me.combimagnetron.sunscreen.neo.render.Viewport;
import me.combimagnetron.sunscreen.neo.render.engine.cache.RenderCache;
import me.combimagnetron.sunscreen.neo.render.engine.grid.ProcessedRenderChunk;
import me.combimagnetron.sunscreen.neo.theme.ModernTheme;
import me.combimagnetron.sunscreen.neo.theme.decorator.ThemeDecorator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class RenderContext {
    private final Map<Identifier, Object> objectStorage;
    private final RenderCache renderCache;
    private final Collection<byte[]> bytes;
    private final Map<Float, Canvas> canvasses;
    private final Collection<MenuComponent<?>> loadedComponents;
    private boolean stop = false;
    private Viewport viewport;
    private Iterable<ElementLike<?>> tree;

    public RenderContext(@Nullable Viewport viewport,
                         @Nullable Iterable<ElementLike<?>> tree, @NotNull Collection<MenuComponent<?>> loadedComponents) {
        this.viewport = viewport;
        this.tree = tree;
        this.objectStorage = new HashMap<>();
        this.loadedComponents = loadedComponents;
        this.renderCache = new RenderCache();
        this.bytes = new ArrayList<>();
        this.canvasses = new HashMap<>();
    }

    private RenderContext(@Nullable Viewport viewport,
                          @Nullable Iterable<ElementLike<?>> tree,
                          @NotNull Map<Identifier, Object> objectStorage,
                          @NotNull RenderCache renderCache,
                          @NotNull Collection<byte[]> bytes,
                          @NotNull Map<Float, Canvas> canvasses, @NotNull Collection<MenuComponent<?>> loadedComponents) {
        this.viewport = viewport;
        this.tree = tree;
        this.objectStorage = objectStorage;
        this.renderCache = renderCache;
        this.bytes = bytes;
        this.canvasses = canvasses;
        this.loadedComponents = loadedComponents;
    }

    public @NotNull RenderContext withViewport(@Nullable Viewport viewport) {
        return new RenderContext(viewport, tree, objectStorage, renderCache, bytes,
                canvasses, loadedComponents);
    }

    public @NotNull RenderContext withBytes(@Nullable Collection<byte[]> bytes) {
        return new RenderContext(viewport, tree, objectStorage, renderCache,
                bytes != null ? bytes : new ArrayList<>(), canvasses, loadedComponents);
    }

    public @NotNull RenderContext withTree(@Nullable Iterable<ElementLike<?>> tree) {
        return new RenderContext(viewport, tree, objectStorage, renderCache, bytes,
            canvasses, loadedComponents);
    }

    public @NotNull RenderContext withStart(@Nullable Map<Float, Canvas> start) {
        if (start != null) canvasses.putAll(start);
        return this;
    }

    public @NotNull ModernTheme theme() {
        return (ModernTheme) loadedComponents.stream().filter(menuComponent -> menuComponent.type().equals(ModernTheme.class)).findAny().orElseThrow();
    }

    public void stop(boolean stop) {
        this.stop = stop;
    }

    public boolean stop() {
        return stop;
    }

    public boolean viewportPresent() {
        return viewport != null;
    }

    public boolean treePresent() {
        return tree != null;
    }

    private int computeChunkId(@NotNull Vec2i position, float scale) {
        return Objects.hash(position.x(), position.y(), scale);
    }

    public boolean chunkChangedAt(@NotNull ProcessedRenderChunk chunk, int mapId) {
        return renderCache.changed(chunk, mapId);
    }

    public void registerChunk(@NotNull ProcessedRenderChunk chunk, int id) {
        renderCache.add(chunk, id);
    }

    public @Nullable Viewport viewport() {
        return viewport;
    }

    public @NotNull Collection<byte[]> bytes() {
        return bytes;
    }

    public @Nullable Iterable<ElementLike<?>> tree() {
        return tree;
    }

    public @NotNull Map<Float, Canvas> start() {
        return canvasses;
    }

    public @NotNull Collection<MenuComponent<?>> components() {
        return loadedComponents;
    }

    public @NotNull Map<Identifier, Object> objectStorage() {
        return objectStorage;
    }

    public @NotNull RenderCache renderCache() {
        return renderCache;
    }

    public @NotNull RenderContext clear() {
        this.viewport = null;
        this.tree = null;
        //this.renderCache.clear();
        return this;
    }

}
