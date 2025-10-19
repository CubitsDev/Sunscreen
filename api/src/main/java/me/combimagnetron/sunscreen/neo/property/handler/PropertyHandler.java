package me.combimagnetron.sunscreen.neo.property.handler;

import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.render.RenderAction;
import me.combimagnetron.sunscreen.neo.render.phase.RenderPhase;
import me.combimagnetron.sunscreen.neo.render.phase.context.RenderPhaseContext;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PropertyHandler<T, C> {

    @NotNull RenderAction<? extends GraphicLike<?>> apply(@NotNull ElementLike<? extends ElementLike<?>> element, @NotNull RenderPhaseContext<RenderPhase.Render, RenderPhase.Send> renderPhaseContext);

}
