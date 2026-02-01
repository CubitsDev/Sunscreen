package me.combimagnetron.sunscreen.neo.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.input.keybind.Keybind;
import me.combimagnetron.sunscreen.user.SunscreenUser;

/**
 * Fired whenever a valid {@link Keybind} is fired.
 * @param keybind keybind that was pressed, can be a combination of multiple modifiers/keys.
 * @param user the user that pressed the keybind.
 */
public record UserPressKeybindEvent(SunscreenUser<?> user, Keybind keybind) implements UserEvent {

    @Override
    public Class<? extends Event> eventType() {
        return UserPressKeybindEvent.class;
    }

}
