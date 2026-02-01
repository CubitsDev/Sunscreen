package me.combimagnetron.sunscreen.neo.graphic.modifier.impl;

import me.combimagnetron.sunscreen.neo.graphic.modifier.GraphicModifier;
import me.combimagnetron.sunscreen.neo.graphic.modifier.ModifierContext;
import me.combimagnetron.sunscreen.neo.graphic.modifier.handler.GraphicModifierHandler;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public record Monochrome() implements GraphicModifier<Monochrome> {

    @Override
    public @NotNull GraphicModifierHandler<Monochrome> handler() {
        return null;
    }

    @Override
    public @NonNull Monochrome modifier() {
        return null;
    }

    @Override
    public @NotNull ModifierContext context() {
        return null;
    }

}
