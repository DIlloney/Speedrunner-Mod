package net.dillon8775.speedrunnermod.option;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
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
                                    SpeedrunnerMod.options().structureSpawnRates, (gameOptions, option, structureSpawnRate) ->
                                    SpeedrunnerMod.options().structureSpawnRates = structureSpawnRate)
                    .tooltip(client -> structureSpawnRate ->
                            client.textRenderer.wrapLines(
                                    new TranslatableText("speedrunnermod.options.structure_spawn_rates.tooltip"), 200));
    public static final CyclingOption<Boolean> FASTER_BLOCK_BREAKING =
            CyclingOption.create("speedrunnermod.options.faster_block_breaking",
                    new TranslatableText("speedrunnermod.options.faster_block_breaking.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().fasterBlockBreaking, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().fasterBlockBreaking = newValue);
    public static final CyclingOption<Boolean> BETTER_BIOMES =
            CyclingOption.create("speedrunnermod.options.better_biomes",
                    new TranslatableText("speedrunnermod.options.better_biomes.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().betterBiomes, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().betterBiomes = newValue);
    public static final CyclingOption<Boolean> ICARUS_MODE =
            CyclingOption.create("speedrunnermod.options.icarus_mode",
                    new TranslatableText("speedrunnermod.options.icarus_mode.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().iCarusMode, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().iCarusMode = newValue);
    public static final CyclingOption<Boolean> FOG =
            CyclingOption.create("speedrunnermod.options.fog", gameOptions ->
                    SpeedrunnerModClient.clientOptions().fog, (gameOptions, option, newValue) -> {
                SpeedrunnerModClient.clientOptions().fog = newValue;
                MinecraftClient.getInstance().worldRenderer.reload();
            }).tooltip(client -> aBoolean -> client.textRenderer.wrapLines(
                    new TranslatableText("speedrunnermod.options.fog.tooltip"), 200));
    public static final CyclingOption<Boolean> INFINITY_PEARL_MODE =
            CyclingOption.create("speedrunnermod.options.infini_pearl_mode",
                    new TranslatableText("speedrunnermod.options.infini_pearl_mode.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().infiniPearlMode, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().infiniPearlMode = newValue);
    public static final CyclingOption<Boolean> ITEM_TOOLTIPS =
            CyclingOption.create("speedrunnermod.options.item_tooltips",
                    new TranslatableText("speedrunnermod.options.item_tooltips.tooltip"), gameOptions ->
                            SpeedrunnerModClient.clientOptions().itemTooltips, (gameOptions, option, newValue) ->
                            SpeedrunnerModClient.clientOptions().itemTooltips = newValue);
    public static final CyclingOption<Boolean> DOOM_MODE =
            CyclingOption.create("speedrunnermod.options.doom_mode",
                    new TranslatableText("speedrunnermod.options.doom_mode.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().doomMode, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().doomMode = newValue);
    public static final DoubleOption DRAGON_PERCH_TIME =
            new DoubleOption("speedrunnermod.options.dragon_perch_time",
                    9.0D, 90.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().dragonPerchTime, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().dragonPerchTime = newValue.intValue(), (gameOptions, option) ->
                    SpeedrunnerMod.options().dragonPerchTime <= 9 ?
                            new LiteralText(I18n.translate("speedrunnermod.options.dragon_perch_time") + ": OFF") :
                            new LiteralText(I18n.translate("speedrunnermod.options.dragon_perch_time") + ": " + (int)option.get(gameOptions) + "s"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.dragon_perch_time.tooltip"), 200));
    public static final CyclingOption<Boolean> KILL_GHAST_ON_FIREBALL =
            CyclingOption.create("speedrunnermod.options.kill_ghast_on_fireball",
                    new TranslatableText("speedrunnermod.options.kill_ghast_on_fireball.tooltip"),
                    gameOptions -> SpeedrunnerMod.options().killGhastOnFireball, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().killGhastOnFireball = newValue);
    public static final CyclingOption<Boolean> BETTER_VILLAGER_TRADES =
            CyclingOption.create("speedrunnermod.options.better_villager_trades",
                    new TranslatableText("speedrunnermod.options.better_villager_trades.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().betterVillagerTrades, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().betterVillagerTrades = newValue);
    public static final CyclingOption<Boolean> FIREPROOF_ITEMS =
            CyclingOption.create("speedrunnermod.options.fireproof_items",
                    new TranslatableText("speedrunnermod.options.fireproof_items.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().fireproofItems, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().fireproofItems = newValue);
    public static final CyclingOption<Boolean> FAST_WORLD_CREATION =
            CyclingOption.create("speedrunnermod.options.fast_world_creation", gameOptions ->
                    SpeedrunnerModClient.clientOptions().fastWorldCreation, (gameOptions, option, newValue) ->
                    SpeedrunnerModClient.clientOptions().fastWorldCreation = newValue);
    public static final CyclingOption<ClientModOptions.GameMode> GAMEMODE =
            CyclingOption.create("speedrunnermod.options.gamemode",
                    ClientModOptions.GameMode.values(), (gameMode) ->
                            new TranslatableText(gameMode.getTranslationKey()), (gameOptions) ->
                            SpeedrunnerModClient.clientOptions().gameMode, (gameOptions, option, gameMode) ->
                            SpeedrunnerModClient.clientOptions().gameMode = gameMode);
    public static final CyclingOption<ClientModOptions.Difficulty> DIFFICULTY =
            CyclingOption.create("speedrunnermod.options.difficulty",
                    ClientModOptions.Difficulty.values(), (difficulty) ->
                            new TranslatableText(difficulty.getTranslationKey()), (gameOptions) ->
                            SpeedrunnerModClient.clientOptions().difficulty, (gameOptions, option, difficulty) ->
                            SpeedrunnerModClient.clientOptions().difficulty = difficulty);
    public static final CyclingOption<Boolean> ALLOW_CHEATS =
            CyclingOption.create("speedrunnermod.options.allow_cheats", gameOptions ->
                    SpeedrunnerModClient.clientOptions().allowCheats, (gameOptions, option, newValue) ->
                    SpeedrunnerModClient.clientOptions().allowCheats = newValue);
    public static final CyclingOption<Boolean> GENERATE_SPEEDRUNNER_TREES =
            CyclingOption.create("speedrunnermod.options.generate_speedrunner_trees",
                    new TranslatableText("speedrunnermod.options.generate_speedrunner_trees.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().generateSpeedrunnerTrees, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().generateSpeedrunnerTrees = newValue);
    public static final CyclingOption<ClientModOptions.Panorama> PANORAMA =
            CyclingOption.create("speedrunnermod.options.panorama",
                            ClientModOptions.Panorama.values(), (panorama) ->
                                    new TranslatableText(panorama.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerModClient.clientOptions().panorama, (gameOptions, option, panorama) ->
                                    SpeedrunnerModClient.clientOptions().panorama = panorama)
                    .tooltip(client -> (mobSpawningRate) -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.panorama.tooltip"), 200));
    public static final DoubleOption MOB_SPAWNER_MINIMUM_SPAWN_DURATION =
            new DoubleOption("speedrunnermod.options.mob_spawner_minimum_spawn_duration",
                    5.0D, 40.0D, 5.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().mobSpawnerMinimumSpawnDuration, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().mobSpawnerMinimumSpawnDuration = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.mob_spawner_minimum_spawn_duration") + ": " + (int)option.get(gameOptions) + " seconds"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mob_spawner_minimum_spawn_duration.tooltip"), 200));
    public static final DoubleOption MOB_SPAWNER_MAXIMUM_SPAWN_DURATION =
            new DoubleOption("speedrunnermod.options.mob_spawner_max_spawn_duration",
                    20.0D, 80.0D, 5.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().mobSpawnerMaximumSpawnDuration, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().mobSpawnerMaximumSpawnDuration = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.mob_spawner_maximum_spawn_duration") + ": " + (int)option.get(gameOptions) + " seconds"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mob_spawner_maximum_spawn_duration.tooltip"), 200));
    public static final CyclingOption<Boolean> CUSTOM_BIOMES =
            CyclingOption.create("speedrunnermod.options.custom_biomes",
                    new TranslatableText("speedrunnermod.options.custom_biomes.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().customBiomes, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().customBiomes = newValue);
    public static final CyclingOption<Boolean> COMMON_ORES =
            CyclingOption.create("speedrunnermod.options.common_ores",
                    new TranslatableText("speedrunnermod.options.common_ores.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().commonOres, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().commonOres = newValue);
    public static final CyclingOption<Boolean> LAVA_BOATS =
            CyclingOption.create("speedrunnermod.options.lava_boats",
                    new TranslatableText("speedrunnermod.options.lava_boats.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().lavaBoats, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().lavaBoats = newValue);
    public static final CyclingOption<Boolean> NETHER_WATER =
            CyclingOption.create("speedrunnermod.options.nether_water",
                    new TranslatableText("speedrunnermod.options.nether_water.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().netherWater, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().netherWater = newValue);
    public static final CyclingOption<Boolean> BETTER_FOODS =
            CyclingOption.create("speedrunnermod.options.better_foods",
                    new TranslatableText("speedrunnermod.options.better_foods.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().betterFoods, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().betterFoods = newValue);
    public static final CyclingOption<Boolean> FALL_DAMAGE =
            CyclingOption.create("speedrunnermod.options.fall_damage",
                    new TranslatableText("speedrunnermod.options.fall_damage.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().fallDamage, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().fallDamage = newValue);
    public static final CyclingOption<Boolean> BLOCK_PARTICLES =
            CyclingOption.create("speedrunnermod.options.block_particles",
                    new TranslatableText("speedrunnermod.options.block_particles.tooltip"), gameOptions ->
                            SpeedrunnerModClient.clientOptions().blockParticles, (gameOptions, option, newValue) ->
                            SpeedrunnerModClient.clientOptions().blockParticles = newValue);
    public static final DoubleOption STRONGHOLD_DISTANCE =
            new DoubleOption("speedrunnermod.options.stronghold_distance",
                    3.0D, 64.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().strongholdDistance, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().strongholdDistance = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_distance") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_distance.tooltip"), 200));
    public static final DoubleOption STRONGHOLD_SPREAD =
            new DoubleOption("speedrunnermod.options.stronghold_spread",
                    2.0D, 32.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().strongholdSpread, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().strongholdSpread = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_spread") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_spread.tooltip"), 200));
    public static final DoubleOption STRONGHOLD_COUNT =
            new DoubleOption("speedrunnermod.options.stronghold_count",
                    4.0D, 156.0D, 4.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().strongholdCount, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().strongholdCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_count") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_count.tooltip"), 200));
    public static final DoubleOption STRONGHOLD_PORTAL_ROOM_COUNT =
            new DoubleOption("speedrunnermod.options.stronghold_portal_room_count",
                    1.0D, 3.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().strongholdPortalRoomCount, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().strongholdPortalRoomCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_portal_room_count") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_portal_room_count.tooltip"), 200));
    public static final DoubleOption STRONGHOLD_LIBRARY_COUNT =
            new DoubleOption("speedrunnermod.options.stronghold_library_count",
                    1.0D, 8.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().strongholdLibraryCount, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().strongholdLibraryCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_library_count") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_library_count.tooltip"), 200));
    public static final CyclingOption<ClientModOptions.ItemMessages> ITEM_MESSAGES =
            CyclingOption.create("speedrunnermod.options.item_messages",
                            ClientModOptions.ItemMessages.values(), (itemMessages) ->
                                    new TranslatableText(itemMessages.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerModClient.clientOptions().itemMessages, (gameOptions, option, itemMessages) ->
                                    SpeedrunnerModClient.clientOptions().itemMessages = itemMessages)
                    .tooltip(client -> itemMessages -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.item_messages.tooltip"), 200));
    public static final CyclingOption<ModOptions.MobSpawningRate> MOB_SPAWNING_RATE =
            CyclingOption.create("speedrunnermod.options.mob_spawning_rate",
                            ModOptions.MobSpawningRate.values(), (mobSpawningRate) ->
                                    new TranslatableText(mobSpawningRate.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerMod.options().mobSpawningRate, (gameOptions, option, mobSpawningRate) ->
                                    SpeedrunnerMod.options().mobSpawningRate = mobSpawningRate)
                    .tooltip(client -> (mobSpawningRate) -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mob_spawning_rate.tooltip"), 200));
    public static final CyclingOption<ClientModOptions.ModButtonType> MOD_BUTTON_TYPE =
            CyclingOption.create("speedrunnermod.options.mod_button_type",
                            ClientModOptions.ModButtonType.values(), (mobSpawningRate) ->
                                    new TranslatableText(mobSpawningRate.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerModClient.clientOptions().modButtonType, (gameOptions, option, modButtonType) ->
                                    SpeedrunnerModClient.clientOptions().modButtonType = modButtonType)
                    .tooltip(client -> modButtonType -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mod_button_type.tooltip"), 200));
    public static final CyclingOption<Boolean> SOCIAL_BUTTONS =
            CyclingOption.create("speedrunnermod.options.social_buttons",
                    new TranslatableText("speedrunnermod.options.social_buttons.tooltip"), gameOptions ->
                            SpeedrunnerModClient.clientOptions().socialButtons, (gameOptions, option, newValue) ->
                            SpeedrunnerModClient.clientOptions().socialButtons = newValue);
    public static final DoubleOption NETHER_PORTAL_COOLDOWN =
            new DoubleOption("speedrunnermod.options.nether_portal_cooldown",
                    0.0D, 20.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().netherPortalCooldown, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().netherPortalCooldown = newValue.intValue(), (gameOptions, option) ->
                    SpeedrunnerMod.options().netherPortalCooldown == 0 ?
                            new LiteralText(I18n.translate("speedrunnermod.options.portal_cooldown") + ": None") :
                            new LiteralText(I18n.translate("speedrunnermod.options.nether_portal_cooldown") + ": " + (int)option.get(gameOptions) + "s"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.nether_portal_cooldown.tooltip"), 200));
    public static final CyclingOption<Boolean> GLOBAL_NETHER_PORTALS =
            CyclingOption.create("speedrunnermod.options.global_nether_portals",
                    new TranslatableText("speedrunnermod.options.global_nether_portals.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().globalNetherPortals, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().globalNetherPortals = newValue);
    public static final CyclingOption<Boolean> BETTER_ANVIL =
            CyclingOption.create("speedrunnermod.options.better_anvil",
                    new TranslatableText("speedrunnermod.options.better_anvil.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().betterAnvil, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().betterAnvil = newValue);
    public static final DoubleOption ANVIL_COST_LIMIT =
            new DoubleOption("speedrunnermod.options.anvil_cost_limit",
                    1.0D, 50.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().anvilCostLimit, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().anvilCostLimit = newValue.intValue(), (gameOptions, option) ->
                    SpeedrunnerMod.options().anvilCostLimit == 50 ?
                            new LiteralText(I18n.translate("speedrunnermod.options.anvil_cost_limit") + ": No Limit") :
                            SpeedrunnerMod.options().anvilCostLimit == 1 ?
                                    new LiteralText(I18n.translate("speedrunnermod.options.anvil_cost_limit") + ": " + (int)option.get(gameOptions) + " level") :
                                    new LiteralText(I18n.translate("speedrunnermod.options.anvil_cost_limit") + ": " + (int)option.get(gameOptions) + " levels"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.anvil_cost_limit.tooltip"), 200));
    public static final CyclingOption<Boolean> HIGHER_ENCHANTMENT_LEVELS =
            CyclingOption.create("speedrunnermod.options.higher_enchantment_levels",
                    new TranslatableText("speedrunnermod.options.higher_enchantment_levels.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().higherEnchantmentLevels, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().higherEnchantmentLevels = newValue);
    public static final CyclingOption<Boolean> HIGHER_BREATH_TIME =
            CyclingOption.create("speedrunnermod.options.higher_breath_time",
                    new TranslatableText("speedrunnermod.options.higher_breath_time.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().higherBreathTime, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().higherBreathTime = newValue);
}