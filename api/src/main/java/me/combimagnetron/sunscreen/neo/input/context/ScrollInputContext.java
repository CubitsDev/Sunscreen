package me.combimagnetron.sunscreen.neo.input.context;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.event.UserScrollStateChangeEvent;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;

public record ScrollInputContext(boolean active, float value) implements InputContext<UserScrollStateChangeEvent> {

    @Override
    public @NotNull Class<UserScrollStateChangeEvent> eventType() {
        return UserScrollStateChangeEvent.class;
    }

    @Override
    public @NotNull UserScrollStateChangeEvent constructEvent(@NotNull SunscreenUser<?> user) {
        return new UserScrollStateChangeEvent(user, this);
    }

    public @NotNull ScrollInputContext withActive(boolean active) {
        return new ScrollInputContext(active, value);
    }

    public @NotNull ScrollInputContext withValue(float value) {
        return new ScrollInputContext(active, value);
    }

}
