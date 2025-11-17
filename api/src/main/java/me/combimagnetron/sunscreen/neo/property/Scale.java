package me.combimagnetron.sunscreen.neo.property;

import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.neo.property.handler.PropertyHandler;
import org.jetbrains.annotations.NotNull;

public class Scale extends RelativeMeasure.FloatRelativeMeasureGroup<Scale> implements Property<Float, Scale> {
    private static final PropertyHandler<Float, Scale> HANDLER = (element, property) -> null;

    public Scale(float value) {
        super(value);
    }

    public Scale(@NotNull RelativeMeasure.FloatRelativeMeasureGroup<Scale> measureGroup) {

    }

    public static @NotNull Scale fixed(float value) {
        return new Scale(value);
    }

    public static @NotNull Scale relative(@NotNull RelativeMeasure.FloatRelativeMeasureGroup<Scale> measureGroup) {
        return new Scale(measureGroup);
    }

    public static @NotNull Scale fit() {
        return new Fit();
    }

    @Override
    public @NotNull Class<@NotNull Float> type() {
        return Float.class;
    }

    @Override
    public @NotNull PropertyHandler<@NotNull Float, @NotNull Scale> handler() {
        return HANDLER;
    }

    @Override
    public void finish(@NotNull ScreenSize screenSize) {

    }

    @Override
    public void add(FloatRelativeBuilder<Scale> scaleFloatRelativeBuilder, Void unused) {

    }

    public static class Fit extends Scale implements FitToContent<Scale> {

        public Fit() {
            super((Float) null);
        }

        @Override
        public @NotNull Class<Scale> parent() {
            return Scale.class;
        }

    }

}
