package me.combimagnetron.sunscreen.neo.graphic.shape;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public class Triangle implements Shape {
    private final BitSet shape = new BitSet();
    private final Vec2i size;

    protected Triangle(Vec2i size) {
        this.size = size;
        for (int y = 0; y < size.y(); y++) {
            int rowWidth = (int)((y + 1) * (double)size.x() / size.y());
            int startX = (size.x() - rowWidth) / 2;
            int endX = startX + rowWidth;
            
            for (int x = startX; x < endX && x < size.x(); x++) {
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