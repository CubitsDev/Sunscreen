package me.combimagnetron.sunscreen;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import me.combimagnetron.passport.internal.entity.metadata.type.Vector3d;
import me.combimagnetron.sunscreen.neo.protocol.PlatformProtocolIntermediate;
import me.combimagnetron.sunscreen.neo.protocol.type.EntityReference;
import me.combimagnetron.sunscreen.neo.protocol.type.Location;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import net.minestom.server.component.DataComponents;
import net.minestom.server.entity.*;
import net.minestom.server.instance.Instance;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.component.Equippable;
import net.minestom.server.network.packet.server.play.MapDataPacket;
import net.minestom.server.network.packet.server.play.SpawnEntityPacket;
import net.minestom.server.network.packet.server.play.TimeUpdatePacket;
import net.minestom.server.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class MinestomPlatformProtocolIntermediate implements PlatformProtocolIntermediate {
    private final Table<UUID, Integer, Object> entities = HashBasedTable.create();

    @Override
    public EntityReference<?> spawnAndRideHorse(@NotNull SunscreenUser<?> user, @NotNull Location location) {
        Player player = player(user);
        LivingEntity entity = new LivingEntity(EntityType.HORSE);
        entity.setInvisible(true);
        entity.setBodyEquipment(ItemStack.of(Material.COPPER_HORSE_ARMOR).with(DataComponents.EQUIPPABLE, new Equippable(EquipmentSlot.BODY, SoundEvent.ITEM_GOAT_HORN_SOUND_2, "", null, null, false, false, false, false, false, SoundEvent.ITEM_GOAT_HORN_SOUND_0)));
        return null;
    }

    @Override
    public EntityReference<?> spawnAndFillItemFrame(@NotNull SunscreenUser<?> user, @NotNull Location location, byte[] data, int mapId) {
        SpawnEntityPacket spawnEntityPacket;
        return null;
    }

    @Override
    public EntityReference<?> spawnAndSpectateDisplay(@NotNull SunscreenUser<?> user, @NotNull Location location) {
        return null;
    }

    @Override
    public void setHorseArmor(@NotNull SunscreenUser<?> user, int horseId, @NotNull String texturePath) {

    }

    @Override
    public void removeEntity(@NotNull SunscreenUser<?> user, int id) {

    }

    @Override
    public void updateMap(@NotNull SunscreenUser<?> user, int mapId, byte @NotNull [] data) {
        final Player player = (Player) user.platformSpecificPlayer();
        player.getPlayerConnection().sendPacket(new MapDataPacket(mapId, (byte) 0, false, false, List.of(), new MapDataPacket.ColorContent((byte) 128, (byte) 128, (byte) 0, (byte) 0, data)));
    }

    @Override
    public void reset(@NotNull SunscreenUser<?> user, @NotNull Vector3d initialRotation) {

    }

    @Override
    public void gameTime(@NotNull SunscreenUser<?> user) {
        final Player player = (Player) user.platformSpecificPlayer();
        final Instance instance = player.getInstance();
        player.getPlayerConnection().sendPacket(new TimeUpdatePacket(-2000, instance.getTime(), false));
    }

    private Player player(SunscreenUser<?> user) {
        return (Player) user.platformSpecificPlayer();
    }

}
