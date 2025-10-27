package me.combimagnetron.sunscreen.neo.theme;

import me.combimagnetron.sunscreen.neo.element.ModernElement;
import me.combimagnetron.sunscreen.neo.loader.Component;
import me.combimagnetron.sunscreen.neo.theme.color.ColorScheme;
import me.combimagnetron.sunscreen.neo.theme.decorator.ThemeDecorator;
import org.jetbrains.annotations.NotNull;

public interface ModernTheme extends Component {

    <E extends ModernElement<E>, D extends ThemeDecorator<E, D>> @NotNull ModernTheme map(@NotNull E element, @NotNull ThemeDecorator<E, D> themeDecorator);

    <E extends ModernElement<E>, D extends ThemeDecorator<E, D>> @NotNull ThemeDecorator<E, D> find(@NotNull Class<@NotNull E> clazz);

    @NotNull ModernTheme colorScheme(@NotNull ColorScheme colorScheme);

}
