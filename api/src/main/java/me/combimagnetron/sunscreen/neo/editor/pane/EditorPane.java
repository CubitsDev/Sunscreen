package me.combimagnetron.sunscreen.neo.editor.pane;

import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.neo.property.PropertyContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public interface EditorPane<P extends EditorPane<P>> extends PropertyContainer<P> {

    @NotNull ResizeDirection resizeDirection();

    boolean fixed();

    final class FloatingEditorPane implements EditorPane<FloatingEditorPane> {

        @Override
        public <T, C, P extends Property<T, C>> @Nullable P property(@NotNull Class<P> propertyClass) {
            return null;
        }

        @Override
        public @NotNull <T, C> FloatingEditorPane property(@NotNull Property<@NotNull T, @NotNull C> property) {
            return null;
        }

        @Override
        public @NotNull Collection<Property<?, ?>> properties() {
            return List.of();
        }

        @Override
        public @NotNull ResizeDirection resizeDirection() {
            return null;
        }

        @Override
        public boolean fixed() {
            return false;
        }
    }

    final class DynamicFixedEditorPane implements EditorPane<DynamicFixedEditorPane> {

        @Override
        public @NotNull ResizeDirection resizeDirection() {
            return null;
        }

        @Override
        public boolean fixed() {
            return false;
        }

        @Override
        public <T, C, P extends Property<T, C>> @Nullable P property(@NotNull Class<P> propertyClass) {
            return null;
        }

        @Override
        public @NotNull <T, C> DynamicFixedEditorPane property(@NotNull Property<@NotNull T, @NotNull C> property) {
            return null;
        }

        @Override
        public @NotNull Collection<Property<?, ?>> properties() {
            return List.of();
        }
    }

    enum ResizeDirection {
        NONE, LEFT, RIGHT, HORIZONTAL, UP, DOWN, VERTICAL, ALL
    }

}
