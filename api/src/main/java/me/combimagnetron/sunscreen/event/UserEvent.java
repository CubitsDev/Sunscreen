package me.combimagnetron.sunscreen.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.user.SunscreenUser;

public interface UserEvent extends Event {

    SunscreenUser<?> user();

}
