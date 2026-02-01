package me.combimagnetron.sunscreen.neo;

import me.combimagnetron.passport.internal.entity.metadata.type.Vector3d;
import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.SunscreenLibrary;
import me.combimagnetron.sunscreen.neo.input.context.InputContext;
import me.combimagnetron.sunscreen.neo.input.context.MouseInputContext;
import me.combimagnetron.sunscreen.neo.input.context.ScrollInputContext;
import me.combimagnetron.sunscreen.neo.loader.MenuComponent;
import me.combimagnetron.sunscreen.neo.loader.MenuComponentLoaderContext;
import me.combimagnetron.sunscreen.neo.protocol.PlatformProtocolIntermediate;
import me.combimagnetron.sunscreen.neo.protocol.type.EntityReference;
import me.combimagnetron.sunscreen.neo.protocol.type.Location;
import me.combimagnetron.sunscreen.neo.render.engine.pipeline.RenderPipeline;
import me.combimagnetron.sunscreen.neo.render.engine.pipeline.RenderThreadPoolHandler;
import me.combimagnetron.sunscreen.neo.session.Session;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.sunscreen.util.IdentifierHolder;
import me.combimagnetron.sunscreen.util.Scheduler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveMenu implements IdentifierHolder {
    private final Map<Class<MenuComponent<?>>, MenuComponent<?>> loadedComponents = new HashMap<>();
    private final Map<Class<? extends InputContext<?>>, InputContext<?>> inputContextMap = new HashMap<>();
    private final MenuRoot menuRoot = new MenuRoot();
    private final Vector3d initialRotation;
    private final SunscreenUser<?> user;
    private final RenderPipeline renderPipeline;
    private final Identifier identifier;

    public ActiveMenu(@NotNull MenuTemplate template, @NotNull SunscreenUser<?> user, @NotNull Identifier identifier) {
        this.initialRotation = user.rotation();
        this.user = user;
        this.identifier = identifier;
        template.build(menuRoot);
        inputContextMap.put(MouseInputContext.class, new MouseInputContext(false, false, false, Vec2i.zero()));
        inputContextMap.put(ScrollInputContext.class, new ScrollInputContext(false, 0f));
        loadComponents();
        PlatformProtocolIntermediate intermediate = SunscreenLibrary.library().intermediate();
        intermediate.gameTime(user);
        renderPipeline = RenderThreadPoolHandler.start(user, menuRoot);

        Location location = user.eyeLocation();
        intermediate.spawnAndSpectateDisplay(user, location);
        intermediate.spawnAndRideHorse(user, location);
        SunscreenLibrary.library().sessionHandler().session(new Session(this, user));
    }

    private void loadComponents() {
        MenuComponentLoaderContext context = new MenuComponentLoaderContext(null, null, null, null);
        menuRoot.components().parallelStream().forEach(component -> {
            MenuComponent<?> loaded = component.loader().load(context);
            loadedComponents.put((Class<MenuComponent<?>>) component.getClass(), loaded);
        });
    }

    public @NotNull MenuRoot root() {
        return menuRoot;
    }

    public @NotNull RenderPipeline loop() {
        return renderPipeline;
    }

    public @NotNull Identifier identifier() {
        return identifier;
    }

    public @NotNull <E extends InputContext<?>> E context(@NotNull Class<E> contextClass) {
        return (E) inputContextMap.get(contextClass);
    }

    public <E extends InputContext<?>> void context(@NotNull E context) {
        inputContextMap.put((Class<InputContext<?>>) context.getClass(), context);
    }

    public void close() {
        SunscreenLibrary.library().sessionHandler().remove(user);
        renderPipeline.stop();
        PlatformProtocolIntermediate intermediate = SunscreenLibrary.library().intermediate();
        intermediate.reset(user);
    }

}
