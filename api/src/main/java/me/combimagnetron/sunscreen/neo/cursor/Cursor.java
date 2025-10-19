package me.combimagnetron.sunscreen.neo.cursor;

import org.jetbrains.annotations.NotNull;

public interface Cursor {

    @NotNull Cursor style(@NotNull CursorStyle cursorStyle);

    @NotNull Cursor mode(@NotNull CursorMode cursorMode);

}
