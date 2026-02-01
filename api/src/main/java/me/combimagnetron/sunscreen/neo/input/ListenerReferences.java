package me.combimagnetron.sunscreen.neo.input;

import me.combimagnetron.passport.event.EventBus;
import me.combimagnetron.passport.event.EventFilter;
import me.combimagnetron.sunscreen.neo.event.UserMoveStateChangeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface ListenerReferences<T, S extends ListenerReferences<T, S>> {

    T back();

    default S mouse(@NotNull Consumer<UserMoveStateChangeEvent> eventConsumer) {
        EventBus.subscribe(UserMoveStateChangeEvent.class, EventFilter.filter(null, null, null), eventConsumer);
        return (S) this;
    }

}
