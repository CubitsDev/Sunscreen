package me.combimagnetron.sunscreen.neo.input;

public interface Interactable<T, R extends ListenerReferences<T>> {

    R listen();

}
