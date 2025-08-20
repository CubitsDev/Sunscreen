package me.combimagnetron.sunscreen.ui.property;

import me.combimagnetron.sunscreen.ui.property.handler.PropertyHandler;
import org.jetbrains.annotations.NotNull;

public interface Property<T, C> {

    @NotNull Class<@NotNull T> type();

    @NotNull PropertyHandler<@NotNull T, @NotNull C> handler();

}
