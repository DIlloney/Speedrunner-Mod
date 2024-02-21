package net.dillon8775.speedrunnermod.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Environment(EnvType.CLIENT)
@Mixin(BackgroundRenderer.class)
public class Fog {

    /**
     * Applies the {@code Fog} option.
     */
    @Overwrite
    public static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        Entity entity = camera.getFocusedEntity();
        float f;
        if (cameraSubmersionType == CameraSubmersionType.WATER) {
            f = 192.0F;
            if (entity instanceof ClientPlayerEntity) {
                ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
                f *= Math.max(0.25F, clientPlayerEntity.getUnderwaterVisibility());
                Biome biome = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
                if (biome.getCategory() == Biome.Category.SWAMP) {
                    f *= 0.85F;
                }
            }

            RenderSystem.setShaderFogStart(-8.0F);
            RenderSystem.setShaderFogEnd(f * 0.5F);
        } else {
            float clientPlayerEntity;
            final int fog = 2147483647;
            final float lavaVisionDistance = 35.0F;
            if (cameraSubmersionType == CameraSubmersionType.LAVA) {
                if (entity.isSpectator()) {
                    f = -8.0F;
                    clientPlayerEntity = viewDistance * 0.5F;
                } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                    f = 0.0F;
                    clientPlayerEntity = lavaVisionDistance;
                } else {
                    f = 0.25F;
                    clientPlayerEntity = 1.0F;
                }
            } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
                int biome = ((LivingEntity)entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
                float g = MathHelper.lerp(Math.min(1.0F, (float)biome / 20.0F), viewDistance, 5.0F);
                if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                    f = 0.0F;
                    clientPlayerEntity = g * 0.8F;
                } else {
                    f = g * 0.25F;
                    clientPlayerEntity = g;
                }
            } else if (cameraSubmersionType == CameraSubmersionType.POWDER_SNOW) {
                if (entity.isSpectator()) {
                    f = -8.0F;
                    clientPlayerEntity = viewDistance * 0.5F;
                } else {
                    f = 0.0F;
                    clientPlayerEntity = 2.0F;
                }
            } else if (thickFog) {
                f = viewDistance * 0.05F;
                if (!SpeedrunnerModClient.clientOptions().fog) {
                    clientPlayerEntity = fog;
                } else {
                    clientPlayerEntity = Math.min(viewDistance, 192.0F) * 0.5F;
                }
            } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                f = 0.0F;
                clientPlayerEntity = viewDistance;
            } else {
                float biome = MathHelper.clamp(viewDistance / 10.0F, 4.0F, 64.0F);
                f = viewDistance - biome;
                if (!SpeedrunnerModClient.clientOptions().fog) {
                    clientPlayerEntity = fog;
                } else {
                    clientPlayerEntity = viewDistance;
                }
            }

            RenderSystem.setShaderFogStart(f);
            RenderSystem.setShaderFogEnd(clientPlayerEntity);
        }

    }
}