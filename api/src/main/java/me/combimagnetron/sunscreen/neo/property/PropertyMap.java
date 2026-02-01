package me.combimagnetron.sunscreen.neo.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class PropertyMap extends HashMap<Class<? extends Property<?, ?>>, Property<?, ?>> {

    public <V, T extends Property<V, T>> @Nullable T get(Class<T> propertyClass) {
        return propertyClass.cast(super.get(propertyClass));
    }

    public static @NotNull PropertyMap map() {
        return new PropertyMap();
    }

}
