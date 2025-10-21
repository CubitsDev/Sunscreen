package me.combimagnetron.sunscreen.neo.graphic;

import me.combimagnetron.passport.util.math.Vec2i;
import org.jetbrains.annotations.NotNull;

/**
 * Represents 9 canvasses, all corners, sides and middle of a rescalable canvas.
 */
public interface NineSlice {

    /**
     * The parts of the nineslice which will be combined to form the final canvas.
     * @return an array with size 9, all corners/middle parts.
     */
    @NotNull Canvas @NotNull [] parts();

    /**
     * Combines the parts from {@link NineSlice#parts()} to form a final Canvas.
     * @param size the desired size of the final combined canvas.
     * @return a canvas with combined parts of requested size.
     */
    @NotNull Canvas size(@NotNull Vec2i size);

}
