package me.combimagnetron.sunscreen.neo;

import me.combimagnetron.sunscreen.event.KeybindPressedEvent;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.element.tree.ElementTree;
import me.combimagnetron.sunscreen.neo.input.keybind.Keybind;
import me.combimagnetron.sunscreen.neo.theme.ModernTheme;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public interface MenuRoot {

    <E extends ElementLike<E>> @NotNull MenuRoot element(@NotNull ElementLike<E> e);

    @NotNull MenuRoot keybind(@NotNull Keybind keybind, @NotNull Consumer<KeybindPressedEvent> eventConsumer);

    @NotNull MenuRoot theme(@NotNull ModernTheme theme);

    @Nullable ElementTree<?> elementTree();

}
