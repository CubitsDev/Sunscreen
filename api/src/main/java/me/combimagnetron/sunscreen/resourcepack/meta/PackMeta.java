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

    record Impl(PackVersion version, String description, String name) implements PackMeta {

        @Override
            public CodeBlock.JsonCodeBlock content() {
                return null;
            }

    }

}
