package me.combimagnetron.sunscreen;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.ActiveMenu;
import me.combimagnetron.sunscreen.neo.TestMenuTemplate;
import me.combimagnetron.sunscreen.neo.graphic.text.style.impl.font.AtlasFont;
import me.combimagnetron.sunscreen.neo.registry.Registries;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.sunscreen.util.FileProvider;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerSwapItemEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;

public class MinestomTest {
    private static final Identifier FONT_ID = Identifier.of("sunscreen", "font/minecraft");
    private static final Identifier SMALL_FONT_ID = Identifier.of("sunscreen", "font/sunburned");

    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init(new Auth.Online());

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        instanceContainer.setChunkSupplier(LightingChunk::new);
        instanceContainer.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK));

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 42, 0));
        });
        new SunscreenLibraryMinestom();
        new PacketListener();
        AtlasFont atlasFont = AtlasFont.font(FONT_ID).fromTtfFile(FileProvider.resource().find("minecraft_font.ttf").toPath(), 8);
        AtlasFont sunburned = AtlasFont.font(SMALL_FONT_ID).fromTtfFile(FileProvider.resource().find("sunburned.ttf").toPath(), 15.8f);
        Registries.register(Registries.FONTS, atlasFont);
        Registries.register(Registries.FONTS, sunburned);
        MinecraftServer.getGlobalEventHandler().addListener(PlayerSwapItemEvent.class, event -> {
            final Player player = event.getPlayer();
            SunscreenUser<?> user = SunscreenLibrary.library().users().user(player);
            new ActiveMenu(new TestMenuTemplate(), user, Identifier.of("hi"));
        });

        minecraftServer.start("0.0.0.0", 25565);
        System.out.println("hi");

    }

}
