package me.combimagnetron.sunscreen.neo.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.input.context.TextInputContext;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;

public record UserTextStateChangeEvent(@NotNull SunscreenUser<?> user, @NotNull TextInputContext context) implements UserEvent {

    @Override
    public Class<? extends Event> eventType() {
        return UserTextStateChangeEvent.class;
    }

}
