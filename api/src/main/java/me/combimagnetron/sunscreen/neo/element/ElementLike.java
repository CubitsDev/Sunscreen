package me.combimagnetron.sunscreen.neo.element;

import me.combimagnetron.sunscreen.neo.property.*;
import me.combimagnetron.passport.util.data.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ElementLike<E extends ElementLike<E>> extends PropertyContainer<ElementLike<E>> {

    @Nullable Identifier identifier();

}
