package com.dilloney.speedrunnermod.mixins.item;

import com.dilloney.speedrunnermod.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SwordItem.class)
public class SwordItemMixin {

    @Overwrite
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (state.isOf(Blocks.COBWEB)) {
            return 15.0F;
        } else if (state.isOf(Blocks.COBWEB) && stack.getItem() == ModItems.SPEEDRUNNER_SWORD || state.isOf(Blocks.COBWEB) && stack.getItem() == ModItems.GOLDEN_SPEEDRUNNER_SWORD) {
            return 20.0F;
        } else {
            Material material = state.getMaterial();
            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && !state.isIn(BlockTags.LEAVES) && material != Material.GOURD ? 1.0F : 1.5F;
        }
    }
}