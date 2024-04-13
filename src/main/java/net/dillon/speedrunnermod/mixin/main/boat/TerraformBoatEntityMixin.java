package net.dillon.speedrunnermod.mixin.main.boat;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.impl.entity.TerraformBoatEntity;
import com.terraformersmc.terraform.boat.impl.entity.TerraformChestBoatEntity;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.entity.ModBoats;
import net.dillon.speedrunnermod.util.Author;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Modifies terraform boats for the {@code speedrunner mod's} liking.
 */
@Mixin(TerraformBoatEntity.class)
public abstract class TerraformBoatEntityMixin extends BoatEntity {
    @Shadow
    public abstract TerraformBoatType getTerraformBoat();

    public TerraformBoatEntityMixin(EntityType<? extends BoatEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Makes fireproof boats fire immune.
     */
    @Override
    public boolean isFireImmune() {
        if (options().main.lavaBoats) {
            return ModBoats.isFireproofBoat(this.getTerraformBoat()) || super.isFireImmune();
        } else {
            return super.isFireImmune();
        }
    }

    /**
     * Allows players to ride in fireproof boats without burning from the lava.
     */
    @Author("Anxietie")
    @Override
    public void setOnFireFromLava() {
        Entity vehicle = getVehicle();
        if (options().main.lavaBoats) {
            if (vehicle instanceof TerraformBoatEntity terraformBoat && ModBoats.isFireproofBoat(terraformBoat.getTerraformBoat()) || vehicle instanceof TerraformChestBoatEntity terraformChestBoat && ModBoats.isFireproofBoat(terraformChestBoat.getTerraformBoat())) {
                if (getFireTicks() > 0 && getFireTicks() % 20 == 0) {
                    ((Entity)this).damage(this.getDamageSources().onFire(), 1.0F);
                }
            } else {
                super.setOnFireFromLava();
            }
        } else {
            super.setOnFireFromLava();
        }
    }

    /**
     * Makes speedrunner boats faster.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void makeSpeedrunnerBoatsFaster(CallbackInfo ci) {
        TerraformBoatEntity terraformBoat = (TerraformBoatEntity)(Object)this;

        if (ModBoats.isFastBoat(this.getTerraformBoat())) {
            terraformBoat.setVelocity(terraformBoat.getVelocity().multiply(SpeedrunnerMod.getSpeedrunnerBoatVelocityMultiplier()));
        }
    }
}