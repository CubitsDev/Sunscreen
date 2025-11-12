package me.combimagnetron.sunscreen.neo.graphic.animated.easing;

import me.combimagnetron.passport.util.data.Range;

public interface Easing {
    Range<Double> RANGE = Range.of(0d, 1d);

    double step(double time);

    static boolean valid(double time) {
        return RANGE.in(time);
    }

}
