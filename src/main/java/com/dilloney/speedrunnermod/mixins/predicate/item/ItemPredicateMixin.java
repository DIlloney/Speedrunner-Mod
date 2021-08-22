package com.dilloney.speedrunnermod.mixins.predicate.item;

import com.dilloney.speedrunnermod.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemPredicate.class)
public class ItemPredicateMixin {

    @ModifyVariable(method = "test", at = @At("HEAD"))
    public ItemStack makeSpeedrunnerItemsWorkLikeVanillaItems(ItemStack stack) {
        if (stack.getItem() == ModItems.SPEEDRUNNER_BOW.getDefaultStack().getItem()) {
            ItemStack itemStack = new ItemStack(Items.BOW);
            itemStack.setCooldown(stack.getCooldown());
            itemStack.setTag(stack.getOrCreateTag());
            return itemStack;
        }

        if (stack.getItem() == ModItems.SPEEDRUNNER_CROSSBOW.getDefaultStack().getItem()) {
            ItemStack itemStack = new ItemStack(Items.CROSSBOW);
            itemStack.setCooldown(stack.getCooldown());
            itemStack.setTag(stack.getOrCreateTag());
            return itemStack;
        }

        if (stack.getItem() == ModItems.SPEEDRUNNER_SHEARS.getDefaultStack().getItem()) {
            ItemStack itemStack = new ItemStack(Items.SHEARS);
            itemStack.setCount(stack.getCount());
            itemStack.setTag(stack.getOrCreateTag());
            return itemStack;
        }

        if (stack.getItem() == ModItems.SPEEDRUNNER_FLINT_AND_STEEL.getDefaultStack().getItem()) {
            ItemStack itemStack = new ItemStack(Items.FLINT_AND_STEEL);
            itemStack.setCount(stack.getCount());
            itemStack.setTag(stack.getOrCreateTag());
            return itemStack;
        }

        if (stack.getItem() == ModItems.SPEEDRUNNER_FISHING_ROD.getDefaultStack().getItem()) {
            ItemStack itemStack = new ItemStack(Items.FISHING_ROD);
            itemStack.setCount(stack.getCount());
            itemStack.setTag(stack.getOrCreateTag());
            return itemStack;
        }

        if (stack.getItem() == ModItems.SPEEDRUNNER_CARROT_ON_A_STICK.getDefaultStack().getItem()) {
            ItemStack itemStack = new ItemStack(Items.CARROT_ON_A_STICK);
            itemStack.setCount(stack.getCount());
            itemStack.setTag(stack.getOrCreateTag());
            return itemStack;
        }

        if (stack.getItem() == ModItems.SPEEDRUNNER_WARPED_FUNGUS_ON_A_STICK.getDefaultStack().getItem()) {
            ItemStack itemStack = new ItemStack(Items.WARPED_FUNGUS_ON_A_STICK);
            itemStack.setCount(stack.getCount());
            itemStack.setTag(stack.getOrCreateTag());
            return itemStack;
        }

        if (stack.getItem() == ModItems.SPEEDRUNNER_SHIELD.getDefaultStack().getItem()) {
            ItemStack itemStack = new ItemStack(Items.SHIELD);
            itemStack.setCount(stack.getCount());
            itemStack.setTag(stack.getOrCreateTag());
            return itemStack;
        }

        return stack;
    }
}