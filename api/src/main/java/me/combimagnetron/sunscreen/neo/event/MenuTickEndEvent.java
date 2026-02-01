package me.combimagnetron.sunscreen.neo.event;

import me.combimagnetron.passport.event.Event;
import me.combimagnetron.sunscreen.neo.ActiveMenu;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record MenuTickEndEvent(@NotNull UUID uuid, long tick) implements Event {

    public boolean is20TpsTick() {
        return tick % 3 == 0;
    }

    //TODO: get menu by the uuid
    public @NotNull ActiveMenu menu() {
        return null;
    }

    @Override
    public Class<? extends Event> eventType() {
        return MenuTickEndEvent.class;
    }

}
