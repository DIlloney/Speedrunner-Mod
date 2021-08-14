package com.dilloney.speedrunnermod.mixins.entity.projectile;

import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {

    @Redirect(method = "removeIfInvalid", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean makeBobberEntityWorkWithSpeedrunnerFishingRod(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.FISHING_RODS);
    }
}