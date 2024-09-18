package net.dillon.speedrunnermod.mixin.main.entity.basic;

import com.terraformersmc.terraform.boat.impl.entity.TerraformBoatEntity;
import com.terraformersmc.terraform.boat.impl.entity.TerraformChestBoatEntity;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.entity.ModBoats;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public World world;
    @Shadow
    public abstract DamageSources getDamageSources();
    @Shadow
    public abstract @Nullable Entity getVehicle();
    @Shadow
    private int fireTicks;

    /**
     * Decreases time set on fire for from lava.
     */
    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(F)V"))
    private float setOnFireFromLavaTime(float x) {
        return SpeedrunnerMod.getFireFromLavaTime();
    }

    /**
     * Decreases damage from lava.
     */
    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float setOnFireFromLavaAmount(float x) {
        return SpeedrunnerMod.getLavaDamageAmount();
    }

    /**
     * Allows players to ride in fireproof boats and chest without burning from the lava.
     */
    @Author(Authors.ANXIETIE)
    @Inject(method = "setOnFireFromLava", at = @At("HEAD"), cancellable = true)
    private void setOnFireFromLava(CallbackInfo ci) {
        Entity vehicle = getVehicle();
        if (options().main.lavaBoats.getCurrentValue()) {
            if (vehicle instanceof TerraformBoatEntity terraformBoat && ModBoats.isFireproofBoat(terraformBoat.getTerraformBoat()) || vehicle instanceof TerraformChestBoatEntity terraformChestBoat && ModBoats.isFireproofBoat(terraformChestBoat.getTerraformBoat())) {
                if (fireTicks > 0 && fireTicks % 20 == 0) {
                    ((Entity)(Object)this).damage(this.getDamageSources().onFire(), 1.0F);
                }
                ci.cancel();
            }
        }
    }
}