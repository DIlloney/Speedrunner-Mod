package com.dilloney.speedrunnermod.mixins.item;

import com.dilloney.speedrunnermod.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemPredicate.class)
public class ItemPredicateMixin {

    @ModifyVariable(method = "test", at = @At("HEAD"))
    public ItemStack testMod(ItemStack stack) {
        if (stack.getItem() == ModItems.SPEEDRUNNER_SHEARS.getDefaultStack().getItem()) {
            ItemStack itemStack = new ItemStack(Items.SHEARS);
            itemStack.setCount(stack.getCount());
            itemStack.setTag(stack.getOrCreateTag());
            return itemStack;
        }
        return stack;
    }
}