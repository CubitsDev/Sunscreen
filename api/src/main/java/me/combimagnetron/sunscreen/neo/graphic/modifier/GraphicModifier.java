package me.combimagnetron.sunscreen.neo.graphic.modifier;

import me.combimagnetron.sunscreen.neo.graphic.BufferedColorSpace;

public interface GraphicModifier<M> {

    void apply(BufferedColorSpace current, M modifier, ModifierContext modifierContext);

}
