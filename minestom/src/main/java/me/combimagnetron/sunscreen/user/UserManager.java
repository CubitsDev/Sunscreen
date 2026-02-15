package me.combimagnetron.sunscreen.user;

import me.combimagnetron.passport.internal.network.ByteBuffer;
import me.combimagnetron.passport.user.UserHandler;
import me.combimagnetron.sunscreen.neo.session.Session;
import net.kyori.adventure.text.Component;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.event.trait.PlayerEvent;

import java.util.*;

public class UserManager implements UserHandler<Player, SunscreenUser<Player>> {
    private final Map<UUID, SunscreenUser<Player>> userMap = new WeakHashMap<>();
    private final EventNode<PlayerEvent> eventNode = EventNode.type("player-spawn-sunscreen", EventFilter.PLAYER);

    public UserManager() {
        MinecraftServer.getGlobalEventHandler().addChild(eventNode);
        eventNode.addListener(PlayerSpawnEvent.class, event -> {
            final Player player = event.getPlayer();
            userMap.put(player.getUuid(), UserImpl.of(player));
        });
        eventNode.addListener(PlayerDisconnectEvent.class, event -> {
            final Player player = event.getPlayer();
            SunscreenUser<Player> user = userMap.get(player.getUuid());
            Session session = user.session();
            if (session != null) {
                session.menu().close();
            }
            userMap.remove(player);
        });
    }

    @Override
    public SunscreenUser<Player> user(Player player) {
        return userMap.get(player.getUuid());
    }

    @Override
    public Optional<SunscreenUser<Player>> user(UUID uuid) {
        SunscreenUser<Player> playerSunscreenUser = userMap.get(uuid);
        if (playerSunscreenUser == null) return Optional.empty();
        return Optional.of(playerSunscreenUser);
    }

    @Override
    public Optional<SunscreenUser<Player>> user(String s) {
        return Optional.ofNullable(userMap.get(MinecraftServer.getConnectionManager().findOnlinePlayer(s).getUuid()));
    }

    @Override
    public Collection<SunscreenUser<Player>> users() {
        return userMap.values();
    }

    @Override
    public Collection<SunscreenUser<Player>> global() {
        return users();
    }

    @Override
    public SunscreenUser<Player> deserialize(ByteBuffer byteBuffer) {
        return null;
    }
}
