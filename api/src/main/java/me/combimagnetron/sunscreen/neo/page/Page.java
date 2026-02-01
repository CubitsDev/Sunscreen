package me.combimagnetron.sunscreen.neo.page;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.RootLike;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.loader.MenuComponent;
import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.neo.property.PropertyContainer;
import me.combimagnetron.sunscreen.neo.property.PropertyMap;
import me.combimagnetron.sunscreen.util.IdentifierHolder;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public final class Page implements PropertyContainer<Page>, RootLike<Page>, IdentifierHolder {
    private final PropertyMap propertyMap = PropertyMap.map();
    private final Identifier identifier;

    private Page(Identifier identifier) {
        this.identifier = identifier;
    }

    public static @NotNull Page page(@NotNull Identifier identifier) {
        return new Page(identifier);
    }

    @Override
    public @NotNull Identifier identifier() {
        return identifier;
    }

    @Override
    public @NonNull <E extends ElementLike<E>> Page element(@NotNull ElementLike<E> e) {
        return this;
    }

    @Override
    public @NonNull <C extends MenuComponent<C>> Page component(@NotNull MenuComponent<C> menuComponent) {
        return this;
    }

    @Override
    public @NotNull Collection<ElementLike<?>> elementLikes() {
        return List.of();
    }

    @Override
    public @NotNull Collection<MenuComponent<?>> components() {
        return List.of();
    }

    @Override
    public @Nullable <T, C, P extends Property<T, C>> P property(@NotNull Class<P> propertyClass) {
        return (P) propertyMap.get(propertyClass);
    }

    @Override
    public @NonNull <T, C> Page property(@NotNull Property<T, C> property) {
        this.propertyMap.put((Class<? extends Property<?, ?>>) property.getClass(), property);
        return this;
    }

    @Override
    public @NotNull Collection<Property<?, ?>> properties() {
        return List.of();
    }

}
