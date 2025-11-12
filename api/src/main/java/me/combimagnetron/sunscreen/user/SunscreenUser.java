package me.combimagnetron.sunscreen.user;

import me.combimagnetron.passport.user.User;
import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.neo.MenuTemplate;
import me.combimagnetron.sunscreen.session.Session;
import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SunscreenUser<T extends Audience> extends User<T> {

    @NotNull ScreenSize screenSize();

    void screenSize(@NotNull ScreenSize screenSize);

    @Nullable Session session();

    Session open(@NotNull MenuTemplate template);

}
