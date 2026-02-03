package me.combimagnetron.sunscreen.neo.element.impl.text;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.impl.TextElement;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.engine.context.RenderContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@link TextElement} implementation for single-lined/limited, small text inputs.
 */
public class TextFieldElement extends TextElement<TextFieldElement> {

    public TextFieldElement(@Nullable Identifier identifier) {
        super(identifier);
    }

    @Override
    public @NotNull Canvas render(@NotNull Size property, @Nullable RenderContext context) {
        return null;
    }

}
