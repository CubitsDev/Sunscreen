package me.combimagnetron.sunscreen.util.helper;

import me.combimagnetron.passport.internal.entity.metadata.type.Vector3d;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import me.combimagnetron.sunscreen.util.math.Vec2i;

public class HoverHelper {

    public static boolean isHovered(Vector3d cursorTranslation, SunscreenUser<?> viewer, Vec2i position, Vec2i size) {
        Vec2i cursor = ViewportHelper.toScreen(cursorTranslation, viewer.screenSize());
        return isHovered(cursor, position, size);
    }

    public static boolean isHovered(Vec2i cursor, Vec2i position, Vec2i size) {
        return cursor.x() > position.x() && cursor.x() < position.x() + size.x() && cursor.y() > position.y() && cursor.y() < position.y() + size.y();
    }

    public static boolean isHovered(Vec2i cursor, Vec2i position, me.combimagnetron.sunscreen.neo.property.Size size) {
        return cursor.x() > position.x() && cursor.x() < position.x() + size.x().finish(0) && cursor.y() > position.y() && cursor.y() < position.y() + size.y().finish(0);
    }

}
