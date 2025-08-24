package me.combimagnetron.sunscreen.user;

import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import com.github.retrooper.packetevents.wrapper.common.client.WrapperCommonClientCustomClickAction;
import me.combimagnetron.passport.user.User;
import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.session.Session;
import me.combimagnetron.sunscreen.ui.ModernLayout;
import net.kyori.adventure.audience.Audience;

import java.util.Arrays;

public interface SunscreenUser<T extends Audience> extends User<T> {

    ScreenSize screenSize();

    void screenSize(ScreenSize screenSize);

    float fov();

    void fov(float fov);

    boolean permission(String permission);

    Session session();

    Session open(ModernLayout template);

    default void send(PacketWrapper<?>... packetWrappers) {
        Arrays.stream(packetWrappers).forEach(wrapper -> connection().send(wrapper));
    }

}
