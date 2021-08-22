package com.dilloney.speedrunnermod.util;

import com.dilloney.speedrunnermod.block.ModBlocks;
import com.dilloney.speedrunnermod.client.render.SpeedrunnerShieldRenderer;
import com.dilloney.speedrunnermod.item.ModItems;
import com.dilloney.speedrunnermod.item.SpeedrunnerCrossbowItem;
import com.dilloney.speedrunnermod.loot.ModLootTables;
import com.dilloney.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe;
import com.dilloney.speedrunnermod.sound.ModSoundEvents;
import com.dilloney.speedrunnermod.tag.ModBlockTags;
import com.dilloney.speedrunnermod.tag.ModItemTags;
import com.dilloney.speedrunnermod.util.manhunt.PersistanceItems;
import com.dilloney.speedrunnermod.util.manhunt.Speedrunners;
import com.dilloney.speedrunnermod.world.feature.OreGeneration;
import com.dilloney.speedrunnermod.world.feature.StructureGeneration;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

public final class ModRegistry {

    public static void registerItems() {
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
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_fishing_rod"), ModItems.SPEEDRUNNER_FISHING_ROD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_carrot_on_a_stick"), ModItems.SPEEDRUNNER_CARROT_ON_A_STICK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_warped_fungus_on_a_stick"), ModItems.SPEEDRUNNER_WARPED_FUNGUS_ON_A_STICK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shield"), ModItems.SPEEDRUNNER_SHIELD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_music_disc"), ModItems.SPEEDRUNNER_MUSIC_DISC);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_apple"), ModItems.SPEEDRUNNER_APPLE);
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
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_piglin_pork"), ModItems.GOLDEN_PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "cooked_flesh"), ModItems.COOKED_FLESH);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_steak"), ModItems.GOLDEN_STEAK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_porkchop"), ModItems.GOLDEN_PORKCHOP);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_mutton"), ModItems.GOLDEN_MUTTON);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_chicken"), ModItems.GOLDEN_CHICKEN);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_rabbit"), ModItems.GOLDEN_RABBIT);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_cod"), ModItems.GOLDEN_COD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_salmon"), ModItems.GOLDEN_SALMON);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_bread"), ModItems.GOLDEN_BREAD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_potato"), ModItems.GOLDEN_POTATO);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_beetroot"), ModItems.GOLDEN_BEETROOT);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "inferno_eye"), ModItems.INFERNO_EYE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "annul_eye"), ModItems.ANNUL_EYE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_sword"), ModItems.ANDESITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_shovel"), ModItems.ANDESITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_pickaxe"), ModItems.ANDESITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_axe"), ModItems.ANDESITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_hoe"), ModItems.ANDESITE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_sword"), ModItems.GRANITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_shovel"), ModItems.GRANITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_pickaxe"), ModItems.GRANITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_axe"), ModItems.GRANITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_hoe"), ModItems.GRANITE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_sword"), ModItems.DIORITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_shovel"), ModItems.DIORITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_pickaxe"), ModItems.DIORITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_axe"), ModItems.DIORITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_hoe"), ModItems.DIORITE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_sword"), ModItems.BLACKSTONE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_shovel"), ModItems.BLACKSTONE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_pickaxe"), ModItems.BLACKSTONE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_axe"), ModItems.BLACKSTONE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_hoe"), ModItems.BLACKSTONE_HOE);
    }

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_block"), ModBlocks.SPEEDRUNNER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_ore"), ModBlocks.SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "deepslate_speedrunner_ore"), ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_speedrunner_ore"), ModBlocks.NETHER_SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "igneous_ore"), ModBlocks.IGNEOUS_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "deepslate_igneous_ore"), ModBlocks.DEEPSLATE_IGNEOUS_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_igneous_ore"), ModBlocks.NETHER_IGNEOUS_ORE);
    }

    public static void registerBlockItems() {
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_block"), ModBlocks.SPEEDRUNNER_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_ore"), ModBlocks.SPEEDRUNNER_ORE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "deepslate_speedrunner_ore"), ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "nether_speedrunner_ore"), ModBlocks.NETHER_SPEEDRUNNER_ORE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "igneous_ore"), ModBlocks.IGNEOUS_ORE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "deepslate_igneous_ore"), ModBlocks.DEEPSLATE_IGNEOUS_ORE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "nether_igneous_ore"), ModBlocks.NETHER_IGNEOUS_ORE_BLOCK_ITEM);
    }

    public static void registerSoundEvents() {
        Registry.register(Registry.SOUND_EVENT, new Identifier("speedrunnermod", "sounds/entity/giant_ambient"), ModSoundEvents.ENTITY_GIANT_AMBIENT);
        Registry.register(Registry.SOUND_EVENT, new Identifier("speedrunnermod", "sounds/entity/giant_hurt"), ModSoundEvents.ENTITY_GIANT_HURT);
        Registry.register(Registry.SOUND_EVENT, new Identifier("speedrunnermod", "sounds/entity/giant_death"), ModSoundEvents.ENTITY_GIANT_DEATH);
        Registry.register(Registry.SOUND_EVENT, new Identifier("speedrunnermod", "sounds/entity/giant_step"), ModSoundEvents.ENTITY_GIANT_STEP);
        Registry.register(Registry.SOUND_EVENT, new Identifier("speedrunnermod", "sounds/records/speedrunner_music_disc"), ModSoundEvents.SPEEDRUNNER_MUSIC_DISC);
    }

    public static void registerConfiguredFeatures() {
        RegistryKey<ConfiguredFeature<?, ?>> speedrunnerOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "speedrunner_ore_configured_feature_worldgen"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, speedrunnerOreOverworld.getValue(), OreGeneration.SPEEDRUNNER_ORE_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, speedrunnerOreOverworld);

        RegistryKey<ConfiguredFeature<?, ?>> deepslateSpeedrunnreOreOverowlrd = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "deepslate_speedrunner_ore_configured_feature_worldgen"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, deepslateSpeedrunnreOreOverowlrd.getValue(), OreGeneration.DEEPSLATE_SPEEDRUNNER_ORE_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, deepslateSpeedrunnreOreOverowlrd);

        RegistryKey<ConfiguredFeature<?, ?>> igneousOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "igneous_ore_configured_feature_worldgen"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, igneousOreOverworld.getValue(), OreGeneration.IGNEOUS_ORE_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, igneousOreOverworld);

        RegistryKey<ConfiguredFeature<?, ?>> deepslateIgneousOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "deepslate_igneous_ore_configured_feature_worldgen"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, deepslateIgneousOreOverworld.getValue(), OreGeneration.DEEPSLATE_IGNEOUS_ORE_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, deepslateIgneousOreOverworld);

        RegistryKey<ConfiguredFeature<?, ?>> igneousNetherOreNether = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "nether_igneous_ore_configured_feature_worldgen"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, igneousNetherOreNether.getValue(), OreGeneration.NETHER_IGNEOUS_ORE_NETHER);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, igneousNetherOreNether);

        RegistryKey<ConfiguredFeature<?, ?>> speedrunnerNetherOreNether = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "nether_speedrunner_ore_configured_feature_worldgen"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, speedrunnerNetherOreNether.getValue(), OreGeneration.NETHER_SPEEDRUNNER_ORE_NETHER);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, speedrunnerNetherOreNether);

        if (OPTIONS.modifiedWorldGeneration && OPTIONS.getModDifficulty() == 1 && !OPTIONS.doomMode) {
            RegistryKey<ConfiguredFeature<?, ?>> diamondOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                    new Identifier("speedrunnermod", "diamond_ore_configured_feature_worldgen"));
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, diamondOre.getValue(), OreGeneration.DIAMOND_ORE);
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, diamondOre);

            RegistryKey<ConfiguredFeature<?, ?>> deepslateDiamondOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                    new Identifier("speedrunnermod", "deepslate_diamond_ore_configured_feature_worldgen"));
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, deepslateDiamondOre.getValue(), OreGeneration.DEEPSLATE_DIAMOND_ORE);
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, deepslateDiamondOre);
        }

        if (OPTIONS.makeStructuresMoreCommon) {
            StructureGeneration.registerModifiedStructureGeneration();
        }
    }

    public static void registerTags() {
        ModItemTags.SPEEDRUNNER_ITEMS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "speedrunner_items"));
        ModItemTags.SPEEDRUNNER_MOD_ITEMS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "speedrunner_mod_items"));
        ModItemTags.SPEED_EFFECT_ITEMS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "speed_effect_items"));
        ModItemTags.DOLPHINS_GRACE_EFFECT_ITEMS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "dolphins_grace_effect_items"));
        ModItemTags.PIGLIN_SAFE_ARMOR = TagFactory.ITEM.create(new Identifier("speedrunnermod", "piglin_safe_armor"));
        ModItemTags.BARTERING_ITEMS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "bartering_items"));
        ModItemTags.GOLDEN_ITEMS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "golden_items"));
        ModItemTags.BOWS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "bows"));
        ModItemTags.CROSSBOWS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "crossbows"));
        ModItemTags.SHEARS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "shears"));
        ModItemTags.FISHING_RODS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "fishing_rods"));
        ModItemTags.FLINT_AND_STEELS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "flint_and_steels"));
        ModItemTags.SHIELDS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "shields"));
        ModItemTags.CARROT_ON_STICKS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "carrot_on_sticks"));
        ModItemTags.WARPED_FUNGUS_ON_STICKS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "warped_fungus_on_sticks"));
        ModItemTags.FIREPROOFS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "fireproofs"));
        ModItemTags.IRON_INGOTS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "iron_ingots"));
        ModItemTags.IRON_NUGGETS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "iron_nuggets"));
        ModItemTags.IRON_BLOCKS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "iron_blocks"));
        ModItemTags.COBBLESTONES = TagFactory.ITEM.create(new Identifier("speedrunnermod", "cobblestones"));
        ModItemTags.IRON_INGOTS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "iron_ingots"));
        ModItemTags.IRON_NUGGETS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "iron_nuggets"));
        ModItemTags.IRON_BLOCKS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "iron_blocks"));
        ModItemTags.COBBLESTONES = TagFactory.ITEM.create(new Identifier("speedrunnermod", "cobblestones"));
        ModItemTags.ZEROTWO_TO_ZEROONE_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/02-01_hardness"));
        ModItemTags.ZEROFIVE_TO_ZEROFOUR_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/05-04_hardness"));
        ModItemTags.ZEROEIGHT_TO_ZEROSIXFIVE_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/08-065_hardness"));
        ModItemTags.ZEROEIGHT_TO_ZEROSEVEN_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/08-07_hardness"));
        ModItemTags.ONEFIVE_TO_ONETHREE_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/15-13_hardness"));
        ModItemTags.TWOZERO_TO_ZEROEIGHT_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/2-08_hardness"));
        ModItemTags.TWOZERO_TO_ONETHREE_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/2-13_hardness"));
        ModItemTags.TWOZERO_TO_ONEFOUR_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/2-14_hardness"));
        ModItemTags.TWOZERO_TO_ONEFIVE_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/2-15_hardness"));
        ModItemTags.TWOFIVE_TO_ONEFIVE_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/25-15_hardness"));
        ModItemTags.THREEZERO_TO_ONEFIVE_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/3-15_hardness"));
        ModItemTags.THREEFIVE_TO_TWOZERO_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/35-2_hardness"));
        ModItemTags.THREEFIVE_TO_TWOFIVE_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/35-25_hardness"));
        ModItemTags.FIVEZERO_TO_THREEZERO_HARDNESS = TagFactory.ITEM.create(new Identifier("speedrunnermod", "tooltips/5-3_hardness"));
        ModBlockTags.NETHER_PORTAL_BASE_BLOCKS = TagFactory.BLOCK.create(new Identifier("speedrunnermod", "nether_portal_base_blocks"));
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

    public static void registerMiscellaneous() {
        Registry.register(Registry.RECIPE_SERIALIZER, "speedrunnermod:crafting_special_speedrunner_shield_decoration", SpeedrunnerShieldDecorationRecipe.SPEEDRUNNER_SHIELD_DECORATION_RECIPE);
        DispenserBlock.registerBehavior(ModItems.SPEEDRUNNER_SHEARS, new ShearsDispenserBehavior());
        DispenserBlock.registerBehavior(ModItems.SPEEDRUNNER_SHIELD, ArmorItem.DISPENSER_BEHAVIOR);
        if (OPTIONS.modifiedLootTables) {
            ModLootTables.registerModifiedLootTables();
        }
    }

    public static void registerManhuntModeCommands() {
        ServerTickEvents.END_SERVER_TICK.register((server) -> {
            ArrayList<PersistanceItems> newList = PersistanceItems.getItems();
            if (newList != null) {
                Iterator var2 = newList.iterator();

                while(var2.hasNext()) {
                    PersistanceItems player = (PersistanceItems)var2.next();
                    if (server.getPlayerManager().getPlayer(UUID.fromString(player.getPlayerUUID())) != null && server.getPlayerManager().getPlayer(UUID.fromString(player.getPlayerUUID())).isAlive()) {
                        server.getPlayerManager().getPlayer(UUID.fromString(player.getPlayerUUID())).giveItemStack(player.getStack());
                    }
                }
            }

        });
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandManager.literal("compass").requires((source) -> {
                return source.hasPermissionLevel(4);
            })).executes((ctx) -> {
                ItemStack hunterCompass = new ItemStack(Items.COMPASS);
                NbtCompound tag = hunterCompass.writeNbt(new NbtCompound());
                tag.putBoolean("huntercompass", true);
                hunterCompass.setTag(tag);
                ((ServerCommandSource)ctx.getSource()).getPlayer().giveItemStack(hunterCompass);
                return 1;
            }));
        });
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandManager.literal("speedrunner").requires((source) -> {
                return source.hasPermissionLevel(4);
            })).then(CommandManager.literal("add").then((RequiredArgumentBuilder)CommandManager.argument("targets", EntityArgumentType.players()).executes((ctx) -> {
                ((ServerCommandSource)ctx.getSource()).sendFeedback(new LiteralText(EntityArgumentType.getPlayer(ctx, "targets").getEntityName() + " successfully added"), false);
                Speedrunners.addRunner(EntityArgumentType.getPlayer(ctx, "targets").getUuidAsString());
                return 1;
            })))).then(CommandManager.literal("remove").then((RequiredArgumentBuilder)CommandManager.argument("targets", EntityArgumentType.players()).executes((ctx) -> {
                ((ServerCommandSource)ctx.getSource()).sendFeedback(new LiteralText(EntityArgumentType.getPlayer(ctx, "targets").getEntityName() + " successfully removed"), false);
                Speedrunners.removeRunner(EntityArgumentType.getPlayer(ctx, "targets").getUuidAsString());
                return 1;
            }))).then(CommandManager.literal("list").executes((ctx) -> {
                ArrayList<String> nameList = Speedrunners.getRunners(((ServerCommandSource)ctx.getSource()).getWorld(), true);
                Iterator var2 = nameList.iterator();

                while(var2.hasNext()) {
                    String str = (String)var2.next();
                    ((ServerCommandSource)ctx.getSource()).sendFeedback(new LiteralText(str), false);
                }

                return 1;
            })));
        });
    }

    @Environment(EnvType.CLIENT)
    public static void registerModels() {
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
            return entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_FISHING_ROD, new Identifier("cast"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                boolean bl = entity.getMainHandStack() == stack;
                boolean bl2 = entity.getOffHandStack() == stack;
                if (entity.getMainHandStack().getItem() instanceof FishingRodItem) {
                    bl2 = false;
                }

                return (bl || bl2) && entity instanceof PlayerEntity && ((PlayerEntity)entity).fishHook != null ? 1.0F : 0.0F;
            }
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
