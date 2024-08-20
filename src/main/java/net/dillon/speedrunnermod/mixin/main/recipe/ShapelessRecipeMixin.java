package net.dillon.speedrunnermod.mixin.main.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.input.CraftingRecipeInput;
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
    @Inject(method = "matches(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/world/World;)Z", at = @At("HEAD"), cancellable = true)
    private void matches(CraftingRecipeInput craftingRecipeInput, World world, CallbackInfoReturnable<Boolean> cir) {
        for (int i = 0; i < craftingRecipeInput.getSize(); i++) {
            ItemStack itemStack = craftingRecipeInput.getStackInSlot(i);
            if (options().main.infiniPearlMode && itemStack.isOf(Items.ENDER_PEARL) && itemStack.hasEnchantments()) {
                cir.setReturnValue(false);
            }
        }
    }
}