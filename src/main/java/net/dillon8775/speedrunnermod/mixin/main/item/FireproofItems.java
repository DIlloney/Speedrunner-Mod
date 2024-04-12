package net.dillon8775.speedrunnermod.mixin.main.item;

import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.dillon8775.speedrunnermod.util.Author;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Mixin(ItemEntity.class)
public class FireproofItems {

    /**
     * Makes all items in the {@code Fireproof Items} tag, fireproof.
     */
    @Author("UNKNOWN")
    @Inject(method = "isFireImmune", at = @At("RETURN"), cancellable = true)
    public void isFireImmune(CallbackInfoReturnable<Boolean> cir) {
        ItemEntity item = (ItemEntity)(Object)this;
        ItemStack stack = item.getStack();

        if (options().main.fireproofItems) {
            if (stack.isIn(ModItemTags.FIREPROOF_ITEMS)) {
                cir.setReturnValue(true);
            }
        }

        cir.getReturnValueZ();
    }
}