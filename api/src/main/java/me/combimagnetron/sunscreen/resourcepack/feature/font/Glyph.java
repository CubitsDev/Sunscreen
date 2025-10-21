package me.combimagnetron.sunscreen.resourcepack.feature.font;

import me.combimagnetron.sunscreen.resourcepack.Asset;
import me.combimagnetron.passport.util.data.Pair;

public interface Glyph extends Asset {

    char character();

    Pair<Integer, Integer> heightAndAscent();

}
