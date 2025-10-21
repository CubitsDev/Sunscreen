package me.combimagnetron.sunscreen.neo.render.engine.pass;

import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.render.RenderAction;
import me.combimagnetron.passport.util.data.Identifier;
import org.jetbrains.annotations.NotNull;

public record RenderPass<T, G extends GraphicLike<G>>(@NotNull Origin<T> origin, @NotNull RenderAction<G> renderAction) {

    public record Origin<T>(@NotNull T type, @NotNull Identifier identifier) {

    }

    public record PerformanceResult(long timeTakenNanos, int ticksTaken, int colorArraySize) {

    }


}
