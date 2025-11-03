package me.combimagnetron.sunscreen.neo.element.impl;

import me.combimagnetron.passport.event.EventBus;
import me.combimagnetron.passport.logic.state.InlinedMutableState;
import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.event.TextInputFinishedEvent;
import me.combimagnetron.sunscreen.event.TextInputUpdatedEvent;
import me.combimagnetron.sunscreen.neo.element.GenericModernElement;
import me.combimagnetron.sunscreen.neo.element.ModernElement;
import me.combimagnetron.sunscreen.neo.input.Interactable;
import me.combimagnetron.sunscreen.neo.input.ListenerReferences;
import me.combimagnetron.sunscreen.neo.input.text.TextInput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public abstract class TextElement<E extends ModernElement<E>> extends GenericModernElement<E> implements Interactable<TextElement<E>, TextElement.TextElementListenerReferences<E>> {
    private final TextElementListenerReferences<E> references = new TextElementListenerReferences<>(this);
    private final TextInput<E> textInput = new TextInput<>();

    protected TextElement(@Nullable Identifier identifier) {
        super(identifier);
        InlinedMutableState<String, E> inlinedMutableState = textInput().state();
        inlinedMutableState.returns((E) this);
    }

    public @NotNull TextInput<E> textInput() {
        if (this.textInput.finished()) {
            this.textInput.reset();
        }
        return this.textInput;
    }

    @Override
    public TextElementListenerReferences<E> listen() {
        return references;
    }

    public record TextElementListenerReferences<F extends ModernElement<F>>(TextElement<F> back) implements ListenerReferences<TextElement<F>> {

        public TextElementListenerReferences<F> finished(Consumer<TextInputFinishedEvent> event) {
            EventBus.subscribe(TextInputFinishedEvent.class, event);
            return this;
        }

        public TextElementListenerReferences<F> updated(Consumer<TextInputUpdatedEvent> event) {
            EventBus.subscribe(TextInputUpdatedEvent.class, event);
            return this;
        }

    }

}
