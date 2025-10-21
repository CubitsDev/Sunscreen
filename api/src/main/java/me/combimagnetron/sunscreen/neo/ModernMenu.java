package me.combimagnetron.sunscreen.neo;

import me.combimagnetron.passport.util.data.Identifier;
import org.jetbrains.annotations.NotNull;

public interface ModernMenu {

    @NotNull MenuRoot root();

    @NotNull Identifier identifier();

    void close();

}
