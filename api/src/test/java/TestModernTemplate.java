import me.combimagnetron.sunscreen.neo.MenuTemplate;
import me.combimagnetron.sunscreen.neo.MenuRoot;
import me.combimagnetron.sunscreen.neo.element.Elements;
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
        //Define an element
        root.element(Elements.label(Identifier.of("test_modern:label"), Component.empty()));

        //Select an element and do stuff with it
        root.element(Layout.flow(
                Selector.filtered(Filter.identifiable(Identifier.Namespace.of("test_modern")))),
                Elements.image(Identifier.of("test_image"), Grap)
        );

        //Register a keybind
        root.keybind(Keybind.of(Keybind.NamedKey.S, Keybind.NamedModifier.CTRL), keybindPressedEvent -> {

        });

        //Properties (size, position, margin etc) can be either fixed or relative
        Position fixed = Position.fixed(Vec2i.of(0, 0));
        Position relative = Position.relative(RelativeMeasure.vec2i().x().percentage(50).back().y().pixel(2).back());
    }
}
