package me.combimagnetron.sunscreen.neo.loader;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface Component {

    @NotNull Collection<? extends @NotNull Component> dependencies();

}
