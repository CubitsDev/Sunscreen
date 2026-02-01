package me.combimagnetron.sunscreen.util.helper;

import me.combimagnetron.passport.internal.entity.metadata.type.Vector3d;
import me.combimagnetron.sunscreen.neo.property.Position;
import me.combimagnetron.passport.util.math.Vec2i;

public class ViewportHelper {

    /**
     * Converts a Vector3d to a Vec2d by supplying a Vec2d with the x and y values.
     * @param vector3d The Vector3d to convert.
     * @return A Vec2d with the x and y values of the Vector3d.
     */
    public static Vec2i fromVector3d(Vector3d vector3d) {
        return Vec2i.of((int) vector3d.x(), (int) vector3d.y());
    }

    public static Vec2i fromPosition(Position position) {
        return Vec2i.of(position.x().finish(0), position.y().finish(0));
    }

}
