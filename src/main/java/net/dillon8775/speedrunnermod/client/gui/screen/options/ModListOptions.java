package net.dillon8775.speedrunnermod.client.gui.screen.options;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.option.ClientModOptions;
import net.dillon8775.speedrunnermod.option.ModOptions;
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
    protected static final CyclingOption<ModOptions.Main.StructureSpawnRate> STRUCTURE_SPAWN_RATE =
            CyclingOption.create("speedrunnermod.options.structure_spawn_rate",
                            ModOptions.Main.StructureSpawnRate.values(), (structureSpawnRate) ->
                                    new TranslatableText(structureSpawnRate.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerMod.options().main.structureSpawnRate, (gameOptions, option, structureSpawnRate) ->
                                    SpeedrunnerMod.options().main.structureSpawnRate = structureSpawnRate)
                    .tooltip(client -> structureSpawnRate ->
                            client.textRenderer.wrapLines(
                                    new TranslatableText("speedrunnermod.options.structure_spawn_rate.tooltip"), 200));

    protected static final CyclingOption<Boolean> FASTER_BLOCK_BREAKING =
            CyclingOption.create("speedrunnermod.options.faster_block_breaking",
                    new TranslatableText("speedrunnermod.options.faster_block_breaking.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.fasterBlockBreaking, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.fasterBlockBreaking = newValue);

    protected static final CyclingOption<Boolean> BETTER_BIOMES =
            CyclingOption.create("speedrunnermod.options.better_biomes",
                    new TranslatableText("speedrunnermod.options.better_biomes.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.betterBiomes, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.betterBiomes = newValue);

    protected static final CyclingOption<Boolean> ICARUS_MODE =
            CyclingOption.create("speedrunnermod.options.icarus_mode",
                    new TranslatableText("speedrunnermod.options.icarus_mode.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.iCarusMode, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.iCarusMode = newValue);

    protected static final CyclingOption<Boolean> FOG =
            CyclingOption.create("speedrunnermod.options.fog", gameOptions ->
                    SpeedrunnerModClient.clientOptions().fog, (gameOptions, option, newValue) -> {
                SpeedrunnerModClient.clientOptions().fog = newValue;
                MinecraftClient.getInstance().worldRenderer.reload();
            }).tooltip(client -> aBoolean -> client.textRenderer.wrapLines(
                    new TranslatableText("speedrunnermod.options.fog.tooltip"), 200));

    protected static final CyclingOption<Boolean> INFINITY_PEARL_MODE =
            CyclingOption.create("speedrunnermod.options.infini_pearl_mode",
                    new TranslatableText("speedrunnermod.options.infini_pearl_mode.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.infiniPearlMode, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.infiniPearlMode = newValue);

    protected static final CyclingOption<Boolean> ITEM_TOOLTIPS =
            CyclingOption.create("speedrunnermod.options.item_tooltips",
                    new TranslatableText("speedrunnermod.options.item_tooltips.tooltip"), gameOptions ->
                            SpeedrunnerModClient.clientOptions().itemTooltips, (gameOptions, option, newValue) ->
                            SpeedrunnerModClient.clientOptions().itemTooltips = newValue);

    protected static final CyclingOption<Boolean> DOOM_MODE =
            CyclingOption.create("speedrunnermod.options.doom_mode",
                    new TranslatableText("speedrunnermod.options.doom_mode.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.doomMode, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.doomMode = newValue);

    protected static final DoubleOption DRAGON_PERCH_TIME =
            new DoubleOption("speedrunnermod.options.dragon_perch_time",
                    20.0D, 90.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.dragonPerchTime, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.dragonPerchTime = newValue.intValue(), (gameOptions, option) ->
                    SpeedrunnerMod.options().main.dragonPerchTime <= 20 ?
                            new LiteralText(I18n.translate("speedrunnermod.options.dragon_perch_time") + ": OFF") :
                            new LiteralText(I18n.translate("speedrunnermod.options.dragon_perch_time") + ": " + (int)option.get(gameOptions) + "s"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.dragon_perch_time.tooltip"), 200));

    protected static final CyclingOption<Boolean> KILL_GHAST_ON_FIREBALL =
            CyclingOption.create("speedrunnermod.options.kill_ghast_on_fireball",
                    new TranslatableText("speedrunnermod.options.kill_ghast_on_fireball.tooltip"),
                    gameOptions -> SpeedrunnerMod.options().main.killGhastOnFireball, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.killGhastOnFireball = newValue);

    protected static final CyclingOption<Boolean> FIREPROOF_ITEMS =
            CyclingOption.create("speedrunnermod.options.fireproof_items",
                    new TranslatableText("speedrunnermod.options.fireproof_items.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().main.fireproofItems, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().main.fireproofItems = newValue);

    protected static final DoubleOption MAX_ITEM_COUNT =
            new DoubleOption("speedrunnermod.options.max_item_count",
                    4.0D, 1000.0D, 4.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().main.maxItemCount, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().main.maxItemCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.max_item_count") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.max_item_count.tooltip"), 200));

    protected static final CyclingOption<Boolean> FAST_WORLD_CREATION =
            CyclingOption.create("speedrunnermod.options.fast_world_creation", gameOptions ->
                    SpeedrunnerModClient.clientOptions().worldSettings.fastWorldCreation, (gameOptions, option, newValue) ->
                    SpeedrunnerModClient.clientOptions().worldSettings.fastWorldCreation = newValue);

    protected static final CyclingOption<ClientModOptions.GameMode> GAMEMODE =
            CyclingOption.create("speedrunnermod.options.gamemode",
                    ClientModOptions.GameMode.values(), (gameMode) ->
                            new TranslatableText(gameMode.getTranslationKey()), (gameOptions) ->
                            SpeedrunnerModClient.clientOptions().worldSettings.gameMode, (gameOptions, option, gameMode) ->
                            SpeedrunnerModClient.clientOptions().worldSettings.gameMode = gameMode);

    protected static final CyclingOption<ClientModOptions.Difficulty> DIFFICULTY =
            CyclingOption.create("speedrunnermod.options.difficulty",
                    ClientModOptions.Difficulty.values(), (difficulty) ->
                            new TranslatableText(difficulty.getTranslationKey()), (gameOptions) ->
                            SpeedrunnerModClient.clientOptions().worldSettings.difficulty, (gameOptions, option, difficulty) ->
                            SpeedrunnerModClient.clientOptions().worldSettings.difficulty = difficulty);

    protected static final CyclingOption<Boolean> ALLOW_CHEATS =
            CyclingOption.create("speedrunnermod.options.allow_cheats", gameOptions ->
                    SpeedrunnerModClient.clientOptions().worldSettings.allowCheats, (gameOptions, option, newValue) ->
                    SpeedrunnerModClient.clientOptions().worldSettings.allowCheats = newValue);

    protected static final CyclingOption<Boolean> GENERATE_SPEEDRUNNER_TREES =
            CyclingOption.create("speedrunnermod.options.generate_speedrunner_trees",
                    new TranslatableText("speedrunnermod.options.generate_speedrunner_trees.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().advanced.generateSpeedrunnerTrees, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().advanced.generateSpeedrunnerTrees = newValue);

    protected static final CyclingOption<ClientModOptions.Panorama> PANORAMA =
            CyclingOption.create("speedrunnermod.options.panorama",
                            ClientModOptions.Panorama.values(), (panorama) ->
                                    new TranslatableText(panorama.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerModClient.clientOptions().panorama, (gameOptions, option, panorama) ->
                                    SpeedrunnerModClient.clientOptions().panorama = panorama)
                    .tooltip(client -> (mobSpawningRate) -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.panorama.tooltip"), 200));

    protected static final DoubleOption MOB_SPAWNER_MINIMUM_SPAWN_DURATION =
            new DoubleOption("speedrunnermod.options.mob_spawner_minimum_spawn_duration",
                    5.0D, 40.0D, 5.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().advanced.mobSpawnerMinimumSpawnDuration, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().advanced.mobSpawnerMinimumSpawnDuration = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.mob_spawner_minimum_spawn_duration") + ": " + (int)option.get(gameOptions) + " seconds"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mob_spawner_minimum_spawn_duration.tooltip"), 200));

    protected static final DoubleOption MOB_SPAWNER_MAXIMUM_SPAWN_DURATION =
            new DoubleOption("speedrunnermod.options.mob_spawner_max_spawn_duration",
                    20.0D, 80.0D, 5.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().advanced.mobSpawnerMaximumSpawnDuration, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().advanced.mobSpawnerMaximumSpawnDuration = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.mob_spawner_maximum_spawn_duration") + ": " + (int)option.get(gameOptions) + " seconds"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mob_spawner_maximum_spawn_duration.tooltip"), 200));

    protected static final CyclingOption<Boolean> CUSTOM_BIOMES =
            CyclingOption.create("speedrunnermod.options.custom_biomes",
                    new TranslatableText("speedrunnermod.options.custom_biomes.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().advanced.customBiomes, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().advanced.customBiomes = newValue);

    protected static final CyclingOption<Boolean> COMMON_ORES =
            CyclingOption.create("speedrunnermod.options.common_ores",
                    new TranslatableText("speedrunnermod.options.common_ores.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().advanced.commonOres, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().advanced.commonOres = newValue);

    protected static final CyclingOption<Boolean> ALLOW_BOATS_IN_LAVA =
            CyclingOption.create("speedrunnermod.options.allow_boats_in_lava",
                    new TranslatableText("speedrunnermod.options.allow_boats_in_lava.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().advanced.allowBoatsInLava, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().advanced.allowBoatsInLava = newValue);

    protected static final CyclingOption<Boolean> ALLOW_WATER_IN_NETHER =
            CyclingOption.create("speedrunnermod.options.allow_water_in_nether",
                    new TranslatableText("speedrunnermod.options.allow_water_in_nether.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().advanced.allowWaterInNether, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().advanced.allowWaterInNether = newValue);

    protected static final CyclingOption<Boolean> BETTER_FOODS =
            CyclingOption.create("speedrunnermod.options.better_foods",
                    new TranslatableText("speedrunnermod.options.better_foods.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().advanced.betterFoods, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().advanced.betterFoods = newValue);

    protected static final CyclingOption<Boolean> BLOCK_PARTICLES =
            CyclingOption.create("speedrunnermod.options.block_particles",
                    new TranslatableText("speedrunnermod.options.block_particles.tooltip"), gameOptions ->
                            SpeedrunnerModClient.clientOptions().blockParticles, (gameOptions, option, newValue) ->
                            SpeedrunnerModClient.clientOptions().blockParticles = newValue);

    protected static final DoubleOption STRONGHOLD_DISTANCE =
            new DoubleOption("speedrunnermod.options.stronghold_distance",
                    2.0D, 32.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().advanced.strongholdDistance, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().advanced.strongholdDistance = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_distance") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_distance.tooltip"), 200));

    protected static final DoubleOption STRONGHOLD_SPREAD =
            new DoubleOption("speedrunnermod.options.stronghold_spread",
                    2.0D, 32.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().advanced.strongholdSpread, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().advanced.strongholdSpread = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_spread") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_spread.tooltip"), 200));

    protected static final DoubleOption STRONGHOLD_COUNT =
            new DoubleOption("speedrunnermod.options.stronghold_count",
                    64.0D, 128.0D, 8.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().advanced.strongholdCount, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().advanced.strongholdCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.stronghold_count") + ": " + (int)option.get(gameOptions)),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.stronghold_count.tooltip"), 200));

    protected static final CyclingOption<ClientModOptions.ItemMessages> ITEM_MESSAGES =
            CyclingOption.create("speedrunnermod.options.item_messages",
                            ClientModOptions.ItemMessages.values(), (itemMessages) ->
                                    new TranslatableText(itemMessages.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerModClient.clientOptions().itemMessages, (gameOptions, option, itemMessages) ->
                                    SpeedrunnerModClient.clientOptions().itemMessages = itemMessages)
                    .tooltip(client -> itemMessages -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.item_messages.tooltip"), 200));

    protected static final CyclingOption<ModOptions.Advanced.MobSpawningRate> MOB_SPAWNING_RATE =
            CyclingOption.create("speedrunnermod.options.mob_spawning_rate",
                            ModOptions.Advanced.MobSpawningRate.values(), (mobSpawningRate) ->
                                    new TranslatableText(mobSpawningRate.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerMod.options().advanced.mobSpawningRate, (gameOptions, option, mobSpawningRate) ->
                                    SpeedrunnerMod.options().advanced.mobSpawningRate = mobSpawningRate)
                    .tooltip(client -> (mobSpawningRate) -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mob_spawning_rate.tooltip"), 200));

    protected static final CyclingOption<ClientModOptions.ModButtonType> MOD_BUTTON_TYPE =
            CyclingOption.create("speedrunnermod.options.mod_button_type",
                            ClientModOptions.ModButtonType.values(), (mobSpawningRate) ->
                                    new TranslatableText(mobSpawningRate.getTranslationKey()), (gameOptions) ->
                                    SpeedrunnerModClient.clientOptions().modButtonType, (gameOptions, option, modButtonType) ->
                                    SpeedrunnerModClient.clientOptions().modButtonType = modButtonType)
                    .tooltip(client -> modButtonType -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.mod_button_type.tooltip"), 200));

    protected static final CyclingOption<Boolean> SOCIAL_BUTTONS =
            CyclingOption.create("speedrunnermod.options.social_buttons",
                    new TranslatableText("speedrunnermod.options.social_buttons.tooltip"), gameOptions ->
                            SpeedrunnerModClient.clientOptions().socialButtons, (gameOptions, option, newValue) ->
                            SpeedrunnerModClient.clientOptions().socialButtons = newValue);

    protected static final DoubleOption NETHER_PORTAL_COOLDOWN =
            new DoubleOption("speedrunnermod.options.nether_portal_cooldown",
                    0.0D, 20.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().advanced.netherPortalCooldown, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().advanced.netherPortalCooldown = newValue.intValue(), (gameOptions, option) ->
                    SpeedrunnerMod.options().advanced.netherPortalCooldown == 0 ?
                            new LiteralText(I18n.translate("speedrunnermod.options.portal_cooldown") + ": None") :
                            new LiteralText(I18n.translate("speedrunnermod.options.nether_portal_cooldown") + ": " + (int)option.get(gameOptions) + "s"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.nether_portal_cooldown.tooltip"), 200));

    protected static final CyclingOption<Boolean> GLOBAL_NETHER_PORTALS =
            CyclingOption.create("speedrunnermod.options.global_nether_portals",
                    new TranslatableText("speedrunnermod.options.global_nether_portals.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().advanced.globalNetherPortals, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().advanced.globalNetherPortals = newValue);

    protected static final CyclingOption<Boolean> BETTER_ANVIL =
            CyclingOption.create("speedrunnermod.options.better_anvil",
                    new TranslatableText("speedrunnermod.options.better_anvil.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().advanced.betterAnvil, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().advanced.betterAnvil = newValue);

    protected static final DoubleOption ANVIL_COST_LIMIT =
            new DoubleOption("speedrunnermod.options.anvil_cost_limit",
                    1.0D, 50.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().advanced.anvilCostLimit, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().advanced.anvilCostLimit = newValue.intValue(), (gameOptions, option) ->
                    SpeedrunnerMod.options().advanced.anvilCostLimit == 50 ?
                            new LiteralText(I18n.translate("speedrunnermod.options.anvil_cost_limit") + ": No Limit") :
                            SpeedrunnerMod.options().advanced.anvilCostLimit == 1 ?
                                    new LiteralText(I18n.translate("speedrunnermod.options.anvil_cost_limit") + ": " + (int)option.get(gameOptions) + " level") :
                                    new LiteralText(I18n.translate("speedrunnermod.options.anvil_cost_limit") + ": " + (int)option.get(gameOptions) + " levels"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.anvil_cost_limit.tooltip"), 200));

    protected static final CyclingOption<Boolean> HIGHER_ENCHANTMENT_LEVELS =
            CyclingOption.create("speedrunnermod.options.higher_enchantment_levels",
                    new TranslatableText("speedrunnermod.options.higher_enchantment_levels.tooltip"), gameOptions ->
                            SpeedrunnerMod.options().advanced.higherEnchantmentLevels, (gameOptions, option, newValue) ->
                            SpeedrunnerMod.options().advanced.higherEnchantmentLevels = newValue);

    protected static final DoubleOption PLAYER_BREATH_TIME =
            new DoubleOption("speedrunnermod.options.player_breath_time",
                    4.0D, 20.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().advanced.playerBreathTime, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().advanced.playerBreathTime = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.player_breath_time") + ": " + (int)option.get(gameOptions) + "s"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.player_breath_time.tooltip"), 200));

    protected static final DoubleOption CATCH_BREATH_TIME =
            new DoubleOption("speedrunnermod.options.catch_breath_time",
                    2.0D, 20.0D, 1.0F, gameOptions ->
                    (double)SpeedrunnerMod.options().advanced.catchBreathTime, (gameOptions, newValue) ->
                    SpeedrunnerMod.options().advanced.catchBreathTime = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("speedrunnermod.options.catch_breath_time") + ": " + (int)option.get(gameOptions) + "s"),
                    client -> client.textRenderer.wrapLines(
                            new TranslatableText("speedrunnermod.options.catch_breath_time.tooltip"), 200));
}