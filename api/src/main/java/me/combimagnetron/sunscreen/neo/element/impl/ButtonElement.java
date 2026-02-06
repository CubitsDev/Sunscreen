package me.combimagnetron.sunscreen.neo.element.impl;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.element.GenericInteractableModernElement;
import me.combimagnetron.sunscreen.neo.event.UserMoveStateChangeEvent;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.input.InputHandler;
import me.combimagnetron.sunscreen.neo.input.ListenerReferences;
import me.combimagnetron.sunscreen.neo.input.context.InputContext;
import me.combimagnetron.sunscreen.neo.input.context.MouseInputContext;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.engine.context.RenderContext;
import me.combimagnetron.sunscreen.util.helper.HoverHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ButtonElement extends GenericInteractableModernElement<ButtonElement, Canvas, ButtonElement.ButtonElementListenerReferences> {
    private final ButtonElementListenerReferences references = new ButtonElementListenerReferences(this);
    private final Canvas hovered;
    private final Canvas idle;
    private boolean hover = false;

    public ButtonElement(@Nullable Identifier identifier) {
        super(identifier);
        Canvas sheet = Canvas.url("https://i.imgur.com/NGqcMHh.png");
        final Vec2i size = Vec2i.of(77, 24);
        this.idle = sheet.sub(Vec2i.zero(), size);
        this.hovered = sheet.sub(Vec2i.of(0, 24), size);
    }

    @Override
    protected void lateInit() {
        super.lateInit();
        input(MouseInputContext.class).listen(this::handleCursor);
    }

    private void handleCursor(@NotNull UserMoveStateChangeEvent event) {
        final MouseInputContext context = event.context();
        Vec2i cursor = context.position();
        hover = HoverHelper.in(this, cursor);
    }

    @Override
    public @NotNull ButtonElementListenerReferences listen() {
        return references;
    }

    @Override
    public @NotNull Canvas render(@NotNull Size property, @Nullable RenderContext context) {
        //todo replace with real error handling for the editor and placeholder textures.
//        if (context == null) return Canvas.url("");
//        return context.decorators().get(this.getClass()).render(property, context);
        return hover ? hovered : idle;
    }

    public record ButtonElementListenerReferences(ButtonElement buttonElement) implements ListenerReferences<ButtonElement, ButtonElementListenerReferences> {

        @Override
        public ButtonElement back() {
            return buttonElement;
        }

    }

}
