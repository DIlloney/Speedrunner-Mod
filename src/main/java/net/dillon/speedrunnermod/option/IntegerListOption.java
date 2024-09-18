package net.dillon.speedrunnermod.option;

public class IntegerListOption {
    private final int[] defaultList;
    private int[] currentList;

    public IntegerListOption(int[] defaultList) {
        this.defaultList = defaultList;
        this.currentList = defaultList;
        OptionManager.addIntegerListOption(this);
    }

    public int getAtIndex(int index) {
        return this.currentList[index];
    }

    public void setAtIndex(int[] newList) {
        this.currentList = newList;
    }

    public void reset() {
        this.currentList = this.defaultList;
    }
}