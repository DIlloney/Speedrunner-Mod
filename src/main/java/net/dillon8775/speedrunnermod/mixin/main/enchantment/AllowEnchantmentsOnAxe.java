package net.dillon8775.speedrunnermod.mixin.main.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.enchantment.KnockbackEnchantment;
import net.minecraft.enchantment.LuckEnchantment;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class AllowEnchantmentsOnAxe {

    /**
     * Allows the {@code Fire Aspect, Knockback and Looting} enchantment to be applied to axes.
     */
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void allowEnchantmentsOnAxe(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Enchantment enchantment = (Enchantment)(Object)this;
        if (stack.getItem() instanceof AxeItem) {
            if (enchantment instanceof FireAspectEnchantment) {
                cir.setReturnValue(true);
            } else if (enchantment instanceof KnockbackEnchantment) {
                cir.setReturnValue(true);
            } else if (enchantment instanceof LuckEnchantment) {
                cir.setReturnValue(true);
            }
        }
    }
}