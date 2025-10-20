package me.combimagnetron.sunscreen.neo.property;

import org.jetbrains.annotations.NotNull;

public interface PropertyContainer<R> {

    <T, C, P extends Property<T, C>> @NotNull P property(@NotNull Class<P> propertyClass);

    <T, C> @NotNull R property(@NotNull Property<@NotNull T, @NotNull C> property);

    default <T, C, P extends Property<T, C>> @NotNull P propOr(@NotNull Class<P> propertyClass, @NotNull P or) {
        P p = property(propertyClass);
        return p == null ? or : p;
    }

    default <T, C, P extends Property<T, C>> @NotNull P propOrThrow(@NotNull Class<P> propertyClass) {
        P p = property(propertyClass);
        if (p == null) throw new IllegalStateException("Property of type %s is null.".formatted(propertyClass.getSimpleName()));
        return p;
    }

    default @NotNull Size size() {
        return propOrThrow(Size.class);
    }

    default @NotNull Position position() {
        return propOrThrow(Position.class);
    }

    default @NotNull Margin margin() {
        return propOrThrow(Margin.class);
    }

    default @NotNull Padding padding() {
        return propOrThrow(Padding.class);
    }

    default @NotNull Size size(@NotNull Size or) {
        return propOr(Size.class, or);
    }

    default @NotNull Position position(@NotNull Position or) {
        return propOr(Position.class, or);
    }

    default @NotNull Margin margin(@NotNull Margin or) {
        return propOr(Margin.class, or);
    }

    default @NotNull Padding padding(@NotNull Padding or) {
        return propOr(Padding.class, or);
    }

}
