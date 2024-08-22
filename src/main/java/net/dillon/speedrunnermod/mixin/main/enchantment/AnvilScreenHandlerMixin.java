package net.dillon.speedrunnermod.mixin.main.enchantment;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {
    @Shadow @Final
    private Property levelCost;

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 40))
    private int mixinLimitInt(int i) {
        if (SpeedrunnerMod.options().main.betterAnvil) {
            return Integer.MAX_VALUE;
        } else {
            return 40;
        }
    }

    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 39))
    private int mixinMaxInt(int i) {
        if (SpeedrunnerMod.options().main.betterAnvil) {
            return Integer.MAX_VALUE - 1;
        } else {
            return 39;
        }
    }

    @Inject(method = "updateResult", at = @At("TAIL"))
    private void setLevelCostIfTooHigh(CallbackInfo ci) {
        if (SpeedrunnerMod.options().main.anvilCostLimit != 50) {
            this.levelCost.set(SpeedrunnerMod.options().main.anvilCostLimit);
        }
    }

    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
    private int countOverMaxLevel(Enchantment enchantment) {
//        ItemStack itemStack = this.input.getStack(0);
//        ItemStack itemStack2 = itemStack.copy();
//        ItemStack itemStack3 = this.input.getStack(1);
//        ItemEnchantmentsComponent map = EnchantmentHelper.getEnchantments(itemStack2);
//        ItemEnchantmentsComponent map2 = EnchantmentHelper.getEnchantments(itemStack3);
//        int q = map.getOrDefault(enchantment, 0);
//        int getCurrentLevelAndAddOne = q == (getCurrentLevelAndAddOne = map2.get(enchantment)) ? getCurrentLevelAndAddOne + 1 : Math.max(getCurrentLevelAndAddOne, q);
//        if (getCurrentLevelAndAddOne > 100) {
//            getCurrentLevelAndAddOne = 100;
//        }
//        boolean isntOne = enchantment.getMaxLevel() != 1;
//        return options().main.higherEnchantmentLevels && isntOne ? getCurrentLevelAndAddOne : enchantment.getMaxLevel();
        return 1;
    }
}