import me.combimagnetron.passport.event.EventBus;
import me.combimagnetron.passport.logic.state.MutableState;
import me.combimagnetron.passport.logic.state.State;
import me.combimagnetron.sunscreen.SunscreenLibrary;
import me.combimagnetron.sunscreen.event.TextInputFinishedEvent;
import me.combimagnetron.sunscreen.neo.MenuTemplate;
import me.combimagnetron.sunscreen.neo.MenuRoot;
import me.combimagnetron.sunscreen.neo.element.Elements;
import me.combimagnetron.sunscreen.neo.element.impl.text.TextBoxElement;
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
import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.passport.util.math.Vec2i;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class TestModernTemplate implements MenuTemplate {

    @Override
    public @NotNull Identifier identifier() {
        return null;
    }

    @Override
    public void build(@NotNull MenuRoot root) {
        //Defining an Element
        MutableState<Component> changingText = State.mutable(Component.text("Hello!"));
        root.element(Elements.label(Identifier.split("test_modern:label"), changingText));
        changingText.state(Component.text("Bye!"));

        //Building an automatically flowing layout (FlowLayout) with the earlier defined element and a new one
        root.element(
                Layout.flow(
                        Selector.filtered(Filter.identifiable(Identifier.Namespace.of("test_modern"))),
                        Elements.image(Identifier.of("test_image"), Canvas.empty(Vec2i.of(100, 200))),
                        Elements.textBox(Identifier.split("test:textbox"))
                                .textInput().state().modify(
                                state -> {
                                    state.observe((old, current) -> SunscreenLibrary.library().logger().debug("Input changed from {} to {}!", old, current));
                                }
                )
        ));


        //Conventional way
        TextBoxElement textBoxElement = Elements.textBox(Identifier.split("test:textbox_2"));
        EventBus.subscribe(TextInputFinishedEvent.class, event -> {
            SunscreenLibrary.library().logger().debug("Input finished and is {}!", event.input().state().value());
        });

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
