package me.combimagnetron.sunscreen.neo.graphic.shape;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public class RoundedRectangle implements Shape {
    private final BitSet shape = new BitSet();
    private final Vec2i size;

    protected RoundedRectangle(Vec2i size, int cornerRadius) {
        this.size = size;
        int radius = Math.min(cornerRadius, Math.min(size.x(), size.y()) / 2);

        for (int y = 0; y < size.y(); y++) {
            for (int x = 0; x < size.x(); x++) {
                boolean inShape;
                if (x < radius && y < radius) {
                    int dx = radius - x;
                    int dy = radius - y;
                    inShape = dx * dx + dy * dy <= radius * radius;
                } else {
                    int dx = x - (size.x() - radius - 1);
                    if (x >= size.x() - radius && y < radius) {
                        int dy = radius - y;
                        inShape = dx * dx + dy * dy <= radius * radius;
                    } else {
                        int dy = y - (size.y() - radius - 1);
                        if (x < radius && y >= size.y() - radius) {
                            inShape = dx * dx + dy * dy <= radius * radius;
                        } else {
                            inShape = true;
                        }
                    }
                }
                if (!inShape) continue;
                shape.set(y * size.x() + x, true);
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