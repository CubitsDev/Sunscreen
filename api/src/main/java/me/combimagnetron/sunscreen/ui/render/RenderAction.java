package me.combimagnetron.sunscreen.ui.render;

import me.combimagnetron.sunscreen.ui.graphic.GraphicLike;
import me.combimagnetron.sunscreen.ui.graphic.modifier.GraphicModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface RenderAction<G extends GraphicLike<G>> {

    @NotNull G current();

    @NotNull Collection<GraphicModifier> modifiers();

    static <L extends GraphicLike<L>> @NotNull RenderAction<L> none(@NotNull L current) {
        return new NoneRenderAction<>(current);
    }

    final class SimpleRenderAction<G extends GraphicLike<G>> implements RenderAction<G> {

        @Override
        public @NotNull G current() {
            return null;
        }

        @Override
        public @NotNull Collection<GraphicModifier> modifiers() {
            return List.of();
        }
    }

    record NoneRenderAction<G extends GraphicLike<G>>(G current) implements RenderAction<G> {

        @Override
        public @NotNull Collection<GraphicModifier> modifiers() {
            return List.of();
        }

    }

}
