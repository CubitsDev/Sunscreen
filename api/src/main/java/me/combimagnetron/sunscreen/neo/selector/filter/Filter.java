package me.combimagnetron.sunscreen.neo.selector.filter;

import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.passport.util.data.Identifiable;
import me.combimagnetron.passport.util.data.Identifier;
import org.jetbrains.annotations.NotNull;

public interface Filter<E extends ElementLike<E>> {

    boolean test(E element);

    static <F extends ElementLike<F>> Filter<F> identifiable(@NotNull Identifiable identifiable, @NotNull Identifier.TestDepth testDepth) {
        return new IdentifiableFilter<>(identifiable, testDepth);
    }

    static <F extends ElementLike<F>> Filter<F> identifiable(@NotNull Identifiable identifiable) {
        return new IdentifiableFilter<>(identifiable);
    }

    record IdentifiableFilter<E extends ElementLike<E>>(Identifiable identifiable, Identifier.TestDepth testDepth) implements Filter<E> {

        public IdentifiableFilter(Identifiable identifiable) {
            this(identifiable, Identifier.TestDepth.ANY);
        }

        @Override
        public boolean test(E element) {
            Identifier identifier = element.identifier();
            if (identifier == null) return false;
            return identifier.anyMatch(identifiable, testDepth);
        }

    }

}
