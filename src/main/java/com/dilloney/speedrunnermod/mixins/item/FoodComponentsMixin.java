package com.dilloney.speedrunnermod.mixins.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FoodComponents.class)
public class FoodComponentsMixin {

    @Shadow final static FoodComponent APPLE;
    @Shadow final static FoodComponent BAKED_POTATO;
    @Shadow final static FoodComponent BEEF;
    @Shadow final static FoodComponent BEETROOT;
    @Shadow final static FoodComponent BREAD;
    @Shadow final static FoodComponent CARROT;
    @Shadow final static FoodComponent CHICKEN;
    @Shadow static final FoodComponent CHORUS_FRUIT;
    @Shadow final static FoodComponent COD;
    @Shadow final static FoodComponent COOKED_BEEF;
    @Shadow final static FoodComponent COOKED_CHICKEN;
    @Shadow final static FoodComponent COOKED_COD;
    @Shadow final static FoodComponent COOKED_MUTTON;
    @Shadow final static FoodComponent COOKED_PORKCHOP;
    @Shadow final static FoodComponent COOKED_RABBIT;
    @Shadow final static FoodComponent COOKED_SALMON;
    @Shadow final static FoodComponent COOKIE;
    @Shadow final static FoodComponent DRIED_KELP;
    @Shadow final static FoodComponent ENCHANTED_GOLDEN_APPLE;
    @Shadow final static FoodComponent GOLDEN_APPLE;
    @Shadow final static FoodComponent GOLDEN_CARROT;
    @Shadow final static FoodComponent HONEY_BOTTLE;
    @Shadow final static FoodComponent MELON_SLICE;
    @Shadow final static FoodComponent MUTTON;
    @Shadow final static FoodComponent POISONOUS_POTATO;
    @Shadow final static FoodComponent PORKCHOP;
    @Shadow final static FoodComponent POTATO;
    @Shadow final static FoodComponent PUFFERFISH;
    @Shadow final static FoodComponent PUMPKIN_PIE;
    @Shadow final static FoodComponent RABBIT;
    @Shadow final static FoodComponent ROTTEN_FLESH;
    @Shadow final static FoodComponent SALMON;
    @Shadow final static FoodComponent SPIDER_EYE;
    @Shadow final static FoodComponent SWEET_BERRIES;
    @Shadow final static FoodComponent GLOW_BERRIES;
    @Shadow final static FoodComponent TROPICAL_FISH;

    static {
        APPLE = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.8F).build();
        BAKED_POTATO = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.9F).build();
        BEEF = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.7F).meat().build();
        BEETROOT = (new FoodComponent.Builder()).hunger(2).saturationModifier(1.4F).build();
        BREAD = (new FoodComponent.Builder()).hunger(5).saturationModifier(1.1F).build();
        CARROT = (new FoodComponent.Builder()).hunger(3).saturationModifier(1.2F).build();
        CHICKEN = (new FoodComponent.Builder()).hunger(2).saturationModifier(1.2F).build();
        CHORUS_FRUIT = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.5F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1F).alwaysEdible().build();
        COD = (new FoodComponent.Builder()).hunger(2).saturationModifier(1.2F).build();
        COOKED_BEEF = (new FoodComponent.Builder()).hunger(8).saturationModifier(0.9F).meat().build();
        COOKED_CHICKEN = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.9F).meat().build();
        COOKED_COD = (new FoodComponent.Builder()).hunger(5).saturationModifier(1.2F).build();
        COOKED_MUTTON = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.9F).meat().build();
        COOKED_PORKCHOP = (new FoodComponent.Builder()).hunger(8).saturationModifier(0.9F).meat().build();
        COOKED_RABBIT = (new FoodComponent.Builder()).hunger(5).saturationModifier(1.1F).meat().build();
        COOKED_SALMON = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.9F).build();
        COOKIE = (new FoodComponent.Builder()).hunger(2).saturationModifier(1.3F).build();
        DRIED_KELP = (new FoodComponent.Builder()).hunger(1).saturationModifier(0.6F).snack().build();
        ENCHANTED_GOLDEN_APPLE = (new FoodComponent.Builder()).hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 4), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 4), 1.0F).alwaysEdible().build();
        GOLDEN_APPLE = (new FoodComponent.Builder()).hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F).alwaysEdible().build();
        GOLDEN_CARROT = (new FoodComponent.Builder()).hunger(6).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).build();
        HONEY_BOTTLE = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.5F).build();
        MELON_SLICE = (new FoodComponent.Builder()).hunger(4).saturationModifier(1.2F).build();
        MUTTON = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.8F).meat().build();
        POISONOUS_POTATO = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.7F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.6F).build();
        PORKCHOP = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.4F).meat().build();
        POTATO = (new FoodComponent.Builder()).hunger(1).saturationModifier(1.0F).build();
        PUFFERFISH = (new FoodComponent.Builder()).hunger(1).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 1200, 3), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0), 1.0F).build();
        PUMPKIN_PIE = (new FoodComponent.Builder()).hunger(8).saturationModifier(0.9F).build();
        RABBIT = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.9F).meat().build();
        ROTTEN_FLESH = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 0), 0.8F).meat().build();
        SALMON = (new FoodComponent.Builder()).hunger(2).saturationModifier(1.4F).build();
        SPIDER_EYE = (new FoodComponent.Builder()).hunger(2).saturationModifier(1.1F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 1.0F).build();
        SWEET_BERRIES = (new FoodComponent.Builder()).hunger(4).saturationModifier(1.2F).build();
        GLOW_BERRIES = (new FoodComponent.Builder()).hunger(2).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0, true, false, true), 1.0F).build();
        TROPICAL_FISH = (new FoodComponent.Builder()).hunger(2).saturationModifier(1.2F).build();
    }
}