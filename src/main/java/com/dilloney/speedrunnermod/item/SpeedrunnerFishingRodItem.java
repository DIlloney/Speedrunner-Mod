package com.dilloney.speedrunnermod.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public final class SpeedrunnerFishingRodItem extends FishingRodItem {

    public SpeedrunnerFishingRodItem(Settings settings) {
        super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).maxCount(1).maxDamage(128));
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