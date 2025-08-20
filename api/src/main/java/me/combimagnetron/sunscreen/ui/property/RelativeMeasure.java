package me.combimagnetron.sunscreen.ui.property;

import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.util.RuntimeDefinable;
import me.combimagnetron.sunscreen.util.Vec2i;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

/**
 *
 * @param <C> class for the relative measure
 * @param <K> type for builder
 * @param <B> builder type
 * @param <V> variable to input and get the relative shizzle
 * @param <R> measure again, to return for builder methods
 * @param <L> segment types
 */
public interface RelativeMeasure<C, K, B extends RuntimeDefinable.Builder<K, K>, V, R extends RelativeMeasure<C, K, B, V, R, L>, L> extends RuntimeDefinable<C, B, V, L> {

    default @NotNull R percentage(double percentage) {
        return offset(OffsetType.percentage(percentage));
    }

    default @NotNull R pixel(int pixel) {
        return offset(OffsetType.pixel(pixel));
    }

    <N extends Number> @NotNull R offset(@NotNull OffsetType<@NotNull N> offsetType);

    static <C> @NotNull Vec2iRelativeMeasureGroup<@NotNull C> relative() {
        return new DummyVec2iRelativeMeasureGroup<>();
    }

    final class DummyVec2iRelativeMeasureGroup<C> extends Vec2iRelativeMeasureGroup<@NotNull C> {

        @Override
        public void finish(@NotNull ScreenSize unused) {

        }

    }

    abstract class Vec2iRelativeMeasureGroup<C> implements RelativeMeasureGroup<C, Integer, Vec2iRelativeMeasureGroup.Vec2iRelativeBuilder<C>, Vec2i, Vec2iRelativeMeasureGroup.Vec2iRelativeBuilder<C>, Axis> {
        private final Map<Axis, Vec2iRelativeBuilder<C>> axisBuilderMap = Map.of(Axis.X, new Vec2iRelativeBuilder<>(this), Axis.Y, new Vec2iRelativeBuilder<>(this));
        private final Function<Vec2iRelativeMeasureGroup<C>, C> constructor;
        protected Vec2i vec2i;

        public Vec2iRelativeMeasureGroup(@NotNull Vec2i vec2i) {
            this.vec2i = vec2i;
            constructor = (cVec2iRelativeMeasureGroup -> null);
        }

        public Vec2iRelativeMeasureGroup() {
            constructor = (cVec2iRelativeMeasureGroup -> null);
        }

        public @NotNull Map<@NotNull Axis, @NotNull Vec2iRelativeBuilder<C>> axisBuilderMap() {
            return axisBuilderMap;
        }

        public @NotNull Vec2iRelativeBuilder<C> x() {
            return axisBuilderMap.get(Axis.X);
        }

        public @NotNull Vec2iRelativeBuilder<C> y() {
            return axisBuilderMap.get(Axis.Y);
        }

        public @Nullable Vec2i vec2i() {
            return vec2i;
        }

        public abstract void finish(@NotNull ScreenSize screenSize);

        @Override
        public void add(@Nullable Vec2iRelativeBuilder<@Nullable C> cVec2iRelativeBuilder, @Nullable Axis axis) {

        }

        public static final class Vec2iRelativeBuilder<C> implements Builder<Integer, Integer>, RelativeMeasure<C, Integer, Vec2iRelativeBuilder<C>, Vec2i, Vec2iRelativeBuilder<C>, Axis> {
            private final Vec2iRelativeMeasureGroup<C> parent;

            private Vec2iRelativeBuilder(Vec2iRelativeMeasureGroup<C> parent) {
                this.parent = parent;
            }

            public Vec2iRelativeMeasureGroup<C> back() {
                return parent;
            }

            @Override
            public <N extends Number> @NotNull Vec2iRelativeBuilder<@NotNull C> offset(@NotNull OffsetType<@NotNull N> offsetType) {
                return this;
            }

            @Override
            public @NotNull C build(@NotNull Vec2i var) {
                return null;
            }

            @Override
            public @NotNull Vec2iRelativeBuilder<@NotNull C> builder(@NotNull Axis axis) {
                return this;
            }

            @Override
            public void builder(@NotNull Axis axis, @NotNull Vec2iRelativeBuilder<@NotNull C> builder) {

            }

            @Override
            public int priority() {
                return 0;
            }

            @Override
            public @NotNull Class<?> type() {
                return null;
            }

            @Override
            public Integer finish(Integer integer) {
                return 0;
            }
        }

    }

    interface RelativeMeasureGroup<C, K, B extends RuntimeDefinable.Builder<K, K>, V, M extends RelativeMeasure<C, K, B, V, M, L>, L> {

        void add(M m, L l);

    }

    enum Axis {
        X, Y
    }

    interface OffsetType<T extends Number> {

        T pixel();

        static OffsetType<Integer> pixel(int pixel) {
            return new PixelOffsetType(pixel);
        }

        static OffsetType<Double> percentage(double percentage) {
            return new PercentageOffsetType(percentage);
        }

        record PixelOffsetType(Integer pixel) implements OffsetType<Integer> {

        }

        record PercentageOffsetType(Double pixel) implements OffsetType<Double> {

        }

    }

}
