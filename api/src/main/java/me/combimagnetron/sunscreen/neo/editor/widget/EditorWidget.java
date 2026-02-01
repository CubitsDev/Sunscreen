package me.combimagnetron.sunscreen.neo.editor.widget;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.ElementContainer;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public abstract class EditorWidget implements ElementContainer<EditorWidget>, ElementLike<EditorWidget> {
    private final Map<Identifier, ElementLike<?>> elements = new HashMap<>();

    @Override
    public @NotNull <L extends ElementLike<L>> ElementContainer<@NotNull EditorWidget> add(@NotNull L elementLike) {
        elements.put(elementLike.identifier(), elementLike);
        return this;
    }

    @Override
    public @NotNull <L extends ElementLike<L>> ElementContainer<@NotNull EditorWidget> add(@NotNull Iterable<@NotNull L> elementLikes) {
        elementLikes.forEach(elementLike -> elements.put(elementLike.identifier(), elementLike));
        return this;
    }

    @Override
    public @NotNull <L extends ElementLike<L>> ElementContainer<@NotNull EditorWidget> remove(@NotNull L elementLike) {
        return remove(elementLike.identifier());
    }

    @Override
    public @NotNull ElementContainer<@NotNull EditorWidget> remove(@Nullable Identifier identifier) {
        elements.remove(identifier);
        return this;
    }

}
