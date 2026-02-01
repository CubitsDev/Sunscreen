package me.combimagnetron.sunscreen.neo;

import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.input.keybind.Keybind;
import me.combimagnetron.sunscreen.neo.loader.MenuComponent;
import me.combimagnetron.sunscreen.neo.theme.ModernTheme;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface RootLike<R extends RootLike<R>> {

    <E extends ElementLike<E>> @NotNull R element(@NotNull ElementLike<E> e);

    default @NotNull R keybind(@NotNull Keybind keybind) {
        return component(keybind);
    }

    default @NotNull R theme(@NotNull ModernTheme theme) {
        return component(theme);
    }

    @NotNull <C extends MenuComponent<C>> R component(@NotNull MenuComponent<C> menuComponent);

    @NotNull Collection<ElementLike<?>> elementLikes();

    @NotNull Collection<MenuComponent<?>> components();

}
