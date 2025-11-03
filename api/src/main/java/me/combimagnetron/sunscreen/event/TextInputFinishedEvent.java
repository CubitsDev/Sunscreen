package me.combimagnetron.sunscreen.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.input.text.TextInput;
import me.combimagnetron.sunscreen.user.SunscreenUser;

public record TextInputFinishedEvent(SunscreenUser<?> user, TextInput<?> input) implements UserEvent {

    @Override
    public Class<? extends Event> eventType() {
        return TextInputFinishedEvent.class;
    }

}
