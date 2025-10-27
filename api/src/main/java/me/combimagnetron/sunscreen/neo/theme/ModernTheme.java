package me.combimagnetron.sunscreen.neo.theme;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.ModernElement;
import me.combimagnetron.sunscreen.neo.loader.Component;
import me.combimagnetron.sunscreen.neo.theme.color.ColorScheme;
import me.combimagnetron.sunscreen.neo.theme.decorator.ThemeDecorator;
import me.combimagnetron.sunscreen.util.IdentifierHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public sealed interface ModernTheme extends Component, IdentifierHolder permits ModernTheme.SimpleModernTheme {

    @NotNull Identifier identifier();

    <E extends ModernElement<E>, D extends ThemeDecorator<E, D>> @NotNull ModernTheme map(@NotNull E element, @NotNull ThemeDecorator<E, D> themeDecorator);

    <E extends ModernElement<E>, D extends ThemeDecorator<E, D>> @NotNull ThemeDecorator<E, D> find(@NotNull Class<@NotNull E> clazz);

    @NotNull ModernTheme colorScheme(@NotNull ColorScheme colorScheme);

    final class SimpleModernTheme implements ModernTheme {

        @Override
        public @NotNull Identifier identifier() {
            return null;
        }

        @Override
        public @NotNull <E extends ModernElement<E>, D extends ThemeDecorator<E, D>> ModernTheme map(@NotNull E element, @NotNull ThemeDecorator<E, D> themeDecorator) {
            return null;
        }

        @Override
        public @NotNull <E extends ModernElement<E>, D extends ThemeDecorator<E, D>> ThemeDecorator<E, D> find(@NotNull Class<@NotNull E> clazz) {
            return null;
        }

        @Override
        public @NotNull ModernTheme colorScheme(@NotNull ColorScheme colorScheme) {
            return null;
        }

        @Override
        public @NotNull Collection<? extends @NotNull Component> dependencies() {
            return List.of();
        }

    }

}
