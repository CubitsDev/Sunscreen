package me.combimagnetron.sunscreen.neo.theme.decorator;

import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.graphic.NineSlice;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.Renderable;
import me.combimagnetron.sunscreen.neo.render.engine.context.RenderContext;
import me.combimagnetron.sunscreen.util.helper.PropertyHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Interface representing theme entries to link to an element, to retexture a button in a theme for example.
 * @param <E> element target type.
 */
public sealed interface ThemeDecorator<E extends ElementLike<E>> extends Renderable<Size, Canvas> permits Divider, ThemeDecorator.NineSliceThemeDecorator, ThemeDecorator.StateNineSLiceThemeDecorator {

    @NotNull Class<E> target();

    static <E extends ElementLike<E>> @NotNull NineSliceThemeDecorator<E> nineSlice(@NotNull Class<E> target, @NotNull NineSlice nineSlice) {
        return new NineSliceThemeDecorator<>(target, nineSlice);
    }

    record NineSliceThemeDecorator<E extends ElementLike<E>>(@NotNull Class<E> target, @NotNull NineSlice nineSlice) implements ThemeDecorator<E> {

        @Override
        public @NotNull Canvas render(@NotNull Size property, @Nullable RenderContext context) {
            Vec2i sizeVec = PropertyHelper.vectorOrThrow(property, Vec2i.class);
            return nineSlice.size(sizeVec);
        }

    }

    record StateNineSLiceThemeDecorator<E extends ElementLike<E>>(@NotNull Class<E> target, @NotNull List<NineSlice> nineSlices) implements ThemeDecorator<E> {

        @Override
        public @NonNull Canvas render(@NonNull Size property, @Nullable RenderContext context) {
            if (context == null) throw new IllegalArgumentException("Context may not be null while constructing decorators.");
            //int state = context.objectStorage().get();
            return null;
        }

    }

}
