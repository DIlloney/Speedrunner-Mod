package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.minecraft.block.TripwireBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TripwireBlock.class)
public class TripwireBlockMixin {

    @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean allowSpeedrunnerShearsOnTripwireBlocks(ItemStack stack, Item isOfItem) {
        return stack.isIn(ModItemTags.SHEARS);
    }
}