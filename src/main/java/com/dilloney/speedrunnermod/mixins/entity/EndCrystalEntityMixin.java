package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EndCrystalEntity.class)
public abstract class EndCrystalEntityMixin extends Entity {

    public EndCrystalEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow abstract void crystalDestroyed(DamageSource source);

    @Overwrite
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (source.getAttacker() instanceof EnderDragonEntity) {
            return false;
        } else {
            if (!this.isRemoved() && !this.world.isClient) {
                this.remove(Entity.RemovalReason.KILLED);
                if (!source.isExplosive()) {
                    if (SpeedrunnerMod.CONFIG.enableChallengeMode) {
                        this.world.createExplosion((Entity)null, this.getX(), this.getY(), this.getZ(), 12.0F, Explosion.DestructionType.DESTROY);
                    } else {
                        this.world.createExplosion((Entity)null, this.getX(), this.getY(), this.getZ(), 6.0F, Explosion.DestructionType.DESTROY);
                    }
                }

                this.crystalDestroyed(source);
            }

            return true;
        }
    }
}