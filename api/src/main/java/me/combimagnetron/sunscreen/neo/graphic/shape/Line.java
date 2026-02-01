package me.combimagnetron.sunscreen.neo.graphic.shape;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public class Line implements Shape {
    private final BitSet shape = new BitSet();
    private final Vec2i size;

    protected Line(Vec2i start, Vec2i end, int thickness) {
        int minX = Math.min(start.x(), end.x());
        int minY = Math.min(start.y(), end.y());
        int maxX = Math.max(start.x(), end.x());
        int maxY = Math.max(start.y(), end.y());

        int padding = thickness / 2;
        this.size = Vec2i.of(maxX - minX + 1 + padding * 2, maxY - minY + 1 + padding * 2);

        int x0 = start.x() - minX + padding;
        int y0 = start.y() - minY + padding;
        int x1 = end.x() - minX + padding;
        int y1 = end.y() - minY + padding;

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        int x = x0;
        int y = y0;

        while (true) {
            drawThickPoint(x, y, thickness);

            if (x == x1 && y == y1) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }

    private void drawThickPoint(int centerX, int centerY, int thickness) {
        int radius = thickness / 2;
        for (int dy = -radius; dy <= radius; dy++) {
            for (int dx = -radius; dx <= radius; dx++) {
                if (dx * dx + dy * dy <= radius * radius) {
                    int px = centerX + dx;
                    int py = centerY + dy;
                    if (px >= 0 && px < size.x() && py >= 0 && py < size.y()) {
                        shape.set(py * size.x() + px, true);
                    }
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