package net.dillon.speedrunnermod.option;

public class BooleanOption {
    private final boolean defaultValue;
    private boolean currentValue;

    public BooleanOption(boolean defaultValue) {
        this.defaultValue = defaultValue;
        this.currentValue = defaultValue;
        OptionManager.addBooleanOption(this);
    }

    public boolean getCurrentValue() {
        return this.currentValue;
    }

    public boolean getDefaultValue() {
        return this.defaultValue;
    }

    public void set(boolean bl) {
        this.currentValue = bl;
    }

    public void invert() {
        this.currentValue = !this.currentValue;
    }

    public void reset() {
        this.currentValue = this.defaultValue;
    }
}