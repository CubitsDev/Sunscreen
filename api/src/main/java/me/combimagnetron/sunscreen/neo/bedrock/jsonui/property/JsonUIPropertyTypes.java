package me.combimagnetron.sunscreen.neo.bedrock.jsonui.property;

import me.combimagnetron.passport.util.Values;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.util.LazyReference;
import org.jetbrains.annotations.NotNull;

import static me.combimagnetron.sunscreen.neo.bedrock.jsonui.property.JsonUIPropertyType.*;

public interface JsonUIPropertyTypes {

    static @NotNull JsonUIPropertyType<@NotNull Vec2i> vector(@NotNull Vec2i vec2i, @NotNull String name) {
        return of(vec2i, name);
    }

    static @NotNull JsonUIPropertyType<@NotNull Integer> integer(int i, @NotNull String name) {
        return of(i, name);
    }

    static @NotNull JsonUIPropertyType<@NotNull String> string(@NotNull String string, @NotNull String name) {
        return of(string, name);
    }

    static @NotNull JsonUIPropertyType<@NotNull Boolean> bool(boolean bool, @NotNull String name) {
        return of(bool, name);
    }

    static @NotNull <D> JsonUIPropertyType<D @NotNull []> array(D @NotNull [] array, @NotNull String name) {
        return of(array, name);
    }

    static @NotNull JsonUIPropertyType<String @NotNull []> stringArray(@NotNull String[] array, @NotNull String name) {
        return array(array, name);
    }

    static @NotNull JsonUIPropertyType<Integer @NotNull []> integerArray(@NotNull Integer[] array, @NotNull String name) {
        return array(array, name);
    }

    static @NotNull <E extends Enum<E>> JsonUIPropertyType<@NotNull E> enumType(@NotNull E enumType, @NotNull String name) {
        return of(enumType, name);
    }

}
