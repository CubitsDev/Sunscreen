package me.combimagnetron.sunscreen.simulate;

import me.combimagnetron.sunscreen.neo.loader.MenuComponent;
import me.combimagnetron.sunscreen.neo.loader.ComponentLoader;
import org.jetbrains.annotations.NotNull;

public class MenuSimulator implements MenuComponent<MenuSimulator> {

    @Override
    public @NotNull ComponentLoader<MenuSimulator, ?> loader() {
        return null;
    }

}
