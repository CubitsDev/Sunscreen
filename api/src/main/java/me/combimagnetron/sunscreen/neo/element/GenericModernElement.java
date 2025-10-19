package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.util.data.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericModernElement<E extends ModernElement<E>> implements ModernElement<E> {
    private final List<Property<?, ?>> properties = new ArrayList<>();
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
        properties.add(property);
        return (E) this;
    }

}
