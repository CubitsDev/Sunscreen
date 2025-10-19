package me.combimagnetron.sunscreen.neo.graphic.shape;

import me.combimagnetron.sunscreen.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public interface Shape {

    @NotNull Vec2i squareSize();

    @NotNull BitSet shape();

}
