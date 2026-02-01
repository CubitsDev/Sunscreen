package me.combimagnetron.sunscreen.neo.loader;

import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.render.Viewport;
import me.combimagnetron.sunscreen.neo.render.engine.grid.RenderGrid;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record MenuComponentLoaderContext(@Nullable Viewport viewport, @Nullable RenderGrid renderGrid,
                                         @Nullable Iterable<ElementLike<?>> tree, @Nullable Canvas start) {

    public @NotNull MenuComponentLoaderContext withViewport(@Nullable Viewport viewport) {
        return new MenuComponentLoaderContext(viewport, renderGrid, tree, start);
    }

    public @NotNull MenuComponentLoaderContext withGrid(@Nullable RenderGrid renderGrid) {
        return new MenuComponentLoaderContext(viewport, renderGrid, tree, start);
    }

    public @NotNull MenuComponentLoaderContext withTree(@Nullable Iterable<ElementLike<?>> tree) {
        return new MenuComponentLoaderContext(viewport, renderGrid, tree, start);
    }

    public @NotNull MenuComponentLoaderContext withStart(@Nullable Canvas start) {
        return new MenuComponentLoaderContext(viewport, renderGrid, tree, start);
    }

}
