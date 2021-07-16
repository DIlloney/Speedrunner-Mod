package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.util.UniqueItemRegistry;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PumpkinBlock.class)
public class PumpkinBlockMixin {

    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean onUseMod(ItemStack stack, Item isOfItem) {
        return UniqueItemRegistry.SHEARS.isItemInRegistry(stack.getItem());
    }
}