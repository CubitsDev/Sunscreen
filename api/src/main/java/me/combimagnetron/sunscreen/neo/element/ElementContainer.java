package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.Renderable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ElementContainer<E extends ElementLike<E>> extends ElementLike<E>, Renderable<Size, Canvas> {

    @NotNull Collection<ElementLike<?>> children();

    <L extends ElementLike<L>> @NotNull ElementContainer<@NotNull E> add(@NotNull L elementLike);

    <L extends ElementLike<L>> @NotNull ElementContainer<@NotNull E> add(@NotNull Iterable<@NotNull L> elementLike);

    <L extends ElementLike<L>> @NotNull ElementContainer<@NotNull E> remove(@NotNull L elementLike);

    @NotNull ElementContainer<@NotNull E> remove(@Nullable Identifier identifier);

}
