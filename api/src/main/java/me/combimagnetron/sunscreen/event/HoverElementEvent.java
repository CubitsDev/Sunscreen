package me.combimagnetron.sunscreen.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.passport.util.math.Vec2d;

/**
 * Event fired when an element is hovered over.
 * @param element element that has been hovered over.
 * @param user user that hovered over said element.
 * @param coords coordinates at which the element was hovered, relative to the element.
 * @param <E> generic representing the element type.
 */
public record HoverElementEvent<E extends ElementLike<E>>(SunscreenUser<?> user, E element, Vec2i coords) implements UserEvent {

    @Override
    public Class<? extends Event> eventType() {
        return HoverElementEvent.class;
    }

}
