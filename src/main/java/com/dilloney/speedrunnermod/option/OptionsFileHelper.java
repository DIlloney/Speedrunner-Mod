package com.dilloney.speedrunnermod.option;

import com.dilloney.speedrunnermod.SpeedrunnerMod;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

public class OptionsFileHelper {

    private static void saveAndReloadOptions() {
        OptionsFileManager.save();
        OptionsFileManager.load();
    }

    public static void fix() {
        if (OPTIONS.getModDifficulty() < 1 || OPTIONS.getModDifficulty() > 4) {
            OPTIONS.setModDifficulty(1);
            SpeedrunnerMod.LOGGER.warn("modDifficulty cannot be set below 1 or above 4!");
            SpeedrunnerMod.LOGGER.info("modDifficulty has been set to " + OPTIONS.getModDifficulty());
            saveAndReloadOptions();
        }

        if (OPTIONS.getStrongholdCount() < 64 || OPTIONS.getStrongholdCount() > 256) {
            OPTIONS.setStrongholdCount(128);
            SpeedrunnerMod.LOGGER.warn("strongholdCount cannot be set below 64 or above 256!");
            SpeedrunnerMod.LOGGER.info("strongholdCount has been set to " + OPTIONS.getStrongholdCount());
            saveAndReloadOptions();
        }

        if (OPTIONS.makeStructuresMoreCommon) {
            if (OPTIONS.getStrongholdDistance() < 3 || OPTIONS.getStrongholdDistance() > 36) {
                OPTIONS.setStrongholdDistance(5);
                SpeedrunnerMod.LOGGER.warn("strongholdDistance cannot be set below 3 or above 36!");
                SpeedrunnerMod.LOGGER.info("strongholdDistance has been set to " + OPTIONS.getStrongholdDistance());
                saveAndReloadOptions();
            }

            if (OPTIONS.getRuinedPortalSeparation() > OPTIONS.getRuinedPortalSpacing()) {
                OPTIONS.setRuinedPortalSeparation(OPTIONS.getRuinedPortalSpacing() - 1);
                SpeedrunnerMod.LOGGER.warn("Separation values cannot be larger than spacing values!");
                SpeedrunnerMod.LOGGER.info("ruinedPortalSeparation has been set to " + OPTIONS.getRuinedPortalSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getVillageSeparation() > OPTIONS.getVillageSpacing()) {
                OPTIONS.setVillageSeparation(OPTIONS.getVillageSpacing() - 1);
                SpeedrunnerMod.LOGGER.warn("Separation values cannot be larger than spacing values!");
                SpeedrunnerMod.LOGGER.info("villageSeparation has been set to " + OPTIONS.getVillageSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getDesertPyramidSeparation() > OPTIONS.getDesertPyramidSpacing()) {
                OPTIONS.setDesertPyramidSeparation(OPTIONS.getDesertPyramidSpacing() - 1);
                SpeedrunnerMod.LOGGER.warn("Separation values cannot be larger than spacing values!");
                SpeedrunnerMod.LOGGER.info("desertPyramidSeparation has been set to " + OPTIONS.getDesertPyramidSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getShipwreckSeparation() > OPTIONS.getShipwreckSpacing()) {
                OPTIONS.setShipwreckSeparation(OPTIONS.getShipwreckSpacing() - 1);
                SpeedrunnerMod.LOGGER.warn("Separation values cannot be larger than spacing values!");
                SpeedrunnerMod.LOGGER.info("shipwreckSeparation has been set to " + OPTIONS.getShipwreckSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getFortressSeparation() > OPTIONS.getFortressSpacing()) {
                OPTIONS.setFortressSeparation(OPTIONS.getFortressSpacing() - 1);
                SpeedrunnerMod.LOGGER.warn("Separation values cannot be larger than spacing values!");
                SpeedrunnerMod.LOGGER.info("fortressSeparation has been set to " + OPTIONS.getFortressSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getBastionSeparation() > OPTIONS.getBastionSpacing()) {
                OPTIONS.setBastionSeparation(OPTIONS.getBastionSpacing() - 1);
                SpeedrunnerMod.LOGGER.warn("Separation values cannot be larger than spacing values!");
                SpeedrunnerMod.LOGGER.info("bastionSeparation has been set to " + OPTIONS.getBastionSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getEndCitySeparation() > OPTIONS.getEndCitySpacing()) {
                OPTIONS.setEndCitySeparation(OPTIONS.getEndCitySpacing() - 1);
                SpeedrunnerMod.LOGGER.warn("Separation values cannot be larger than spacing values!");
                SpeedrunnerMod.LOGGER.info("endCitySeparation has been set to " + OPTIONS.getEndCitySeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getRuinedPortalSpacing() < 2 || OPTIONS.getRuinedPortalSpacing() > 40) {
                OPTIONS.setRuinedPortalSpacing(9);
                SpeedrunnerMod.LOGGER.warn("ruinedPortalSpacing cannot be set below 2 or above 40!");
                SpeedrunnerMod.LOGGER.info("ruinedPortalSpacing has been set to " + OPTIONS.getRuinedPortalSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getRuinedPortalSeparation() < 2 || OPTIONS.getRuinedPortalSeparation() > 15) {
                OPTIONS.setRuinedPortalSeparation(8);
                SpeedrunnerMod.LOGGER.warn("ruinedPortalSeparation cannot be set below 2 or above 15!");
                SpeedrunnerMod.LOGGER.info("ruinedPortalSeparation has been set to " + OPTIONS.getRuinedPortalSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getVillageSpacing() < 2 || OPTIONS.getVillageSpacing() > 32) {
                OPTIONS.setVillageSpacing(16);
                SpeedrunnerMod.LOGGER.warn("villageSpacing cannot be set below 2 or above 32!");
                SpeedrunnerMod.LOGGER.info("villageSpacing has been set to " + OPTIONS.getVillageSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getVillageSeparation() < 2 || OPTIONS.getVillageSeparation() > 12) {
                OPTIONS.setVillageSeparation(9);
                SpeedrunnerMod.LOGGER.warn("villageSeparation cannot be set below 2 or above 12!");
                SpeedrunnerMod.LOGGER.info("villageSeparation has been set to " + OPTIONS.getVillageSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getDesertPyramidSpacing() < 2 || OPTIONS.getDesertPyramidSpacing() > 32) {
                OPTIONS.setDesertPyramidSpacing(10);
                SpeedrunnerMod.LOGGER.warn("desertPyramidSpacing cannot be set below 2 or above 32!");
                SpeedrunnerMod.LOGGER.info("desertPyramidSpacing has been set to " + OPTIONS.getDesertPyramidSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getDesertPyramidSeparation() < 2 || OPTIONS.getDesertPyramidSeparation() > 10) {
                OPTIONS.setDesertPyramidSeparation(8);
                SpeedrunnerMod.LOGGER.warn("desertPyramidSeparation cannot be set below 2 or above 10!");
                SpeedrunnerMod.LOGGER.info("desertPyramidSeparation has been set to " + OPTIONS.getDesertPyramidSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getShipwreckSpacing() < 2 || OPTIONS.getShipwreckSpacing() > 24) {
                OPTIONS.setShipwreckSpacing(10);
                SpeedrunnerMod.LOGGER.warn("shipwreckSpacing cannot be set below 2 or above 24!");
                SpeedrunnerMod.LOGGER.info("shipwreckSpacing has been set to " + OPTIONS.getShipwreckSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getShipwreckSeparation() < 2 || OPTIONS.getShipwreckSeparation() > 10) {
                OPTIONS.setShipwreckSeparation(8);
                SpeedrunnerMod.LOGGER.warn("shipwreckSeparation cannot be set below 2 or above 10!");
                SpeedrunnerMod.LOGGER.info("shipwreckSeparation has been set to " + OPTIONS.getShipwreckSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getFortressSpacing() < 2 || OPTIONS.getFortressSpacing() > 27) {
                OPTIONS.setFortressSpacing(8);
                SpeedrunnerMod.LOGGER.warn("fortressSpacing cannot be set below 2 or above 27!");
                SpeedrunnerMod.LOGGER.info("fortressSpacing has been set to " + OPTIONS.getFortressSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getFortressSeparation() < 2 || OPTIONS.getFortressSeparation() > 10) {
                OPTIONS.setFortressSeparation(7);
                SpeedrunnerMod.LOGGER.warn("fortressSeparation cannot be set below 2 or above 10!");
                SpeedrunnerMod.LOGGER.info("fortressSeparation has been set to " + OPTIONS.getFortressSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getBastionSpacing() < 2 || OPTIONS.getBastionSpacing() > 27) {
                OPTIONS.setBastionSpacing(9);
                SpeedrunnerMod.LOGGER.warn("bastionSpacing cannot be set below 2 or above 27!");
                SpeedrunnerMod.LOGGER.info("bastionSpacing has been set to " + OPTIONS.getBastionSpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getBastionSeparation() < 2 || OPTIONS.getBastionSeparation() > 10) {
                OPTIONS.setBastionSeparation(8);
                SpeedrunnerMod.LOGGER.warn("bastionSeparation cannot be set below 2 or above 10!");
                SpeedrunnerMod.LOGGER.info("bastionSeparation has been set to " + OPTIONS.getBastionSeparation());
                saveAndReloadOptions();
            }

            if (OPTIONS.getEndCitySpacing() < 2 || OPTIONS.getEndCitySpacing() > 20) {
                OPTIONS.setEndCitySpacing(7);
                SpeedrunnerMod.LOGGER.warn("endCitySpacing cannot be set below 2 or above 20!");
                SpeedrunnerMod.LOGGER.info("endCitySpacing has been set to " + OPTIONS.getEndCitySpacing());
                saveAndReloadOptions();
            }

            if (OPTIONS.getEndCitySeparation() < 2 || OPTIONS.getEndCitySeparation() > 11) {
                OPTIONS.setEndCitySeparation(6);
                SpeedrunnerMod.LOGGER.warn("endCitySeparation cannot be set below 2 or above 11!");
                SpeedrunnerMod.LOGGER.info("endCitySeparation has been set to " + OPTIONS.getEndCitySeparation());
                saveAndReloadOptions();
            }
        }

        if (OPTIONS.modifiedWorldGeneration) {
            if (OPTIONS.getMaximumFortressBlazeSpawners() < 2 || OPTIONS.getMaximumFortressBlazeSpawners() > 4) {
                OPTIONS.setMaximumFortressBlazeSpawners(3);
                SpeedrunnerMod.LOGGER.warn("maximumFortressBlazeSpawners cannot be set below 2 or above 4!");
                SpeedrunnerMod.LOGGER.info("maximumFortressBlazeSpawners has been set to " + OPTIONS.getMaximumFortressBlazeSpawners());
                saveAndReloadOptions();
            }

            if (OPTIONS.getMaximumStrongholdPortalRooms() < 1 || OPTIONS.getMaximumStrongholdPortalRooms() > 3) {
                OPTIONS.setMaximumStrongholdPortalRooms(3);
                SpeedrunnerMod.LOGGER.warn("maximumStrongholdPortalRooms cannot be set below 1 or above 3!");
                SpeedrunnerMod.LOGGER.info("maximumStrongholdPortalRooms has been set to " + OPTIONS.getMaximumStrongholdPortalRooms());
                saveAndReloadOptions();
            }

            if (OPTIONS.getMaximumStrongholdLibraries() < 1 || OPTIONS.getMaximumStrongholdLibraries() > 2) {
                OPTIONS.setMaximumStrongholdLibraries(2);
                SpeedrunnerMod.LOGGER.warn("maximumStrongholdLibraries cannot be set below 1 or above 2!");
                SpeedrunnerMod.LOGGER.info("maximumStrongholdLibraries has been set to " + OPTIONS.getMaximumStrongholdLibraries());
                saveAndReloadOptions();
            }
        }

    }

    public static void fixForOfficialSpeedruns() {
        if (!OPTIONS.makeStructuresMoreCommon) {
            SpeedrunnerMod.LOGGER.warn("makeStructuresMoreCommon is not turned on, this is required for submitting your runs!");
            SpeedrunnerMod.LOGGER.info("Turning ON makeStructuresMoreCommon for you.");
            OPTIONS.setMakeStructuresMoreCommon(true);
            saveAndReloadOptions();
        }

        if (!OPTIONS.modifiedWorldGeneration) {
            SpeedrunnerMod.LOGGER.warn("modifiedWorldGeneration is not turned on, this is required for submitting your runs!");
            SpeedrunnerMod.LOGGER.info("Turning ON modifiedWorldGeneration for you.");
            OPTIONS.setModifiedWorldGeneration(true);
            saveAndReloadOptions();
        }

        if (!OPTIONS.modifiedBlockHardness) {
            SpeedrunnerMod.LOGGER.warn("modifiedBlockHardness is not turned on, this is required for submitting your runs!");
            SpeedrunnerMod.LOGGER.info("Turning ON modifiedBlockHardness for you.");
            OPTIONS.setModifiedBlockHardness(true);
            saveAndReloadOptions();
        }

        if (!OPTIONS.modifiedLootTables) {
            SpeedrunnerMod.LOGGER.warn("modifiedLootTables is not turned on, this is required for submitting your runs!");
            SpeedrunnerMod.LOGGER.info("Turning ON modifiedLootTables for you.");
            OPTIONS.setModifiedLootTables(true);
            saveAndReloadOptions();
        }

        if (OPTIONS.killGhastUponFireball) {
            SpeedrunnerMod.LOGGER.warn("killGhastUponFireball is turned on, this option is not allowed when submitting your runs!");
            SpeedrunnerMod.LOGGER.info("Turning OFF killGhastUponFireball for you.");
            OPTIONS.setKillGhastUponFireball(false);
            saveAndReloadOptions();
        }

        if (OPTIONS.getMaximumFortressBlazeSpawners() != 3) {
            SpeedrunnerMod.LOGGER.warn("maximumFortressBlazeSpawners is set to " + OPTIONS.getMaximumFortressBlazeSpawners() + ", this option must be set to 3 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting maximumFortressBlazeSpawners to 3 for you.");
            OPTIONS.setMaximumFortressBlazeSpawners(3);
            saveAndReloadOptions();
        }

        if (OPTIONS.getStrongholdCount() != 128) {
            SpeedrunnerMod.LOGGER.warn("strongholdCount is set to " + OPTIONS.getStrongholdCount() + ", this option must be set to 128 in order to your runs!");
            SpeedrunnerMod.LOGGER.info("Setting strongholdCount to 128 for you.");
            OPTIONS.setStrongholdCount(128);
            saveAndReloadOptions();
        }

        if (OPTIONS.getStrongholdDistance() != 5) {
            SpeedrunnerMod.LOGGER.warn("strongholdDistance is set to " + OPTIONS.getStrongholdDistance() + ", this option must be set to 5 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting strongholdDistance to 5 for you.");
            OPTIONS.setStrongholdDistance(5);
            saveAndReloadOptions();
        }

        if (OPTIONS.getMaximumStrongholdPortalRooms() != 3) {
            OPTIONS.setMaximumStrongholdPortalRooms(3);
            SpeedrunnerMod.LOGGER.warn("maximumStrongholdPortalRooms is not set to 3, this option must be set to 3 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting maximumStrongholdPortalRooms to 3 for you.");
            saveAndReloadOptions();
        }

        if (OPTIONS.getMaximumStrongholdLibraries() != 2) {
            OPTIONS.setMaximumStrongholdLibraries(2);
            SpeedrunnerMod.LOGGER.warn("maximumStrongholdLibraries is not set to 2, this option must be set to 2 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting maximumStrongholdLibraries to 2 for you.");
            saveAndReloadOptions();
        }

        if (OPTIONS.netherRuinedPortals) {
            SpeedrunnerMod.LOGGER.warn("netherRuinedPortals is turned on, this option is not allowed when submitting your runs!");
            SpeedrunnerMod.LOGGER.info("Turning OFF netherRuinedPortals for you.");
            OPTIONS.setNetherRuinedPortals(false);
            saveAndReloadOptions();
        }

        if (OPTIONS.netherPortalsInTheEnd) {
            SpeedrunnerMod.LOGGER.warn("netherPortalsInTheEnd is turned on, this option is not allowed when submitting your runs!");
            SpeedrunnerMod.LOGGER.info("Turning OFF netherPortalsInTheEnd for you.");
            OPTIONS.setNetherPortalsInTheEnd(false);
            saveAndReloadOptions();
        }

        if (OPTIONS.getRuinedPortalSpacing() != 9) {
            SpeedrunnerMod.LOGGER.warn("ruinedPortalSpacing is set to " + OPTIONS.getRuinedPortalSpacing() + ", this option must be set to 9 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting ruinedPortalSpacing to 9 for you.");
            OPTIONS.setRuinedPortalSpacing(9);
            saveAndReloadOptions();
        }

        if (OPTIONS.getRuinedPortalSeparation() != 8) {
            SpeedrunnerMod.LOGGER.warn("ruinedPortalSeparation is set to " + OPTIONS.getRuinedPortalSeparation() + ", this option must be set to 8 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting ruinedPortalSeparation to 8 for you.");
            OPTIONS.setRuinedPortalSeparation(8);
            saveAndReloadOptions();
        }

        if (OPTIONS.getVillageSpacing() != 16) {
            SpeedrunnerMod.LOGGER.warn("villageSpacing is set to " + OPTIONS.getVillageSpacing() + ", this option must be set to 16 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting villageSpacing to 16 for you.");
            OPTIONS.setVillageSpacing(16);
            saveAndReloadOptions();
        }

        if (OPTIONS.getVillageSeparation() != 9) {
            SpeedrunnerMod.LOGGER.warn("villageSeparation is set to " + OPTIONS.getVillageSeparation() + ", this option must be set to 9 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting villageSeparation to 9 for you.");
            OPTIONS.setVillageSeparation(9);
            saveAndReloadOptions();
        }

        if (OPTIONS.getDesertPyramidSpacing() != 10) {
            SpeedrunnerMod.LOGGER.warn("desertPyramidSpacing is set to " + OPTIONS.getDesertPyramidSpacing() + ", this option must be set to 10 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting desertPyramidSpacing to 10 for you.");
            OPTIONS.setDesertPyramidSpacing(10);
            saveAndReloadOptions();
        }

        if (OPTIONS.getDesertPyramidSeparation() != 8) {
            SpeedrunnerMod.LOGGER.warn("desertPyramidSeparation is set to " + OPTIONS.getDesertPyramidSeparation() + ", this option must be set to 8 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting desertPyramidSeparation to 8 for you.");
            OPTIONS.setDesertPyramidSeparation(8);
            saveAndReloadOptions();
        }

        if (OPTIONS.getShipwreckSpacing() != 10) {
            SpeedrunnerMod.LOGGER.warn("shipwreckSpacing is set to " + OPTIONS.getShipwreckSpacing() + ", this option must be set to 10 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting shipwreckSpacing to 10 for you.");
            OPTIONS.setShipwreckSpacing(10);
            saveAndReloadOptions();
        }

        if (OPTIONS.getShipwreckSeparation() != 8) {
            SpeedrunnerMod.LOGGER.warn("shipwreckSeparation is set to " + OPTIONS.getShipwreckSeparation() + ", this option must be set to 8 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting shipwreckSeparation to 8 for you.");
            OPTIONS.setShipwreckSeparation(8);
            saveAndReloadOptions();
        }

        if (OPTIONS.getFortressSpacing() != 8) {
            SpeedrunnerMod.LOGGER.warn("fortressSpacing is set to " + OPTIONS.getFortressSpacing() + ", this option must be set to 8 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting fortressSpacing to 8 for you.");
            OPTIONS.setFortressSpacing(8);
            saveAndReloadOptions();
        }

        if (OPTIONS.getFortressSeparation() != 7) {
            SpeedrunnerMod.LOGGER.warn("fortressSeparation is set to " + OPTIONS.getFortressSeparation() + ", this option must be set to 7 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting fortressSeparation to 7 for you.");
            OPTIONS.setFortressSeparation(7);
            saveAndReloadOptions();
        }

        if (OPTIONS.getBastionSpacing() != 9) {
            SpeedrunnerMod.LOGGER.warn("bastionSpacing is set to " + OPTIONS.getBastionSpacing() + ", this option must be set to 9 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting bastionSpacing to 9 for you.");
            OPTIONS.setBastionSpacing(9);
            saveAndReloadOptions();
        }

        if (OPTIONS.getBastionSeparation() != 8) {
            SpeedrunnerMod.LOGGER.warn("bastionSeparation is set to " + OPTIONS.getBastionSeparation() + ", this option must be set to 8 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting bastionSeparation to 8 for you.");
            OPTIONS.setBastionSeparation(8);
            saveAndReloadOptions();
        }

        if (OPTIONS.getEndCitySpacing() != 7) {
            SpeedrunnerMod.LOGGER.warn("endCitySpacing is set to " + OPTIONS.getEndCitySpacing() + ", this option must be set to 7 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting endCitySpacing to 7 for you.");
            OPTIONS.setEndCitySpacing(7);
            saveAndReloadOptions();
        }

        if (OPTIONS.getEndCitySeparation() != 6) {
            SpeedrunnerMod.LOGGER.warn("endCitySeparation is set to " + OPTIONS.getEndCitySeparation() + ", this option must be set to 6 in order to submit your runs!");
            SpeedrunnerMod.LOGGER.info("Setting endCitySeparation to 6 for you.");
            OPTIONS.setEndCitySeparation(6);
            saveAndReloadOptions();
        }
    }
}