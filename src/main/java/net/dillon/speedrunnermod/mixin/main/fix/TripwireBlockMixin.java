package net.dillon.speedrunnermod.mixin.main.fix;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.block.TripwireBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TripwireBlock.class)
public class TripwireBlockMixin {

    /**
     * Fixes {@code speedrunner shears} not working on tripwire hooks.
     */
    @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean onBreak(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.SHEARS);
    }
}