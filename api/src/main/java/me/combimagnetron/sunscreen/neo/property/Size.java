package me.combimagnetron.sunscreen.neo.property;

import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.neo.property.handler.PropertyHandler;
import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Size extends RelativeMeasure.Vec2iRelativeMeasureGroup<Size> implements Property<Vec2i, Size> {
    private static final PropertyHandler<Vec2i, Size> PROPERTY_HANDLER = (element, property) -> null;
    private final Map<RelativeMeasure.Axis2d, Vec2iRelativeBuilder<Size>> axisMap = new LinkedHashMap<>();

    public Size(@NotNull Vec2i vec2i) {
        super(vec2i);
    }

    public Size(@NotNull RelativeMeasure.Vec2iRelativeMeasureGroup<?> measureGroup) {
        //axisMap.putAll((Map<? extends RelativeMeasure.Axis2d, ? extends Vec2iRelativeBuilder<Size>>) measureGroup.axisBuilderMap());
    }

    public static <C> @NotNull Size relative(RelativeMeasure.Vec2iRelativeMeasureGroup<C> measureGroup) {
        return new Size(measureGroup);
    }

    public static @NotNull Size fixed(Vec2i vec2i) {
        return new Size(vec2i);
    }

    @Override
    public @NotNull Class<Vec2i> type() {
        return Vec2i.class;
    }

    @Override
    public @NotNull PropertyHandler<Vec2i, Size> handler() {
        return PROPERTY_HANDLER;
    }

    @Override
    public void finish(@NotNull ScreenSize screenSize) {

    }

}
