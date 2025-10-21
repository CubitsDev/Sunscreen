package me.combimagnetron.sunscreen.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.passport.util.math.Vec2d;

public record HoverElementEvent<E extends ElementLike<E>>(E element, SunscreenUser<?> user, Vec2d coords) implements Event {
    @Override
    public Class<? extends Event> eventType() {
        return HoverElementEvent.class;
    }
}
