package com.dilloney.speedrunnermod.mixins.entity.boss.dragon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.WitherEntity;
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

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;
import static com.dilloney.speedrunnermod.SpeedrunnerMod.LOGGER;

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
        if (OPTIONS.officialSpeedrunMode) {
            LOGGER.info("officialSpeedrunMode: ON");
            if (OPTIONS.getModDifficulty() == 1) {
                LOGGER.info("modDifficulty: " + OPTIONS.getModDifficulty());
            } else if (OPTIONS.getModDifficulty() == 2) {
                LOGGER.info("modDifficulty: " + OPTIONS.getModDifficulty());
            } else if (OPTIONS.getModDifficulty() == 3) {
                LOGGER.info("modDifficulty: " + OPTIONS.getModDifficulty());
            } else if (OPTIONS.getModDifficulty() == 4) {
                LOGGER.info("modDifficulty: " + OPTIONS.getModDifficulty());
            } else {
                LOGGER.fatal("modDifficulty: " + OPTIONS.getModDifficulty() + " - (not verifiable)");
            }

            if (OPTIONS.makeStructuresMoreCommon) {
                LOGGER.info("makeStructuresMoreCommon: ON");
            } else {
                LOGGER.fatal("makeStructuresMoreCommon: OFF - (not verifiable)");
            }

            if (OPTIONS.modifiedWorldGeneration) {
                LOGGER.info("modifiedWorldGeneration: ON");
            } else {
                LOGGER.fatal("modifiedWorldGeneration: OFF - (not verifiable)");
            }

            if (OPTIONS.modifiedBlockHardness) {
                LOGGER.info("modifiedBlockHardness: ON");
            } else {
                LOGGER.fatal("modifiedBlockHardness: OFF - (not verifiable)");
            }

            if (OPTIONS.killGhastUponFireball) {
                LOGGER.fatal("killGhastUponFireball: ON - (not verifiable)");
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
                LOGGER.fatal("manhuntMode: ON - (not verifiable)");
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

            if (OPTIONS.hardnessTooltips) {
                LOGGER.info("hardnessTooltips: ON");
            } else {
                LOGGER.info("hardnessTooltips: OFF");
            }

            if (OPTIONS.getMaximumFortressBlazeSpawners() == 3) {
                LOGGER.info("maximumFortressBlazeSpawners: " + OPTIONS.getMaximumFortressBlazeSpawners());
            } else {
                LOGGER.fatal("maximumFortressBlazeSpawners: " + OPTIONS.getMaximumFortressBlazeSpawners() + " - (not verifiable)");
            }

            if (OPTIONS.getStrongholdCount() == 128) {
                LOGGER.info("strongholdCount: " + OPTIONS.getStrongholdCount());
            } else {
                LOGGER.fatal("strongholdCount: " + OPTIONS.getStrongholdCount() + " - (not verifiable)");
            }

            if (OPTIONS.getStrongholdDistance() == 5) {
                LOGGER.info("strongholdCount: " + OPTIONS.getStrongholdDistance());
            } else {
                LOGGER.fatal("strongholdCount: " + OPTIONS.getStrongholdDistance() + " - (not verifiable)");
            }

            if (OPTIONS.getMaximumStrongholdPortalRooms() == 3) {
                LOGGER.info("maximumStrongholdPortalRooms: " + OPTIONS.getMaximumStrongholdPortalRooms());
            } else {
                LOGGER.fatal("maximumStrongholdPortalRooms: " + OPTIONS.getMaximumStrongholdPortalRooms() + " - (not verifiable)");
            }

            if (OPTIONS.netherRuinedPortals) {
                LOGGER.fatal("netherRuinedPortals: ON - (not verifiable)");
            } else {
                LOGGER.info("netherRuinedPortals: OFF");
            }

            if (OPTIONS.netherPortalsInTheEnd) {
                LOGGER.fatal("netherPortalsInTheEnd: ON - (not verifiable)");
            } else {
                LOGGER.info("netherPortalsInTheEnd: OFF");
            }

            if (OPTIONS.getRuinedPortalSpacing() == 9) {
                LOGGER.info("ruinedPortalSpacing: " + OPTIONS.getRuinedPortalSpacing());
            } else {
                LOGGER.fatal("ruinedPortalSpacing " + OPTIONS.getRuinedPortalSpacing() + " - (not verifiable)");
            }

            if (OPTIONS.getRuinedPortalSeparation() == 8) {
                LOGGER.info("ruinedPortalSeparation: " + OPTIONS.getRuinedPortalSeparation());
            } else {
                LOGGER.fatal("ruinedPortalSeparation: " + OPTIONS.getRuinedPortalSeparation() + " - (not verifiable)");
            }

            if (OPTIONS.getVillageSpacing() == 16) {
                LOGGER.info("villageSpacing: " + OPTIONS.getVillageSpacing());
            } else {
                LOGGER.fatal("villageSpacing: " + OPTIONS.getVillageSpacing() + " - (not verifiable)");
            }

            if (OPTIONS.getVillageSeparation() == 9) {
                LOGGER.info("villageSeparation: " + OPTIONS.getVillageSeparation());
            } else {
                LOGGER.fatal("villageSeparation: " + OPTIONS.getVillageSeparation() + " - (not verifiable)");
            }

            if (OPTIONS.getDesertPyramidSpacing() == 10) {
                LOGGER.info("desertPyramidSpacing: " + OPTIONS.getDesertPyramidSpacing());
            } else {
                LOGGER.fatal("desertPyramidSpacing: " + OPTIONS.getDesertPyramidSpacing() + " - (not verifiable)");
            }

            if (OPTIONS.getDesertPyramidSeparation() == 8) {
                LOGGER.info("desertPyramidSeparation: " + OPTIONS.getDesertPyramidSeparation());
            } else {
                LOGGER.fatal("desertPyramidSeparation: " + OPTIONS.getDesertPyramidSeparation() + " - (not verifiable)");
            }

            if (OPTIONS.getShipwreckSpacing() == 10) {
                LOGGER.info("shipwreckSpacing: " + OPTIONS.getShipwreckSpacing());
            } else {
                LOGGER.fatal("shipwreckSpacing: " + OPTIONS.getShipwreckSpacing() + " - (not verifiable)");
            }

            if (OPTIONS.getShipwreckSeparation() == 8) {
                LOGGER.info("shipwreckSeparation: " + OPTIONS.getShipwreckSeparation());
            } else {
                LOGGER.fatal("shipwreckSeparation: " + OPTIONS.getShipwreckSeparation() + " - (not verifiable)");
            }

            if (OPTIONS.getFortressSpacing() == 8) {
                LOGGER.info("fortressSpacing: " + OPTIONS.getFortressSpacing());
            } else {
                LOGGER.fatal("fortressSpacing: " + OPTIONS.getFortressSpacing() + " - (not verifiable)");
            }

            if (OPTIONS.getFortressSeparation() == 7) {
                LOGGER.info("fortressSeparation: " + OPTIONS.getFortressSeparation());
            } else {
                LOGGER.fatal("fortressSeparation: " + OPTIONS.getFortressSeparation() + " - (not verifiable)");
            }

            if (OPTIONS.getBastionSpacing() == 9) {
                LOGGER.info("bastionSpacing: " + OPTIONS.getBastionSpacing());
            } else {
                LOGGER.fatal("bastionSpacing: " + OPTIONS.getBastionSpacing() + " - (not verifiable)");
            }

            if (OPTIONS.getBastionSeparation() == 8) {
                LOGGER.info("bastionSeparation: " + OPTIONS.getBastionSeparation());
            } else {
                LOGGER.fatal("bastionSeparation: " + OPTIONS.getBastionSeparation() + " - (not verifiable)");
            }

            if (OPTIONS.getEndCitySpacing() == 7) {
                LOGGER.info("endCitySpacing: " + OPTIONS.getEndCitySpacing());
            } else {
                LOGGER.fatal("endCitySpacing: " + OPTIONS.getEndCitySpacing() + " - (not verifiable)");
            }

            if (OPTIONS.getEndCitySeparation() == 6) {
                LOGGER.info("endCitySeparation: " + OPTIONS.getEndCitySeparation());
            } else {
                LOGGER.fatal("endCitySeparation: " + OPTIONS.getEndCitySeparation() + " - (not verifiable)");
            }
        }
    }

    @Inject(method = "createDragon", at = @At("TAIL"))
    private void spawnGiantWhenDoomModeIsON(CallbackInfoReturnable callbackInfoReturnable) {
        if (OPTIONS.doomMode) {
            WitherEntity witherEntity = (WitherEntity)EntityType.WITHER.create(this.world);
            GiantEntity giantEntity = (GiantEntity)EntityType.GIANT.create(this.world);
            witherEntity.refreshPositionAndAngles(0.0D, 96.0D, 0.0D, this.world.random.nextFloat() * 270.0F, 0.0F);
            giantEntity.refreshPositionAndAngles(0.0D, 192.0D, -0.0D, this.world.random.nextFloat() * 270.0F, 0.0F);
            this.world.spawnEntity(witherEntity);
            this.world.spawnEntity(giantEntity);
        }
    }
}
