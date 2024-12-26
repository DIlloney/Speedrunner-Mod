package net.dillon.speedrunnermod.mixin.main.fix;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {

    /**
     * Fixes {@code speedrunner shears} not working on beehives.
     */
    @Redirect(method = "onUseWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean onUse(ItemStack stack, Item item) {
        return stack.isIn(ConventionalItemTags.SHEAR_TOOLS);
    }
}