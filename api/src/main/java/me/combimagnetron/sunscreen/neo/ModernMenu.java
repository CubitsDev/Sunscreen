package me.combimagnetron.sunscreen.neo;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.element.tree.ElementTree;
import me.combimagnetron.sunscreen.util.IdentifierHolder;
import org.jetbrains.annotations.NotNull;

public interface ModernMenu extends IdentifierHolder {

    @NotNull MenuRoot root();

    @NotNull ElementTree tree();

    @NotNull Identifier identifier();

    void close();

}
