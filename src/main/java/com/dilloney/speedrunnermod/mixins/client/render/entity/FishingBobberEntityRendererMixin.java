package com.dilloney.speedrunnermod.mixins.client.render.entity;

import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishingBobberEntityRenderer.class)
public class FishingBobberEntityRendererMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean renderBobberEntityWithSpeedrunnerFishingRod(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.FISHING_RODS);
    }
}