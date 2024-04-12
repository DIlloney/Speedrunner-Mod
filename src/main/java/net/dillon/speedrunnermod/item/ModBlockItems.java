package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * {@link SpeedrunnerMod} block items.
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
    public static final BlockItem DEAD_SPEEDRUNNER_LOG = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_LOG,
            new Item.Settings());
    public static final BlockItem DEAD_STRIPPED_SPEEDRUNNER_LOG = new BlockItem(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG,
            new Item.Settings());
    public static final BlockItem DEAD_SPEEDRUNNER_WOOD = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_WOOD,
            new Item.Settings());
    public static final BlockItem DEAD_STRIPPED_SPEEDRUNNER_WOOD = new BlockItem(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD,
            new Item.Settings());
    public static final BlockItem SPEEDRUNNER_LEAVES = new BlockItem(ModBlocks.SPEEDRUNNER_LEAVES,
            new Item.Settings());
    public static final BlockItem DEAD_SPEEDRUNNER_LEAVES = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_LEAVES,
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
    public static final BlockItem SPEEDRUNNER_TRAPDOOR = new BlockItem(ModBlocks.SPEEDRUNNER_TRAPDOOR,
            new Item.Settings());
    public static final BlockItem SPEEDRUNNER_BUTTON = new BlockItem(ModBlocks.SPEEDRUNNER_BUTTON,
            new Item.Settings());
    public static final BlockItem WOODEN_SPEEDRUNNER_PRESSURE_PLATE = new BlockItem(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE,
            new Item.Settings());
    public static final BlockItem SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = new BlockItem(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE,
            new Item.Settings());
    public static final BlockItem WOODEN_SPEEDRUNNER_DOOR = new TallBlockItem(ModBlocks.WOODEN_SPEEDRUNNER_DOOR,
            new Item.Settings());
    public static final BlockItem SPEEDRUNNER_DOOR = new BlockItem(ModBlocks.SPEEDRUNNER_DOOR,
            new Item.Settings());
    public static final BlockItem SPEEDRUNNER_SIGN = new SignItem(
            new Item.Settings().maxCount(16), ModBlocks.SPEEDRUNNER_SIGN, ModBlocks.SPEEDRUNNER_WALL_SIGN);
    public static final BlockItem SPEEDRUNNER_HANGING_SIGN = new HangingSignItem(
            ModBlocks.SPEEDRUNNER_HANGING_SIGN, ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN, new Item.Settings().maxCount(16));
    public static final BlockItem DEAD_SPEEDRUNNER_BUSH = new BlockItem(ModBlocks.DEAD_SPEEDRUNNER_BUSH,
            new Item.Settings());
    public static final Item SPEEDRUNNERS_WORKBENCH = new BlockItem(ModBlocks.SPEEDRUNNERS_WORKBENCH,
            new Item.Settings()) {

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
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

    public static void init() {
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_log"), SPEEDRUNNER_LOG);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "stripped_speedrunner_log"), STRIPPED_SPEEDRUNNER_LOG);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_wood"), SPEEDRUNNER_WOOD);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "stripped_speedrunner_wood"), STRIPPED_SPEEDRUNNER_WOOD);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_log"), DEAD_SPEEDRUNNER_LOG);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "dead_stripped_speedrunner_log"), DEAD_STRIPPED_SPEEDRUNNER_LOG);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_wood"), DEAD_SPEEDRUNNER_WOOD);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "dead_stripped_speedrunner_wood"), DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_leaves"), SPEEDRUNNER_LEAVES);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_leaves"), DEAD_SPEEDRUNNER_LEAVES);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_sapling"), SPEEDRUNNER_SAPLING);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_planks"), SPEEDRUNNER_PLANKS);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_slab"), SPEEDRUNNER_SLAB);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_stairs"), SPEEDRUNNER_STAIRS);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_fence"), SPEEDRUNNER_FENCE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_fence_gate"), SPEEDRUNNER_FENCE_GATE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "wooden_speedrunner_trapdoor"), WOODEN_SPEEDRUNNER_TRAPDOOR);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_trapdoor"), SPEEDRUNNER_TRAPDOOR);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_button"), SPEEDRUNNER_BUTTON);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "wooden_speedrunner_pressure_plate"), WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_weighted_pressure_plate"), SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "wooden_speedrunner_door"), WOODEN_SPEEDRUNNER_DOOR);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_door"), SPEEDRUNNER_DOOR);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_sign"), SPEEDRUNNER_SIGN);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_hanging_sign"), SPEEDRUNNER_HANGING_SIGN);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_bush"), DEAD_SPEEDRUNNER_BUSH);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunners_workbench"), SPEEDRUNNERS_WORKBENCH);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_block"), SPEEDRUNNER_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "raw_speedrunner_block"), RAW_SPEEDRUNNER_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "thruster_block"), THRUSTER_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_ore"), SPEEDRUNNER_ORE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "deepslate_speedrunner_ore"), DEEPSLATE_SPEEDRUNNER_ORE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "nether_speedrunner_ore"), NETHER_SPEEDRUNNER_ORE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "igneous_ore"), IGNEOUS_ORE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "deepslate_igneous_ore"), DEEPSLATE_IGNEOUS_ORE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "nether_igneous_ore"), NETHER_IGNEOUS_ORE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "experience_ore"), EXPERIENCE_ORE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "deepslate_experience_ore"), DEEPSLATE_EXPERIENCE_ORE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "nether_experience_ore"), NETHER_EXPERIENCE_ORE);

        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "doom_stone"), DOOM_STONE);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "doom_log"), DOOM_LOG);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "stripped_doom_log"), STRIPPED_DOOM_LOG);
        Registry.register(Registries.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "doom_leaves"), DOOM_LEAVES);

        info("Initialized block items.");
    }
}