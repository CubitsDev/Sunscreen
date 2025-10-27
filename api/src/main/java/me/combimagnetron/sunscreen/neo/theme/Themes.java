package me.combimagnetron.sunscreen.neo.theme;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.registry.Registries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Themes {
    Identifier DARK_BASIC = Identifier.of("sunscreen:themes/builtin", "dark_basic");
    Identifier LIGHT_BASIC = Identifier.of("sunscreen:themes/builtin", "light_basic");

    static boolean register(ModernTheme modernTheme) {
        return Registries.register(Registries.themes(), modernTheme);
    }

    static @NotNull ModernTheme darkBasic() {
        return Registries.themes().get(DARK_BASIC);
    }

    static @NotNull ModernTheme lightBasic() {
        return Registries.themes().get(LIGHT_BASIC);
    }

    static @Nullable ModernTheme find(@NotNull Identifier identifier) {
        return Registries.themes().get(identifier);
    }

}
