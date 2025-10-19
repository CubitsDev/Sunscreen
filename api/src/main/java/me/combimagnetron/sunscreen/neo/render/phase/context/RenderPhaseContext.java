package me.combimagnetron.sunscreen.neo.render.phase.context;

import me.combimagnetron.sunscreen.neo.render.phase.RenderPhase;

public interface RenderPhaseContext<P extends RenderPhase<P, N>, N extends RenderPhase<N, ? extends RenderPhase<?, ?>>> {

    final class ProcessRenderPhaseContext implements me.combimagnetron.sunscreen.neo.render.phase.context.RenderPhaseContext<RenderPhase.Process, RenderPhase.Draw> {

    }

    final class DrawRenderPhaseContext implements me.combimagnetron.sunscreen.neo.render.phase.context.RenderPhaseContext<RenderPhase.Draw, RenderPhase.Render> {

    }

    final class RenderRenderPhaseContext implements me.combimagnetron.sunscreen.neo.render.phase.context.RenderPhaseContext<RenderPhase.Render, RenderPhase.Send> {

    }

    final class SendRenderPhaseContext implements me.combimagnetron.sunscreen.neo.render.phase.context.RenderPhaseContext<RenderPhase.Send, RenderPhase.Empty> {

    }

}
