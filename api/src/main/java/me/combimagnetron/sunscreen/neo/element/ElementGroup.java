package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.passport.util.data.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ElementGroup<E extends ElementLike<E>> extends ElementLike<E> {

    <L extends ElementLike<L>> @NotNull ElementGroup<@NotNull E> add(@NotNull L elementLike);

    <L extends ElementLike<L>> @NotNull ElementGroup<@NotNull E> add(@NotNull Iterable<@NotNull L> elementLike);

    <L extends ElementLike<L>> @NotNull ElementGroup<@NotNull E> remove(@NotNull L elementLike);

    @NotNull ElementGroup<@NotNull E> remove(@Nullable Identifier identifier);

}
