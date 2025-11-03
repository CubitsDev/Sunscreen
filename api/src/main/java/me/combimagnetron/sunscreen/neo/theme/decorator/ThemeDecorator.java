package me.combimagnetron.sunscreen.neo.theme.decorator;

import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.Renderable;
import me.combimagnetron.sunscreen.neo.render.engine.pass.RenderPass;
import org.jetbrains.annotations.NotNull;

/**
 * Interface representing theme entries to link to an element, to retexture a button in a theme for example.
 * @param <E> element target type.
 */
public sealed interface ThemeDecorator<E extends ElementLike<E>, D extends ThemeDecorator<E, D>> extends Renderable<D, Size> permits ThemeDecorator.StaticThemeDecorator {

    Class<E> target();

    final class StaticThemeDecorator<E extends ElementLike<E>> implements ThemeDecorator<E, StaticThemeDecorator<E>> {

        @Override
        public Class<E> target() {
            return null;
        }

        @Override
        public @NotNull <G extends GraphicLike<G>> RenderPass<StaticThemeDecorator<E>, G> render(@NotNull Size property) {
            return null;// newRenderPass<StaticThemeDecorator<E>, Canvas>(new RenderPass.Origin<>(this, Identifier.of("")), RenderAction.simple(Canvas.empty(Vec2i.zero())));
        }

    }

}
