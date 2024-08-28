package net.dillon.speedrunnermod.mixin.main.enchantment;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Improves the anvils functionality by adding and changing several different features.
 */
@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {
    @Shadow @Final
    private Property levelCost;

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    /**
     * Sets the maximum cost for an anvil.
     */
    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 40))
    private int mixinLimitInt(int i) {
        if (options().main.betterAnvil) {
            return Integer.MAX_VALUE;
        } else {
            return 40;
        }
    }

    /**
     * Sets the maximum cost for an anvil, - 1.
     */
    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 39))
    private int mixinMaxInt(int i) {
        if (options().main.betterAnvil) {
            return Integer.MAX_VALUE - 1;
        } else {
            return 39;
        }
    }

    /**
     * Sets the anvil cost to the {@code anvil cost limit} option if the cost exceeds the limit value (unless the anvil cost limit is 50, meaning that there is no limit).
     */
    @Inject(method = "updateResult", at = @At("TAIL"))
    private void setLevelCostIfTooHigh(CallbackInfo ci) {
        if (options().main.anvilCostLimit != 50) {
            this.levelCost.set(options().main.anvilCostLimit);
        }
    }

    /**
     * Allows the combination of two maximum level enchanted items to go above the enchantment level cap.
     */
    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
    private int countOverMaxLevel(Enchantment enchantment) {
        ItemStack itemStack = this.input.getStack(0); // Gets the input of the first slot in the anvil
        ItemStack itemStack2 = itemStack.copy(); // Copies the first input in the anvil, used for the result
        ItemStack itemStack3 = this.input.getStack(1); // Gets the input of the second slot in the anvil
        ItemEnchantmentsComponent itemStack2Component = EnchantmentHelper.getEnchantments(itemStack2); // Gets the enchantments on the "copied" first stack
        ItemEnchantmentsComponent itemStack3Component = EnchantmentHelper.getEnchantments(itemStack3); // Gets the enchantments on the second slot in the anvil
        int getCurrentLevelAndAddOne = 0; // Start enchantment level at zero

        // For each enchantment on the copied slot
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemStack2Component.getEnchantmentEntries()) {
            RegistryEntry registryEntry = entry.getKey(); // Get the enchantment entry
            int q = itemStack2Component.getLevel(registryEntry); // Get the enchantment entry enchantment level

            // If current level is equal to the enchantment level in second slot, get the current level and add one
            // Otherwise, return the maximum value between the enchantment level in second slot and q
            getCurrentLevelAndAddOne = q == (getCurrentLevelAndAddOne = itemStack3Component.getLevel(registryEntry)) ? getCurrentLevelAndAddOne + 1 : Math.max(getCurrentLevelAndAddOne, q);

            // Cap the enchantment level at 100
            if (getCurrentLevelAndAddOne > 100) {
                getCurrentLevelAndAddOne = 100;
            }
        }

        // Return the incremented integer value, unless the default maximum level is 1, then there is no point to increment
        return options().main.higherEnchantmentLevels && enchantment.getMaxLevel() != 1 ? getCurrentLevelAndAddOne : enchantment.getMaxLevel();
    }
}