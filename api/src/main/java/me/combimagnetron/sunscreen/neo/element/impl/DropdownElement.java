package me.combimagnetron.sunscreen.neo.element.impl;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.GenericInteractableModernElement;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.input.ListenerReferences;
import me.combimagnetron.sunscreen.neo.input.context.InputContext;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.engine.context.RenderContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class DropdownElement extends GenericInteractableModernElement<DropdownElement, Canvas, DropdownElement.DropdownElementListenerReferences> {
    private final DropdownElementListenerReferences references = new DropdownElementListenerReferences(this);

    protected DropdownElement(@Nullable Identifier identifier) {
        super(identifier);
    }

    @Override
    public @NotNull DropdownElementListenerReferences listen() {
        return references;
    }

    @Override
    public <C extends InputContext<?>> @NotNull C input(Class<C> clazz) {
        return null;
    }

    @Override
    public @NonNull Canvas render(@NonNull Size property, @Nullable RenderContext context) {
        return null;
    }

    public record DropdownElementListenerReferences(DropdownElement parent) implements ListenerReferences<DropdownElement, DropdownElementListenerReferences> {

        @Override
        public DropdownElement back() {
            return parent;
        }

    }

}
