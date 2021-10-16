package net.dilloney.speedrunnermod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {

    public static final FoodComponent SPEEDRUNNER_BULK = new FoodComponent.Builder().hunger(12).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1200, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 500, 1), 0.6F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200), 0.25F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 400), 0.25F).alwaysEdible().build();

    public static final FoodComponent ROTTEN_SPEEDRUNNER_BULK = new FoodComponent.Builder().hunger(4).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F).statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 0), 0.1F).build();

    public static final FoodComponent COOKED_PIGLIN_PORK = new FoodComponent.Builder().hunger(8).saturationModifier(0.8F).build();

    public static final FoodComponent RAW_PIGLIN_PORK = new FoodComponent.Builder().hunger(3).saturationModifier(0.3F).build();

    public static final FoodComponent COOKED_FLESH = new FoodComponent.Builder().hunger(6).saturationModifier(0.8F).build();
}