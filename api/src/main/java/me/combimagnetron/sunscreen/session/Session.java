package me.combimagnetron.sunscreen.session;

import me.combimagnetron.sunscreen.SunscreenLibrary;
import me.combimagnetron.sunscreen.ui.ModernMenu;
import me.combimagnetron.sunscreen.user.SunscreenUser;

public sealed interface Session permits Session.Impl {

    ModernMenu menu();

    SunscreenUser<?> user();

    boolean close();

    static Session session(ModernMenu openedMenu, SunscreenUser<?> user) {
        return new Impl(openedMenu, user);
    }

    record Impl(ModernMenu openedMenu, SunscreenUser<?> user) implements Session {

        @Override
        public ModernMenu menu() {
            return openedMenu;
        }

        @Override
        public boolean close() {
            if (openedMenu != null) {
                menu().close();
                SunscreenLibrary.library().sessionHandler().session(Session.session(null, user));
            }
            return true;
        }
    }

}
