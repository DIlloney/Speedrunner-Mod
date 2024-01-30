package net.dillon8775.speedrunnermod.mixin.main.boat;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.entity.ModEntityTypes;
import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.sound.ModSoundEvents;
import net.dillon8775.speedrunnermod.tag.ModFluidTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * A mixin to register, control, and fix modded boats.
 */
@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
    @Shadow
    private double waterLevel;
    @Shadow
    private double fallVelocity;
    @Shadow
    abstract BoatEntity.Type getBoatType();
    @Shadow
    public abstract ActionResult interact(PlayerEntity player, Hand hand);

    public BoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /**
     * Fixes the "fire immune" attribute on modded boats.
     */
    @Override
    public boolean isFireImmune() {
        if (SpeedrunnerMod.options().advanced.allowBoatsInLava) {
            return ModEntityTypes.isFireproofBoat(this.getBoatType()) || super.isFireImmune();
        } else {
            return super.isFireImmune();
        }
    }

    /**
     * Allows the paddling in lava sound to play when paddling a boat in lava.
     */
    @Inject(method = "getPaddleSoundEvent", at = @At("RETURN"), cancellable = true)
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
     * Allows the modded boats to float in lava, just like it would in water.
     */
    @Overwrite
    private boolean checkBoatInWater() {
        Box box = this.getBoundingBox();
        int i = MathHelper.floor(box.minX);
        int j = MathHelper.ceil(box.maxX);
        int k = MathHelper.floor(box.minY);
        int l = MathHelper.ceil(box.minY + 0.001);
        int m = MathHelper.floor(box.minZ);
        int n = MathHelper.ceil(box.maxZ);
        boolean flag = false;
        this.waterLevel = -1.7976931348623157E308;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int o = i; o < j; ++o) {
            for (int p = k; p < l; ++p) {
                for (int q = m; q < n; ++q) {
                    mutable.set(o, p, q);
                    FluidState fluidState = this.world.getFluidState(mutable);
                    if (!fluidState.isIn(ModFluidTags.BOAT_SAFE_FLUIDS)) continue;
                    float f = (float)p + fluidState.getHeight(this.world, mutable);
                    this.waterLevel = Math.max((double)f, this.waterLevel);
                    flag |= box.minY < (double)f;
                }
            }
        }
        return flag;
    }

    /**
     * Fixes a bug where fireproof boats go slightly under lava when landing on it from a high distance.
     */
    @Overwrite
    public float method_7544() {
        Box box = this.getBoundingBox();
        int i = MathHelper.floor(box.minX);
        int j = MathHelper.ceil(box.maxX);
        int k = MathHelper.floor(box.maxY);
        int l = MathHelper.ceil(box.maxY - this.fallVelocity);
        int m = MathHelper.floor(box.minZ);
        int n = MathHelper.ceil(box.maxZ);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        block0: for (int o = k; o < l; ++o) {
            float f = 0.0f;
            for (int p = i; p < j; ++p) {
                for (int q = m; q < n; ++q) {
                    mutable.set(p, o, q);
                    FluidState fluidState = this.world.getFluidState(mutable);
                    if (fluidState.isIn(ModFluidTags.BOAT_SAFE_FLUIDS)) {
                        f = Math.max(f, fluidState.getHeight(this.world, mutable));
                    }
                    if (f >= 1.0f) continue block0;
                }
            }
            if (!(f < 1.0f)) continue;
            return (float)mutable.getY() + f;
        }
        return l + 1;
    }
}