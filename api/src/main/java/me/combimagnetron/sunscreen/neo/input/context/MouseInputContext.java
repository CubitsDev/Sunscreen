package me.combimagnetron.sunscreen.neo.input.context;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.event.UserMoveStateChangeEvent;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;

public record MouseInputContext(boolean active, boolean rightPressed, boolean leftPressed, @NotNull Vec2i position) implements InputContext<UserMoveStateChangeEvent> {

    public boolean both() {
        return active && rightPressed && leftPressed;
    }

    @Override
    public @NotNull Class<UserMoveStateChangeEvent> eventType() {
        return UserMoveStateChangeEvent.class;
    }

    @Override
    public @NotNull UserMoveStateChangeEvent constructEvent(@NotNull SunscreenUser<?> user) {
        return new UserMoveStateChangeEvent(user, this);
    }

    public @NotNull MouseInputContext withActive(boolean newActive) {
        return new MouseInputContext(newActive, rightPressed, leftPressed, position);
    }

    public @NotNull MouseInputContext withRightPressed(boolean newRightPressed) {
        return new MouseInputContext(active, newRightPressed, leftPressed, position);
    }

    public @NotNull MouseInputContext withLeftPressed(boolean newLeftPressed) {
        return new MouseInputContext(active, rightPressed, newLeftPressed, position);
    }

    public @NotNull MouseInputContext withPosition(@NotNull Vec2i newPosition) {
        return new MouseInputContext(active, rightPressed, leftPressed, newPosition);
    }

}
