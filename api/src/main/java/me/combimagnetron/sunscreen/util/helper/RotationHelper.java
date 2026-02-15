package me.combimagnetron.sunscreen.util.helper;

import me.combimagnetron.passport.util.math.Vec2f;
import me.combimagnetron.passport.util.math.Vec2i;
import me.combimagnetron.sunscreen.neo.render.ScreenInfo;
import me.combimagnetron.sunscreen.neo.render.Viewport;
import org.jetbrains.annotations.NotNull;

public class RotationHelper {
    private final static float LESS_ACCURATE_PI = 3.14159265359f;
    private final static float CURSOR_SIZE = 0.5f;

    public static @NotNull Vec2i convert(float yawDegrees, float pitchDegrees, @NotNull ScreenInfo screenSize) {
        final Viewport viewport = screenSize.viewport();
        final Vec2i viewportVec = viewport.currentView();

        float pitch = (float) Math.toRadians(pitchDegrees);
        float yaw = (float) Math.toRadians(yawDegrees);

        float cursorSize = 1.0f / CURSOR_SIZE;
        Vec2f pos = Vec2f.of(yaw / LESS_ACCURATE_PI * 1.5f - 0.048f, (pitch / LESS_ACCURATE_PI * 7.0f) + 0.3825f).mul(cursorSize);

        float aspect = (float) viewportVec.x() / viewportVec.y();
        float scale = 0.05f;

        Vec2f offset = Vec2f.of(-2.15f, 3.0f);
        Vec2f rotatedOffset = Vec2f.of(offset.y(), -offset.x());

        pos = pos.add(Vec2f.of(aspect, 1.0f).mul(scale).mul(rotatedOffset));

        Vec2f ndc = pos.div(cursorSize);

        int screenX = Math.round((ndc.x() + 1.0f) * 0.5f * viewportVec.x()) + viewport.position().x();
        int screenY = Math.round((ndc.y() + 3.0f) * 0.25f * viewportVec.y()) + viewport.position().y();

        return new Vec2i(screenX - 34, screenY + 7);
    }

}
