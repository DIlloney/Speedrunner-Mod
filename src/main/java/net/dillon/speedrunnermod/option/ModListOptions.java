package net.dillon.speedrunnermod.option;

import com.mojang.serialization.Codec;
import net.dillon.speedrunnermod.client.util.InactiveableOption;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.client.util.TranslationStringKeys;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Arrays;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * All {@code "list"} options, which are used on the actual options screens to allow changing of these options.
 */
@Environment(EnvType.CLIENT)
public class ModListOptions {

    public static final SimpleOption<StructureSpawnRate> STRUCTURE_SPAWN_RATES =
            new SimpleOption<>("speedrunnermod.options.structure_spawn_rates", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(StructureSpawnRate.values()), Codec.INT.xmap(StructureSpawnRate::byId, StructureSpawnRate::getId)),
                    options().main.structureSpawnRates, value -> options().main.structureSpawnRates = value);

    public static final SimpleOption<ItemMessages> ITEM_MESSAGES =
            new SimpleOption<>("speedrunnermod.options.item_messages", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.item_messages.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ItemMessages.values()), Codec.INT.xmap(ItemMessages::byId, ItemMessages::getId)),
                    options().client.itemMessages, value -> options().client.itemMessages = value);

    public static final SimpleOption<MobSpawningRate> MOB_SPAWNING_RATE =
            new SimpleOption<>("speedrunnermod.options.mob_spawning_rate", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.mob_spawning_rate.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(MobSpawningRate.values()), Codec.INT.xmap(MobSpawningRate::byId, MobSpawningRate::getId)),
                    options().main.mobSpawningRate, value -> options().main.mobSpawningRate = value);

    public static final SimpleOption<GameMode> GAMEMODE =
            new SimpleOption<>("speedrunnermod.options.gamemode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.gamemode.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(GameMode.values()), Codec.INT.xmap(GameMode::byId, GameMode::getId)),
                    options().client.gameMode, value -> options().client.gameMode = value);

    public static final SimpleOption<Difficulty> DIFFICULTY =
            new SimpleOption<>("speedrunnermod.options.difficulty", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.difficulty.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(Difficulty.values()), Codec.INT.xmap(Difficulty::byId, Difficulty::getId)),
                    Difficulty.EASY, value -> options().client.difficulty = value);

    public static final SimpleOption<Boolean> STATE_OF_THE_ART_ITEMS = new SimpleOption<>("speedrunnermod.options.state_of_the_art_items", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.state_of_the_art_items.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue(), value -> options().stateOfTheArtItems.stateOfTheArtItems.set(value));

    public static final SimpleOption<Boolean> FASTER_BLOCK_BREAKING = new SimpleOption<>("speedrunnermod.options.faster_block_breaking", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.faster_block_breaking.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fasterBlockBreaking.getCurrentValue(), value -> options().main.fasterBlockBreaking.set(value));

    public static final SimpleOption<Boolean> ICARUS_MODE = new SimpleOption<>("speedrunnermod.options.icarus_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.icarus_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.iCarusMode.getCurrentValue(), value -> options().main.iCarusMode.set(value));

    public static final SimpleOption<Boolean> FOG = new SimpleOption<>("speedrunnermod.options.fog", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fog.tooltip")),
            (optionText, value) -> !options().mixins.backgroundRendererMixin.getCurrentValue() ? ModTexts.FEATURE_DISABLED : !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.fog.getCurrentValue(), value -> {
        options().client.fog.set(value);
        MinecraftClient.getInstance().worldRenderer.reload();
    });

    public static final SimpleOption<Boolean> INFINI_PEARL_MODE = new SimpleOption<>("speedrunnermod.options.infini_pearl_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.infini_pearl_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.infiniPearlMode.getCurrentValue(), value -> options().main.infiniPearlMode.set(value));

    @Deprecated
    public static final SimpleOption<Boolean> LEADERBOARDS_MODE = new SimpleOption<>("speedrunnermod.options.leaderboards_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.leaderboards_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.leaderboardsMode.getCurrentValue(), value -> options().main.leaderboardsMode.set(value));

    public static final SimpleOption<Boolean> ITEM_TOOLTIPS = new SimpleOption<>("speedrunnermod.options.item_tooltips", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.item_tooltips.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.itemTooltips.getCurrentValue(), value -> options().client.itemTooltips.set(value));

    public static final SimpleOption<Boolean> TEXTURE_TOOLTIPS = new SimpleOption<>("speedrunnermod.options.texture_tooltips", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.texture_tooltips.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.textureTooltips.getCurrentValue(), value -> options().client.textureTooltips.set(value));

    public static final SimpleOption<Boolean> DOOM_MODE = new SimpleOption<>("speedrunnermod.options.doom_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.doom_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.doomMode.getCurrentValue(), value -> options().main.doomMode.set(value));

    public static final SimpleOption<Boolean> KILL_GHAST_ON_FIREBALL = new SimpleOption<>("speedrunnermod.options.kill_ghast_on_fireball", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.kill_ghast_on_fireball.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.killGhastOnFireball.getCurrentValue(), value -> options().main.killGhastOnFireball.set(value));

    public static final SimpleOption<Boolean> BETTER_VILLAGER_TRADES = new SimpleOption<>("speedrunnermod.options.better_villager_trades", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_villager_trades.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterVillagerTrades.getCurrentValue(), value -> options().main.betterVillagerTrades.set(value));

    public static final SimpleOption<Boolean> FIREPROOF_ITEMS = new SimpleOption<>("speedrunnermod.options.fireproof_items", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fireproof_items.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fireproofItems.getCurrentValue(), value -> options().main.fireproofItems.set(value));

    public static final SimpleOption<Boolean> FASTER_SPAWNERS = new SimpleOption<>("speedrunnermod.options.faster_spawners", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.faster_spawners.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fasterSpawners.getCurrentValue(), value -> options().main.fasterSpawners.set(value));

    public static final SimpleOption<Boolean> CUSTOM_BIOMES_AND_CUSTOM_BIOME_FEATURES = new SimpleOption<>("speedrunnermod.options.custom_biomes_and_custom_biome_features", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.customBiomesAndCustomBiomeFeatures.getCurrentValue(), value -> options().main.customBiomesAndCustomBiomeFeatures.set(value));

    public static final SimpleOption<Boolean> COMMON_ORES = new SimpleOption<>("speedrunnermod.options.common_ores", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.common_ores.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.commonOres.getCurrentValue(), value -> options().main.commonOres.set(value));

    public static final SimpleOption<Boolean> LAVA_BOATS = new SimpleOption<>("speedrunnermod.options.lava_boats", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.lava_boats.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.lavaBoats.getCurrentValue(), value -> options().main.lavaBoats.set(value));

    public static final SimpleOption<Boolean> NETHER_WATER = new SimpleOption<>("speedrunnermod.options.nether_water", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.nether_water.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.netherWater.getCurrentValue(), value -> options().main.netherWater.set(value));

    public static final SimpleOption<Boolean> BETTER_FOODS = new SimpleOption<>("speedrunnermod.options.better_foods", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_foods.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterFoods.getCurrentValue(), value -> options().main.betterFoods.set(value));

    public static final SimpleOption<Boolean> BETTER_BIOMES = new SimpleOption<>("speedrunnermod.options.better_biomes", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_biomes.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterFoods.getCurrentValue(), value -> options().main.betterFoods.set(value));

    public static final SimpleOption<Boolean> FALL_DAMAGE = new SimpleOption<>("speedrunnermod.options.fall_damage", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fall_damage.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fallDamage.getCurrentValue(), value -> options().main.fallDamage.set(value));

    public static final SimpleOption<Boolean> SOCIAL_BUTTONS = new SimpleOption<>("speedrunnermod.options.social_buttons", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.social_buttons.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.socialButtons.getCurrentValue(), value -> options().client.socialButtons.set(value));

    public static final SimpleOption<Boolean> ARROWS_DESTROY_BEDS = new SimpleOption<>("speedrunnermod.options.arrows_destroy_beds", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.arrows_destroy_beds.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.arrowsDestroyBeds.getCurrentValue(), value -> options().main.arrowsDestroyBeds.set(value));

    public static final SimpleOption<Boolean> GLOBAL_NETHER_PORTALS = new SimpleOption<>("speedrunnermod.options.global_nether_portals", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.global_nether_portals.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.globalNetherPortals.getCurrentValue(), value -> options().main.globalNetherPortals.set(value));

    public static final SimpleOption<Boolean> BETTER_ANVIL = new SimpleOption<>("speedrunnermod.options.better_anvil", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_anvil.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterAnvil.getCurrentValue(), value -> options().main.betterAnvil.set(value));

    public static final SimpleOption<Boolean> HIGHER_ENCHANTMENT_LEVELS = new SimpleOption<>("speedrunnermod.options.higher_enchantment_levels", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.higher_enchantment_levels.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.higherEnchantmentLevels.getCurrentValue(), value -> options().main.higherEnchantmentLevels.set(value));

    public static final SimpleOption<Boolean> RIGHT_CLICK_TO_REMOVE_SILK_TOUCH = new SimpleOption<>("speedrunnermod.options.right_click_to_remove_silk_touch", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.right_click_to_remove_silk_touch.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.rightClickToRemoveSilkTouch.getCurrentValue(), value -> options().main.rightClickToRemoveSilkTouch.set(value));

    public static final SimpleOption<Boolean> CONFIRM_MESSAGES = new SimpleOption<>("speedrunnermod.options.confirm_messages", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.confirm_messages.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.confirmMessages.getCurrentValue(), value -> options().client.confirmMessages.set(value));

    public static final SimpleOption<Boolean> SHOW_DEATH_CORDS = new SimpleOption<>("speedrunnermod.options.show_death_cords", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.show_death_cords.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.showDeathCords.getCurrentValue(), value -> options().client.showDeathCords.set(value));

    public static final SimpleOption<Boolean> KINETIC_DAMAGE = new SimpleOption<>("speedrunnermod.options.kinetic_damage", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.kinetic_damage.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.kineticDamage.getCurrentValue(), value -> options().main.kineticDamage.set(value));

    public static final SimpleOption<Boolean> THROWABLE_FIREBALLS = new SimpleOption<>("speedrunnermod.options.throwable_fireballs", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.throwable_fireballs.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.throwableFireballs.getCurrentValue(), value -> options().main.throwableFireballs.set(value));

    public static final SimpleOption<Boolean> CUSTOM_DATA_GENERATION = new SimpleOption<>("speedrunnermod.options.custom_data_generation", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_data_generation.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.customDataGeneration.getCurrentValue(), value -> options().main.customDataGeneration.set(value));

    public static final SimpleOption<Boolean> FAST_WORLD_CREATION = new SimpleOption<>("speedrunnermod.options.fast_world_creation", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fast_world_creation.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.fastWorldCreation.getCurrentValue(), value -> options().client.fastWorldCreation.set(value));

    public static final SimpleOption<Boolean> ALLOW_CHEATS = new SimpleOption<>("speedrunnermod.options.allow_cheats", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.allow_cheats.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.allowCheats.getCurrentValue(), value -> options().client.allowCheats.set(value));

    public static final SimpleOption<Boolean> PANORAMA = new SimpleOption<>("speedrunnermod.options.custom_panorama", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_panorama.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.customPanorama.getCurrentValue(), value -> options().client.customPanorama.set(value));

    public static final SimpleOption<Boolean> MODIFIED_STRONGHOLD_GENERATION = new SimpleOption<>("speedrunnermod.options.modified_stronghold_generation", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.modified_stronghold_generation.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.modifiedStrongholdGeneration.getCurrentValue(), value -> options().advanced.modifiedStrongholdGeneration.set(value));

    public static final SimpleOption<Boolean> MODIFIED_STRONGHOLD_Y_GENERATION = new SimpleOption<>("speedrunnermod.options.modified_stronghold_y_generation", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.modified_stronghold_y_generation.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.modifiedStrongholdYGeneration.getCurrentValue(), value -> options().advanced.modifiedStrongholdYGeneration.set(value));

    public static final SimpleOption<Boolean> MODIFIED_NETHER_FORTRESS_GENERATION = new SimpleOption<>("speedrunnermod.options.modified_nether_fortress_generation", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.modified_nether_fortress_generation.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.modifiedNetherFortressGeneration.getCurrentValue(), value -> options().advanced.modifiedNetherFortressGeneration.set(value));

    public static final SimpleOption<Boolean> SHOW_RESET_BUTTON = new SimpleOption<>("speedrunnermod.options.show_reset_button", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.show_reset_button.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.showResetButton.getCurrentValue(), value -> options().advanced.showResetButton.set(value));

    public static final SimpleOption<Boolean> HIGHER_BREATH_TIME = new SimpleOption<>("speedrunnermod.options.higher_breath_time", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.higher_breath_time.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.higherBreathTime.getCurrentValue(), value -> options().advanced.higherBreathTime.set(value));

    public static final SimpleOption<Boolean> GENERATE_SPEEDRUNNER_WOOD = new SimpleOption<>("speedrunnermod.options.generate_speedrunner_wood", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.generate_speedrunner_wood.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.generateSpeedrunnerWood.getCurrentValue(), value -> options().advanced.generateSpeedrunnerWood.set(value));

    public static final SimpleOption<Boolean> LONGER_DRAGON_PERCH_STAY_TIME = new SimpleOption<>("speedrunnermod.options.longer_dragon_perch_stay_time", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.longer_dragon_perch_stay_time.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.longerDragonPerchStayTime.getCurrentValue(), value -> options().advanced.longerDragonPerchStayTime.set(value));

    public static final SimpleOption<Boolean> DECREASED_ZOMBIFIED_PIGLIN_SCARE_DISTANCE = new SimpleOption<>("speedrunnermod.options.decreased_zombified_piglin_scare_distance", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.decreased_zombified_piglin_scare_distance.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.decreasedZombifiedPiglinScareDistance.getCurrentValue(), value -> options().advanced.decreasedZombifiedPiglinScareDistance.set(value));

    public static final SimpleOption<Boolean> DRAGON_KILLS_NEARBY_HOSTILE_ENTITIES = new SimpleOption<>("speedrunnermod.options.dragon_kills_nearby_hostile_entities", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.dragon_kills_nearby_hostile_entities.tooltip")),
            (optionText, value) -> !value ? ModTexts.NO : ModTexts.YES, SimpleOption.BOOLEAN, options().advanced.dragonKillsNearbyHostileEntities.getCurrentValue(), value -> options().advanced.dragonKillsNearbyHostileEntities.set(value));

    public static final SimpleOption<Boolean> DRAGON_IMMUNITY_FROM_GIANT_AND_WITHER = new SimpleOption<>("speedrunnermod.options.dragon_immunity_from_giant_and_wither", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.dragon_immunity_from_giant_and_wither.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.dragonImmunityFromGiantAndWither.getCurrentValue(), value -> options().advanced.dragonImmunityFromGiantAndWither.set(value));

    public static final SimpleOption<Boolean> TERRABLENDER_SURFACE_RULE_DATA_MIXIN = new SimpleOption<>("speedrunnermod.options.terrablender_surface_rule_data_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.terrablender_surface_rule_data_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, options().mixins.terraBlenderSurfaceRuleDataMixin.getCurrentValue(), value -> options().mixins.terraBlenderSurfaceRuleDataMixin.set(value));

    public static final SimpleOption<Boolean> BACKGROUND_RENDERER_MIXIN = new SimpleOption<>("speedrunnermod.options.background_renderer_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.background_renderer_mixin.tooltip")),
                (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, options().mixins.backgroundRendererMixin.getCurrentValue(), value -> options().mixins.backgroundRendererMixin.set(value));

    public static final SimpleOption<Boolean> SIMPLE_OPTION_MIXIN = new SimpleOption<>("speedrunnermod.options.simple_option_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.simple_option_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, options().mixins.simpleOptionMixin.getCurrentValue(), value -> options().mixins.simpleOptionMixin.set(value));

    public static final SimpleOption<Boolean> LOGO_DRAWER_MIXIN = new SimpleOption<>("speedrunnermod.options.logo_drawer_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.logo_drawer_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, options().mixins.logoDrawerMixin.getCurrentValue(), value -> options().mixins.logoDrawerMixin.set(value));

    public static final SimpleOption<Boolean> RENDER_LAYERS_MIXIN = new SimpleOption<>("speedrunnermod.options.render_layers_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.render_layers_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, options().mixins.renderLayersMixin.getCurrentValue(), value -> options().mixins.renderLayersMixin.set(value));

    public static final SimpleOption<Integer> BLOCK_BREAKING_MULTIPLIER =
            new SimpleOption<>("speedrunnermod.options.block_breaking_multiplier", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.block_breaking_multiplier.tooltip")),
                    (optionText, value) -> {
                        if (value == 1) {
                            return GameOptions.getGenericValueText(optionText, ModTexts.OFF);
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal("x" + value).formatted(Formatting.AQUA));
                        }
                    },
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 3), options().main.blockBreakingMultiplier.getCurrentValue(), value -> options().main.blockBreakingMultiplier.set(value));

    public static final SimpleOption<Integer> DRAGON_PERCH_TIME =
            new SimpleOption<>("speedrunnermod.options.dragon_perch_time", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.dragon_perch_time.tooltip")),
                    (optionText, value) -> {
                        if (value == 9) {
                            return GameOptions.getGenericValueText(optionText, Text.literal("Instant").formatted(Formatting.AQUA));
                        } else if (value <= 8) {
                            return GameOptions.getGenericValueText(optionText, ModTexts.OFF);
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + " seconds").formatted(Formatting.AQUA));
                        }},
                    new SimpleOption.ValidatingIntSliderCallbacks(8, 90), options().main.dragonPerchTime.getCurrentValue(), value -> options().main.dragonPerchTime.set(value));

    public static final SimpleOption<Integer> STRONGHOLD_DISTANCE =
            new SimpleOption<>("speedrunnermod.options.stronghold_distance", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.stronghold_distance.tooltip")),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(3, 64), options().main.strongholdDistance.getCurrentValue(), value -> options().main.strongholdDistance.set(value));

    public static final SimpleOption<Integer> STRONGHOLD_SPREAD =
            new SimpleOption<>("speedrunnermod.options.stronghold_spread", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.stronghold_spread.tooltip")),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(2, 32), options().main.strongholdSpread.getCurrentValue(), value -> options().main.strongholdSpread.set(value));

    public static final SimpleOption<Integer> STRONGHOLD_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_count", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.stronghold_count.tooltip")),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(4, 156), options().main.strongholdCount.getCurrentValue(), value -> options().main.strongholdCount.set(value));

    public static final SimpleOption<Integer> STRONGHOLD_PORTAL_ROOM_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_portal_room_count", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.stronghold_portal_room_count.tooltip")),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 3), options().main.strongholdPortalRoomCount.getCurrentValue(), value -> options().main.strongholdPortalRoomCount.set(value));

    public static final SimpleOption<Integer> STRONGHOLD_LIBRARY_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_library_count", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.stronghold_library_count.tooltip")),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 8), options().main.strongholdLibraryCount.getCurrentValue(), value -> options().main.strongholdLibraryCount.set(value));

    public static final SimpleOption<Integer> NETHER_PORTAL_DELAY =
            new SimpleOption<>("speedrunnermod.options.nether_portal_delay", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.nether_portal_delay.tooltip")),
                    (optionText, value) -> {
                        if (value == -1) {
                            return GameOptions.getGenericValueText(optionText, Text.literal("Go by Gamerule").formatted(Formatting.GREEN));
                        } else if (value == 0) {
                            return GameOptions.getGenericValueText(optionText, Text.literal("None").formatted(Formatting.RED));
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + "s").formatted(Formatting.AQUA));
                        }},
                    new SimpleOption.ValidatingIntSliderCallbacks(-1, 20), options().main.netherPortalDelay.getCurrentValue(), value -> options().main.netherPortalDelay.set(value));

    public static final SimpleOption<Integer> ANVIL_COST_LIMIT =
            new SimpleOption<>("speedrunnermod.options.anvil_cost_limit", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.anvil_cost_limit.tooltip")),
                    (optionText, value) -> {
                        if (value == 50) {
                            return GameOptions.getGenericValueText(optionText, Text.literal("No Limit").formatted(Formatting.RED));
                        } else if (value == 1) {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + " level").formatted(Formatting.AQUA));
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + " levels").formatted(Formatting.AQUA));
                        }},
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 50), options().main.anvilCostLimit.getCurrentValue(), value -> options().main.anvilCostLimit.set(value));

    public static final SimpleOption<Integer> SPEEDRUNNERS_WASTELAND_BIOME_WEIGHT =
            new SimpleOption<>("speedrunnermod.options.speedrunners_wasteland_biome_weight", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.speedrunners_wasteland_biome_weight.tooltip")),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(2, 32), options().advanced.speedrunnersWastelandBiomeWeight.getCurrentValue(), value -> options().advanced.speedrunnersWastelandBiomeWeight.set(value));

    public static final SimpleOption<Integer> ENDER_EYE_BREAKING_COOLDOWN =
            new SimpleOption<>("speedrunnermod.options.ender_eye_breaking_cooldown", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.ender_eye_breaking_cooldown.tooltip")),
                    (optionText, value) -> GameOptions.getGenericValueText(optionText, Text.literal(value + " seconds")),
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 10), options().advanced.enderEyeBreakingCooldown.getCurrentValue() / 20, value -> options().advanced.enderEyeBreakingCooldown.set(value * 20));

    public static final SimpleOption<Integer> PIGLIN_AWAKENER_PIGLIN_COUNT =
            new SimpleOption<>("speedrunnermod.options.piglin_awakener_piglin_count", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.piglin_awakener_piglin_count.tooltip")),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(3, 25), options().advanced.piglinAwakenerPiglinCount.getCurrentValue(), value -> options().advanced.piglinAwakenerPiglinCount.set(value));

    public static final SimpleOption<Integer> ICARUS_FIREWORKS_INVENTORY_SLOT =
            new SimpleOption<>("speedrunnermod.options.icarus_fireworks_inventory_slot", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.icarus_fireworks_inventory_slot.tooltip")),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 36), options().advanced.iCarusFireworksInventorySlot.getCurrentValue(), value -> options().advanced.iCarusFireworksInventorySlot.set(value));

    public static final SimpleOption<Integer> INFINI_PEARL_INVENTORY_SLOT =
            new SimpleOption<>("speedrunnermod.options.infini_pearl_inventory_slot", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.infini_pearl_inventory_slot.tooltip")),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 36), options().advanced.infiniPearlInventorySlot.getCurrentValue(), value -> options().advanced.infiniPearlInventorySlot.set(value));

    public static final SimpleOption<Integer> FIREBALL_EXPLOSION_POWER =
            new SimpleOption<>("speedrunnermod.options.fireball_explosion_power", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fireball_explosion_power.tooltip")),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 10), options().advanced.fireballExplosionPower.getCurrentValue(), value -> options().advanced.fireballExplosionPower.set(value));

    /**
     * Returns a {@link SimpleOption} for a {@code State-Of-The-Art item.}
     */
    public static SimpleOption<Boolean> stateOfTheArtItem(String itemTranslationKey) {
        return new SimpleOption<>("speedrunnermod.options.state_of_the_art_items." + itemTranslationKey, options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue() ? SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.state_of_the_art_items." + itemTranslationKey + ".tooltip")) : SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.state_of_the_art_items.disabled")),
                (optionText, value) -> !value || !options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue() ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, defaultItemValue(itemTranslationKey), value -> {
            switch (itemTranslationKey) {
                case TranslationStringKeys.ANNUL_EYE -> options().stateOfTheArtItems.annulEye.set(value);
                case TranslationStringKeys.BLAZE_SPOTTER -> options().stateOfTheArtItems.blazeSpotter.set(value);
                case TranslationStringKeys.DRAGONS_PEARL -> options().stateOfTheArtItems.dragonsPearl.set(value);
                case TranslationStringKeys.DRAGONS_SWORD -> options().stateOfTheArtItems.dragonsSword.set(value);
                case TranslationStringKeys.ENDER_THRUSTER -> options().stateOfTheArtItems.enderThruster.set(value);
                case TranslationStringKeys.PIGLIN_AWAKENER -> options().stateOfTheArtItems.piglinAwakener.set(value);
                case TranslationStringKeys.RAID_ERADICATOR -> options().stateOfTheArtItems.raidEradicator.set(value);
            }
        });
    }

    /**
     * Returns the {@code default value} that the {@code ModListOption} should default to for {@code State-Of-The-Art items.}
     */
    private static boolean defaultItemValue(String itemTranslationKey) {
        switch (itemTranslationKey) {
            case TranslationStringKeys.ANNUL_EYE -> {
                return options().stateOfTheArtItems.annulEye.getCurrentValue();
            }
            case TranslationStringKeys.BLAZE_SPOTTER -> {
                return options().stateOfTheArtItems.blazeSpotter.getCurrentValue();
            }
            case TranslationStringKeys.DRAGONS_PEARL -> {
                return options().stateOfTheArtItems.dragonsPearl.getCurrentValue();
            }
            case TranslationStringKeys.DRAGONS_SWORD -> {
                return options().stateOfTheArtItems.dragonsSword.getCurrentValue();
            }
            case TranslationStringKeys.ENDER_THRUSTER -> {
                return options().stateOfTheArtItems.enderThruster.getCurrentValue();
            }
            case TranslationStringKeys.PIGLIN_AWAKENER -> {
                return options().stateOfTheArtItems.piglinAwakener.getCurrentValue();
            }
            case TranslationStringKeys.RAID_ERADICATOR -> {
                return options().stateOfTheArtItems.raidEradicator.getCurrentValue();
            }
            default -> {
                return options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue();
            }
        }
    }

    /**
     * Creates a new {@code Structure Spawn Rate option.}
     */
    public static SimpleOption<Integer> structureSpawnRate(String structure) {
        return new SimpleOption<>("speedrunnermod.options.structure_spawn_rates." + structure, options().main.structureSpawnRates.custom() ? SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates_description.tooltip")) : SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates.custom_required")),
                (optionText, value) -> ModListOptions.listIntegerText(optionText, structure),
                new SimpleOption.ValidatingIntSliderCallbacks(3, 24), defaultStructureValue(structure), value -> determineValue(structure, value));
    }

    /**
     * Sets the values for each respective structure setting.
     */
    private static void determineValue(String structure, int value) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> setValue(options().structureSpawnRates.ancientCities, value);
            case TranslationStringKeys.VILLAGE -> setValue(options().structureSpawnRates.villages, value);
            case TranslationStringKeys.DESERT_PYRAMID -> setValue(options().structureSpawnRates.desertPyramids, value);
            case TranslationStringKeys.JUNGLE_PYRAMID -> setValue(options().structureSpawnRates.junglePyramids, value);
            case TranslationStringKeys.PILLAGER_OUTPOST -> setValue(options().structureSpawnRates.pillagerOutposts, value);
            case TranslationStringKeys.END_CITY -> setValue(options().structureSpawnRates.endCities, value);
            case TranslationStringKeys.WOODLAND_MANSION -> setValue(options().structureSpawnRates.woodlandMansions, value);
            case TranslationStringKeys.RUINED_PORTAL -> setValue(options().structureSpawnRates.ruinedPortals, value);
            case TranslationStringKeys.SHIPWRECK -> setValue(options().structureSpawnRates.shipwrecks, value);
            case TranslationStringKeys.TRIAL_CHAMBER -> setValue(options().structureSpawnRates.trialChambers, value);
            case TranslationStringKeys.NETHER_COMPLEXES -> setValue(options().structureSpawnRates.netherComplexes, value);
        }
    }

    /**
     * Bounds the value of the {@link SimpleOption} to the {@code spacing value} of the structure, and then sets the separate value to that divided by 2.
     */
    private static void setValue(int[] option, int value) {
        option[0] = value;
        option[1] = option[0] / 2;
    }

    /**
     * Returns the text that should be displayed on the {@link SimpleOption}.
     */
    private static Text listIntegerText(Text prefix, String structure) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.ancientCities[0] + ", " + options().structureSpawnRates.ancientCities[1]));
            }
            case TranslationStringKeys.DESERT_PYRAMID -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.desertPyramids[0] + ", " + options().structureSpawnRates.desertPyramids[1]));
            }
            case TranslationStringKeys.JUNGLE_PYRAMID -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.junglePyramids[0] + ", " + options().structureSpawnRates.junglePyramids[1]));
            }
            case TranslationStringKeys.PILLAGER_OUTPOST -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.pillagerOutposts[0] + ", " + options().structureSpawnRates.pillagerOutposts[1]));
            }
            case TranslationStringKeys.END_CITY -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.endCities[0] + ", " + options().structureSpawnRates.endCities[1]));
            }
            case TranslationStringKeys.WOODLAND_MANSION -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.woodlandMansions[0] + ", " + options().structureSpawnRates.woodlandMansions[1]));
            }
            case TranslationStringKeys.RUINED_PORTAL -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.ruinedPortals[0] + ", " + options().structureSpawnRates.ruinedPortals[1]));
            }
            case TranslationStringKeys.SHIPWRECK -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.shipwrecks[0] + ", " + options().structureSpawnRates.shipwrecks[1]));
            }
            case TranslationStringKeys.TRIAL_CHAMBER -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.trialChambers[0] + ", " + options().structureSpawnRates.trialChambers[1]));
            }
            case TranslationStringKeys.NETHER_COMPLEXES -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.netherComplexes[0] + ", " + options().structureSpawnRates.netherComplexes[1]));
            }
            default -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.villages[0] + ", " + options().structureSpawnRates.villages[1]));
            }
        }
    }

    /**
     * Returns the {@code default spacing value} that the respective {@link SimpleOption} should return when loading into the game.
     */
    private static int defaultStructureValue(String structure) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> {
                return options().structureSpawnRates.ancientCities[0];
            }
            case TranslationStringKeys.DESERT_PYRAMID -> {
                return options().structureSpawnRates.desertPyramids[0];
            }
            case TranslationStringKeys.JUNGLE_PYRAMID -> {
                return options().structureSpawnRates.junglePyramids[0];
            }
            case TranslationStringKeys.PILLAGER_OUTPOST -> {
                return options().structureSpawnRates.pillagerOutposts[0];
            }
            case TranslationStringKeys.END_CITY -> {
                return options().structureSpawnRates.endCities[0];
            }
            case TranslationStringKeys.WOODLAND_MANSION -> {
                return options().structureSpawnRates.woodlandMansions[0];
            }
            case TranslationStringKeys.RUINED_PORTAL -> {
                return options().structureSpawnRates.ruinedPortals[0];
            }
            case TranslationStringKeys.SHIPWRECK -> {
                return options().structureSpawnRates.shipwrecks[0];
            }
            case TranslationStringKeys.TRIAL_CHAMBER -> {
                return options().structureSpawnRates.trialChambers[0];
            }
            case TranslationStringKeys.NETHER_COMPLEXES -> {
                return options().structureSpawnRates.netherComplexes[0];
            }
            default -> {
                return options().structureSpawnRates.villages[0];
            }
        }
    }

    /**
     * Returns the generic value text prefix, with aqua formatting.
     */
    private static Text getGenericValueText(Text prefix, int value) {
        return GameOptions.getGenericValueText(prefix, Text.literal(Integer.toString(value)).formatted(Formatting.AQUA));
    }

    /**
     * {@code "Inactivable"} options, which are buttons that can be disabled, or grayed out under certain conditions.
     */
    public static class Inactiveable {

        @InactiveableOption
        public static final SimpleOption<GameMode> IAO_GAMEMODE =
                new SimpleOption<>("speedrunnermod.options.gamemode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fwc_required")), SimpleOption.enumValueText(),
                        new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(GameMode.values()), Codec.INT.xmap(GameMode::byId, GameMode::getId)),
                        options().client.gameMode, value -> options().client.gameMode = value);

        @InactiveableOption
        public static final SimpleOption<Difficulty> IAO_DIFFICULTY =
                new SimpleOption<>("speedrunnermod.options.difficulty", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fwc_required")), SimpleOption.enumValueText(),
                        new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(Difficulty.values()), Codec.INT.xmap(Difficulty::byId, Difficulty::getId)),
                        Difficulty.EASY, value -> options().client.difficulty = value);

        @InactiveableOption
        public static final SimpleOption<Boolean> IAO_FOG = new SimpleOption<>("speedrunnermod.options.fog", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.apply_fog_mixin_required")),
                (optionText, value) -> !options().mixins.backgroundRendererMixin.getCurrentValue() ? ModTexts.FEATURE_DISABLED : !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.fog.getCurrentValue(), value -> {
            options().client.fog.set(value);
            MinecraftClient.getInstance().worldRenderer.reload();
        });

        @InactiveableOption
        public static final SimpleOption<Boolean> IAO_ALLOW_CHEATS = new SimpleOption<>("speedrunnermod.options.allow_cheats", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fwc_required")),
                (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.allowCheats.getCurrentValue(), value -> options().client.allowCheats.set(value));

        @InactiveableOption
        public static final SimpleOption<Boolean> IAO_DRAGON_IMMUNITY_FROM_GIANT_AND_WITHER = new SimpleOption<>("speedrunnermod.options.dragon_immunity_from_giant_and_wither", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.doom_mode_required")),
                (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.dragonImmunityFromGiantAndWither.getCurrentValue(), value -> options().advanced.dragonImmunityFromGiantAndWither.set(value));

        @InactiveableOption
        public static final SimpleOption<Integer> IAO_PIGLIN_AWAKENER_PIGLIN_COUNT =
                new SimpleOption<>("speedrunnermod.options.piglin_awakener_piglin_count", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.state_of_the_art_items_required")),
                        ModListOptions::getGenericValueText,
                        new SimpleOption.ValidatingIntSliderCallbacks(3, 25), options().advanced.piglinAwakenerPiglinCount.getCurrentValue(), value -> options().advanced.piglinAwakenerPiglinCount.set(value));

        @InactiveableOption
        public static final SimpleOption<Integer> IAO_ICARUS_FIREWORKS_INVENTORY_SLOT =
                new SimpleOption<>("speedrunnermod.options.icarus_fireworks_inventory_slot", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.icarus_mode_required")),
                        ModListOptions::getGenericValueText,
                        new SimpleOption.ValidatingIntSliderCallbacks(1, 36), options().advanced.iCarusFireworksInventorySlot.getCurrentValue(), value -> options().advanced.iCarusFireworksInventorySlot.set(value));

        @InactiveableOption
        public static final SimpleOption<Integer> IAO_INFINI_PEARL_INVENTORY_SLOT =
                new SimpleOption<>("speedrunnermod.options.infini_pearl_inventory_slot", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.infini_pearl_mode_required")),
                        ModListOptions::getGenericValueText,
                        new SimpleOption.ValidatingIntSliderCallbacks(1, 36), options().advanced.infiniPearlInventorySlot.getCurrentValue(), value -> options().advanced.infiniPearlInventorySlot.set(value));

        @InactiveableOption
        public static final SimpleOption<Integer> IAO_FIREBALL_EXPLOSION_POWER =
                new SimpleOption<>("speedrunnermod.options.fireball_explosion_power", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.throwable_fireballs_required")),
                        ModListOptions::getGenericValueText,
                        new SimpleOption.ValidatingIntSliderCallbacks(1, 10), options().advanced.fireballExplosionPower.getCurrentValue(), value -> options().advanced.fireballExplosionPower.set(value));
    }
}