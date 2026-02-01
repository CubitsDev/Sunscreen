package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.Renderable;

public interface ModernElement<E extends ModernElement<E, G>, G extends GraphicLike<G>> extends ElementLike<E>, Renderable<Size, G> {

}
