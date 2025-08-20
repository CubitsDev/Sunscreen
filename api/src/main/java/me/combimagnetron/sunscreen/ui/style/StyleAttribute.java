package me.combimagnetron.sunscreen.ui.style;

import org.jetbrains.annotations.NotNull;

public interface StyleAttribute<T> {

    @NotNull Class<@NotNull T> type();

}
