package com.dilloney.speedrunnermod.registry;

import com.dilloney.speedrunnermod.blocks.ModBlocks;
import com.dilloney.speedrunnermod.items.ModItems;
import com.dilloney.speedrunnermod.mixins.misc.StructuresConfigAccessor;
import com.dilloney.speedrunnermod.util.UniqueItemRegistry;
import com.dilloney.speedrunnermod.world.feature.OreGeneration;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.HashMap;
import java.util.Map;

public class ModRegistry {

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_ingot"), ModItems.SPEEDRUNNER_INGOT);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_nugget"), ModItems.SPEEDRUNNER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "igneous_rock"), ModItems.IGNEOUS_ROCK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "eye_of_inferno"), ModItems.EYE_OF_INFERNO);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "eye_of_annul"), ModItems.EYE_OF_ANNUL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_sword"), ModItems.SPEEDRUNNER_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shovel"), ModItems.SPEEDRUNNER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_pickaxe"), ModItems.SPEEDRUNNER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_axe"), ModItems.SPEEDRUNNER_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_hoe"), ModItems.SPEEDRUNNER_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_bow"), ModItems.SPEEDRUNNER_BOW);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shears"), ModItems.SPEEDRUNNER_SHEARS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_helmet"), ModItems.SPEEDRUNNER_HELMET);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_chestplate"), ModItems.SPEEDRUNNER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_leggings"), ModItems.SPEEDRUNNER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_boots"), ModItems.SPEEDRUNNER_BOOTS);
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
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "raw_piglin_pork"), ModItems.RAW_PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "cooked_piglin_pork"), ModItems.COOKED_PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_bulk"), ModItems.SPEEDRUNNER_BULK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "rotten_speedrunner_bulk"), ModItems.ROTTEN_SPEEDRUNNER_BULK);
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
    }

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_block"), ModBlocks.SPEEDRUNNER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_ore"), ModBlocks.SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "deepslate_speedrunner_ore"), ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_speedrunner_ore"), ModBlocks.SPEEDRUNNER_NETHER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "igneous_ore"), ModBlocks.IGNEOUS_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "deepslate_igneous_ore"), ModBlocks.DEEPSLATE_IGNEOUS_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_igneous_ore"), ModBlocks.IGNEOUS_NETHER_ORE);
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

    public static void registerStructureConfigs() {
        ServerWorldEvents.LOAD.register((server, world) -> {
            Map<StructureFeature<?>, StructureConfig> map = new HashMap<>(world.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());

            map.computeIfPresent(StructureFeature.RUINED_PORTAL, (structureFeature, structureConfig) -> {
                return new StructureConfig(7, 6, 34222645);
            });
            map.computeIfPresent(StructureFeature.IGLOO, (structureFeature, structureConfig) -> {
                return new StructureConfig(18, 14, 14357618);
            });
            map.computeIfPresent(StructureFeature.VILLAGE, (structureFeature, structureConfig) -> {
                return new StructureConfig(12, 9, 10387312);
            });
            map.computeIfPresent(StructureFeature.DESERT_PYRAMID, (structureFeature, structureConfig) -> {
                return new StructureConfig(10, 8, 14357617);
            });
            map.computeIfPresent(StructureFeature.SHIPWRECK, (structureFeature, structureConfig) -> {
                return new StructureConfig(10, 8, 165745295);
            });
            map.computeIfPresent(StructureFeature.FORTRESS, (structureFeature, structureConfig) -> {
                return new StructureConfig(10, 9, 30084232);
            });
            map.computeIfPresent(StructureFeature.BASTION_REMNANT, (structureFeature, structureConfig) -> {
                return new StructureConfig(10, 8, 30084232);
            });

            ImmutableMap<StructureFeature<?>, StructureConfig> immutableMap = ImmutableMap.copyOf(map);

            ((StructuresConfigAccessor) world.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(immutableMap);
        });
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

        RegistryKey<ConfiguredFeature<?, ?>> diamondOreMesaJungleMountains = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "diamond_ore_mesa_jungle_mountains_configured_feature_worldgen"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, diamondOreMesaJungleMountains.getValue(), OreGeneration.DIAMOND_ORE_MESA_JUNGLE_MOUNTAINS);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.MESA), GenerationStep.Feature.UNDERGROUND_ORES, diamondOreMesaJungleMountains);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.JUNGLE), GenerationStep.Feature.UNDERGROUND_ORES, diamondOreMesaJungleMountains);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.EXTREME_HILLS), GenerationStep.Feature.UNDERGROUND_ORES, diamondOreMesaJungleMountains);

        RegistryKey<ConfiguredFeature<?, ?>> deepslateDiamondOreMesaJungleMountains = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("speedrunnermod", "deepslate_diamond_ore_mesa_jungle_mountains_configured_feature_worldgen"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, deepslateDiamondOreMesaJungleMountains.getValue(), OreGeneration.DEEPSLATE_DIAMOND_ORE_MESA_JUNGLE_MOUNTAINS);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.MESA), GenerationStep.Feature.UNDERGROUND_ORES, deepslateDiamondOreMesaJungleMountains);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.JUNGLE), GenerationStep.Feature.UNDERGROUND_ORES, deepslateDiamondOreMesaJungleMountains);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.EXTREME_HILLS), GenerationStep.Feature.UNDERGROUND_ORES, deepslateDiamondOreMesaJungleMountains);
    }

    public static void registerUniqueItems() {
        UniqueItemRegistry.BOW.addItemToRegistry(ModItems.SPEEDRUNNER_BOW.getDefaultStack().getItem());
        UniqueItemRegistry.BOW.addItemToRegistry(Items.BOW.getDefaultStack().getItem());
        UniqueItemRegistry.SHEARS.addItemToRegistry(ModItems.SPEEDRUNNER_SHEARS.getDefaultStack().getItem());
        UniqueItemRegistry.SHEARS.addItemToRegistry(Items.SHEARS.getDefaultStack().getItem());
        DispenserBlock.registerBehavior(ModItems.SPEEDRUNNER_SHEARS, new ShearsDispenserBehavior());
    }
}
