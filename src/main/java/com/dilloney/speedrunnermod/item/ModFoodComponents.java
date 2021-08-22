package com.dilloney.speedrunnermod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

public final class ModFoodComponents {

    public static final FoodComponent SPEEDRUNNER_BULK =
            new FoodComponent.Builder().hunger(12).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1200, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 500, 1), 0.6F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200), 0.5F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200), 0.4F).statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 1800, 0, false, false, false), 0.25F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 400), 0.2F).build();
    public static final FoodComponent ROTTEN_SPEEDRUNNER_BULK =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 0), 0.1F).statusEffect(new StatusEffectInstance(StatusEffects.UNLUCK, 1200, 0), 0.1F).build();
    public static final FoodComponent SPEEDRUNNER_APPLE =
            new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 0), 1.0F).build();
    public static final FoodComponent COOKED_PIGLIN_PORK;
    public static final FoodComponent RAW_PIGLIN_PORK;
    public static final FoodComponent GOLDEN_PIGLIN_PORK;
    public static final FoodComponent COOKED_FLESH;
    public static final FoodComponent GOLDEN_STEAK;
    public static final FoodComponent GOLDEN_PORKCHOP;
    public static final FoodComponent GOLDEN_MUTTON;
    public static final FoodComponent GOLDEN_CHICKEN;
    public static final FoodComponent GOLDEN_RABBIT;
    public static final FoodComponent GOLDEN_COD;
    public static final FoodComponent GOLDEN_SALMON;
    public static final FoodComponent GOLDEN_BREAD;
    public static final FoodComponent GOLDEN_POTATO;
    public static final FoodComponent GOLDEN_BEETROOT;

    static {
        if (OPTIONS.doomMode) {
            COOKED_PIGLIN_PORK = new FoodComponent.Builder().hunger(8).saturationModifier(0.6F).build();
            RAW_PIGLIN_PORK = new FoodComponent.Builder().hunger(3).saturationModifier(0.1F).build();
            GOLDEN_PIGLIN_PORK = new FoodComponent.Builder().hunger(8).saturationModifier(0.7F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            COOKED_FLESH = new FoodComponent.Builder().hunger(6).saturationModifier(0.7F).build();
            GOLDEN_STEAK = new FoodComponent.Builder().meat().hunger(8).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_PORKCHOP = new FoodComponent.Builder().meat().hunger(8).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_MUTTON = new FoodComponent.Builder().meat().hunger(6).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_CHICKEN = new FoodComponent.Builder().hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
            GOLDEN_RABBIT = new FoodComponent.Builder().hunger(5).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
            GOLDEN_COD = new FoodComponent.Builder().hunger(5).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_SALMON = new FoodComponent.Builder().hunger(6).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(0.7F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
            GOLDEN_POTATO = new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
            GOLDEN_BEETROOT = new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
        } else if (OPTIONS.getModDifficulty() <= 2) {
            COOKED_PIGLIN_PORK = new FoodComponent.Builder().hunger(8).saturationModifier(0.8F).build();
            RAW_PIGLIN_PORK = new FoodComponent.Builder().hunger(3).saturationModifier(0.3F).build();
            GOLDEN_PIGLIN_PORK = new FoodComponent.Builder().hunger(8).saturationModifier(0.9F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).build();
            COOKED_FLESH = new FoodComponent.Builder().hunger(6).saturationModifier(0.8F).build();
            GOLDEN_STEAK = new FoodComponent.Builder().meat().hunger(8).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).build();
            GOLDEN_PORKCHOP = new FoodComponent.Builder().meat().hunger(8).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).build();
            GOLDEN_MUTTON = new FoodComponent.Builder().meat().hunger(6).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).build();
            GOLDEN_CHICKEN = new FoodComponent.Builder().hunger(6).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_RABBIT = new FoodComponent.Builder().hunger(5).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0), 1.0F).build();
            GOLDEN_COD = new FoodComponent.Builder().hunger(5).saturationModifier(1.1F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).build();
            GOLDEN_SALMON = new FoodComponent.Builder().hunger(6).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).build();
            GOLDEN_BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_POTATO = new FoodComponent.Builder().hunger(4).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
            GOLDEN_BEETROOT = new FoodComponent.Builder().hunger(4).saturationModifier(0.9F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
        } else {
            COOKED_PIGLIN_PORK = new FoodComponent.Builder().hunger(8).saturationModifier(0.7F).build();
            RAW_PIGLIN_PORK = new FoodComponent.Builder().hunger(3).saturationModifier(0.2F).build();
            GOLDEN_PIGLIN_PORK = new FoodComponent.Builder().hunger(8).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            COOKED_FLESH = new FoodComponent.Builder().hunger(6).saturationModifier(0.7F).build();
            GOLDEN_STEAK = new FoodComponent.Builder().meat().hunger(8).saturationModifier(0.9F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_PORKCHOP = new FoodComponent.Builder().meat().hunger(8).saturationModifier(0.9F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_MUTTON = new FoodComponent.Builder().meat().hunger(6).saturationModifier(0.9F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_CHICKEN = new FoodComponent.Builder().hunger(6).saturationModifier(0.9F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
            GOLDEN_RABBIT = new FoodComponent.Builder().hunger(5).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
            GOLDEN_COD = new FoodComponent.Builder().hunger(5).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_SALMON = new FoodComponent.Builder().hunger(6).saturationModifier(0.9F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).build();
            GOLDEN_BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(0.7F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
            GOLDEN_POTATO = new FoodComponent.Builder().hunger(4).saturationModifier(0.7F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
            GOLDEN_BEETROOT = new FoodComponent.Builder().hunger(4).saturationModifier(0.7F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F).build();
        }
    }
}