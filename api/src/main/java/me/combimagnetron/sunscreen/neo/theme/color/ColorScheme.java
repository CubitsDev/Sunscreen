package me.combimagnetron.sunscreen.neo.theme.color;

import me.combimagnetron.sunscreen.neo.graphic.color.Color;
import org.jetbrains.annotations.NotNull;

public interface ColorScheme {

    @NotNull ColorMode mode();

    @NotNull Color main();

    @NotNull Color background();

    @NotNull Color accent();

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

    }

    record BasicLightColorScheme(Color main, Color background, Color accent) implements ColorScheme {

        @Override
        public @NotNull ColorMode mode() {
            return ColorMode.LIGHT;
        }

    }

}
