package com.dilloney.speedrunnermod.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.tag.BlockTags;

public final class SpeedrunnerShearsItem extends ShearsItem {

    public SpeedrunnerShearsItem(Settings settings) {
        super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).maxCount(1).maxDamage(476));
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (!state.isOf(Blocks.COBWEB) && !state.isIn(BlockTags.LEAVES)) {
            if (state.isIn(BlockTags.WOOL) || state.isIn(BlockTags.CARPETS)) {
                return 7.5F;
            } else {
                return !state.isOf(Blocks.VINE) && !state.isOf(Blocks.GLOW_LICHEN) ? super.getMiningSpeedMultiplier(stack, state) : 2.0F;
            }
        } else {
            return 17.0F;
        }
    }
}