package net.dilloney.speedrunnermod;

import com.google.common.collect.ImmutableMap;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dilloney.speedrunnermod.block.ModBlocks;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.option.ModOptions;
import net.dilloney.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe;
import net.dilloney.speedrunnermod.tag.ModBlockTags;
import net.dilloney.speedrunnermod.tag.ModItemTags;
import net.dilloney.speedrunnermod.world.gen.feature.ModOrePlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.mixin.structure.StructuresConfigAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.structure.NetherFortressGenerator;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.addBatsAndMonsters;

public class SpeedrunnerMod implements ModInitializer {
    public static final Text SPEEDRUNNER_MOD_TITLE = new TranslatableText("speedrunnermod.title");
    public static final Identifier SPEEDRUNNER_BOOTS = new Identifier(SpeedrunnerMod.MOD_ID, "textures/item/speedrunner_boots.png");
    public static final Identifier SPEEDRUNNER_INGOT = new Identifier(SpeedrunnerMod.MOD_ID, "textures/item/speedrunner_ingot.png");
    public static final String MOD_ID = "speedrunnermod";
    public static final String MOD_VERSION = "v1.5";
    public static final String MINECRAFT_VERSION = "1.18";
    public static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static final String CONFIG = "speedrunnermod-options.json";
    private static File file;
    private static ModOptions OPTIONS = getConfig();
    public static final RegistryKey<Biome>[][] COMMON_BIOMES = SpeedrunnerMod.options().main.commonBiomes ? new RegistryKey[][]{{BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA, BiomeKeys.PLAINS}, {BiomeKeys.PLAINS, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.DESERT, BiomeKeys.SAVANNA}, {BiomeKeys.FLOWER_FOREST, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.DARK_FOREST}, {BiomeKeys.SAVANNA, BiomeKeys.SAVANNA, BiomeKeys.FOREST, BiomeKeys.JUNGLE, BiomeKeys.SAVANNA_PLATEAU}, {BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT}} : new RegistryKey[][]{{BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.TAIGA}, {BiomeKeys.PLAINS, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA}, {BiomeKeys.FLOWER_FOREST, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.DARK_FOREST}, {BiomeKeys.SAVANNA, BiomeKeys.SAVANNA, BiomeKeys.FOREST, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE}, {BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT}};
    public static final Pool<SpawnSettings.SpawnEntry> NETHER_FORTRESS_MOB_SPAWNS = SpeedrunnerMod.options().main.doomMode ? Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 50, 1, 4), new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 75, 4, 12), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 20, 1, 4)) : Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4), new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 2), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 2), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 1));
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_BRIDGE = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 10, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 50, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 10, 1)};
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_CORRIDOR = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 25, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 25, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 2, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 20, 2)};
    public static final StrongholdGenerator.PieceData[] STRONGHOLD_GENERATION = SpeedrunnerMod.options().main.doomMode ? new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 75, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, 4) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength) && chainLength > 3;
        }
    }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 20, 1) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength) && chainLength > 5;
        }
    }} : new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 20, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 10, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 10, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 2) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength) && chainLength > 4;
        }
    }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 200, 3) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength) && chainLength > 2;
        }
    }, new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 200, 2) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength);
        }
    }};

    public void onInitialize() {
        loadConfig();

        ModItems.init();
        ModBlocks.init();
        ModItemTags.init();
        ModBlockTags.init();
        SpeedrunnerShieldDecorationRecipe.register();

        if (SpeedrunnerMod.options().main.commonStructures) {
            ServerWorldEvents.LOAD.register((server, world) -> {
                Map<StructureFeature<?>, StructureConfig> map = new HashMap<>(world.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());

                map.computeIfPresent(StructureFeature.VILLAGE, (structureFeature, structureConfig) -> {
                    return new StructureConfig(16, 9, 10387312);
                });
                map.computeIfPresent(StructureFeature.DESERT_PYRAMID, (structureFeature, structureConfig) -> {
                    return new StructureConfig(10, 8, 14357617);
                });
                map.computeIfPresent(StructureFeature.END_CITY, (structureFeature, structureConfig) -> {
                    return new StructureConfig(7, 6, 10387313);
                });
                map.computeIfPresent(StructureFeature.RUINED_PORTAL, (structureFeature, structureConfig) -> {
                    return new StructureConfig(9, 8, 34222645);
                });
                map.computeIfPresent(StructureFeature.SHIPWRECK, (structureFeature, structureConfig) -> {
                    return new StructureConfig(10, 8, 165745295);
                });
                map.computeIfPresent(StructureFeature.BASTION_REMNANT, (structureFeature, structureConfig) -> {
                    return new StructureConfig(9, 8, 30084232);
                });
                map.computeIfPresent(StructureFeature.FORTRESS, (structureFeature, structureConfig) -> {
                    return new StructureConfig(8, 7, 30084232);
                });

                ImmutableMap<StructureFeature<?>, StructureConfig> immutableMap = ImmutableMap.copyOf(map);

                ((StructuresConfigAccessor)world.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(immutableMap);
            });
        }

        LOGGER.info("The Speedrunner Mod (" + MOD_VERSION + ")" + " has been loaded.");
    }

    public static void applyBlockHardnessValues(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_HARDNESS)) {
            cir.setReturnValue(0.0F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_ONE_HARDNESS)) {
            cir.setReturnValue(0.1F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_TWO_HARDNESS)) {
            cir.setReturnValue(0.2F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_THREESEVEN_HARDNESS)) {
            cir.setReturnValue(0.37F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_FOUR_HARDNESS)) {
            cir.setReturnValue(0.4F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_FIVE_HARDNESS)) {
            cir.setReturnValue(0.5F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SIX_HARDNESS)) {
            cir.setReturnValue(0.6F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SIXFIVE_HARDNESS)) {
            cir.setReturnValue(0.65F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SEVEN_HARDNESS)) {
            cir.setReturnValue(0.7F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_EIGHT_HARDNESS)) {
            cir.setReturnValue(0.8F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ONE_ZERO_HARDNESS)) {
            cir.setReturnValue(1.0F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ONE_THREE_HARDNESS)) {
            cir.setReturnValue(1.3F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ONE_FOUR_HARDNESS)) {
            cir.setReturnValue(1.4F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ONE_FIVE_HARDNESS)) {
            cir.setReturnValue(1.5F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.ONE_SIX_HARDNESS)) {
            cir.setReturnValue(1.6F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.TWO_ZERO_HARDNESS)) {
            cir.setReturnValue(2.0F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.TWO_FIVE_HARDNESS)) {
            cir.setReturnValue(2.5F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.THREE_ZERO_HARDNESS)) {
            cir.setReturnValue(3.0F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.TEN_HARDNESS)) {
            cir.setReturnValue(10.0F);
        }

        if (world.getBlockState(pos).isIn(ModBlockTags.TWENTY_FIVE_HARDNESS)) {
            cir.setReturnValue(25.0F);
        }
    }

    public static Biome modifyNetherWastes() {
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.options().main.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 20, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 50, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 100, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 20, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 100, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 20, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 2, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 50, 2, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3344392).skyColor(getSkyColor(2.0F)).loopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_NETHER_WASTES)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome modifySoulSandValley() {
        double d = 0.7D;
        double e = 0.15D;
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.options().main.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 4, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.SKELETON, 0.7D, 0.15D).spawnCost(EntityType.GHAST, 0.7D, 0.15D).spawnCost(EntityType.ENDERMAN, 0.7D, 0.15D).spawnCost(EntityType.STRIDER, 0.7D, 0.15D).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 10, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 4, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.SKELETON, 0.7D, 0.15D).spawnCost(EntityType.GHAST, 0.7D, 0.15D).spawnCost(EntityType.ENDERMAN, 0.7D, 0.15D).spawnCost(EntityType.STRIDER, 0.7D, 0.15D).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA).feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, NetherPlacedFeatures.BASALT_PILLAR).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_CRIMSON_ROOTS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_SOUL_SAND);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1787717).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.ASH, 0.00625F)).loopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_SOUL_SAND_VALLEY)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome modifyBasaltDeltas() {
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.options().main.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 40, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 25, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 25, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.SURFACE_STRUCTURES, NetherPlacedFeatures.DELTA).feature(GenerationStep.Feature.SURFACE_STRUCTURES, NetherPlacedFeatures.SMALL_BASALT_COLUMNS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, NetherPlacedFeatures.LARGE_BASALT_COLUMNS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.BASALT_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.BLACKSTONE_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_DELTA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED_DOUBLE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_GOLD_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_QUARTZ_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModOrePlacedFeatures.ORE_SPEEDRUNNER_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModOrePlacedFeatures.ORE_IGNEOUS_DELTAS);
        DefaultBiomeFeatures.addAncientDebris(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(6840176).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.118093334F)).loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_BASALT_DELTAS)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome addDeltasOres() {
        SpawnSettings spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 40, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 100, 2, 5)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.SURFACE_STRUCTURES, NetherPlacedFeatures.DELTA).feature(GenerationStep.Feature.SURFACE_STRUCTURES, NetherPlacedFeatures.SMALL_BASALT_COLUMNS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, NetherPlacedFeatures.LARGE_BASALT_COLUMNS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.BASALT_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.BLACKSTONE_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_DELTA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED_DOUBLE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_GOLD_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_QUARTZ_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModOrePlacedFeatures.ORE_SPEEDRUNNER_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModOrePlacedFeatures.ORE_IGNEOUS_DELTAS);
        DefaultBiomeFeatures.addAncientDebris(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(6840176).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.118093334F)).loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_BASALT_DELTAS)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome modifyCrimsonForest() {
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.options().main.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 50, 4, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 2, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 6, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 9, 2, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.WEEPING_VINES).feature(GenerationStep.Feature.VEGETAL_DECORATION, TreePlacedFeatures.CRIMSON_FUNGI).feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.CRIMSON_FOREST_VEGETATION);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3343107).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.025F)).loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome modifyWarpedForest() {
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.options().main.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 4, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.ENDERMAN, 1.0D, 0.12D).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 5, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.ENDERMAN, 1.0D, 0.12D).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.VEGETAL_DECORATION, TreePlacedFeatures.WARPED_FUNGI).feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.WARPED_FOREST_VEGETATION).feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.NETHER_SPROUTS).feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.TWISTING_VINES);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1705242).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WARPED_SPORE, 0.01428F)).loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    private static int getSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    public static void modifyMonsters(net.minecraft.world.biome.SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, boolean drowned) {
        if (SpeedrunnerMod.options().main.doomMode) {
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(drowned ? EntityType.DROWNED : EntityType.ZOMBIE, zombieWeight, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, skeletonWeight, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 100, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 75, 1, 5));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 50, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 50, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 25, 1, 4));
        } else {
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(drowned ? EntityType.DROWNED : EntityType.ZOMBIE, zombieWeight, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, skeletonWeight, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 2, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 50, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
        }
    }

    public static void makeAnimalsMoreCommon(net.minecraft.world.biome.SpawnSettings.Builder builder) {
        switch (SpeedrunnerMod.options().advanced.mobSpawningRate) {
            case LOW:
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 16, 1, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 1, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 1, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 8, 1, 4));
            case NORMAL:
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 16, 4, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 4, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 4, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 8, 4, 4));
            case HIGH:
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 16, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 8, 4, 8));
            default:
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 16, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 8, 4, 8));
        }
    }

    public static void makeDolphinsMoreCommon(net.minecraft.world.biome.SpawnSettings.Builder builder, int squidWeight, int squidMinGroupSize) {
        switch (SpeedrunnerMod.options().advanced.mobSpawningRate) {
            case LOW:
                builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 10, 1, 4));
                builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 15, 1, 1));
            case NORMAL:
                builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 10, 4, 4));
                builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 15, 1, 1));
            case HIGH:
                builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 10, 4, 8));
                builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 15, 1, 2));
            default:
                builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 10, 4, 8));
                builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 15, 1, 2));
        }
        builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, squidWeight, squidMinGroupSize, 4));
        addBatsAndMonsters(builder);
    }

    public static void modifyEndMobs(net.minecraft.world.biome.SpawnSettings.Builder builder) {
        if (SpeedrunnerMod.options().main.doomMode) {
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 85, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 75, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 75, 1, 2));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.RAVAGER, 50, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.EVOKER, 50, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 25, 1, 1));
        } else {
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 4, 4));
        }
    }

    public static boolean canPiglinBruteSpawn(EntityType<PiglinBruteEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return !world.getBlockState(pos.down()).isOf(Blocks.NETHER_WART_BLOCK);
    }

    private static void loadConfig() {
        File file = getConfigFile();
        if (!file.exists()) {
            SpeedrunnerMod.OPTIONS = new ModOptions();
        } else {
            sanitize();
            readConfig();
        }
        saveConfig();
    }

    private static void readConfig() {
        SpeedrunnerMod.OPTIONS = getConfig();
    }

    public static void saveConfig() {
        File file = getConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(SpeedrunnerMod.options()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setConfig(ModOptions config) {
        SpeedrunnerMod.OPTIONS = config;
        saveConfig();
    }

    private static ModOptions getConfig() {
        File file = getConfigFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, ModOptions.class);
        } catch (Exception e) {
            ModOptions newconfig = new ModOptions();
            setConfig(newconfig);
            return newconfig;
        }
    }

    private static File getConfigFile() {
        if (file == null) {
            file = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG);
        }
        return file;
    }

    private static void sanitize() {
        if (SpeedrunnerMod.options().advanced.mobSpawningRate == null) {
            SpeedrunnerMod.options().advanced.mobSpawningRate = ModOptions.Advanced.MobSpawningRate.HIGH;
        }
    }

    public static ModOptions options() {
        return OPTIONS;
    }
}