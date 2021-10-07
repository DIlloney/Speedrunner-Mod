package com.dilloney.speedrunnermod.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.tag.ItemTags;

public class SpeedrunnerShieldItem extends ShieldItem {

    public SpeedrunnerShieldItem(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(ModItems.SPEEDRUNNER_INGOT) || ingredient.isIn(ItemTags.PLANKS);
    }
}