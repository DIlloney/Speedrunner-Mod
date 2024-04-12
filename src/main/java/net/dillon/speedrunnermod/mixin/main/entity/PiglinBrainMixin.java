package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.Author;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {

    /**
     * Allows players to be able to wear modded items, such as golden speedrunner armor, and still be safe from piglin.
     */
    @Author("UNKNOWN")
    @Inject(method = "wearsGoldArmor(Lnet/minecraft/entity/LivingEntity;)Z", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private static void wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir, Iterable iterable, Iterator var2, ItemStack stack) {
        if (isPiglinSafeArmor(stack)) {
            cir.setReturnValue(true);
        }
    }

    /**
     * Removes the first argument of the original method, and makes piglins {@code willing to trade}, even if they were previously hit by the player.
     */
    @Overwrite
    public static boolean isWillingToTrade(PiglinEntity piglin, ItemStack nearbyItems) {
        return !PiglinBrain.isAdmiringItem(piglin) && piglin.isAdult() && PiglinBrain.acceptsForBarter(nearbyItems);
    }

    /**
     * Lowers the distance piglins have to be in order for them to run away from the nearest zombified piglin.
     */
    @ModifyArg(method = "getNearestZombifiedPiglin", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/PiglinEntity;isInRange(Lnet/minecraft/entity/Entity;D)Z"), index = 1)
    private static double nearestZombifiedPiglin(double radius) {
        return SpeedrunnerMod.getZombifiedPiglinRunawayDistance();
    }

    /**
     * Gets if the player is wearing a piglin safe armor item.
     */
    @Unique
    private static boolean isPiglinSafeArmor(ItemStack stack) {
        return stack.isIn(ModItemTags.PIGLIN_SAFE_ARMOR);
    }
}