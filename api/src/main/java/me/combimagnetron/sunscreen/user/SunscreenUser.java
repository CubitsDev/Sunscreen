package me.combimagnetron.sunscreen.user;

import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import me.combimagnetron.passport.user.User;
import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.session.Session;
import me.combimagnetron.sunscreen.neo.MenuRoot;
import net.kyori.adventure.audience.Audience;

import java.util.Arrays;

public interface SunscreenUser<T extends Audience> extends User<T> {

    ScreenSize screenSize();

    void screenSize(ScreenSize screenSize);

    boolean permission(String permission);

    Session session();

    Session open(MenuRoot template);

    default void send(PacketWrapper<?>... packetWrappers) {
        Arrays.stream(packetWrappers).forEach(wrapper -> connection().send(wrapper));
    }

}
