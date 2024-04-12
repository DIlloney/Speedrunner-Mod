<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.entity.basic;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.entity.ModBoatTypes;
import net.dillon8775.speedrunnermod.util.Author;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private int fireTicks;
    @Shadow @Nullable
    public abstract Entity getVehicle();
    @Shadow
    public World world;

    /**
     * Decreases time set on fire for from lava.
     */
    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
    private int setOnFireFromLava(int x) {
        return SpeedrunnerMod.getFireFromLavaTime();
    }

    /**
     * Decreases damage from lava.
     */
    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float setOnFireFromLava(float x) {
        return SpeedrunnerMod.getLavaDamageAmount();
    }

    /**
     * Allows players to ride in fireproof boats without burning from the lava.
     */
    @Author("Anxietie")
    @Inject(method = "setOnFireFromLava", at = @At("HEAD"), cancellable = true)
    private void setOnFireFromLava(CallbackInfo ci) {
        Entity vehicle = getVehicle();
        if (options().main.lavaBoats && vehicle instanceof BoatEntity boat && ModBoatTypes.isFireproofBoat(boat.getBoatType())) {
            if (fireTicks > 0 && fireTicks % 20 == 0) {
                ((Entity)(Object)this).damage(DamageSource.ON_FIRE, 1.0F);
            }
            ci.cancel();
        }
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.entity.basic;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.entity.ModBoatTypes;
import net.dillon8775.speedrunnermod.util.Author;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private int fireTicks;
    @Shadow @Nullable
    public abstract Entity getVehicle();
    @Shadow
    public World world;

    /**
     * Decreases time set on fire for from lava.
     */
    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
    private int setOnFireFromLava(int x) {
        return SpeedrunnerMod.getFireFromLavaTime();
    }

    /**
     * Decreases damage from lava.
     */
    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float setOnFireFromLava(float x) {
        return SpeedrunnerMod.getLavaDamageAmount();
    }

    /**
     * Allows players to ride in fireproof boats without burning from the lava.
     */
    @Author("Anxietie")
    @Inject(method = "setOnFireFromLava", at = @At("HEAD"), cancellable = true)
    private void setOnFireFromLava(CallbackInfo ci) {
        Entity vehicle = getVehicle();
        if (options().main.lavaBoats && vehicle instanceof BoatEntity boat && ModBoatTypes.isFireproofBoat(boat.getBoatType())) {
            if (fireTicks > 0 && fireTicks % 20 == 0) {
                ((Entity)(Object)this).damage(DamageSource.ON_FIRE, 1.0F);
            }
            ci.cancel();
        }
    }
>>>>>>> Stashed changes
}