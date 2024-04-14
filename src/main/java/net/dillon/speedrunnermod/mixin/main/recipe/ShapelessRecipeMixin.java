package net.dillon.speedrunnermod.mixin.main.recipe;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(ShapelessRecipe.class)
public abstract class ShapelessRecipeMixin implements CraftingRecipe {

    /**
     * Disables {@code Infini Pearl ender pearls} from being able to craft certain items.
     */
    @Inject(method = "matches(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/world/World;)Z", at = @At("HEAD"), cancellable = true)
    private void matches(RecipeInputInventory recipeInputInventory, World world, CallbackInfoReturnable<Boolean> cir) {
        for (int i = 0; i < recipeInputInventory.size(); i++) {
            ItemStack itemStack = recipeInputInventory.getStack(i);
            if (options().main.infiniPearlMode && itemStack.isOf(Items.ENDER_PEARL) && itemStack.hasEnchantments()) {
                cir.setReturnValue(false);
            }
        }
    }
}