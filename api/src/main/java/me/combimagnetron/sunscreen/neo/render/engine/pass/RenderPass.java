package me.combimagnetron.sunscreen.neo.render.engine.pass;

import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.render.RenderAction;
import me.combimagnetron.passport.util.data.Identifier;
import org.jetbrains.annotations.NotNull;

public record RenderPass<T, G extends GraphicLike<G>>(@NotNull Origin<T> origin, @NotNull RenderAction<G> renderAction) {

    public static <T, G extends GraphicLike<G>> @NotNull RenderPass<T, G> pass(@NotNull Origin<T> origin, @NotNull RenderAction<G> renderAction) {
        return new RenderPass<>(origin, renderAction);
    }

    public record Origin<T>(@NotNull T type, int hash) {

        public static <T> @NotNull Origin<T> origin(@NotNull T type) {
            return new Origin<>(type, type.hashCode());
        }

    }

    public record PerformanceResult(long timeTakenNanos, int ticksTaken, int colorArraySize) {

    }


}
