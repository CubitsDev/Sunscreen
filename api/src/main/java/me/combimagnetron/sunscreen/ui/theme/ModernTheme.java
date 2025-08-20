package me.combimagnetron.sunscreen.ui.theme;

import me.combimagnetron.sunscreen.ui.element.ModernElement;
import org.jetbrains.annotations.NotNull;

public interface ModernTheme {

    <E extends ModernElement<E>> @NotNull ModernTheme map(@NotNull E element, @NotNull ThemeDecorator themeDecorator);

    <E extends ModernElement<E>> @NotNull ThemeDecorator find(@NotNull Class<@NotNull E> clazz);

}
