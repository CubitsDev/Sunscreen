package me.combimagnetron.sunscreen.neo.graphic.text.style.impl.font;

import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.util.IdentifierHolder;
import me.combimagnetron.sunscreen.util.helper.FontRendererHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public final class AtlasFont implements IdentifierHolder, Font {
    private final Char2ObjectMap<Canvas> characterMap = new Char2ObjectArrayMap<>();
    private final Identifier identifier;

    AtlasFont(@NotNull Identifier identifier) {
        this.identifier = identifier;
    }

    public static @NotNull AtlasFont font(@NotNull Identifier identifier) {
        return new AtlasFont(identifier);
    }

    public @NotNull AtlasFont register(char character, @NotNull Canvas canvas) {
        characterMap.put(character, canvas);
        return this;
    }

    public @NotNull AtlasFont fromTtfFile(@NotNull Path path, int fontSize) {
        if (!path.toFile().exists()) throw new IllegalArgumentException("Font file does not exist!");
        Char2ObjectMap<Canvas> charToCanvasMap = FontRendererHelper.font(path, fontSize);
        characterMap.putAll(charToCanvasMap);
        return this;
    }

    public @Nullable Canvas character(char character) {
        return characterMap.get(character);
    }

    @Override
    public @NotNull Identifier identifier() {
        return identifier;
    }

}
