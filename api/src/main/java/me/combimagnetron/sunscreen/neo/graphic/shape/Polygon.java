package me.combimagnetron.sunscreen.neo.graphic.shape;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;
import java.util.List;

public class Polygon implements Shape {
    private final BitSet shape = new BitSet();
    private final Vec2i size;

    protected Polygon(List<Vec2i> vertices) {
        if (vertices.size() < 3) {
            throw new IllegalArgumentException("Polygon must have at least 3 vertices");
        }
        int minX = vertices.stream().mapToInt(Vec2i::x).min().orElse(0);
        int minY = vertices.stream().mapToInt(Vec2i::y).min().orElse(0);
        int maxX = vertices.stream().mapToInt(Vec2i::x).max().orElse(0);
        int maxY = vertices.stream().mapToInt(Vec2i::y).max().orElse(0);
        
        this.size = Vec2i.of(maxX - minX + 1, maxY - minY + 1);
        Vec2i[] localVertices = vertices.stream()
            .map(v -> Vec2i.of(v.x() - minX, v.y() - minY))
            .toArray(Vec2i[]::new);
        for (int y = 0; y < size.y(); y++) {
            for (int x = 0; x < size.x(); x++) {
                if (isInsidePolygon(x, y, localVertices)) {
                    shape.set(y * size.x() + x, true);
                }
            }
        }
    }
    
    private boolean isInsidePolygon(int x, int y, Vec2i[] vertices) {
        boolean inside = false;
        int j = vertices.length - 1;
        
        for (int i = 0; i < vertices.length; i++) {
            if ((vertices[i].y() > y) != (vertices[j].y() > y) &&
                x < (vertices[j].x() - vertices[i].x()) * (y - vertices[i].y()) / 
                    (vertices[j].y() - vertices[i].y()) + vertices[i].x()) {
                inside = !inside;
            }
            j = i;
        }
        
        return inside;
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