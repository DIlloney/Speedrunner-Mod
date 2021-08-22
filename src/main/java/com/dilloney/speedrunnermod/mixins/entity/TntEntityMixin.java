package com.dilloney.speedrunnermod.mixins.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends Entity {

    @Deprecated
    protected TntEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public boolean canExplosionDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float explosionPower) {
        return !(this.world instanceof ServerWorld) || this.world.getRegistryKey() != World.END || !OPTIONS.doomMode;
    }
}