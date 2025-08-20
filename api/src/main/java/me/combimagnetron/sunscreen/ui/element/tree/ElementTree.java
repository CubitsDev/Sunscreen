package me.combimagnetron.sunscreen.ui.element.tree;

import me.combimagnetron.sunscreen.ui.element.ElementLike;
import org.jetbrains.annotations.NotNull;

public interface ElementTree {

    <E extends ElementLike<E>> ElementTree add(@NotNull E element);



}
