package me.combimagnetron.sunscreen.neo.graphic.animated.easing.impl;

import me.combimagnetron.sunscreen.neo.graphic.animated.easing.Easing;

public class EaseInSine implements Easing {

    @Override
    public double step(double time) {
        if (!Easing.valid(time)) return 0;
        return 1 - Math.cos((time * Math.PI) / 2);
    }

}
