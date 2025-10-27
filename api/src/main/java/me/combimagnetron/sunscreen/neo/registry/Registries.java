package me.combimagnetron.sunscreen.neo.registry;

import me.combimagnetron.passport.internal.registry.Registry;
import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.MenuTemplate;
import me.combimagnetron.sunscreen.neo.graphic.text.font.Font;
import me.combimagnetron.sunscreen.neo.theme.ModernTheme;
import me.combimagnetron.sunscreen.util.IdentifierHolder;
import org.jetbrains.annotations.NotNull;

public interface Registries {
    Registry<MenuTemplate> TEMPLATES = Registry.empty();
    Registry<ModernTheme> THEMES = Registry.empty();
    Registry<Font> FONTS = Registry.empty();

    static <T extends IdentifierHolder> boolean register(@NotNull Registry<T> registry, @NotNull T t) {
        Identifier identifier = t.identifier();
        if (registry.contains(identifier)) return false;
        registry.register(identifier, t);
        return true;
    }

    static @NotNull Registry<MenuTemplate> templates() {
        return TEMPLATES;
    }

    static @NotNull Registry<ModernTheme> themes() {
        return THEMES;
    }

    static @NotNull Registry<Font> fonts() {
        return FONTS;
    }

}
