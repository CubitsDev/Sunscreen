package me.combimagnetron.sunscreen.ui.property;

import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.ui.property.handler.PropertyHandler;
import me.combimagnetron.sunscreen.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Position extends RelativeMeasure.Vec2iRelativeMeasureGroup<Position> implements Property<Vec2i, Position> {
    private static final PropertyHandler<Vec2i, Position> PROPERTY_HANDLER = (element, property) -> null;
    private final Map<RelativeMeasure.Axis2d, Vec2iRelativeBuilder<Position>> axisMap = new LinkedHashMap<>();

    public Position(@NotNull Vec2i vec2i) {
        super(vec2i);
    }

    public Position(@NotNull RelativeMeasure.Vec2iRelativeMeasureGroup<?> measureGroup) {
        //axisMap.putAll((Map<? extends RelativeMeasure.Axis2d, ? extends Vec2iRelativeBuilder<Position>>) measureGroup.axisBuilderMap());
    }

    public static <C> @NotNull Position relative(RelativeMeasure.Vec2iRelativeMeasureGroup<C> measureGroup) {
        return new Position(measureGroup);
    }

    public static @NotNull Position fixed(Vec2i vec2i) {
        return new Position(vec2i);
    }

    @Override
    public @NotNull Class<Vec2i> type() {
        return Vec2i.class;
    }

    @Override
    public @NotNull PropertyHandler<Vec2i, Position> handler() {
        return PROPERTY_HANDLER;
    }

    @Override
    public void finish(@NotNull ScreenSize screenSize) {

    }

}
