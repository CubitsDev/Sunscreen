package me.combimagnetron.sunscreen.neo.element.impl.text;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.element.impl.TextElement;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.phase.context.RenderContext;
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
    public @NotNull Canvas render(@NotNull Size property, @Nullable RenderContext context) {
        return Canvas.empty(Vec2i.zero());
    }

}
