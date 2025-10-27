package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.sunscreen.neo.loader.Component;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.Renderable;

public interface ModernElement<E extends ModernElement<E>> extends ElementLike<E>, Component, Renderable<E, Size> {

}
