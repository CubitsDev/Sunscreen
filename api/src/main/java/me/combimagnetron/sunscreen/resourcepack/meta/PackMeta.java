package me.combimagnetron.sunscreen.resourcepack.meta;

import me.combimagnetron.sunscreen.resourcepack.CodeBlock;

public interface PackMeta {

    CodeBlock.JsonCodeBlock content();

    PackVersion version();

    String description();

    String name();

    static PackMeta meta(PackVersion version, String description, String name) {
        return new Impl(version, description, name);
    }

    final class Impl implements PackMeta {
        private final PackVersion version;
        private final String description;
        private final String name;

        public Impl(PackVersion version, String description, String name) {
            this.version = version;
            this.description = description;
            this.name = name;
        }

        @Override
        public CodeBlock.JsonCodeBlock content() {
            return null;
        }

        @Override
        public PackVersion version() {
            return version;
        }

        @Override
        public String description() {
            return description;
        }

        @Override
        public String name() {
            return name;
        }

        }

}
