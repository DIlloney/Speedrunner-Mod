package com.dilloney.speedrunnermod.items;

import com.dilloney.speedrunnermod.materials.ModArmorMaterials;
import com.dilloney.speedrunnermod.materials.ModToolMaterials;
import com.dilloney.speedrunnermod.registry.ModItemGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.tag.Tag;
import net.minecraft.util.Rarity;

public final class ModItems {

    public static final Item SPEEDRUNNER_INGOT = new SpeedrunnerItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_NUGGET = new SpeedrunnerItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_SWORD = new SwordItem(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 5, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_SHOVEL = new ShovelItem(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 4, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_PICKAXE = new PickaxeItem(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 3, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_AXE = new AxeItem(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 8, -3.05F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_HOE = new HoeItem(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_HELMET = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.HEAD,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_CHESTPLATE = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.CHEST,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_LEGGINGS = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.LEGS,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_BOOTS = new ArmorItem(ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.FEET,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item SPEEDRUNNER_BOW = new SpeedrunnerItem.SpeedrunnerBow(new Item.Settings());

    public static final Item SPEEDRUNNER_CROSSBOW = new SpeedrunnerItem.SpeedrunnerCrossbow(new Item.Settings());

    public static final Item SPEEDRUNNER_SHEARS = new SpeedrunnerItem.SpeedrunnerShears(new Item.Settings());

    public static final Item SPEEDRUNNER_FLINT_AND_STEEL = new SpeedrunnerItem.SpeedrunnerFlintAndSteel(new Item.Settings());

    public static final Item SPEEDRUNNER_FISHING_ROD = new SpeedrunnerItem.SpeedrunnerFishingRod(new Item.Settings());

    public static final Item SPEEDRUNNER_CARROT_ON_A_STICK = new SpeedrunnerItem.SpeedrunnerCarrotOnAStick(new Item.Settings());

    public static final Item SPEEDRUNNER_WARPED_FUNGUS_ON_A_STICK = new SpeedrunnerItem.SpeedrunnerWarpedFungusOnAStick(new Item.Settings());

    public static final Item GOLDEN_SPEEDRUNNER_SWORD = new SwordItem(ModToolMaterials.GOLDEN_SPEEDRUNNER, 4, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item GOLDEN_SPEEDRUNNER_SHOVEL = new ShovelItem(ModToolMaterials.GOLDEN_SPEEDRUNNER, 2.5F, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item GOLDEN_SPEEDRUNNER_PICKAXE = new PickaxeItem(ModToolMaterials.GOLDEN_SPEEDRUNNER, 2, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item GOLDEN_SPEEDRUNNER_AXE = new AxeItem(ModToolMaterials.GOLDEN_SPEEDRUNNER, 7, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item GOLDEN_SPEEDRUNNER_HOE = new HoeItem(ModToolMaterials.GOLDEN_SPEEDRUNNER, 0, -0.5F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item GOLDEN_SPEEDRUNNER_HELMET = new ArmorItem(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.HEAD,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item GOLDEN_SPEEDRUNNER_CHESTPLATE = new ArmorItem(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.CHEST,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item GOLDEN_SPEEDRUNNER_LEGGINGS = new ArmorItem(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.LEGS,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item GOLDEN_SPEEDRUNNER_BOOTS = new ArmorItem(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.FEET,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item IGNEOUS_ROCK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS).rarity(Rarity.COMMON));

    public static final Item INFERNO_EYE = new InfernoEyeItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).fireproof().rarity(Rarity.COMMON));

    public static final Item ANNUL_EYE = new AnnulEyeItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).rarity(Rarity.EPIC));

    public static final Item COOKED_PIGLIN_PORK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(0.7F)
                    .build()));

    public static final Item RAW_PIGLIN_PORK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(3)
                    .saturationModifier(0.2F)
                    .build()));

    public static final Item SPEEDRUNNER_BULK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.RARE).food(new FoodComponent.Builder()
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
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(4)
                    .saturationModifier(0.1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 400), 0.5F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300), 0.1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.UNLUCK, 1200), 0.1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 160), 0.1F)
                    .build()));

    public static final Item SPEEDRUNNER_APPLE = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.RARE).food(new FoodComponent.Builder()
                    .hunger(4)
                    .saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0), 1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 0), 1F)
                    .build()));

    public static final Item COOKED_FLESH = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(6)
                    .saturationModifier(0.8F)
                    .build()));

    public static final Item GOLDEN_STEAK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder().meat()
                    .hunger(8)
                    .saturationModifier(0.9F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200), 0.25F)
                    .build()));

    public static final Item GOLDEN_PORKCHOP = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder().meat()
                    .hunger(8)
                    .saturationModifier(0.9F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200), 0.25F)
                    .build()));

    public static final Item GOLDEN_MUTTON = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder().meat()
                    .hunger(6)
                    .saturationModifier(1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_CHICKEN = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(6)
                    .saturationModifier(0.8F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_RABBIT = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.8F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_COD = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.8F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_SALMON = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(6)
                    .saturationModifier(0.9F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_BREAD = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.8F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_POTATO = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.4F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item GOLDEN_BEETROOT = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder()
                    .hunger(1)
                    .saturationModifier(0.7F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .build()));

    public static final Item ANDESITE_SWORD = new SwordItem(ModToolMaterials.ANDESITE, 3, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item ANDESITE_SHOVEL = new ShovelItem(ModToolMaterials.ANDESITE, 1.5F, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item ANDESITE_PICKAXE = new PickaxeItem(ModToolMaterials.ANDESITE, 1, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item ANDESITE_AXE = new AxeItem(ModToolMaterials.ANDESITE, 7, -3.2F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item ANDESITE_HOE = new HoeItem(ModToolMaterials.ANDESITE, -1, -2.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item GRANITE_SWORD = new SwordItem(ModToolMaterials.GRANITE, 3, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item GRANITE_SHOVEL = new ShovelItem(ModToolMaterials.GRANITE, 1.5F, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item GRANITE_PICKAXE = new PickaxeItem(ModToolMaterials.GRANITE, 1, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item GRANITE_AXE = new AxeItem(ModToolMaterials.GRANITE, 7, -3.2F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item GRANITE_HOE = new HoeItem(ModToolMaterials.GRANITE, -1, -2.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item DIORITE_SWORD = new SwordItem(ModToolMaterials.DIORITE, 3, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item DIORITE_SHOVEL = new ShovelItem(ModToolMaterials.DIORITE, 1.5F, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item DIORITE_PICKAXE = new PickaxeItem(ModToolMaterials.DIORITE, 1, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item DIORITE_AXE = new AxeItem(ModToolMaterials.DIORITE, 7, -3.2F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item DIORITE_HOE = new HoeItem(ModToolMaterials.DIORITE, -1, -2.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item BLACKSTONE_SWORD = new SwordItem(ModToolMaterials.BLACKSTONE, 3, -2.4F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON));

    public static final Item BLACKSTONE_SHOVEL = new ShovelItem(ModToolMaterials.BLACKSTONE, 1.5F, -3.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item BLACKSTONE_PICKAXE = new PickaxeItem(ModToolMaterials.BLACKSTONE, 1, -2.8F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item BLACKSTONE_AXE = new AxeItem(ModToolMaterials.BLACKSTONE, 7, -3.2F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static final Item BLACKSTONE_HOE = new HoeItem(ModToolMaterials.BLACKSTONE, -1, -2.0F,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON));

    public static Tag<Item> PIGLIN_SAFE_ARMOR;
    public static Tag<Item> PIGLIN_BARTERING_ITEMS;
}
