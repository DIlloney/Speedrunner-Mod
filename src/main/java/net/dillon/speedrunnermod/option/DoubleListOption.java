package net.dillon.speedrunnermod.option;

public class DoubleListOption {
    private final double[] defaultList;
    private double[] currentList;

    public DoubleListOption(double[] defaultList) {
        this.defaultList = defaultList;
        this.currentList = defaultList;
        OptionManager.addDoubleListOption(this);
    }

    public double getAtIndex(int index) {
        return this.currentList[index];
    }

    public double[] getDefaultList() {
        return this.defaultList;
    }

    public void set(double[] newList) {
        this.currentList = newList;
    }

    public void reset() {
        this.currentList = this.defaultList;
    }
}