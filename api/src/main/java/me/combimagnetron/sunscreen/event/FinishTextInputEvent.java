package me.combimagnetron.sunscreen.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.user.SunscreenUser;

public record FinishTextInputEvent<E extends ElementLike<E>>(E element, SunscreenUser<?> user, String input) implements Event {
    @Override
    public Class<? extends Event> eventType() {
        return FinishTextInputEvent.class;
    }
}
