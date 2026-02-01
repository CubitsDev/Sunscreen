package me.combimagnetron.sunscreen.neo.graphic.shape;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public class Ellipse implements Shape {
    private final BitSet shape = new BitSet();
    private final Vec2i size;

    protected Ellipse(int radiusX, int radiusY) {
        int width = radiusX * 2 + 1;
        int height = radiusY * 2 + 1;
        this.size = Vec2i.of(width, height);
        int centerX = radiusX;
        int centerY = radiusY;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int dx = x - centerX;
                int dy = y - centerY;
                double normalized = (double)(dx * dx) / (radiusX * radiusX) + (double)(dy * dy) / (radiusY * radiusY);
                if (normalized <= 1.0) {
                    shape.set(y * width + x, true);
                }
            }
        }
    }

    @Override
    public @NotNull Vec2i squareSize() {
        return size;
    }

    @Override
    public @NotNull BitSet shape() {
        return shape;
    }
}