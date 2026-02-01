package me.combimagnetron.sunscreen.neo.loader;

import org.jetbrains.annotations.NotNull;

public interface Component<S extends Component<S, C>, C> {

    @NotNull ComponentLoader<S, C> loader();

    @NotNull Class<S> type();

}
