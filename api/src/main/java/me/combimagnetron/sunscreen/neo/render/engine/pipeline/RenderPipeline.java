package me.combimagnetron.sunscreen.neo.render.engine.pipeline;

import it.unimi.dsi.fastutil.Pair;
import me.combimagnetron.passport.event.Dispatcher;
import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.event.MenuTickEndEvent;
import me.combimagnetron.sunscreen.neo.render.engine.phase.RenderPhase;
import me.combimagnetron.sunscreen.neo.render.engine.context.RenderContext;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public final class RenderPipeline {
    public static final ExecutorService WORK_EXECUTOR = Executors.newWorkStealingPool();
    private static final int PHASE_AMOUNTS = 4;
    private static final long TARGET_FPS = 60 * PHASE_AMOUNTS; //4 states that each need their own cycle, very nasty hack
    private static final long PERIOD_MS = 1000 / TARGET_FPS;

    private final AtomicLong tick = new AtomicLong();
    private final UUID menuUuid;
    private final ScheduledFuture<?> scheduledFuture;
    private final Map<Identifier, ElementLike<?>> queuedElements = new LinkedHashMap<>();
    private final SunscreenUser<?> user;
    private volatile RenderContext context;
    private RenderPhase<?> state;
    private long fullCycleTime = System.currentTimeMillis();
    private long stageCycleTime = System.currentTimeMillis();

    RenderPipeline(@NotNull ScheduledExecutorService scheduler, @NotNull SunscreenUser<?> user, @NotNull UUID menuUuid,
                   @NotNull Collection<ElementLike<?>> initialElements) {
        context = new RenderContext(user.screenInfo().viewport(), initialElements);
        this.menuUuid = menuUuid;
        this.state = new RenderPhase.Process(initialElements, user);
        for (ElementLike<?> initialElement : initialElements) {
            queuedElements.put(initialElement.identifier(), initialElement);
        }
        this.user = user;
        this.scheduledFuture = start(scheduler);
    }

    public void stop() {
        state = null;
        scheduledFuture.cancel(true);
    }

    private @NotNull ScheduledFuture<?> start(@NotNull ScheduledExecutorService scheduler) {
        return scheduler.scheduleAtFixedRate(this::tick, 0L, PERIOD_MS, TimeUnit.MILLISECONDS);
    }

    private void tick() {
        try {
            long currentTick = tick.incrementAndGet();
            //Class<?> type = state.getClass();
            Pair<RenderPhase<?>, RenderContext> pair = (Pair<RenderPhase<?>, RenderContext>) state.advance(context);
            state = pair.left();
            //System.out.println(type + " " + (System.currentTimeMillis() - stageCycleTime));
            context = pair.right();
            Dispatcher.dispatcher().post(new MenuTickEndEvent(menuUuid, currentTick));
            //stageCycleTime = System.currentTimeMillis();
            if (state.nextType() == RenderPhase.Process.class) {
                //System.out.println(System.currentTimeMillis() - fullCycleTime + " " + currentTick);
                state = new RenderPhase.Process(queuedElements.values(), user);
                //fullCycleTime = System.currentTimeMillis();
            }
            //queuedElements.clear();
            if (context.stop()) stop();
        } catch (Exception e) {
            stop();
            throw new RuntimeException("RenderPipeline tick failed", e);
        }
    }

    public void submit(ElementLike<?>... elementLikes) {
        for (ElementLike<?> elementLike : elementLikes) {
            queuedElements.put(elementLike.identifier(), elementLike);
        }
    }

    public @Nullable ElementLike<?> element(@NotNull Identifier identifier) {
        return queuedElements.get(identifier);
    }

}
