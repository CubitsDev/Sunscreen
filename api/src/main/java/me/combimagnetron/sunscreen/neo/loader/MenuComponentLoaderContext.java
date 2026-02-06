package me.combimagnetron.sunscreen.neo.loader;

import me.combimagnetron.sunscreen.neo.MenuRoot;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.render.Viewport;
import me.combimagnetron.sunscreen.neo.theme.decorator.ThemeDecorator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public record MenuComponentLoaderContext(@Nullable Viewport viewport, @Nullable Iterable<ElementLike<?>> tree, @Nullable Canvas start, @NotNull
                                         MenuRoot menuRoot) {

    public @NotNull MenuComponentLoaderContext withViewport(@Nullable Viewport viewport) {
        return new MenuComponentLoaderContext(viewport, tree, start, menuRoot);
    }

    public @NotNull MenuComponentLoaderContext withTree(@Nullable Iterable<ElementLike<?>> tree) {
        return new MenuComponentLoaderContext(viewport, tree, start, menuRoot);
    }

    public @NotNull MenuComponentLoaderContext withStart(@Nullable Canvas start) {
        return new MenuComponentLoaderContext(viewport, tree, start, menuRoot);
    }

}
