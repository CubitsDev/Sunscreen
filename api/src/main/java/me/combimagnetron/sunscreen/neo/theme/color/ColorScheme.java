package me.combimagnetron.sunscreen.neo.theme.color;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.graphic.color.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ColorScheme {
    private final static ColorKey MAIN = ColorKey.colorKey("#main");
    private final static ColorKey BACKGROUND = ColorKey.colorKey("#background");
    private final static ColorKey ACCENT = ColorKey.colorKey("#accent");
    private final Map<String, Color> keyColorMap = new HashMap<>();
    private final ColorMode colorMode;

    protected ColorScheme(ColorMode colorMode) {
        this.colorMode = colorMode;
    }

    static @NotNull ColorScheme scheme(@NotNull ColorMode mode, @NotNull Color main, @NotNull Color background, @NotNull Color accent) {
        return new ColorScheme(mode).color(MAIN, main).color(BACKGROUND, background).color(ACCENT, accent);
    }

    public @NotNull ColorMode mode() {
        return colorMode;
    }

    public @NotNull Color main() {
        return type(MAIN);
    }

    public @NotNull Color background() {
        return type(BACKGROUND);
    }

    public @NotNull Color accent() {
        return type(ACCENT);
    }

    public @Nullable Color type(@NotNull ColorKey key) {
        return keyColorMap.get(key.id());
    }

    public @NotNull ColorScheme color(@NotNull ColorKey key, @NotNull Color color) {
        String id = key.id();
        if (!id.startsWith("#")) id = "#" + id;
        keyColorMap.put(id, color);
        return this;
    }

    public enum ColorMode {
        DARK, LIGHT
    }

    static @NotNull ColorScheme basicDark(@NotNull Color main, @NotNull Color background, @NotNull Color accent) {
        return new BasicDarkColorScheme(main, background, accent);
    }

    static @NotNull ColorScheme basicLight(@NotNull Color main, @NotNull Color background, @NotNull Color accent) {
        return new BasicLightColorScheme(main, background, accent);
    }

    static class BasicDarkColorScheme extends ColorScheme {

        public BasicDarkColorScheme(Color main, Color background, Color accent) {
            super(ColorMode.DARK);
        }

        @Override
        public @NotNull ColorMode mode() {
            return ColorMode.DARK;
        }

        @Override
        public @Nullable Color type(@NotNull ColorKey key) {
            return null;
        }

    }

    static class BasicLightColorScheme extends ColorScheme {

        public BasicLightColorScheme(Color main, Color background, Color accent) {
            super(ColorMode.DARK);
        }

        @Override
        public @NotNull ColorMode mode() {
            return ColorMode.DARK;
        }

        @Override
        public @Nullable Color type(@NotNull ColorKey key) {
            return null;
        }

    }

}
