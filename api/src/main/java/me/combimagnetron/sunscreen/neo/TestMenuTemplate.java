package me.combimagnetron.sunscreen.neo;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.element.Elements;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.graphic.color.Color;
import me.combimagnetron.sunscreen.neo.graphic.shape.Shape;
import me.combimagnetron.sunscreen.neo.property.Position;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.theme.ModernTheme;
import org.jetbrains.annotations.NotNull;

public class TestMenuTemplate implements MenuTemplate {
    private static final Identifier IDENTIFIER = Identifier.of("sunscreen", "test_menu");

    @Override
    public @NotNull Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public void build(@NotNull MenuRoot root) {
        root.theme(
            ModernTheme.theme(
                Identifier.of(
                    "sunscreen",
                    "test_menu/theme/test")
            )
        );
        //root.element(Elements.image(Identifier.of("woopsie_fuck"), Canvas.url("https://i.imgur.com/eIacYAm.png")).position(Position.nil()).scale(Scale.fixed(1.56f))
        root.element(
            Elements.image(
                Identifier.of(
                    "sunscreen",
                    "test_menu/element/test_image"
                ),
                Canvas.empty(Vec2i.of(100, 100)).fill(Position.nil(), Size.fixed(Vec2i.of(100, 100)), Color.of(54, 197, 244))
            ).position(Position.fixed(Vec2i.of(0, 0)))
        ).element(
            Elements.button(
                Identifier.of(
                    "sunscreen",
                    "test_menu/element/button"
                )
            ).position(Position.fixed(Vec2i.of(450, 200))).size(Size.fixed(Vec2i.of(77, 24)))
        ).element(
            Elements.shape(
                Identifier.of(
                "sunscreen",
                "test_menu/element/test_shape"
                ),
                Shape.line(Vec2i.of(200, 322), Vec2i.of(600, 20), 3),
                Color.of(0, 0, 0)
            ).position(Position.fixed(Vec2i.of(200, 200)))
        ).element(
            Elements.image(
                Identifier.of(
                    "sunscreen",
                    "test_menu/element/test_image_url"
                ),
                Canvas.url("https://i.imgur.com/YNMmJRM.png")
            ).position(Position.fixed(Vec2i.of(200, 300)))//.position(Position.relative(RelativeMeasure.vec2i().x().percentage(50).back().y().percentage(50).back()))
        );
    }

}
