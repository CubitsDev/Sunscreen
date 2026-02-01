package me.combimagnetron.sunscreen.neo.loader;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ComponentLoader<S extends Component<S, C>, C> {

    @NotNull S load(@NotNull C context);

}
