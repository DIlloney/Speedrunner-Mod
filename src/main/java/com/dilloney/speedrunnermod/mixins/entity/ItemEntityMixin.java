package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Inject(method = "isFireImmune", at = @At("RETURN"))
    public boolean makeFireproofsFireImmune(CallbackInfoReturnable info) {
        ItemEntity item = (ItemEntity)(Object)this;
        ItemStack stack = item.getStack();

        if (stack.isIn(ModItemTags.FIREPROOFS)) {
            return true;
        }
        return info.getReturnValueZ();
    }
}