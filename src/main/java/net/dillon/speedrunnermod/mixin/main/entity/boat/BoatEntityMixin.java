package net.dillon.speedrunnermod.mixin.main.entity.boat;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.sound.ModSoundEvents;
import net.dillon.speedrunnermod.tag.ModFluidTags;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A mixin to register, control, and fix modded boats.
 */
@Author(Authors.ANXIETIE)
@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
    @Shadow
    public abstract ActionResult interact(PlayerEntity player, Hand hand);

    public BoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /**
     * Makes all boats slightly slower in lava.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void slowDownBoats(CallbackInfo ci) {
        BoatEntity boat = (BoatEntity)(Object)this;

        if (boat.isInLava()) {
            boat.setVelocity(boat.getVelocity().multiply(SpeedrunnerMod.getBoatInLavaVelocityMultiplier()));
        }
    }

    /**
     * Allows the paddling in lava sound to play when paddling a boat in lava.
     */
    @Inject(method = "getPaddleSoundEvent", at = @At("HEAD"), cancellable = true)
    public void getPaddleSoundEvent(CallbackInfoReturnable<SoundEvent> cir) {
        if (this.isInLava()) {
            cir.setReturnValue(ModSoundEvents.ENTITY_BOAT_PADDLE_LAVA);
        }
    }

    /**
     * Lowers the fall damage when landing on a boat.
     */
    @ModifyArg(method = "fall", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;handleFallDamage(FFLnet/minecraft/entity/damage/DamageSource;)Z"), index = 1)
    private float lowerFallDamage(float par1) {
        if (!options().main.fallDamage) {
            return 0.0F;
        } else {
            return DOOM_MODE ? 1.0F : 0.7F;
        }
    }

    /**
     * Allows the modded boats to float in lava, just like it would in water.
     */
    @Redirect(method = "checkBoatInWater", at = @At(value = "FIELD", target = "Lnet/minecraft/registry/tag/FluidTags;WATER:Lnet/minecraft/registry/tag/TagKey;"))
    private TagKey<Fluid> checkBoatInLava() {
        return ModFluidTags.BOAT_SAFE_FLUIDS;
    }

    /**
     * Fixes a bug where fireproof boats go slightly under lava when landing on it from a high distance.
     */
    @Redirect(method = "getWaterHeightBelow", at = @At(value = "FIELD", target = "Lnet/minecraft/registry/tag/FluidTags;WATER:Lnet/minecraft/registry/tag/TagKey;"))
    private TagKey<Fluid> fireproofBoats() {
        return ModFluidTags.BOAT_SAFE_FLUIDS;
    }
}