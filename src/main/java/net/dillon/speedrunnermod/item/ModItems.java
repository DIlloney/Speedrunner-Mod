package net.dillon.speedrunnermod.item;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.dillon.speedrunnermod.entity.ModBoats;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * All Speedrunner Mod {@code items.}
 */
public class ModItems {
    private static final Text GOLDEN_SPEEDRUNNER_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.applies_to"))).formatted(Formatting.GOLD);
    private static final Text GOLDEN_SPEEDRUNNER_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.ingredients"))).formatted(Formatting.AQUA);
    private static final Text GOLDEN_SPEEDRUNNER_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", ofSpeedrunnerMod("golden_speedrunner_upgrade"))).formatted(Formatting.GRAY);
    private static final Text GOLDEN_SPEEDRUNNER_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.base_slot_description")));
    private static final Text GOLDEN_SPEEDRUNNER_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.additions_slot_description")));

    public static final Item SPEEDRUNNER_INGOT = new Item(
            new Item.Settings()) {

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line1").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line3").formatted(Formatting.GRAY));
            }
        }
    };

    public static final Item SPEEDRUNNER_NUGGET = new Item(
            new Item.Settings());

    public static final Item RAW_SPEEDRUNNER = new Item(
            new Item.Settings());

    public static final Item SPEEDRUNNER_SWORD = new SpeedrunnerSwordItem(
            5, new Item.Settings());

    public static final Item SPEEDRUNNER_SHOVEL = new ShovelItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, new Item.Settings()
            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 4, -3.0F)));

    public static final Item SPEEDRUNNER_PICKAXE = new PickaxeItem(
            ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, new Item.Settings()
            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 3, -2.8F)));

    public static final Item SPEEDRUNNER_AXE = new AxeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, new Item.Settings()
            .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 8, -3.05F)));

    public static final Item SPEEDRUNNER_HOE = new HoeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, new Item.Settings()
            .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F)));

    public static final Item SPEEDRUNNER_HELMET = new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentType.HELMET, new Item.Settings().maxCount(1).maxDamage(EquipmentType.HELMET.getMaxDamage(30)));

    public static final Item SPEEDRUNNER_CHESTPLATE = new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentType.CHESTPLATE, new Item.Settings().maxCount(1).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(30)));

    public static final Item SPEEDRUNNER_LEGGINGS = new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentType.LEGGINGS, new Item.Settings().maxCount(1).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(30)));

    public static final Item SPEEDRUNNER_BOOTS = new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentType.BOOTS, new Item.Settings().maxCount(1).maxDamage(EquipmentType.BOOTS.getMaxDamage(30)));

    public static final Item SPEEDRUNNER_BOW = new SpeedrunnerBowItem(new Item.Settings());

    public static final Item SPEEDRUNNER_CROSSBOW = new SpeedrunnerCrossbowItem(new Item.Settings());

    public static final Item SPEEDRUNNER_SHEARS = new SpeedrunnerShearsItem(new Item.Settings());

    public static final Item SPEEDRUNNER_FLINT_AND_STEEL = new FlintAndSteelItem(
            new Item.Settings().maxCount(1).maxDamage(128));

    public static final Item SPEEDRUNNER_SHIELD = new SpeedrunnerShieldItem(new Item.Settings()) {

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_shield.tooltip").formatted(Formatting.GRAY));
            }
        }
    };

    public static final Item GOLDEN_SPEEDRUNNER_SWORD = new SpeedrunnerSwordItem(
            4, new Item.Settings());

    public static final Item GOLDEN_SPEEDRUNNER_SHOVEL = new ShovelItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, new Item.Settings()
            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 2.5F, -3.0F)));

    public static final Item GOLDEN_SPEEDRUNNER_PICKAXE = new PickaxeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, new Item.Settings()
            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 2, -2.8F)));

    public static final Item GOLDEN_SPEEDRUNNER_AXE = new AxeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, new Item.Settings()
            .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 7, -3.0F)));

    public static final Item GOLDEN_SPEEDRUNNER_HOE = new HoeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, new Item.Settings()
            .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F)));

    public static final Item GOLDEN_SPEEDRUNNER_HELMET = new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.HELMET, new Item.Settings().maxCount(1).maxDamage(EquipmentType.HELMET.getMaxDamage(11)));

    public static final Item GOLDEN_SPEEDRUNNER_CHESTPLATE = new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.CHESTPLATE, new Item.Settings().maxCount(1).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(11)));

    public static final Item GOLDEN_SPEEDRUNNER_LEGGINGS = new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.LEGGINGS, new Item.Settings().maxCount(1).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(11)));

    public static final Item GOLDEN_SPEEDRUNNER_BOOTS = new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.BOOTS, new Item.Settings().maxCount(1).maxDamage(EquipmentType.BOOTS.getMaxDamage(11)));

    public static final Item GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE = new SmithingTemplateItem(GOLDEN_SPEEDRUNNER_UPGRADE_APPLIES_TO_TEXT, GOLDEN_SPEEDRUNNER_INGREDIENTS_TEXT, GOLDEN_SPEEDRUNNER_UPGRADE_TEXT, GOLDEN_SPEEDRUNNER_BASE_SLOT_DESCRIPTION_TEXT, GOLDEN_SPEEDRUNNER_ADDITIONS_SLOT_DESCRIPTION_TEXT, SmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(), SmithingTemplateItem.getNetheriteUpgradeEmptyAdditionsSlotTextures());

    public static final Item SPEEDRUNNER_BULK = new Item(
            new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.SPEEDRUNNER_BULK, ModConsumableComponents.SPEEDRUNNER_BULK)) {

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_bulk.tooltip.line1"));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_bulk.tooltip.line2"));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_bulk.tooltip.line3"));
            }
        }
    };

    public static final Item ROTTEN_SPEEDRUNNER_BULK = new Item(
            new Item.Settings().food(ModFoodComponents.ROTTEN_SPEEDRUNNER_BULK, ModConsumableComponents.ROTTEN_SPEEDRUNNER_BULK));

    public static final Item COOKED_FLESH = new Item(
            new Item.Settings().food(ModFoodComponents.COOKED_FLESH));

    public static final Item PIGLIN_PORK = new Item(
            new Item.Settings().food(ModFoodComponents.PIGLIN_PORK));

    public static final Item COOKED_PIGLIN_PORK = new Item(
            new Item.Settings().food(ModFoodComponents.COOKED_PIGLIN_PORK));

    public static final Item GOLDEN_PIGLIN_PORK = new Item(
            new Item.Settings().food(ModFoodComponents.GOLDEN_PIGLIN_PORK));

    public static final Item GOLDEN_BEEF = new Item(
            new Item.Settings().food(ModFoodComponents.GOLDEN_BEEF));

    public static final Item GOLDEN_PORKCHOP = new Item(
            new Item.Settings().food(ModFoodComponents.GOLDEN_PORKCHOP));

    public static final Item GOLDEN_MUTTON = new Item(
            new Item.Settings().food(ModFoodComponents.GOLDEN_MUTTON));

    public static final Item GOLDEN_CHICKEN = new Item(
            new Item.Settings().food(ModFoodComponents.GOLDEN_CHICKEN));

    public static final Item GOLDEN_RABBIT = new Item(
            new Item.Settings().food(ModFoodComponents.GOLDEN_RABBIT));

    public static final Item GOLDEN_COD = new Item(
            new Item.Settings().food(ModFoodComponents.GOLDEN_COD));

    public static final Item GOLDEN_SALMON = new Item(
            new Item.Settings().food(ModFoodComponents.GOLDEN_SALMON));

    public static final Item GOLDEN_BREAD = new Item(
            new Item.Settings().food(ModFoodComponents.GOLDEN_BREAD));

    public static final Item GOLDEN_POTATO = new Item(
            new Item.Settings().food(ModFoodComponents.GOLDEN_POTATO));

    public static final Item GOLDEN_BEETROOT = new Item(
            new Item.Settings().food(ModFoodComponents.GOLDEN_BEETROOT));

    public static final Item IGNEOUS_ROCK = new Item(
            new Item.Settings()) {

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.igneous_rock.tooltip").formatted(Formatting.GRAY));
            }
        }
    };

    public static final Item SPEEDRUNNER_STICK = new Item(
            new Item.Settings());

    public static final Item SPEEDRUNNER_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.SPEEDRUNNER_BOAT_ID, ModBoats.SPEEDRUNNER_BOAT_KEY, false, new Item.Settings().maxCount(1).fireproof());
    public static final Item SPEEDRUNNER_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.SPEEDRUNNER_CHEST_BOAT_ID, ModBoats.SPEEDRUNNER_BOAT_KEY, true, new Item.Settings().maxCount(1).fireproof());
    public static final Item DEAD_SPEEDRUNNER_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.DEAD_SPEEDRUNNER_BOAT_ID, ModBoats.DEAD_SPEEDRUNNER_BOAT_KEY, false, new Item.Settings().maxCount(1).fireproof());
    public static final Item DEAD_SPEEDRUNNER_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.DEAD_SPEEDRUNNER_CHEST_BOAT_ID, ModBoats.DEAD_SPEEDRUNNER_BOAT_KEY, true, new Item.Settings().maxCount(1).fireproof());
    public static final Item CRIMSON_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.CRIMSON_BOAT_ID, ModBoats.CRIMSON_BOAT_KEY, false, new Item.Settings().maxCount(1).fireproof());
    public static final Item CRIMSON_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.CRIMSON_CHEST_BOAT_ID, ModBoats.CRIMSON_BOAT_KEY, true, new Item.Settings().maxCount(1).fireproof());
    public static final Item WARPED_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.WARPED_BOAT_ID, ModBoats.WARPED_BOAT_KEY, false, new Item.Settings().maxCount(1).fireproof());
    public static final Item WARPED_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.WARPED_CHEST_BOAT_ID, ModBoats.WARPED_BOAT_KEY, true, new Item.Settings().maxCount(1).fireproof());

    public static final Item WITHER_BONE = new Item(
            new Item.Settings()) {

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.wither_bone.tooltip").formatted(Formatting.GRAY));
            }
        }
    };

    public static final Item WITHER_SWORD = new WitherSwordItem(new Item.Settings());
    public static final Item ANNUL_EYE = new AnnulEyeItem(new Item.Settings());
    public static final Item SPEEDRUNNERS_EYE = new SpeedrunnersEyeItem(new Item.Settings());
    public static final Item INFERNO_EYE = new InfernoEyeItem(new Item.Settings());
    public static final Item PIGLIN_AWAKENER = new PiglinAwakenerItem(new Item.Settings());
    public static final Item BLAZE_SPOTTER = new BlazeSpotterItem(new Item.Settings());
    public static final Item RAID_ERADICATOR = new RaidEradicatorItem(new Item.Settings());
    public static final Item ENDER_THRUSTER = new EnderThrusterItem(new Item.Settings());
    public static final Item DRAGONS_SWORD = new DragonsSwordItem(new Item.Settings());
    public static final Item DRAGONS_PEARL = new DragonsPearlItem(new Item.Settings());
    public static final Item INFINI_PEARL = new InfiniPearlItem(new Item.Settings());

    /**
     * Registers all Speedrunner Mod {@code items.}
     */
    public static void registerItems() {
        registerItem("speedrunner_ingot", SPEEDRUNNER_INGOT);
        registerItem("speedrunner_nugget", SPEEDRUNNER_NUGGET);
        registerItem("raw_speedrunner", RAW_SPEEDRUNNER);
        registerItem("speedrunner_sword", SPEEDRUNNER_SWORD);
        registerItem("speedrunner_shovel", SPEEDRUNNER_SHOVEL);
        registerItem("speedrunner_pickaxe", SPEEDRUNNER_PICKAXE);
        registerItem("speedrunner_axe", SPEEDRUNNER_AXE);
        registerItem("speedrunner_hoe", SPEEDRUNNER_HOE);
        registerItem("speedrunner_helmet", SPEEDRUNNER_HELMET);
        registerItem("speedrunner_chestplate", SPEEDRUNNER_CHESTPLATE);
        registerItem("speedrunner_leggings", SPEEDRUNNER_LEGGINGS);
        registerItem("speedrunner_boots", SPEEDRUNNER_BOOTS);
        registerItem("speedrunner_bow", SPEEDRUNNER_BOW);
        registerItem("speedrunner_crossbow", SPEEDRUNNER_CROSSBOW);
        registerItem("speedrunner_shears", SPEEDRUNNER_SHEARS);
        registerItem("speedrunner_flint_and_steel", SPEEDRUNNER_FLINT_AND_STEEL);
        registerItem("speedrunner_shield", SPEEDRUNNER_SHIELD);
        registerItem("golden_speedrunner_sword", GOLDEN_SPEEDRUNNER_SWORD);
        registerItem("golden_speedrunner_shovel", GOLDEN_SPEEDRUNNER_SHOVEL);
        registerItem("golden_speedrunner_pickaxe", GOLDEN_SPEEDRUNNER_PICKAXE);
        registerItem("golden_speedrunner_axe", GOLDEN_SPEEDRUNNER_AXE);
        registerItem("golden_speedrunner_hoe", GOLDEN_SPEEDRUNNER_HOE);
        registerItem("golden_speedrunner_helmet", GOLDEN_SPEEDRUNNER_HELMET);
        registerItem("golden_speedrunner_chestplate", GOLDEN_SPEEDRUNNER_CHESTPLATE);
        registerItem("golden_speedrunner_leggings", GOLDEN_SPEEDRUNNER_LEGGINGS);
        registerItem("golden_speedrunner_boots", GOLDEN_SPEEDRUNNER_BOOTS);
        registerItem("golden_speedrunner_upgrade_smithing_template", GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE);
        registerItem("speedrunner_bulk", SPEEDRUNNER_BULK);
        registerItem("rotten_speedrunner_bulk", ROTTEN_SPEEDRUNNER_BULK);
        registerItem("cooked_flesh", COOKED_FLESH);
        registerItem("piglin_pork", PIGLIN_PORK);
        registerItem("cooked_piglin_pork", COOKED_PIGLIN_PORK);
        registerItem("golden_piglin_pork", GOLDEN_PIGLIN_PORK);
        registerItem("golden_beef", GOLDEN_BEEF);
        registerItem("golden_porkchop", GOLDEN_PORKCHOP);
        registerItem("golden_mutton", GOLDEN_MUTTON);
        registerItem("golden_chicken", GOLDEN_CHICKEN);
        registerItem("golden_rabbit", GOLDEN_RABBIT);
        registerItem("golden_cod", GOLDEN_COD);
        registerItem("golden_salmon", GOLDEN_SALMON);
        registerItem("golden_bread", GOLDEN_BREAD);
        registerItem("golden_potato", GOLDEN_POTATO);
        registerItem("golden_beetroot", GOLDEN_BEETROOT);
        registerItem("igneous_rock", IGNEOUS_ROCK);
        registerItem("speedrunner_stick", SPEEDRUNNER_STICK);
        registerItem("wither_bone", WITHER_BONE);
        registerItem("wither_sword", WITHER_SWORD);
        registerItem("annul_eye", ANNUL_EYE);
        registerItem("speedrunners_eye", SPEEDRUNNERS_EYE);
        registerItem("inferno_eye", INFERNO_EYE);
        registerItem("piglin_awakener", PIGLIN_AWAKENER);
        registerItem("blaze_spotter", BLAZE_SPOTTER);
        registerItem("raid_eradicator", RAID_ERADICATOR);
        registerItem("ender_thruster", ENDER_THRUSTER);
        registerItem("dragons_sword", DRAGONS_SWORD);
        registerItem("dragons_pearl", DRAGONS_PEARL);
        registerItem("infini_pearl", INFINI_PEARL);
    }

    /**
     * Registers an {@code item}.
     */
    protected static void registerItem(String path, Item item) {
        Registry.register(Registries.ITEM, ofSpeedrunnerMod(path), item);
    }
}