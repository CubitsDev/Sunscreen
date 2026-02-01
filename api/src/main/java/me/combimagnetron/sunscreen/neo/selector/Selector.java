package me.combimagnetron.sunscreen.neo.selector;

import com.google.common.graph.Traverser;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.element.ModernElement;
import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.neo.selector.filter.Filter;
import me.combimagnetron.passport.util.data.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public sealed interface Selector<E extends ElementLike<E>> extends ElementLike<E> permits Selector.FilteredSelector, Selector.AllSelector {

    <L extends ElementLike<L>> @NotNull Result<L> result(@NotNull Traverser<ElementLike<E>> tree);

    static @NotNull Selector<?> filtered(@NotNull Predicate<? extends ElementLike<?>> elementLikePredicate) {
        return new FilteredSelector(elementLikePredicate);
    }

    static @NotNull Selector<?> filtered(@NotNull Filter<?> elementLikePredicate) {
        return new FilteredSelector<>(elementLikePredicate);
    }

    static <E extends ElementLike<E>> @NotNull Selector<?> all() {
        return new AllSelector();
    }

    final class FilteredSelector<E extends ElementLike<E>> implements Selector<E> {
        private final Object filter;

        public FilteredSelector(Predicate<ElementLike<E>> elementLikePredicate) {
            this.filter = elementLikePredicate;
        }

        public FilteredSelector(Filter<E> filter) {
            this.filter = filter;
        }

        @Override
        public @NotNull <L extends ElementLike<L>> Result<L> result(@NotNull Traverser<ElementLike<E>> tree) {
            if (filter instanceof Predicate<?> elementLikePredicate) {

            } else if (filter instanceof Filter<?> filter) {

            }
            return null;
        }

        @Override
        public @Nullable Identifier identifier() {
            return null;
        }

        @Override
        public <T, C, P extends Property<T, C>> @NotNull P property(@NotNull Class<P> propertyClass) {
            return null;
        }

        @Override
        public <T, C> @NotNull E property(@NotNull Property<@NotNull T, @NotNull C> property) {
            return null;
        }

        @Override
        public @NotNull Collection<Property<?, ?>> properties() {
            return List.of();
        }

    }

    final class AllSelector<E extends ElementLike<E>> implements Selector<E> {

        @Override
        public @NotNull <L extends ElementLike<L>> Result<L> result(@NotNull Traverser<ElementLike<E>> tree) {
            return null;
        }

        @Override
        public @NotNull Identifier identifier() {
            return null;
        }

        @Override
        public @NotNull ElementLike property(@NotNull Property property) {
            return null;
        }

        @Override
        public @NotNull Property property(@NotNull Class propertyClass) {
            return null;
        }

        @Override
        public @NotNull Collection<Property<?, ?>> properties() {
            return List.of();
        }
    }

    final class Result<E extends ElementLike<E>> implements ElementLike<E> {
        public static final Result EMPTY = new Result(List.of());
        private final Identifier identifier = Identifier.of("selector_result", String.valueOf(hashCode()));
        private final Iterable<E> result;

        public Result(Iterable<E> result) {
            this.result = result;
        }

        @Override
        public @NotNull Identifier identifier() {
            return identifier;
        }

        @Override
        public <T, C, P extends Property<T, C>> @NotNull P property(@NotNull Class<P> propertyClass) {
            return null;
        }

        @Override
        public <T, C> @NotNull E property(@NotNull Property<@NotNull T, @NotNull C> property) {
            return (E) this;
        }

        @Override
        public @NotNull Collection<Property<?, ?>> properties() {
            return List.of();
        }

        public Iterable<E> result() {
            return result;
        }

    }

}
