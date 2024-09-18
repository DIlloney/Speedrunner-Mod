package net.dillon.speedrunnermod.option;

public class DoubleOption {
    private final double defaultValue;
    private double currentValue;

    public DoubleOption(double defaultValue) {
        this.defaultValue = defaultValue;
        this.currentValue = defaultValue;
        OptionManager.addDoubleOption(this);
    }

    public double getCurrentValue() {
        return this.currentValue;
    }

    public double getDefaultValue() {
        return this.defaultValue;
    }

    public void set(int value) {
        this.currentValue = value;
    }

    public void reset() {
        this.currentValue = this.defaultValue;
    }
}