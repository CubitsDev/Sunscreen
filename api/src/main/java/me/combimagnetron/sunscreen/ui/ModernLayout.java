package me.combimagnetron.sunscreen.ui;

import me.combimagnetron.sunscreen.ui.element.ElementLike;
import me.combimagnetron.sunscreen.ui.theme.ModernTheme;
import org.jetbrains.annotations.NotNull;

public interface ModernLayout {

    <E extends ElementLike<E>> @NotNull ModernLayout element(@NotNull E e);

    @NotNull ModernLayout theme(@NotNull ModernTheme theme);

}
