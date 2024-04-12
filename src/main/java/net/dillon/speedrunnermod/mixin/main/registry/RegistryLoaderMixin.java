package net.dillon.speedrunnermod.mixin.main.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Decoder;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.minecraft.registry.*;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static net.dillon.speedrunnermod.SpeedrunnerMod.*;

@Mixin(RegistryLoader.class)
public abstract class RegistryLoaderMixin {

    /**
     * Directly modifies json files to change world generation.
     */
    @Author("MaxenceDC")
    @ChatGPT
    @Inject(method = "load(Lnet/minecraft/registry/RegistryOps$RegistryInfoGetter;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/registry/MutableRegistry;Lcom/mojang/serialization/Decoder;Ljava/util/Map;)V", at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Decoder;parse(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static <E> void load(RegistryOps.RegistryInfoGetter registryInfoGetter, ResourceManager resourceManager, RegistryKey<? extends Registry<E>> registryRef, MutableRegistry<E> newRegistry, Decoder<E> decoder, Map<RegistryKey<?>, Exception> exceptions, CallbackInfo ci, String string, ResourceFinder resourceFinder, RegistryOps<?> registryOps, Iterator<?> var9, Map.Entry<?, ?> entry, Identifier identifier, RegistryKey<?> registryKey, Resource resource, Reader reader, JsonElement jsonElement) {
        String fileName = identifier.getPath();

        for (int i = 0; i < biomesWithDefaultMonsters().size(); i++) {
            if (fileName.equals(biomesWithDefaultMonsters().get(i))) {
                Map<String, Integer[]> monsterSpawns = new HashMap<>();
                monsterSpawns.put("minecraft:spider", createSpawnSettings(DOOM_MODE ? 75 : 100, DOOM_MODE ? 1 : 4, DOOM_MODE ? 5 : 4));
                monsterSpawns.put("minecraft:slime", createSpawnSettings(DOOM_MODE ? 50 : 100, 1, 4));
                monsterSpawns.put("minecraft:enderman", createSpawnSettings(DOOM_MODE ? 25 : 50, DOOM_MODE ? 1 : 4, 4));
                monsterSpawns.put("minecraft:witch", createSpawnSettings(DOOM_MODE ? 50 : 5, 1, DOOM_MODE ? 4 : 1));

                modifyMonsterSpawns(jsonElement, monsterSpawns, false);

                Map<String, Integer[]> customOrNoChangedWeightMonsterSpawns = new HashMap<>();
                monsterSpawns.put("minecraft:zombie", createSpawnSettings(DOOM_MODE ? 1 : 4, 4));
                monsterSpawns.put("minecraft:creeper", createSpawnSettings(DOOM_MODE ? 1 : 2, 4));

                modifyMonsterSpawns(jsonElement, customOrNoChangedWeightMonsterSpawns, true);

                debug("Modified monster spawns for " + biomesWithDefaultMonsters().get(i).replace("_", " ").replace("worldgen/biome/", "").replace(".json", "") + " biome.");
            }
        }

        for (int i = 0; i < biomesWithFarmAnimals().size(); i++) {
            if (fileName.equals(biomesWithFarmAnimals().get(i))) {
                Map<String, Integer[]> creatureSpawns = new HashMap<>();
                creatureSpawns.put("minecraft:cow", createSpawnSettings(16, 1, 4, 8, 4));
                creatureSpawns.put("minecraft:pig", createSpawnSettings(12, 1, 4, 8, 4));
                creatureSpawns.put("minecraft:sheep", createSpawnSettings(8, 1, 4, 8, 4));
                creatureSpawns.put("minecraft:chicken", createSpawnSettings(8, 1, 4, 8, 4));

                modifyCreatureSpawns(jsonElement, creatureSpawns, false);

                debug("Modified creature spawns for " + biomesWithFarmAnimals().get(i).replace("_", " ").replace("worldgen/biome/", "").replace(".json", "") + " biome.");
            }
        }

        if (fileName.equals("worldgen/biome/warm_ocean.json")) {
            Map<String, Integer[]> waterCreatureSpawns = new HashMap<>();
            waterCreatureSpawns.put("minecraft:dolphin", createSpawnSettings(15, 1, 1, 2, 1));
            waterCreatureSpawns.put("minecraft:tropical_fish", createSpawnSettings(10, 1, 4, 8, 4));

            modifyCreatureSpawns(jsonElement, waterCreatureSpawns, true);

            debug("Modified water creature spawns for warm ocean biome.");
        }

        if (fileName.equals("worldgen/biome/basalt_deltas.json")) {
            JsonObject basaltDeltasSpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
            JsonArray basaltDeltasMonsters = new JsonArray();

            JsonObject ghast = new JsonObject();
            ghast.addProperty("type", "minecraft:ghast");
            ghast.addProperty("maxCount", 1);
            ghast.addProperty("minCount", 1);
            ghast.addProperty("weight", DOOM_MODE ? 40 : 25);

            JsonObject magmaCube = new JsonObject();
            magmaCube.addProperty("type", "minecraft:magma_cube");
            magmaCube.addProperty("maxCount", 4);
            magmaCube.addProperty("minCount", 1);
            magmaCube.addProperty("weight", DOOM_MODE ? 50 : 25);

            JsonObject piglinBrute = new JsonObject();
            piglinBrute.addProperty("type", "minecraft:piglin_brute");
            piglinBrute.addProperty("maxCount", 4);
            piglinBrute.addProperty("minCount", 1);
            piglinBrute.addProperty("weight", 25);

            JsonObject witherSkeleton = new JsonObject();
            witherSkeleton.addProperty("type", "minecraft:wither_skeleton");
            witherSkeleton.addProperty("maxCount", 4);
            witherSkeleton.addProperty("minCount", 1);
            witherSkeleton.addProperty("weight", 50);

            basaltDeltasMonsters.add(ghast);
            basaltDeltasMonsters.add(magmaCube);
            if (DOOM_MODE) {
                basaltDeltasMonsters.add(piglinBrute);
                basaltDeltasMonsters.add(witherSkeleton);
            }

            basaltDeltasSpawners.getAsJsonArray("monster").addAll(basaltDeltasMonsters);

            debug("Created basalt deltas.");
        }

        if (fileName.equals("worldgen/biome/crimson_forest.json")) {
            JsonObject crimsonForestSpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
            JsonArray crimsonForestMonsters = new JsonArray();

            JsonObject zombifiedPiglin = new JsonObject();
            zombifiedPiglin.addProperty("type", "minecraft:zombified_piglin");
            zombifiedPiglin.addProperty("maxCount", 1);
            zombifiedPiglin.addProperty("minCount", 1);
            zombifiedPiglin.addProperty("weight", 2);

            JsonObject hoglin = new JsonObject();
            hoglin.addProperty("type", "minecraft:hoglin");
            hoglin.addProperty("maxCount", DOOM_MODE ? 6 : 4);
            hoglin.addProperty("minCount", DOOM_MODE ? 4 : 1);
            hoglin.addProperty("weight", DOOM_MODE ? 50 : 6);

            JsonObject piglin = new JsonObject();
            piglin.addProperty("type", "minecraft:piglin");
            piglin.addProperty("maxCount", DOOM_MODE ? 6 : 4);
            piglin.addProperty("minCount", 2);
            piglin.addProperty("weight", DOOM_MODE ? 25 : 9);

            JsonObject piglinBrute = new JsonObject();
            piglinBrute.addProperty("type", "minecraft:piglin_brute");
            piglinBrute.addProperty("maxCount", 4);
            piglinBrute.addProperty("minCount", 1);
            piglinBrute.addProperty("weight", 25);

            JsonObject magmaCube = new JsonObject();
            magmaCube.addProperty("type", "minecraft:magma_cube");
            magmaCube.addProperty("maxCount", 4);
            magmaCube.addProperty("minCount", 1);
            magmaCube.addProperty("weight", 50);

            crimsonForestMonsters.add(zombifiedPiglin);
            crimsonForestMonsters.add(hoglin);
            crimsonForestMonsters.add(piglin);
            if (DOOM_MODE) {
                crimsonForestMonsters.add(piglinBrute);
                crimsonForestMonsters.add(magmaCube);
            }

            crimsonForestSpawners.getAsJsonArray("monster").addAll(crimsonForestMonsters);

            debug("Created crimson forest.");
        }

        if (fileName.equals("worldben/biome/nether_wastes.json")) {
            JsonObject netherWastesSpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
            JsonArray netherWastesMonsters = new JsonArray();

            JsonObject ghast = new JsonObject();
            ghast.addProperty("type", "minecraft:ghast");
            ghast.addProperty("maxCount", DOOM_MODE ? 1 : 4);
            ghast.addProperty("minCount", 1);
            ghast.addProperty("weight", 20);

            JsonObject zombifiedPiglin = new JsonObject();
            zombifiedPiglin.addProperty("type", "minecraft:zombified_piglin");
            zombifiedPiglin.addProperty("maxCount", 4);
            zombifiedPiglin.addProperty("minCount", DOOM_MODE ? 4 : 1);
            zombifiedPiglin.addProperty("weight", DOOM_MODE ? 50 : 25);

            JsonObject magmaCube = new JsonObject();
            magmaCube.addProperty("type", "minecraft:magma_cube");
            magmaCube.addProperty("maxCount", 4);
            magmaCube.addProperty("minCount", 1);
            magmaCube.addProperty("weight", DOOM_MODE ? 50 : 2);

            JsonObject enderman = new JsonObject();
            enderman.addProperty("type", "minecraft:enderman");
            enderman.addProperty("maxCount", 4);
            enderman.addProperty("minCount", 4);
            enderman.addProperty("weight", DOOM_MODE ? 20 : 1);

            JsonObject piglin = new JsonObject();
            piglin.addProperty("type", "minecraft:piglin");
            piglin.addProperty("maxCount", DOOM_MODE ? 2 : 4);
            piglin.addProperty("minCount", DOOM_MODE ? 1 : 2);
            piglin.addProperty("weight", DOOM_MODE ? 25 : 50);

            JsonObject piglinBrute = new JsonObject();
            piglinBrute.addProperty("type", "minecraft:piglin_brute");
            piglinBrute.addProperty("maxCount", 4);
            piglinBrute.addProperty("minCount", 1);
            piglinBrute.addProperty("weight", 25);

            JsonObject hoglin = new JsonObject();
            hoglin.addProperty("type", "minecraft:hoglin");
            hoglin.addProperty("maxCount", 4);
            hoglin.addProperty("minCount", 1);
            hoglin.addProperty("weight", 100);

            netherWastesMonsters.add(ghast);
            netherWastesMonsters.add(zombifiedPiglin);
            netherWastesMonsters.add(magmaCube);
            netherWastesMonsters.add(enderman);
            netherWastesMonsters.add(piglin);
            if (DOOM_MODE) {
                netherWastesMonsters.add(piglinBrute);
                netherWastesMonsters.add(hoglin);
            }

            netherWastesSpawners.getAsJsonArray("monster").addAll(netherWastesMonsters);

            debug("Created nether wastes.");
        }

        if (fileName.equals("worldgen/biome/soul_sand_valley.json")) {
            JsonObject soulSandValleySpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
            JsonArray soulSandValleyMonsters = new JsonArray();

            JsonObject skeleton = new JsonObject();
            skeleton.addProperty("type", "minecraft:skeleton");
            skeleton.addProperty("maxCount", DOOM_MODE ? 5 : 4);
            skeleton.addProperty("minCount", DOOM_MODE ? 5 : 1);
            skeleton.addProperty("weight", DOOM_MODE ? 50 : 10);

            JsonObject ghast = new JsonObject();
            ghast.addProperty("type", "minecraft:ghast");
            ghast.addProperty("maxCount", 4);
            ghast.addProperty("minCount", DOOM_MODE ? 4 : 1);
            ghast.addProperty("weight", 50);

            JsonObject enderman = new JsonObject();
            enderman.addProperty("type", "minecraft:enderman");
            enderman.addProperty("maxCount", 4);
            enderman.addProperty("minCount", 4);
            enderman.addProperty("weight", DOOM_MODE ? 10 : 5);

            JsonObject piglinBrute = new JsonObject();
            piglinBrute.addProperty("type", "minecraft:piglin_brute");
            piglinBrute.addProperty("maxCount", 4);
            piglinBrute.addProperty("minCount", 1);
            piglinBrute.addProperty("weight", 25);

            soulSandValleyMonsters.add(skeleton);
            soulSandValleyMonsters.add(ghast);
            soulSandValleyMonsters.add(enderman);
            if (DOOM_MODE) {
                soulSandValleyMonsters.add(piglinBrute);
            }

            soulSandValleySpawners.getAsJsonArray("monster").addAll(soulSandValleyMonsters);

            debug("Created soul sand valley.");
        }

        if (fileName.equals("worldgen/biome/the_end.json")) {
            JsonObject theEndSpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
            JsonArray theEndMonsters = new JsonArray();

            JsonObject enderman = new JsonObject();
            enderman.addProperty("type", "minecraft:enderman");
            enderman.addProperty("maxCount", 4);
            enderman.addProperty("minCount", 1);
            enderman.addProperty("weight", DOOM_MODE ? 85 : 10);

            JsonObject skeleton = new JsonObject();
            skeleton.addProperty("type", "minecraft:skeleton");
            skeleton.addProperty("maxCount", 4);
            skeleton.addProperty("minCount", 1);
            skeleton.addProperty("weight", 75);

            JsonObject vindicator = new JsonObject();
            vindicator.addProperty("type", "minecraft:vindicator");
            vindicator.addProperty("maxCount", 2);
            vindicator.addProperty("minCount", 1);
            vindicator.addProperty("weight", 75);

            JsonObject ravager = new JsonObject();
            ravager.addProperty("type", "minecraft:ravager");
            ravager.addProperty("maxCount", 1);
            ravager.addProperty("minCount", 1);
            ravager.addProperty("weight", 50);

            JsonObject evoker = new JsonObject();
            evoker.addProperty("type", "minecraft:evoker");
            evoker.addProperty("maxCount", 1);
            evoker.addProperty("minCount", 1);
            evoker.addProperty("weight", 50);

            JsonObject zombie = new JsonObject();
            zombie.addProperty("type", "minecraft:zombie");
            zombie.addProperty("maxCount", 1);
            zombie.addProperty("minCount", 1);
            zombie.addProperty("weight", 25);

            theEndMonsters.add(enderman);
            if (DOOM_MODE) {
                theEndMonsters.add(skeleton);
                theEndMonsters.add(vindicator);
                theEndMonsters.add(ravager);
                theEndMonsters.add(evoker);
                theEndMonsters.add(zombie);
            }

            theEndSpawners.getAsJsonArray("monster").addAll(theEndMonsters);

            JsonObject theEndEffects = jsonElement.getAsJsonObject().getAsJsonObject("effects");

            JsonObject particleObject = new JsonObject();
            JsonObject optionsObject = new JsonObject();
            optionsObject.addProperty("type", "minecraft:crimson_spore");
            particleObject.add("options", optionsObject);
            particleObject.addProperty("probability", 0.030);

            if (DOOM_MODE) {
                theEndEffects.add("particle", particleObject);
            }

            debug("Created the end.");
        }

        if (fileName.equals("worldgen/biome/warped_forest.json")) {
            JsonObject warpedForestSpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
            JsonArray warpedForestMonsters = new JsonArray();

            JsonObject enderman = new JsonObject();
            enderman.addProperty("type", "minecraft:enderman");
            enderman.addProperty("maxCount", 4);
            enderman.addProperty("minCount", 4);
            enderman.addProperty("weight", DOOM_MODE ? 1 : 5);

            JsonObject piglin = new JsonObject();
            piglin.addProperty("type", "minecraft:piglin");
            piglin.addProperty("maxCount", 4);
            piglin.addProperty("minCount", DOOM_MODE ? 4 : 1);
            piglin.addProperty("weight", DOOM_MODE ? 25 : 5);

            JsonObject hoglin = new JsonObject();
            hoglin.addProperty("type", "minecraft:hoglin");
            hoglin.addProperty("maxCount", 4);
            hoglin.addProperty("minCount", 1);
            hoglin.addProperty("weight", 50);

            JsonObject piglinBrute = new JsonObject();
            piglinBrute.addProperty("type", "minecraft:piglin_brute");
            piglinBrute.addProperty("maxCount", 4);
            piglinBrute.addProperty("minCount", 1);
            piglinBrute.addProperty("weight", 25);

            JsonObject magmaCube = new JsonObject();
            magmaCube.addProperty("type", "minecraft:magma_cube");
            magmaCube.addProperty("maxCount", 4);
            magmaCube.addProperty("minCount", 1);
            magmaCube.addProperty("weight", 50);

            warpedForestMonsters.add(enderman);
            warpedForestMonsters.add(piglin);
            if (DOOM_MODE) {
                warpedForestMonsters.add(hoglin);
                warpedForestMonsters.add(piglinBrute);
                warpedForestMonsters.add(magmaCube);
            }

            warpedForestSpawners.getAsJsonArray("monster").addAll(warpedForestMonsters);

            debug("Created warped forest.");
        }

        String monsterRoom = "worldgen/placed_feature/monster_room.json";
        if (fileName.equals(monsterRoom) || fileName.equals("worldgen/placed_feature/monster_room_deep.json")) {
            JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

            for (JsonElement element : placement) {
                JsonObject placementObj = element.getAsJsonObject();
                if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                    placementObj.addProperty("count", options().main.structureSpawnRates.everywhere() || options().main.structureSpawnRates.veryCommonOrCommon() || options().main.structureSpawnRates.normal() ? 16 : 8);
                    break;
                }
            }

            info(fileName.equals(monsterRoom) ? "Created deep monster room feature." : "Created monster room feature.");
        }

        if (options().main.commonOres) {
            String oreDiamond = "worldgen/placed_feature/ore_diamond.json";
            if (fileName.equals(oreDiamond) || fileName.equals("worldgen/placed_feature/ore_diamond_buried.json")) {
                JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

                for (JsonElement element : placement) {
                    JsonObject placementObj = element.getAsJsonObject();
                    if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                        placementObj.addProperty("count", fileName.equals(oreDiamond) ? getOreDiamondChance() : getOreDiamondBuriedChance());
                        break;
                    }
                }

                info(fileName.equals(oreDiamond) ? "Created diamond ore feature." : "Created diamond ore buried feature.");
            }

            if (fileName.equals("worldgen/placed_feature/ore_diamond_large.json")) {
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

                debug("Created diamond ore large feature.");
            }

            String oreLapis = "worldgen/placed_feature/ore_lapis.json";
            if (fileName.equals(oreLapis) || fileName.equals("worldgen/placed_feature/ore_lapis_buried.json")) {
                JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

                for (JsonElement element : placement) {
                    JsonObject placementObj = element.getAsJsonObject();
                    if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                        placementObj.addProperty("count", fileName.equals(oreLapis) ? getOreLapisChance() : getOreLapisBuriedChance());
                        break;
                    }
                }

                info(fileName.equals(oreLapis) ? "Created lapis ore feature." : "Created lapis ore buried feature.");
            }
        }

        if (options().main.customBiomesAndCustomBiomeFeatures) {
            if (fileName.equals("worldgen/placed_feature/trees_plains.json")) {
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

        if (!options().main.structureSpawnRates.equals(ModOptions.StructureSpawnRates.DISABLED)) {
            if (fileName.equals("worldgen/structure_set/ancient_cities.json")) {
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getAncientCitySpacing());
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getAncientCitySeparation());
                logChange("ancient cities");
            }

            if (fileName.equals("worldgen/structure_set/desert_pyramids.json")) {
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getDesertPyramidSpacing());
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getDesertPyramidSeparation());
                logChange("desert pyramids");
            }

            if (fileName.equals("worldgen/structure_set/end_cities.json")) {
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getEndCitySpacing());
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getEndCitySeparation());
                logChange("end cities");
            }

            if (fileName.equals("worldgen/structure_set/jungle_temples.json")) {
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getJungleTempleSpacing());
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getJungleTempleSeparation());
                logChange("jungle temples");
            }

            if (fileName.equals("worldgen/structure_set/nether_complexes.json")) {
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getNetherComplexesSpacing());
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getNetherComplexesSeparation());
                logChange("nether complexes");
            }

            if (fileName.equals("worldgen/structure_set/pillager_outposts.json")) {
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getPillagerOutpostSpacing());
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getPillagerOutpostSeparation());
                logChange("pillager outposts");
            }

            if (fileName.equals("worldgen/structure_set/ruined_portals.json")) {
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getRuinedPortalSpacing());
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getRuinedPortalSeparation());
                logChange("ruined portals");
            }

            if (fileName.equals("worldgen/structure_set/shipwrecks.json")) {
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getShipwreckSpacing());
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getShipwreckSeparation());
                logChange("shipwrecks");
            }

            if (fileName.equals("worldgen/structure_set/strongholds.json")) {
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("distance", options().main.strongholdDistance);
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spread", options().main.strongholdSpread);
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("count", options().main.strongholdCount);
                logChange("strongholds");
            }

            if (fileName.equals("worldgen/structure_set/villages.json")) {
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getVillageSpacing());
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getVillageSeparation());
                logChange("villages");
            }

            if (fileName.equals("worldgen/structure_set/woodland_mansions.json")) {
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getWoodlandMansionSpacing());
                jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getWoodlandMansionSeparation());
                logChange("woodland mansions");
            }
        } else {
            debug("Structure Spawn Rates is disabled. No changes were made to structures rarity.");
        }

        if (fileName.equals("worldgen/noise_settings/end.json")) {
            String stone = DOOM_MODE ? "speedrunnermod:doom_stone" : "minecraft:end_stone";
            JsonObject defaultBlock = jsonElement.getAsJsonObject().getAsJsonObject("default_block");
            defaultBlock.addProperty("Name", stone);

            JsonObject surfaceRule = jsonElement.getAsJsonObject().getAsJsonObject("surface_rule");
            JsonObject resultState = surfaceRule.getAsJsonObject("result_state");
            resultState.addProperty("Name", stone);

            debug("Created end dimension.");
        }
    }

    /**
     * Modifies the creature spawn parameters in a given biome.
     */
    @Unique @ChatGPT
    private static void modifyCreatureSpawns(JsonElement jsonElement, Map<String, Integer[]> creatureValues, boolean waterCreature) {
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
    @Unique @ChatGPT
    private static void modifyMonsterSpawns(JsonElement jsonElement, Map<String, Integer[]> monsterValues, boolean customWeight) {
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
    private static Integer[] createSpawnSettings(int weight, int minCountLow, int defaultMinCount, int maxCountHigh, int defaultMaxCount) {
        return new Integer[]{weight, minCountLow, defaultMinCount, maxCountHigh, defaultMaxCount};
    }

    /**
     * Creates a mob spawn setting, specifically for monster spawns.
     */
    @Unique
    private static Integer[] createSpawnSettings(int weight, int minCount, int maxCount) {
        return new Integer[]{weight, minCount, maxCount};
    }

    /**
     * Creates a mob spawn setting without a weight, specifically for monster spawns.
     */
    @Unique
    private static Integer[] createSpawnSettings(int minCount, int maxCount) {
        return new Integer[]{0, minCount, maxCount};
    }

    /**
     * A list of all biomes that include {@link net.minecraft.world.gen.feature.DefaultBiomeFeatures#addMonsters(SpawnSettings.Builder, int, int, int, boolean)}.
     */
    @Unique
    private static List<String> biomesWithDefaultMonsters() {
        return List.of(biomeName("old_growth_pine_taiga"),
                biomeName("old_growth_spruce_taiga"),
                biomeName("windswept_hills"),
                biomeName("windswept_gravelly_hills"),
                biomeName("windswept_forest"),
                biomeName("savanna"),
                biomeName("savanna_plateau"),
                biomeName("windswept_savanna"),
                biomeName("badlands"),
                biomeName("eroded_badlands"),
                biomeName("wooded_badlands"),
                biomeName("frozen_ocean"),
                biomeName("deep_frozen_ocean"),
                biomeName("forest"),
                biomeName("flower_forest"),
                biomeName("birch_forest"),
                biomeName("old_growth_birch_forest"),
                biomeName("taiga"),
                biomeName("snowy_taiga"),
                biomeName("dark_forest"),
                biomeName("swamp"),
                biomeName("mangrove_swamp"),
                biomeName("river"),
                biomeName("frozen_river"),
                biomeName("beach"),
                biomeName("snowy_beach"),
                biomeName("stony_shore"),
                biomeName("meadow"),
                biomeName("frozen_peaks"),
                biomeName("jagged_peaks"),
                biomeName("stony_peaks"),
                biomeName("snowy_slopes"),
                biomeName("grove"),
                biomeName("lush_caves"));
    }

    /**
     * A list of all biomes that use {@link net.minecraft.world.gen.feature.DefaultBiomeFeatures#addFarmAnimals(SpawnSettings.Builder)}.
     */
    @Unique
    private static List<String> biomesWithFarmAnimals() {
        return List.of(biomeName("old_growth_pine_taiga"),
                biomeName("old_growth_spruce_taiga"),
                biomeName("windswept_hills"),
                biomeName("windswept_gravelly_hills"),
                biomeName("windswept_forest"),
                biomeName("savanna"),
                biomeName("savanna_plateau"),
                biomeName("windswept_savanna"),
                biomeName("forest"),
                biomeName("flower_forest"),
                biomeName("birch_forest"),
                biomeName("old_growth_birch_forest"),
                biomeName("taiga"),
                biomeName("snowy_taiga"),
                biomeName("dark_forest"),
                biomeName("swamp"),
                biomeName("grove"));
    }

    /**
     * Returns the file name of the given biome.
     */
    @Unique
    private static String biomeName(String biomeName) {
        return "worldgen/biome/" + biomeName + ".json";
    }

    /**
     * Logs that the structure file was found, and logs which structure spawn rate the structure is generating on.
     */
    @Unique
    private static void logChange(String structure) {
        debug("Generating " + structure + " on StructureSpawnRates." + options().main.structureSpawnRates.toString().toLowerCase() + "() setting.");
    }
}