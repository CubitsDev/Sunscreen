package me.combimagnetron.sunscreen.neo.input.text;

import me.combimagnetron.passport.logic.state.InlinedMutableState;
import me.combimagnetron.passport.logic.state.State;
import me.combimagnetron.sunscreen.neo.element.ModernElement;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;

public class TextInput<E extends ModernElement<E, Canvas>> {
    private final InlinedMutableState<String, E> input = State.inlined("", (_, _) -> {});
    private final boolean active = false;
    private final boolean finished = false;

    public boolean active() {
        return active;
    }

    public boolean finished() {
        return finished;
    }

    public void reset() {

    }

    public InlinedMutableState<String, E> state() {
        return input;
    }

}
