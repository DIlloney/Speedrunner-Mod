package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {

    @Inject(method = "wearsGoldArmor(Lnet/minecraft/entity/LivingEntity;)Z", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"), cancellable = true,locals = LocalCapture.CAPTURE_FAILHARD)
    private static void wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir, Iterable iterable, Iterator var2, ItemStack stack) {
        if (isPiglinSafeArmor(stack)) {
            cir.setReturnValue(true);
        }
    }

    @Overwrite
    private static boolean acceptsForBarter(ItemStack stack) {
        return stack.isIn(ModItemTags.BARTERING_ITEMS);
    }

    @ModifyArg(method = "getNearestZombifiedPiglin", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/PiglinEntity;isInRange(Lnet/minecraft/entity/Entity;D)Z"), index = 1)
    private static double nearestZombifiedPiglin(double radius) {
        return 2.0D;
    }

    private static boolean isPiglinSafeArmor(ItemStack stack) {
        return stack.isIn(ModItemTags.PIGLIN_SAFE_ARMOR);
    }
}