package me.combimagnetron.sunscreen.neo.render;

import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.neo.render.phase.context.RenderContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Renderable<P extends Property<?, P>, G extends GraphicLike<G>> {

    @NotNull G render(@NotNull P property, @Nullable RenderContext context);

}
