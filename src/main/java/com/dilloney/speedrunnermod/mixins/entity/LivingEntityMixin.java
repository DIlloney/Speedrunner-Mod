package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Redirect(method = "getPreferredEquipmentSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 2))
    private static boolean allowSpeedrunnerShieldToBePreferredInOffhand(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.SHIELDS);
    }
}