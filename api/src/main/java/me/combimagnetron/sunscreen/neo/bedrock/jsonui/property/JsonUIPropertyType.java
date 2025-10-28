package me.combimagnetron.sunscreen.neo.bedrock.jsonui.property;

import org.jetbrains.annotations.NotNull;

public record JsonUIPropertyType<T>(@NotNull Class<T> type, @NotNull String name, @NotNull T value) {

    public static <D> @NotNull JsonUIPropertyType<@NotNull D> of(@NotNull D d, @NotNull String name) {
        return new JsonUIPropertyType<>((Class<D>) d.getClass(), name, d);
    }

}
