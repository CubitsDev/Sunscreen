package me.combimagnetron.sunscreen.neo.platform;

import me.combimagnetron.sunscreen.neo.loader.MenuComponent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface Platform {

    @NotNull String name();

    @NotNull Collection<MenuComponent<?>> unavailable();

    boolean packetEventsBase();

}
