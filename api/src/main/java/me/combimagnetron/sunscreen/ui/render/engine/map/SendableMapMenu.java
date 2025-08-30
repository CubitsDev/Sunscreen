package me.combimagnetron.sunscreen.ui.render.engine.map;

import java.util.Arrays;

public record SendableMapMenu(SendableMap[] sendableMaps) {

    public static SendableMapMenu of(SendableMap... maps) {
        return new SendableMapMenu((SendableMap[]) Arrays.stream(maps).toArray());
    }

}
