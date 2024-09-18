package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.item.ModFoodComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Modifies {@code vanilla food items} to be better.
 * <p>See {@link ModFoodComponents} for more.</p>
 */
@Mixin(FoodComponents.class)
public class FoodComponentsMixin {
    @Shadow
    private static final FoodComponent APPLE, BAKED_POTATO, BEEF, BEETROOT, BREAD, CARROT, CHICKEN , CHORUS_FRUIT, COD, COOKED_BEEF, COOKED_CHICKEN, COOKED_COD, COOKED_MUTTON, COOKED_PORKCHOP, COOKED_RABBIT, COOKED_SALMON, COOKIE, DRIED_KELP, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE, GOLDEN_CARROT, HONEY_BOTTLE, MELON_SLICE, MUTTON, POISONOUS_POTATO, PORKCHOP, POTATO, PUFFERFISH, PUMPKIN_PIE, RABBIT, ROTTEN_FLESH, SALMON, SPIDER_EYE, SWEET_BERRIES, GLOW_BERRIES, TROPICAL_FISH;

    static {
        APPLE = food(ModFoodComponents.APPLE, FoodComponents.APPLE);
        BAKED_POTATO = food(ModFoodComponents.BAKED_POTATO, FoodComponents.BAKED_POTATO);
        BEEF = food(ModFoodComponents.BEEF, FoodComponents.BEEF);
        BEETROOT = food(ModFoodComponents.BEETROOT, FoodComponents.BEETROOT);
        BREAD = food(ModFoodComponents.BREAD, FoodComponents.BREAD);
        CARROT = food(ModFoodComponents.CARROT, FoodComponents.CARROT);
        CHICKEN = food(ModFoodComponents.CHICKEN, FoodComponents.CHICKEN);
        CHORUS_FRUIT = food(ModFoodComponents.CHORUS_FRUIT, FoodComponents.CHORUS_FRUIT);
        COD = food(ModFoodComponents.COD, FoodComponents.COD);
        COOKED_BEEF = food(ModFoodComponents.COOKED_BEEF, FoodComponents.COOKED_BEEF);
        COOKED_CHICKEN = food(ModFoodComponents.COOKED_CHICKEN, FoodComponents.COOKED_CHICKEN);
        COOKED_COD = food(ModFoodComponents.COOKED_COD, FoodComponents.COOKED_COD);
        COOKED_MUTTON = food(ModFoodComponents.COOKED_MUTTON, FoodComponents.COOKED_MUTTON);
        COOKED_PORKCHOP = food(ModFoodComponents.COOKED_PORKCHOP, FoodComponents.COOKED_PORKCHOP);
        COOKED_RABBIT = food(ModFoodComponents.COOKED_RABBIT, FoodComponents.COOKED_RABBIT);
        COOKED_SALMON = food(ModFoodComponents.COOKED_SALMON, FoodComponents.COOKED_SALMON);
        COOKIE = food(ModFoodComponents.COOKIE, FoodComponents.COOKIE);
        DRIED_KELP = food(ModFoodComponents.DRIED_KELP, FoodComponents.DRIED_KELP);
        ENCHANTED_GOLDEN_APPLE = food(ModFoodComponents.ENCHANTED_GOLDEN_APPLE, FoodComponents.ENCHANTED_GOLDEN_APPLE);
        GOLDEN_APPLE = food(ModFoodComponents.GOLDEN_APPLE, FoodComponents.GOLDEN_APPLE);
        GOLDEN_CARROT = food(ModFoodComponents.GOLDEN_CARROT, FoodComponents.GOLDEN_CARROT);
        HONEY_BOTTLE = food(ModFoodComponents.HONEY_BOTTLE, FoodComponents.HONEY_BOTTLE);
        MELON_SLICE = food(ModFoodComponents.MELON_SLICE, FoodComponents.MELON_SLICE);
        MUTTON = food(ModFoodComponents.MUTTON, FoodComponents.MUTTON);
        POISONOUS_POTATO = food(ModFoodComponents.POISONOUS_POTATO, FoodComponents.POISONOUS_POTATO);
        PORKCHOP = food(ModFoodComponents.PORKCHOP, FoodComponents.PORKCHOP);
        POTATO = food(ModFoodComponents.POTATO, FoodComponents.POTATO);
        PUFFERFISH = food(ModFoodComponents.PUFFERFISH, FoodComponents.PUFFERFISH);
        PUMPKIN_PIE = food(ModFoodComponents.PUMPKIN_PIE, FoodComponents.PUMPKIN_PIE);
        RABBIT = food(ModFoodComponents.RABBIT, FoodComponents.RABBIT);
        ROTTEN_FLESH = food(ModFoodComponents.ROTTEN_FLESH, FoodComponents.ROTTEN_FLESH);
        SALMON = food(ModFoodComponents.SALMON, FoodComponents.SALMON);
        SPIDER_EYE = food(ModFoodComponents.SPIDER_EYE, FoodComponents.SPIDER_EYE);
        SWEET_BERRIES = food(ModFoodComponents.SWEET_BERRIES, FoodComponents.SWEET_BERRIES);
        GLOW_BERRIES = food(ModFoodComponents.GLOW_BERRIES, FoodComponents.GLOW_BERRIES);
        TROPICAL_FISH = food(ModFoodComponents.TROPICAL_FISH, FoodComponents.TROPICAL_FISH);
    }

    /**
     * Returns the {@code modified food component} and the {@code vanilla food component} based on if the {@code better foods} option is enabled.
     */
    @Unique
    private static FoodComponent food(FoodComponent mod, FoodComponent vanilla) {
        return options().main.betterFoods.getCurrentValue() ? mod : vanilla;
    }
}