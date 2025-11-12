package me.combimagnetron.sunscreen.protocol;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerRotation;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerTimeUpdate;
import me.combimagnetron.passport.event.Dispatcher;
import me.combimagnetron.sunscreen.SunscreenLibrary;
import me.combimagnetron.sunscreen.event.UserMoveCursorEvent;
import me.combimagnetron.sunscreen.user.SunscreenUser;
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
            default -> {}
        }
    }

    private void handleTimeUpdate(WrapperPlayServerTimeUpdate wrapperPlayServerTimeUpdate, SunscreenUser<?> user) {
        wrapperPlayServerTimeUpdate.setWorldAge(-2000);
    }

    private void handleInteractEntity(PacketReceiveEvent packetReceiveEvent, SunscreenUser<?> user) {
        WrapperPlayClientInteractEntity wrapperPlayClientInteractEntity = new WrapperPlayClientInteractEntity(packetReceiveEvent);
        if (wrapperPlayClientInteractEntity.getEntityId() != user.entityId()) return;
        packetReceiveEvent.setCancelled(true);
    }

    private void handleRotation(WrapperPlayClientPlayerRotation wrapperPlayClientPlayerRotation, SunscreenUser<?> user) {
        Dispatcher.dispatcher().post(new UserMoveCursorEvent(user, RotationHelper.convert(wrapperPlayClientPlayerRotation.getYaw(), wrapperPlayClientPlayerRotation.getPitch(), user.screenSize())));
    }

    private static boolean inMenu(@NotNull SunscreenUser<?> user) {
        return true;
        //return user.session() != null; TODO: Turn into real check
    }

}
