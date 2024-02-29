package net.dillon8775.speedrunnermod.mixin.main.block;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.particle.ModParticleEffects;
import net.dillon8775.speedrunnermod.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(Block.class)
public class BlockMixin {

    /**
     * <p>Lowers fall damage. This applies to all entities.</p>
     * <p>If the entity is {@code sneaking}, then the damage can be reduced by {@code ~1.25F}.</p>
     */
    @Overwrite
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        float fallDamage;
        if (!SpeedrunnerMod.options().fallDamage) {
            fallDamage = 0.0F;
        } else {
            fallDamage = DOOM_MODE ? 1.0F : 0.7F;
            if (entity.isSneaking()) {
                fallDamage = fallDamage / 1.25F;
            }
        }
        entity.handleFallDamage(fallDistance, fallDamage, DamageSource.FALL);
    }

    /**
     * Applies the modded block particles to certain ores/blocks that are in the {@code Speedrunner's Wasteland} biome.
     */
    @Inject(method = "randomDisplayTick", at = @At("TAIL"))
    private void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo callbackInfo) {
        if (SpeedrunnerModClient.clientOptions().blockParticles && world.getBiome(pos) == ModBiomes.SPEEDRUNNERS_WASTELAND_KEY) {
            ModParticleEffects.spawnParticles(world, pos);
        }
    }
}