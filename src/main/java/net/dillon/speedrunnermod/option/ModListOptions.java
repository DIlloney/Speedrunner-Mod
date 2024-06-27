package net.dillon.speedrunnermod.option;

import com.mojang.serialization.Codec;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

import java.util.Arrays;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class ModListOptions {

    public static final SimpleOption<ModOptions.StructureSpawnRates> STRUCTURE_SPAWN_RATES =
            new SimpleOption<>("speedrunnermod.options.structure_spawn_rates", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.StructureSpawnRates.values()), Codec.INT.xmap(ModOptions.StructureSpawnRates::byId, ModOptions.StructureSpawnRates::getId)),
                    options().main.structureSpawnRates, value -> options().main.structureSpawnRates = value);

    public static final SimpleOption<ModOptions.ItemMessages> ITEM_MESSAGES =
            new SimpleOption<>("speedrunnermod.options.item_messages", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.item_messages.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.ItemMessages.values()), Codec.INT.xmap(ModOptions.ItemMessages::byId, ModOptions.ItemMessages::getId)),
                    options().client.itemMessages, value -> options().client.itemMessages = value);

    public static final SimpleOption<ModOptions.MobSpawningRate> MOB_SPAWNING_RATE =
            new SimpleOption<>("speedrunnermod.options.mob_spawning_rate", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.mob_spawning_rate.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.MobSpawningRate.values()), Codec.INT.xmap(ModOptions.MobSpawningRate::byId, ModOptions.MobSpawningRate::getId)),
                    options().main.mobSpawningRate, value -> options().main.mobSpawningRate = value);

    public static final SimpleOption<ModOptions.GameMode> GAMEMODE =
            new SimpleOption<>("speedrunnermod.options.gamemode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.gamemode.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.GameMode.values()), Codec.INT.xmap(ModOptions.GameMode::byId, ModOptions.GameMode::getId)),
                    options().client.gameMode, value -> options().client.gameMode = value);

    public static final SimpleOption<ModOptions.Difficulty> DIFFICULTY =
            new SimpleOption<>("speedrunnermod.options.difficulty", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.difficulty.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.Difficulty.values()), Codec.INT.xmap(ModOptions.Difficulty::byId, ModOptions.Difficulty::getId)),
                    ModOptions.Difficulty.EASY, value -> options().client.difficulty = value);

    public static final SimpleOption<Boolean> STATE_OF_THE_ART_ITEMS = new SimpleOption<>("speedrunnermod.options.state_of_the_art_items", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.state_of_the_art_items.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.stateOfTheArtItems, value -> options().main.stateOfTheArtItems = value);

    public static final SimpleOption<Boolean> FASTER_BLOCK_BREAKING = new SimpleOption<>("speedrunnermod.options.faster_block_breaking", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.faster_block_breaking.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fasterBlockBreaking, value -> options().main.fasterBlockBreaking = value);

    public static final SimpleOption<Boolean> ICARUS_MODE = new SimpleOption<>("speedrunnermod.options.icarus_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.icarus_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.iCarusMode, value -> options().main.iCarusMode = value);

    public static final SimpleOption<Boolean> FOG = new SimpleOption<>("speedrunnermod.options.fog", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fog.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.fog, value -> {
        options().client.fog = value;
        MinecraftClient.getInstance().worldRenderer.reload();
    });

    public static final SimpleOption<Boolean> INFINI_PEARL_MODE = new SimpleOption<>("speedrunnermod.options.infini_pearl_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.infini_pearl_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.infiniPearlMode, value -> options().main.infiniPearlMode = value);

    @Deprecated
    public static final SimpleOption<Boolean> LEADERBOARDS_MODE = new SimpleOption<>("speedrunnermod.options.leaderboards_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.leaderboards_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.leaderboardsMode, value -> options().main.leaderboardsMode = value);

    public static final SimpleOption<Boolean> ITEM_TOOLTIPS = new SimpleOption<>("speedrunnermod.options.item_tooltips", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.item_tooltips.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.itemTooltips, value -> options().client.itemTooltips = value);

    public static final SimpleOption<Boolean> TEXTURE_TOOLTIPS = new SimpleOption<>("speedrunnermod.options.texture_tooltips", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.texture_tooltips.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.textureTooltips, value -> options().client.textureTooltips = value);

    public static final SimpleOption<Boolean> DOOM_MODE = new SimpleOption<>("speedrunnermod.options.doom_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.doom_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.doomMode, value -> options().main.doomMode = value);

    public static final SimpleOption<Boolean> KILL_GHAST_ON_FIREBALL = new SimpleOption<>("speedrunnermod.options.kill_ghast_on_fireball", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.kill_ghast_on_fireball.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.killGhastOnFireball, value -> options().main.killGhastOnFireball = value);

    public static final SimpleOption<Boolean> BETTER_VILLAGER_TRADES = new SimpleOption<>("speedrunnermod.options.better_villager_trades", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_villager_trades.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterVillagerTrades, value -> options().main.betterVillagerTrades = value);

    public static final SimpleOption<Boolean> FIREPROOF_ITEMS = new SimpleOption<>("speedrunnermod.options.fireproof_items", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fireproof_items.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fireproofItems, value -> options().main.fireproofItems = value);

    public static final SimpleOption<Boolean> FASTER_SPAWNERS = new SimpleOption<>("speedrunnermod.options.faster_spawners", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.faster_spawners.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fasterSpawners, value -> options().main.fasterSpawners = value);

    public static final SimpleOption<Boolean> CUSTOM_BIOMES_AND_CUSTOM_BIOME_FEATURES = new SimpleOption<>("speedrunnermod.options.custom_biomes_and_custom_biome_features", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.customBiomesAndCustomBiomeFeatures, value -> options().main.customBiomesAndCustomBiomeFeatures = value);

    public static final SimpleOption<Boolean> COMMON_ORES = new SimpleOption<>("speedrunnermod.options.common_ores", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.common_ores.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.commonOres, value -> options().main.commonOres = value);

    public static final SimpleOption<Boolean> LAVA_BOATS = new SimpleOption<>("speedrunnermod.options.lava_boats", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.lava_boats.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.lavaBoats, value -> options().main.lavaBoats = value);

    public static final SimpleOption<Boolean> NETHER_WATER = new SimpleOption<>("speedrunnermod.options.nether_water", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.nether_water.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.netherWater, value -> options().main.netherWater = value);

    public static final SimpleOption<Boolean> BETTER_FOODS = new SimpleOption<>("speedrunnermod.options.better_foods", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_foods.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterFoods, value -> options().main.betterFoods = value);

    public static final SimpleOption<Boolean> BETTER_BIOMES = new SimpleOption<>("speedrunnermod.options.better_biomes", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_biomes.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterFoods, value -> options().main.betterFoods = value);

    public static final SimpleOption<Boolean> FALL_DAMAGE = new SimpleOption<>("speedrunnermod.options.fall_damage", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fall_damage.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fallDamage, value -> options().main.fallDamage = value);

    public static final SimpleOption<Boolean> SOCIAL_BUTTONS = new SimpleOption<>("speedrunnermod.options.social_buttons", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.social_buttons.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.socialButtons, value -> options().client.socialButtons = value);

    public static final SimpleOption<Boolean> ARROWS_DESTROY_BEDS = new SimpleOption<>("speedrunnermod.options.arrows_destroy_beds", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.arrows_destroy_beds.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.arrowsDestroyBeds, value -> options().main.arrowsDestroyBeds = value);

    public static final SimpleOption<Boolean> GLOBAL_NETHER_PORTALS = new SimpleOption<>("speedrunnermod.options.global_nether_portals", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.global_nether_portals.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.globalNetherPortals, value -> options().main.globalNetherPortals = value);

    public static final SimpleOption<Boolean> BETTER_ANVIL = new SimpleOption<>("speedrunnermod.options.better_anvil", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_anvil.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterAnvil, value -> options().main.betterAnvil = value);

    public static final SimpleOption<Boolean> HIGHER_ENCHANTMENT_LEVELS = new SimpleOption<>("speedrunnermod.options.higher_enchantment_levels", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.higher_enchantment_levels.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.higherEnchantmentLevels, value -> options().main.higherEnchantmentLevels = value);

    public static final SimpleOption<Boolean> CONFIRM_MESSAGES = new SimpleOption<>("speedrunnermod.options.confirm_messages", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.confirm_messages.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.confirmMessages, value -> options().client.confirmMessages = value);

    public static final SimpleOption<Boolean> STACK_UNSTACKABLES = new SimpleOption<>("speedrunnermod.options.stack_unstackables", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.stack_unstackables.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.stackUnstackables, value -> options().main.stackUnstackables = value);

    public static final SimpleOption<Boolean> SHOW_DEATH_CORDS = new SimpleOption<>("speedrunnermod.options.show_death_cords", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.show_death_cords.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.showDeathCords, value -> options().client.showDeathCords = value);

    public static final SimpleOption<Boolean> KINETIC_DAMAGE = new SimpleOption<>("speedrunnermod.options.kinetic_damage", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.kinetic_damage.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.kineticDamage, value -> options().main.kineticDamage = value);

    public static final SimpleOption<Boolean> THROWABLE_FIREBALLS = new SimpleOption<>("speedrunnermod.options.throwable_fireballs", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.throwable_fireballs.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.throwableFireballs, value -> options().main.throwableFireballs = value);

    public static final SimpleOption<Boolean> CUSTOM_DATA_GENERATION = new SimpleOption<>("speedrunnermod.options.custom_data_generation", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_data_generation.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.customDataGeneration, value -> options().main.customDataGeneration = value);

    public static final SimpleOption<Boolean> FAST_WORLD_CREATION = new SimpleOption<>("speedrunnermod.options.fast_world_creation", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fast_world_creation.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.fastWorldCreation, value -> options().client.fastWorldCreation = value);

    public static final SimpleOption<Boolean> ALLOW_CHEATS = new SimpleOption<>("speedrunnermod.options.allow_cheats", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.allow_cheats.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.allowCheats, value -> options().client.allowCheats = value);

    public static final SimpleOption<Boolean> PANORAMA = new SimpleOption<>("speedrunnermod.options.custom_panorama", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_panorama.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.customPanorama, value -> options().client.customPanorama = value);

    public static final SimpleOption<Integer> BLOCK_BREAKING_MULTIPLIER =
            new SimpleOption<>("speedrunnermod.options.block_breaking_multiplier", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.block_breaking_multiplier.tooltip")),
                    (optionText, value) -> {
                        if (value == 1) {
                            return GameOptions.getGenericValueText(optionText, ModTexts.OFF);
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal("x" + value));
                        }
                    },
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 3), 1, value -> SpeedrunnerMod.options().main.blockBreakingMultiplier = value);

    public static final SimpleOption<Integer> DRAGON_PERCH_TIME =
            new SimpleOption<>("speedrunnermod.options.dragon_perch_time", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.dragon_perch_time.tooltip")),
                    (optionText, value) -> {
                        if (value == 9) {
                            return GameOptions.getGenericValueText(optionText, Text.literal("Instant"));
                        } else if (value <= 8) {
                            return GameOptions.getGenericValueText(optionText, ModTexts.OFF);
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + " seconds"));
                        }},
                    new SimpleOption.ValidatingIntSliderCallbacks(8, 90), 8, value -> SpeedrunnerMod.options().main.dragonPerchTime = value);

    public static final SimpleOption<Integer> STRONGHOLD_DISTANCE =
            new SimpleOption<>("speedrunnermod.options.stronghold_distance", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.stronghold_distance.tooltip")),
                    GameOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(3, 64), 4, value -> SpeedrunnerMod.options().main.strongholdDistance = value);

    public static final SimpleOption<Integer> STRONGHOLD_SPREAD =
            new SimpleOption<>("speedrunnermod.options.stronghold_spread", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.stronghold_spread.tooltip")),
                    GameOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(2, 32), 3, value -> SpeedrunnerMod.options().main.strongholdSpread = value);

    public static final SimpleOption<Integer> STRONGHOLD_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_count", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.stronghold_count.tooltip")),
                    GameOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(4, 156), 128, value -> SpeedrunnerMod.options().main.strongholdCount = value);

    public static final SimpleOption<Integer> STRONGHOLD_PORTAL_ROOM_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_portal_room_count", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.stronghold_portal_room_count.tooltip")),
                    GameOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 3), 3, value -> SpeedrunnerMod.options().main.strongholdPortalRoomCount = value);

    public static final SimpleOption<Integer> STRONGHOLD_LIBRARY_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_library_count", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.stronghold_library_count.tooltip")),
                    GameOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 8), 2, value -> SpeedrunnerMod.options().main.strongholdLibraryCount = value);

    public static final SimpleOption<Integer> NETHER_PORTAL_COOLDOWN =
            new SimpleOption<>("speedrunnermod.options.nether_portal_cooldown", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.nether_portal_cooldown.tooltip")),
                    (optionText, value) -> {
                        if (value == 0) {
                            return GameOptions.getGenericValueText(optionText, Text.literal("None"));
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + "s"));
                        }},
                    new SimpleOption.ValidatingIntSliderCallbacks(0, 20), 2, value -> SpeedrunnerMod.options().main.netherPortalCooldown = value);

    public static final SimpleOption<Integer> ANVIL_COST_LIMIT =
            new SimpleOption<>("speedrunnermod.options.anvil_cost_limit", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.anvil_cost_limit.tooltip")),
                    (optionText, value) -> {
                        if (value == 50) {
                            return GameOptions.getGenericValueText(optionText, Text.literal("No Limit"));
                        } else if (value == 1) {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + " level"));
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + " levels"));
                        }},
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 50), 10, value -> SpeedrunnerMod.options().main.anvilCostLimit = value);
}