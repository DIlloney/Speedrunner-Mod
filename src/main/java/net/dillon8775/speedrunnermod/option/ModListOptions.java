package net.dillon8775.speedrunnermod.option;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ModListOptions {
    public static final CyclingOption<ModOptions.StructureSpawnRates> STRUCTURE_SPAWN_RATE =
            CyclingOption.create("speedrunnermod.options.structure_spawn_rates",
                            ModOptions.StructureSpawnRates.values(), (structureSpawnRate) ->
                                    new TranslatableText(structureSpawnRate.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerMod.options().main.structureSpawnRates, (gameOptions, option, structureSpawnRate) ->
                                    SpeedrunnerMod.options().main.structureSpawnRates = structureSpawnRate)
                    .tooltip(client -> structureSpawnRate ->
                            client.textRenderer.wrapLines(
                                    new TranslatableText("speedrunnermod.options.structure_spawn_rates.tooltip"), 200));
    public static final CyclingOption<Boolean> FASTER_BLOCK_BREAKING =
            CyclingOption.create("speedrunnermod.options.faster_block_breaking",
                    new TranslatableText("speedrunnermod.options.faster_block_breaking.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.fasterBlockBreaking, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.fasterBlockBreaking = newValue);
    public static final DoubleOption BLOCK_BREAKING_MULTIPLIER =
            new DoubleOption("speedrunnermod.options.block_breaking_multiplier",
                    1.0D, 3.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.blockBreakingMultiplier, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.blockBreakingMultiplier = newValue.intValue(), (gameOptions, option) ->
                    SpeedrunnerMod.options().main.blockBreakingMultiplier == 1 ?
                            new LiteralText(I18n.translate("speedrunnermod.options.block_breaking_multiplier") + ": OFF") :
                            new LiteralText(I18n.translate("speedrunnermod.options.block_breaking_multiplier") + ": x" + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.block_breaking_multiplier.tooltip"), 200));
    public static final CyclingOption<Boolean> BETTER_BIOMES =
            CyclingOption.create("speedrunnermod.options.better_biomes",
                    new TranslatableText("speedrunnermod.options.better_biomes.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.betterBiomes, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.betterBiomes = newValue);
    public static final CyclingOption<Boolean> ICARUS_MODE =
            CyclingOption.create("speedrunnermod.options.icarus_mode",
                    new TranslatableText("speedrunnermod.options.icarus_mode.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.iCarusMode, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.iCarusMode = newValue);
    public static final CyclingOption<Boolean> FOG =
            CyclingOption.create("speedrunnermod.options.fog", gameOptions ->
                    SpeedrunnerMod.options().client.fog, (gameOptions, option, newValue) -> {
                SpeedrunnerMod.options().client.fog = newValue;
                MinecraftClient.getInstance().worldRenderer.reload();
            }).tooltip(client -> aBoolean -> client.textRenderer.wrapLines(
                    new TranslatableText("speedrunnermod.options.fog.tooltip"), 200));
    public static final CyclingOption<Boolean> INFINITY_PEARL_MODE =
            CyclingOption.create("speedrunnermod.options.infini_pearl_mode",
                    new TranslatableText("speedrunnermod.options.infini_pearl_mode.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.infiniPearlMode, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.infiniPearlMode = newValue);
    public static final CyclingOption<Boolean> LEADERBOARDS_MODE =
            CyclingOption.create("speedrunnermod.options.leaderboards_mode",
                    new TranslatableText("speedrunnermod.options.leaderboards_mode.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.leaderboardsMode, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.leaderboardsMode = newValue);
    public static final CyclingOption<Boolean> ITEM_TOOLTIPS =
            CyclingOption.create("speedrunnermod.options.item_tooltips",
                    new TranslatableText("speedrunnermod.options.item_tooltips.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().client.itemTooltips, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().client.itemTooltips = newValue);
    public static final CyclingOption<Boolean> DOOM_MODE =
            CyclingOption.create("speedrunnermod.options.doom_mode",
                    new TranslatableText("speedrunnermod.options.doom_mode.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.doomMode, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.doomMode = newValue);
    public static final DoubleOption DRAGON_PERCH_TIME =
            new DoubleOption("speedrunnermod.options.dragon_perch_time",
                    8.0D, 90.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.dragonPerchTime, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.dragonPerchTime = newValue.intValue(), (gameOptions, option) ->
                    SpeedrunnerMod.options().main.dragonPerchTime == 9 ?
                            new LiteralText(I18n.translate("speedrunnermod.options.dragon_perch_time") + ": Instant") :
                            SpeedrunnerMod.options().main.dragonPerchTime <= 8 ?
                                    new LiteralText(I18n.translate("speedrunnermod.options.dragon_perch_time") + ": OFF") :
                                    new LiteralText(I18n.translate("speedrunnermod.options.dragon_perch_time") + ": " + (int)option.get(gameOptions) + " seconds"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.dragon_perch_time.tooltip"), 200));
    public static final CyclingOption<Boolean> KILL_GHAST_ON_FIREBALL =
            CyclingOption.create("speedrunnermod.options.kill_ghast_on_fireball",
                    new TranslatableText("speedrunnermod.options.kill_ghast_on_fireball.tooltip"),
                    gameOptions -> SpeedrunnerMod.options().main.killGhastOnFireball, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.killGhastOnFireball = newValue);
    public static final CyclingOption<Boolean> BETTER_VILLAGER_TRADES =
            CyclingOption.create("speedrunnermod.options.better_villager_trades",
                    new TranslatableText("speedrunnermod.options.better_villager_trades.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.betterVillagerTrades, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.betterVillagerTrades = newValue);
    public static final CyclingOption<Boolean> FIREPROOF_ITEMS =
            CyclingOption.create("speedrunnermod.options.fireproof_items",
                    new TranslatableText("speedrunnermod.options.fireproof_items.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.fireproofItems, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.fireproofItems = newValue);
    public static final CyclingOption<ModOptions.Panorama> PANORAMA =
            CyclingOption.create("speedrunnermod.options.panorama",
                            ModOptions.Panorama.values(), (panorama) ->
                                    new TranslatableText(panorama.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerMod.options().client.panorama, (gameOptions, option, panorama) ->
                                    SpeedrunnerMod.options().client.panorama = panorama)
                    .tooltip(client -> (mobSpawningRate) -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.panorama.tooltip"), 200));
    public static final DoubleOption MOB_SPAWNER_MINIMUM_SPAWN_DURATION =
            new DoubleOption("speedrunnermod.options.mob_spawner_minimum_spawn_duration",
                    5.0D, 40.0D, 5.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.mobSpawnerMinimumSpawnDuration, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.mobSpawnerMinimumSpawnDuration = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.mob_spawner_minimum_spawn_duration") + ": " + (int)option.get(gameOptions) + " seconds"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mob_spawner_minimum_spawn_duration.tooltip"), 200));
    public static final DoubleOption MOB_SPAWNER_MAXIMUM_SPAWN_DURATION =
            new DoubleOption("speedrunnermod.options.mob_spawner_max_spawn_duration",
                    20.0D, 80.0D, 5.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.mobSpawnerMaximumSpawnDuration, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.mobSpawnerMaximumSpawnDuration = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.mob_spawner_maximum_spawn_duration") + ": " + (int)option.get(gameOptions) + " seconds"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mob_spawner_maximum_spawn_duration.tooltip"), 200));
    public static final CyclingOption<Boolean> CUSTOM_BIOMES =
            CyclingOption.create("speedrunnermod.options.custom_biomes",
                    new TranslatableText("speedrunnermod.options.custom_biomes.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.customBiomes, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.customBiomes = newValue);
    public static final CyclingOption<Boolean> COMMON_ORES =
            CyclingOption.create("speedrunnermod.options.common_ores",
                    new TranslatableText("speedrunnermod.options.common_ores.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.commonOres, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.commonOres = newValue);
    public static final CyclingOption<Boolean> LAVA_BOATS =
            CyclingOption.create("speedrunnermod.options.lava_boats",
                    new TranslatableText("speedrunnermod.options.lava_boats.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.lavaBoats, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.lavaBoats = newValue);
    public static final CyclingOption<Boolean> NETHER_WATER =
            CyclingOption.create("speedrunnermod.options.nether_water",
                    new TranslatableText("speedrunnermod.options.nether_water.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.netherWater, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.netherWater = newValue);
    public static final CyclingOption<Boolean> BETTER_FOODS =
            CyclingOption.create("speedrunnermod.options.better_foods",
                    new TranslatableText("speedrunnermod.options.better_foods.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.betterFoods, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.betterFoods = newValue);
    public static final CyclingOption<Boolean> FALL_DAMAGE =
            CyclingOption.create("speedrunnermod.options.fall_damage",
                    new TranslatableText("speedrunnermod.options.fall_damage.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.fallDamage, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.fallDamage = newValue);
    public static final DoubleOption STRONGHOLD_DISTANCE =
            new DoubleOption("speedrunnermod.options.stronghold_distance",
                    3.0D, 64.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.strongholdDistance, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.strongholdDistance = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_distance") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_distance.tooltip"), 200));
    public static final DoubleOption STRONGHOLD_SPREAD =
            new DoubleOption("speedrunnermod.options.stronghold_spread",
                    2.0D, 32.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.strongholdSpread, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.strongholdSpread = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_spread") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_spread.tooltip"), 200));
    public static final DoubleOption STRONGHOLD_COUNT =
            new DoubleOption("speedrunnermod.options.stronghold_count",
                    4.0D, 156.0D, 4.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.strongholdCount, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.strongholdCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_count") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_count.tooltip"), 200));
    public static final DoubleOption STRONGHOLD_PORTAL_ROOM_COUNT =
            new DoubleOption("speedrunnermod.options.stronghold_portal_room_count",
                    1.0D, 3.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.strongholdPortalRoomCount, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.strongholdPortalRoomCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_portal_room_count") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_portal_room_count.tooltip"), 200));
    public static final DoubleOption STRONGHOLD_LIBRARY_COUNT =
            new DoubleOption("speedrunnermod.options.stronghold_library_count",
                    1.0D, 8.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.strongholdLibraryCount, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.strongholdLibraryCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_library_count") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_library_count.tooltip"), 200));
    public static final CyclingOption<ModOptions.ItemMessages> ITEM_MESSAGES =
            CyclingOption.create("speedrunnermod.options.item_messages",
                            ModOptions.ItemMessages.values(), (itemMessages) ->
                                    new TranslatableText(itemMessages.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerMod.options().client.itemMessages, (gameOptions, option, itemMessages) ->
                                    SpeedrunnerMod.options().client.itemMessages = itemMessages)
                    .tooltip(client -> itemMessages -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.item_messages.tooltip"), 200));
    public static final CyclingOption<ModOptions.MobSpawningRate> MOB_SPAWNING_RATE =
            CyclingOption.create("speedrunnermod.options.mob_spawning_rate",
                            ModOptions.MobSpawningRate.values(), (mobSpawningRate) ->
                                    new TranslatableText(mobSpawningRate.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerMod.options().main.mobSpawningRate, (gameOptions, option, mobSpawningRate) ->
                                    SpeedrunnerMod.options().main.mobSpawningRate = mobSpawningRate)
                    .tooltip(client -> (mobSpawningRate) -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mob_spawning_rate.tooltip"), 200));
    public static final CyclingOption<ModOptions.ModButtonType> MOD_BUTTON_TYPE =
            CyclingOption.create("speedrunnermod.options.mod_button_type",
                            ModOptions.ModButtonType.values(), (mobSpawningRate) ->
                                    new TranslatableText(mobSpawningRate.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerMod.options().client.modButtonType, (gameOptions, option, modButtonType) ->
                                    SpeedrunnerMod.options().client.modButtonType = modButtonType)
                    .tooltip(client -> modButtonType -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mod_button_type.tooltip"), 200));
    public static final CyclingOption<Boolean> SOCIAL_BUTTONS =
            CyclingOption.create("speedrunnermod.options.social_buttons",
                    new TranslatableText("speedrunnermod.options.social_buttons.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().client.socialButtons, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().client.socialButtons = newValue);
    public static final DoubleOption NETHER_PORTAL_COOLDOWN =
            new DoubleOption("speedrunnermod.options.nether_portal_cooldown",
                    0.0D, 20.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.netherPortalCooldown, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.netherPortalCooldown = newValue.intValue(), (gameOptions, option) ->
                    SpeedrunnerMod.options().main.netherPortalCooldown == 0 ?
                            new LiteralText(I18n.translate("speedrunnermod.options.portal_cooldown") + ": None") :
                            new LiteralText(I18n.translate("speedrunnermod.options.nether_portal_cooldown") + ": " + (int)option.get(gameOptions) + "s"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.nether_portal_cooldown.tooltip"), 200));
    public static final CyclingOption<Boolean> ARROWS_DESTROY_BEDS =
            CyclingOption.create("speedrunnermod.options.arrows_destroy_beds",
                    new TranslatableText("speedrunnermod.options.arrows_destroy_beds.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.arrowsDestroyBeds, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.arrowsDestroyBeds = newValue);
    public static final CyclingOption<Boolean> GLOBAL_NETHER_PORTALS =
            CyclingOption.create("speedrunnermod.options.global_nether_portals",
                    new TranslatableText("speedrunnermod.options.global_nether_portals.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.globalNetherPortals, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.globalNetherPortals = newValue);
    public static final CyclingOption<Boolean> BETTER_ANVIL =
            CyclingOption.create("speedrunnermod.options.better_anvil",
                    new TranslatableText("speedrunnermod.options.better_anvil.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.betterAnvil, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.betterAnvil = newValue);
    public static final DoubleOption ANVIL_COST_LIMIT =
            new DoubleOption("speedrunnermod.options.anvil_cost_limit",
                    1.0D, 50.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.anvilCostLimit, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.anvilCostLimit = newValue.intValue(), (gameOptions, option) ->
                    SpeedrunnerMod.options().main.anvilCostLimit == 50 ?
                            new LiteralText(I18n.translate("speedrunnermod.options.anvil_cost_limit") + ": No Limit") :
                            SpeedrunnerMod.options().main.anvilCostLimit == 1 ?
                                    new LiteralText(I18n.translate("speedrunnermod.options.anvil_cost_limit") + ": " + (int)option.get(gameOptions) + " level") :
                                    new LiteralText(I18n.translate("speedrunnermod.options.anvil_cost_limit") + ": " + (int)option.get(gameOptions) + " levels"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.anvil_cost_limit.tooltip"), 200));
    public static final CyclingOption<Boolean> HIGHER_ENCHANTMENT_LEVELS =
            CyclingOption.create("speedrunnermod.options.higher_enchantment_levels",
                    new TranslatableText("speedrunnermod.options.higher_enchantment_levels.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.higherEnchantmentLevels, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.higherEnchantmentLevels = newValue);
    public static final CyclingOption<Boolean> CONFIRM_MESSAGES =
            CyclingOption.create("speedrunnermod.options.confirm_messages",
                    new TranslatableText("speedrunnermod.options.confirm_messages.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().client.confirmMessages, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().client.confirmMessages = newValue);
    public static final CyclingOption<Boolean> STACK_UNSTACKABLES =
            CyclingOption.create("speedrunnermod.options.stack_unstackables",
                    new TranslatableText("speedrunnermod.options.stack_unstackables.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.stackUnstackables, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.stackUnstackables = newValue);
    public static final CyclingOption<Boolean> SHOW_DEATH_CORDS =
            CyclingOption.create("speedrunnermod.options.show_death_cords",
                    new TranslatableText("speedrunnermod.options.show_death_cords.tooltip"),
                    gameOptions -> SpeedrunnerMod.options().client.showDeathCords, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().client.showDeathCords = newValue);
    public static final CyclingOption<Boolean> KINETIC_DAMAGE =
            CyclingOption.create("speedrunnermod.options.kinetic_damage",
                    new TranslatableText("speedrunnermod.options.kinetic_damage.tooltip"),
                    gameOptions -> SpeedrunnerMod.options().main.kineticDamage, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.kineticDamage = newValue);
    public static final CyclingOption<Boolean> THROWABLE_FIREBALLS =
            CyclingOption.create("speedrunnermod.options.throwable_fireballs",
                    new TranslatableText("speedrunnermod.options.throwable_fireballs.tooltip"),
                    gameOptions -> SpeedrunnerMod.options().main.throwableFireballs, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.throwableFireballs = newValue);

    public static final CyclingOption<Boolean> FAST_WORLD_CREATION =
            CyclingOption.create("speedrunnermod.options.fast_world_creation",
                    new TranslatableText("speedrunnermod.options.fast_world_creation.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().client.fastWorldCreation, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().client.fastWorldCreation = newValue);
    public static final CyclingOption<ModOptions.GameMode> GAMEMODE =
            CyclingOption.create("speedrunnermod.options.gamemode",
                            ModOptions.GameMode.values(), (gameMode) ->
                                    new TranslatableText(gameMode.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerMod.options().client.gameMode, (gameOptions, option, gameMode) ->
                                    SpeedrunnerMod.options().client.gameMode = gameMode)
                    .tooltip(client -> (gameMode) -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.gamemode.tooltip"), 200));
    public static final CyclingOption<ModOptions.Difficulty> DIFFICULTY =
            CyclingOption.create("speedrunnermod.options.difficulty",
                            ModOptions.Difficulty.values(), (difficulty) ->
                                    new TranslatableText(difficulty.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerMod.options().client.difficulty, (gameOptions, option, difficulty) ->
                                    SpeedrunnerMod.options().client.difficulty = difficulty)
                    .tooltip(client -> (difficulty) -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.difficulty.tooltip"), 200));
    public static final CyclingOption<Boolean> ALLOW_CHEATS =
            CyclingOption.create("speedrunnermod.options.allow_cheats",
                    new TranslatableText("speedrunnermod.options.allow_cheats.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().client.allowCheats, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().client.allowCheats = newValue);
}