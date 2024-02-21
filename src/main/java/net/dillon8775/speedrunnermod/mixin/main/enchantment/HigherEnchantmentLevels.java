package net.dillon8775.speedrunnermod.mixin.main.enchantment;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(AnvilScreenHandler.class)
public abstract class HigherEnchantmentLevels extends ForgingScreenHandler {

    public HigherEnchantmentLevels(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
    private int countOverMaxLevel(Enchantment enchantment) {
        ItemStack itemStack = this.input.getStack(0);
        ItemStack itemStack2 = itemStack.copy();
        ItemStack itemStack3 = this.input.getStack(1);
        Map<Enchantment, Integer> map = EnchantmentHelper.get(itemStack2);
        Map<Enchantment, Integer> map2 = EnchantmentHelper.get(itemStack3);
        int q = map.getOrDefault(enchantment, 0);
        int getCurrentLevelAndAddOne = q == (getCurrentLevelAndAddOne = map2.get(enchantment).intValue()) ? getCurrentLevelAndAddOne + 1 : Math.max(getCurrentLevelAndAddOne, q);
        if (getCurrentLevelAndAddOne > 100) {
            getCurrentLevelAndAddOne = 100;
        }
        boolean isntOne = enchantment.getMaxLevel() != 1;
        return SpeedrunnerMod.options().higherEnchantmentLevels && isntOne ? getCurrentLevelAndAddOne : enchantment.getMaxLevel();
    }
}