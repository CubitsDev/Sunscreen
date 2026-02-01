package me.combimagnetron.sunscreen;

import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerMapData;
import me.combimagnetron.passport.util.math.Vec2f;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.protocol.PlatformProtocolIntermediate;
import me.combimagnetron.sunscreen.neo.protocol.type.EntityReference;
import me.combimagnetron.sunscreen.neo.protocol.type.Location;
import me.combimagnetron.sunscreen.neo.render.engine.encode.MapEncoder;
import me.combimagnetron.sunscreen.neo.render.engine.grid.ProcessedRenderChunk;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerSwapItemEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;
import net.minestom.server.timer.TaskSchedule;

import java.util.stream.IntStream;

public class MinestomTest {

    static void main() {
        MinecraftServer minecraftServer = MinecraftServer.init(new Auth.Online());
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();
        instanceContainer.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK));
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 42, 0));
        });

        new SunscreenLibraryMinestom();

        minecraftServer.start("0.0.0.0", 25565);

        MinecraftServer.getGlobalEventHandler().addListener(PlayerSwapItemEvent.class, event -> {
            final Player player = event.getPlayer();
            Canvas canvas = Canvas.empty(Vec2i.of(896, 896)).place(Canvas.url("https://i.imgur.com/K87ZEG7.png"), Vec2i.zero());
            PlatformProtocolIntermediate intermediate = SunscreenLibrary.library().intermediate();
            SunscreenUser<?> user = userManager().user(player);
            Location location = new Location(player.getPosition().x(), player.getEyeHeight(), player.getPosition().z());
            intermediate.spawnAndSpectateDisplay(user, location);
            EntityReference<?> horse = intermediate.spawnAndRideHorse(user, location);
            final Vec2i size = Vec2i.of(128, 128);
            final boolean[] first = new boolean[]{true};
            MinecraftServer.getSchedulerManager().scheduleTask(() -> {
                IntStream.range(0, 49).parallel().forEach(idx -> {
                    int i = idx % 7;
                    int j = idx / 7;
                    int oopsj = (6 - j);
                    Vec2i subPos = Vec2i.of(i * 128, oopsj * 128);
                    Canvas temp = canvas.sub(subPos, size);
                    MapEncoder mapEncoder = new MapEncoder(new ProcessedRenderChunk(
                            temp.bufferedColorSpace(),
                            Vec2f.of((float) (-1 * (i - 2.625)), (float) (j - (4.74))),
                            1.56f
                    ));
                    byte[] data = mapEncoder.bytes().toByteArray();
                    int mapId = 99 + i + 10 * j;
                    if (first[0]) {
                        intermediate.spawnAndFillItemFrame(user, new Location(location.x(), location.y(), location.z()), data, mapId);
                    }
                });
                first[0] = false;
            }, TaskSchedule.tick(0), TaskSchedule.tick(1));
        });
    }

}
