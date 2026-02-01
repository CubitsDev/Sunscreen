package me.combimagnetron.sunscreen.neo.graphic.text.style.impl.font;

import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.graphic.text.style.Style;
import org.jetbrains.annotations.Nullable;

public interface Font extends Style<Font> {

    @Nullable Canvas character(char character);

}
