package me.combimagnetron.sunscreen.menu;

import me.combimagnetron.passport.internal.network.ByteBuffer;
import me.combimagnetron.passport.util.data.Pair;
import me.combimagnetron.passport.util.math.Vec2d;
import me.combimagnetron.passport.util.math.Vec2i;

import java.util.*;

public record ScreenSize(Vec2i pixel, Pair<Vec2d, Vec2d> coordinates) {

    public static ScreenSize of(Vec2i pixel, Pair<Vec2d, Vec2d> coordinates) {
        return new ScreenSize(pixel, coordinates);
    }

    public static ScreenSize fromBytes(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.of(bytes);
        return new ScreenSize(new Vec2i(buffer.read(ByteBuffer.Adapter.INT), buffer.read(ByteBuffer.Adapter.INT)), new Pair<>(new Vec2d(buffer.read(ByteBuffer.Adapter.DOUBLE), buffer.read(ByteBuffer.Adapter.DOUBLE)), new Vec2d(buffer.read(ByteBuffer.Adapter.DOUBLE), buffer.read(ByteBuffer.Adapter.DOUBLE))));
    }

    public byte[] bytes() {
        ByteBuffer buffer = ByteBuffer.empty();
        buffer.write(ByteBuffer.Adapter.INT, pixel.x()).write(ByteBuffer.Adapter.INT, pixel.y()).write(ByteBuffer.Adapter.DOUBLE, coordinates.k().x()).write(ByteBuffer.Adapter.DOUBLE, coordinates.k().y()).write(ByteBuffer.Adapter.DOUBLE, coordinates.v().x()).write(ByteBuffer.Adapter.DOUBLE, coordinates.v().y());
        return buffer.bytes();
    }

}
