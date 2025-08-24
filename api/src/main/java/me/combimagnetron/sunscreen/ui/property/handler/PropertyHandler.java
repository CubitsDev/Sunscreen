package me.combimagnetron.sunscreen.ui.property.handler;

import me.combimagnetron.sunscreen.ui.element.ElementLike;
import me.combimagnetron.sunscreen.ui.graphic.GraphicLike;
import me.combimagnetron.sunscreen.ui.render.RenderAction;
import me.combimagnetron.sunscreen.ui.render.phase.Phase;
import me.combimagnetron.sunscreen.ui.render.phase.context.PhaseContext;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PropertyHandler<T, C> {

    @NotNull RenderAction<? extends GraphicLike<?>> apply(@NotNull ElementLike<? extends ElementLike<?>> element, @NotNull PhaseContext<Phase.Render, Phase.Send> phaseContext);

}
