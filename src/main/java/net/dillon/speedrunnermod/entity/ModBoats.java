package net.dillon.speedrunnermod.entity;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Used to create all the Speedrunner Mod {@code boats,} and these keys are used in several different mixins.
 * <p>See {@code directory} {@link net.dillon.speedrunnermod.mixin.main.entity.boat} for more.</p>
 */
public class ModBoats {
    public static final Identifier SPEEDRUNNER = ofSpeedrunnerMod("speedrunner");
    public static final Identifier DEAD_SPEEDRUNNER = ofSpeedrunnerMod("dead_speedrunner");
    public static final Identifier CRIMSON = ofSpeedrunnerMod("crimson");
    public static final Identifier WARPED = ofSpeedrunnerMod("warped");

    /**
     * Determines {@code "fireproof"} boats.
     */
    public static boolean isFireproofBoat(Supplier<Item> itemSupplier) {
        return itemSupplier.get().getDefaultStack().isIn(ModItemTags.FIREPROOF_BOATS) || itemSupplier.get().getDefaultStack().isIn(ModItemTags.FIREPROOF_CHEST_BOATS);
    }

    /**
     * <p>Determines {@code "faster"} boats, boats that ride slightly faster than normal boats.
     */
    public static boolean isFastBoat(Supplier<Item> itemSupplier) {
        return itemSupplier.get().getDefaultStack().isIn(ModItemTags.FASTER_BOATS) || itemSupplier.get().getDefaultStack().isIn(ModItemTags.FASTER_CHEST_BOATS);
    }

    /**
     * Initializes all {@code Speedrunner Mod boats.}
     */
    public static void initializeBoats() {
    }
}