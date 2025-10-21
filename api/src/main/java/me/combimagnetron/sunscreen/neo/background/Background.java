package me.combimagnetron.sunscreen.neo.background;

import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.loader.Component;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.Renderable;
import me.combimagnetron.sunscreen.neo.render.engine.pass.RenderPass;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface Background extends Renderable<Background, Size>, Component {

    class Solid implements Background {

        @Override
        public <G extends GraphicLike<G>> @NotNull RenderPass<Background, G> render(@NotNull Size property) {
            return null;
        }

        @Override
        public @NotNull Collection<? extends @NotNull Component> dependencies() {
            return List.of();
        }
    }

    class Tiled implements Background {

        @Override
        public <G extends GraphicLike<G>> @NotNull RenderPass<Background, G> render(@NotNull Size property) {
            return null;
        }

        @Override
        public @NotNull Collection<? extends @NotNull Component> dependencies() {
            return List.of();
        }
    }

}
