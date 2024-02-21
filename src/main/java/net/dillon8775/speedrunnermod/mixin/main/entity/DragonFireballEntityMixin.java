package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.projectile.DragonFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(DragonFireballEntity.class)
public class DragonFireballEntityMixin {

    @ModifyArg(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;II)V"), index = 2)
    private int onCollision(int x) {
        return DOOM_MODE ? 1 : 0;
    }
}