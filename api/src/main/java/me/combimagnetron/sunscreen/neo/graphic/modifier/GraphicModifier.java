package me.combimagnetron.sunscreen.neo.graphic.modifier;

import me.combimagnetron.sunscreen.neo.graphic.modifier.handler.GraphicModifierHandler;
import org.jetbrains.annotations.NotNull;

public interface GraphicModifier<M> {

    @NotNull GraphicModifierHandler<@NotNull M> handler();

    @NotNull M modifier();

    @NotNull ModifierContext context();

}
