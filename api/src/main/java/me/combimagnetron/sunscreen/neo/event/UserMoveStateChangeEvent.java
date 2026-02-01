package me.combimagnetron.sunscreen.neo.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.input.context.MouseInputContext;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;

public record UserMoveStateChangeEvent(@NotNull SunscreenUser<?> user, @NotNull MouseInputContext context) implements UserEvent {

    @Override
    public Class<? extends Event> eventType() {
        return UserMoveStateChangeEvent.class;
    }

}
