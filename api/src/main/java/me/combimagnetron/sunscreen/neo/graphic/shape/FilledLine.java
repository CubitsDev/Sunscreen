package me.combimagnetron.sunscreen.neo.graphic.shape;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.BitSet;

public class FilledLine implements Shape {
    private final BitSet shape = new BitSet();
    private final Vec2i size;

    protected FilledLine(Vec2i start, Vec2i end, boolean fillAbove) {
        int minX = Math.min(start.x(), end.x());
        int minY = Math.min(start.y(), end.y());
        int maxX = Math.max(start.x(), end.x());
        int maxY = Math.max(start.y(), end.y());

        this.size = Vec2i.of(maxX - minX + 1, maxY - minY + 1);

        int x0 = start.x() - minX;
        int y0 = start.y() - minY;
        int x1 = end.x() - minX;
        int y1 = end.y() - minY;

        int[] lineY = new int[size.x()];
        Arrays.fill(lineY, -1);

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        int x = x0;
        int y = y0;

        while (true) {
            if (x >= 0 && x < size.x()) {
                if (lineY[x] == -1 || (fillAbove ? y < lineY[x] : y > lineY[x])) {
                    lineY[x] = y;
                }
            }

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

        for (int px = 0; px < size.x(); px++) {
            if (lineY[px] != -1) {
                if (fillAbove) {
                    for (int py = 0; py <= lineY[px]; py++) {
                        shape.set(py * size.x() + px, true);
                    }
                } else {
                    for (int py = lineY[px]; py < size.y(); py++) {
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