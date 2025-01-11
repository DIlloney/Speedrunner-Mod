package net.dillon.speedrunnermod.client.util;

/**
 * Determines the type of button on an option screen.
 * <p>{@code left} is a button on the left side (index 0), where there are two buttons on a singular row.</p>
 * <p>{@code right} is a button on the right side (index 1).</p>
 * <p>{@code large} is a button in the "middle", or one that takes up an entire row, but has an index of 0.</p>
 */
public enum ButtonSide {
    LEFT(0),
    RIGHT(1),
    LARGE(0);

    private final int index;

    ButtonSide(int index) {
        this.index = index;
    }

    /**
     * Returns the index of a button type.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Returns the index value for each button side.
     */
    public static int buttonIndexes(ButtonSide buttonSide) {
        return buttonSide == ButtonSide.LEFT ? ButtonSide.LEFT.getIndex() : buttonSide == ButtonSide.RIGHT ? ButtonSide.RIGHT.getIndex() : ButtonSide.LARGE.getIndex();
    }
}