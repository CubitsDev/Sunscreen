package me.combimagnetron.sunscreen;

import co.aikar.commands.PaperCommandManager;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerMapData;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.combimagnetron.passport.Passport;
import me.combimagnetron.sunscreen.hook.SunscreenHook;
import me.combimagnetron.sunscreen.hook.betterhud.BetterHudSunscreenHook;
import me.combimagnetron.sunscreen.hook.mythichud.MythicHudSunscreenHook;
import me.combimagnetron.sunscreen.hook.tab.TABSunscreenHook;
import me.combimagnetron.sunscreen.placeholder.PapiPlaceholderProvider;
import me.combimagnetron.sunscreen.resourcepack.ResourcePack;
import me.combimagnetron.sunscreen.resourcepack.feature.shader.Shader;
import me.combimagnetron.sunscreen.resourcepack.feature.shader.ShaderFeature;
import me.combimagnetron.sunscreen.resourcepack.meta.PackMeta;
import me.combimagnetron.sunscreen.resourcepack.meta.PackVersion;
import me.combimagnetron.sunscreen.neo.graphic.BufferedColorSpace;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.graphic.modifier.GraphicModifier;
import me.combimagnetron.sunscreen.neo.render.engine.encode.MapEncoder;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.sunscreen.user.UserManager;
import me.combimagnetron.sunscreen.util.data.Identifier;
import me.combimagnetron.sunscreen.util.data.Range;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SunscreenPlugin extends JavaPlugin implements Listener {
    private SunscreenLibrary<SunscreenPlugin, Player> library;
    private UserManager userManager;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
        //PacketEvents.getAPI().getEventManager().registerListener(new MenuListener(), PacketListenerPriority.LOWEST);
        //PacketEvents.getAPI().getEventManager().registerListener(new AnvilListener(), PacketListenerPriority.LOWEST);

    }

    @EventHandler
    public void onSneak(PlayerSwapHandItemsEvent sneakEvent) throws IOException {
        BufferedImage image = ImageIO.read(new URL("https://i.imgur.com/4zDrYvx.png"));
        GraphicLike<?> graphicLike = new GraphicLike() {
            @Override
            public @NotNull GraphicLike<?> modifier(@NotNull GraphicModifier modifier) {
                return this;
            }

            @Override
            public @NotNull BufferedImage image() {
                return image;
            }

            @Override
            public @NotNull BufferedColorSpace bufferedColorSpace() {
                return null;
            }
        };
        MapEncoder mapEncoder = new MapEncoder(graphicLike);
        WrapperPlayServerMapData serverMapData = new WrapperPlayServerMapData(99, (byte) 0, false, false, null, 128, 128, 0, 0, mapEncoder.bytes().toByteArray());
        PacketEvents.getAPI().getPlayerManager().sendPacket(sneakEvent.getPlayer(), serverMapData);
        ItemStack itemStack = new ItemStack(Material.FILLED_MAP);
        itemStack.editMeta(MapMeta.class, mapMeta -> {
            mapMeta.setMapId(99);
        });
        File file = getDataPath().resolve("bytes.txt").toFile();
        mapEncoder.write(file);
        sneakEvent.getPlayer().getInventory().addItem(itemStack);
    }

    @Override
    public void onEnable() {
        PacketEvents.getAPI().init();
        this.library = new SunscreenLibrarySpigot(this);
        SunscreenLibrary.Holder.INSTANCE = library;
        Passport.Holder.INSTANCE = library.passport();
        this.getDataFolder().mkdirs();
        this.userManager = new UserManager(this);
        unzip();
        commands();
        //menus();
        platformSpecific();
        resourcePack();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    private void unzip() {
        if (getDataFolder().toPath().resolve("menus").toFile().exists()) {
            return;
        }
        File file;
        try {
            file = File.createTempFile("files", ".zip");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        InputStream inputStream = this.getClass().getResourceAsStream("/files.zip");
        if (inputStream == null) {
            throw new RuntimeException("Resource not found");
        }
        try(OutputStream outputStream = new FileOutputStream(file)) {
            IOUtils.copy(inputStream, outputStream);
            inputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ZipInputStream zipInputStream = null;
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    new File(getDataFolder(), entry.getName()).mkdirs();
                } else {
                    File file1 = new File(getDataFolder(), entry.getName());
                    file1.getParentFile().mkdirs();
                    try (OutputStream outputStream = new FileOutputStream(file1)) {
                        IOUtils.copy(zipInputStream, outputStream);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                IOUtils.close(zipInputStream);
            } catch (IOException ignored) {

            }
        }
    }

    private void resourcePack() {
        ResourcePack pack = ResourcePack.with(
                PackMeta.meta(
                        PackVersion.version(Range.of(46, 48)),
                        "Sunscreen Resource Pack",
                        "Sunscreen Resource Pack")
        );
        ShaderFeature shaderFeature = pack.feature(Shader.class);
    }

    private void platformSpecific() {
        //Action.ACTION_MAP.put(RunCommandAction.ActionIdentifier, new RunCommandAction());
        SunscreenHook.HOOKS.add(new MythicHudSunscreenHook());
        SunscreenHook.HOOKS.add(new BetterHudSunscreenHook());
        SunscreenHook.HOOKS.add(new TABSunscreenHook());
        library.passport().placeholders().register(new PapiPlaceholderProvider());
    }

    /*private void menus() {
        Collection<MenuTemplate> templates = library.menuConfigTransformer().read(getDataFolder().toPath().resolve(Path.of("menus")));
        library.logger().info("Loaded {} menu(s).", templates.size());
        for (MenuTemplate template : templates) {
            library.menuRegistry().register(template);
        }
    }*/

    private void commands() {
        PaperCommandManager manager = new PaperCommandManager(this);
        manager.getCommandContexts().registerContext(Identifier.class, (bukkitCommandExecutionContext -> Identifier.split(bukkitCommandExecutionContext.popFirstArg())));
        manager.getCommandContexts().registerContext(SunscreenUser.class, (bukkitCommandExecutionContext) -> {
            String name = bukkitCommandExecutionContext.popFirstArg();
            return userManager.users().stream().filter(user -> user.name().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new IllegalArgumentException("User not found"));
        });
        manager.enableUnstableAPI("brigadier");
        manager.getCommandCompletions().registerAsyncCompletion("users", (bukkitCommandCompletionContext) -> userManager.users().stream().map(SunscreenUser::name).filter(name -> name.startsWith(bukkitCommandCompletionContext.getInput())).toList());
        ///manager.getCommandCompletions().registerAsyncCompletion("menus", (bukkitCommandCompletionContext) -> library.menuRegistry().all().stream().map(menu -> menu.identifier().string()).filter(name -> name.startsWith(bukkitCommandCompletionContext.getInput())).toList());
        //manager.registerCommand(new SunscreenCommand());
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }

    public UserManager userManager() {
        return userManager;
    }


}
