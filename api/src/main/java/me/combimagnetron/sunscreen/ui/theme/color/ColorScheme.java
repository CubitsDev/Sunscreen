package me.combimagnetron.sunscreen.ui.theme.color;

import me.combimagnetron.sunscreen.ui.graphic.color.ColorLike;
import org.jetbrains.annotations.NotNull;

public interface ColorScheme {

    @NotNull ColorMode mode();

    @NotNull ColorLike main();

    @NotNull ColorLike background();

    @NotNull ColorLike accent();

    enum ColorMode {
        DARK, LIGHT
    }

    static @NotNull ColorScheme basicDark(@NotNull ColorLike main, @NotNull ColorLike background, @NotNull ColorLike accent) {
        return new BasicDarkColorScheme(main, background, accent);
    }

    static @NotNull ColorScheme basicLight(@NotNull ColorLike main, @NotNull ColorLike background, @NotNull ColorLike accent) {
        return new BasicLightColorScheme(main, background, accent);
    }

    record BasicDarkColorScheme(ColorLike main, ColorLike background, ColorLike accent) implements ColorScheme {

        @Override
        public @NotNull ColorMode mode() {
            return ColorMode.DARK;
        }

    }

    record BasicLightColorScheme(ColorLike main, ColorLike background, ColorLike accent) implements ColorScheme {

        @Override
        public @NotNull ColorMode mode() {
            return ColorMode.LIGHT;
        }

    }

}
