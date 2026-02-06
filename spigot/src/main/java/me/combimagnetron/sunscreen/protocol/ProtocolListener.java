package me.combimagnetron.sunscreen.protocol;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientAnimation;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerInput;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerRotation;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerMapData;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerTimeUpdate;
import me.combimagnetron.sunscreen.SunscreenLibrary;
import me.combimagnetron.sunscreen.neo.input.InputHandler;
import me.combimagnetron.sunscreen.neo.input.context.MouseInputContext;
import me.combimagnetron.sunscreen.neo.session.Session;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.sunscreen.util.Scheduler;
import me.combimagnetron.sunscreen.util.helper.RotationHelper;
import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ProtocolListener implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent packetReceiveEvent) {
        Optional<SunscreenUser<Audience>> userOptional = SunscreenLibrary.library().users().user(packetReceiveEvent.getUser().getUUID());
        if (userOptional.isEmpty()) {
            return;
        }
        SunscreenUser<?> user = userOptional.get();
        if (!inMenu(user)) return;
        switch (packetReceiveEvent.getPacketType()) {
            case PacketType.Play.Client.PLAYER_ROTATION -> handleRotation(new WrapperPlayClientPlayerRotation(packetReceiveEvent), user);
            case PacketType.Play.Client.INTERACT_ENTITY -> handleInteractEntity(packetReceiveEvent, user);
            case PacketType.Play.Client.PLAYER_INPUT -> handleSneak(new WrapperPlayClientPlayerInput(packetReceiveEvent), user);
            case PacketType.Play.Client.ANIMATION -> handleAnimation(new WrapperPlayClientAnimation(packetReceiveEvent), user);
            default -> {}
        }
    }

    @Override
    public void onPacketSend(PacketSendEvent packetSendEvent) {
        Optional<SunscreenUser<Audience>> userOptional = SunscreenLibrary.library().users().user(packetSendEvent.getUser().getUUID());
        if (userOptional.isEmpty()) {
            return;
        }
        SunscreenUser<?> user = userOptional.get();
        if (!inMenu(user)) return;
        switch (packetSendEvent.getPacketType()) {
            case PacketType.Play.Server.TIME_UPDATE -> handleTimeUpdate(new WrapperPlayServerTimeUpdate(packetSendEvent), user);
            case PacketType.Play.Server.MAP_DATA -> handleMapData(packetSendEvent, user);
            default -> {}
        }
    }

    private void handleMapData(PacketSendEvent packetSendEvent, SunscreenUser<?> user) {
        if (inMenu(user)) return;
        WrapperPlayServerMapData data = new WrapperPlayServerMapData(packetSendEvent);
        if (data.getMapId() > 0) return;
        packetSendEvent.setCancelled(true);
    }

    private void handleAnimation(WrapperPlayClientAnimation wrapperPlayClientAnimation, SunscreenUser<?> user) {
        if (!inMenu(user)) return;
        final Session session = user.session();
        if (session == null) return;
        final InputHandler inputHandler = session.menu().inputHandler();
        inputHandler.peek(MouseInputContext.class, old -> old.withLeftPressed(true), user);
        Scheduler.delayTick(() -> {
            inputHandler.peek(MouseInputContext.class, old -> old.withLeftPressed(false), user);
        });
    }

    private void handleTimeUpdate(WrapperPlayServerTimeUpdate wrapperPlayServerTimeUpdate, SunscreenUser<?> user) {
        if (!inMenu(user)) return;
        wrapperPlayServerTimeUpdate.setWorldAge(-2000);
    }

    public void handleSneak(WrapperPlayClientPlayerInput input, SunscreenUser<?> user) {
        if (!inMenu(user)) return;
        if (input.isShift()) user.session().menu().close();
    }

    private void handleInteractEntity(PacketReceiveEvent packetReceiveEvent, SunscreenUser<?> user) {
        WrapperPlayClientInteractEntity wrapperPlayClientInteractEntity = new WrapperPlayClientInteractEntity(packetReceiveEvent);
        if (wrapperPlayClientInteractEntity.getEntityId() != user.entityId()) return;
        packetReceiveEvent.setCancelled(true);
    }

    private void handleRotation(WrapperPlayClientPlayerRotation wrapperPlayClientPlayerRotation, SunscreenUser<?> user) {
        if (!inMenu(user)) return;
        final Session session = user.session();
        if (session == null) return;
        final InputHandler inputHandler = session.menu().inputHandler();
        float yaw = wrapperPlayClientPlayerRotation.getYaw();
        float pitch = wrapperPlayClientPlayerRotation.getPitch();
        inputHandler.peek(MouseInputContext.class, old -> old.withPosition(RotationHelper.convert(yaw, pitch, user.screenInfo())), user);
    }

    static boolean inMenu(@NotNull SunscreenUser<?> user) {
        return SunscreenLibrary.library().sessionHandler().inMenu(user);
    }

}
