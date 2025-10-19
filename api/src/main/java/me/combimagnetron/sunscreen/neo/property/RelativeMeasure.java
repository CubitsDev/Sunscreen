package me.combimagnetron.sunscreen.neo.property;

import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.util.data.RuntimeDefinable;
import me.combimagnetron.sunscreen.util.math.Vec2i;
import me.combimagnetron.sunscreen.util.math.Vec4i;
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
public interface RelativeMeasure<C, K, I, B extends RuntimeDefinable.Builder<K, I>, V, R extends RelativeMeasure<C, K, I, B, V, R, L>, L> extends RuntimeDefinable<C, B, V, L> {

    default @NotNull R percentage(double percentage) {
        return offset(OffsetType.percentage(percentage));
    }

    default @NotNull R pixel(int pixel) {
        return offset(OffsetType.pixel(pixel));
    }

    <N extends Number> @NotNull R offset(@NotNull OffsetType<@NotNull N> offsetType);

    static <C> @NotNull Vec2iRelativeMeasureGroup<@NotNull C> vec2i() {
        return new DummyVec2iRelativeMeasureGroup<>();
    }

    static <C> @NotNull Vec4iRelativeMeasureGroup<@NotNull C> vec4i() {
        return new DummyVec4iRelativeMeasureGroup<>();
    }

    final class DummyVec2iRelativeMeasureGroup<C> extends Vec2iRelativeMeasureGroup<@NotNull C> {

        @Override
        public void finish(@NotNull ScreenSize unused) {

        }

    }

    final class DummyVec4iRelativeMeasureGroup<C> extends Vec4iRelativeMeasureGroup<@NotNull C> {

        @Override
        public void finish(@NotNull ScreenSize unused) {

        }

    }

    abstract class Vec2iRelativeMeasureGroup<C> implements RelativeMeasureGroup<C, Integer, Integer, Vec2iRelativeMeasureGroup.Vec2iRelativeBuilder<C>, Vec2i, Vec2iRelativeMeasureGroup.Vec2iRelativeBuilder<C>, Axis2d> {
        private final Map<Axis2d, Vec2iRelativeBuilder<C>> axisBuilderMap = Map.of(Axis2d.X, new Vec2iRelativeBuilder<>(this), Axis2d.Y, new Vec2iRelativeBuilder<>(this));
        private final Function<Vec2iRelativeMeasureGroup<C>, C> constructor;
        protected Vec2i vec2i;

        public Vec2iRelativeMeasureGroup(@NotNull Vec2i vec2i) {
            this.vec2i = vec2i;
            constructor = (cVec2iRelativeMeasureGroup -> null);
        }

        public Vec2iRelativeMeasureGroup() {
            constructor = (cVec2iRelativeMeasureGroup -> null);
        }

        public @NotNull Map<@NotNull Axis2d, @NotNull Vec2iRelativeBuilder<C>> axisBuilderMap() {
            return axisBuilderMap;
        }

        public @NotNull Vec2iRelativeBuilder<C> x() {
            return axisBuilderMap.get(Axis2d.X);
        }

        public @NotNull Vec2iRelativeBuilder<C> y() {
            return axisBuilderMap.get(Axis2d.Y);
        }

        public @Nullable Vec2i vec2i() {
            return vec2i;
        }

        public abstract void finish(@NotNull ScreenSize screenSize);

        @Override
        public void add(@Nullable Vec2iRelativeBuilder<@Nullable C> cVec2iRelativeBuilder, @Nullable RelativeMeasure.Axis2d axis2d) {

        }

        public static final class Vec2iRelativeBuilder<C> implements Builder<Integer, Integer>, RelativeMeasure<C, Integer, Integer, Vec2iRelativeBuilder<C>, Vec2i, Vec2iRelativeBuilder<C>, Axis2d> {
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
            public @NotNull Vec2iRelativeBuilder<@NotNull C> builder(@NotNull RelativeMeasure.Axis2d axis2d) {
                return this;
            }

            @Override
            public void builder(@NotNull RelativeMeasure.Axis2d axis2d, @NotNull Vec2iRelativeBuilder<@NotNull C> builder) {

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



    interface RelativeMeasureGroup<C, K, I, B extends RuntimeDefinable.Builder<K, I>, V, M extends RelativeMeasure<C, K, I, B, V, M, L>, L> {

        void add(M m, L l);

    }

    abstract class Vec4iRelativeMeasureGroup<C> implements RelativeMeasureGroup<C, Vec4i, Vec2i, Vec4iRelativeMeasureGroup.Vec4iRelativeBuilder<C>, Vec2i, Vec4iRelativeMeasureGroup.Vec4iRelativeBuilder<C>, Axis4d> {
        private final Map<Axis4d, Vec4iRelativeBuilder<C>> axisBuilderMap = Map.of(Axis4d.UP, new Vec4iRelativeBuilder<>(this), Axis4d.DOWN, new Vec4iRelativeBuilder<>(this), Axis4d.LEFT, new Vec4iRelativeBuilder<>(this), Axis4d.RIGHT, new Vec4iRelativeBuilder<>(this));
        private final Function<Vec4iRelativeMeasureGroup<C>, C> constructor;
        protected Vec4i vec4i;

        public Vec4iRelativeMeasureGroup(@NotNull Vec4i vec4i) {
            this.vec4i = vec4i;
            constructor = (cVec2iRelativeMeasureGroup -> null);
        }

        public Vec4iRelativeMeasureGroup() {
            constructor = (cVec2iRelativeMeasureGroup -> null);
        }

        public @NotNull Map<@NotNull Axis4d, @NotNull Vec4iRelativeBuilder<C>> axisBuilderMap() {
            return axisBuilderMap;
        }

        public @NotNull Vec4iRelativeBuilder<C> up() {
            return axisBuilderMap.get(Axis4d.UP);
        }

        public @NotNull Vec4iRelativeBuilder<C> down() {
            return axisBuilderMap.get(Axis4d.DOWN);
        }

        public @NotNull Vec4iRelativeBuilder<C> left() {
            return axisBuilderMap.get(Axis4d.LEFT);
        }

        public @NotNull Vec4iRelativeBuilder<C> right() {
            return axisBuilderMap.get(Axis4d.RIGHT);
        }

        public @Nullable Vec4i vec4i() {
            return vec4i;
        }

        public abstract void finish(@NotNull ScreenSize screenSize);

        @Override
        public void add(@Nullable Vec4iRelativeBuilder<@Nullable C> cVec2iRelativeBuilder, @Nullable Axis4d axis) {

        }

        public static final class Vec4iRelativeBuilder<C> implements Builder<Vec4i, Vec2i>, RelativeMeasure<C, Vec4i, Vec2i, Vec4iRelativeBuilder<C>, Vec2i, Vec4iRelativeBuilder<C>, Axis4d> {
            private final Vec4iRelativeMeasureGroup<C> parent;

            private Vec4iRelativeBuilder(Vec4iRelativeMeasureGroup<C> parent) {
                this.parent = parent;
            }

            public Vec4iRelativeMeasureGroup<C> back() {
                return parent;
            }

            @Override
            public <N extends Number> @NotNull Vec4iRelativeBuilder<@NotNull C> offset(@NotNull OffsetType<@NotNull N> offsetType) {
                return this;
            }

            @Override
            public @NotNull C build(@NotNull Vec2i var) {
                return null;
            }

            @Override
            public @NotNull Vec4iRelativeBuilder<@NotNull C> builder(@NotNull Axis4d axis) {
                return this;
            }

            @Override
            public void builder(@NotNull Axis4d axis, @NotNull Vec4iRelativeBuilder<@NotNull C> builder) {

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
            public Vec4i finish(Vec2i vec2i) {
                return null;
            }

        }

    }

    enum Axis2d {
        X, Y
    }

    enum Axis4d {
        UP, DOWN, LEFT, RIGHT
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
