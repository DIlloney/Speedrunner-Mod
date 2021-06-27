package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.util.UniqueItemRegistry;
import net.minecraft.block.TripwireBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TripwireBlock.class)
public class TripwireBlockMixin {
    @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean onBreak(ItemStack stack, Item isOfItem) {
        return UniqueItemRegistry.SHEARS.isItemInRegistry(stack.getItem());
    }
}