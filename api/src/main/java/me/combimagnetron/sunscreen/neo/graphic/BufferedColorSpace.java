package me.combimagnetron.sunscreen.neo.graphic;

import me.combimagnetron.sunscreen.neo.graphic.color.Color;
import me.combimagnetron.sunscreen.neo.graphic.color.ColorLike;
import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

public final class BufferedColorSpace {
    private final int[] buffer;
    private final Vec2i size;

    public BufferedColorSpace(Vec2i size) {
        this.buffer = new int[size.x() * size.y()];
        this.size = size;
    }

    public @NotNull ColorLike at(Vec2i pixel) {
        return Color.of(buffer[pixelIndex(pixel, size)]);
    }

    public void fill(Vec2i start, Vec2i end, ColorLike colorLike) {
        int width = end.x() - start.x();
        int height = end.y() - start.y();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (start.x() + width > size.x() || start.y() + height > size.y()) return;
                color(x, y, colorLike);
            }
        }
    }

    public void place(BufferedColorSpace bufferedColorSpace, Vec2i position) {

    }

    public void erase(int x, int y) {
        buffer[pixelIndex(x, y, size.x())] = 0;
    }

    public void erase(Vec2i pixel) {
        erase(pixel.x(), pixel.y());
    }

    public void colorDirect(int index, ColorLike colorLike) {
        if (colorLike.alpha() == 0) {
        }
    }

    public void color(int x, int y, ColorLike colorLike) {
        colorDirect(pixelIndex(x, y, size.x()), colorLike);
    }

    public void color(Vec2i pixel, ColorLike colorLike) {
        color(pixel.x(), pixel.y(), colorLike);
    }

    public static int pixelIndex(Vec2i pixel, Vec2i size) {
        return pixelIndex(pixel.x(), pixel.y(), size.x());
    }

    private static int pixelIndex(int x, int y, int width) {
        return x + y * width;
    }

    public int[] buffer() {
        return buffer;
    }

    public Vec2i size() {
        return size;
    }
}
