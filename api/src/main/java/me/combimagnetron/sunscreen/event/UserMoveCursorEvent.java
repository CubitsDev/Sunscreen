package me.combimagnetron.sunscreen.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;

public record UserMoveCursorEvent(@NotNull SunscreenUser<?> user, @NotNull Vec2i position) implements UserEvent {

    @Override
    public Class<? extends Event> eventType() {
        return UserMoveCursorEvent.class;
    }

}
