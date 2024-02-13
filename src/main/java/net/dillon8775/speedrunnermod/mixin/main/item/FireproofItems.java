package net.dillon8775.speedrunnermod.mixin.main.item;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class FireproofItems {

    /**
     * Makes all items in the {@code Fireproof Items} tag, fireproof.
     */
    @Inject(method = "isFireImmune", at = @At("RETURN"), cancellable = true)
    public void isFireImmune(CallbackInfoReturnable cir) {
        ItemEntity item = (ItemEntity)(Object)this;
        ItemStack stack = item.getStack();

        if (SpeedrunnerMod.options().main.fireproofItems) {
            if (stack.isIn(ModItemTags.FIREPROOF_ITEMS)) {
                cir.setReturnValue(true);
            }
        }

        cir.getReturnValueZ();
    }
}