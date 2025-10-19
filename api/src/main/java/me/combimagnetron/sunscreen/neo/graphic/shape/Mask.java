package me.combimagnetron.sunscreen.neo.graphic.shape;

import me.combimagnetron.sunscreen.neo.graphic.BufferedColorSpace;
import me.combimagnetron.sunscreen.neo.graphic.modifier.GraphicModifier;

public class Mask implements GraphicModifier<Shape> {

    @Override
    public void apply(BufferedColorSpace current, Shape modifier) {
        modifier.shape().stream().forEachOrdered();
    }

}
