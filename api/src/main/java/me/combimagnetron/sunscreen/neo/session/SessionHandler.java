package me.combimagnetron.sunscreen.neo.session;

import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class SessionHandler {
    private final Map<UUID, Session> sessions = new HashMap<>();

    public @Nullable Session session(@NotNull SunscreenUser<?> user) {
        return sessions.get(user.uniqueIdentifier());
    }

    public void remove(@NotNull SunscreenUser<?> user) {
        sessions.remove(user.uniqueIdentifier());
    }

    public void session(@NotNull Session session) {
        sessions.put(session.user().uniqueIdentifier(), session);
    }

    public boolean inMenu(@NotNull SunscreenUser<?> user) {
        return session(user) != null;
    }

}
