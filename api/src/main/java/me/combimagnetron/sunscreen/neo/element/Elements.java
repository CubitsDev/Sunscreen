package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.sunscreen.neo.element.impl.ImageElement;
import me.combimagnetron.sunscreen.neo.element.impl.LabelElement;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.util.data.Identifier;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface Elements {

    static <G extends GraphicLike<G>> @NotNull ImageElement<G> image(@NotNull Identifier identifier, @NotNull G graphicLike) {
        return new ImageElement<>(identifier, graphicLike);
    }

    static <G extends GraphicLike<G>> @NotNull LabelElement<G> label(@NotNull Identifier identifier, @NotNull Component text) {
        return new LabelElement<>(identifier, text);
    }

}
