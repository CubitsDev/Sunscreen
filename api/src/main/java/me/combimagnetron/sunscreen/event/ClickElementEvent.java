package me.combimagnetron.sunscreen.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.element.ModernElement;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.passport.util.math.Vec2i;

public record ClickElementEvent<E extends ModernElement<E>>(E element, SunscreenUser<?> user, Vec2i coords, boolean right) implements Event {

    public static <T extends ModernElement<T>> ClickElementEvent<T> create(T element, SunscreenUser<?> user, Vec2i coords, boolean right) {
        return new ClickElementEvent<>(element, user, coords, right);
    }

    @Override
    public Class<? extends Event> eventType() {
        return ClickElementEvent.class;
    }
}
