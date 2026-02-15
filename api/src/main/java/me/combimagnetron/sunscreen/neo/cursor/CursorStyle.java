package me.combimagnetron.sunscreen.neo.cursor;

import me.combimagnetron.passport.util.data.Identifier;
import org.jetbrains.annotations.NotNull;

public record CursorStyle(@NotNull Identifier identifier, @NotNull String asset) {
    private final static CursorStyle POINTER = new CursorStyle(Identifier.of("sunscreen", "cursor/pointer"), "default");
    private final static CursorStyle TEXT_CARET = new CursorStyle(Identifier.of("sunscreen", "cursor/text_caret"), "text");
    private final static CursorStyle MOVE = new CursorStyle(Identifier.of("sunscreen", "cursor/move"), "move");
    private final static CursorStyle RESIZE_VERTICAL = new CursorStyle(Identifier.of("sunscreen", "cursor/resize_vertical"), "resize_vertical");
    private final static CursorStyle RESIZE_HORIZONTAL = new CursorStyle(Identifier.of("sunscreen", "cursor/resize_horizontal"), "resize_horizontal");
    private final static CursorStyle MAGNIFY = new CursorStyle(Identifier.of("sunscreen", "cursor/magnify"), "magnify");
    private final static CursorStyle BUSY = new CursorStyle(Identifier.of("sunscreen", "cursor/busy"), "await");
    private final static CursorStyle CLICK = new CursorStyle(Identifier.of("sunscreen", "cursor/click"), "click");

    public static @NotNull CursorStyle style(@NotNull Identifier identifier, @NotNull String asset) {
        return new CursorStyle(identifier, asset);
    }

    public static @NotNull CursorStyle pointer() {
        return POINTER;
    }

    public static @NotNull CursorStyle textCaret() {
        return TEXT_CARET;
    }

    public static @NotNull CursorStyle move() {
        return MOVE;
    }

    public static @NotNull CursorStyle resizeVertical() {
        return RESIZE_VERTICAL;
    }

    public static @NotNull CursorStyle resizeHorizontal() {
        return RESIZE_HORIZONTAL;
    }

    public static @NotNull CursorStyle magnify() {
        return MAGNIFY;
    }

    public static @NotNull CursorStyle busy() {
        return BUSY;
    }

    public static @NotNull CursorStyle click() {
        return CLICK;
    }

}
