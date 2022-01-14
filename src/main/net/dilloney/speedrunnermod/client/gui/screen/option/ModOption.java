package net.dilloney.speedrunnermod.client.gui.screen.option;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.option.CLModOptions;
import net.dilloney.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ModOption {

    protected static final CyclingOption<Boolean> MAKE_STRUCTURES_MORE_COMMON;
    protected static final CyclingOption<Boolean> MAKE_BIOMES_MORE_COMMON;
    protected static final CyclingOption<Boolean> ICARUS_MODE;
    protected static final CyclingOption<Boolean> INFINITY_PEARL_MODE;
    public static final CyclingOption<Boolean> FOG;
    protected static final CyclingOption<Boolean> DOOM_MODE;
    protected static final CyclingOption<Boolean> KILL_GHAST_UPON_FIREBALL;
    protected static final CyclingOption<Boolean> MODIFIED_BLOCK_HARDNESS;
    protected static final DoubleOption STRONGHOLD_COUNT;
    protected static final DoubleOption DRAGON_PERCH_TIME;
    protected static final CyclingOption<Boolean> AUTO_CREATE_WORLD;
    protected static final CyclingOption<CLModOptions.WorldDifficulty> WORLD_DIFFICULTY;
    protected static final CyclingOption<Boolean> ALLOW_CHEATS;
    protected static final CyclingOption<ModOptions.Advanced.MobSpawningRate> MOB_SPAWNING_RATE;
    protected static final CyclingOption<Boolean> MODIFIED_FOODS;
    protected static final CyclingOption<Boolean> MODIFIED_ITEM_EFFECTS;
    protected static final CyclingOption<Boolean> MAKE_ORES_MORE_COMMON;
    protected static final DoubleOption MOB_SPAWNER_SPAWN_DURATION;
    protected static final DoubleOption STRONGHOLD_DISTANCE;
    protected static final CyclingOption<CLModOptions.ModButtonType> MOD_BUTTON_TYPE;
    protected static final CyclingOption<Boolean> DEBUG_MODE;

    static {
        MAKE_STRUCTURES_MORE_COMMON = CyclingOption.create("options.make_structures_more_common", new TranslatableText("options.make_structures_more_common.tooltip"), gameOptions -> SpeedrunnerMod.options().main.makeStructuresMoreCommon, (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.makeStructuresMoreCommon = newValue);
        MAKE_BIOMES_MORE_COMMON = CyclingOption.create("options.make_biomes_more_common", new TranslatableText("options.make_biomes_more_common.tooltip"), gameOptions -> SpeedrunnerMod.options().main.makeBiomesMoreCommon, (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.makeBiomesMoreCommon = newValue);
        FOG = CyclingOption.create("options.fog", gameOptions -> SpeedrunnerModClient.clOptions().fog, (gameOptions, option, newValue) -> {
            SpeedrunnerModClient.clOptions().fog = newValue;
            MinecraftClient.getInstance().worldRenderer.reload();
        });
        ICARUS_MODE = CyclingOption.create("options.icarus_mode", new TranslatableText("options.icarus_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.iCarusMode, (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.iCarusMode = newValue);
        INFINITY_PEARL_MODE = CyclingOption.create("options.infini_pearl_mode", new TranslatableText("options.infini_pearl_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.infiniPearlMode, (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.infiniPearlMode = newValue);
        DOOM_MODE = CyclingOption.create("options.doom_mode", new TranslatableText("options.doom_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.doomMode, (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.doomMode = newValue);
        KILL_GHAST_UPON_FIREBALL = CyclingOption.create("options.kill_ghast_upon_fireball", new TranslatableText("options.kill_ghast_upon_fireball.tooltip"), gameOptions -> SpeedrunnerMod.options().main.killGhastUponFireball, (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.killGhastUponFireball = newValue);
        MODIFIED_BLOCK_HARDNESS = CyclingOption.create("options.modified_block_hardness", gameOptions -> SpeedrunnerMod.options().main.modifiedBlockHardness, (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.modifiedBlockHardness = newValue);
        STRONGHOLD_COUNT = new DoubleOption("options.stronghold_count", 64.0D, 128.0D, 8.0F, gameOptions -> (double)SpeedrunnerMod.options().main.strongholdCount, (gameOptions, newValue) -> SpeedrunnerMod.options().main.strongholdCount = newValue.intValue(), (gameOptions, option) -> new LiteralText(I18n.translate("options.stronghold_count") + ": " + (int)option.get(gameOptions)));
        DRAGON_PERCH_TIME = new DoubleOption("options.dragon_perch_time", 20.0D, 90.0D, 1.0F, gameOptions -> (double)SpeedrunnerMod.options().main.dragonPerchTime, (gameOptions, newValue) -> SpeedrunnerMod.options().main.dragonPerchTime = newValue.intValue(), (gameOptions, option) -> SpeedrunnerMod.options().main.dragonPerchTime <= 20 ? new LiteralText(I18n.translate("options.dragon_perch_time") + ": OFF") : new LiteralText(I18n.translate("options.dragon_perch_time") + ": " + (int)option.get(gameOptions) + "s"));
        AUTO_CREATE_WORLD = CyclingOption.create("options.auto_create_world", new TranslatableText("options.auto_create_world.tooltip"), gameOptions -> SpeedrunnerModClient.clOptions().autoCreateWorld, (gameOptions, option, newValue) -> SpeedrunnerModClient.clOptions().autoCreateWorld = newValue);
        WORLD_DIFFICULTY = CyclingOption.create("options.world_difficulty", CLModOptions.WorldDifficulty.values(), (worldDifficulty) -> new TranslatableText(worldDifficulty.getTranslationKey()), (gameOptions) -> SpeedrunnerModClient.clOptions().worldDifficulty, (gameOptions, option, worldDifficulty) -> SpeedrunnerModClient.clOptions().worldDifficulty = worldDifficulty).tooltip(client -> (worldDifficulty) -> client.textRenderer.wrapLines(new TranslatableText("options.world_difficulty.tooltip"), 200));
        ALLOW_CHEATS = CyclingOption.create("options.allow_cheats", new TranslatableText("options.allow_cheats.tooltip"), gameOptions -> SpeedrunnerModClient.clOptions().allowCheats, (gameOptions, option, newValue) -> SpeedrunnerModClient.clOptions().allowCheats = newValue);
        MOB_SPAWNING_RATE = CyclingOption.create("options.mob_spawning_rate", ModOptions.Advanced.MobSpawningRate.values(), (mobSpawningRate) -> new TranslatableText(mobSpawningRate.getTranslationKey()), (gameOptions) -> SpeedrunnerMod.options().advanced.mobSpawningRate, (gameOptions, option, mobSpawningRate) -> SpeedrunnerMod.options().advanced.mobSpawningRate = mobSpawningRate).tooltip(client -> (worldDifficulty) -> client.textRenderer.wrapLines(new TranslatableText("options.mob_spawning_rate.tooltip"), 200));
        MODIFIED_FOODS = CyclingOption.create("options.modified_foods", new TranslatableText("options.modified_foods.tooltip"), gameOptions -> SpeedrunnerMod.options().advanced.modifiedFoods, (gameOptions, option, newValue) -> SpeedrunnerMod.options().advanced.modifiedFoods = newValue);
        MODIFIED_ITEM_EFFECTS = CyclingOption.create("options.modified_item_effects", new TranslatableText("options.modified_item_effects.tooltip"), gameOptions -> SpeedrunnerMod.options().advanced.modifiedItemEffects, (gameOptions, option, newValue) -> SpeedrunnerMod.options().advanced.modifiedItemEffects = newValue);
        MAKE_ORES_MORE_COMMON = CyclingOption.create("options.make_ores_more_common", new TranslatableText("options.make_ores_more_common.tooltip"), gameOptions -> SpeedrunnerMod.options().advanced.makeOresMoreCommon, (gameOptions, option, newValue) -> SpeedrunnerMod.options().advanced.makeOresMoreCommon = newValue);
        MOB_SPAWNER_SPAWN_DURATION = new DoubleOption("options.mob_spawner_spawn_duration", 40.0D, 80.0D, 5.0F, gameOptions -> (double)SpeedrunnerMod.options().advanced.mobSpawnerSpawnDuration, (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.mobSpawnerSpawnDuration = newValue.intValue(), (gameOptions, option) -> new LiteralText(I18n.translate("options.mob_spawner_spawn_duration") + ": " + (int)option.get(gameOptions) + "s"));
        STRONGHOLD_DISTANCE = new DoubleOption("options.stronghold_distance", 3.0D, 32.0D, 1.0F, gameOptions -> (double)SpeedrunnerMod.options().advanced.strongholdDistance, (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.strongholdDistance = newValue.intValue(), (gameOptions, option) -> new LiteralText(I18n.translate("options.stronghold_distance") + ": " + (int)option.get(gameOptions)));
        MOD_BUTTON_TYPE = CyclingOption.create("options.mod_button_type", CLModOptions.ModButtonType.values(), (mobSpawningRate) -> new TranslatableText(mobSpawningRate.getTranslationKey()), (gameOptions) -> SpeedrunnerModClient.clOptions().modButtonType, (gameOptions, option, modButtonType) -> SpeedrunnerModClient.clOptions().modButtonType = modButtonType);
        DEBUG_MODE = CyclingOption.create("options.debug_mode", new TranslatableText("options.debug_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().advanced.debugMode, (gameOptions, option, newValue) -> SpeedrunnerMod.options().advanced.debugMode = newValue);
    }
}