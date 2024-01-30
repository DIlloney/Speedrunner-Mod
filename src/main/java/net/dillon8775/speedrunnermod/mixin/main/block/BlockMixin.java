package net.dillon8775.speedrunnermod.mixin.main.block;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Block.class)
public class BlockMixin {

    /**
     * Changes the player's fall damage multiplier when {@code Doom Mode} is on.
     */
    @ModifyArg(method = "onLandedUpon", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;handleFallDamage(FFLnet/minecraft/entity/damage/DamageSource;)Z"), index = 1)
    private float onLandedUpon(float damageMultiplier) {
        return SpeedrunnerMod.options().main.doomMode ? 1.0F : 0.7F;
    }
}