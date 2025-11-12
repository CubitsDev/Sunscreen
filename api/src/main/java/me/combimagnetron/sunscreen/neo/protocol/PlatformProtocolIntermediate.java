package me.combimagnetron.sunscreen.neo.protocol;

import me.combimagnetron.sunscreen.neo.protocol.type.EntityReference;
import me.combimagnetron.sunscreen.neo.protocol.type.Location;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;

public interface PlatformProtocolIntermediate {

    EntityReference<?> spawnAndRideHorse(@NotNull SunscreenUser<?> user, @NotNull Location location);

    EntityReference<?> spawnAndFillItemFrame(@NotNull SunscreenUser<?> user, @NotNull Location location, byte[] data, int mapId);

    EntityReference<?> spawnAndSpectateDisplay(@NotNull SunscreenUser<?> user, @NotNull Location location);

    void setHorseArmor(@NotNull SunscreenUser<?> user, int horseId, @NotNull String texturePath);

    void removeEntity(@NotNull SunscreenUser<?> user, int id);

}
