package me.combimagnetron.sunscreen.neo.graphic;

import me.combimagnetron.passport.util.math.Vec2i;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Represents 9 canvasses, all corners, sides and middle of a rescalable canvas.
 */
public final class NineSlice {
    private final Canvas canvas;
    private final Canvas[] parts = new Canvas[9];
    private final Vec2i[] partSizes = new Vec2i[4];

    public static @NotNull NineSlice nineSlice(@NotNull Canvas canvas) {
        return new NineSlice(canvas);
    }

    public static @NotNull NineSlice nineSlice(@NotNull Canvas canvas, @NotNull Vec2i... corners) {
        return new NineSlice(canvas, List.of(corners));
    }

    private NineSlice(@NotNull Canvas canvas) {
        this.canvas = canvas;
        final Vec2i size = canvas.size();
        int xSize = size.x()/3;
        int ySize = size.y()/3;
        Arrays.fill(partSizes, Vec2i.of(xSize, ySize));
        cutPieces();
    }

    private NineSlice(@NotNull Canvas canvas, @NotNull List<Vec2i> sizes) {
        this.canvas = canvas;
        if (sizes.size() > 3) throw new IllegalArgumentException("Size of NineSlice sizes cannot be larger than 4.");
        for (int i = 0; i < sizes.size(); i++) {
            Vec2i size = sizes.get(i);
            partSizes[i] = size;
        }
        partSizes[3] = Vec2i.of(partSizes[1].x(), partSizes[2].y());
        cutPieces();
    }

    private void cutPieces() {
        Vec2i position = Vec2i.zero();
        for (int i = 0; i < 9; i++) {
            Vec2i size = pieceSizeByIndex(i);
            parts[i] = canvas.sub(position, size);
            if (i == 2 || i == 5) {
                position = position.mul(0, 1).add(0, size.y());
            }
        }
    }

    private @NotNull Vec2i pieceSizeByIndex(int i) {
        return switch (i) {
            case 0, 2, 6, 8 -> partSizes[0];
            case 1, 7 -> partSizes[1];
            case 3, 5 -> partSizes[2];
            case 4 -> partSizes[3];
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };
    }

    /**
     * The parts of the nineslice which will be combined to form the final canvas.
     * @return an array with size 9, all corners/middle parts.
     */
    public @NotNull Canvas @NotNull [] parts() {
        return parts;
    }

    /**
     * Combines the parts from {@link NineSlice#parts()} to form a final Canvas.
     * @param size the desired size of the final combined canvas.
     * @return a canvas with combined parts of requested size.
     */
    public @NotNull Canvas size(@NotNull Vec2i size) {
        Canvas construct = Canvas.empty(size);
        Vec2i corner = partSizes[0];
        Vec2i topBottomEdge = partSizes[1];
        Vec2i leftRightEdge = partSizes[2];
        Vec2i middle = partSizes[3];
        int middleWidth = size.x() - corner.x() * 2;
        int middleHeight = size.y() - corner.y() * 2;
        if (middleWidth < 0 || middleHeight < 0) {
            throw new IllegalArgumentException("Target size is too small for the nine-slice corners");
        }
        BufferedColorSpace target = construct.bufferedColorSpace();
        target.place(parts[0].bufferedColorSpace(), 0, 0);
        for (int x = 0; x < middleWidth; x += topBottomEdge.x()) {
            int width = Math.min(topBottomEdge.x(), middleWidth - x);
            Canvas tile = parts[1].sub(Vec2i.zero(), Vec2i.of(width, topBottomEdge.y()));
            target.place(tile.bufferedColorSpace(), corner.x() + x, 0);
        }
        target.place(parts[2].bufferedColorSpace(), corner.x() + middleWidth, 0);
        for (int y = 0; y < middleHeight; y += leftRightEdge.y()) {
            int height = Math.min(leftRightEdge.y(), middleHeight - y);
            Canvas tile = parts[3].sub(Vec2i.zero(), Vec2i.of(leftRightEdge.x(), height));
            target.place(tile.bufferedColorSpace(), 0, corner.y() + y);
        }
        for (int y = 0; y < middleHeight; y += middle.y()) {
            int height = Math.min(middle.y(), middleHeight - y);
            for (int x = 0; x < middleWidth; x += middle.x()) {
                int width = Math.min(middle.x(), middleWidth - x);
                Canvas tile = parts[4].sub(Vec2i.zero(), Vec2i.of(width, height));
                target.place(tile.bufferedColorSpace(), corner.x() + x, corner.y() + y);
            }
        }
        for (int y = 0; y < middleHeight; y += leftRightEdge.y()) {
            int height = Math.min(leftRightEdge.y(), middleHeight - y);
            Canvas tile = parts[5].sub(Vec2i.zero(), Vec2i.of(leftRightEdge.x(), height));
            target.place(tile.bufferedColorSpace(), corner.x() + middleWidth, corner.y() + y);
        }
        target.place(parts[6].bufferedColorSpace(), 0, corner.y() + middleHeight);
        for (int x = 0; x < middleWidth; x += topBottomEdge.x()) {
            int width = Math.min(topBottomEdge.x(), middleWidth - x);
            Canvas tile = parts[7].sub(Vec2i.zero(), Vec2i.of(width, topBottomEdge.y()));
            target.place(tile.bufferedColorSpace(), corner.x() + x, corner.y() + middleHeight);
        }
        target.place(parts[8].bufferedColorSpace(), corner.x() + middleWidth, corner.y() + middleHeight);

        return construct;
    }

}
