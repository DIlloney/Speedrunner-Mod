package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(value = EnderDragonEntity.class, priority = 999)
public abstract class EnderDragonEntityMixin extends MobEntity {
    @Shadow
    private float damageDuringSitting;
    @Shadow
    protected abstract boolean parentDamage(DamageSource source, float amount);
    @Shadow @Final
    public EnderDragonPart head;
    @Shadow @Final
    private PhaseManager phaseManager;

    public EnderDragonEntityMixin(EntityType<? extends EnderDragonEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies the ender dragon's maximum health.
     */
    @ModifyArg(method = "createEnderDragonAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
    private static double genericMaxHealth(double baseValue) {
        return SpeedrunnerMod.getEnderDragonMaxHealth();
    }

    /**
     * Makes the ender dragon heal slower from end crystals.
     */
    @ModifyArg(method = "tickWithEndCrystals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;setHealth(F)V"))
    private float tickCrystals(float value) {
        return this.getHealth() + SpeedrunnerMod.getEnderDragonEndCrystalHealAmount();
    }

    /**
     * Makes the ender dragon do less damage.
     */
    @ModifyArg(method = "damageLivingEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 1)
    private float damageLivingEntities(float amount) {
        return SpeedrunnerMod.getEnderDragonDamageMultiplier();
    }

    /**
     * Makes end crystals do more damage to the ender dragon.
     */
    @ModifyArg(method = "crystalDestroyed", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;damagePart(Lnet/minecraft/entity/boss/dragon/EnderDragonPart;Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 2)
    private float crystalDestroyed(float amount) {
        return SpeedrunnerMod.getEnderDragonEndCrystalDestroyedHealthAmount();
    }

    /**
     * Makes all nearby hostile entities die upon the dragon's death, excluding {@code Enderman.}
     */
    @Inject(method = "updatePostDeath", at = @At("TAIL"))
    public void killAllHostiles(CallbackInfo ci) {
        if (options().advanced.dragonKillsNearbyHostileEntities && this.world instanceof ServerWorld) {
            EnderDragonEntity dragon = (EnderDragonEntity)(Object)this;
            World world = ((Entity)(Object)this).getEntityWorld();

            List<HostileEntity> hostiles = world.getEntitiesByClass(HostileEntity.class,
                    dragon.getBoundingBox().expand(options().advanced.dragonKillsHostileEntitiesDistance[0], options().advanced.dragonKillsHostileEntitiesDistance[1], options().advanced.dragonKillsHostileEntitiesDistance[2]), entity -> true);

            for (HostileEntity hostile : hostiles) {
                if (!(hostile instanceof EndermanEntity)) {
                    hostile.kill();
                }
            }
        }
    }

    /**
     * Stops the dragon from dying if there is a nearby wither and/or giant, only on doom mode.
     */
    @Override
    public void onDeath(DamageSource source) {
        if (DOOM_MODE && options().advanced.dragonImmunityFromGiantAndWither && this.isGiantOrWitherAlive()) {
            this.setHealth(1.0F);
        } else {
            super.onDeath(source);
        }
    }

    /**
     * Allows the ender dragon to stay perched for a longer period of time.
     */
    @Overwrite
    public boolean damagePart(EnderDragonPart part, DamageSource source, float amount) {
        if (this.phaseManager.getCurrent().getType() == PhaseType.DYING) {
            return false;
        } else {
            amount = this.phaseManager.getCurrent().modifyDamageTaken(source, amount);
            if (part != this.head) {
                amount = amount / 4.0F + Math.min(amount, 1.0F);
            }

            if (amount < 0.01F) {
                return false;
            }

            if (DOOM_MODE && this.getHealth() <= 1.0F && options().advanced.dragonImmunityFromGiantAndWither && this.isGiantOrWitherAlive()) {
                return false;
            } else {
                if (source.getAttacker() instanceof PlayerEntity || source.isExplosive()) {
                    float f = this.getHealth();
                    this.parentDamage(source, amount);
                    if (this.isDead() && !this.phaseManager.getCurrent().isSittingOrHovering()) {
                        this.setHealth(0.0F);
                        this.phaseManager.setPhase(PhaseType.DYING);
                    }

                    if (this.phaseManager.getCurrent().isSittingOrHovering()) {
                        this.damageDuringSitting = (int)((float)this.damageDuringSitting + (f - this.getHealth()));
                        if ((float)this.damageDuringSitting > SpeedrunnerMod.getEnderDragonStayPerchedTime() * this.getMaxHealth()) {
                            this.damageDuringSitting = 0;
                            this.phaseManager.setPhase(PhaseType.TAKEOFF);
                        }
                    }
                }

            }
            return true;
        }
    }

    /**
     * Checks if a giant and/or a wither are alive, and if one of them is, the dragon cannot die.
     */
    @Unique
    private boolean isGiantOrWitherAlive() {
        EnderDragonEntity dragon = (EnderDragonEntity)(Object)this;
        List<GiantEntity> giants = world.getEntitiesByClass(GiantEntity.class,
                dragon.getBoundingBox().expand(options().advanced.dragonImmunityDetectionDistanceForGiant[0], options().advanced.dragonImmunityDetectionDistanceForGiant[1], options().advanced.dragonImmunityDetectionDistanceForGiant[2]), entity -> true);
        List<WitherEntity> withers = world.getEntitiesByClass(WitherEntity.class,
                dragon.getBoundingBox().expand(options().advanced.dragonImmunityDetectionDistanceForWither[0], options().advanced.dragonImmunityDetectionDistanceForWither[1], options().advanced.dragonImmunityDetectionDistanceForWither[2]), entity -> true);

        for (GiantEntity giant : giants) {
            if (giant.isAlive()) {
                return true;
            }
        }

        for (WitherEntity wither : withers) {
            if (wither.isAlive()) {
                return true;
            }
        }

        return false;
    }
}