package me.combimagnetron.sunscreen.neo.selector;

import me.combimagnetron.sunscreen.neo.MenuRoot;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.element.tree.ElementTree;
import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.neo.selector.filter.Filter;
import me.combimagnetron.sunscreen.util.data.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public interface Selector<E extends ElementLike<E>> extends ElementLike<E> {

    <E extends ElementLike<E>> @NotNull Result<E> result(@NotNull MenuRoot menuRoot);

    static <E extends ElementLike<E>> @NotNull Selector<?> filtered(@NotNull Predicate<? extends ElementLike<?>> elementLikePredicate) {
        return new FilteredSelector(elementLikePredicate);
    }

    static <E extends ElementLike<E>> @NotNull Selector<?> filtered(@NotNull Filter<?> elementLikePredicate) {
        return new FilteredSelector<>(elementLikePredicate);
    }

    static <E extends ElementLike<E>> @NotNull Selector<?> all(@NotNull MenuRoot menuRoot) {
        return new AllSelector();
    }

    class FilteredSelector<E extends ElementLike<E>> implements Selector<E> {
        private Object filter;

        public FilteredSelector(Predicate<ElementLike<E>> elementLikePredicate) {
            this.filter = elementLikePredicate;
        }

        public FilteredSelector(Filter<E> filter) {
            this.filter = filter;
        }

        @Override
        public @NotNull Result<E> result(@NotNull MenuRoot menuRoot) {
            ElementTree elementTree = menuRoot.elementTree();
            if (elementTree == null) return (Result<E>) Result.EMPTY;
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
    }

    class AllSelector implements Selector {

        @Override
        public @NotNull Result result(@NotNull MenuRoot menuRoot) {
            return null;
        }

        @Override
        public @Nullable Identifier identifier() {
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
    }

    final class Result<E extends ElementLike<E>> implements ElementLike<E> {
        public static final Result EMPTY = new Result(List.of());
        private final Identifier identifier = Identifier.of("selector_result", String.valueOf(hashCode()));
        private final Iterable<E> result;

        public Result(Iterable<E> result) {
            this.result = result;
        }

        @Override
        public @Nullable Identifier identifier() {
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

        public Iterable<E> result() {
            return result;
        }

    }

}
