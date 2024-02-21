package net.dillon8775.speedrunnermod.mixin.client.enchantment;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Environment(EnvType.CLIENT)
@Mixin(AnvilScreen.class)
public class RemoveTooExpensiveText {

    @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40))
    private int mixinLimitInt(int i) {
        if (SpeedrunnerMod.options().betterAnvil) {
            return Integer.MAX_VALUE;
        } else {
            return 40;
        }
    }
}