package me.combimagnetron.sunscreen.neo.graphic.shape;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public class Circle implements Shape {
    private final BitSet shape = new BitSet();
    private final Vec2i size;

    protected Circle(int radius) {
        int diameter = radius * 2 + 1;
        this.size = Vec2i.of(diameter, diameter);
        int centerX = radius;
        int centerY = radius;
        for (int y = 0; y < diameter; y++) {
            for (int x = 0; x < diameter; x++) {
                int dx = x - centerX;
                int dy = y - centerY;
                if (dx * dx + dy * dy <= radius * radius) {
                    shape.set(y * diameter + x, true);
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