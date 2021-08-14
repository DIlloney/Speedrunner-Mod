package com.dilloney.speedrunnermod.mixins.misc;

import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {

    @Inject(method = "acceptsForBarter(Lnet/minecraft/item/ItemStack;)Z", at = @At("RETURN"), cancellable = true)
    private static void allowPiglinsToBarterWithOtherItems(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (stack.isIn(ModItemTags.BARTERING_ITEMS)) {
            callbackInfoReturnable.setReturnValue(true);
        }
    }

    @Inject(method = "wearsGoldArmor(Lnet/minecraft/entity/LivingEntity;)Z", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private static void makePiglinsLikePiglinSafeArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> callbackInfoReturnable, Iterable<ItemStack> iterable, Iterator iterator, ItemStack stack, Item item) {
        if (stack.isIn(ModItemTags.PIGLIN_SAFE_ARMOR)) {
            callbackInfoReturnable.setReturnValue(true);
        }
    }

    @Overwrite
    private static boolean getNearestZombifiedPiglin(PiglinEntity piglin) {
        Brain<PiglinEntity> brain = piglin.getBrain();
        if (brain.hasMemoryModule(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED)) {
            LivingEntity livingEntity = (LivingEntity)brain.getOptionalMemory(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED).get();
            return piglin.isInRange(livingEntity, 2.0D);
        } else {
            return false;
        }
    }
}