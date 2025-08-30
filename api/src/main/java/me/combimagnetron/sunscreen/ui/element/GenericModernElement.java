package me.combimagnetron.sunscreen.ui.element;

import me.combimagnetron.sunscreen.ui.property.Property;
import me.combimagnetron.sunscreen.util.data.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericModernElement<E extends ModernElement<E>> implements ModernElement<E> {
    private final List<Property<?, ?>> properties = new ArrayList<>();
    private final Identifier identifier;

    protected GenericModernElement(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public @NotNull Identifier identifier() {
        return identifier;
    }

    @Override
    public @NotNull <T, C> E property(@NotNull Property<T, C> property) {
        properties.add(property);
        return (E) this;
    }

}
