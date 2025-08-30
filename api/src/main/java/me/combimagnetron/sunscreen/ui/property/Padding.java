package me.combimagnetron.sunscreen.ui.property;

import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.ui.property.handler.PropertyHandler;
import me.combimagnetron.sunscreen.util.math.Vec4i;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Padding extends RelativeMeasure.Vec4iRelativeMeasureGroup<Padding> implements Property<Vec4i, Padding> {
    private static final PropertyHandler<Vec4i, Padding> PROPERTY_HANDLER = (element, property) -> null;
    private final Map<RelativeMeasure.Axis2d, Vec4iRelativeBuilder<Padding>> axisMap = new LinkedHashMap<>();

    public Padding(@NotNull Vec4i Vec4i) {
        super(Vec4i);
    }

    public Padding(@NotNull RelativeMeasure.Vec4iRelativeMeasureGroup<?> measureGroup) {
        //axisMap.putAll((Map<? extends RelativeMeasure.Axis2d, ? extends Vec4iRelativeBuilder<Padding>>) measureGroup.axisBuilderMap());
    }

    public static <C> @NotNull Padding relative(RelativeMeasure.Vec4iRelativeMeasureGroup<C> measureGroup) {
        return new Padding(measureGroup);
    }

    public static @NotNull Padding fixed(Vec4i Vec4i) {
        return new Padding(Vec4i);
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

}
