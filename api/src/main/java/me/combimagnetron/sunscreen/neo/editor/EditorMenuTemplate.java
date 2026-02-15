package me.combimagnetron.sunscreen.neo.editor;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.MenuTemplate;
import me.combimagnetron.sunscreen.neo.MenuRoot;
import me.combimagnetron.sunscreen.neo.element.GenericInteractableModernElement;
import me.combimagnetron.sunscreen.neo.element.impl.ButtonElement;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.graphic.NineSlice;
import me.combimagnetron.sunscreen.neo.graphic.color.Color;
import me.combimagnetron.sunscreen.neo.property.Position;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.theme.ModernTheme;
import me.combimagnetron.sunscreen.neo.theme.color.ColorScheme;
import me.combimagnetron.sunscreen.neo.theme.color.ColorSchemes;
import me.combimagnetron.sunscreen.neo.theme.decorator.ThemeDecorator;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EditorMenuTemplate implements MenuTemplate {
    private final static Identifier IDENTIFIER = Identifier.of("sunscreen", "internal/editor");

    @Override
    public @NotNull Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public void build(@NotNull MenuRoot root) {
        root.theme(
            ModernTheme.theme(
                Identifier.of("sunscreen", "internal/editor/theme/basic")
            ).colorScheme(ColorSchemes.EDITOR)
                .decorator(
                    ThemeDecorator.stateNineSlice(
                        ButtonElement.class,
                        Map.of(
                            GenericInteractableModernElement.ElementPhase.DEFAULT, NineSlice.nineSlice(Canvas.url("")),
                            GenericInteractableModernElement.ElementPhase.HOVER, NineSlice.nineSlice(Canvas.url("")),
                            GenericInteractableModernElement.ElementPhase.CLICK, NineSlice.nineSlice(Canvas.empty(Vec2i.of(11, 11)).fill(Position.nil(), Size.fixed(Vec2i.of(11, 11)), Color.of(13, 13, 13)))
                        )
                    )
                )

        );
    }

}
