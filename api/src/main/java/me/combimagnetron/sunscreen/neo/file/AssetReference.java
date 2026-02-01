package me.combimagnetron.sunscreen.neo.file;

import me.combimagnetron.passport.util.data.Identifier;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an asset reference in the menu index.
 *
 * @param type The type of asset (e.g., "image", "sound")
 * @param id   The unique identifier for the asset
 * @param file The relative file path to the asset
 */
public record AssetReference(
        @NotNull String type,
        @NotNull Identifier id,
        @NotNull String file) {

    public static @NotNull AssetReference image(@NotNull Identifier id, @NotNull String file) {
        return new AssetReference("image", id, file);
    }

    public static @NotNull AssetReference sound(@NotNull Identifier id, @NotNull String file) {
        return new AssetReference("sound", id, file);
    }
}
