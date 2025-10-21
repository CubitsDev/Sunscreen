package me.combimagnetron.sunscreen.neo.theme;

import me.combimagnetron.sunscreen.neo.element.ElementLike;

/**
 * Interface representing theme entries to link to an element, to retexture a button in a theme for example.
 * @param <E> element target type.
 */
public interface ThemeDecorator<E extends ElementLike<E>> {

    Class<E> target();

}
