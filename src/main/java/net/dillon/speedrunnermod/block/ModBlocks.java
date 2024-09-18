package net.dillon.speedrunnermod.block;

import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * All Speedrunner Mod {@code blocks.}
 */
public class ModBlocks {
    public static final Identifier SPEEDRUNNER_SIGN_TEXTURE = Identifier.of(SpeedrunnerMod.MOD_ID, "entity/signs/speedrunner");
    public static final Identifier SPEEDRUNNER_HANGING_SIGN_TEXTURE = Identifier.of(SpeedrunnerMod.MOD_ID, "entity/signs/hanging/speedrunner");
    public static final Identifier SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE = Identifier.of(SpeedrunnerMod.MOD_ID, "textures/gui/hanging_signs/speedrunner");
    public static final Identifier DEAD_SPEEDRUNNER_SIGN_TEXTURE = Identifier.of(SpeedrunnerMod.MOD_ID, "entity/signs/dead_speedrunner");
    public static final Identifier DEAD_SPEEDRUNNER_HANGING_SIGN_TEXTURE = Identifier.of(SpeedrunnerMod.MOD_ID, "entity/signs/hanging/dead_speedrunner");
    public static final Identifier DEAD_SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE = Identifier.of(SpeedrunnerMod.MOD_ID, "textures/gui/hanging_signs/dead_speedrunner");

    public static final Block SPEEDRUNNER_LOG = new PillarBlock(AbstractBlock.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block STRIPPED_SPEEDRUNNER_LOG = new PillarBlock(AbstractBlock.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_WOOD = new PillarBlock(AbstractBlock.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block STRIPPED_SPEEDRUNNER_WOOD = new PillarBlock(AbstractBlock.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_LEAVES = new LeavesBlock(AbstractBlock.Settings.create()
            .strength(0.1F)
            .nonOpaque()
            .ticksRandomly()
            .allowsSpawning(Blocks::canSpawnOnLeaves)
            .suffocates(Blocks::never)
            .blockVision(Blocks::never)
            .pistonBehavior(PistonBehavior.DESTROY)
            .solidBlock(Blocks::never)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block SPEEDRUNNER_SAPLING = new SpeedrunnerSaplingBlock(ModSaplingGenerators.SPEEDRUNNER, AbstractBlock.Settings.create()
            .breakInstantly()
            .ticksRandomly()
            .noCollision()
            .sounds(BlockSoundGroup.GRASS));

    public static final Block POTTED_SPEEDRUNNER_SAPLING = new FlowerPotBlock(ModBlocks.SPEEDRUNNER_SAPLING, AbstractBlock.Settings.create()
            .breakInstantly()
            .nonOpaque()
            .pistonBehavior(PistonBehavior.DESTROY));

    public static final Block SPEEDRUNNER_PLANKS = new Block(AbstractBlock.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_SLAB = new SlabBlock(AbstractBlock.Settings.create()
            .strength(1.0F, 6.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_STAIRS = new StairsBlock(ModBlocks.SPEEDRUNNER_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_PLANKS));

    public static final Block SPEEDRUNNER_FENCE = new FenceBlock(AbstractBlock.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_FENCE_GATE = new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_TRAPDOOR = new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create()
            .strength(1.3F)
            .nonOpaque()
            .allowsSpawning(Blocks::never)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_BUTTON = new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.create()
            .strength(0.35F)
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_PRESSURE_PLATE = new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.create()
            .strength(0.35F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_DOOR = new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create()
            .strength(1.3F)
            .nonOpaque()
            .instrument(NoteBlockInstrument.BASS)
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_SIGN = new TerraformSignBlock(ModBlocks.SPEEDRUNNER_SIGN_TEXTURE, AbstractBlock.Settings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_WALL_SIGN = new TerraformWallSignBlock(ModBlocks.SPEEDRUNNER_SIGN_TEXTURE, AbstractBlock.Settings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_HANGING_SIGN = new TerraformHangingSignBlock(ModBlocks.SPEEDRUNNER_HANGING_SIGN_TEXTURE, ModBlocks.SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.HANGING_SIGN));

    public static final Block SPEEDRUNNER_HANGING_WALL_SIGN = new TerraformWallHangingSignBlock(ModBlocks.SPEEDRUNNER_HANGING_SIGN_TEXTURE, ModBlocks.SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.HANGING_SIGN));

    public static final Block DEAD_SPEEDRUNNER_LOG = new PillarBlock(AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_LOG).burnable());
    public static final Block DEAD_STRIPPED_SPEEDRUNNER_LOG = new PillarBlock(AbstractBlock.Settings.copy(ModBlocks.STRIPPED_SPEEDRUNNER_LOG).burnable());
    public static final Block DEAD_SPEEDRUNNER_WOOD = new PillarBlock(AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_WOOD).burnable());
    public static final Block DEAD_STRIPPED_SPEEDRUNNER_WOOD = new PillarBlock(AbstractBlock.Settings.copy(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD).burnable());
    public static final Block DEAD_SPEEDRUNNER_LEAVES = new LeavesBlock(AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_LEAVES).burnable());
    public static final Block DEAD_SPEEDRUNNER_SAPLING = new SpeedrunnerSaplingBlock(ModSaplingGenerators.DEAD_SPEEDRUNNER, AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_SAPLING).burnable());
    public static final Block DEAD_POTTED_SPEEDRUNNER_SAPLING = new FlowerPotBlock(ModBlocks.DEAD_SPEEDRUNNER_SAPLING, AbstractBlock.Settings.copy(ModBlocks.DEAD_SPEEDRUNNER_SAPLING).burnable());
    public static final Block DEAD_SPEEDRUNNER_PLANKS = new Block(AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_PLANKS).burnable());
    public static final Block DEAD_SPEEDRUNNER_SLAB = new SlabBlock(AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_SLAB).burnable());
    public static final Block DEAD_SPEEDRUNNER_STAIRS = new StairsBlock(ModBlocks.DEAD_SPEEDRUNNER_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_STAIRS).burnable());
    public static final Block DEAD_SPEEDRUNNER_FENCE = new FenceBlock(AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_FENCE).burnable());
    public static final Block DEAD_SPEEDRUNNER_FENCE_GATE = new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_FENCE_GATE).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR = new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_BUTTON = new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE = new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_DOOR = new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(ModBlocks.WOODEN_SPEEDRUNNER_DOOR).burnable());
    public static final Block DEAD_SPEEDRUNNER_SIGN = new TerraformSignBlock(ModBlocks.DEAD_SPEEDRUNNER_SIGN_TEXTURE, AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_SIGN).burnable());
    public static final Block DEAD_SPEEDRUNNER_WALL_SIGN = new TerraformWallSignBlock(ModBlocks.DEAD_SPEEDRUNNER_SIGN_TEXTURE, AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_WALL_SIGN).burnable());
    public static final Block DEAD_SPEEDRUNNER_HANGING_SIGN = new TerraformHangingSignBlock(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN_TEXTURE, ModBlocks.DEAD_SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_HANGING_SIGN).burnable());
    public static final Block DEAD_SPEEDRUNNER_HANGING_WALL_SIGN = new TerraformWallHangingSignBlock(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN_TEXTURE, ModBlocks.DEAD_SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.copy(ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN).burnable());

    public static final Block SPEEDRUNNER_TRAPDOOR = new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
            .requiresTool()
            .strength(2.5F)
            .nonOpaque()
            .allowsSpawning(Blocks::never)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.METAL));

    public static final Block SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = new WeightedPressurePlateBlock(100, BlockSetType.IRON, AbstractBlock.Settings.create()
            .requiresTool()
            .strength(0.5F)
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_DOOR = new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
            .requiresTool()
            .strength(2.5F)
            .nonOpaque()
            .instrument(NoteBlockInstrument.BASS)
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.METAL));

    public static final Block DEAD_SPEEDRUNNER_BUSH = new DeadBushBlock(AbstractBlock.Settings.create()
            .breakInstantly()
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block POTTED_DEAD_SPEEDRUNNER_BUSH = new FlowerPotBlock(ModBlocks.DEAD_SPEEDRUNNER_BUSH, AbstractBlock.Settings.create()
            .breakInstantly()
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block SPEEDRUNNERS_WORKBENCH = new SpeedrunnersWorkbenchBlock(AbstractBlock.Settings.create()
            .strength(1.6F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_BLOCK = new Block(AbstractBlock.Settings.create()
            .requiresTool()
            .strength(5.0F, 6.0F)
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .sounds(BlockSoundGroup.METAL));

    public static final Block RAW_SPEEDRUNNER_BLOCK = new Block(AbstractBlock.Settings.create()
            .requiresTool()
            .strength(5.0F, 6.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block THRUSTER_BLOCK = new Block(AbstractBlock.Settings.create()
            .strength(0.5F)
            .allowsSpawning(Blocks::never)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block SPEEDRUNNER_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), AbstractBlock.Settings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DEEPSLATE_SPEEDRUNNER_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), AbstractBlock.Settings.create()
            .requiresTool()
            .strength(4.5F, 4.5F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static final Block NETHER_SPEEDRUNNER_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), AbstractBlock.Settings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.NETHER_GOLD_ORE));

    public static final Block IGNEOUS_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), AbstractBlock.Settings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DEEPSLATE_IGNEOUS_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), AbstractBlock.Settings.create()
            .requiresTool()
            .strength(4.5F, 4.5F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static final Block NETHER_IGNEOUS_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), AbstractBlock.Settings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.NETHER_ORE));

    public static final Block EXPERIENCE_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), AbstractBlock.Settings.create()
            .requiresTool()
            .strength(5.0F, 10.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DEEPSLATE_EXPERIENCE_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), AbstractBlock.Settings.create()
            .requiresTool()
            .strength(6.0F, 12.5F)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static final Block NETHER_EXPERIENCE_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), AbstractBlock.Settings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.NETHER_ORE));

    public static final Block DOOM_STONE = new DoomBlock.Default(AbstractBlock.Settings.create()
            .requiresTool()
            .strength(1.5F, 3600000.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DOOM_LOG = new DoomBlock.Pillar(AbstractBlock.Settings.create()
            .strength(1.0F, 3600000.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block STRIPPED_DOOM_LOG = new DoomBlock.Pillar(AbstractBlock.Settings.create()
            .strength(1.0F, 3600000.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block DOOM_LEAVES = new DoomBlock.Leaves(AbstractBlock.Settings.create()
            .strength(0.1F, 3600000.0F)
            .nonOpaque()
            .ticksRandomly()
            .suffocates(Blocks::never)
            .blockVision(Blocks::never)
            .pistonBehavior(PistonBehavior.DESTROY)
            .solidBlock(Blocks::never)
            .sounds(BlockSoundGroup.GRASS));

    /**
     * Makes certain blocks {@code strippable}.
     */
    private static void registerStrippables() {
        StrippableBlockRegistry.register(SPEEDRUNNER_LOG, STRIPPED_SPEEDRUNNER_LOG);
        StrippableBlockRegistry.register(SPEEDRUNNER_WOOD, STRIPPED_SPEEDRUNNER_WOOD);
        StrippableBlockRegistry.register(DOOM_LOG, STRIPPED_DOOM_LOG);
    }

    /**
     * Initializes all Speedrunner Mod {@code blocks.}
     */
    public static void registerBlocks() {
        register("speedrunner_log", SPEEDRUNNER_LOG);
        register("stripped_speedrunner_log", STRIPPED_SPEEDRUNNER_LOG);
        register("speedrunner_wood", SPEEDRUNNER_WOOD);
        register("stripped_speedrunner_wood", STRIPPED_SPEEDRUNNER_WOOD);
        register("speedrunner_leaves", SPEEDRUNNER_LEAVES);
        register("speedrunner_sapling", SPEEDRUNNER_SAPLING);
        register("potted_speedrunner_sapling", POTTED_SPEEDRUNNER_SAPLING);
        register("speedrunner_planks", SPEEDRUNNER_PLANKS);
        register("speedrunner_slab", SPEEDRUNNER_SLAB);
        register("speedrunner_stairs", SPEEDRUNNER_STAIRS);
        register("speedrunner_fence", SPEEDRUNNER_FENCE);
        register("speedrunner_fence_gate", SPEEDRUNNER_FENCE_GATE);
        register("wooden_speedrunner_trapdoor", WOODEN_SPEEDRUNNER_TRAPDOOR);
        register("wooden_speedrunner_button", WOODEN_SPEEDRUNNER_BUTTON);
        register("wooden_speedrunner_pressure_plate", WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        register("wooden_speedrunner_door", WOODEN_SPEEDRUNNER_DOOR);
        register("speedrunner_sign", SPEEDRUNNER_SIGN);
        register("speedrunner_wall_sign", SPEEDRUNNER_WALL_SIGN);
        register("speedrunner_hanging_sign", SPEEDRUNNER_HANGING_SIGN);
        register("speedrunner_hanging_wall_sign", SPEEDRUNNER_HANGING_WALL_SIGN);
        register("dead_speedrunner_log", DEAD_SPEEDRUNNER_LOG);
        register("dead_stripped_speedrunner_log", DEAD_STRIPPED_SPEEDRUNNER_LOG);
        register("dead_speedrunner_wood", DEAD_SPEEDRUNNER_WOOD);
        register("dead_stripped_speedrunner_wood", DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        register("dead_speedrunner_leaves", DEAD_SPEEDRUNNER_LEAVES);
        register("dead_speedrunner_sapling", DEAD_SPEEDRUNNER_SAPLING);
        register("dead_potted_speedrunner_sapling", DEAD_POTTED_SPEEDRUNNER_SAPLING);
        register("dead_speedrunner_planks", DEAD_SPEEDRUNNER_PLANKS);
        register("dead_speedrunner_slab", DEAD_SPEEDRUNNER_SLAB);
        register("dead_speedrunner_stairs", DEAD_SPEEDRUNNER_STAIRS);
        register("dead_speedrunner_fence", DEAD_SPEEDRUNNER_FENCE);
        register("dead_speedrunner_fence_gate", DEAD_SPEEDRUNNER_FENCE_GATE);
        register("dead_wooden_speedrunner_trapdoor", DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
        register("dead_wooden_speedrunner_button", DEAD_WOODEN_SPEEDRUNNER_BUTTON);
        register("dead_wooden_speedrunner_pressure_plate", DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        register("dead_wooden_speedrunner_door", DEAD_WOODEN_SPEEDRUNNER_DOOR);
        register("dead_speedrunner_sign", DEAD_SPEEDRUNNER_SIGN);
        register("dead_speedrunner_wall_sign", DEAD_SPEEDRUNNER_WALL_SIGN);
        register("dead_speedrunner_hanging_sign", DEAD_SPEEDRUNNER_HANGING_SIGN);
        register("dead_speedrunner_hanging_wall_sign", DEAD_SPEEDRUNNER_HANGING_WALL_SIGN);
        register("speedrunner_trapdoor", SPEEDRUNNER_TRAPDOOR);
        register("speedrunner_weighted_pressure_plate", SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        register("speedrunner_door", SPEEDRUNNER_DOOR);
        register("dead_speedrunner_bush", DEAD_SPEEDRUNNER_BUSH);
        register("potted_dead_speedrunner_bush", POTTED_DEAD_SPEEDRUNNER_BUSH);
        register("speedrunners_workbench", SPEEDRUNNERS_WORKBENCH);
        register("speedrunner_block", SPEEDRUNNER_BLOCK);
        register("raw_speedrunner_block", RAW_SPEEDRUNNER_BLOCK);
        register("thruster_block", THRUSTER_BLOCK);
        register("speedrunner_ore", SPEEDRUNNER_ORE);
        register("deepslate_speedrunner_ore", DEEPSLATE_SPEEDRUNNER_ORE);
        register("nether_speedrunner_ore", NETHER_SPEEDRUNNER_ORE);
        register("igneous_ore", IGNEOUS_ORE);
        register("deepslate_igneous_ore", DEEPSLATE_IGNEOUS_ORE);
        register("nether_igneous_ore", NETHER_IGNEOUS_ORE);
        register("experience_ore", EXPERIENCE_ORE);
        register("deepslate_experience_ore", DEEPSLATE_EXPERIENCE_ORE);
        register("nether_experience_ore", NETHER_EXPERIENCE_ORE);

        register("doom_stone", DOOM_STONE);
        register("doom_log", DOOM_LOG);
        register("stripped_doom_log", STRIPPED_DOOM_LOG);
        register("doom_leaves", DOOM_LEAVES);

        registerStrippables();

        info("Initialized blocks.");
    }

    /**
     * Registers a {@code block.}
     */
    private static void register(String path, Block block) {
        Registry.register(Registries.BLOCK, Identifier.of(SpeedrunnerMod.MOD_ID, path), block);
    }
}