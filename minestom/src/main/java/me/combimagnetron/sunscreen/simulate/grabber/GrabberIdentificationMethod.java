package me.combimagnetron.sunscreen.simulate.grabber;

import org.jetbrains.annotations.NotNull;

public interface GrabberIdentificationMethod<V> {

    int windowId(@NotNull V v);

}
