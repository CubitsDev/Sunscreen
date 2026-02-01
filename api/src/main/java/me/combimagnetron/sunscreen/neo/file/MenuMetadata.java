package me.combimagnetron.sunscreen.neo.file;

import me.combimagnetron.passport.util.data.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Holds metadata for a menu, used when encoding to XML.
 *
 * @param identifier      The unique identifier for this menu
 * @param author          The author of the menu
 * @param minVersion      Minimum compatible version
 * @param maxVersion      Maximum compatible version (null for no limit)
 * @param originalVersion The version this menu was created with
 */
public record MenuMetadata(
        @NotNull Identifier identifier,
        @NotNull String author,
        @NotNull String minVersion,
        @Nullable String maxVersion,
        @NotNull String originalVersion) {

    public static @NotNull Builder builder(@NotNull Identifier identifier) {
        return new Builder(identifier);
    }

    public static final class Builder {
        private final Identifier identifier;
        private String author = "Unknown";
        private String minVersion = "0.0.1";
        private String maxVersion = null;
        private String originalVersion = "0.0.1";

        private Builder(Identifier identifier) {
            this.identifier = identifier;
        }

        public @NotNull Builder author(@NotNull String author) {
            this.author = author;
            return this;
        }

        public @NotNull Builder minVersion(@NotNull String minVersion) {
            this.minVersion = minVersion;
            return this;
        }

        public @NotNull Builder maxVersion(@Nullable String maxVersion) {
            this.maxVersion = maxVersion;
            return this;
        }

        public @NotNull Builder originalVersion(@NotNull String originalVersion) {
            this.originalVersion = originalVersion;
            return this;
        }

        public @NotNull MenuMetadata build() {
            return new MenuMetadata(identifier, author, minVersion, maxVersion, originalVersion);
        }
    }
}
