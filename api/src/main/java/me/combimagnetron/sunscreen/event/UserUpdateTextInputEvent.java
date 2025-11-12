package me.combimagnetron.sunscreen.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.user.SunscreenUser;

public record UserUpdateTextInputEvent(SunscreenUser<?> user, String current) implements UserEvent {

    @Override
    public Class<? extends Event> eventType() {
        return UserUpdateTextInputEvent.class;
    }

}
