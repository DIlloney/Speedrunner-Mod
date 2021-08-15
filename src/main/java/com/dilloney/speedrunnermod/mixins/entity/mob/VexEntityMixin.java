package com.dilloney.speedrunnermod.mixins.entity.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(VexEntity.class)
public class VexEntityMixin extends HostileEntity {

    protected VexEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow boolean alive;
    @Shadow int lifeTicks;

    @Overwrite
    public static DefaultAttributeContainer.Builder createVexAttributes() {
        return OPTIONS.doomMode ? HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 7.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D) : HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 14.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D);
    }

    @Overwrite
    public void tick() {
        this.noClip = !OPTIONS.doomMode;
        super.tick();
        this.noClip = false;
        this.setNoGravity(true);
        if (this.alive && --this.lifeTicks <= 0) {
            this.lifeTicks = 20;
            if (OPTIONS.doomMode) {
                this.damage(DamageSource.STARVE, 100.0F);
            } else {
                this.damage(DamageSource.STARVE, 1.0F);
            }
        }
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return !OPTIONS.doomMode;
    }
}