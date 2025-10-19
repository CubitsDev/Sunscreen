package me.combimagnetron.sunscreen.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.input.keybind.Keybind;
import me.combimagnetron.sunscreen.user.SunscreenUser;

public record KeybindPressedEvent(Keybind keybind, SunscreenUser<?> user) implements Event {

    @Override
    public Class<? extends Event> eventType() {
        return KeybindPressedEvent.class;
    }

}
