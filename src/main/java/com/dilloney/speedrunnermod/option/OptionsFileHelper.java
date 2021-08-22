package com.dilloney.speedrunnermod.option;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.LOGGER;
import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

public class OptionsFileHelper {

    private static void saveAndReloadOptions() {
        OptionsFileManager.save();
        OptionsFileManager.load();
    }

    public static void fix() {
        if (OPTIONS.getModDifficulty() < 1 || OPTIONS.getModDifficulty() > 4) {
            OPTIONS.setModDifficulty(1);
            LOGGER.warn("modDifficulty cannot be set below 1 or above 4!");
            LOGGER.info("modDifficulty has been set to " + OPTIONS.getModDifficulty());
            saveAndReloadOptions();
        }

        if (OPTIONS.getStrongholdCount() < 64 || OPTIONS.getStrongholdCount() > 256) {
            OPTIONS.setStrongholdCount(128);
            LOGGER.warn("strongholdCount cannot be set below 64 or above 256!");
            LOGGER.info("strongholdCount has been set to " + OPTIONS.getStrongholdCount());
            saveAndReloadOptions();
        }

        if (OPTIONS.makeStructuresMoreCommon) {
            if (OPTIONS.getStrongholdDistance() < 3 || OPTIONS.getStrongholdDistance() > 36) {
                OPTIONS.setStrongholdDistance(5);
                LOGGER.warn("strongholdDistance cannot be set below 3 or above 36!");
                LOGGER.info("strongholdDistance has been set to " + OPTIONS.getStrongholdDistance());
                saveAndReloadOptions();
            }

            if (OPTIONS.getRuinedPortalSeparation() > OPTIONS.getRuinedPortalSpacing()) {
                OPTIONS.setRuinedPortalSeparation(OPTIONS.getRuinedPortalSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("ruinedPortalSeparation has been set to " + OPTIONS.getRuinedPortalSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getVillageSeparation() > OPTIONS.getVillageSpacing()) {
                OPTIONS.setVillageSeparation(OPTIONS.getVillageSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("villageSeparation has been set to " + OPTIONS.getVillageSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getDesertPyramidSeparation() > OPTIONS.getDesertPyramidSpacing()) {
                OPTIONS.setDesertPyramidSeparation(OPTIONS.getDesertPyramidSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("desertPyramidSeparation has been set to " + OPTIONS.getDesertPyramidSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getIglooSeparation() > OPTIONS.getIglooSpacing()) {
                OPTIONS.setIglooSeparation(OPTIONS.getIglooSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("iglooSeparation has been set to " + OPTIONS.getIglooSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getJunglePyramidSeparation() > OPTIONS.getJunglePyramidSpacing()) {
                OPTIONS.setJunglePyramidSeparation(OPTIONS.getJunglePyramidSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("junglePyramidSeparation has been set to " + OPTIONS.getJunglePyramidSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getSwampHutSeparation() > OPTIONS.getSwampHutSpacing()) {
                OPTIONS.setSwampHutSeparation(OPTIONS.getSwampHutSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("swampHutSeparation has been set to " + OPTIONS.getSwampHutSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getPillagerOutpostSeparation() > OPTIONS.getPillagerOutpostSpacing()) {
                OPTIONS.setPillagerOutpostSeparation(OPTIONS.getPillagerOutpostSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("pillagerOutpostSeparation has been set to " + OPTIONS.getPillagerOutpostSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getMonumentSeparation() > OPTIONS.getMonumentSpacing()) {
                OPTIONS.setMonumentSeparation(OPTIONS.getMonumentSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("monumentSeparation has been set to " + OPTIONS.getMonumentSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getShipwreckSeparation() > OPTIONS.getShipwreckSpacing()) {
                OPTIONS.setShipwreckSeparation(OPTIONS.getShipwreckSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("shipwreckSeparation has been set to " + OPTIONS.getShipwreckSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getOceanRuinSeparation() > OPTIONS.getOceanRuinSpacing()) {
                OPTIONS.setOceanRuinSeparation(OPTIONS.getOceanRuinSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("oceanRuinSeparation has been set to " + OPTIONS.getOceanRuinSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getFortressSeparation() > OPTIONS.getFortressSpacing()) {
                OPTIONS.setFortressSeparation(OPTIONS.getFortressSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("fortressSeparation has been set to " + OPTIONS.getFortressSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getBastionSeparation() > OPTIONS.getBastionSpacing()) {
                OPTIONS.setBastionSeparation(OPTIONS.getBastionSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("bastionSeparation has been set to " + OPTIONS.getBastionSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getEndCitySeparation() > OPTIONS.getEndCitySpacing()) {
                OPTIONS.setEndCitySeparation(OPTIONS.getEndCitySpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("endCitySeparation has been set to " + OPTIONS.getEndCitySeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getMansionSeparation() > OPTIONS.getMansionSpacing()) {
                OPTIONS.setMansionSeparation(OPTIONS.getMansionSpacing() - 1);
                LOGGER.warn("Separation values cannot be larger than spacing values!");
                LOGGER.info("mansionSeparation has been set to " + OPTIONS.getMansionSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getRuinedPortalSpacing() < 6 || OPTIONS.getRuinedPortalSpacing() > 40) {
                OPTIONS.setRuinedPortalSpacing(9);
                LOGGER.warn("ruinedPortalSpacing cannot be set below 6 or above 40!");
                LOGGER.info("ruinedPortalSpacing has been set to " + OPTIONS.getRuinedPortalSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getRuinedPortalSeparation() < 3 || OPTIONS.getRuinedPortalSeparation() > 15) {
                OPTIONS.setRuinedPortalSeparation(8);
                LOGGER.warn("ruinedPortalSeparation cannot be set below 3 or above 15!");
                LOGGER.info("ruinedPortalSeparation has been set to " + OPTIONS.getRuinedPortalSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getVillageSpacing() < 5 || OPTIONS.getVillageSpacing() > 32) {
                OPTIONS.setVillageSpacing(16);
                LOGGER.warn("villageSpacing cannot be set below 5 or above 32!");
                LOGGER.info("villageSpacing has been set to " + OPTIONS.getVillageSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getVillageSeparation() < 3 || OPTIONS.getVillageSeparation() > 12) {
                OPTIONS.setVillageSeparation(9);
                LOGGER.warn("villageSeparation cannot be set below 3 or above 12!");
                LOGGER.info("villageSeparation has been set to " + OPTIONS.getVillageSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getDesertPyramidSpacing() < 8 || OPTIONS.getDesertPyramidSpacing() > 32) {
                OPTIONS.setDesertPyramidSpacing(10);
                LOGGER.warn("desertPyramidSpacing cannot be set below 8 or above 32!");
                LOGGER.info("desertPyramidSpacing has been set to " + OPTIONS.getDesertPyramidSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getDesertPyramidSeparation() < 3 || OPTIONS.getDesertPyramidSeparation() > 10) {
                OPTIONS.setDesertPyramidSeparation(8);
                LOGGER.warn("desertPyramidSeparation cannot be set below 3 or above 10!");
                LOGGER.info("desertPyramidSeparation has been set to " + OPTIONS.getDesertPyramidSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getIglooSpacing() < 2 || OPTIONS.getIglooSpacing() > 32) {
                OPTIONS.setIglooSpacing(32);
                LOGGER.warn("iglooSpacing cannot be set below 2 or above 32!");
                LOGGER.info("iglooSpacing has been set to " + OPTIONS.getIglooSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getIglooSeparation() < 1 || OPTIONS.getIglooSeparation() > 8) {
                OPTIONS.setIglooSeparation(8);
                LOGGER.warn("iglooSeparation cannot be set below 1 or above 8!");
                LOGGER.info("iglooSeparation has been set to " + OPTIONS.getIglooSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getJunglePyramidSpacing() < 3 || OPTIONS.getJunglePyramidSpacing() > 32) {
                OPTIONS.setJunglePyramidSpacing(32);
                LOGGER.warn("junglePyramidSpacing cannot be set below 3 or above 32!");
                LOGGER.info("junglePyramidSpacing has been set to " + OPTIONS.getJunglePyramidSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getJunglePyramidSeparation() < 1 || OPTIONS.getJunglePyramidSeparation() > 8) {
                OPTIONS.setJunglePyramidSeparation(8);
                LOGGER.warn("junglePyramidSeparation cannot be set below 1 or above 8!");
                LOGGER.info("junglePyramidSeparation has been set to " + OPTIONS.getJunglePyramidSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getSwampHutSpacing() < 2 || OPTIONS.getSwampHutSpacing() > 32) {
                OPTIONS.setSwampHutSpacing(32);
                LOGGER.warn("swampHutSpacing cannot be set below 2 or above 32!");
                LOGGER.info("swampHutSpacing has been set to " + OPTIONS.getSwampHutSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getSwampHutSeparation() < 1 || OPTIONS.getSwampHutSeparation() > 8) {
                OPTIONS.setSwampHutSeparation(8);
                LOGGER.warn("swampHutSeparation cannot be set below 1 or above 8!");
                LOGGER.info("swampHutSeparation has been set to " + OPTIONS.getSwampHutSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getPillagerOutpostSpacing() < 8 || OPTIONS.getPillagerOutpostSpacing() > 32) {
                OPTIONS.setPillagerOutpostSpacing(32);
                LOGGER.warn("pillagerOutpostSpacing cannot be set below 8 or above 32!");
                LOGGER.info("pillagerOutpostSpacing has been set to " + OPTIONS.getPillagerOutpostSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getPillagerOutpostSeparation() < 6 || OPTIONS.getPillagerOutpostSeparation() > 8) {
                OPTIONS.setPillagerOutpostSeparation(8);
                LOGGER.warn("pillagerOutpostSeparation cannot be set below 6 or above 8!");
                LOGGER.info("pillagerOutpostSeparation has been set to " + OPTIONS.getPillagerOutpostSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getMonumentSpacing() < 5 || OPTIONS.getMonumentSpacing() > 32) {
                OPTIONS.setMonumentSpacing(32);
                LOGGER.warn("monumentSpacing cannot be set below 5 or above 32!");
                LOGGER.info("monumentSpacing has been set to " + OPTIONS.getMonumentSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getMonumentSeparation() < 2 || OPTIONS.getMonumentSeparation() > 5) {
                OPTIONS.setMonumentSeparation(5);
                LOGGER.warn("monumentSeparation cannot be set below 2 or above 5!");
                LOGGER.info("monumentSeparation has been set to " + OPTIONS.getMonumentSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getShipwreckSpacing() < 6 || OPTIONS.getShipwreckSpacing() > 24) {
                OPTIONS.setShipwreckSpacing(10);
                LOGGER.warn("shipwreckSpacing cannot be set below 6 or above 24!");
                LOGGER.info("shipwreckSpacing has been set to " + OPTIONS.getShipwreckSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getShipwreckSeparation() < 2 || OPTIONS.getShipwreckSeparation() > 10) {
                OPTIONS.setShipwreckSeparation(8);
                LOGGER.warn("shipwreckSeparation cannot be set below 2 or above 10!");
                LOGGER.info("shipwreckSeparation has been set to " + OPTIONS.getShipwreckSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getOceanRuinSpacing() < 3 || OPTIONS.getOceanRuinSpacing() > 20) {
                OPTIONS.setOceanRuinSpacing(20);
                LOGGER.warn("oceanRuinSpacing cannot be set below 3 or above 20!");
                LOGGER.info("oceanRuinSpacing has been set to " + OPTIONS.getOceanRuinSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getOceanRuinSeparation() < 2 || OPTIONS.getOceanRuinSeparation() > 8) {
                OPTIONS.setOceanRuinSeparation(8);
                LOGGER.warn("oceanRuinSeparation cannot be set below 2 or above 8!");
                LOGGER.info("oceanRuinSeparation has been set to " + OPTIONS.getOceanRuinSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getFortressSpacing() < 4 || OPTIONS.getFortressSpacing() > 27) {
                OPTIONS.setFortressSpacing(8);
                LOGGER.warn("fortressSpacing cannot be set below 4 or above 27!");
                LOGGER.info("fortressSpacing has been set to " + OPTIONS.getFortressSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getFortressSeparation() < 2 || OPTIONS.getFortressSeparation() > 10) {
                OPTIONS.setFortressSeparation(7);
                LOGGER.warn("fortressSeparation cannot be set below 2 or above 10!");
                LOGGER.info("fortressSeparation has been set to " + OPTIONS.getFortressSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getBastionSpacing() < 5 || OPTIONS.getBastionSpacing() > 27) {
                OPTIONS.setBastionSpacing(9);
                LOGGER.warn("bastionSpacing cannot be set below 5 or above 27!");
                LOGGER.info("bastionSpacing has been set to " + OPTIONS.getBastionSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getBastionSeparation() < 3 || OPTIONS.getBastionSeparation() > 10) {
                OPTIONS.setBastionSeparation(8);
                LOGGER.warn("bastionSeparation cannot be set below 3 or above 10!");
                LOGGER.info("bastionSeparation has been set to " + OPTIONS.getBastionSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getEndCitySpacing() < 3 || OPTIONS.getEndCitySpacing() > 20) {
                OPTIONS.setEndCitySpacing(7);
                LOGGER.warn("endCitySpacing cannot be set below 3 or above 20!");
                LOGGER.info("endCitySpacing has been set to " + OPTIONS.getEndCitySpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getEndCitySeparation() < 1 || OPTIONS.getEndCitySeparation() > 11) {
                OPTIONS.setEndCitySeparation(6);
                LOGGER.warn("endCitySeparation cannot be set below 1 or above 11!");
                LOGGER.info("endCitySeparation has been set to " + OPTIONS.getEndCitySeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getMansionSpacing() < 3 || OPTIONS.getMansionSpacing() > 80) {
                OPTIONS.setMansionSpacing(80);
                LOGGER.warn("mansionSpacing cannot be set below 3 or above 80!");
                LOGGER.info("mansionSpacing has been set to " + OPTIONS.getMansionSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getMansionSeparation() < 1 || OPTIONS.getMansionSeparation() > 20) {
                OPTIONS.setMansionSeparation(20);
                LOGGER.warn("mansionSeparation cannot be set below 1 or above 20!");
                LOGGER.info("mansionSeparation has been set to " + OPTIONS.getMansionSeparation());
                saveAndReloadOptions();
            }
        }
    }

    public static void fixForNormalMode() {
        if (OPTIONS.makeStructuresMoreCommon) {
            OPTIONS.setMakeStructuresMoreCommon(false);
        }

        if (OPTIONS.modifiedWorldGeneration) {
            OPTIONS.setModifiedWorldGeneration(false);
        }

        if (OPTIONS.getStrongholdCount() != 128) {
            OPTIONS.setStrongholdDistance(128);
        }

        if (OPTIONS.getStrongholdDistance() != 32) {
            OPTIONS.setStrongholdDistance(32);
        }

        if (!OPTIONS.netherRuinedPortals) {
            OPTIONS.setNetherRuinedPortals(true);
        }
    }
}