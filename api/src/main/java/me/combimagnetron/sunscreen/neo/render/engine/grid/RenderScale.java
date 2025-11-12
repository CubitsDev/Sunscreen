package me.combimagnetron.sunscreen.neo.render.engine.grid;

public enum RenderScale {
    X8(0.125), X4(0.25), X2(1.25), X1(1.5), X0_5(2), X0_25(4), X0_125(8);

    private final double coefficient;

    RenderScale(double coefficient) {
        this.coefficient = coefficient;
    }

    public double coefficient() {
        return coefficient;
    }
}
