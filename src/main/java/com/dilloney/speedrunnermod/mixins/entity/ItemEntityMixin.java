package com.dilloney.speedrunnermod.mixins.entity;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Inject(method = "isFireImmune", at = @At("RETURN"))
    public boolean isFireImmune(CallbackInfoReturnable info) {
        ItemEntity item = (ItemEntity) (Object) this;
        ItemStack stack = item.getStack();

        if (stack.getItem() == Items.BLAZE_ROD) {
            return true;
        }
        if (stack.getItem() == Items.BLAZE_POWDER) {
            return true;
        }
        if (stack.getItem() == Items.FIRE_CHARGE) {
            return true;
        }
        if (stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
            return true;
        }
        return info.getReturnValueZ();
    }
}
