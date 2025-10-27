package me.combimagnetron.sunscreen.neo;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.util.IdentifierHolder;
import org.jetbrains.annotations.NotNull;

public interface MenuTemplate extends IdentifierHolder {

    @NotNull Identifier identifier();

    void build(@NotNull MenuRoot root);

}
