package net.dillon8775.speedrunnermod.mixin.main.fix;

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
public class FixShapelessRecipe {
    @Shadow @Final
    private Identifier id;

    /**
     * Fixes the ender eye recipes not working correctly.
     */
    @Inject(method = "matches", at = @At("HEAD"), cancellable = true)
    private void matches(CraftingInventory craftingInventory, World world, CallbackInfoReturnable<Boolean> cir) {
        if (id.toString().equals("minecraft:ender_eye") || id.toString().equals("speedrunnermod:inferno_eye") || id.toString().equals("speedrunnermod:annul_eye")) {
            for (int i = 0; i < craftingInventory.size(); i++) {
                ItemStack itemStack = craftingInventory.getStack(i);
                if (itemStack.hasEnchantments()) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}