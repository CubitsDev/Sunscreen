package me.combimagnetron.sunscreen.neo.editor.element;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.cursor.CursorStyle;
import me.combimagnetron.sunscreen.neo.element.GenericInteractableModernElement;
import me.combimagnetron.sunscreen.neo.event.UserMoveStateChangeEvent;
import me.combimagnetron.sunscreen.neo.graphic.Canvas;
import me.combimagnetron.sunscreen.neo.graphic.color.Color;
import me.combimagnetron.sunscreen.neo.graphic.shape.Shape;
import me.combimagnetron.sunscreen.neo.graphic.text.Text;
import me.combimagnetron.sunscreen.neo.graphic.text.style.impl.font.FontProperties;
import me.combimagnetron.sunscreen.neo.input.ListenerReferences;
import me.combimagnetron.sunscreen.neo.input.context.MouseInputContext;
import me.combimagnetron.sunscreen.neo.property.Size;
import me.combimagnetron.sunscreen.neo.registry.Registries;
import me.combimagnetron.sunscreen.neo.render.engine.context.RenderContext;
import me.combimagnetron.sunscreen.util.FileProvider;
import me.combimagnetron.sunscreen.util.helper.HoverHelper;
import me.combimagnetron.sunscreen.util.helper.PropertyHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class PaddingMarginElement extends GenericInteractableModernElement<PaddingMarginElement, Canvas, PaddingMarginElement.PaddingMarginElementListenerReferences> {
    private static final Vec2i SIZE = Vec2i.of(137, 38);
    private static final Canvas SELECTED_PANES = Canvas.file(FileProvider.resource().find("panes_selected.png").toPath());
    private final PaddingMarginElementListenerReferences references = new PaddingMarginElementListenerReferences(this);
    private CursorStyle style = CursorStyle.pointer();
    private Vec2i startPos = null;
    private Section previous = null;
    private Section hovered = null;
    private int[] values = new int[8];

    public PaddingMarginElement(@Nullable Identifier identifier) {
        super(identifier);
        size(Size.fixed(SIZE));
    }

    @Override
    protected void lateInit() {
        super.lateInit();
        input(MouseInputContext.class).listen(this::handleCursor);
    }

    private void handleCursor(@NotNull UserMoveStateChangeEvent event) {
        if (event.user() != inputHandler().user()) return;
        final MouseInputContext context = event.context();
        Vec2i cursor = context.position();
        boolean hover = HoverHelper.in(this, cursor);
        if (hovered == null && style != CursorStyle.pointer()) {
            inputHandler().cursor(CursorStyle.pointer());
            style = CursorStyle.pointer();
        }
        if (!hover) {
            hovered = null;
            startPos = null;
            return;
        }
        Vec2i position = PropertyHelper.vectorOrThrow(position(), Vec2i.class);
        Vec2i relativePos = cursor.sub(position);
        previous = hovered;
        hovered = Section.at(relativePos);
        if (hovered != previous) {
            startPos = null;
        }
        if (startPos == null) {
            startPos = cursor;
            return;
        }
        if (!context.leftPressed()) return;
        inputHandler().cursor(CursorStyle.resizeHorizontal());
        style = CursorStyle.resizeHorizontal();
        int ordinal = hovered.ordinal();
        int delta = startPos.x() - cursor.x();
        values[ordinal] += delta;
        startPos = cursor;
    }

    @Override
    public @NonNull PaddingMarginElementListenerReferences listen() {
        return references;
    }

    @Override
    public @NonNull Canvas render(@NonNull Size property, @Nullable RenderContext context) {
        if (context == null) return Canvas.error(Size.fixed(SIZE));
        final Color top = Color.of(133, 133, 133);//context.theme().colorScheme()
        final Color mid = Color.of(61, 61, 61);
        final Color low = Color.of(39, 39, 39);
        final Color other = Color.of(27, 27, 27);
        final Canvas canvas = Canvas.empty(SIZE);
        canvas.fill(Vec2i.of(1, 1), Vec2i.of(135, 7), top);
        canvas.fill(Vec2i.of(1, 8), Vec2i.of(14, 22), mid);
        canvas.fill(Vec2i.of(122, 8), Vec2i.of(14, 22), mid);
        canvas.fill(Vec2i.of(1, 31), Vec2i.of(135, 6), low);
        canvas.fill(Vec2i.of(15, 8), Vec2i.of(107, 23), other);
        canvas.fill(Vec2i.of(16, 9), Vec2i.of(105, 7), low);
        canvas.fill(Vec2i.of(16, 23), Vec2i.of(105, 7), top);
        canvas.fill(Vec2i.of(16, 16), Vec2i.of(14, 7), mid);
        canvas.fill(Vec2i.of(107, 16), Vec2i.of(14, 7), mid);
        canvas.shape(Shape.filledLine(Vec2i.of(0, 0), Vec2i.of(13, 6)), mid, Vec2i.of(1, 1));
        canvas.shape(Shape.filledLine(Vec2i.of(0, 6), Vec2i.of(13, 0), true), mid, Vec2i.of(1, 30));
        canvas.shape(Shape.filledLine(Vec2i.of(0, 6), Vec2i.of(13, 0)), mid, Vec2i.of(122, 1));
        canvas.shape(Shape.filledLine(Vec2i.of(0, 0), Vec2i.of(13, 6), true), mid, Vec2i.of(122, 30));
        canvas.shape(Shape.filledLine(Vec2i.of(0, 0), Vec2i.of(13, 6)), mid, Vec2i.of(16, 9));
        canvas.shape(Shape.filledLine(Vec2i.of(0, 6), Vec2i.of(13, 0), true), mid, Vec2i.of(16, 23));
        canvas.shape(Shape.filledLine(Vec2i.of(0, 6), Vec2i.of(13, 0)), mid, Vec2i.of(107, 9));
        canvas.shape(Shape.filledLine(Vec2i.of(0, 0), Vec2i.of(13, 6), true), mid, Vec2i.of(107, 23));
        if (hovered != null) canvas.place(hovered.overlay, hovered.position);
        canvas.text(Text.basic("(Content)").font(Registries.fonts().get(Identifier.of("sunscreen", "font/sunburned"))).fontProperties(FontProperties.properties().baseline(-6)), Vec2i.of(48, 17));
        canvas.text(Text.basic(String.valueOf(values[0])).font(Registries.fonts().get(Identifier.of("sunscreen", "font/sunburned"))).fontProperties(FontProperties.properties().baseline(-6)), Vec2i.of(60, 2));
        canvas.text(Text.basic(String.valueOf(values[1])).font(Registries.fonts().get(Identifier.of("sunscreen", "font/sunburned"))).fontProperties(FontProperties.properties().baseline(-6)), Vec2i.of(60, 10));
        return canvas;
    }

    public record PaddingMarginElementListenerReferences(PaddingMarginElement back) implements ListenerReferences<PaddingMarginElement, PaddingMarginElementListenerReferences> {

    }

    public enum Section {
        TOP_OUTSIDE(Vec2i.of(2, 0), SELECTED_PANES.sub(Vec2i.of(0, 0), Vec2i.of(133, 9))),
        TOP_INSIDE(Vec2i.of(17, 8), SELECTED_PANES.sub(Vec2i.of(0, 54), Vec2i.of(103, 9)));
//        BOTTOM_OUTSIDE(),
//        BOTTOM_INSIDE(),
//        LEFT_OUTSIDE(),
//        LEFT_INSIDE(),
//        RIGHT_OUTSIDE(),
//        RIGHT_INSIDE();

        private final Vec2i position;
        private final Canvas overlay;
        Section(Vec2i position, Canvas overlay) {
            this.position = position;
            this.overlay = overlay;
        }

        boolean contains(Vec2i point) {
            Vec2i size = overlay.size();
            return point.x() >= position.x() && point.x() < position.x() + size.x() &&
                point.y() >= position.y() && point.y() < position.y() + size.y();
        }

        static Section at(Vec2i point) {
            for (Section section : values()) {
                if (section.contains(point)) return section;
            }
            return null;
        }

    }

}
