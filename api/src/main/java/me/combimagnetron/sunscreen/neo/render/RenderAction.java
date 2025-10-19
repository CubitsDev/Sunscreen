package me.combimagnetron.sunscreen.neo.render;

import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.graphic.modifier.GraphicModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface RenderAction<G extends GraphicLike<G>> {

    @NotNull G target();

    @NotNull Collection<@NotNull GraphicModifier> modifiers();

    static <L extends GraphicLike<L>> @NotNull RenderAction<L> none(@NotNull L current) {
        return new NoneRenderAction<>(current);
    }

    final class SimpleRenderAction<G extends GraphicLike<G>> implements RenderAction<G> {

        @Override
        public @NotNull G target() {
            return null;
        }

        @Override
        public @NotNull Collection<GraphicModifier> modifiers() {
            return List.of();
        }
    }

    record NoneRenderAction<G extends GraphicLike<G>>(G target) implements RenderAction<G> {

        @Override
        public @NotNull Collection<GraphicModifier> modifiers() {
            return List.of();
        }

    }

}
