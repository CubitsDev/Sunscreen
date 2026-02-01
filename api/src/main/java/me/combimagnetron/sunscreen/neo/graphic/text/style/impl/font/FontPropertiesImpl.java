package me.combimagnetron.sunscreen.neo.graphic.text.style.impl.font;

import org.jetbrains.annotations.NotNull;

public record FontPropertiesImpl(float size, float tracking, float kerning, float baseline, float leading, float shear) implements FontProperties {


    @Override
    public @NotNull FontProperties size(float size) {
        return new FontPropertiesImpl(size, tracking, kerning, baseline, leading, shear);
    }

    @Override
    public @NotNull FontProperties tracking(float tracking) {
        return new FontPropertiesImpl(size, tracking, kerning, baseline, leading, shear);
    }

    @Override
    public @NotNull FontProperties kerning(float kerning) {
        return new FontPropertiesImpl(size, tracking, kerning, baseline, leading, shear);
    }

    @Override
    public @NotNull FontProperties baseline(float baseline) {
        return new FontPropertiesImpl(size, tracking, kerning, baseline, leading, shear);
    }

    @Override
    public @NotNull FontProperties leading(float leading) {
        return new FontPropertiesImpl(size, tracking, kerning, baseline, leading, shear);
    }

    @Override
    public @NotNull FontProperties shear(float shear) {
        return new FontPropertiesImpl(size, tracking, kerning, baseline, leading, shear);
    }
}
