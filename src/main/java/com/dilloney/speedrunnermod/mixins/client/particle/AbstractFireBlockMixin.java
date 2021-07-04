package com.dilloney.speedrunnermod.mixins.client.particle;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Environment(EnvType.CLIENT)
@Mixin(AbstractFireBlock.class)
public abstract class AbstractFireBlockMixin {

    @Shadow abstract boolean isFlammable(BlockState state);

    @Overwrite
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(24) == 0) {
            world.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
        }

        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        int x;
        double y;
        double z;
        double aa;
        if (!this.isFlammable(blockState) && !blockState.isSideSolidFullSquare(world, blockPos, Direction.UP)) {
            if (this.isFlammable(world.getBlockState(pos.west()))) {
                for(x = 0; x < 2; ++x) {
                    y = (double)pos.getX() + random.nextDouble() * 0.10000000149011612D;
                    z = (double)pos.getY() + random.nextDouble();
                    aa = (double)pos.getZ() + random.nextDouble();
                    if (SpeedrunnerMod.CONFIG.defaultParticles) {
                        world.addParticle(ParticleTypes.LARGE_SMOKE, y, z, aa, 0.0D, 0.0D, 0.0D);
                    }
                }
            }

            if (this.isFlammable(world.getBlockState(pos.east()))) {
                for(x = 0; x < 2; ++x) {
                    y = (double)(pos.getX() + 1) - random.nextDouble() * 0.10000000149011612D;
                    z = (double)pos.getY() + random.nextDouble();
                    aa = (double)pos.getZ() + random.nextDouble();
                    if (SpeedrunnerMod.CONFIG.defaultParticles) {
                        world.addParticle(ParticleTypes.LARGE_SMOKE, y, z, aa, 0.0D, 0.0D, 0.0D);
                    }
                }
            }

            if (this.isFlammable(world.getBlockState(pos.north()))) {
                for(x = 0; x < 2; ++x) {
                    y = (double)pos.getX() + random.nextDouble();
                    z = (double)pos.getY() + random.nextDouble();
                    aa = (double)pos.getZ() + random.nextDouble() * 0.10000000149011612D;
                    if (SpeedrunnerMod.CONFIG.defaultParticles) {
                        world.addParticle(ParticleTypes.LARGE_SMOKE, y, z, aa, 0.0D, 0.0D, 0.0D);
                    }
                }
            }

            if (this.isFlammable(world.getBlockState(pos.south()))) {
                for(x = 0; x < 2; ++x) {
                    y = (double)pos.getX() + random.nextDouble();
                    z = (double)pos.getY() + random.nextDouble();
                    aa = (double)(pos.getZ() + 1) - random.nextDouble() * 0.10000000149011612D;
                    if (SpeedrunnerMod.CONFIG.defaultParticles) {
                        world.addParticle(ParticleTypes.LARGE_SMOKE, y, z, aa, 0.0D, 0.0D, 0.0D);
                    }
                }
            }

            if (this.isFlammable(world.getBlockState(pos.up()))) {
                for(x = 0; x < 2; ++x) {
                    y = (double)pos.getX() + random.nextDouble();
                    z = (double)(pos.getY() + 1) - random.nextDouble() * 0.10000000149011612D;
                    aa = (double)pos.getZ() + random.nextDouble();
                    if (SpeedrunnerMod.CONFIG.defaultParticles) {
                        world.addParticle(ParticleTypes.LARGE_SMOKE, y, z, aa, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        } else {
            for(x = 0; x < 3; ++x) {
                y = (double)pos.getX() + random.nextDouble();
                z = (double)pos.getY() + random.nextDouble() * 0.5D + 0.5D;
                aa = (double)pos.getZ() + random.nextDouble();
                if (SpeedrunnerMod.CONFIG.defaultParticles) {
                    world.addParticle(ParticleTypes.LARGE_SMOKE, y, z, aa, 0.0D, 0.0D, 0.0D);
                }
            }
        }

    }
}
