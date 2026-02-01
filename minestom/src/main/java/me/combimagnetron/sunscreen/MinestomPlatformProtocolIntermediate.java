package me.combimagnetron.sunscreen;

import me.combimagnetron.sunscreen.neo.protocol.PlatformProtocolIntermediate;
import me.combimagnetron.sunscreen.neo.protocol.type.EntityReference;
import me.combimagnetron.sunscreen.neo.protocol.type.Location;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import net.minestom.server.component.DataComponents;
import net.minestom.server.entity.*;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.component.Equippable;
import net.minestom.server.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;

public class MinestomPlatformProtocolIntermediate implements PlatformProtocolIntermediate {

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

    }

    @Override
    public void remove(@NotNull EntityReference<?> reference) {

    }

    @Override
    public void updateMap(int mapId, byte @NotNull [] data) {

    }

    private Player player(SunscreenUser<?> user) {
        return (Player) user.platformSpecificPlayer();
    }

}
