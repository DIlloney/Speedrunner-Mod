<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.item;

import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Item.class)
public class ItemMixin {
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

    /**
     * Adds tooltips to items can be used to craft the piglin awakener item.
     */
    @Inject(method = "appendTooltip", at = @At("TAIL"))
    private void appendTooltipsForPiglinAwakenerItems(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        if (options().client.itemTooltips && stack.isIn(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
            tooltip.add(Text.translatable("item.speedrunnermod.piglin_awakener_craftable").formatted(Formatting.GOLD));
        }
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.item;

import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Item.class)
public class ItemMixin {
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

    /**
     * Adds tooltips to items can be used to craft the piglin awakener item.
     */
    @Inject(method = "appendTooltip", at = @At("TAIL"))
    private void appendTooltipsForPiglinAwakenerItems(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        if (options().client.itemTooltips && stack.isIn(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
            tooltip.add(Text.translatable("item.speedrunnermod.piglin_awakener_craftable").formatted(Formatting.GOLD));
        }
    }
>>>>>>> Stashed changes
}