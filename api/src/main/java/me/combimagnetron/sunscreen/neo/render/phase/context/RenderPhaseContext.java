package me.combimagnetron.sunscreen.neo.render.phase.context;

import me.combimagnetron.sunscreen.menu.ScreenSize;
import me.combimagnetron.sunscreen.neo.element.tree.ElementTree;
import me.combimagnetron.sunscreen.neo.graphic.BufferedColorSpace;
import me.combimagnetron.sunscreen.neo.render.engine.grid.RenderGrid;
import me.combimagnetron.sunscreen.neo.render.phase.RenderPhase;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import org.jetbrains.annotations.NotNull;

public interface RenderPhaseContext<P extends RenderPhase<P, N>, N extends RenderPhase<N, ? extends RenderPhase<?, ?>>> {

    record ProcessRenderPhaseContext(@NotNull ElementTree processedTree, @NotNull ScreenSize screenSize) implements me.combimagnetron.sunscreen.neo.render.phase.context.RenderPhaseContext<RenderPhase.Process, RenderPhase.Draw> {

    }

    record DrawRenderPhaseContext(@NotNull ElementTree processedTree, @NotNull ScreenSize screenSize, @NotNull BufferedColorSpace start) implements me.combimagnetron.sunscreen.neo.render.phase.context.RenderPhaseContext<RenderPhase.Draw, RenderPhase.Render> {

    }

    record RenderRenderPhaseContext(@NotNull RenderGrid renderGrid, @NotNull BufferedColorSpace space) implements me.combimagnetron.sunscreen.neo.render.phase.context.RenderPhaseContext<RenderPhase.Render, RenderPhase.Send> {

    }

    record SendRenderPhaseContext(@NotNull SunscreenUser<?> user, byte @NotNull [] data) implements me.combimagnetron.sunscreen.neo.render.phase.context.RenderPhaseContext<RenderPhase.Send, RenderPhase.Empty> {

    }

}
