package me.combimagnetron.sunscreen.neo.background;

import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.Renderable;
import me.combimagnetron.sunscreen.neo.render.engine.pass.RenderPass;
import org.jetbrains.annotations.NotNull;

public interface Background extends Renderable<Background, Size> {

    class Solid implements Background {

        @Override
        public <G extends GraphicLike<G>> @NotNull RenderPass<Background, G> render(@NotNull Size property) {
            return null;
        }

    }

    class Tiled implements Background {

        @Override
        public <G extends GraphicLike<G>> @NotNull RenderPass<Background, G> render(@NotNull Size property) {
            return null;
        }

    }

}
