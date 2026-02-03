package me.combimagnetron.sunscreen.neo.background;

import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.graphic.color.Color;
import me.combimagnetron.sunscreen.neo.loader.MenuComponent;
import me.combimagnetron.sunscreen.neo.loader.ComponentLoader;
import me.combimagnetron.sunscreen.neo.loader.MenuComponentLoaderContext;
import me.combimagnetron.sunscreen.neo.property.Position;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.Renderable;
import me.combimagnetron.sunscreen.neo.render.engine.context.RenderContext;
import me.combimagnetron.sunscreen.util.helper.PropertyHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

/**
 * Represents a background for a {@link me.combimagnetron.sunscreen.neo.MenuRoot}.
 */
public interface Background<B extends MenuComponent<B>> extends Renderable<Size, Canvas>, MenuComponent<B> {

    /**
     * Solid color background representation.
     */
    class Solid implements Background<Solid> {
        private final Color color;

        protected Solid(Color color) {
            this.color = color;
        }

        @Override
        public @NotNull ComponentLoader<Solid, MenuComponentLoaderContext> loader() {
            return null;
        }

        @Override
        public @NotNull Class<Solid> type() {
            return Solid.class;
        }

        @Override
        public @NonNull Canvas render(@NonNull Size property, @Nullable RenderContext context) {
            Vec2i vec2iSize = PropertyHelper.vectorOrThrow(property, Vec2i.class);
            return Canvas.empty(vec2iSize).fill(Position.nil(), property, color);
        }

    }

    /**
     * Tiles a given canvas to fill the full menu background.
     */
    class Tiled implements Background<Tiled> {

        @Override
        public @NotNull ComponentLoader<Tiled, MenuComponentLoaderContext> loader() {
            return null;
        }

        @Override
        public @NotNull Class<Tiled> type() {
            return Tiled.class;
        }

        @Override
        public @NotNull Canvas render(@NotNull Size property, @Nullable RenderContext context) {
            return null;
        }

    }

}
