package net.dillon8775.speedrunnermod.mixin.main.item;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.item.ModFoodComponents;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Replaces the vanilla Minecraft food items with better foods.
 * <p>See {@link ModFoodComponents}.</p>
 */
@Mixin(FoodComponents.class)
public class BetterFoods {
    @Shadow
    private static final FoodComponent APPLE, BAKED_POTATO, BEEF, BEETROOT, BREAD, CARROT, CHICKEN , CHORUS_FRUIT, COD, COOKED_BEEF, COOKED_CHICKEN, COOKED_COD, COOKED_MUTTON, COOKED_PORKCHOP, COOKED_RABBIT, COOKED_SALMON, COOKIE, DRIED_KELP, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE, GOLDEN_CARROT, HONEY_BOTTLE, MELON_SLICE, MUTTON, POISONOUS_POTATO, PORKCHOP, POTATO, PUFFERFISH, PUMPKIN_PIE, RABBIT, ROTTEN_FLESH, SALMON, SPIDER_EYE, SWEET_BERRIES, GLOW_BERRIES, TROPICAL_FISH;

    static {
        APPLE = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.APPLE : FoodComponents.APPLE;
        BAKED_POTATO = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.BAKED_POTATO : FoodComponents.BAKED_POTATO;
        BEEF = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.BEEF : FoodComponents.BEEF;
        BEETROOT = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.BEETROOT : FoodComponents.BEETROOT;
        BREAD = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.BREAD : FoodComponents.BREAD;
        CARROT = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.CARROT : FoodComponents.CARROT;
        CHICKEN = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.CHICKEN : FoodComponents.CHICKEN;
        CHORUS_FRUIT = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.CHORUS_FRUIT : FoodComponents.CHORUS_FRUIT;
        COD = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.COD : FoodComponents.COD;
        COOKED_BEEF = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.COOKED_BEEF : FoodComponents.COOKED_BEEF;
        COOKED_CHICKEN = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.COOKED_CHICKEN : FoodComponents.COOKED_CHICKEN;
        COOKED_COD = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.COOKED_COD : FoodComponents.COOKED_COD;
        COOKED_MUTTON = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.COOKED_MUTTON : FoodComponents.COOKED_MUTTON;
        COOKED_PORKCHOP = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.COOKED_PORKCHOP : FoodComponents.COOKED_PORKCHOP;
        COOKED_RABBIT = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.COOKED_RABBIT : FoodComponents.COOKED_RABBIT;
        COOKED_SALMON = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.COOKED_SALMON : FoodComponents.COOKED_SALMON;
        COOKIE = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.COOKIE : FoodComponents.COOKIE;
        DRIED_KELP = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.DRIED_KELP : FoodComponents.DRIED_KELP;
        ENCHANTED_GOLDEN_APPLE = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.ENCHANTED_GOLDEN_APPLE : FoodComponents.ENCHANTED_GOLDEN_APPLE;
        GOLDEN_APPLE = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.GOLDEN_APPLE : FoodComponents.GOLDEN_APPLE;
        GOLDEN_CARROT = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.GOLDEN_CARROT : FoodComponents.GOLDEN_CARROT;
        HONEY_BOTTLE = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.HONEY_BOTTLE : FoodComponents.HONEY_BOTTLE;
        MELON_SLICE = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.MELON_SLICE : FoodComponents.MELON_SLICE;
        MUTTON = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.MUTTON : FoodComponents.MUTTON;
        POISONOUS_POTATO = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.POISONOUS_POTATO : FoodComponents.POISONOUS_POTATO;
        PORKCHOP = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.PORKCHOP : FoodComponents.PORKCHOP;
        POTATO = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.POTATO : FoodComponents.POTATO;
        PUFFERFISH = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.PUFFERFISH : FoodComponents.PUFFERFISH;
        PUMPKIN_PIE = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.PUMPKIN_PIE : FoodComponents.PUMPKIN_PIE;
        RABBIT = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.RABBIT : FoodComponents.RABBIT;
        ROTTEN_FLESH = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.ROTTEN_FLESH : FoodComponents.ROTTEN_FLESH;
        SALMON = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.SALMON : FoodComponents.SALMON;
        SPIDER_EYE = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.SPIDER_EYE : FoodComponents.SPIDER_EYE;
        SWEET_BERRIES = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.SWEET_BERRIES : FoodComponents.SWEET_BERRIES;
        GLOW_BERRIES = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.GLOW_BERRIES : FoodComponents.GLOW_BERRIES;
        TROPICAL_FISH = SpeedrunnerMod.options().advanced.betterFoods ? ModFoodComponents.TROPICAL_FISH : FoodComponents.TROPICAL_FISH;
    }
}