package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;

import static net.dillon.speedrunnermod.SpeedrunnerMod.*;

/**
 * Contains all of the {@code placed feature modifications.}
 */
@ChatGPT(Credit.FULL_CREDIT)
public class PlacedFeaturesLoader {

    /**
     * Increases the spawn rate of {@code monster rooms.}
     */
    public static void modifyMonsterRoom(JsonElement jsonElement) {
        JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

        for (JsonElement element : placement) {
            JsonObject placementObj = element.getAsJsonObject();
            if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                placementObj.addProperty("count", options().main.structureSpawnRates.everywhere() || options().main.structureSpawnRates.veryCommonOrCommon() || options().main.structureSpawnRates.normal() ? 16 : 8);
                break;
            }
        }
    }

    /**
     * Increases the spawn rate of normal-sized diamond ores.
     */
    public static void modifyOreDiamond(String fileName, String oreDiamond, JsonElement jsonElement) {
        JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

        for (JsonElement element : placement) {
            JsonObject placementObj = element.getAsJsonObject();
            if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                placementObj.addProperty("count", fileName.equals(oreDiamond) ? getOreDiamondChance() : getOreDiamondBuriedChance());
                break;
            }
        }
    }

    /**
     * Increases the spawn rate of large diamond ore veins.
     */
    public static void modifyOreDiamondLarge(JsonElement jsonElement) {
        JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

        for (JsonElement element : placement) {
            JsonObject placementObj = element.getAsJsonObject();
            if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:rarity_filter")) {
                placementObj.addProperty("type", "minecraft:count");

                int chance = placementObj.get("chance").getAsInt();
                placementObj.remove("chance");
                placementObj.addProperty("count", chance);

                placementObj.addProperty("count", getOreDiamondLargeChance());
                break;
            }
        }
    }

    /**
     * Increases the spawn rate of lapis ores.
     */
    public static void modifyOreLapis(String fileName, String oreLapis, JsonElement jsonElement) {
        JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

        for (JsonElement element : placement) {
            JsonObject placementObj = element.getAsJsonObject();
            if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                placementObj.addProperty("count", fileName.equals(oreLapis) ? getOreLapisChance() : getOreLapisBuriedChance());
                break;
            }
        }
    }

    /**
     * Increases the spawn rate of plain oak trees in plains biomes.
     */
    public static void modifyTreePlains(JsonElement jsonElement) {
        JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

        for (JsonElement element : placement) {
            JsonObject placementObj = element.getAsJsonObject();
            if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                JsonObject countObject = placementObj.getAsJsonObject("count");
                JsonArray distributionArray = countObject.getAsJsonArray("distribution");
                JsonObject firstElement = distributionArray.get(0).getAsJsonObject();
                firstElement.addProperty("data", getTreesPlainsCount());
                break;
            }
        }
    }
}