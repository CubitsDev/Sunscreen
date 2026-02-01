package me.combimagnetron.sunscreen.neo.graphic.modifier;

import me.combimagnetron.sunscreen.neo.graphic.modifier.impl.Mask;
import me.combimagnetron.sunscreen.neo.graphic.modifier.impl.Monochrome;
import me.combimagnetron.sunscreen.neo.graphic.shape.Shape;
import org.jetbrains.annotations.NotNull;

public interface GraphicModifiers {

    static @NotNull Mask mask(@NotNull Shape mask, @NotNull ModifierContext modifierContext) {
        return new Mask(mask, modifierContext);
    }

    static @NotNull Monochrome monochrome() {
        return new Monochrome();
    }

}
