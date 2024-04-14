package net.dillon.speedrunnermod.mixin.main.recipe;

import net.minecraft.inventory.RecipeInputInventory;
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
public class ShapelessRecipeMixin {
    @Shadow @Final
    private Identifier id;

    /**
     * Disables {@code Infini Pearl ender pearls} from being able to craft certain items.
     */
    @Inject(method = "matches(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/world/World;)Z", at = @At("HEAD"), cancellable = true)
    private void matches(RecipeInputInventory recipeInputInventory, World world, CallbackInfoReturnable<Boolean> cir) {
        if (id.toString().equals("minecraft:ender_eye") ||
                id.toString().equals("speedrunnermod:inferno_eye") ||
                id.toString().equals("speedrunnermod:annul_eye") ||
                id.toString().equals("speedrunnermod:speedrunners_eye") ||
                id.toString().equals("speedrunnermod:dragons_pearl") ||
                id.toString().equals("speedrunnermod:ender_thruster") ||
                id.toString().equals("speedrunnermod:blaze_spotter") ||
                id.toString().equals("speedrunnermod:raid_eradicator")) {
            for (int i = 0; i < recipeInputInventory.size(); i++) {
                ItemStack itemStack = recipeInputInventory.getStack(i);
                if (itemStack.hasEnchantments()) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}