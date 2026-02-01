package me.combimagnetron.sunscreen.neo.session;

import me.combimagnetron.sunscreen.neo.ActiveMenu;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;

public record Session(@NotNull ActiveMenu menu, @NotNull SunscreenUser<?> user) {

}
