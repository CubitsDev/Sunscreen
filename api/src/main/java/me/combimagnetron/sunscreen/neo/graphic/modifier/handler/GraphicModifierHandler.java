package me.combimagnetron.sunscreen.neo.graphic.modifier.handler;

import me.combimagnetron.sunscreen.neo.graphic.BufferedColorSpace;
import me.combimagnetron.sunscreen.neo.graphic.modifier.ModifierContext;

@FunctionalInterface
public interface GraphicModifierHandler<M> {

    void apply(BufferedColorSpace current, M modifier, ModifierContext modifierContext);

}
