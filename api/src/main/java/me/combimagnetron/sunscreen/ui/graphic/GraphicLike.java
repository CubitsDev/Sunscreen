package me.combimagnetron.sunscreen.ui.graphic;

import me.combimagnetron.sunscreen.ui.graphic.modifier.GraphicModifier;
import me.combimagnetron.sunscreen.ui.style.Style;
import org.jetbrains.annotations.NotNull;

public interface GraphicLike<G extends GraphicLike<G>> {

    <M extends GraphicModifier> @NotNull G modifier(@NotNull M modifier);

    <S extends Style> @NotNull G style(@NotNull S style);

}
