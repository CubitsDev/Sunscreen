package me.combimagnetron.sunscreen.neo.element.tree;

import me.combimagnetron.sunscreen.neo.MenuRoot;
import me.combimagnetron.sunscreen.neo.MenuTemplate;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;


public final class ElementTree implements Iterable<Branch> {

    public <F extends ElementLike<F>> @NotNull ElementTree add(@NotNull Branch element) {
        return this;
    }

    @Override
    public @NotNull Iterator<Branch> iterator() {
        return null;
    }

    public static @NotNull ElementTree from(@NotNull MenuTemplate template) {
        return null;
    }

}
