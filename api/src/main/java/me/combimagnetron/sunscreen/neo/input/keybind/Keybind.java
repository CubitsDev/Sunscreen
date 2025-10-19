package me.combimagnetron.sunscreen.neo.input.keybind;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public record Keybind(Registered registered, Collection<Modifier> modifiers) {

    public static Keybind of(@NotNull Keybind.NamedKey registered, @NotNull Modifier... modifiers) {
        return of(registered.registered, modifiers);
    }

    public static Keybind of(@NotNull Registered registered, @NotNull Collection<Modifier> modifiers) {
        return new Keybind(registered, modifiers);
    }

    public static Keybind of(@NotNull Registered registered, @NotNull Modifier... modifiers) {
        return of(registered, Arrays.stream(modifiers).collect(Collectors.toSet()));
    }

    public static Keybind of(@NotNull Keybind.NamedKey registered, @NotNull NamedModifier... modifiers) {
        return of(registered.registered, Arrays.stream(modifiers).map(namedModifier -> namedModifier.modifier).collect(Collectors.toSet()));
    }

    public static Keybind of(@NotNull Registered registered, @NotNull NamedModifier... modifiers) {
        return of(registered, Arrays.stream(modifiers).map(namedModifier -> namedModifier.modifier).collect(Collectors.toSet()));
    }

    /** Enum class containing all detectable modifier keys in Minecraft.
     */
    public enum Modifier {
        SPRINT, SNEAK, JUMP
    }

    /**
     * Enum class to wrap modifiers to default Minecraft key.
     *
     * @apiNote Do not expect these modifiers to be the actual modifiers mentioned, in case of mentioning it in label please use {@link KeybindTextMapping}.
     */
    public enum NamedModifier {
        CTRL(Modifier.SPRINT), SHIFT(Modifier.SNEAK), SPACE(Modifier.JUMP);

        private final Modifier modifier;

        NamedModifier(Modifier modifier) {
            this.modifier = modifier;
        }

    }

    /** Enum class containing all detectable keys in Minecraft.
     */
    public enum Registered {
        FORWARD, BACKWARD, LEFT, RIGHT, DROP_ITEM, ADVANCEMENTS, SWAP_HAND, SLOT_1, SLOT_2, SLOT_3, SLOT_4, SLOT_5, SLOT_6, SLOT_7, SLOT_8, SLOT_9
    }

    /**
     * Enum class to wrap keys to default Minecraft key.
     *
     * @apiNote Do not expect these keys to be the actual key mentioned, in case of mentioning it in label please use {@link KeybindTextMapping}.
     */
    public enum NamedKey {
        W(Registered.FORWARD), S(Registered.BACKWARD), A(Registered.LEFT), D(Registered.RIGHT), Q(Registered.DROP_ITEM), L(Registered.ADVANCEMENTS), F(Registered.SWAP_HAND), KEY_1(Registered.SLOT_1), KEY_2(Registered.SLOT_2), KEY_3(Registered.SLOT_3), KEY_4(Registered.SLOT_4), KEY_5(Registered.SLOT_5), KEY_6(Registered.SLOT_6), KEY_7(Registered.SLOT_7), KEY_8(Registered.SLOT_8), KEY_9(Registered.SLOT_9);

        private final Registered registered;

        NamedKey(Registered registered) {
            this.registered = registered;
        }

    }

}
