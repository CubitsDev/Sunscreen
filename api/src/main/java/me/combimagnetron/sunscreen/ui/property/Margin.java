package me.combimagnetron.sunscreen.ui.property;

import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.ui.property.handler.PropertyHandler;
import me.combimagnetron.sunscreen.util.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Margin extends RelativeMeasure.Vec2iRelativeMeasureGroup<Margin> implements Property<Vec2i, Margin> {
    private static final PropertyHandler<Vec2i, Margin> PROPERTY_HANDLER = (element, property) -> null;

    private final Map<RelativeMeasure.Axis, Vec2iRelativeBuilder<@NotNull Margin>> axisMap = new LinkedHashMap<>();

    public Margin(@NotNull Vec2i vec2i) {
        super(vec2i);
    }

    public Margin(@NotNull RelativeMeasure.Vec2iRelativeMeasureGroup<?> measureGroup) {
        //axisMap.putAll((Map<? extends RelativeMeasure.Axis, ? extends Vec2iRelativeBuilder<Margin>>) measureGroup.axisBuilderMap());
    }

    public static <C> @NotNull Margin relative(RelativeMeasure.Vec2iRelativeMeasureGroup<@NotNull C> measureGroup) {
        return new Margin(measureGroup);
    }

    public static @NotNull Margin fixed(@NotNull Vec2i vec2i) {
        return new Margin(vec2i);
    }

    @Override
    public @NotNull Class<@NotNull Vec2i> type() {
        return Vec2i.class;
    }

    @Override
    public @NotNull PropertyHandler<@NotNull Vec2i, @NotNull Margin> handler() {
        return PROPERTY_HANDLER;
    }

    @Override
    public void finish(@NotNull ScreenSize screenSize) {

    }

}
