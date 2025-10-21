package me.combimagnetron.sunscreen.resourcepack.feature.font;

import me.combimagnetron.sunscreen.resourcepack.ResourcePackFeature;
import me.combimagnetron.passport.util.data.Identifier;

import java.util.Collection;

public interface FontFeature extends ResourcePackFeature<FontFeature, Font> {

    Identifier identifier();

    int size();

    Collection<Glyph> glyphs();

}
