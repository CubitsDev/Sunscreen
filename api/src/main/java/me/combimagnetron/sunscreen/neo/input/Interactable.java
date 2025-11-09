package me.combimagnetron.sunscreen.neo.input;

import org.jetbrains.annotations.NotNull;

public interface Interactable<T, R extends ListenerReferences<T>> {

    @NotNull R listen();

}
