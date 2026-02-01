package me.combimagnetron.sunscreen.neo.layout;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.element.ElementContainer;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.element.ModernElement;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.render.phase.context.RenderContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.List;

public interface Layout<E extends ElementLike<E>> extends ElementContainer<E> {

     E root();

     static FlowLayout<?> flow(@NotNull Identifier identifier) {
         return new FlowLayout<>(identifier);
     }

     static FlowLayout<?> flow(@NotNull Identifier identifier, @NotNull ElementLike<?> elementLike) {
         return new FlowLayout<>(identifier, elementLike);
     }

     static FlowLayout<?> flow(@NotNull Identifier identifier, @NotNull ElementLike<?> @NotNull... elementLike) {
         return new FlowLayout<>(identifier, elementLike);
     }

     class FlowLayout<E extends ElementLike<E>> implements Layout<E> {
         private final Int2ObjectOpenHashMap<ElementLike<?>> indexedElementLikeMap = new Int2ObjectOpenHashMap<>();
         private final Identifier identifier;

         protected FlowLayout(Identifier identifier, ElementLike<?>... elementLikes) {
             this.identifier = identifier;
         }

         @Override
         public E root() {
             return null;
         }

         @Override
         public @NotNull Collection<ElementLike<?>> children() {
             return List.of();
         }

         @Override
         public @NotNull <L extends ElementLike<L>> ElementContainer<@NotNull E> add(@NotNull L elementLike) {
             return null;
         }

         @Override
         public @NotNull <L extends ElementLike<L>> ElementContainer<@NotNull E> add(@NotNull Iterable<@NotNull L> elementLike) {
             return null;
         }

         @Override
         public @NotNull <L extends ElementLike<L>> ElementContainer<@NotNull E> remove(@NotNull L elementLike) {
             return null;
         }

         @Override
         public @NotNull ElementContainer<@NotNull E> remove(@Nullable Identifier identifier) {
             return null;
         }

         @Override
         public @NotNull Identifier identifier() {
             return null;
         }

         @Override
         public <T, C, P extends Property<T, C>> @NotNull P property(@NotNull Class<P> propertyClass) {
             return null;
         }

         @Override
         public <T, C> @NotNull E property(@NotNull Property<@NotNull T, @NotNull C> property) {
             return null;
         }

         @Override
         public @NotNull Collection<Property<?, ?>> properties() {
             return List.of();
         }

         @Override
         public @NonNull Canvas render(@NonNull Size property, @Nullable RenderContext context) {
             Vec2i calculatedSize = property.value();
             Preconditions.checkNotNull(calculatedSize, "Size in layout may not be null");
             Canvas finalCanvas = Canvas.empty(calculatedSize);
             indexedElementLikeMap.int2ObjectEntrySet().parallelStream().forEach((entry) -> {
                 ElementLike<?> element = entry.getValue();
                 Vec2i elementPosition = element.position().value();
                 if (elementPosition == null) return;
                 if (!(element instanceof ModernElement<?,?> modernElement)) return;
                 finalCanvas.place((Canvas) modernElement.render(property, context), elementPosition);
             });
             return finalCanvas;
         }

     }

}
