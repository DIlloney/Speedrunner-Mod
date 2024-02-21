package net.dillon8775.speedrunnermod.mixin.client.block;

import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.particle.ModParticleEffects;
import net.dillon8775.speedrunnermod.world.biome.ModBiomes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Environment(EnvType.CLIENT)
@Mixin(Block.class)
public class BlockParticles {

    /**
     * Applies the modded block particles to certain ores/blocks that are in the {@code Speedrunner's Wasteland} biome.
     */
    @Inject(method = "randomDisplayTick", at = @At("TAIL"))
    private void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo callbackInfo) {
        if (SpeedrunnerModClient.clientOptions().blockParticles && world.getBiomeKey(pos).get() == ModBiomes.SPEEDRUNNERS_WASTELAND_KEY) {
            ModParticleEffects.spawnParticles(world, pos);
        }
    }
}