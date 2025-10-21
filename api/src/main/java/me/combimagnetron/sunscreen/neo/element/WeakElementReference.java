package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.util.data.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;

public class WeakElementReference<E extends ElementLike<E>> implements ElementLike<E> {
    private final WeakReference<E> weakReference;

    protected WeakElementReference(@NotNull E element) {
        this.weakReference = new WeakReference<>(element);
    }

    @Override
    public @Nullable Identifier identifier() {
        E e = weakReference.get();
        if (e == null) throw new RuntimeException("Fatal error: WeakRefence in element (WeakElementReference) is null. Contact plugin developers immediately.");
        return e.identifier();
    }

    @Override
    public <T, C, P extends Property<T, C>> @NotNull P property(@NotNull Class<P> propertyClass) {
        return null;
    }

    @Override
    public <T, C> @NotNull E property(@NotNull Property<@NotNull T, @NotNull C> property) {
        return null;
    }

}
