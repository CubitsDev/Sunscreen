package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.passport.util.data.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ElementContainer<E extends ElementLike<E>> extends ElementLike<E> {

    <L extends ElementLike<L>> @NotNull ElementContainer<@NotNull E> add(@NotNull L elementLike);

    <L extends ElementLike<L>> @NotNull ElementContainer<@NotNull E> add(@NotNull Iterable<@NotNull L> elementLike);

    <L extends ElementLike<L>> @NotNull ElementContainer<@NotNull E> remove(@NotNull L elementLike);

    @NotNull ElementContainer<@NotNull E> remove(@Nullable Identifier identifier);

}
