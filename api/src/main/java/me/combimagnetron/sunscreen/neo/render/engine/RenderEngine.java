package me.combimagnetron.sunscreen.neo.render.engine;

import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.render.engine.pass.RenderPass;
import me.combimagnetron.sunscreen.neo.render.phase.RenderPhase;
import org.jetbrains.annotations.NotNull;

public interface RenderEngine {

    @NotNull <P extends RenderPhase<P, N>, N extends RenderPhase<N, ?>> RenderPhase<P, N> phase();

    @NotNull <T, G extends GraphicLike<G>> RenderPass.PerformanceResult sumbit(RenderPass<T, G> renderPass);

}
