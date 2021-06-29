package com.dilloney.speedrunnermod.mixins.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FoodComponents.class)
public class FoodComponentsMixin {

    @Shadow @Final static FoodComponent APPLE;
    @Shadow @Final static FoodComponent BAKED_POTATO;
    @Shadow @Final static FoodComponent BEEF;
    @Shadow @Final static FoodComponent BEETROOT;
    @Shadow @Final static FoodComponent BREAD;
    @Shadow @Final static FoodComponent CARROT;
    @Shadow @Final static FoodComponent CHICKEN;
    @Shadow @Final static FoodComponent COD;
    @Shadow @Final static FoodComponent COOKED_BEEF;
    @Shadow @Final static FoodComponent COOKED_CHICKEN;
    @Shadow @Final static FoodComponent COOKED_COD;
    @Shadow @Final static FoodComponent COOKED_MUTTON;
    @Shadow @Final static FoodComponent COOKED_PORKCHOP;
    @Shadow @Final static FoodComponent COOKED_RABBIT;
    @Shadow @Final static FoodComponent COOKED_SALMON;
    @Shadow @Final static FoodComponent COOKIE;
    @Shadow @Final static FoodComponent DRIED_KELP;
    @Shadow @Final static FoodComponent ENCHANTED_GOLDEN_APPLE;
    @Shadow @Final static FoodComponent GOLDEN_APPLE;
    @Shadow @Final static FoodComponent GOLDEN_CARROT;
    @Shadow @Final static FoodComponent HONEY_BOTTLE;
    @Shadow @Final static FoodComponent MELON_SLICE;
    @Shadow @Final static FoodComponent MUTTON;
    @Shadow @Final static FoodComponent POISONOUS_POTATO;
    @Shadow @Final static FoodComponent PORKCHOP;
    @Shadow @Final static FoodComponent POTATO;
    @Shadow @Final static FoodComponent PUFFERFISH;
    @Shadow @Final static FoodComponent PUMPKIN_PIE;
    @Shadow @Final static FoodComponent RABBIT;
    @Shadow @Final static FoodComponent ROTTEN_FLESH;
    @Shadow @Final static FoodComponent SALMON;
    @Shadow @Final static FoodComponent SPIDER_EYE;
    @Shadow @Final static FoodComponent SWEET_BERRIES;
    @Shadow @Final static FoodComponent GLOW_BERRIES;
    @Shadow @Final static FoodComponent TROPICAL_FISH;

    static {
        APPLE = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.8F).build();
        BAKED_POTATO = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.9F).build();
        BEEF = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.7F).meat().build();
        BEETROOT = (new FoodComponent.Builder()).hunger(2).saturationModifier(1.4F).build();
        BREAD = (new FoodComponent.Builder()).hunger(5).saturationModifier(1.1F).build();
        CARROT = (new FoodComponent.Builder()).hunger(3).saturationModifier(1.2F).build();
        CHICKEN = (new FoodComponent.Builder()).hunger(2).saturationModifier(1.2F).build();
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
        System.out.println("[Speedrunner Mod] [main/INFO]: Ignore these errors, everything will work properly.");
    }
}
