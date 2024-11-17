package net.dillon.speedrunnermod.mixin.main.entity.boat;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.impl.entity.TerraformChestBoatEntity;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.entity.ModBoats;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Modifies terraform chest boats for the {@code speedrunner mod's} liking.
 */
@Mixin(TerraformChestBoatEntity.class)
public abstract class TerraformChestBoatEntityMixin extends ChestBoatEntity {
    @Shadow
    public abstract TerraformBoatType getTerraformBoat();

    public TerraformChestBoatEntityMixin(EntityType<? extends ChestBoatEntity> entityType, World world, Supplier<Item> supplier) {
        super(entityType, world, supplier);
    }

    /**
     * Makes fireproof chest boats fire immune.
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
     * Makes speedrunner chest boats faster.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void makeSpeedrunnerBoatsFaster(CallbackInfo ci) {
        TerraformChestBoatEntity terraformChestBoat = (TerraformChestBoatEntity)(Object)this;

        if (ModBoats.isFastBoat(this.getTerraformBoat())) {
            terraformChestBoat.setVelocity(terraformChestBoat.getVelocity().multiply(SpeedrunnerMod.getSpeedrunnerBoatVelocityMultiplier()));
        }
    }
}