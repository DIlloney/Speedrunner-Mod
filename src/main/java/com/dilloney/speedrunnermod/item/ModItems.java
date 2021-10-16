package net.dilloney.speedrunnermod.item;

import net.dilloney.speedrunnermod.block.ModBlocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item SPEEDRUNNER_INGOT = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS));

    public static final Item SPEEDRUNNER_NUGGET = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS));

    public static final Item RAW_SPEEDRUNNER = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS));

    public static final Item SPEEDRUNNER_SWORD = new SpeedrunnerSwordItem(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 5, -2.4F,
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

    public static final Item SPEEDRUNNER_BOW = new BowItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).maxCount(1).maxDamage(768));

    public static final Item SPEEDRUNNER_CROSSBOW = new SpeedrunnerCrossbowItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).maxCount(1).maxDamage(652));

    public static final Item SPEEDRUNNER_SHEARS = new SpeedrunnerShearsItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).maxCount(1).maxDamage(476));

    public static final Item SPEEDRUNNER_FLINT_AND_STEEL = new FlintAndSteelItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).maxCount(1).maxDamage(128));

    public static final Item SPEEDRUNNER_SHIELD = new SpeedrunnerShieldItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).maxCount(1).maxDamage(672));

    public static final Item SPEEDRUNNER_BULK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).rarity(Rarity.RARE).food(ModFoodComponents.SPEEDRUNNER_BULK));

    public static final Item ROTTEN_SPEEDRUNNER_BULK = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.ROTTEN_SPEEDRUNNER_BULK));

    public static final Item GOLDEN_SPEEDRUNNER_SWORD = new SpeedrunnerSwordItem(ModToolMaterials.GOLDEN_SPEEDRUNNER, 4, -2.4F,
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

    public static final Item COOKED_FLESH = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.FOOD).food(ModFoodComponents.COOKED_FLESH));

    public static final Item INFERNO_EYE = new InfernoEyeItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).fireproof());

    public static final Item ANNUL_EYE = new AnnulEyeItem(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).rarity(Rarity.EPIC));

    public static final BlockItem SPEEDRUNNER_BLOCK = new BlockItem(ModBlocks.SPEEDRUNNER_BLOCK,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS));

    public static final BlockItem RAW_SPEEDRUNNER_BLOCK = new BlockItem(ModBlocks.RAW_SPEEDRUNNER_BLOCK,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS));

    public static final BlockItem SPEEDRUNNER_ORE = new BlockItem(ModBlocks.SPEEDRUNNER_ORE,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem DEEPSLATE_SPEEDRUNNER_ORE = new BlockItem(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem NETHER_SPEEDRUNNER_ORE = new BlockItem(ModBlocks.NETHER_SPEEDRUNNER_ORE,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem IGNEOUS_ORE = new BlockItem(ModBlocks.IGNEOUS_ORE,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem DEEPSLATE_IGNEOUS_ORE = new BlockItem(ModBlocks.DEEPSLATE_IGNEOUS_ORE,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem NETHER_IGNEOUS_ORE = new BlockItem(ModBlocks.NETHER_IGNEOUS_ORE,
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS));

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
}
