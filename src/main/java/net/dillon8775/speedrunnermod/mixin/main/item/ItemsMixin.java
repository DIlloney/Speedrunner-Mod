package net.dillon8775.speedrunnermod.mixin.main.item;

import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Item.class)
public class ItemsMixin {
    @Shadow @Final
    private int maxCount;

    /**
     * Increases the maximum count for certain items, if {@code stack unstackables} is on.
     */
    @Inject(method = "getMaxCount", at = @At("HEAD"), cancellable = true)
    private void getItemMaxCounts(CallbackInfoReturnable<Integer> info) {
        Item item = (Item)(Object)this;
        if (item.getDefaultStack().isIn(ModItemTags.STACK_TO_64)) {
            info.setReturnValue(options().main.stackUnstackables ? 64 : this.maxCount);
        }
    }
}