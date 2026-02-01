package me.combimagnetron.sunscreen.neo.property;

public class Options {
    private boolean adaptPropertiesToScale = true;
    private boolean requiresRelativeProperties = false;
    private boolean preloadRelativeProperties = false;
    private int refreshRate = 20;

    public Options adapatPropertiesToScale(boolean adaptPropertiesToScale) {
        this.adaptPropertiesToScale = adaptPropertiesToScale;
        return this;
    }

    public Options requiresRelativeProperties(boolean requiresRelativeProperties) {
        this.requiresRelativeProperties = requiresRelativeProperties;
        return this;
    }

    public Options preloadRelativeProperties(boolean preloadRelativeProperties) {
        this.preloadRelativeProperties = preloadRelativeProperties;
        return this;
    }

    public Options refreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
        return this;
    }

    public boolean adaptPropertiesToScale() {
        return adaptPropertiesToScale;
    }

    public boolean requiresRelativeProperties() {
        return requiresRelativeProperties;
    }

    public boolean preloadRelativePropeties() {
        return preloadRelativeProperties;
    }

    public int refreshRate() {
        return refreshRate;
    }
}
