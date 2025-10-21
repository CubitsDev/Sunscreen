package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.sunscreen.neo.loader.Component;
import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.util.data.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

@SuppressWarnings("unchecked")
public abstract class GenericModernElement<E extends ModernElement<E>> implements ModernElement<E> {
    private final Map<Class<? extends Property<?,?>>, Property<?, ?>> properties = new WeakHashMap<>();
    private final Identifier identifier;

    protected GenericModernElement(@Nullable Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public @Nullable Identifier identifier() {
        return identifier;
    }

    @Override
    public @NotNull <T, C> E property(@NotNull Property<T, C> property) {
        properties.put((Class<? extends Property<?, ?>>) property.getClass(), property);
        return (E) this;
    }

    @Override
    public <T, C, P extends Property<T, C>> @NotNull P property(@NotNull Class<P> propertyClass) {
        return (P) properties.get(propertyClass);
    }

    @Override
    public @NotNull Collection<? extends @NotNull Component> dependencies() {
        return List.of();
    }

}
