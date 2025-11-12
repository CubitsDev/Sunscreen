package me.combimagnetron.sunscreen.neo;

import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.element.tree.ElementTree;
import me.combimagnetron.sunscreen.neo.input.keybind.Keybind;
import me.combimagnetron.sunscreen.neo.theme.ModernTheme;
import org.jetbrains.annotations.NotNull;

public interface MenuRoot {

    <E extends ElementLike<E>> @NotNull MenuRoot element(@NotNull ElementLike<E> e);

    @NotNull MenuRoot keybind(@NotNull Keybind keybind);

    @NotNull MenuRoot theme(@NotNull ModernTheme theme);

    @NotNull ElementTree elementTree();

}
