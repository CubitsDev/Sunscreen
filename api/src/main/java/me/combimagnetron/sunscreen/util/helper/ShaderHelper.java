package me.combimagnetron.sunscreen.util.helper;

import me.combimagnetron.sunscreen.ui.property.Position;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.sunscreen.util.math.Vec2i;

public class ShaderHelper {

    public static int encode(Position position, SunscreenUser<?> user) {
        Vec2i screenSize = user.screenSize().pixel();
        int x = decimals((double) position.x().finish(0) /screenSize.x());
        int y = decimals((double) position.y().finish(0) /screenSize.y());
        x = Math.max(0, Math.min(153, x));
        y = Math.max(0, Math.min(153, y));
        return x * 154 + y;
    }

    public static int decimals(double fraction) {
        fraction = Math.max(0.0, Math.min(1.0, fraction));
        return (int) Math.floor(fraction * 100.0);
    }

}
