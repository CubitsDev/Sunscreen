package me.combimagnetron.sunscreen.ui.element;

import me.combimagnetron.sunscreen.ui.property.*;
import me.combimagnetron.sunscreen.util.data.Identifier;
import org.jetbrains.annotations.NotNull;

public interface ElementLike<E extends ElementLike<E>> {

    @NotNull Identifier identifier();

    <T, C> @NotNull E property(@NotNull Property<@NotNull T, @NotNull C> property);

    default @NotNull E size(@NotNull Size size) {
        return property(size);
    }

    default @NotNull E position(@NotNull Position position) {
        return property(position);
    }

    default @NotNull E padding(@NotNull Padding padding) {
        return property(padding);
    }

    default @NotNull E margin(@NotNull Margin margin) {
        return property(margin);
    }

}
