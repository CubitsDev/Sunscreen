package me.combimagnetron.sunscreen.neo.theme.decorator;

import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.graphic.NineSlice;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.Renderable;
import me.combimagnetron.sunscreen.neo.render.phase.context.RenderContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface representing theme entries to link to an element, to retexture a button in a theme for example.
 * @param <E> element target type.
 */
public sealed interface ThemeDecorator<E extends ElementLike<E>> extends Renderable<Size, Canvas> permits Divider, ThemeDecorator.NineSliceThemeDecorator {

    @NotNull Class<E> target();

    static <E extends ElementLike<E>> @NotNull NineSliceThemeDecorator<E> nineSlice(@NotNull Class<E> target, @NotNull NineSlice nineSlice) {
        return new NineSliceThemeDecorator<>(target, nineSlice);
    }

    record NineSliceThemeDecorator<E extends ElementLike<E>>(@NotNull Class<E> target, @NotNull NineSlice nineSlice) implements ThemeDecorator<E> {

        @Override
        public @NotNull Canvas render(@NotNull Size property, @Nullable RenderContext context) {
            return null;// newRenderPass<StaticThemeDecorator<E>, Canvas>(new RenderPass.Origin<>(this, Identifier.of("")), RenderAction.simple(Canvas.empty(Vec2i.zero())));
        }

    }

}
