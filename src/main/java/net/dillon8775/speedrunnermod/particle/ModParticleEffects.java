package net.dillon8775.speedrunnermod.particle;

import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

import java.util.Random;

@Environment(EnvType.CLIENT)
public class ModParticleEffects {
    private static final Vec3f DIAMOND_BLUE = new Vec3f(Vec3d.unpackRgb(0xD5FFF6));
    private static final Vec3f SPEEDRUNNER_BLUE = new Vec3f(Vec3d.unpackRgb(0x71C8C5));
    private static final Vec3f GOLD_YELLOW = new Vec3f(Vec3d.unpackRgb(0xFCEE4B));
    private static final Vec3f LAPIS_BLUE = new Vec3f(Vec3d.unpackRgb(0x1855BD));
    private static final Vec3f EMERALD_GREEN = new Vec3f(Vec3d.unpackRgb(0x41F384));
    public static final DustParticleEffect DIAMOND = new DustParticleEffect(DIAMOND_BLUE, 1.0f);
    public static final DustParticleEffect SPEEDRUNNER = new DustParticleEffect(SPEEDRUNNER_BLUE, 1.0f);
    public static final DustParticleEffect GOLD = new DustParticleEffect(GOLD_YELLOW, 1.0f);
    public static final DustParticleEffect LAPIS = new DustParticleEffect(LAPIS_BLUE, 1.0f);
    public static final DustParticleEffect EMERALD = new DustParticleEffect(EMERALD_GREEN, 1.0f);
    public static final DustParticleEffect NONE = new DustParticleEffect(SPEEDRUNNER_BLUE, 0f);

    public static void spawnParticles(World world, BlockPos pos) {
        Random random = world.random;
        for (Direction direction : Direction.values()) {
            BlockPos blockPos = pos.offset(direction);
            if (world.getBlockState(blockPos).isOpaqueFullCube(world, blockPos)) continue;
            Direction.Axis axis = direction.getAxis();
            double e = axis == Direction.Axis.X ? 0.5 + 0.5625 * (double)direction.getOffsetX() : (double)random.nextFloat();
            double f = axis == Direction.Axis.Y ? 0.5 + 0.5625 * (double)direction.getOffsetY() : (double)random.nextFloat();
            double g = axis == Direction.Axis.Z ? 0.5 + 0.5625 * (double)direction.getOffsetZ() : (double)random.nextFloat();
            DustParticleEffect particleEffect =
                  world.getBlockState(pos).isOf(ModBlocks.SPEEDRUNNER_ORE) || world.getBlockState(pos).isOf(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE) || world.getBlockState(pos).isOf(ModBlocks.SPEEDRUNNER_BLOCK) || world.getBlockState(pos).isOf(ModBlocks.RAW_SPEEDRUNNER_BLOCK) ? SPEEDRUNNER
                : world.getBlockState(pos).isOf(Blocks.DIAMOND_ORE) || world.getBlockState(pos).isOf(Blocks.DEEPSLATE_DIAMOND_ORE) ? DIAMOND
                : world.getBlockState(pos).isOf(Blocks.GOLD_ORE) || world.getBlockState(pos).isOf(Blocks.DEEPSLATE_GOLD_ORE) ? GOLD
                : world.getBlockState(pos).isOf(Blocks.LAPIS_ORE) || world.getBlockState(pos).isOf(Blocks.DEEPSLATE_LAPIS_ORE) ? LAPIS
                : world.getBlockState(pos).isOf(Blocks.EMERALD_ORE) || world.getBlockState(pos).isOf(Blocks.DEEPSLATE_EMERALD_ORE) ? EMERALD : NONE;
            world.addParticle(particleEffect, (double)pos.getX() + e, (double)pos.getY() + f, (double)pos.getZ() + g, 0.0, 0.0, 0.0);
        }
    }
}