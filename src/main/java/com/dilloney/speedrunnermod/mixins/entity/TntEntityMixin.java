package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends Entity {

    public TntEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Overwrite
    private void explode() {
        if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 3.0F, Explosion.DestructionType.BREAK);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2 || SpeedrunnerMod.CONFIG.difficulty == 3) {
            this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 4.0F, Explosion.DestructionType.BREAK);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
            this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 5.0F, Explosion.DestructionType.BREAK);
        } else {
            this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 3.0F, Explosion.DestructionType.BREAK);
        }
    }
}
