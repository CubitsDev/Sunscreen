package me.combimagnetron.sunscreen.neo.render.engine.encode.strategy;

public interface EncodeStrategy {

    int maxUniqueColorAmount();

    boolean lossless();

}
