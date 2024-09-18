package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonElement;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;

import static net.dillon.speedrunnermod.SpeedrunnerMod.*;

/**
 * Contains all of the {@code structure modifications,} making them generate more commonly.
 */
@ChatGPT(Credit.FULL_CREDIT)
public class StructuresLoader {

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ancient cities.}
     */
    public static void modifyAncientCities(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getAncientCitySpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getAncientCitySeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code desert pyramids.}
     */
    public static void modifyDesertPyramids(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getDesertPyramidSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getDesertPyramidSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code end cities.}
     */
    public static void modifyEndCities(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getEndCitySpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getEndCitySeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code jungle temples.}
     */
    public static void modifyJungleTemples(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getJungleTempleSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getJungleTempleSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code nether complexes} (nether fortresses and bastions).
     */
    public static void modifyNetherComplexes(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getNetherComplexesSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getNetherComplexesSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code pillager outposts.}
     */
    public static void modifyPillagerOutposts(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getPillagerOutpostSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getPillagerOutpostSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ruined portals.}
     */
    public static void modifyRuinedPortals(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getRuinedPortalSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getRuinedPortalSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code shipwrecks.}
     */
    public static void modifyShipwrecks(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getShipwreckSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getShipwreckSeparation());
    }

    /**
     * Changes the {@code distance,} {@code spread,} and {@code count} values for {@code strongholds.}
     * <p>Distance - how close strongholds can generate to spawn. </p>
     * <p>Spread - how far apart strongholds can generate from each other.</p>
     * <p>Count - the total amount of strongholds that are allowed to create in a single Minecraft world.</p>
     */
    public static void modifyStrongholds(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("distance", options().main.strongholdDistance.getCurrentValue());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spread", options().main.strongholdSpread.getCurrentValue());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("count", options().main.strongholdCount.getCurrentValue());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code trial chambers.}
     */
    public static void modifyTrialChambers(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getTrialChambersSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getTrialChambersSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code villagers.}
     */
    public static void modifyVillages(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getVillageSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getVillageSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code woodland mansions.}
     */
    public static void modifyWoodlandMansions(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getWoodlandMansionSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getWoodlandMansionSeparation());
    }
}