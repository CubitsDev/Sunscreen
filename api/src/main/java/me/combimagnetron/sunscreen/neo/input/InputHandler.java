package me.combimagnetron.sunscreen.neo.input;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import me.combimagnetron.passport.event.Dispatcher;
import me.combimagnetron.passport.event.Event;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.input.context.InputContext;
import me.combimagnetron.sunscreen.neo.input.context.MouseInputContext;
import me.combimagnetron.sunscreen.neo.input.context.ScrollInputContext;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class InputHandler {
    private final Map<Class<? extends InputContext<?>>, InputContext<?>> inputContextMap = new HashMap<>();

    protected InputHandler() {
        inputContextMap.put(MouseInputContext.class, new MouseInputContext(false, false, false, Vec2i.zero()));
        inputContextMap.put(ScrollInputContext.class, new ScrollInputContext(false, 0f));
    }

    public static @NotNull InputHandler defaults() {
        return new InputHandler();
    }

    public <C extends InputContext<E>, E extends Event> @NotNull C peek(@NotNull Class<C> type, @NotNull Function<C, C> function, @NotNull SunscreenUser<?> user) {
        C currentInput = (C) inputContextMap.get(type);
        C mutatedInput = function.apply(currentInput);
        inputContextMap.put(type, mutatedInput);
        Event event = currentInput.constructEvent(user);
        Dispatcher.dispatcher().post(currentInput.eventType(), event);
        return mutatedInput;
    }

    public <C extends InputContext<?>> @NotNull C context(@NotNull Class<C> type) {
        return (C) inputContextMap.get(type);
    }

}
