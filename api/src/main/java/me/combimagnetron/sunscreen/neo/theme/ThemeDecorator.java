package me.combimagnetron.sunscreen.neo.theme;

import me.combimagnetron.sunscreen.neo.element.ElementLike;

public interface ThemeDecorator<E extends ElementLike<E>> {

    Class<E> target();

}
