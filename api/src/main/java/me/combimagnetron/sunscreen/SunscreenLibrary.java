package me.combimagnetron.sunscreen;

import me.combimagnetron.passport.Passport;
import me.combimagnetron.passport.user.UserHandler;
import me.combimagnetron.sunscreen.session.SessionHandler;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import net.kyori.adventure.audience.Audience;
import org.slf4j.Logger;

import java.io.InputStream;
import java.nio.file.Path;

public interface SunscreenLibrary<T, P extends Audience> {
    static <T, P extends Audience> SunscreenLibrary<T, P> library() {
        return (SunscreenLibrary<T, P>) Holder.INSTANCE;
    }

    Passport<T> passport();

    Path path();

    T plugin();

    InputStream resource(String path);

    SessionHandler sessionHandler();

    UserHandler<P, SunscreenUser<P>> users();

    Logger logger();

    final class Holder {
        public static SunscreenLibrary<?, ? extends Audience> INSTANCE = null;

    }

}
