package me.combimagnetron.sunscreen.neo.element.impl.breadcrumb;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.GenericInteractableModernElement;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.input.ListenerReferences;
import me.combimagnetron.sunscreen.neo.input.context.InputContext;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.engine.context.RenderContext;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class BreadcrumbElement extends GenericInteractableModernElement<BreadcrumbElement, Canvas, BreadcrumbElement.BreadcrumbElementListenerReferences> {
    private final BreadcrumbElementListenerReferences references = new BreadcrumbElementListenerReferences(this);

    protected BreadcrumbElement(@Nullable Identifier identifier) {
        super(identifier);
    }

    @Override
    public @NonNull BreadcrumbElementListenerReferences listen() {
        return references;
    }

    @Override
    public @NonNull <C extends InputContext<?>> C input(Class<C> clazz) {
        return null;
    }

    @Override
    public @NonNull Canvas render(@NonNull Size property, @Nullable RenderContext context) {
        return null;
    }

    public record BreadcrumbElementListenerReferences(
            BreadcrumbElement parent) implements ListenerReferences<BreadcrumbElement, BreadcrumbElementListenerReferences> {

        @Override
        public BreadcrumbElement back() {
            return parent;
        }

    }

}
