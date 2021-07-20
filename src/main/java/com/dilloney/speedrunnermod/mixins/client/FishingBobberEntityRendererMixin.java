package com.dilloney.speedrunnermod.mixins.client;

import com.dilloney.speedrunnermod.util.UniqueItemRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(FishingBobberEntityRenderer.class)
public class FishingBobberEntityRendererMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean renderMod(ItemStack stack, Item item) {
        return UniqueItemRegistry.FISHING_ROD.isItemInRegistry(stack.getItem());
    }
}