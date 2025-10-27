package me.combimagnetron.sunscreen.neo.element.impl;

import me.combimagnetron.passport.logic.state.MutableState;
import me.combimagnetron.passport.logic.state.State;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.element.GenericModernElement;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.RenderAction;
import me.combimagnetron.sunscreen.neo.render.engine.pass.RenderPass;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class LabelElement extends GenericModernElement<LabelElement> {
    private final MutableState<Component> text;

    public LabelElement(@NotNull Identifier identifier, @NotNull Component text) {
        super(identifier);
        this.text = State.mutable(Component.empty());
    }

    public LabelElement(@NotNull Identifier identifier, @NotNull State<Component> componentState) {
        super(identifier);
        this.text = (MutableState<Component>) componentState;
        MutableState<Component> componentMutableState = (MutableState<Component>) componentState;
        componentMutableState.observe((old, current) -> {
        });
    }

    @Override
    public @NotNull <G extends GraphicLike<G>> RenderPass<LabelElement, G> render(@NotNull Size property) {
        return (RenderPass<LabelElement, G>) RenderPass.pass(RenderPass.Origin.origin(this), RenderAction.simple(Canvas.empty(Vec2i.of(100, 100)).text(text.value())));
    }
}
