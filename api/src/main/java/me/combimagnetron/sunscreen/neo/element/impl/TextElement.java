package me.combimagnetron.sunscreen.neo.element.impl;

import me.combimagnetron.passport.logic.state.InlinedMutableState;
import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.GenericModernElement;
import me.combimagnetron.sunscreen.neo.element.ModernElement;
import me.combimagnetron.sunscreen.neo.input.text.TextInput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class TextElement<E extends ModernElement<E>> extends GenericModernElement<E> {
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

}
