package me.combimagnetron.sunscreen.neo.graphic.modifier;

import me.combimagnetron.sunscreen.neo.graphic.shape.Mask;
import me.combimagnetron.sunscreen.neo.graphic.shape.Shape;
import org.jetbrains.annotations.NotNull;

public interface GraphicModifiers {

    static @NotNull Mask mask(@NotNull Shape mask) {
        return new Mask(mask);
    }

}
