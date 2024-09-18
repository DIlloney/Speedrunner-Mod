package net.dillon.speedrunnermod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * All Speedrunner Mod {@code food components} (or food items).
 * <p>Also contains all modified vanilla food components, see {@link net.dillon.speedrunnermod.mixin.main.item.FoodComponentsMixin} for more.</p>
 */
public class ModFoodComponents {
    protected static final FoodComponent SPEEDRUNNER_BULK;
    protected static final FoodComponent ROTTEN_SPEEDRUNNER_BULK;
    protected static final FoodComponent COOKED_FLESH;
    protected static final FoodComponent PIGLIN_PORK;
    protected static final FoodComponent COOKED_PIGLIN_PORK;
    protected static final FoodComponent GOLDEN_PIGLIN_PORK;
    protected static final FoodComponent GOLDEN_BEEF;
    protected static final FoodComponent GOLDEN_PORKCHOP;
    protected static final FoodComponent GOLDEN_MUTTON;
    protected static final FoodComponent GOLDEN_CHICKEN;
    protected static final FoodComponent GOLDEN_RABBIT;
    protected static final FoodComponent GOLDEN_COD;
    protected static final FoodComponent GOLDEN_SALMON;
    protected static final FoodComponent GOLDEN_BREAD;
    protected static final FoodComponent GOLDEN_POTATO;
    protected static final FoodComponent GOLDEN_BEETROOT;
    public static final FoodComponent APPLE;
    public static final FoodComponent BAKED_POTATO;
    public static final FoodComponent BEEF;
    public static final FoodComponent BEETROOT;
    public static final FoodComponent BREAD;
    public static final FoodComponent CARROT;
    public static final FoodComponent CHICKEN;
    public static final FoodComponent CHORUS_FRUIT;
    public static final FoodComponent COD;
    public static final FoodComponent COOKED_BEEF;
    public static final FoodComponent COOKED_CHICKEN;
    public static final FoodComponent COOKED_COD;
    public static final FoodComponent COOKED_MUTTON;
    public static final FoodComponent COOKED_PORKCHOP;
    public static final FoodComponent COOKED_RABBIT;
    public static final FoodComponent COOKED_SALMON;
    public static final FoodComponent COOKIE;
    public static final FoodComponent DRIED_KELP;
    public static final FoodComponent ENCHANTED_GOLDEN_APPLE;
    public static final FoodComponent GOLDEN_APPLE;
    public static final FoodComponent GOLDEN_CARROT;
    public static final FoodComponent HONEY_BOTTLE;
    public static final FoodComponent MELON_SLICE;
    public static final FoodComponent MUTTON;
    public static final FoodComponent POISONOUS_POTATO;
    public static final FoodComponent PORKCHOP;
    public static final FoodComponent POTATO;
    public static final FoodComponent PUFFERFISH;
    public static final FoodComponent PUMPKIN_PIE;
    public static final FoodComponent RABBIT;
    public static final FoodComponent ROTTEN_FLESH;
    public static final FoodComponent SALMON;
    public static final FoodComponent SPIDER_EYE;
    public static final FoodComponent SWEET_BERRIES;
    public static final FoodComponent GLOW_BERRIES;
    public static final FoodComponent TROPICAL_FISH;

    static {
        SPEEDRUNNER_BULK = new FoodComponent.Builder().nutrition(12).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1200, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 500, 1), 0.5F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200), 0.25F).alwaysEdible().build();
        ROTTEN_SPEEDRUNNER_BULK = new FoodComponent.Builder().nutrition(4).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 0), 0.1F).build();
        COOKED_FLESH = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8F).build();
        PIGLIN_PORK = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build() : new FoodComponent.Builder().nutrition(3).saturationModifier(0.2F).build();
        COOKED_PIGLIN_PORK = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(8).saturationModifier(0.8F).build() : new FoodComponent.Builder().nutrition(8).saturationModifier(0.7F).build();
        GOLDEN_PIGLIN_PORK = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(8).saturationModifier(0.9F).build() : new FoodComponent.Builder().nutrition(8).saturationModifier(0.8F).build();
        GOLDEN_BEEF = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(8).saturationModifier(1.0F).build() : new FoodComponent.Builder().nutrition(8).saturationModifier(0.9F).build();
        GOLDEN_PORKCHOP = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(8).saturationModifier(1.0F).build() : new FoodComponent.Builder().nutrition(8).saturationModifier(0.9F).build();
        GOLDEN_MUTTON = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(6).saturationModifier(1.0F).build() : new FoodComponent.Builder().nutrition(8).saturationModifier(0.9F).build();
        GOLDEN_CHICKEN = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(6).saturationModifier(1.0F).build() : new FoodComponent.Builder().nutrition(8).saturationModifier(0.7F).build();
        GOLDEN_RABBIT = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(5).saturationModifier(1.2F).build() : new FoodComponent.Builder().nutrition(5).saturationModifier(0.7F).build();
        GOLDEN_COD = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(5).saturationModifier(1.2F).build() : new FoodComponent.Builder().nutrition(5).saturationModifier(0.7F).build();
        GOLDEN_SALMON = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(6).saturationModifier(1.0F).build() : new FoodComponent.Builder().nutrition(6).saturationModifier(0.9F).build();
        GOLDEN_BREAD = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(5).saturationModifier(1.2F).build() : new FoodComponent.Builder().nutrition(5).saturationModifier(0.7F).build();
        GOLDEN_POTATO = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(6).saturationModifier(1.0F).build() : new FoodComponent.Builder().nutrition(5).saturationModifier(0.7F).build();
        GOLDEN_BEETROOT = options().main.betterFoods.getCurrentValue() ? new FoodComponent.Builder().nutrition(2).saturationModifier(1.5F).build() : new FoodComponent.Builder().nutrition(2).saturationModifier(0.7F).build();
        APPLE = new FoodComponent.Builder().nutrition(4).saturationModifier(0.8F).build();
        BAKED_POTATO = new FoodComponent.Builder().nutrition(6).saturationModifier(0.9F).build();
        BEEF = new FoodComponent.Builder().nutrition(4).saturationModifier(0.7F).build();
        BEETROOT = new FoodComponent.Builder().nutrition(2).saturationModifier(1.4F).build();
        BREAD = new FoodComponent.Builder().nutrition(5).saturationModifier(1.1F).build();
        CARROT = new FoodComponent.Builder().nutrition(3).saturationModifier(1.2F).build();
        CHICKEN = new FoodComponent.Builder().nutrition(2).saturationModifier(1.2F).build();
        CHORUS_FRUIT = new FoodComponent.Builder().nutrition(4).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).alwaysEdible().build();
        COD = new FoodComponent.Builder().nutrition(2).saturationModifier(1.2F).build();
        COOKED_BEEF = new FoodComponent.Builder().nutrition(8).saturationModifier(0.9F).build();
        COOKED_CHICKEN = new FoodComponent.Builder().nutrition(6).saturationModifier(0.9F).build();
        COOKED_COD = new FoodComponent.Builder().nutrition(5).saturationModifier(1.1F).build();
        COOKED_MUTTON = new FoodComponent.Builder().nutrition(6).saturationModifier(0.9F).build();
        COOKED_PORKCHOP = new FoodComponent.Builder().nutrition(8).saturationModifier(0.9F).build();
        COOKED_RABBIT = new FoodComponent.Builder().nutrition(5).saturationModifier(1.1F).build();
        COOKED_SALMON = new FoodComponent.Builder().nutrition(6).saturationModifier(0.9F).build();
        COOKIE = new FoodComponent.Builder().nutrition(2).saturationModifier(1.3F).build();
        DRIED_KELP = new FoodComponent.Builder().nutrition(1).saturationModifier(0.6F).snack().build();
        ENCHANTED_GOLDEN_APPLE = new FoodComponent.Builder().nutrition(8).saturationModifier(1.4F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), 1.0F).alwaysEdible().build();
        GOLDEN_APPLE = new FoodComponent.Builder().nutrition(8).saturationModifier(1.3F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F).alwaysEdible().build();
        GOLDEN_CARROT = new FoodComponent.Builder().nutrition(6).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).build();
        HONEY_BOTTLE = new FoodComponent.Builder().nutrition(6).saturationModifier(0.5F).build();
        MELON_SLICE = new FoodComponent.Builder().nutrition(4).saturationModifier(1.2F).build();
        MUTTON = new FoodComponent.Builder().nutrition(2).saturationModifier(0.8F).build();
        POISONOUS_POTATO = new FoodComponent.Builder().nutrition(2).saturationModifier(0.7F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.6F).build();
        PORKCHOP = new FoodComponent.Builder().nutrition(4).saturationModifier(0.4F).build();
        POTATO = new FoodComponent.Builder().nutrition(1).saturationModifier(1.0F).build();
        PUFFERFISH = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 1200, 3), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0), 1.0F).build();
        PUMPKIN_PIE = new FoodComponent.Builder().nutrition(8).saturationModifier(0.9F).build();
        RABBIT = new FoodComponent.Builder().nutrition(3).saturationModifier(0.9F).build();
        ROTTEN_FLESH = new FoodComponent.Builder().nutrition(4).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 0), 0.8F).build();
        SALMON = new FoodComponent.Builder().nutrition(2).saturationModifier(1.4F).build();
        SPIDER_EYE = new FoodComponent.Builder().nutrition(2).saturationModifier(1.1F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 1.0F).build();
        SWEET_BERRIES = new FoodComponent.Builder().nutrition(4).saturationModifier(1.2F).build();
        GLOW_BERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(1.0F).build();
        TROPICAL_FISH = new FoodComponent.Builder().nutrition(2).saturationModifier(1.2F).build();
    }
}