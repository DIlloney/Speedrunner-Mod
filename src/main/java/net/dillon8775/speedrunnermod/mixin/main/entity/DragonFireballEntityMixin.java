package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.projectile.DragonFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(DragonFireballEntity.class)
public class DragonFireballEntityMixin {

    @ModifyArg(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;II)V"), index = 2)
    private int onCollision(int x) {
        return SpeedrunnerMod.options().main.doomMode ? 1 : 0;
    }
}