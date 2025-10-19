package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.sunscreen.neo.property.*;
import me.combimagnetron.sunscreen.util.data.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ElementLike<E extends ElementLike<E>> {

    @Nullable Identifier identifier();

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
