package me.combimagnetron.sunscreen.neo.property.handler;

import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.render.phase.context.RenderContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface PropertyHandler<C> {

    @Nullable Canvas apply(@Nullable ElementLike<? extends ElementLike<?>> parent, @NotNull RenderContext renderContext, @NotNull C property);

}
