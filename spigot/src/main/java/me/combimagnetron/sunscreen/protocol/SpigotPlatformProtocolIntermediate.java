package me.combimagnetron.sunscreen.protocol;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.protocol.attribute.Attributes;
import com.github.retrooper.packetevents.protocol.component.ComponentTypes;
import com.github.retrooper.packetevents.protocol.component.builtin.item.ItemEquippable;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.item.type.ItemTypes;
import com.github.retrooper.packetevents.protocol.player.*;
import com.github.retrooper.packetevents.protocol.sound.Sounds;
import com.github.retrooper.packetevents.resources.ResourceLocation;
import com.github.retrooper.packetevents.wrapper.play.server.*;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import me.combimagnetron.passport.internal.entity.Entity;
import me.combimagnetron.passport.internal.entity.impl.display.ItemDisplay;
import me.combimagnetron.passport.internal.entity.impl.passive.horse.Horse;
import me.combimagnetron.passport.internal.entity.impl.tile.ItemFrame;
import me.combimagnetron.passport.internal.entity.metadata.type.Vector3d;
import me.combimagnetron.sunscreen.neo.protocol.PlatformProtocolIntermediate;
import me.combimagnetron.sunscreen.neo.protocol.type.EntityReference;
import me.combimagnetron.sunscreen.neo.protocol.type.Location;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.sunscreen.util.Scheduler;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SpigotPlatformProtocolIntermediate implements PlatformProtocolIntermediate {
    private static final WrapperPlayServerUpdateAttributes.PropertyModifier MODIFIER = new WrapperPlayServerUpdateAttributes.PropertyModifier(UUID.randomUUID(), 0, WrapperPlayServerUpdateAttributes.PropertyModifier.Operation.MULTIPLY_BASE);
    private final Table<UUID, Integer, Entity> entities = HashBasedTable.create();
    private EquipmentSlot equipmentSlot = EquipmentSlot.BODY;

    public SpigotPlatformProtocolIntermediate() {
        PacketEvents.getAPI().getEventManager().registerListener(new ProtocolListener(), PacketListenerPriority.LOW);
    }

    public @NotNull Map<Integer, Entity> entities(@NotNull UUID uuid) {
        return entities.row(uuid);
    }

    @Override
    public EntityReference<?> spawnAndRideHorse(@NotNull SunscreenUser<?> user, @NotNull Location location) {
        Horse horse = Horse.horse(loc2Vec3(location));
        horse.invisible(true);
        horse.crouching(true);
        if (user.clientVersion().getProtocolVersion() >= ClientVersion.V_1_21_5.getProtocolVersion()) {
            equipmentSlot = EquipmentSlot.SADDLE;
        } else {
            horse.saddled(true);
        }
        WrapperPlayServerEntityEquipment entityEquipment = horseEquipment("cursor_default", horse.id().intValue(), equipmentSlot);
        WrapperPlayServerSetPassengers passengers = new WrapperPlayServerSetPassengers(horse.id().intValue(), new int[]{user.entityId()});
        List<WrapperPlayServerUpdateAttributes.Property> attributes = List.of(
                new WrapperPlayServerUpdateAttributes.Property(
                        Attributes.JUMP_STRENGTH,
                        0.0D,
                        List.of(MODIFIER)
                ),
                new WrapperPlayServerUpdateAttributes.Property(
                        Attributes.SCALE,
                        0.01,
                        List.of(MODIFIER)
                )
        );
        WrapperPlayServerEntityTeleport entityTeleport = new WrapperPlayServerEntityTeleport(horse.id().intValue(), new com.github.retrooper.packetevents.util.Vector3d(horse.position().x(), horse.position().y(), horse.position().z()), 0f, 0f, false);
        WrapperPlayServerUpdateAttributes updateAttributes = new WrapperPlayServerUpdateAttributes(horse.id().intValue(), attributes);
        user.show(horse);
        user.connection().send(updateAttributes);
        user.connection().send(entityEquipment);
        user.connection().send(entityTeleport);
        user.connection().send(passengers);
        entities.put(user.uniqueIdentifier(), horse.id().intValue(), horse);
        return new EntityReference<>(horse.id().intValue(), horse);
    }

    @Override
    public EntityReference<?> spawnAndFillItemFrame(@NotNull SunscreenUser<?> user, @NotNull Location location, byte[] data, int mapId) {
        WrapperPlayServerMapData mapData = new WrapperPlayServerMapData(mapId, (byte) 0, false, false, null, 128, 128, 0, 0, data);
        ItemStack itemStack = ItemStack.builder().type(ItemTypes.FILLED_MAP).component(ComponentTypes.MAP_ID, mapId).build();
        ItemFrame upper = ItemFrame.frame(loc2Vec3(location), itemStack);
        ItemFrame lower = ItemFrame.frame(loc2Vec3(location), itemStack);
        upper.invisible(true);
        upper.direction(ItemFrame.Direction.UP);
        lower.invisible(true);
        lower.direction(ItemFrame.Direction.DOWN);
        user.connection().send(mapData);
        user.show(upper);
        user.show(lower);
        entities.put(user.uniqueIdentifier(), upper.id().intValue(), upper);
        entities.put(user.uniqueIdentifier(), lower.id().intValue(), lower);
        return new EntityReference<>(upper.id().intValue(), upper);
    }

    @Override
    public EntityReference<?> spawnAndSpectateDisplay(@NotNull SunscreenUser<?> user, @NotNull Location location) {
        ItemDisplay display = ItemDisplay.itemDisplay(loc2Vec3(location));
        Player player = (Player) user.platformSpecificPlayer();
        display.rotation(Vector3d.vec3(player.getPitch(), player.getYaw(), 0));
        WrapperPlayServerCamera camera = new WrapperPlayServerCamera(display.id().intValue());
        user.show(display);
        user.connection().send(camera);
        entities.put(user.uniqueIdentifier(), display.id().intValue(), display);
        user.connection().send(new WrapperPlayServerChangeGameState(WrapperPlayServerChangeGameState.Reason.CHANGE_GAME_MODE, 3));
        //user.connection().send(new WrapperPlayServerPlayerInfoUpdate(WrapperPlayServerPlayerInfoUpdate.Action.UPDATE_GAME_MODE, new WrapperPlayServerPlayerInfoUpdate.PlayerInfo(new UserProfile(user.uniqueIdentifier(), user.name()), true, 0, GameMode.SPECTATOR, MenuComponent.empty(), null)));
        return new EntityReference<>(display.id().intValue(), display);
    }

    @Override
    public void setHorseArmor(@NotNull SunscreenUser<?> user, int horseId, @NotNull String texturePath) {
        WrapperPlayServerEntityEquipment entityEquipment = horseEquipment(texturePath, horseId, equipmentSlot);
        user.connection().send(entityEquipment);
    }

    @Override
    public void removeEntity(@NotNull SunscreenUser<?> user, int id) {
        entities.remove(user.uniqueIdentifier(), id);
        WrapperPlayServerDestroyEntities destroyEntities = new WrapperPlayServerDestroyEntities(id);
        user.connection().send(destroyEntities);
    }

    @Override
    public void updateMap(@NotNull SunscreenUser<?> user, int mapId, byte @NotNull [] data) {
        WrapperPlayServerMapData mapData = new WrapperPlayServerMapData(mapId, (byte) 0, false, false, null, 128, 128, 0, 0, data);
        user.connection().send(mapData);
    }

    @Override
    public void reset(@NotNull SunscreenUser<?> user) {
        Player player = (Player) user.platformSpecificPlayer();
        WrapperPlayServerCamera camera = new WrapperPlayServerCamera(user.entityId());
        WrapperPlayServerChangeGameState gameState = new WrapperPlayServerChangeGameState(WrapperPlayServerChangeGameState.Reason.CHANGE_GAME_MODE, user.gameMode());
        WrapperPlayServerTimeUpdate timeUpdate = new WrapperPlayServerTimeUpdate(player.getWorld().getGameTime(), player.getPlayerTime());
        int[] ids = entities.columnKeySet().stream().mapToInt(Integer::intValue).toArray();
        user.connection().send(new WrapperPlayServerDestroyEntities(ids));
        entities.row(user.uniqueIdentifier()).clear();
        user.connection().send(timeUpdate);
        user.connection().send(gameState);
        user.connection().send(camera);
    }

    @Override
    public void gameTime(@NotNull SunscreenUser<?> user) {
        WrapperPlayServerTimeUpdate time = new WrapperPlayServerTimeUpdate(-2000, (long) user.worldTime(), false);
        user.connection().send(time);
    }

    private static @NotNull Vector3d loc2Vec3(@NotNull Location location) {
        return Vector3d.vec3(location.x(), location.y(), location.z());
    }

    private static @NotNull WrapperPlayServerEntityEquipment horseEquipment(@NotNull String texturePath, int id, EquipmentSlot equipmentSlot) {
        return new WrapperPlayServerEntityEquipment(
                id,
                List.of(
                        new Equipment(equipmentSlot,
                                ItemStack.builder().type(ItemTypes.SADDLE).build()),
                        new Equipment(EquipmentSlot.BODY,
                                ItemStack.builder().type(ItemTypes.COPPER_HORSE_ARMOR).
                                        component(ComponentTypes.EQUIPPABLE,
                                                new ItemEquippable(EquipmentSlot.BODY, Sounds.ITEM_ARMOR_EQUIP_GENERIC, new ResourceLocation("sunscreen", texturePath), null, null, false, false, false)).build())));

    }

}
