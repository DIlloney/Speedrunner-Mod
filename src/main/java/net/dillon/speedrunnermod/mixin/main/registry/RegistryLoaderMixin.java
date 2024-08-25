package net.dillon.speedrunnermod.mixin.main.registry;

import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.serialization.Decoder;
import net.dillon.speedrunnermod.data.loader.*;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryLoader;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.entry.RegistryEntryInfo;
import net.minecraft.resource.Resource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(RegistryLoader.class)
public class RegistryLoaderMixin {
    @Unique
    private final String CHAT = "https://chatgpt.com/c/501e728a-d6a2-405c-bc2c-b80cfd5b9b18"; // The ChatGPT chat used for generating JSON objects and arrays.

    /**
     * Directly modifies json files to change world generation.
     */
    @Author(Authors.MAXENCEDC)
    @ChatGPT(Credit.MOST_CREDIT)
    @Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Decoder;parse(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;"), method = "parseAndAdd")
    private static <E> void load(MutableRegistry<E> registry, Decoder<E> decoder, RegistryOps<JsonElement> ops, RegistryKey<E> resourceKey, Resource resource, RegistryEntryInfo registrationInfo, CallbackInfo ci, @Local JsonElement jsonElement) {
        String path = registry.getKey().getValue().getPath();
        String fileName = path + "/" + resourceKey.getValue().getPath() + ".json";

        if (options().main.customDataGeneration) {
            for (int i = 0; i < EntitySpawnsLoader.biomesWithDefaultMonsters().size(); i++) {
                if (fileName.equals(EntitySpawnsLoader.biomesWithDefaultMonsters().get(i))) {
                    EntitySpawnsLoader.modifyBiomesWithDefaultMonsters(jsonElement);
                }
            }

            for (int i = 0; i < EntitySpawnsLoader.biomesWithFarmAnimals().size(); i++) {
                if (fileName.equals(EntitySpawnsLoader.biomesWithFarmAnimals().get(i))) {
                    EntitySpawnsLoader.modifyBiomesWithFarmAnimals(jsonElement);
                }
            }

            if (fileName.equals(JsonIdentifiers.WARM_OCEAN)) {
                EntitySpawnsLoader.modifyWaterCreatureSpawns(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.BASALT_DELTAS)) {
                NetherBiomesLoader.modifyBasaltDeltas(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.CRIMSON_FOREST)) {
                NetherBiomesLoader.modifyCrimsonForest(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.NETHER_WASTES)) {
                NetherBiomesLoader.modifyNetherWastes(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.SOUL_SAND_VALLEY)) {
                NetherBiomesLoader.modifySoulSandValley(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.THE_END)) {
                TheEndBiomesLoader.modifyTheEnd(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.WARPED_FOREST)) {
                NetherBiomesLoader.modifyWarpedForest(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.MONSTER_ROOM) || fileName.equals(JsonIdentifiers.MONSTER_ROOM_DEEP)) {
                PlacedFeaturesLoader.modifyMonsterRoom(jsonElement);
            }

            if (options().main.commonOres) {
                String oreDiamond = JsonIdentifiers.ORE_DIAMOND;
                if (fileName.equals(oreDiamond) || fileName.equals(JsonIdentifiers.ORE_DIAMOND_BURIED)) {
                    PlacedFeaturesLoader.modifyOreDiamond(fileName, oreDiamond, jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.ORE_DIAMOND_LARGE)) {
                    PlacedFeaturesLoader.modifyOreDiamondLarge(jsonElement);
                }

                String oreLapis = JsonIdentifiers.ORE_LAPIS;
                if (fileName.equals(oreLapis) || fileName.equals(JsonIdentifiers.ORE_LAPIS_BURIED)) {
                    PlacedFeaturesLoader.modifyOreLapis(fileName, oreLapis, jsonElement);
                }
            }

            if (options().main.customBiomesAndCustomBiomeFeatures) {
                if (fileName.equals(JsonIdentifiers.TREES_PLAINS)) {
                    PlacedFeaturesLoader.modifyTreePlains(jsonElement);
                }
            }

            if (!options().main.structureSpawnRates.equals(ModOptions.StructureSpawnRates.DISABLED)) {
                if (fileName.equals(JsonIdentifiers.ANCIENT_CITIES)) {
                    StructuresLoader.modifyAncientCities(jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.DESERT_PYRAMIDS)) {
                    StructuresLoader.modifyDesertPyramids(jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.END_CITIES)) {
                    StructuresLoader.modifyEndCities(jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.JUNGLE_TEMPLES)) {
                    StructuresLoader.modifyJungleTemples(jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.NETHER_COMPLEXES)) {
                    StructuresLoader.modifyNetherComplexes(jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.PILLAGER_OUTPOSTS)) {
                    StructuresLoader.modifyPillagerOutposts(jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.RUINED_PORTALS)) {
                    StructuresLoader.modifyRuinedPortals(jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.SHIPWRECKS)) {
                    StructuresLoader.modifyShipwrecks(jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.STRONGHOLDS)) {
                    StructuresLoader.modifyStrongholds(jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.TRIAL_CHAMBERS)) {
                    StructuresLoader.modifyTrialChambers(jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.VILLAGES)) {
                    StructuresLoader.modifyVillages(jsonElement);
                }

                if (fileName.equals(JsonIdentifiers.WOODLAND_MANSIONS)) {
                    StructuresLoader.modifyWoodlandMansions(jsonElement);
                }
            }

            if (fileName.equals(JsonIdentifiers.END)) {
                TheEndBiomesLoader.modifyEnd(jsonElement);
            }
        }
    }
}