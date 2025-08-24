package me.combimagnetron.sunscreen.ui.graphic;

import me.combimagnetron.sunscreen.ui.graphic.modifier.GraphicModifier;
import org.jetbrains.annotations.NotNull;

public interface GraphicLike<G extends GraphicLike<G>> {

    @NotNull G modifier(@NotNull GraphicModifier modifier);

}
