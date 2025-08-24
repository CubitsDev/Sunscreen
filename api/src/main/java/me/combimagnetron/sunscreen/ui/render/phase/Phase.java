package me.combimagnetron.sunscreen.ui.render.phase;

import me.combimagnetron.sunscreen.ui.render.phase.context.PhaseContext;
import org.jetbrains.annotations.NotNull;

public interface Phase<P extends Phase<P, N>, N extends Phase<N, ? extends Phase<?, ?>>> {

    @NotNull Class<@NotNull N> nextType();

    @NotNull N advance(PhaseContext<@NotNull P, @NotNull N> phaseContext);

    class Build implements Phase<Build, Render> {

        @Override
        public @NotNull Class<Render> nextType() {
            return Render.class;
        }

        @Override
        public @NotNull Render advance(PhaseContext<Build, Render> phaseContext) {
            return null;
        }

    }

    class Render implements Phase<Render, Send> {

        @Override
        public @NotNull Class<Send> nextType() {
            return Send.class;
        }

        @Override
        public @NotNull Send advance(PhaseContext<Render, Send> phaseContext) {
            return null;
        }

    }

    class Send implements Phase<Send, Empty> {

        @Override
        public @NotNull Class<Empty> nextType() {
            return Empty.class;
        }

        @Override
        public @NotNull Empty advance(PhaseContext<Send, Empty> phaseContext) {
            return null;
        }

    }

    class Empty implements Phase<Empty, Empty> {

        @Override
        public @NotNull Class<Empty> nextType() {
            return Empty.class;
        }

        @Override
        public @NotNull Empty advance(PhaseContext<Empty, Empty> phaseContext) {
            return null;
        }

    }

}
