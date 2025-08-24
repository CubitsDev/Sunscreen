package me.combimagnetron.sunscreen.ui.render.phase.context;

import me.combimagnetron.sunscreen.ui.render.phase.Phase;

public interface PhaseContext<P extends Phase<P, N>, N extends Phase<N, ? extends Phase<?, ?>>> {

    final class BuildPhaseContext implements PhaseContext<Phase.Build, Phase.Render> {

    }

    final class RenderPhaseContext implements PhaseContext<Phase.Render, Phase.Send> {

    }

    final class SendPhaseContext implements PhaseContext<Phase.Send, Phase.Empty> {

    }

}
