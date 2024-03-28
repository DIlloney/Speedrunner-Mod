package net.dillon8775.speedrunnermod.mixin.main.boat;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.entity.ModEntityTypes;
import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.sound.ModSoundEvents;
import net.dillon8775.speedrunnermod.tag.ModFluidTags;
import net.dillon8775.speedrunnermod.util.Author;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.TagKey;
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

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;
import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * A mixin to register, control, and fix modded boats.
 */
@Author("Anxietie")
@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
    @Shadow
    public abstract BoatEntity.Type getBoatType();
    @Shadow
    public abstract ActionResult interact(PlayerEntity player, Hand hand);

    public BoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /**
     * Makes speedrunner boats  faster, and makes boats slightly slower in lava.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void makeSpeedrunnerBoatsFaster(CallbackInfo ci) {
        BoatEntity boat = (BoatEntity)(Object)this;

        if (boat.isInLava()) {
            boat.setVelocity(boat.getVelocity().multiply(SpeedrunnerMod.getBoatInLavaVelocityMultiplier()));
        }

        if (ModEntityTypes.isFastBoat(boat.getBoatType())) {
            boat.setVelocity(boat.getVelocity().multiply(SpeedrunnerMod.getSpeedrunnerBoatVelocityMultiplier()));
        }
    }

    /**
     * Fixes the "fire immune" attribute on modded boats.
     */
    @Override
    public boolean isFireImmune() {
        if (options().main.lavaBoats) {
            return ModEntityTypes.isFireproofBoat(this.getBoatType()) || super.isFireImmune();
        } else {
            return super.isFireImmune();
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
     * Makes the correct item drop for modded boats.
     */
    @Inject(method = "asItem", at = @At("RETURN"), cancellable = true)
    public void dropItem(CallbackInfoReturnable<Item> cir) {
        if (this.getBoatType().equals(ModEntityTypes.SPEEDRUNNER_BOAT)) {
            cir.setReturnValue(ModItems.SPEEDRUNNER_BOAT);
        } else if (this.getBoatType().equals(ModEntityTypes.CRIMSON_BOAT)) {
            cir.setReturnValue(ModItems.CRIMSON_BOAT);
        } else if (this.getBoatType().equals(ModEntityTypes.WARPED_BOAT)) {
            cir.setReturnValue(ModItems.WARPED_BOAT);
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
    @Redirect(method = "checkBoatInWater", at = @At(value = "FIELD", target = "Lnet/minecraft/tag/FluidTags;WATER:Lnet/minecraft/tag/TagKey;"))
    private TagKey<Fluid> checkBoatInLava() {
        return ModFluidTags.BOAT_SAFE_FLUIDS;
    }

    /**
     * Fixes a bug where fireproof boats go slightly under lava when landing on it from a high distance.
     */
    @Redirect(method = "method_7544", at = @At(value = "FIELD", target = "Lnet/minecraft/tag/FluidTags;WATER:Lnet/minecraft/tag/TagKey;"))
    private TagKey<Fluid> fireproofBoats() {
        return ModFluidTags.BOAT_SAFE_FLUIDS;
    }
}