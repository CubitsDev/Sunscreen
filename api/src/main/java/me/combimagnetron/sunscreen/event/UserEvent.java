package me.combimagnetron.sunscreen.event;

import me.combimagnetron.sunscreen.user.SunscreenUser;

public abstract class UserEvent {
    private final SunscreenUser<?> user;

    protected UserEvent(SunscreenUser<?> user) {
        this.user = user;
    }

}
