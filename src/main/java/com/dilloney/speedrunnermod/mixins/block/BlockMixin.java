package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Block.class)
public class BlockMixin {

    @Overwrite
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (SpeedrunnerMod.CONFIG.enableChallengeMode) {
            entity.handleFallDamage(fallDistance + 0.5F, 1.0F, DamageSource.FALL);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            entity.handleFallDamage(fallDistance, 0.6F, DamageSource.FALL);
        } else {
            entity.handleFallDamage(fallDistance, 1.0F, DamageSource.FALL);
        }
    }
}