package me.combimagnetron.sunscreen.neo.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.input.context.ScrollInputContext;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;

public record UserScrollStateChangeEvent(@NotNull SunscreenUser<?> user, @NotNull ScrollInputContext context) implements UserEvent {

    @Override
    public Class<? extends Event> eventType() {
        return UserScrollStateChangeEvent.class;
    }

}
