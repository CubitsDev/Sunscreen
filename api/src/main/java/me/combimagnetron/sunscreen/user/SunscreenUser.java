package me.combimagnetron.sunscreen.user;

import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import me.combimagnetron.passport.user.User;
import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.session.Session;
import me.combimagnetron.sunscreen.neo.MenuRoot;
import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public interface SunscreenUser<T extends Audience> extends User<T> {

    @NotNull ScreenSize screenSize();

    void screenSize(@NotNull ScreenSize screenSize);

    boolean permission(@NotNull String permission);

    @Nullable Session session();

    Session open(@NotNull MenuRoot template);

    default void send(@NotNull PacketWrapper<?>... packetWrappers) {
        Arrays.stream(packetWrappers).forEach(wrapper -> connection().send(wrapper));
    }

}
