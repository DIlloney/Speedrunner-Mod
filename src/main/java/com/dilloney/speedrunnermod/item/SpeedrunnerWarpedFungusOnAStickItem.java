package com.dilloney.speedrunnermod.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.OnAStickItem;

public class SpeedrunnerWarpedFungusOnAStickItem extends OnAStickItem {

    public SpeedrunnerWarpedFungusOnAStickItem(Settings settings) {
        super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).maxDamage(150), EntityType.STRIDER, 1);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 1.5F;
    }
}