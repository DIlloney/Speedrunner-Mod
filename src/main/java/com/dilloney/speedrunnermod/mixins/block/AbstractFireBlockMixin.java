package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(AbstractFireBlock.class)
public abstract class AbstractFireBlockMixin extends Block {

    public AbstractFireBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow @Final float damage;

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

    @Overwrite
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!entity.isFireImmune()) {
            entity.setFireTicks(entity.getFireTicks() + 1);
            if (entity.getFireTicks() == 0 && SpeedrunnerMod.CONFIG.difficulty == 1 || entity.getFireTicks() == 0 && SpeedrunnerMod.CONFIG.difficulty == 2) {
                entity.setOnFireFor(4);
            } else if (entity.getFireTicks() == 0 && SpeedrunnerMod.CONFIG.difficulty == 3) {
                entity.setOnFireFor(6);
            } else if (entity.getFireTicks() == 0 && SpeedrunnerMod.CONFIG.difficulty == 4) {
                entity.setOnFireFor(8);
            } else {
                entity.setOnFireFor(4);
            }

            entity.damage(DamageSource.IN_FIRE, this.damage);
        }

        super.onEntityCollision(state, world, pos, entity);
    }

    @Overwrite
    private static boolean shouldLightPortalAt(World world, BlockPos pos, Direction direction) {
        if (!isOverworldOrNether(world)) {
            return false;
        } else {
            BlockPos.Mutable mutable = pos.mutableCopy();
            boolean bl = false;
            Direction[] var5 = Direction.values();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Direction direction2 = var5[var7];
                if (world.getBlockState(mutable.set(pos).move(direction2)).isOf(Blocks.OBSIDIAN) || world.getBlockState(mutable.set(pos).move(direction2)).isOf(Blocks.CRYING_OBSIDIAN)) {
                    bl = true;
                    break;
                }
            }

            if (!bl) {
                return false;
            } else {
                Direction.Axis axis = direction.getAxis().isHorizontal() ? direction.rotateYCounterclockwise().getAxis() : Direction.Type.HORIZONTAL.randomAxis(world.random);
                return AreaHelper.getNewPortal(world, pos, axis).isPresent();
            }
        }
    }

    private static boolean isOverworldOrNether(World world) {
        return world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER;
    }
}
