package net.dillon.speedrunnermod.item;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

/**
 * All Speedrunner Mod {@code tool materials} (for pickaxes, axes, swords, etc.)
 */
public class ModToolMaterials {
    public static final ToolMaterial SPEEDRUNNER_SHOVEL_AXE_HOE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 11.0F, 0.0F, 17, ModItems.SPEEDRUNNER_INGOT);
    public static final ToolMaterial SPEEDRUNNER_SWORD_PICKAXE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0F, 1.0F, 5, ModItems.SPEEDRUNNER_INGOT);
    public static final ToolMaterial GOLDEN_SPEEDRUNNER = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 72, 13.0F, 0.0F, 25, Items.GOLD_INGOT);
    public static final ToolMaterial WITHER_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 27, 4.0F, 1.5F, 7, ModItems.WITHER_BONE);
    public static final ToolMaterial DRAGONS_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2036, 14.0F, 0.0F, 30, ModItems.DRAGONS_PEARL);
}
