package me.combimagnetron.sunscreen.neo.render.engine.grid;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;

public interface RenderGrid {

    void add(@NotNull RenderChunk renderChunk);

    void addAt(@NotNull RenderChunk renderChunk, @NotNull Vec2i position);

    @NotNull Vec2i size();

    @NotNull RenderChunk[] chunks();

    class CenteredRenderGrid implements RenderGrid {

        @Override
        public void add(@NotNull RenderChunk renderChunk) {

        }

        @Override
        public void addAt(@NotNull RenderChunk renderChunk, @NotNull Vec2i position) {

        }

        @Override
        public @NotNull Vec2i size() {
            return null;
        }

        @Override
        public @NotNull RenderChunk[] chunks() {
            return new RenderChunk[0];
        }

    }

}
