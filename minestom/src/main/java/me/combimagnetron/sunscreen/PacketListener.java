package me.combimagnetron.sunscreen;

import me.combimagnetron.sunscreen.neo.input.InputHandler;
import me.combimagnetron.sunscreen.neo.input.context.MouseInputContext;
import me.combimagnetron.sunscreen.neo.session.Session;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.sunscreen.util.helper.RotationHelper;
import net.kyori.adventure.audience.Audience;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerPacketEvent;
import net.minestom.server.event.player.PlayerPacketOutEvent;
import net.minestom.server.network.packet.client.play.ClientInputPacket;
import net.minestom.server.network.packet.client.play.ClientPlayerActionPacket;
import net.minestom.server.network.packet.client.play.ClientPlayerRotationPacket;
import net.minestom.server.network.packet.server.play.TimeUpdatePacket;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@SuppressWarnings("UnstableApiUsage")
public class PacketListener {

    public PacketListener() {
        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        eventHandler.addListener(PlayerPacketOutEvent.class, event -> {
            Optional<SunscreenUser<Audience>> userOptional = SunscreenLibrary.library().users().user(event.getPlayer().getUuid());
            if (userOptional.isEmpty()) {
                return;
            }
            SunscreenUser<?> user = userOptional.get();
            if (!inMenu(user)) return;
            switch (event.getPacket()) {
                case TimeUpdatePacket _ -> handleTimeUpdate(event, user);
                default -> {}
            }
        });
        eventHandler.addListener(PlayerPacketEvent.class, event -> {
            Optional<SunscreenUser<Audience>> userOptional = SunscreenLibrary.library().users().user(event.getPlayer().getUuid());
            if (userOptional.isEmpty()) {
                return;
            }
            SunscreenUser<?> user = userOptional.get();
            if (!inMenu(user)) return;
            switch (event.getPacket()) {
                case ClientPlayerActionPacket _ -> handleDigging(event, user);
                case ClientInputPacket inputPacket -> handleSneak(inputPacket, user);
                case ClientPlayerRotationPacket playerRotationPacket -> handleRotation(playerRotationPacket, user);
                default -> {}
            }
        });
    }

    private void handleDigging(PlayerPacketEvent playerPacketEvent, SunscreenUser<?> user) {
        ClientPlayerActionPacket playerActionPacket = (ClientPlayerActionPacket) playerPacketEvent.getPacket();
        final Session session = user.session();
        if (session == null) return;
        final InputHandler inputHandler = session.menu().inputHandler();
        ClientPlayerActionPacket.Status status = playerActionPacket.status();
        boolean click;
        if (status == ClientPlayerActionPacket.Status.STARTED_DIGGING) {
            click = true;
            playerPacketEvent.setCancelled(true);
        } else if (status == ClientPlayerActionPacket.Status.CANCELLED_DIGGING) {
            click = false;
            playerPacketEvent.setCancelled(true);
        } else {
            return;
        }
        inputHandler.peek(MouseInputContext.class, old -> old.withLeftPressed(click), user);
    }

    private void handleTimeUpdate(PlayerPacketOutEvent event, SunscreenUser<?> user) {
        Player player = (Player) user.platformSpecificPlayer();
        TimeUpdatePacket timeUpdatePacket = (TimeUpdatePacket) event.getPacket();
        if (timeUpdatePacket.worldAge() < 0) return;
        event.setCancelled(true);
        player.getPlayerConnection().sendPacket(new TimeUpdatePacket(-2000, timeUpdatePacket.timeOfDay(), timeUpdatePacket.tickDayTime()));
    }

    public void handleSneak(ClientInputPacket input, SunscreenUser<?> user) {
        if (input.shift()) user.session().menu().close();
    }

    private void handleRotation(ClientPlayerRotationPacket playerRotation, SunscreenUser<?> user) {
        final Session session = user.session();
        if (session == null) return;
        final InputHandler inputHandler = session.menu().inputHandler();
        float yaw = playerRotation.yaw();
        float pitch = playerRotation.pitch();
        inputHandler.peek(MouseInputContext.class, old -> old.withPosition(RotationHelper.convert(yaw, pitch, user.screenInfo())), user);
    }

    static boolean inMenu(@NotNull SunscreenUser<?> user) {
        return SunscreenLibrary.library().sessionHandler().inMenu(user);
    }

}
