package me.combimagnetron.sunscreen.neo.render.engine.map;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public record SendableMapMenu(SendableMap[] sendableMaps) {

    public static SendableMapMenu of(@NotNull SendableMap... maps) {
        return new SendableMapMenu((SendableMap[]) Arrays.stream(maps).toArray());
    }

}
