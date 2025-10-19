package me.combimagnetron.sunscreen.neo.render;

import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.neo.render.engine.pass.RenderPass;
import org.jetbrains.annotations.NotNull;

public interface Renderable<T, P extends Property<?, P>> {

    <G extends GraphicLike<G>> @NotNull RenderPass<T, G> render(@NotNull P property);

}
