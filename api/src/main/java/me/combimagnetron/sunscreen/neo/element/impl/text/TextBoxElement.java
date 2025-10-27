package me.combimagnetron.sunscreen.neo.element.impl.text;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.element.impl.TextElement;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.RenderAction;
import me.combimagnetron.sunscreen.neo.render.engine.pass.RenderPass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@link TextElement} implementation for multilined text input
 */
public class TextBoxElement extends TextElement<TextBoxElement> {

    public TextBoxElement(@Nullable Identifier identifier) {
        super(identifier);
    }

    @Override
    public @NotNull <G extends GraphicLike<G>> RenderPass<TextBoxElement, G> render(@NotNull Size property) {
        return (RenderPass<TextBoxElement, G>) RenderPass.pass(RenderPass.Origin.origin(this), RenderAction.simple(Canvas.empty(Vec2i.zero())));
    }

}
