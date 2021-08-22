package com.dilloney.speedrunnermod.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;

public final class ModItems {

    public static final Item SPEEDRUNNER_INGOT = new SpeedrunnerItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS));

    public static final Item SPEEDRUNNER_NUGGET = new SpeedrunnerItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS));

    public static final Item RAW_SPEEDRUNNER = new SpeedrunnerItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS));

    public static final Item SPEEDRUNNER_SWORD = new SwordItem(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 5, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item SPEEDRUNNER_SHOVEL = new ShovelItem(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 4, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item SPEEDRUNNER_PICKAXE = new PickaxeItem(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 3, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item SPEEDRUNNER_AXE = new AxeItem(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 8, -3.05F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item SPEEDRUNNER_HOE = new HoeItem(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item SPEEDRUNNER_HELMET = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.HEAD,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item SPEEDRUNNER_CHESTPLATE = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.CHEST,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item SPEEDRUNNER_LEGGINGS = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.LEGS,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item SPEEDRUNNER_BOOTS = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.FEET,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item SPEEDRUNNER_BOW = new SpeedrunnerBowItem(new Item.Settings());

    public static final Item SPEEDRUNNER_CROSSBOW = new SpeedrunnerCrossbowItem(new Item.Settings());

    public static final Item SPEEDRUNNER_SHEARS = new SpeedrunnerShearsItem(new Item.Settings());

    public static final Item SPEEDRUNNER_FLINT_AND_STEEL = new SpeedrunnerFlintAndSteelItem(new Item.Settings());

    public static final Item SPEEDRUNNER_FISHING_ROD = new SpeedrunnerFishingRodItem(new Item.Settings());

    public static final Item SPEEDRUNNER_CARROT_ON_A_STICK = new SpeedrunnerCarrotOnAStickItem(new Item.Settings());

    public static final Item SPEEDRUNNER_WARPED_FUNGUS_ON_A_STICK = new SpeedrunnerWarpedFungusOnAStickItem(new Item.Settings());

    public static final Item SPEEDRUNNER_SHIELD = new SpeedrunnerShieldItem(new Item.Settings());

    public static final Item SPEEDRUNNER_MUSIC_DISC = new SpeedrunnerMusicDiscItem(new Item.Settings());

    public static final Item SPEEDRUNNER_APPLE = new SpeedrunnerItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.RARE).food(ModFoodComponents.SPEEDRUNNER_APPLE));

    public static final Item SPEEDRUNNER_BULK = new SpeedrunnerItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.RARE).food(ModFoodComponents.SPEEDRUNNER_BULK));

    public static final Item ROTTEN_SPEEDRUNNER_BULK = new SpeedrunnerItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.ROTTEN_SPEEDRUNNER_BULK));

    public static final Item GOLDEN_SPEEDRUNNER_SWORD = new SwordItem(ModToolMaterials.GOLDEN_SPEEDRUNNER, 4, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item GOLDEN_SPEEDRUNNER_SHOVEL = new ShovelItem(ModToolMaterials.GOLDEN_SPEEDRUNNER, 2.5F, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item GOLDEN_SPEEDRUNNER_PICKAXE = new PickaxeItem(ModToolMaterials.GOLDEN_SPEEDRUNNER, 2, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item GOLDEN_SPEEDRUNNER_AXE = new AxeItem(ModToolMaterials.GOLDEN_SPEEDRUNNER, 7, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item GOLDEN_SPEEDRUNNER_HOE = new HoeItem(ModToolMaterials.GOLDEN_SPEEDRUNNER, 0, -0.5F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item GOLDEN_SPEEDRUNNER_HELMET = new ArmorItem(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.HEAD,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item GOLDEN_SPEEDRUNNER_CHESTPLATE = new ArmorItem(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.CHEST,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item GOLDEN_SPEEDRUNNER_LEGGINGS = new ArmorItem(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.LEGS,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item GOLDEN_SPEEDRUNNER_BOOTS = new ArmorItem(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.FEET,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item IGNEOUS_ROCK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS));

    public static final Item COOKED_PIGLIN_PORK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.COOKED_PIGLIN_PORK));

    public static final Item PIGLIN_PORK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.RAW_PIGLIN_PORK));

    public static final Item GOLDEN_PIGLIN_PORK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_PIGLIN_PORK));

    public static final Item COOKED_FLESH = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.COOKED_FLESH));

    public static final Item GOLDEN_STEAK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_STEAK));

    public static final Item GOLDEN_PORKCHOP = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_PORKCHOP));

    public static final Item GOLDEN_MUTTON = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_MUTTON));

    public static final Item GOLDEN_CHICKEN = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_CHICKEN));

    public static final Item GOLDEN_RABBIT = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_RABBIT));

    public static final Item GOLDEN_COD = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_COD));

    public static final Item GOLDEN_SALMON = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_SALMON));

    public static final Item GOLDEN_BREAD = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_BREAD));

    public static final Item GOLDEN_POTATO = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_POTATO));

    public static final Item GOLDEN_BEETROOT = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_BEETROOT));

    public static final Item INFERNO_EYE = new InfernoEyeItem(new Item.Settings());

    public static final Item ANNUL_EYE = new AnnulEyeItem(new Item.Settings());

    public static final Item ANDESITE_SWORD = new SwordItem(ModToolMaterials.ANDESITE, 3, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item ANDESITE_SHOVEL = new ShovelItem(ModToolMaterials.ANDESITE, 1.5F, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item ANDESITE_PICKAXE = new PickaxeItem(ModToolMaterials.ANDESITE, 1, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item ANDESITE_AXE = new AxeItem(ModToolMaterials.ANDESITE, 7, -3.2F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item ANDESITE_HOE = new HoeItem(ModToolMaterials.ANDESITE, -1, -2.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item GRANITE_SWORD = new SwordItem(ModToolMaterials.GRANITE, 3, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item GRANITE_SHOVEL = new ShovelItem(ModToolMaterials.GRANITE, 1.5F, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item GRANITE_PICKAXE = new PickaxeItem(ModToolMaterials.GRANITE, 1, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item GRANITE_AXE = new AxeItem(ModToolMaterials.GRANITE, 7, -3.2F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item GRANITE_HOE = new HoeItem(ModToolMaterials.GRANITE, -1, -2.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item DIORITE_SWORD = new SwordItem(ModToolMaterials.DIORITE, 3, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item DIORITE_SHOVEL = new ShovelItem(ModToolMaterials.DIORITE, 1.5F, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item DIORITE_PICKAXE = new PickaxeItem(ModToolMaterials.DIORITE, 1, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item DIORITE_AXE = new AxeItem(ModToolMaterials.DIORITE, 7, -3.2F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item DIORITE_HOE = new HoeItem(ModToolMaterials.DIORITE, -1, -2.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item BLACKSTONE_SWORD = new SwordItem(ModToolMaterials.BLACKSTONE, 3, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT));

    public static final Item BLACKSTONE_SHOVEL = new ShovelItem(ModToolMaterials.BLACKSTONE, 1.5F, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item BLACKSTONE_PICKAXE = new PickaxeItem(ModToolMaterials.BLACKSTONE, 1, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item BLACKSTONE_AXE = new AxeItem(ModToolMaterials.BLACKSTONE, 7, -3.2F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));

    public static final Item BLACKSTONE_HOE = new HoeItem(ModToolMaterials.BLACKSTONE, -1, -2.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS));
}