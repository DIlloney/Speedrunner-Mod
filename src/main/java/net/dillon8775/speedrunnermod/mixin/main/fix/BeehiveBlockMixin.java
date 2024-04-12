<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.fix;

import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {

    /**
     * Fixes speedrunner shears not working on beehives.
     */
    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean onUse(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.SHEARS);
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.fix;

import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {

    /**
     * Fixes speedrunner shears not working on beehives.
     */
    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean onUse(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.SHEARS);
    }
>>>>>>> Stashed changes
}