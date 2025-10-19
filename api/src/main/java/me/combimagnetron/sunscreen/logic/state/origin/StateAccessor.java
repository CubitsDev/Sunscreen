package me.combimagnetron.sunscreen.logic.state.origin;

import me.combimagnetron.sunscreen.logic.state.State;
import org.jetbrains.annotations.Nullable;

public interface StateAccessor {

    @Nullable <T> Ticket access(State<T> state);

    record Ticket(Origin origin, long time) {

    }

}
