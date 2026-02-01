package me.combimagnetron.sunscreen.neo.file;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.neo.page.Page;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a page reference in the menu index.
 *
 * @param id     The unique identifier for the page
 * @param file   The relative file path to the page XML
 * @param isRoot Whether this is the root/main page
 */
public record PageReference(
        @NotNull Identifier id,
        @NotNull String file,
        boolean isRoot) {

    public static @NotNull PageReference root(@NotNull Identifier id, @NotNull String file) {
        return new PageReference(id, file, true);
    }

    public static @NotNull PageReference page(@NotNull Identifier id, @NotNull String file) {
        return new PageReference(id, file, false);
    }
}
