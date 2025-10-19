package me.combimagnetron.sunscreen.neo.element.impl;

import me.combimagnetron.sunscreen.logic.state.MutableState;
import me.combimagnetron.sunscreen.logic.state.State;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.util.data.Identifier;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class LabelElement<G extends GraphicLike<G>> extends ImageElement<G> {

    public LabelElement(@NotNull Identifier identifier, @NotNull Component text) {
        super(identifier, null);
    }

    public LabelElement(@NotNull Identifier identifier, @NotNull State<Component> componentState) {
        super(identifier, null);
        if (componentState instanceof MutableState<Component> componentMutableState) {
            componentMutableState.observe((old, current) -> {});
        }
    }

}
