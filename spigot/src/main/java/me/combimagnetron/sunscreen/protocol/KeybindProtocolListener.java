package me.combimagnetron.sunscreen.protocol;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerInput;
import me.combimagnetron.sunscreen.SunscreenLibrary;
import me.combimagnetron.sunscreen.neo.input.keybind.Keybind;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import net.kyori.adventure.audience.Audience;

import java.util.Optional;

import static me.combimagnetron.sunscreen.protocol.ProtocolListener.inMenu;

public class KeybindProtocolListener implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        Optional<SunscreenUser<Audience>> userOptional = SunscreenLibrary.library().users().user(event.getUser().getUUID());
        if (userOptional.isEmpty()) {
            return;
        }
        SunscreenUser<?> user = userOptional.get();
        if (!inMenu(user)) return;
        switch (event.getPacketType()) {
            case PacketType.Play.Client.PLAYER_INPUT -> handleInput(new WrapperPlayClientPlayerInput(event), user);
            default -> throw new IllegalStateException("Unexpected value: " + event.getPacketType());
        }
    }

    private void handleInput(WrapperPlayClientPlayerInput input, SunscreenUser<?> user) {
        Keybind.Registered key = null;
        Keybind.Modifier modifier = null;
        if (input.isForward()) {

        }
        if (input.isBackward()) {

        }
        if (input.isLeft()) {

        }
        if (input.isRight()) {

        }
        if (input.isSprint()) {

        }
        if (input.isSprint()) {

        }
    }

}
