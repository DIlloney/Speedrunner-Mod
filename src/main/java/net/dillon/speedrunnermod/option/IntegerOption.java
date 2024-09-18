package net.dillon.speedrunnermod.option;

public class IntegerOption {
    private final int defaultValue;
    public int currentValue;

    public IntegerOption(int defaultValue) {
        this.defaultValue = defaultValue;
        OptionManager.addIntegerOption(this);
    }

    public int getCurrentValue() {
        return this.currentValue;
    }

    public int getDefaultValue() {
        return this.defaultValue;
    }

    public void set(int value) {
        this.currentValue = value;
    }

    public void reset() {
        this.currentValue = this.defaultValue;
        ModOptions.saveConfig();
    }
}