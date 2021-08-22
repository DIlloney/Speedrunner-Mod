package com.dilloney.speedrunnermod.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.tag.ItemTags;

public class SpeedrunnerShieldItem extends ShieldItem {

    public SpeedrunnerShieldItem(Settings settings) {
        super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).maxCount(1).maxDamage(672));
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 1.5F;
    }

    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(ModItems.SPEEDRUNNER_INGOT) || ingredient.isIn(ItemTags.PLANKS);
    }
}