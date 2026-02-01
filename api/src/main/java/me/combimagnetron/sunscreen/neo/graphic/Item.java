package me.combimagnetron.sunscreen.neo.graphic;

import me.combimagnetron.sunscreen.neo.graphic.modifier.GraphicModifier;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class Item implements GraphicLike<Item> {

    @Override
    public @NonNull <M> Item modifier(@NotNull GraphicModifier<M> modifier) {
        return this;
    }

    @Override
    public @NotNull BufferedColorSpace bufferedColorSpace() {
        return null;
    }

}
