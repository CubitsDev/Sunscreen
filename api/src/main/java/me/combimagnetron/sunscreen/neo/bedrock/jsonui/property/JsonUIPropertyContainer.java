package me.combimagnetron.sunscreen.neo.bedrock.jsonui.property;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;

public class JsonUIPropertyContainer {
    private final Collection<JsonUIProperty> properties = new HashSet<>();

    public @NotNull Collection<JsonUIProperty> properties() {
        return properties;
    }

}
