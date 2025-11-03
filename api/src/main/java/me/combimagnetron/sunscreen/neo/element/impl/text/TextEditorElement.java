package me.combimagnetron.sunscreen.neo.element.impl.text;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.impl.TextElement;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.engine.pass.RenderPass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@link TextElement} implementation for multilined text input with editor functions, think bold, italic font etc.
 */
public class TextEditorElement extends TextElement<TextEditorElement> {

    public TextEditorElement(@Nullable Identifier identifier) {
        super(identifier);
    }

    @Override
    public @NotNull <G extends GraphicLike<G>> RenderPass<TextEditorElement, G> render(@NotNull Size property) {
        return null;
    }

}
