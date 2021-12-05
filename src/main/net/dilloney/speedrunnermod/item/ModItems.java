package net.dilloney.speedrunnermod.item;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.block.ModBlocks;
import net.dilloney.speedrunnermod.client.render.SpeedrunnerShieldRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item SPEEDRUNNER_INGOT = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS));

    public static final Item SPEEDRUNNER_NUGGET = new Item(
            new Item.Settings().group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS));

    public static final Item RAW_SPEEDRUNNER = new Item(
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

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_ingot"), ModItems.SPEEDRUNNER_INGOT);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_nugget"), ModItems.SPEEDRUNNER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "raw_speedrunner"), ModItems.RAW_SPEEDRUNNER);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_sword"), ModItems.SPEEDRUNNER_SWORD);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_shovel"), ModItems.SPEEDRUNNER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_pickaxe"), ModItems.SPEEDRUNNER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_axe"), ModItems.SPEEDRUNNER_AXE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_hoe"), ModItems.SPEEDRUNNER_HOE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_helmet"), ModItems.SPEEDRUNNER_HELMET);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_chestplate"), ModItems.SPEEDRUNNER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_leggings"), ModItems.SPEEDRUNNER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_boots"), ModItems.SPEEDRUNNER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_bow"), ModItems.SPEEDRUNNER_BOW);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_crossbow"), ModItems.SPEEDRUNNER_CROSSBOW);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_shears"), ModItems.SPEEDRUNNER_SHEARS);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_flint_and_steel"), ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_shield"), ModItems.SPEEDRUNNER_SHIELD);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_bulk"), ModItems.SPEEDRUNNER_BULK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "rotten_speedrunner_bulk"), ModItems.ROTTEN_SPEEDRUNNER_BULK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "igneous_rock"), ModItems.IGNEOUS_ROCK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "cooked_piglin_pork"), ModItems.COOKED_PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "piglin_pork"), ModItems.PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "cooked_flesh"), ModItems.COOKED_FLESH);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "inferno_eye"), ModItems.INFERNO_EYE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "annul_eye"), ModItems.ANNUL_EYE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_block"), ModItems.SPEEDRUNNER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "raw_speedrunner_block"), ModItems.RAW_SPEEDRUNNER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_ore"), ModItems.SPEEDRUNNER_ORE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "deepslate_speedrunner_ore"), ModItems.DEEPSLATE_SPEEDRUNNER_ORE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "nether_speedrunner_ore"), ModItems.NETHER_SPEEDRUNNER_ORE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "igneous_ore"), ModItems.IGNEOUS_ORE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "deepslate_igneous_ore"), ModItems.DEEPSLATE_IGNEOUS_ORE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "nether_igneous_ore"), ModItems.NETHER_IGNEOUS_ORE);
    }

    @Environment(EnvType.CLIENT)
    public static void clinit() {
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW.asItem(), new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW.asItem(), new Identifier("pulling"), (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW.asItem(), new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return SpeedrunnerCrossbowItem.isCharged(stack) ? 0.0F : (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (float)SpeedrunnerCrossbowItem.getPullTime(stack);
            }
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW.asItem(), new Identifier("pulling"), (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !SpeedrunnerCrossbowItem.isCharged(stack) ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW.asItem(), new Identifier("charged"), (stack, world, entity, seed) -> {
            return entity != null && SpeedrunnerCrossbowItem.isCharged(stack) ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW.asItem(), new Identifier("firework"), (stack, world, entity, seed) -> {
            return entity != null && SpeedrunnerCrossbowItem.isCharged(stack) && SpeedrunnerCrossbowItem.hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_SHIELD, new Identifier("blocking"), (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });

        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SPEEDRUNNER_SHIELD, new SpeedrunnerShieldRenderer());

        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            if (atlasTexture.getId() == SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE) {
                registry.register(new Identifier(SpeedrunnerMod.MOD_ID, "entity/speedrunner_shield_base"));
            }
        });
    }
}