package me.combimagnetron.sunscreen.neo.loader;

import it.unimi.dsi.fastutil.objects.ObjectLists;

import java.util.List;

public interface MenuComponent<S extends MenuComponent<S>> extends Component<S, MenuComponentLoaderContext> {

    List<MenuComponent<?>> EMPTY = ObjectLists.emptyList();

}
