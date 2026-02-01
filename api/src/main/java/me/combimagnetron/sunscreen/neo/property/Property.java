package me.combimagnetron.sunscreen.neo.property;

import me.combimagnetron.sunscreen.neo.property.handler.PropertyHandler;
import org.jetbrains.annotations.NotNull;

public interface Property<T, C> {

    @NotNull Class<@NotNull T> type();

    @NotNull PropertyHandler<@NotNull C> handler();

}
