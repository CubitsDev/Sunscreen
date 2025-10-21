import me.combimagnetron.sunscreen.neo.MenuTemplate;
import me.combimagnetron.sunscreen.neo.MenuRoot;
import me.combimagnetron.sunscreen.neo.element.Elements;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.graphic.modifier.GraphicModifiers;
import me.combimagnetron.sunscreen.neo.graphic.modifier.ModifierContext;
import me.combimagnetron.sunscreen.neo.graphic.shape.Shape;
import me.combimagnetron.sunscreen.neo.input.keybind.Keybind;
import me.combimagnetron.sunscreen.neo.layout.Layout;
import me.combimagnetron.sunscreen.neo.property.Position;
import me.combimagnetron.sunscreen.neo.property.RelativeMeasure;
import me.combimagnetron.sunscreen.neo.selector.Selector;
import me.combimagnetron.sunscreen.neo.selector.filter.Filter;
import me.combimagnetron.sunscreen.util.data.Identifier;
import me.combimagnetron.sunscreen.util.math.Vec2i;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class TestModernTemplate implements MenuTemplate {
    @Override
    public void build(@NotNull MenuRoot root) {
        //Defining an Element
        root.element(Elements.label(Identifier.of("test_modern:label"), Component.empty()));

        //Building an automatically flowing layout (FlowLayout) with the earlier defined element and a new one
        root.element(
                Layout.flow(
                    Selector.filtered(Filter.identifiable(Identifier.Namespace.of("test_modern"))),
                    Elements.image(Identifier.of("test_image"), Canvas.empty(Vec2i.of(100, 200)))
                )
        );

        //Making a new canvas and applying a GraphicModifier
        Canvas canvas = Canvas.empty(Vec2i.of(100, 300));

        canvas.modifier(
                GraphicModifiers.mask(
                        Shape.rectangle(Vec2i.of(16, 16)),
                        ModifierContext.of(Position.fixed(Vec2i.zero()))
                )
        );

        //Registering a keybind
        root.keybind(Keybind.of(Keybind.NamedKey.S, Keybind.NamedModifier.CTRL), keybindPressedEvent -> {

        });

        //Properties (Size, Position, Margin etc.) can be either fixed or relative
        Position fixed = Position.fixed(Vec2i.of(0, 0));
        Position relative = Position.relative(RelativeMeasure.vec2i().x().percentage(50).back().y().pixel(2).back());
    }
}
