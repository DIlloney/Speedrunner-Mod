package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Contains helper methods used to create certain objects and arrays in JSON files.
 */
@ChatGPT(Credit.FULL_CREDIT)
public class LoaderMain {

    /**
     * Modifies the creature spawn parameters in a given biome.
     */
    @Unique
    @ChatGPT(Credit.FULL_CREDIT)
    protected static void modifyCreatureSpawns(JsonElement jsonElement, Map<String, Integer[]> creatureValues, boolean waterCreature) {
        JsonArray spawnersArray = jsonElement.getAsJsonObject().getAsJsonObject("spawners").getAsJsonArray(waterCreature ? "water_creature" : "creature");

        for (JsonElement spawnerElement : spawnersArray) {
            JsonObject spawner = spawnerElement.getAsJsonObject();
            String mobType = spawner.get("type").getAsString();

            if (creatureValues.containsKey(mobType)) {
                Integer[] values = creatureValues.get(mobType);
                spawner.addProperty("weight", values[0]);
                if (options().main.mobSpawningRate.equals(ModOptions.MobSpawningRate.LOW)) {
                    spawner.addProperty("minCount", values[1]);
                    spawner.addProperty("maxCount", values[4]);
                } else if (options().main.mobSpawningRate.equals(ModOptions.MobSpawningRate.NORMAL)) {
                    spawner.addProperty("minCount", values[2]);
                    spawner.addProperty("maxCount", values[4]);
                } else if (options().main.mobSpawningRate.equals(ModOptions.MobSpawningRate.HIGH)) {
                    spawner.addProperty("minCount", values[2]);
                    spawner.addProperty("maxCount", values[3]);
                }
            }
        }
    }

    /**
     * Modifies the monster spawn parameters in a given biome.
     */
    @Unique @ChatGPT(Credit.FULL_CREDIT)
    protected static void modifyMonsterSpawns(JsonElement jsonElement, Map<String, Integer[]> monsterValues, boolean customWeight) {
        JsonArray spawnersArray = jsonElement.getAsJsonObject().getAsJsonObject("spawners").getAsJsonArray("monster");

        for (JsonElement spawnerElement : spawnersArray) {
            JsonObject spawner = spawnerElement.getAsJsonObject();
            String mobType = spawner.get("type").getAsString();

            if (monsterValues.containsKey(mobType)) {
                Integer[] values = monsterValues.get(mobType);
                if (!customWeight) {
                    spawner.addProperty("weight", values[0]);
                    spawner.addProperty("minCount", values[1]);
                    spawner.addProperty("maxCount", values[2]);
                } else {
                    spawner.addProperty("minCount", values[0]);
                    spawner.addProperty("maxCount", values[1]);
                }
            }
        }

        if (DOOM_MODE) {
            JsonObject vindicator = new JsonObject();
            vindicator.addProperty("type", "minecraft:vindicator");
            vindicator.addProperty("maxCount", 4);
            vindicator.addProperty("minCount", 1);
            vindicator.addProperty("weight", 100);
            spawnersArray.add(vindicator);
        }
    }

    /**
     * Creates a mob spawn setting, specifically for creature spawns.
     */
    @Unique
    protected static Integer[] createSpawnSettings(int weight, int minCountLow, int defaultMinCount, int maxCountHigh, int defaultMaxCount) {
        return new Integer[]{weight, minCountLow, defaultMinCount, maxCountHigh, defaultMaxCount};
    }

    /**
     * Creates a mob spawn setting, specifically for monster spawns.
     */
    @Unique
    protected static Integer[] createSpawnSettings(int weight, int minCount, int maxCount) {
        return new Integer[]{weight, minCount, maxCount};
    }

    /**
     * Creates a mob spawn setting without a weight, specifically for monster spawns.
     */
    @Unique
    protected static Integer[] createSpawnSettings(int minCount, int maxCount) {
        return new Integer[]{0, minCount, maxCount};
    }

    /**
     * Creates a basic item entry for chest loot tables.
     */
    protected static JsonObject createItemEntry(String name, int weight) {
        JsonObject entry = new JsonObject();
        entry.addProperty("type", "minecraft:item");
        entry.addProperty("name", name);
        entry.addProperty("weight", weight);
        return entry;
    }

    /**
     * Creates an item entry with a name, weight, and enchantment levels.
     */
    protected static JsonObject createItemEntry(String name, int weight, String function, int level, boolean treasure) {
        JsonObject entry = createItemEntry(name, weight);
        JsonArray functions = new JsonArray();
        JsonObject functionObject = new JsonObject();
        functionObject.addProperty("function", function);
        functionObject.addProperty("levels", level);
        functionObject.addProperty("treasure", treasure);
        functions.add(functionObject);
        entry.add("functions", functions);
        return entry;
    }

    /**
     * Creates an item entry with a name, weight, and enchantments.
     */
    protected static JsonObject createItemEntry(String name, int weight, String function, String... enchantments) {
        JsonObject entry = createItemEntry(name, weight);
        JsonArray functions = new JsonArray();
        JsonObject functionObject = new JsonObject();
        functionObject.addProperty("function", function);
        JsonArray enchantArray = new JsonArray();
        for (String enchantment : enchantments) {
            enchantArray.add(enchantment);
        }
        functionObject.add("enchantments", enchantArray);
        functions.add(functionObject);
        entry.add("functions", functions);
        return entry;
    }

    /**
     * Creates an item entry with a name.
     */
    protected static JsonObject createItemEntry(String name) {
        JsonObject entry = new JsonObject();
        entry.addProperty("type", "minecraft:item");
        entry.addProperty("name", name);
        return entry;
    }

    /**
     * Creates a uniform range, with a minimum and maximum item count.
     */
    protected static JsonObject createUniformRange(int min, int max) {
        JsonObject range = new JsonObject();
        range.addProperty("type", "minecraft:uniform");
        range.addProperty("min", min);
        range.addProperty("max", max);
        return range;
    }

    /**
     * Creates a set count function.
     */
    protected static JsonObject createSetCountFunction(int min, int max) {
        JsonObject function = new JsonObject();
        function.addProperty("function", "minecraft:set_count");
        function.add("count", createUniformRange(min, max));
        return function;
    }

    /**
     * Creates an item with a weight.
     */
    protected static JsonObject createWeightedItem(String name, int weight, JsonObject countRange) {
        JsonObject entry = createItemEntry(name, weight);
        JsonArray functions = new JsonArray();
        JsonObject setCountFunction = new JsonObject();
        setCountFunction.addProperty("function", "minecraft:set_count");
        setCountFunction.add("count", countRange);
        functions.add(setCountFunction);
        entry.add("functions", functions);
        return entry;
    }

    /**
     * Creates an item with a weight and count.
     */
    protected static JsonObject createWeightedItem(String name, int weight, int count) {
        JsonObject entry = createItemEntry(name, weight);
        JsonArray functions = new JsonArray();
        JsonObject setCountFunction = new JsonObject();
        setCountFunction.addProperty("function", "minecraft:set_count");
        JsonObject countObject = new JsonObject();
        countObject.addProperty("type", "minecraft:uniform");
        countObject.addProperty("min", count);
        countObject.addProperty("max", count);
        setCountFunction.add("count", countObject);
        functions.add(setCountFunction);
        entry.add("functions", functions);
        return entry;
    }
}