package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.passport.logic.state.State;
import me.combimagnetron.sunscreen.neo.element.impl.ImageElement;
import me.combimagnetron.sunscreen.neo.element.impl.LabelElement;
import me.combimagnetron.sunscreen.neo.element.impl.text.TextBoxElement;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.passport.util.data.Identifier;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface Elements {

    static <G extends GraphicLike<G>> @NotNull ImageElement<G> image(@NotNull Identifier identifier, @NotNull G graphicLike) {
        return new ImageElement<>(identifier, graphicLike);
    }

    static @NotNull LabelElement label(@NotNull Identifier identifier, @NotNull Component text) {
        return new LabelElement(identifier, text);
    }

    static @NotNull LabelElement label(@NotNull Identifier identifier, @NotNull State<Component> text) {
        return new LabelElement(identifier, text);
    }

    static @NotNull TextBoxElement textBox(@NotNull Identifier identifier, @NotNull Component initialMessage) {
        return new TextBoxElement(identifier);
    }

    static @NotNull TextBoxElement textBox(@NotNull Identifier identifier) {
        return new TextBoxElement(identifier);
    }

}
