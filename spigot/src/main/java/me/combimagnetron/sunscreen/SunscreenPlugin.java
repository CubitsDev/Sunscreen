package me.combimagnetron.sunscreen;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.combimagnetron.passport.Passport;
import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.hook.SunscreenHook;
import me.combimagnetron.sunscreen.neo.ActiveMenu;
import me.combimagnetron.sunscreen.neo.graphic.text.style.impl.font.AtlasFont;
import me.combimagnetron.sunscreen.neo.registry.Registries;
import me.combimagnetron.sunscreen.placeholder.PapiPlaceholderProvider;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.sunscreen.user.UserManager;
import me.combimagnetron.sunscreen.util.FileProvider;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SunscreenPlugin extends JavaPlugin implements Listener {
    private SunscreenLibrary<SunscreenPlugin, Player> library;
    private UserManager userManager;
    private static final Identifier FONT_ID = Identifier.of("sunscreen", "font/minecraft");

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
        //PacketEvents.getAPI().getEventManager().registerListener(new MenuListener(), PacketListenerPriority.LOWEST);
        //PacketEvents.getAPI().getEventManager().registerListener(new AnvilListener(), PacketListenerPriority.LOWEST);
    }

    @EventHandler
    public void onSneak(PlayerSwapHandItemsEvent sneakEvent) {
        SunscreenUser<?> user = userManager().user(sneakEvent.getPlayer());
        ActiveMenu menu = new ActiveMenu(new TestMenuTemplate(), user, Identifier.of("aa"));
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
        AtlasFont atlasFont = AtlasFont.font(FONT_ID).fromTtfFile(FileProvider.resource().find("minecraft_font.ttf").toPath(), 8);
        Registries.register(Registries.FONTS, atlasFont);
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

    private void platformSpecific() {
        library.passport().placeholders().register(new PapiPlaceholderProvider());
    }

    private void commands() {
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }

    public UserManager userManager() {
        return userManager;
    }


}
