package me.combimagnetron.sunscreen.neo.graphic.shape;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;
import java.util.List;

public interface Shape {

    @NotNull Vec2i squareSize();

    @NotNull BitSet shape();

    static @NotNull Rectangle rectangle(@NotNull Vec2i size) {
        return new Rectangle(size);
    }

    static @NotNull Circle circle(int radius) {
        return new Circle(radius);
    }

    static @NotNull Ellipse ellipse(int radiusX, int radiusY) {
        return new Ellipse(radiusX, radiusY);
    }

    static @NotNull Triangle triangle(@NotNull Vec2i size) {
        return new Triangle(size);
    }

    static @NotNull Line line(@NotNull Vec2i start, @NotNull Vec2i end) {
        return line(start, end, 1);
    }

    static @NotNull Line line(@NotNull Vec2i start, @NotNull Vec2i end, int thickness) {
        return new Line(start, end, thickness);
    }

    static @NotNull Polygon polygon(@NotNull Vec2i @NotNull... vertices) {
        return new Polygon(List.of(vertices));
    }

    static @NotNull RoundedRectangle roundedRectangle(@NotNull Vec2i size, int cornerRadius) {
        return new RoundedRectangle(size, cornerRadius);
    }

}
