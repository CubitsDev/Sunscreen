package me.combimagnetron.sunscreen.neo.element.impl;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.GenericInteractableModernElement;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.input.ListenerReferences;
import me.combimagnetron.sunscreen.neo.input.context.InputContext;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.phase.context.RenderContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ButtonElement extends GenericInteractableModernElement<ButtonElement, Canvas, ButtonElement.ButtonElementListenerReferences> {
    private final ButtonElementListenerReferences references = new ButtonElementListenerReferences(this);

    public ButtonElement(@Nullable Identifier identifier) {
        super(identifier);
    }

    @Override
    public @NotNull ButtonElementListenerReferences listen() {
        return references;
    }

    @Override
    public <C extends InputContext<?>> @NotNull C input(Class<C> clazz) {
        return null;
    }

    @Override
    public @NotNull Canvas render(@NotNull Size property, @Nullable RenderContext context) {
        //todo replace with real error handling for the editor and placeholder textures.
        if (context == null) return Canvas.url("");
        return context.decorators().get(this.getClass()).render(property, context);
    }

    public record ButtonElementListenerReferences(ButtonElement buttonElement) implements ListenerReferences<ButtonElement, ButtonElementListenerReferences> {

        @Override
        public ButtonElement back() {
            return buttonElement;
        }

    }

}
