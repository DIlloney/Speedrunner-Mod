package net.dillon8775.speedrunnermod.mixin.main.block;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.block.BedBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BedBlock.class)
public class BedBlockMixin {

    /**
     * Makes beds more powerful when on doom mode.
     */
    @ModifyArg(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"), index = 6)
    private float createExplosion(float power) {
        return SpeedrunnerMod.options().main.doomMode ? 15.0F : 5.0F;
    }
}