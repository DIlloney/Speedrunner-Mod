package com.dilloney.speedrunnermod.util;

import com.dilloney.speedrunnermod.block.ModBlocks;
import com.dilloney.speedrunnermod.client.render.SpeedrunnerShieldRenderer;
import com.dilloney.speedrunnermod.item.ModItems;
import com.dilloney.speedrunnermod.item.SpeedrunnerCrossbowItem;
import com.dilloney.speedrunnermod.option.OptionsFileManager;
import com.dilloney.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe;
import com.dilloney.speedrunnermod.tag.ModBlockTags;
import com.dilloney.speedrunnermod.tag.ModItemTags;
import com.dilloney.speedrunnermod.world.gen.feature.ModConfiguredFeatures;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class ModRegistry {

    public static void loadOptions() {
        OptionsFileManager.loadAll();
    }

    public static void loadItems() {
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_ingot"), ModItems.SPEEDRUNNER_INGOT);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_nugget"), ModItems.SPEEDRUNNER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "raw_speedrunner"), ModItems.RAW_SPEEDRUNNER);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_sword"), ModItems.SPEEDRUNNER_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shovel"), ModItems.SPEEDRUNNER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_pickaxe"), ModItems.SPEEDRUNNER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_axe"), ModItems.SPEEDRUNNER_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_hoe"), ModItems.SPEEDRUNNER_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_helmet"), ModItems.SPEEDRUNNER_HELMET);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_chestplate"), ModItems.SPEEDRUNNER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_leggings"), ModItems.SPEEDRUNNER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_boots"), ModItems.SPEEDRUNNER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_bow"), ModItems.SPEEDRUNNER_BOW);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_crossbow"), ModItems.SPEEDRUNNER_CROSSBOW);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shears"), ModItems.SPEEDRUNNER_SHEARS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_flint_and_steel"), ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shield"), ModItems.SPEEDRUNNER_SHIELD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_bulk"), ModItems.SPEEDRUNNER_BULK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "rotten_speedrunner_bulk"), ModItems.ROTTEN_SPEEDRUNNER_BULK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "igneous_rock"), ModItems.IGNEOUS_ROCK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_sword"), ModItems.GOLDEN_SPEEDRUNNER_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_shovel"), ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_pickaxe"), ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_axe"), ModItems.GOLDEN_SPEEDRUNNER_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_hoe"), ModItems.GOLDEN_SPEEDRUNNER_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_helmet"), ModItems.GOLDEN_SPEEDRUNNER_HELMET);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_chestplate"), ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_leggings"), ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_boots"), ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "cooked_piglin_pork"), ModItems.COOKED_PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "piglin_pork"), ModItems.PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "cooked_flesh"), ModItems.COOKED_FLESH);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "inferno_eye"), ModItems.INFERNO_EYE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "annul_eye"), ModItems.ANNUL_EYE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_sword"), ModItems.ANDESITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_shovel"), ModItems.ANDESITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_pickaxe"), ModItems.ANDESITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_axe"), ModItems.ANDESITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_hoe"), ModItems.ANDESITE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_sword"), ModItems.BLACKSTONE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_shovel"), ModItems.BLACKSTONE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_pickaxe"), ModItems.BLACKSTONE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_axe"), ModItems.BLACKSTONE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_hoe"), ModItems.BLACKSTONE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_sword"), ModItems.DIORITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_shovel"), ModItems.DIORITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_pickaxe"), ModItems.DIORITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_axe"), ModItems.DIORITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_hoe"), ModItems.DIORITE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_sword"), ModItems.GRANITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_shovel"), ModItems.GRANITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_pickaxe"), ModItems.GRANITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_axe"), ModItems.GRANITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_hoe"), ModItems.GRANITE_HOE);
    }

    public static void loadBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_block"), ModBlocks.SPEEDRUNNER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_ore"), ModBlocks.SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "deepslate_speedrunner_ore"), ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_speedrunner_ore"), ModBlocks.NETHER_SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "igneous_ore"), ModBlocks.IGNEOUS_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "deepslate_igneous_ore"), ModBlocks.DEEPSLATE_IGNEOUS_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_igneous_ore"), ModBlocks.NETHER_IGNEOUS_ORE);
    }

    public static void loadBlockItems() {
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_block"), ModBlocks.SPEEDRUNNER_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_ore"), ModBlocks.SPEEDRUNNER_ORE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "deepslate_speedrunner_ore"), ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "nether_speedrunner_ore"), ModBlocks.NETHER_SPEEDRUNNER_ORE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "igneous_ore"), ModBlocks.IGNEOUS_ORE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "deepslate_igneous_ore"), ModBlocks.DEEPSLATE_IGNEOUS_ORE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "nether_igneous_ore"), ModBlocks.NETHER_IGNEOUS_ORE_BLOCK_ITEM);
    }

    public static void loadConfiguredFeatures() {
        RegistryKey<ConfiguredFeature<?, ?>> speedrunnerOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "speedrunner_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, speedrunnerOre.getValue(), ModConfiguredFeatures.SPEEDRUNNER_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> netherSpeedrunnerOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "nether_speedrunner_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, netherSpeedrunnerOre.getValue(), ModConfiguredFeatures.NETHER_SPEEDRUNNER_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> igneousOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "igneous_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, igneousOre.getValue(), ModConfiguredFeatures.IGNEOUS_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> netherIgneousOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "nether_igneous_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, netherIgneousOre.getValue(), ModConfiguredFeatures.NETHER_IGNEOUS_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> diamondOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "diamond_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, diamondOre.getValue(), ModConfiguredFeatures.DIAMOND_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> ancientDebris = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "ancient_debris_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ancientDebris.getValue(), ModConfiguredFeatures.ANCIENT_DEBRIS);
    }

    public static void loadItemTags() {
        ModItemTags.IRON_INGOTS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "iron_ingots"));
        ModItemTags.IRON_NUGGETS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "iron_nuggets"));
        ModItemTags.IRON_BLOCKS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "iron_blocks"));
        ModItemTags.COBBLESTONES = TagFactory.ITEM.create(new Identifier("speedrunnermod", "cobblestones"));
    }

    public static void loadBlockTags() {
        ModBlockTags.ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/0_hardness"));
        ModBlockTags.ZERO_ONE_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/0-1_hardness"));
        ModBlockTags.ZERO_TWO_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/0-2_hardness"));
        ModBlockTags.ZERO_THREESEVEN_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/0-37_hardness"));
        ModBlockTags.ZERO_FOUR_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/0-4_hardness"));
        ModBlockTags.ZERO_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/0-5_hardness"));
        ModBlockTags.ZERO_SIX_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/0-6_hardness"));
        ModBlockTags.ZERO_SIXFIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/0-65_hardness"));
        ModBlockTags.ZERO_SEVEN_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/0-7_hardness"));
        ModBlockTags.ZERO_EIGHT_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/0-8_hardness"));
        ModBlockTags.ONE_ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/1-0_hardness"));
        ModBlockTags.ONE_THREE_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/1-3_hardness"));
        ModBlockTags.ONE_FOUR_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/1-4_hardness"));
        ModBlockTags.ONE_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/1-5_hardness"));
        ModBlockTags.ONE_SIX_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/1-6_hardness"));
        ModBlockTags.TWO_ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/2-0_hardness"));
        ModBlockTags.TWO_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/2-5_hardness"));
        ModBlockTags.THREE_ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/3-0_hardness"));
        ModBlockTags.TEN_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/10_hardness"));
        ModBlockTags.TWENTY_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "block_hardness/25_hardness"));
    }

    public static void loadMiscellaneous() {
        Registry.register(Registry.RECIPE_SERIALIZER, "speedrunnermod:crafting_special_speedrunner_shield_decoration", SpeedrunnerShieldDecorationRecipe.SPEEDRUNNER_SHIELD_DECORATION_RECIPE);
    }

    @Environment(EnvType.CLIENT)
    public static void loadModels() {
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

        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlaxTexture, registry) -> {
            if (atlaxTexture.getId() == SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE) {
                registry.register(new Identifier("speedrunnermod:entity/speedrunner_shield_base"));
            }
        });
    }
}
