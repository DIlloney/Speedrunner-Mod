package com.dilloney.speedrunnermod.mixins.entity.boss.dragon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.LOGGER;
import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {

    @Shadow @Final ServerWorld world;
    @Shadow @Final @Mutable ServerBossBar bossBar;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void removeFogWhenDoomModeIsON(CallbackInfo callbackInfo) {
        if (OPTIONS.doomMode) {
            this.bossBar = (ServerBossBar)(new ServerBossBar(new TranslatableText("entity.minecraft.ender_dragon"), BossBar.Color.PINK, BossBar.Style.PROGRESS)).setDragonMusic(true);
        }
    }

    @Inject(method = "generateEndPortal", at = @At("TAIL"))
    private void logSpeedrunnerModSettingsUponGeneratingEndPortal(CallbackInfo callbackInfo) {
        if (OPTIONS.normalMode) {
            LOGGER.info("normalMode: ON");
            if (OPTIONS.getModDifficulty() == 1) {
                LOGGER.info("modDifficulty: " + OPTIONS.getModDifficulty());
            } else if (OPTIONS.getModDifficulty() == 2) {
                LOGGER.info("modDifficulty: " + OPTIONS.getModDifficulty());
            } else if (OPTIONS.getModDifficulty() == 3) {
                LOGGER.info("modDifficulty: " + OPTIONS.getModDifficulty());
            } else if (OPTIONS.getModDifficulty() == 4) {
                LOGGER.info("modDifficulty: " + OPTIONS.getModDifficulty());
            } else {
                LOGGER.fatal("modDifficulty: " + OPTIONS.getModDifficulty());
            }

            if (OPTIONS.makeStructuresMoreCommon) {
                LOGGER.info("makeStructuresMoreCommon: ON");
            } else {
                LOGGER.fatal("makeStructuresMoreCommon: OFF");
            }

            if (OPTIONS.modifiedWorldGeneration) {
                LOGGER.info("modifiedWorldGeneration: ON");
            } else {
                LOGGER.fatal("modifiedWorldGeneration: OFF");
            }

            if (OPTIONS.modifiedBlockHardness) {
                LOGGER.info("modifiedBlockHardness: ON");
            } else {
                LOGGER.fatal("modifiedBlockHardness: OFF");
            }

            if (OPTIONS.killGhastUponFireball) {
                LOGGER.fatal("killGhastUponFireball: ON");
            } else {
                LOGGER.info("killGhastUponFireball: OFF");
            }

            if (OPTIONS.doomMode) {
                LOGGER.info("doomMode: ON");
            } else {
                LOGGER.info("doomMode: OFF");
            }

            if (OPTIONS.iCarusMode) {
                LOGGER.info("icarusMode: ON");
            } else {
                LOGGER.info("icarusMode: OFF");
            }

            if (OPTIONS.infiniPearlMode) {
                LOGGER.info("infiniPearlMode: ON");
            } else {
                LOGGER.info("infinitPearlMode: OFF");
            }

            if (OPTIONS.manhuntMode) {
                LOGGER.fatal("manhuntMode: ON");
            } else {
                LOGGER.info("manhuntMode: OFF");
            }

            if (OPTIONS.fog) {
                LOGGER.info("fog: ON");
            } else {
                LOGGER.info("fog: OFF");
            }

            if (OPTIONS.customTextures) {
                LOGGER.info("customTextures: ON");
            } else {
                LOGGER.info("customTextures: OFF");
            }

            if (OPTIONS.advancedModTooltips) {
                LOGGER.info("advancedModTooltips: ON");
            } else {
                LOGGER.info("advancedModTooltips: OFF");
            }

            if (OPTIONS.netherRuinedPortals) {
                LOGGER.fatal("netherRuinedPortals: ON");
            } else {
                LOGGER.info("netherRuinedPortals: OFF");
            }

            if (OPTIONS.netherPortalsInTheEnd) {
                LOGGER.fatal("netherPortalsInTheEnd: ON");
            } else {
                LOGGER.info("netherPortalsInTheEnd: OFF");
            }
        }
    }

    @Inject(method = "createDragon", at = @At("TAIL"))
    private void spawnGiantWhenDoomModeIsON(CallbackInfoReturnable callbackInfoReturnable) {
        if (OPTIONS.doomMode) {
            GiantEntity giantEntity = (GiantEntity)EntityType.GIANT.create(this.world);
            giantEntity.refreshPositionAndAngles(0.0D, 96.0D, -0.0D, this.world.random.nextFloat() * 270.0F, 0.0F);
            this.world.spawnEntity(giantEntity);
        }
    }
}
