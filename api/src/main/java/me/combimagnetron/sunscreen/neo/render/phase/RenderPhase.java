package me.combimagnetron.sunscreen.neo.render.phase;

import me.combimagnetron.sunscreen.neo.render.phase.context.RenderPhaseContext;
import org.jetbrains.annotations.NotNull;

public interface RenderPhase<P extends RenderPhase<P, N>, N extends RenderPhase<N, ? extends RenderPhase<?, ?>>> {

    @NotNull Class<@NotNull N> nextType();

    @NotNull N advance(RenderPhaseContext<@NotNull P, @NotNull N> renderPhaseContext);

    class Process implements RenderPhase<Process, Draw> {

        @Override
        public @NotNull Class<Draw> nextType() {
            return Draw.class;
        }

        @Override
        public @NotNull Draw advance(RenderPhaseContext<Process, Draw> renderPhaseContext) {
            return null;
        }

    }

    class Draw implements RenderPhase<Draw, Render> {

        @Override
        public @NotNull Class<Render> nextType() {
            return Render.class;
        }

        @Override
        public @NotNull Render advance(RenderPhaseContext<Draw, Render> renderPhaseContext) {
            return null;
        }

    }

    class Render implements RenderPhase<Render, Send> {

        @Override
        public @NotNull Class<Send> nextType() {
            return Send.class;
        }

        @Override
        public @NotNull Send advance(RenderPhaseContext<Render, Send> renderPhaseContext) {
            return null;
        }

    }

    class Send implements RenderPhase<Send, Empty> {

        @Override
        public @NotNull Class<Empty> nextType() {
            return Empty.class;
        }

        @Override
        public @NotNull Empty advance(RenderPhaseContext<Send, Empty> renderPhaseContext) {
            return null;
        }

    }

    class Empty implements RenderPhase<Empty, Empty> {

        @Override
        public @NotNull Class<Empty> nextType() {
            return Empty.class;
        }

        @Override
        public @NotNull Empty advance(RenderPhaseContext<Empty, Empty> renderPhaseContext) {
            return null;
        }

    }

}
