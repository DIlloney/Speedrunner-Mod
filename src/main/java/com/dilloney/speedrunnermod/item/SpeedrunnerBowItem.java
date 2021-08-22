package com.dilloney.speedrunnermod.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class SpeedrunnerBowItem extends BowItem {

    public SpeedrunnerBowItem(Settings settings) {
        super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).maxCount(1).maxDamage(768));
    }

    @Override
    public int getEnchantability() {
        return 17;
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 1.5F;
    }
}