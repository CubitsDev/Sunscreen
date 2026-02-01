package me.combimagnetron.sunscreen.neo.editor.toolbar;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.editor.widget.EditorWidget;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.phase.context.RenderContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.List;

public class Toolbar extends EditorWidget {

    @Override
    public @NotNull Identifier identifier() {
        return null;
    }

    @Override
    public <T, C, P extends Property<T, C>> @Nullable P property(@NotNull Class<P> propertyClass) {
        return null;
    }

    @Override
    public @NotNull <T, C> ElementLike<EditorWidget> property(@NotNull Property<@NotNull T, @NotNull C> property) {
        return null;
    }

    @Override
    public @NotNull Collection<Property<?, ?>> properties() {
        return List.of();
    }

    @Override
    public @NotNull Collection<ElementLike<?>> children() {
        return List.of();
    }

    @Override
    public @NonNull Canvas render(@NonNull Size property, @Nullable RenderContext context) {
        return null;
    }
}
