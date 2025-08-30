package me.combimagnetron.sunscreen.session;

import me.combimagnetron.sunscreen.user.SunscreenUser;

import java.util.*;

public class SessionHandler {
    private final Map<UUID, Session> sessions = new HashMap<>();

    public Session session(SunscreenUser<?> user) {
        return sessions.get(user.uniqueIdentifier());
    }

    public Session session(Session session) {
        sessions.put(session.user().uniqueIdentifier(), session);
        return session;
    }

}
