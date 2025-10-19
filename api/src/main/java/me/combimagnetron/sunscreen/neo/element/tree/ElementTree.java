package me.combimagnetron.sunscreen.neo.element.tree;

import me.combimagnetron.sunscreen.neo.element.ElementLike;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public final class ElementTree<E extends ElementLike<E>> implements Iterable<E> {

    public <F extends ElementLike<F>> @NotNull ElementTree<E> add(@NotNull F element) {
        return this;
    }

    @Override
    public @NotNull Iterator<E> iterator() {
        return null;
    }
}
