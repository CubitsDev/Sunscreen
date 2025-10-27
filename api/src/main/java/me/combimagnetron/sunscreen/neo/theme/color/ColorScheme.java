package me.combimagnetron.sunscreen.neo.theme.color;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.graphic.color.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ColorScheme {

    @NotNull ColorMode mode();

    @NotNull Color main();

    @NotNull Color background();

    @NotNull Color accent();

    @Nullable Color type(@NotNull Identifier identifier);

    enum ColorMode {
        DARK, LIGHT
    }

    static @NotNull ColorScheme basicDark(@NotNull Color main, @NotNull Color background, @NotNull Color accent) {
        return new BasicDarkColorScheme(main, background, accent);
    }

    static @NotNull ColorScheme basicLight(@NotNull Color main, @NotNull Color background, @NotNull Color accent) {
        return new BasicLightColorScheme(main, background, accent);
    }

    record BasicDarkColorScheme(Color main, Color background, Color accent) implements ColorScheme {

        @Override
        public @NotNull ColorMode mode() {
            return ColorMode.DARK;
        }

        @Override
        public @Nullable Color type(@NotNull Identifier identifier) {
            return null;
        }

    }

    record BasicLightColorScheme(Color main, Color background, Color accent) implements ColorScheme {

        @Override
        public @NotNull ColorMode mode() {
            return ColorMode.LIGHT;
        }

        @Override
        public @Nullable Color type(@NotNull Identifier identifier) {
            return null;
        }

    }

}
