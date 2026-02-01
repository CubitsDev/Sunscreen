package me.combimagnetron.sunscreen.neo.graphic.modifier;

import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.neo.property.PropertyContainer;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record ModifierContext(@NotNull Map<Class<? extends Property<?, ?>>, Property<?, ?>> propertyMap) implements PropertyContainer<ModifierContext> {

    public static ModifierContext of(Property<?, ?>... properties) {
        return new ModifierContext(streamToMap(Arrays.stream(properties)));
    }

    public static ModifierContext of(Collection<? extends Property<?, ?>> properties) {
        return new ModifierContext(streamToMap(properties.stream()));
    }

    private static Map<Class<? extends Property<?, ?>>, Property<?, ?>> streamToMap(Stream<? extends Property<?, ?>> stream) {
        return stream.collect(Collectors.toMap(prop -> (Class<? extends Property<?, ?>>) prop.getClass(), Function.identity()));
    }

    @Override
    public <T, C, P extends Property<T, C>> @NotNull P property(@NotNull Class<P> propertyClass) {
        return (P) propertyMap.get(propertyClass);
    }

    @Override
    public @NotNull <T, C> ModifierContext property(@NotNull Property<@NotNull T, @NotNull C> property) {
        propertyMap.put((Class<? extends Property<?, ?>>) property.getClass(), property);
        return this;
    }

    @Override
    public @NotNull Collection<Property<?, ?>> properties() {
        return List.of();
    }

}
