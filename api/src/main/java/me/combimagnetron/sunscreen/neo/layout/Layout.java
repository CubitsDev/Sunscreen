package me.combimagnetron.sunscreen.neo.layout;

import me.combimagnetron.sunscreen.neo.element.ElementGroup;
import me.combimagnetron.sunscreen.neo.element.ElementLike;
import me.combimagnetron.sunscreen.neo.property.Property;
import me.combimagnetron.sunscreen.util.data.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Layout<E extends ElementLike<E>> extends ElementGroup<E> {

     E root();
     static <E extends ElementLike<E>> FlowLayout<E> flow() {
         return new FlowLayout<>();
     }

     static <E extends ElementLike<E>> FlowLayout<?> flow(ElementLike<?> elementLike) {
         return new FlowLayout<>(elementLike);
     }

     static <E extends ElementLike<E>> FlowLayout<?> flow(ElementLike<?>... elementLike) {
         return new FlowLayout<>(elementLike);
     }

     class FlowLayout<E extends ElementLike<E>> implements Layout<E> {

         protected FlowLayout(ElementLike<?>... elementLikes) {

         }

         @Override
         public E root() {
             return null;
         }

         @Override
         public @NotNull <L extends ElementLike<L>> ElementGroup<@NotNull E> add(@NotNull L elementLike) {
             return null;
         }

         @Override
         public @NotNull <L extends ElementLike<L>> ElementGroup<@NotNull E> add(@NotNull Iterable<@NotNull L> elementLike) {
             return null;
         }

         @Override
         public @NotNull <L extends ElementLike<L>> ElementGroup<@NotNull E> remove(@NotNull L elementLike) {
             return null;
         }

         @Override
         public @NotNull ElementGroup<@NotNull E> remove(@Nullable Identifier identifier) {
             return null;
         }

         @Override
         public @Nullable Identifier identifier() {
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
     }

}
