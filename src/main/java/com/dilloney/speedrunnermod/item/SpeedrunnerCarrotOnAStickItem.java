package com.dilloney.speedrunnermod.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.OnAStickItem;

public final class SpeedrunnerCarrotOnAStickItem extends OnAStickItem {

    public SpeedrunnerCarrotOnAStickItem(Settings settings) {
        super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).maxDamage(50), EntityType.PIG, 3);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 1.5F;
    }
}