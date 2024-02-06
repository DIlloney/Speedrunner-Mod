package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.entity.ModEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.BoatEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private int fireTicks;
    @Shadow @Nullable
    public abstract Entity getVehicle();

    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
    private int setOnFireFromLava(int x) {
        return SpeedrunnerMod.options().main.doomMode ? 15 : 7;
    }

    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float setOnFireFromLava(float x) {
        return SpeedrunnerMod.options().main.doomMode ? 4.0F : 2.0F;
    }

    /**
     * Allows players to ride in fireproof boats without burning from the lava.
     */
    @Inject(method = "setOnFireFromLava", at = @At("HEAD"), cancellable = true)
    private void setOnFireFromLava(CallbackInfo ci) {
        Entity vehicle = getVehicle();
        if (SpeedrunnerMod.options().advanced.allowBoatsInLava && vehicle instanceof BoatEntity boat && ModEntityTypes.isFireproofBoat(boat.getBoatType())) {
            if (fireTicks > 0 && fireTicks % 20 == 0) {
                ((Entity)(Object)this).damage(DamageSource.ON_FIRE, 1.0f);
            }
            ci.cancel();
        }
    }
}