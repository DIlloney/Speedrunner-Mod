package com.dilloney.speedrunnermod.items;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.dilloney.speedrunnermod.bases.ToolBases;
import com.dilloney.speedrunnermod.materials.ModArmorMaterials;
import com.dilloney.speedrunnermod.materials.ModToolMaterials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item SPEEDRUNNER_INGOT = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_NUGGET = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS).rarity(Rarity.COMMON));

    public static final Item IGNEOUS_ROCK = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS).rarity(Rarity.COMMON));

    public static final Item EYE_OF_INFERNO = new EyeOfInfernoItem(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).fireproof().rarity(Rarity.COMMON));

    public static final Item EYE_OF_ANNUL = new EyeOfAnnulItem(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).rarity(Rarity.EPIC));

    public static final ToolItem SPEEDRUNNER_SWORD = new SwordItem(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 5, -2.4F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final ToolItem SPEEDRUNNER_SHOVEL = new ShovelItem(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 4, -3.0F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem SPEEDRUNNER_PICKAXE = new ToolBases.PickaxeBase(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 3, -2.8F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem SPEEDRUNNER_AXE = new ToolBases.AxeBase(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 8, -3.05F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem SPEEDRUNNER_HOE = new ToolBases.HoeBase(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final BowItem SPEEDRUNNER_BOW = new SpeedrunnerBowItem(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON).maxCount(1).maxDamage(768));

    public static final ShearsItem SPEEDRUNNER_SHEARS = new ShearsItem(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON).maxCount(1).maxDamage(476));

    public static final FlintAndSteelItem SPEEDRUNNER_FLINT_AND_STEEL = new FlintAndSteelItem(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON).maxCount(1).maxDamage(128));

    public static final ToolItem ANDESITE_SWORD = new SwordItem(ModToolMaterials.ANDESITE_EXCLUDING_SHOVEL, 4, -2.4F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final ToolItem ANDESITE_SHOVEL = new ShovelItem(ModToolMaterials.ANDESITE_SHOVEL, 2, -3.0F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem ANDESITE_PICKAXE = new ToolBases.PickaxeBase(ModToolMaterials.ANDESITE_EXCLUDING_SHOVEL, 2, -2.8F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem ANDESITE_AXE = new ToolBases.AxeBase(ModToolMaterials.ANDESITE_EXCLUDING_SHOVEL, 8, -3.2F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem ANDESITE_HOE = new ToolBases.HoeBase(ModToolMaterials.ANDESITE_EXCLUDING_SHOVEL, 0, -2.0F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem GRANITE_SWORD = new SwordItem(ModToolMaterials.GRANITE_EXCLUDING_SHOVEL, 4, -2.4F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem GRANITE_SHOVEL = new ShovelItem(ModToolMaterials.GRANITE_SHOVEL, 2, -3.0F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem GRANITE_PICKAXE = new ToolBases.PickaxeBase(ModToolMaterials.GRANITE_EXCLUDING_SHOVEL, 2, -2.8F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem GRANITE_AXE = new ToolBases.AxeBase(ModToolMaterials.GRANITE_EXCLUDING_SHOVEL, 8, -3.2F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem GRANITE_HOE = new ToolBases.HoeBase(ModToolMaterials.GRANITE_EXCLUDING_SHOVEL, 0, -2.0F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem DIORITE_SWORD = new SwordItem(ModToolMaterials.DIORITE_EXCLUDING_SHOVEL, 4, -2.4F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final ToolItem DIORITE_SHOVEL = new ShovelItem(ModToolMaterials.DIORITE_SHOVEL, 2, -3.0F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem DIORITE_PICKAXE = new ToolBases.PickaxeBase(ModToolMaterials.DIORITE_EXCLUDING_SHOVEL, 2, -2.8F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem DIORITE_AXE = new ToolBases.AxeBase(ModToolMaterials.DIORITE_EXCLUDING_SHOVEL, 8, -3.2F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem DIORITE_HOE = new ToolBases.HoeBase(ModToolMaterials.DIORITE_EXCLUDING_SHOVEL, 0, -2.0F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem BLACKSTONE_SWORD = new SwordItem(ModToolMaterials.BLACKSTONE_EXCLUDING_SHOVEL, 4, -2.4F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final ToolItem BLACKSTONE_SHOVEL = new ShovelItem(ModToolMaterials.BLACKSTONE_SHOVEL, 2, -3.0F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem BLACKSTONE_PICKAXE = new ToolBases.PickaxeBase(ModToolMaterials.BLACKSTONE_EXCLUDING_SHOVEL, 2, -2.8F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem BLACKSTONE_AXE = new ToolBases.AxeBase(ModToolMaterials.BLACKSTONE_EXCLUDING_SHOVEL, 8, -3.2F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final ToolItem BLACKSTONE_HOE = new ToolBases.HoeBase(ModToolMaterials.BLACKSTONE_EXCLUDING_SHOVEL, 0, -2.0F,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item COOKED_PIGLIN_PORK = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(0.7F)
                    .build()));

    public static final Item RAW_PIGLIN_PORK = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(3)
                    .saturationModifier(0.2F)
                    .build()));

    public static final Item SPEEDRUNNER_BULK = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.RARE).food(new FoodComponent.Builder()
                    .hunger(12)
                    .saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600), 1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200), 1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1200), 1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 500, 1), 0.6F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200), 0.5F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200), 0.4F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 1800, 0, false, false, false), 0.25F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 400), 0.2F)
                    .build()));

    public static final Item ROTTEN_SPEEDRUNNER_BULK = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(4)
                    .saturationModifier(0.1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400), 0.5F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300), 0.1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.UNLUCK, 1200), 0.1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 160), 0.1F)
                    .build()));

    public static final Item SPEEDRUNNER_APPLE = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.RARE).food(new FoodComponent.Builder()
                    .hunger(4)
                    .saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0), 1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 0), 1F)
                    .build()));

    public static final Item COOKED_FLESH = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(6)
                    .saturationModifier(0.8F)
                    .build()));

    public static final Item GOLDEN_STEAK = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder().meat()
                    .hunger(8)
                    .saturationModifier(0.9F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200), 0.25F)
                    .build()));

    public static final Item GOLDEN_PORKCHOP = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder().meat()
                    .hunger(8)
                    .saturationModifier(0.9F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200), 0.25F)
                    .build()));

    public static final Item GOLDEN_MUTTON = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder().meat()
                    .hunger(6)
                    .saturationModifier(1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_CHICKEN = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(6)
                    .saturationModifier(0.8F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_RABBIT = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.8F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_COD = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.8F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_SALMON = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(6)
                    .saturationModifier(0.9F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_BREAD = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.8F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_POTATO = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.4F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_BEETROOT = new Item(
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(1)
                    .saturationModifier(0.7F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item SPEEDRUNNER_HELMET = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.HEAD,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_CHESTPLATE = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.CHEST,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_LEGGINGS = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.LEGS,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_BOOTS = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.FEET,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));
}