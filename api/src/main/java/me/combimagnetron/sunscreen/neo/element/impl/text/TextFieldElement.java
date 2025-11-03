package me.combimagnetron.sunscreen.neo.element.impl.text;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.impl.TextElement;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.engine.pass.RenderPass;
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
    public @NotNull <G extends GraphicLike<G>> RenderPass<TextFieldElement, G> render(@NotNull Size property) {
        return null;
    }

}
