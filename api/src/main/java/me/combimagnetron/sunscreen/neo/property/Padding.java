package me.combimagnetron.sunscreen.neo.property;

import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.neo.property.handler.PropertyHandler;
import me.combimagnetron.passport.util.math.Vec4i;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class Padding extends RelativeMeasure.Vec4iRelativeMeasureGroup<Padding> implements Property<Vec4i, Padding> {
    private static final PropertyHandler<Vec4i, Padding> PROPERTY_HANDLER = (element, property) -> null;
    private final Map<RelativeMeasure.Axis4d, Vec4iRelativeBuilder<Padding>> axisMap = new LinkedHashMap<>();

    public Padding(@NotNull Vec4i Vec4i) {
        super(Vec4i);
    }

    public Padding(@NotNull RelativeMeasure.Vec4iRelativeMeasureGroup<?> measureGroup) {
        //axisMap.putAll((Map<? extends RelativeMeasure.Axis4d, ? extends Vec4iRelativeBuilder<Padding>>) measureGroup.axisBuilderMap());
    }

    public static <C> @NotNull Padding relative(RelativeMeasure.Vec4iRelativeMeasureGroup<C> measureGroup) {
        return new Padding(measureGroup);
    }

    public static @NotNull Padding fixed(@NotNull Vec4i vec4i) {
        return new Padding(vec4i);
    }

    public static @NotNull Padding fixed(int padding) {
        return fixed(Vec2i.of(padding, padding));
    }

    public static @NotNull Padding fixed(@NotNull Vec2i vec2i) {
        return fixed(Vec4i.mirror(vec2i));
    }

    public static @NotNull Padding fit() {
        return new Fit();
    }

    @Override
    public @NotNull Class<Vec4i> type() {
        return Vec4i.class;
    }

    @Override
    public @NotNull PropertyHandler<Vec4i, Padding> handler() {
        return PROPERTY_HANDLER;
    }

    @Override
    public void finish(@NotNull ScreenSize screenSize) {

    }

    public static class Fit extends Padding implements FitToContent<Padding> {

        public Fit() {
            super((Vec4i) null);
        }

        @Override
        public @NotNull Class<Padding> parent() {
            return Padding.class;
        }

    }

}
