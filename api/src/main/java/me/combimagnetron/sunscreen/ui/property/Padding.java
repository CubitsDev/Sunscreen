package me.combimagnetron.sunscreen.ui.property;

import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.ui.property.handler.PropertyHandler;
import me.combimagnetron.sunscreen.util.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Padding extends RelativeMeasure.Vec2iRelativeMeasureGroup<Padding> implements Property<Vec2i, Padding> {
    private static final PropertyHandler<Vec2i, Padding> PROPERTY_HANDLER = (element, property) -> null;
    private final Map<RelativeMeasure.Axis, Vec2iRelativeBuilder<Padding>> axisMap = new LinkedHashMap<>();

    public Padding(@NotNull Vec2i vec2i) {
        super(vec2i);
    }

    public Padding(@NotNull RelativeMeasure.Vec2iRelativeMeasureGroup<?> measureGroup) {
        //axisMap.putAll((Map<? extends RelativeMeasure.Axis, ? extends Vec2iRelativeBuilder<Padding>>) measureGroup.axisBuilderMap());
    }

    public static <C> @NotNull Padding relative(RelativeMeasure.Vec2iRelativeMeasureGroup<C> measureGroup) {
        return new Padding(measureGroup);
    }

    public static @NotNull Padding fixed(Vec2i vec2i) {
        return new Padding(vec2i);
    }

    @Override
    public @NotNull Class<Vec2i> type() {
        return Vec2i.class;
    }

    @Override
    public @NotNull PropertyHandler<Vec2i, Padding> handler() {
        return PROPERTY_HANDLER;
    }

    @Override
    public void finish(@NotNull ScreenSize screenSize) {

    }

}
