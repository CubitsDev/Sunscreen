package me.combimagnetron.sunscreen.ui.property;

import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.ui.property.handler.PropertyHandler;
import me.combimagnetron.sunscreen.util.math.Vec4i;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Margin extends RelativeMeasure.Vec4iRelativeMeasureGroup<Margin> implements Property<Vec4i, Margin> {
    private static final PropertyHandler<Vec4i, Margin> PROPERTY_HANDLER = (element, context) -> null;

    private final Map<RelativeMeasure.Axis2d, Vec4iRelativeBuilder<@NotNull Margin>> axisMap = new LinkedHashMap<>();

    public Margin(@NotNull Vec4i Vec4i) {
        super(Vec4i);
    }

    public Margin(@NotNull RelativeMeasure.Vec4iRelativeMeasureGroup<?> measureGroup) {
        //axisMap.putAll((Map<? extends RelativeMeasure.Axis2d, ? extends Vec4iRelativeBuilder<Margin>>) measureGroup.axisBuilderMap());
    }

    public static <C> @NotNull Margin relative(RelativeMeasure.Vec4iRelativeMeasureGroup<@NotNull C> measureGroup) {
        return new Margin(measureGroup);
    }

    public static @NotNull Margin fixed(@NotNull Vec4i Vec4i) {
        return new Margin(Vec4i);
    }

    @Override
    public @NotNull Class<@NotNull Vec4i> type() {
        return Vec4i.class;
    }

    @Override
    public @NotNull PropertyHandler<@NotNull Vec4i, @NotNull Margin> handler() {
        return PROPERTY_HANDLER;
    }

    @Override
    public void finish(@NotNull ScreenSize screenSize) {

    }

}
