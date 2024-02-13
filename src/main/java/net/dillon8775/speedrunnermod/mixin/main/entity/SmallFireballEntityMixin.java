package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.projectile.SmallFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SmallFireballEntity.class)
public class SmallFireballEntityMixin {

    @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
    private int onEntityHit(int x) {
        return SpeedrunnerMod.options().main.doomMode ? 6 : 3;
    }

    @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 1)
    private float onEntityHit(float x) {
        return SpeedrunnerMod.options().main.doomMode ? 5.0F : 3.0F;
    }
}