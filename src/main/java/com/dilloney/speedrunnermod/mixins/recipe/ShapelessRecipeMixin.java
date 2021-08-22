package com.dilloney.speedrunnermod.mixins.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapelessRecipe.class)
public abstract class ShapelessRecipeMixin {

    @Shadow @Final Identifier id;

    @Inject(at = @At("HEAD"), method = "matches", cancellable = true)
    private void cancelCraftingWithInfinityPearls(CraftingInventory craftingInventory, World world, CallbackInfoReturnable<Boolean> info) {
        if (id.toString().equals("minecraft:ender_eye") || id.toString().equals("speedrunnermod:inferno_eye") || id.toString().equals("speedrunnermod:annul_eye")) {
            for (int i = 0; i < craftingInventory.size(); i++) {
                ItemStack itemStack = craftingInventory.getStack(i);
                if (itemStack.hasEnchantments()) {
                    info.setReturnValue(false);
                }
            }
        }
    }
}