package net.dillon8775.speedrunnermod.mixin.main.block;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreen.class)
public class RemoveTooExpensiveText {

    @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40))
    private int mixinLimitInt(int i) {
        if (SpeedrunnerMod.options().advanced.betterAnvil) {
            return Integer.MAX_VALUE;
        } else {
            return 40;
        }
    }
}