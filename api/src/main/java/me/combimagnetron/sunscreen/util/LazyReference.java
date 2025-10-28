package me.combimagnetron.sunscreen.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public record LazyReference<T>(@NotNull Class<T> type, @NotNull String reference, @Nullable Function<T, ?> construct) {

    public static <D> LazyReference<D> of(@NotNull Class<D> type, @NotNull String reference, @Nullable Function<D, ?> constructor) {
        return new LazyReference<>(type, reference, constructor);
    }

    public static <D> LazyReference<D> of(@NotNull Class<D> type, @NotNull String reference) {
        return of(type, reference, null);
    }

}
