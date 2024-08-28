package net.dillon.speedrunnermod.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
@Mixin(BackgroundRenderer.class)
public class Fog {

    /**
     * Applies the {@code Fog} option to the game.
     */
    @Overwrite
    public static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta) {
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        Entity entity = camera.getFocusedEntity();
        FogShape fogShape = FogShape.SPHERE;
        float f;
        float g;
        final int fog = 2147483647; // Maximum integer value removes fog from the game entirely
        final float lavaVisionDistance = 35.0F; // The distance that the player can see when submergged in lava
        if (cameraSubmersionType == CameraSubmersionType.LAVA) {
            if (entity.isSpectator()) {
                f = -8.0F;
                g = viewDistance * 0.5F;
            } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                f = 0.0F;
                g = lavaVisionDistance;
            } else {
                f = 0.25F;
                g = 1.0F;
            }
        } else if (cameraSubmersionType == CameraSubmersionType.POWDER_SNOW) {
            if (entity.isSpectator()) {
                f = -8.0F;
                g = viewDistance * 0.5F;
            } else {
                f = 0.0F;
                g = 2.0F;
            }
        } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
            int i = ((LivingEntity)entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
            float h = MathHelper.lerp(Math.min(1.0F, (float)i / 20.0F), viewDistance, 5.0F);
            if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                f = 0.0F;
                g = h * 0.8F;
            } else {
                f = cameraSubmersionType == CameraSubmersionType.WATER ? -4.0F : h * 0.25F;
                g = h;
            }
        } else if (cameraSubmersionType == CameraSubmersionType.WATER) {
            f = -8.0F;
            g = 96.0F;
            if (entity instanceof ClientPlayerEntity) {
                ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
                g *= Math.max(0.25F, clientPlayerEntity.getUnderwaterVisibility());
                RegistryEntry<Biome> registryEntry = clientPlayerEntity.getWorld().getBiome(clientPlayerEntity.getBlockPos());
                if (registryEntry.isIn(BiomeTags.HAS_CLOSER_WATER_FOG)) {
                    g *= 0.85F;
                }
            }

            if (g > viewDistance) {
                g = viewDistance;
                fogShape = FogShape.CYLINDER;
            }
        } else if (thickFog) {
            f = viewDistance * 0.05F;
            if (!options().client.fog) {
                g = fog;
            } else {
                g = Math.min(viewDistance, 192.0F) * 0.5F;
            }
        } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
            f = 0.0F;
            g = viewDistance;
            fogShape = FogShape.CYLINDER;
        } else {
            float j = MathHelper.clamp(viewDistance / 10.0F, 4.0F, 64.0F);
            f = viewDistance - j;
            if (!options().client.fog) {
                g = fog;
            } else {
                g = viewDistance;
            }
            fogShape = FogShape.CYLINDER;
        }

        RenderSystem.setShaderFogStart(f);
        RenderSystem.setShaderFogEnd(g);
        RenderSystem.setShaderFogShape(fogShape);
    }
}