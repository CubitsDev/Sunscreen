package me.combimagnetron.sunscreen.neo.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.element.ModernElement;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.passport.util.math.Vec2i;

/**
 * Event fired when an element is clicked, only when the element implements {@link me.combimagnetron.sunscreen.neo.input.Interactable}
 * @param element the element that was interacted with.
 * @param user user that interacted with said element.
 * @param coords the coordinates at which the element was clicked, relative to the element itself.
 * @param <E> generic element type.
 */
public record UserClickElementEvent<E extends ModernElement<E, ?>>(SunscreenUser<?> user, E element, Vec2i coords) implements UserEvent {

    @Override
    public Class<? extends Event> eventType() {
        return UserClickElementEvent.class;
    }

}
