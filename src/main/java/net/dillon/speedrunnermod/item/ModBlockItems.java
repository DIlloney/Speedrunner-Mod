package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.item.ModItems.registerItem;

/**
 * All Speedrunner Mod {@code block items.}
 */
public class ModBlockItems {
    public static final BlockItem SPEEDRUNNER_LOG = new BlockItem(ModBlocks.SPEEDRUNNER_LOG,
            new Item.Settings());

    public static final BlockItem STRIPPED_SPEEDRUNNER_LOG = new BlockItem(ModBlocks.STRIPPED_SPEEDRUNNER_LOG,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_WOOD = new BlockItem(ModBlocks.SPEEDRUNNER_WOOD,
            new Item.Settings());

    public static final BlockItem STRIPPED_SPEEDRUNNER_WOOD = new BlockItem(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_LEAVES = new BlockItem(ModBlocks.SPEEDRUNNER_LEAVES,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_SAPLING = new BlockItem(ModBlocks.SPEEDRUNNER_SAPLING,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_PLANKS = new BlockItem(ModBlocks.SPEEDRUNNER_PLANKS,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_SLAB = new BlockItem(ModBlocks.SPEEDRUNNER_SLAB,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_STAIRS = new BlockItem(ModBlocks.SPEEDRUNNER_STAIRS,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_FENCE = new BlockItem(ModBlocks.SPEEDRUNNER_FENCE,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_FENCE_GATE = new BlockItem(ModBlocks.SPEEDRUNNER_FENCE_GATE,
            new Item.Settings());

    public static final BlockItem WOODEN_SPEEDRUNNER_TRAPDOOR = new BlockItem(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR,
            new Item.Settings());

    public static final BlockItem WOODEN_SPEEDRUNNER_BUTTON = new BlockItem(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON,
            new Item.Settings());

    public static final BlockItem WOODEN_SPEEDRUNNER_PRESSURE_PLATE = new BlockItem(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE,
            new Item.Settings());

    public static final BlockItem WOODEN_SPEEDRUNNER_DOOR = new TallBlockItem(ModBlocks.WOODEN_SPEEDRUNNER_DOOR,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_SIGN = new SignItem(
            new Item.Settings().maxCount(16), ModBlocks.SPEEDRUNNER_SIGN, ModBlocks.SPEEDRUNNER_WALL_SIGN);

    public static final BlockItem SPEEDRUNNER_HANGING_SIGN = new HangingSignItem(
            ModBlocks.SPEEDRUNNER_HANGING_SIGN, ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN, new Item.Settings().maxCount(16));

    public static final BlockItem DEAD_SPEEDRUNNER_LOG = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_LOG,
            new Item.Settings());

    public static final BlockItem DEAD_STRIPPED_SPEEDRUNNER_LOG = new BlockItem(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG,
            new Item.Settings());

    public static final BlockItem DEAD_SPEEDRUNNER_WOOD = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_WOOD,
            new Item.Settings());

    public static final BlockItem DEAD_STRIPPED_SPEEDRUNNER_WOOD = new BlockItem(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD,
            new Item.Settings());

    public static final BlockItem DEAD_SPEEDRUNNER_LEAVES = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_LEAVES,
            new Item.Settings());

    public static final BlockItem DEAD_SPEEDRUNNER_SAPLING = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_SAPLING,
            new Item.Settings());

    public static final BlockItem DEAD_SPEEDRUNNER_PLANKS = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS,
            new Item.Settings());

    public static final BlockItem DEAD_SPEEDRUNNER_SLAB = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_SLAB,
            new Item.Settings());

    public static final BlockItem DEAD_SPEEDRUNNER_STAIRS = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_STAIRS,
            new Item.Settings());

    public static final BlockItem DEAD_SPEEDRUNNER_FENCE = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_FENCE,
            new Item.Settings());

    public static final BlockItem DEAD_SPEEDRUNNER_FENCE_GATE = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE,
            new Item.Settings());

    public static final BlockItem DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR = new BlockItem(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR,
            new Item.Settings());

    public static final BlockItem DEAD_WOODEN_SPEEDRUNNER_BUTTON = new BlockItem(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON,
            new Item.Settings());

    public static final BlockItem DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE = new BlockItem(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE,
            new Item.Settings());

    public static final BlockItem DEAD_WOODEN_SPEEDRUNNER_DOOR = new TallBlockItem(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR,
            new Item.Settings());

    public static final BlockItem DEAD_SPEEDRUNNER_SIGN = new SignItem(
            new Item.Settings().maxCount(16), ModBlocks.DEAD_SPEEDRUNNER_SIGN, ModBlocks.DEAD_SPEEDRUNNER_WALL_SIGN);

    public static final BlockItem DEAD_SPEEDRUNNER_HANGING_SIGN = new HangingSignItem(
            ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, ModBlocks.DEAD_SPEEDRUNNER_HANGING_WALL_SIGN, new Item.Settings().maxCount(16));

    public static final BlockItem SPEEDRUNNER_TRAPDOOR = new BlockItem(ModBlocks.SPEEDRUNNER_TRAPDOOR,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = new BlockItem(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_DOOR = new BlockItem(ModBlocks.SPEEDRUNNER_DOOR,
            new Item.Settings());

    public static final BlockItem DEAD_SPEEDRUNNER_BUSH = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_BUSH,
            new Item.Settings());

    public static final Item SPEEDRUNNERS_WORKBENCH = new BlockItem(ModBlocks.SPEEDRUNNERS_WORKBENCH,
            new Item.Settings()) {

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line1").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line3").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line4").formatted(Formatting.GRAY));
            }
        }
    };

    public static final BlockItem SPEEDRUNNER_BLOCK = new BlockItem(ModBlocks.SPEEDRUNNER_BLOCK,
            new Item.Settings());

    public static final BlockItem RAW_SPEEDRUNNER_BLOCK = new BlockItem(ModBlocks.RAW_SPEEDRUNNER_BLOCK,
            new Item.Settings());

    public static final BlockItem THRUSTER_BLOCK = new BlockItem(ModBlocks.THRUSTER_BLOCK,
            new Item.Settings());

    public static final BlockItem SPEEDRUNNER_ORE = new BlockItem(ModBlocks.SPEEDRUNNER_ORE,
            new Item.Settings());

    public static final BlockItem DEEPSLATE_SPEEDRUNNER_ORE = new BlockItem(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE,
            new Item.Settings());

    public static final BlockItem NETHER_SPEEDRUNNER_ORE = new BlockItem(ModBlocks.NETHER_SPEEDRUNNER_ORE,
            new Item.Settings());

    public static final BlockItem IGNEOUS_ORE = new BlockItem(ModBlocks.IGNEOUS_ORE,
            new Item.Settings());

    public static final BlockItem DEEPSLATE_IGNEOUS_ORE = new BlockItem(ModBlocks.DEEPSLATE_IGNEOUS_ORE,
            new Item.Settings());

    public static final BlockItem NETHER_IGNEOUS_ORE = new BlockItem(ModBlocks.NETHER_IGNEOUS_ORE,
            new Item.Settings());

    public static final BlockItem EXPERIENCE_ORE = new ExperienceOreItem(ModBlocks.EXPERIENCE_ORE, new Item.Settings());
    public static final BlockItem DEEPSLATE_EXPERIENCE_ORE = new ExperienceOreItem(ModBlocks.DEEPSLATE_EXPERIENCE_ORE, new Item.Settings());
    public static final BlockItem NETHER_EXPERIENCE_ORE = new ExperienceOreItem(ModBlocks.NETHER_EXPERIENCE_ORE, new Item.Settings());

    public static final BlockItem DOOM_STONE = new BlockItem(ModBlocks.DOOM_STONE,
            new Item.Settings());

    public static final BlockItem DOOM_LOG = new BlockItem(ModBlocks.DOOM_LOG,
            new Item.Settings());

    public static final BlockItem STRIPPED_DOOM_LOG = new BlockItem(ModBlocks.STRIPPED_DOOM_LOG,
            new Item.Settings());

    public static final BlockItem DOOM_LEAVES = new BlockItem(ModBlocks.DOOM_LEAVES,
            new Item.Settings());

    /**
     * Registers all Speedrunner Mod {@code block items.}
     */
    public static void registerBlockItems() {
        registerItem("speedrunner_log", SPEEDRUNNER_LOG);
        registerItem("stripped_speedrunner_log", STRIPPED_SPEEDRUNNER_LOG);
        registerItem("speedrunner_wood", SPEEDRUNNER_WOOD);
        registerItem("stripped_speedrunner_wood", STRIPPED_SPEEDRUNNER_WOOD);
        registerItem("speedrunner_leaves", SPEEDRUNNER_LEAVES);
        registerItem("speedrunner_sapling", SPEEDRUNNER_SAPLING);
        registerItem("speedrunner_planks", SPEEDRUNNER_PLANKS);
        registerItem("speedrunner_slab", SPEEDRUNNER_SLAB);
        registerItem("speedrunner_stairs", SPEEDRUNNER_STAIRS);
        registerItem("speedrunner_fence", SPEEDRUNNER_FENCE);
        registerItem("speedrunner_fence_gate", SPEEDRUNNER_FENCE_GATE);
        registerItem("wooden_speedrunner_trapdoor", WOODEN_SPEEDRUNNER_TRAPDOOR);
        registerItem("wooden_speedrunner_button", WOODEN_SPEEDRUNNER_BUTTON);
        registerItem("wooden_speedrunner_pressure_plate", WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        registerItem("wooden_speedrunner_door", WOODEN_SPEEDRUNNER_DOOR);
        registerItem("speedrunner_sign", SPEEDRUNNER_SIGN);
        registerItem("speedrunner_hanging_sign", SPEEDRUNNER_HANGING_SIGN);
        registerItem("dead_speedrunner_log", DEAD_SPEEDRUNNER_LOG);
        registerItem("dead_stripped_speedrunner_log", DEAD_STRIPPED_SPEEDRUNNER_LOG);
        registerItem("dead_speedrunner_wood", DEAD_SPEEDRUNNER_WOOD);
        registerItem("dead_stripped_speedrunner_wood", DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        registerItem("dead_speedrunner_leaves", DEAD_SPEEDRUNNER_LEAVES);
        registerItem("dead_speedrunner_sapling", DEAD_SPEEDRUNNER_SAPLING);
        registerItem("dead_speedrunner_planks", DEAD_SPEEDRUNNER_PLANKS);
        registerItem("dead_speedrunner_slab", DEAD_SPEEDRUNNER_SLAB);
        registerItem("dead_speedrunner_stairs", DEAD_SPEEDRUNNER_STAIRS);
        registerItem("dead_speedrunner_fence", DEAD_SPEEDRUNNER_FENCE);
        registerItem("dead_speedrunner_fence_gate", DEAD_SPEEDRUNNER_FENCE_GATE);
        registerItem("dead_wooden_speedrunner_trapdoor", DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
        registerItem("dead_wooden_speedrunner_button", DEAD_WOODEN_SPEEDRUNNER_BUTTON);
        registerItem("dead_wooden_speedrunner_pressure_plate", DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        registerItem("dead_wooden_speedrunner_door", DEAD_WOODEN_SPEEDRUNNER_DOOR);
        registerItem("dead_speedrunner_sign", DEAD_SPEEDRUNNER_SIGN);
        registerItem("dead_speedrunner_hanging_sign", DEAD_SPEEDRUNNER_HANGING_SIGN);
        registerItem("speedrunner_trapdoor", SPEEDRUNNER_TRAPDOOR);
        registerItem("speedrunner_weighted_pressure_plate", SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        registerItem("speedrunner_door", SPEEDRUNNER_DOOR);
        registerItem("dead_speedrunner_bush", DEAD_SPEEDRUNNER_BUSH);
        registerItem("speedrunners_workbench", SPEEDRUNNERS_WORKBENCH);
        registerItem("speedrunner_block", SPEEDRUNNER_BLOCK);
        registerItem("raw_speedrunner_block", RAW_SPEEDRUNNER_BLOCK);
        registerItem("thruster_block", THRUSTER_BLOCK);
        registerItem("speedrunner_ore", SPEEDRUNNER_ORE);
        registerItem("deepslate_speedrunner_ore", DEEPSLATE_SPEEDRUNNER_ORE);
        registerItem("nether_speedrunner_ore", NETHER_SPEEDRUNNER_ORE);
        registerItem("igneous_ore", IGNEOUS_ORE);
        registerItem("deepslate_igneous_ore", DEEPSLATE_IGNEOUS_ORE);
        registerItem("nether_igneous_ore", NETHER_IGNEOUS_ORE);
        registerItem("experience_ore", EXPERIENCE_ORE);
        registerItem("deepslate_experience_ore", DEEPSLATE_EXPERIENCE_ORE);
        registerItem("nether_experience_ore", NETHER_EXPERIENCE_ORE);

        registerItem("doom_stone", DOOM_STONE);
        registerItem("doom_log", DOOM_LOG);
        registerItem("stripped_doom_log", STRIPPED_DOOM_LOG);
        registerItem("doom_leaves", DOOM_LEAVES);
    }
}