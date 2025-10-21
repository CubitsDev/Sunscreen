package me.combimagnetron.sunscreen.neo.theme;

import me.combimagnetron.sunscreen.neo.element.ModernElement;
import me.combimagnetron.sunscreen.neo.loader.Component;
import org.jetbrains.annotations.NotNull;

public interface ModernTheme extends Component {

    <E extends ModernElement<E>> @NotNull ModernTheme map(@NotNull E element, @NotNull ThemeDecorator themeDecorator);

    <E extends ModernElement<E>> @NotNull ThemeDecorator find(@NotNull Class<@NotNull E> clazz);

}
