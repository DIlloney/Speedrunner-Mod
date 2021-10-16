package net.dilloney.speedrunnermod.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.BlockTags;

public class SpeedrunnerSwordItem extends SwordItem {

    public SpeedrunnerSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (stack.isOf(ModItems.SPEEDRUNNER_SWORD) && state.isOf(Blocks.COBWEB)) {
            return 16.0F;
        } else if (stack.isOf(ModItems.GOLDEN_SPEEDRUNNER_SWORD) && state.isOf(Blocks.COBWEB)) {
            return 17.0F;
        } else {
            Material material = state.getMaterial();
            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && !state.isIn(BlockTags.LEAVES) && material != Material.GOURD ? 1.0F : 1.5F;
        }
    }
}