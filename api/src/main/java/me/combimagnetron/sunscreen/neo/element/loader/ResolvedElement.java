package me.combimagnetron.sunscreen.neo.element.loader;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.neo.render.ScreenInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class ResolvedElement<E extends ElementLike<E>> implements Comparable<ResolvedElement<E>> {
    private final Collection<Property<?, ?>> properties = new ArrayList<>();
    private final Identifier identifier;
    private final E elementLike;
    private final ElementLike<?> parent;

    public ResolvedElement(@NotNull E elementLike, @Nullable ElementLike<?> parent, @NotNull ScreenInfo size) {
        this.identifier = elementLike.identifier();
        this.elementLike = elementLike;
        this.parent = parent;
    }

    public Identifier identifier() {
        return identifier;
    }

    public E elementLike() {
        return elementLike;
    }

    public ElementLike<?> parent() {
        return parent;
    }

    @Override
    public int compareTo(@NotNull ResolvedElement<E> o) {
        return 0;
    }
}
