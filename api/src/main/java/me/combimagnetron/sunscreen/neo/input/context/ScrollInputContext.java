package me.combimagnetron.sunscreen.neo.input.context;

import me.combimagnetron.sunscreen.neo.event.UserScrollStateChangeEvent;
import org.jetbrains.annotations.NotNull;

public record ScrollInputContext(boolean active, float value) implements InputContext<UserScrollStateChangeEvent> {

    @Override
    public @NotNull Class<UserScrollStateChangeEvent> eventType() {
        return UserScrollStateChangeEvent.class;
    }

    public @NotNull ScrollInputContext withActive(boolean active) {
        return new ScrollInputContext(active, value);
    }

    public @NotNull ScrollInputContext withValue(float value) {
        return new ScrollInputContext(active, value);
    }

}
