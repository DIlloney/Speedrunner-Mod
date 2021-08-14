package com.dilloney.speedrunnermod.mixins.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(Block.class)
public class BlockMixin {

    @Overwrite
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (OPTIONS.doomMode) {
            entity.handleFallDamage(fallDistance, 1.0F, DamageSource.FALL);
        } else if (OPTIONS.getModDifficulty() == 1) {
            entity.handleFallDamage(fallDistance, 0.7F, DamageSource.FALL);
        } else {
            entity.handleFallDamage(fallDistance, 1.0F, DamageSource.FALL);
        }
    }
}