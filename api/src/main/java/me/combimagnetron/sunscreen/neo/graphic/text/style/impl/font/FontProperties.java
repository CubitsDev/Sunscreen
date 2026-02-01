package me.combimagnetron.sunscreen.neo.graphic.text.style.impl.font;

import me.combimagnetron.sunscreen.neo.graphic.text.style.Style;
import org.jetbrains.annotations.NotNull;

public sealed interface FontProperties extends Style<Integer> permits FontPropertiesImpl {

    float size();

    float tracking();

    float kerning();

    float baseline();

    float leading();

    float shear();

    @NotNull FontProperties size(float size);

    @NotNull FontProperties tracking(float tracking);

    @NotNull FontProperties kerning(float kerning);

    @NotNull FontProperties baseline(float baseline);

    @NotNull FontProperties leading(float leading);

    @NotNull FontProperties shear(float shear);

    static @NotNull FontProperties properties() {
        return new FontPropertiesImpl(0, 0, 0, 0, 0, 0);
    }

}
