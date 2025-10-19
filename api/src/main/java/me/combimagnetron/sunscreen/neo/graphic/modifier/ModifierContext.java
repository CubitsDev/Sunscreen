package me.combimagnetron.sunscreen.neo.graphic.modifier;

import me.combimagnetron.sunscreen.neo.property.Property;

import java.util.Collection;
import java.util.List;

public record ModifierContext(Collection<? extends Property<?, ?>> properties) {

    public static ModifierContext from(Property<?, ?>... properties) {
        return new ModifierContext(List.of(properties));
    }

    public static ModifierContext of(Collection<? extends Property<?, ?>> properties) {
        return new ModifierContext(properties);
    }

}
