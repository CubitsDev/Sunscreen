package me.combimagnetron.sunscreen.neo.graphic.text.transform;

import me.combimagnetron.sunscreen.neo.graphic.text.Text;
import me.combimagnetron.sunscreen.neo.graphic.text.TextImpl;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class Adventure2NativeTransformer {

    public static @NotNull Text adventure(@NotNull Component component) {
        return TextImpl.fromComponent(component);
    }

}
