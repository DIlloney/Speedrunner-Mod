package net.dillon.speedrunnermod.block;

import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * The {@code Speedrunner Mod} blocks.
 */
public class ModBlocks {
    public static final Identifier SPEEDRUNNER_SIGN_TEXTURE = new Identifier(SpeedrunnerMod.MOD_ID, "entity/signs/speedrunner");
    public static final Identifier SPEEDRUNNER_HANGING_SIGN_TEXTURE = new Identifier(SpeedrunnerMod.MOD_ID, "entity/signs/hanging/speedrunner");
    public static final Identifier SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE = new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/hanging_signs/speedrunner");
    public static final Identifier DEAD_SPEEDRUNNER_SIGN_TEXTURE = new Identifier(SpeedrunnerMod.MOD_ID, "entity/signs/dead_speedrunner");
    public static final Identifier DEAD_SPEEDRUNNER_HANGING_SIGN_TEXTURE = new Identifier(SpeedrunnerMod.MOD_ID, "entity/signs/hanging/dead_speedrunner");
    public static final Identifier DEAD_SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE = new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/hanging_signs/dead_speedrunner");

    public static final Block SPEEDRUNNER_LOG = new PillarBlock(FabricBlockSettings.create()
            .strength(1.0F)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block STRIPPED_SPEEDRUNNER_LOG = new PillarBlock(FabricBlockSettings.create()
            .strength(1.0F)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_WOOD = new PillarBlock(FabricBlockSettings.create()
            .strength(1.0F)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block STRIPPED_SPEEDRUNNER_WOOD = new PillarBlock(FabricBlockSettings.create()
            .strength(1.0F)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_LEAVES = new LeavesBlock(FabricBlockSettings.create()
            .strength(0.1F)
            .nonOpaque()
            .ticksRandomly()
            .allowsSpawning(Blocks::canSpawnOnLeaves)
            .suffocates(Blocks::never)
            .blockVision(Blocks::never)
            .pistonBehavior(PistonBehavior.DESTROY)
            .solidBlock(Blocks::never)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block SPEEDRUNNER_SAPLING = new SpeedrunnerSaplingBlock(ModSaplingGenerators.SPEEDRUNNER, FabricBlockSettings.create()
            .breakInstantly()
            .ticksRandomly()
            .noCollision()
            .sounds(BlockSoundGroup.GRASS));

    public static final Block POTTED_SPEEDRUNNER_SAPLING = new FlowerPotBlock(SPEEDRUNNER_SAPLING, FabricBlockSettings.create()
            .breakInstantly()
            .nonOpaque()
            .pistonBehavior(PistonBehavior.DESTROY));

    public static final Block SPEEDRUNNER_PLANKS = new Block(FabricBlockSettings.create()
            .strength(1.0F)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_SLAB = new SlabBlock(FabricBlockSettings.create()
            .strength(1.0F, 6.0F)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_STAIRS = new StairsBlock(SPEEDRUNNER_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_PLANKS));

    public static final Block SPEEDRUNNER_FENCE = new FenceBlock(FabricBlockSettings.create()
            .strength(1.0F)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_FENCE_GATE = new FenceGateBlock(WoodType.OAK, FabricBlockSettings.create()
            .strength(1.0F)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_TRAPDOOR = new TrapdoorBlock(BlockSetType.OAK, FabricBlockSettings.create()
            .strength(1.3F)
            .nonOpaque()
            .allowsSpawning(Blocks::never)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_BUTTON = new ButtonBlock(BlockSetType.OAK, 30, FabricBlockSettings.create()
            .strength(0.35F)
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_PRESSURE_PLATE = new PressurePlateBlock(BlockSetType.OAK, FabricBlockSettings.create()
            .strength(0.35F)
            .noCollision()
            .instrument(Instrument.BASS)
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_DOOR = new DoorBlock(BlockSetType.OAK, FabricBlockSettings.create()
            .strength(1.3F)
            .nonOpaque()
            .instrument(Instrument.BASS)
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_SIGN = new TerraformSignBlock(SPEEDRUNNER_SIGN_TEXTURE, FabricBlockSettings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_WALL_SIGN = new TerraformWallSignBlock(SPEEDRUNNER_SIGN_TEXTURE, FabricBlockSettings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_HANGING_SIGN = new TerraformHangingSignBlock(SPEEDRUNNER_HANGING_SIGN_TEXTURE, SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.HANGING_SIGN));

    public static final Block SPEEDRUNNER_HANGING_WALL_SIGN = new TerraformWallHangingSignBlock(SPEEDRUNNER_HANGING_SIGN_TEXTURE, SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.HANGING_SIGN));

    public static final Block DEAD_SPEEDRUNNER_LOG = new PillarBlock(FabricBlockSettings.copy(ModBlocks.SPEEDRUNNER_LOG).burnable());
    public static final Block DEAD_STRIPPED_SPEEDRUNNER_LOG = new PillarBlock(FabricBlockSettings.copyOf(ModBlocks.STRIPPED_SPEEDRUNNER_LOG).burnable());
    public static final Block DEAD_SPEEDRUNNER_WOOD = new PillarBlock(FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_WOOD).burnable());
    public static final Block DEAD_STRIPPED_SPEEDRUNNER_WOOD = new PillarBlock(FabricBlockSettings.copyOf(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD).burnable());
    public static final Block DEAD_SPEEDRUNNER_LEAVES = new LeavesBlock(FabricBlockSettings.copy(ModBlocks.SPEEDRUNNER_LEAVES).burnable());
    public static final Block DEAD_SPEEDRUNNER_SAPLING = new SpeedrunnerSaplingBlock(ModSaplingGenerators.DEAD_SPEEDRUNNER, FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_SAPLING).burnable());
    public static final Block DEAD_POTTED_SPEEDRUNNER_SAPLING = new FlowerPotBlock(ModBlocks.DEAD_SPEEDRUNNER_SAPLING, FabricBlockSettings.copyOf(ModBlocks.DEAD_SPEEDRUNNER_SAPLING).burnable());
    public static final Block DEAD_SPEEDRUNNER_PLANKS = new Block(FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_PLANKS).burnable());
    public static final Block DEAD_SPEEDRUNNER_SLAB = new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_SLAB).burnable());
    public static final Block DEAD_SPEEDRUNNER_STAIRS = new StairsBlock(ModBlocks.DEAD_SPEEDRUNNER_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_STAIRS).burnable());
    public static final Block DEAD_SPEEDRUNNER_FENCE = new FenceBlock(FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_FENCE).burnable());
    public static final Block DEAD_SPEEDRUNNER_FENCE_GATE = new FenceGateBlock(WoodType.OAK, FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_FENCE_GATE).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR = new TrapdoorBlock(BlockSetType.OAK, FabricBlockSettings.copyOf(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_BUTTON = new ButtonBlock(BlockSetType.OAK, 30, FabricBlockSettings.copyOf(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE = new PressurePlateBlock(BlockSetType.OAK, FabricBlockSettings.copyOf(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_DOOR = new DoorBlock(BlockSetType.OAK, FabricBlockSettings.copyOf(ModBlocks.WOODEN_SPEEDRUNNER_DOOR).burnable());
    public static final Block DEAD_SPEEDRUNNER_SIGN = new TerraformSignBlock(ModBlocks.DEAD_SPEEDRUNNER_SIGN_TEXTURE, FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_SIGN).burnable());
    public static final Block DEAD_SPEEDRUNNER_WALL_SIGN = new TerraformWallSignBlock(ModBlocks.DEAD_SPEEDRUNNER_SIGN_TEXTURE, FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_WALL_SIGN).burnable());
    public static final Block DEAD_SPEEDRUNNER_HANGING_SIGN = new TerraformHangingSignBlock(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN_TEXTURE, ModBlocks.DEAD_SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_HANGING_SIGN).burnable());
    public static final Block DEAD_SPEEDRUNNER_HANGING_WALL_SIGN = new TerraformWallHangingSignBlock(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN_TEXTURE, ModBlocks.DEAD_SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN).burnable());

    public static final Block SPEEDRUNNER_TRAPDOOR = new TrapdoorBlock(BlockSetType.IRON, FabricBlockSettings.create()
            .requiresTool()
            .strength(2.5F)
            .nonOpaque()
            .allowsSpawning(Blocks::never)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.METAL));

    public static final Block SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = new WeightedPressurePlateBlock(100, BlockSetType.IRON, FabricBlockSettings.create()
            .requiresTool()
            .strength(0.5F)
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_DOOR = new DoorBlock(BlockSetType.IRON, FabricBlockSettings.create()
            .requiresTool()
            .strength(2.5F)
            .nonOpaque()
            .instrument(Instrument.BASS)
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.METAL));

    public static final Block DEAD_SPEEDRUNNER_BUSH = new DeadBushBlock(FabricBlockSettings.create()
            .breakInstantly()
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block POTTED_DEAD_SPEEDRUNNER_BUSH = new FlowerPotBlock(ModBlocks.DEAD_SPEEDRUNNER_BUSH, FabricBlockSettings.create()
            .breakInstantly()
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block SPEEDRUNNERS_WORKBENCH = new SpeedrunnersWorkbenchBlock(FabricBlockSettings.create()
            .strength(1.6F)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_BLOCK = new Block(FabricBlockSettings.create()
            .requiresTool()
            .strength(5.0F, 6.0F)
            .instrument(Instrument.IRON_XYLOPHONE)
            .sounds(BlockSoundGroup.METAL));

    public static final Block RAW_SPEEDRUNNER_BLOCK = new Block(FabricBlockSettings.create()
            .requiresTool()
            .strength(5.0F, 6.0F)
            .instrument(Instrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block THRUSTER_BLOCK = new Block(FabricBlockSettings.create()
            .strength(0.5F)
            .allowsSpawning(Blocks::never)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block SPEEDRUNNER_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), FabricBlockSettings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(Instrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DEEPSLATE_SPEEDRUNNER_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), FabricBlockSettings.create()
            .requiresTool()
            .strength(4.5F, 4.5F)
            .instrument(Instrument.BASEDRUM)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static final Block NETHER_SPEEDRUNNER_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), FabricBlockSettings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(Instrument.BASEDRUM)
            .sounds(BlockSoundGroup.NETHER_GOLD_ORE));

    public static final Block IGNEOUS_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), FabricBlockSettings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(Instrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DEEPSLATE_IGNEOUS_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), FabricBlockSettings.create()
            .requiresTool()
            .strength(4.5F, 4.5F)
            .instrument(Instrument.BASEDRUM)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static final Block NETHER_IGNEOUS_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), FabricBlockSettings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(Instrument.BASEDRUM)
            .sounds(BlockSoundGroup.NETHER_ORE));

    public static final Block EXPERIENCE_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), FabricBlockSettings.create()
            .requiresTool()
            .strength(5.0F, 10.0F)
            .instrument(Instrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DEEPSLATE_EXPERIENCE_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), FabricBlockSettings.create()
            .requiresTool()
            .strength(6.0F, 12.5F)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static final Block NETHER_EXPERIENCE_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), FabricBlockSettings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(Instrument.BASEDRUM)
            .sounds(BlockSoundGroup.NETHER_ORE));

    public static final Block DOOM_STONE = new DoomBlock.Default(FabricBlockSettings.create()
            .requiresTool()
            .strength(1.5F, 3600000.0F)
            .instrument(Instrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DOOM_LOG = new DoomBlock.Pillar(FabricBlockSettings.create()
            .strength(1.0F, 3600000.0F)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block STRIPPED_DOOM_LOG = new DoomBlock.Pillar(FabricBlockSettings.create()
            .strength(1.0F, 3600000.0F)
            .instrument(Instrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block DOOM_LEAVES = new DoomBlock.Leaves(FabricBlockSettings.create()
            .strength(0.1F, 3600000.0F)
            .nonOpaque()
            .ticksRandomly()
            .suffocates(Blocks::never)
            .blockVision(Blocks::never)
            .pistonBehavior(PistonBehavior.DESTROY)
            .solidBlock(Blocks::never)
            .sounds(BlockSoundGroup.GRASS));

    /**
     * Makes certain blocks strippable.
     */
    private static void registerStrippables() {
        StrippableBlockRegistry.register(SPEEDRUNNER_LOG, STRIPPED_SPEEDRUNNER_LOG);
        StrippableBlockRegistry.register(SPEEDRUNNER_WOOD, STRIPPED_SPEEDRUNNER_WOOD);
        StrippableBlockRegistry.register(DOOM_LOG, STRIPPED_DOOM_LOG);
    }

    /**
     * Initialize the {@code Speedrunner Mod} blocks.
     */
    public static void init() {
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_log"), SPEEDRUNNER_LOG);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "stripped_speedrunner_log"), STRIPPED_SPEEDRUNNER_LOG);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_wood"), SPEEDRUNNER_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "stripped_speedrunner_wood"), STRIPPED_SPEEDRUNNER_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_leaves"), SPEEDRUNNER_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_sapling"), SPEEDRUNNER_SAPLING);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "potted_speedrunner_sapling"), POTTED_SPEEDRUNNER_SAPLING);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_planks"), SPEEDRUNNER_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_slab"), SPEEDRUNNER_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_stairs"), SPEEDRUNNER_STAIRS);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_fence"), SPEEDRUNNER_FENCE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_fence_gate"), SPEEDRUNNER_FENCE_GATE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "wooden_speedrunner_trapdoor"), WOODEN_SPEEDRUNNER_TRAPDOOR);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "wooden_speedrunner_button"), WOODEN_SPEEDRUNNER_BUTTON);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "wooden_speedrunner_pressure_plate"), WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "wooden_speedrunner_door"), WOODEN_SPEEDRUNNER_DOOR);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_sign"), SPEEDRUNNER_SIGN);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_wall_sign"), SPEEDRUNNER_WALL_SIGN);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_hanging_sign"), SPEEDRUNNER_HANGING_SIGN);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_hanging_wall_sign"), SPEEDRUNNER_HANGING_WALL_SIGN);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_log"), DEAD_SPEEDRUNNER_LOG);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_stripped_speedrunner_log"), DEAD_STRIPPED_SPEEDRUNNER_LOG);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_wood"), DEAD_SPEEDRUNNER_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_stripped_speedrunner_wood"), DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_leaves"), DEAD_SPEEDRUNNER_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_sapling"), DEAD_SPEEDRUNNER_SAPLING);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_potted_speedrunner_sapling"), DEAD_POTTED_SPEEDRUNNER_SAPLING);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_planks"), DEAD_SPEEDRUNNER_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_slab"), DEAD_SPEEDRUNNER_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_stairs"), DEAD_SPEEDRUNNER_STAIRS);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_fence"), DEAD_SPEEDRUNNER_FENCE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_fence_gate"), DEAD_SPEEDRUNNER_FENCE_GATE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_wooden_speedrunner_trapdoor"), DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_wooden_speedrunner_button"), DEAD_WOODEN_SPEEDRUNNER_BUTTON);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_wooden_speedrunner_pressure_plate"), DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_wooden_speedrunner_door"), DEAD_WOODEN_SPEEDRUNNER_DOOR);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_sign"), DEAD_SPEEDRUNNER_SIGN);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_wall_sign"), DEAD_SPEEDRUNNER_WALL_SIGN);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_hanging_sign"), DEAD_SPEEDRUNNER_HANGING_SIGN);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_hanging_wall_sign"), DEAD_SPEEDRUNNER_HANGING_WALL_SIGN);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_trapdoor"), SPEEDRUNNER_TRAPDOOR);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_weighted_pressure_plate"), SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_door"), SPEEDRUNNER_DOOR);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_bush"), DEAD_SPEEDRUNNER_BUSH);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "potted_dead_speedrunner_bush"), POTTED_DEAD_SPEEDRUNNER_BUSH);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunners_workbench"), SPEEDRUNNERS_WORKBENCH);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_block"), SPEEDRUNNER_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "raw_speedrunner_block"), RAW_SPEEDRUNNER_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "thruster_block"), THRUSTER_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_ore"), SPEEDRUNNER_ORE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "deepslate_speedrunner_ore"), DEEPSLATE_SPEEDRUNNER_ORE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "nether_speedrunner_ore"), NETHER_SPEEDRUNNER_ORE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "igneous_ore"), IGNEOUS_ORE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "deepslate_igneous_ore"), DEEPSLATE_IGNEOUS_ORE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "nether_igneous_ore"), NETHER_IGNEOUS_ORE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "experience_ore"), EXPERIENCE_ORE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "deepslate_experience_ore"), DEEPSLATE_EXPERIENCE_ORE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "nether_experience_ore"), NETHER_EXPERIENCE_ORE);

        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "doom_stone"), DOOM_STONE);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "doom_log"), DOOM_LOG);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "stripped_doom_log"), STRIPPED_DOOM_LOG);
        Registry.register(Registries.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "doom_leaves"), DOOM_LEAVES);

        registerStrippables();

        info("Initialized blocks.");
    }
}