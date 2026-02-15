package me.combimagnetron.sunscreen.simulate;

import me.combimagnetron.sunscreen.neo.loader.MenuComponent;
import me.combimagnetron.sunscreen.neo.loader.ComponentLoader;
import me.combimagnetron.sunscreen.neo.loader.MenuComponentLoaderContext;
import org.jetbrains.annotations.NotNull;

public class MenuSimulator implements MenuComponent<MenuSimulator> {

    @Override
    public @NotNull ComponentLoader<MenuSimulator, MenuComponentLoaderContext> loader() {
        return null;
    }

    @Override
    public @NotNull Class<MenuSimulator> type() {
        return null;
    }

}
