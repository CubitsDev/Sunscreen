package me.combimagnetron.sunscreen.ui.render;

import me.combimagnetron.sunscreen.ui.graphic.GraphicLike;
import me.combimagnetron.sunscreen.ui.graphic.modifier.GraphicModifier;

import java.util.Collection;
import java.util.List;

public interface RenderAction<G extends GraphicLike<G>> {

    G current();

    Collection<GraphicModifier> modifiers();

    final class SimpleRenderAction<G extends GraphicLike<G>> implements RenderAction<G> {

        @Override
        public G current() {
            return null;
        }

        @Override
        public Collection<GraphicModifier> modifiers() {
            return List.of();
        }
    }

}
