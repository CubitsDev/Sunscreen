package me.combimagnetron.sunscreen.protocol;

import com.destroystokyo.paper.profile.ProfileProperty;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.protocol.attribute.Attributes;
import com.github.retrooper.packetevents.protocol.component.ComponentTypes;
import com.github.retrooper.packetevents.protocol.component.builtin.item.ItemEquippable;
import com.github.retrooper.packetevents.protocol.entity.type.EntityType;
import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.item.type.ItemTypes;
import com.github.retrooper.packetevents.protocol.npc.NPC;
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
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerTextures;
import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;

import java.nio.channels.Channel;
import java.util.*;

public class SpigotPlatformProtocolIntermediate implements PlatformProtocolIntermediate {
    private static final WrapperPlayServerUpdateAttributes.PropertyModifier MODIFIER = new WrapperPlayServerUpdateAttributes.PropertyModifier(UUID.randomUUID(), 0, WrapperPlayServerUpdateAttributes.PropertyModifier.Operation.MULTIPLY_BASE);
    private final Table<UUID, Integer, Object> entities = HashBasedTable.create();
    private EquipmentSlot equipmentSlot = EquipmentSlot.BODY;

    public SpigotPlatformProtocolIntermediate() {
        PacketEvents.getAPI().getEventManager().registerListener(new ProtocolListener(), PacketListenerPriority.LOW);
    }

    public @NotNull Map<Integer, Object> entities(@NotNull UUID uuid) {
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
        WrapperPlayServerEntityTeleport entityTeleport = new WrapperPlayServerEntityTeleport(horse.id().intValue(), new com.github.retrooper.packetevents.util.Vector3d(horse.position().x(), horse.position().y(), horse.position().z()), 0f, 180f, false);
        WrapperPlayServerUpdateAttributes updateAttributes = new WrapperPlayServerUpdateAttributes(horse.id().intValue(), attributes);
        user.connection().send(new WrapperPlayServerPlayerRotation(0f, -180f));
        user.show(horse);
        user.connection().send(updateAttributes);
        user.connection().send(entityEquipment);
        user.connection().send(entityTeleport);
        user.connection().send(passengers);
        user.connection().send(new WrapperPlayServerPlayerRotation(0f, -37.3f));
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
        WrapperPlayServerCamera camera = new WrapperPlayServerCamera(-10_000);
        UUID uuid = UUID.randomUUID();
        List<TextureProperty> properties = new ArrayList<>();
        for (ProfileProperty property : player.getPlayerProfile().getProperties()) {
            properties.add(new TextureProperty(property.getName(), property.getValue(), property.getSignature()));
        }
        UserProfile profile = new UserProfile(uuid, player.getName(), properties);
        WrapperPlayServerSpawnEntity spawnEntity = new WrapperPlayServerSpawnEntity(-10_000, uuid, EntityTypes.PLAYER, vec32PeLoc(user.position(), user.rotation()), player.getYaw(), 0, com.github.retrooper.packetevents.util.Vector3d.zero());
        WrapperPlayServerPlayerInfoUpdate infoUpdate = new WrapperPlayServerPlayerInfoUpdate(WrapperPlayServerPlayerInfoUpdate.Action.ADD_PLAYER, new WrapperPlayServerPlayerInfoUpdate.PlayerInfo(profile, false, 0, GameMode.CREATIVE, null, null, 0, true));
        user.connection().send(infoUpdate);
        user.connection().send(spawnEntity);
        user.connection().send(camera);
        entities.put(user.uniqueIdentifier(), display.id().intValue(), display);
        user.connection().send(new WrapperPlayServerChangeGameState(WrapperPlayServerChangeGameState.Reason.CHANGE_GAME_MODE, 3));
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
    public void reset(@NotNull SunscreenUser<?> user, @NotNull Vector3d initialRotation) {
        Player player = (Player) user.platformSpecificPlayer();
        WrapperPlayServerCamera camera = new WrapperPlayServerCamera(user.entityId());
        WrapperPlayServerChangeGameState gameState = new WrapperPlayServerChangeGameState(WrapperPlayServerChangeGameState.Reason.CHANGE_GAME_MODE, user.gameMode());
        WrapperPlayServerTimeUpdate timeUpdate = new WrapperPlayServerTimeUpdate(player.getWorld().getGameTime(), player.getPlayerTime());
        final Map<Integer, Object> trackedEntities = entities.row(user.uniqueIdentifier());
        final int[] ids = trackedEntities.keySet().stream().mapToInt(Integer::intValue).toArray();
        if (ids.length > 0) {
            user.connection().send(new WrapperPlayServerDestroyEntities(ids));
        }
        user.connection().send(new WrapperPlayServerDestroyEntities(-10_000));
        trackedEntities.clear();
        user.connection().send(new WrapperPlayServerPlayerPositionAndLook(user.position().x(), user.position().y(), user.position().z(), (float) initialRotation.x(), (float) initialRotation.y(), (byte)0, 0, false));
        user.connection().send(timeUpdate);
        user.connection().send(gameState);
        user.connection().send(camera);
    }

    @Override
    public void gameTime(@NotNull SunscreenUser<?> user) {
        WrapperPlayServerTimeUpdate time = new WrapperPlayServerTimeUpdate(-2000, (long) user.worldTime(), false);
        user.connection().send(time);
    }

    private static @NotNull Object channel(@NotNull SunscreenUser<?> user) {
        Player player = (Player) user.platformSpecificPlayer();
        return PacketEvents.getAPI().getPlayerManager().getChannel(player);
    }

    private static @NotNull Vector3d loc2Vec3(@NotNull Location location) {
        return Vector3d.vec3(location.x(), location.y(), location.z());
    }

    private static @NotNull com.github.retrooper.packetevents.protocol.world.Location vec32PeLoc(@NotNull Vector3d position, @NotNull Vector3d rotation) {
        return new com.github.retrooper.packetevents.protocol.world.Location(new com.github.retrooper.packetevents.util.Vector3d(position.x(), position.y(), position.z()), (float) rotation.x(), (float) rotation.y());
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
