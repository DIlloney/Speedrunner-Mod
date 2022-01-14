package net.dilloney.speedrunnermod.client.gui.screen.option;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.option.CLModOptions;
import net.dilloney.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.BooleanOption;
import net.minecraft.client.options.CyclingOption;
import net.minecraft.client.options.DoubleOption;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ModOption {

    protected static final BooleanOption MAKE_STRUCTURES_MORE_COMMON;
    protected static final BooleanOption MAKE_BIOMES_MORE_COMMON;
    protected static final BooleanOption ICARUS_MODE;
    protected static final BooleanOption INFINITY_PEARL_MODE;
    public static final BooleanOption FOG;
    protected static final BooleanOption DOOM_MODE;
    protected static final BooleanOption KILL_GHAST_UPON_FIREBALL;
    protected static final BooleanOption MODIFIED_BLOCK_HARDNESS;
    protected static final DoubleOption STRONGHOLD_COUNT;
    protected static final DoubleOption DRAGON_PERCH_TIME;
    protected static final BooleanOption AUTO_CREATE_WORLD;
    protected static final CyclingOption WORLD_DIFFICULTY;
    protected static final BooleanOption ALLOW_CHEATS;
    protected static final CyclingOption MOB_SPAWNING_RATE;
    protected static final BooleanOption MODIFIED_FOODS;
    protected static final BooleanOption MODIFIED_ITEM_EFFECTS;
    protected static final BooleanOption MAKE_ORES_MORE_COMMON;
    protected static final DoubleOption MOB_SPAWNER_SPAWN_DURATION;
    protected static final DoubleOption STRONGHOLD_DISTANCE;
    protected static final CyclingOption MOD_BUTTON_TYPE;
    protected static final BooleanOption DEBUG_MODE;

    static {
        MAKE_STRUCTURES_MORE_COMMON = new BooleanOption("options.make_structures_more_common", new TranslatableText("options.make_structures_more_common.tooltip"), gameOptions -> SpeedrunnerMod.options().main.makeStructuresMoreCommon, (gameOptions, newValue) -> SpeedrunnerMod.options().main.makeStructuresMoreCommon = newValue);
        MAKE_BIOMES_MORE_COMMON = new BooleanOption("options.make_biomes_more_common", new TranslatableText("options.make_biomes_more_common.tooltip"), gameOptions -> SpeedrunnerMod.options().main.makeBiomesMoreCommon, (gameOptions, newValue) -> SpeedrunnerMod.options().main.makeBiomesMoreCommon = newValue);
        FOG = new BooleanOption("options.fog", gameOptions -> SpeedrunnerModClient.clOptions().fog, (gameOptions, newValue) -> {
            SpeedrunnerModClient.clOptions().fog = newValue;
            MinecraftClient.getInstance().worldRenderer.reload();
        });
        ICARUS_MODE = new BooleanOption("options.icarus_mode", new TranslatableText("options.icarus_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.iCarusMode, (gameOptions, newValue) -> SpeedrunnerMod.options().main.iCarusMode = newValue);
        INFINITY_PEARL_MODE = new BooleanOption("options.infini_pearl_mode", new TranslatableText("options.infini_pearl_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.infiniPearlMode, (gameOptions, newValue) -> SpeedrunnerMod.options().main.infiniPearlMode = newValue);
        DOOM_MODE = new BooleanOption("options.doom_mode", new TranslatableText("options.doom_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.doomMode, (gameOptions, newValue) -> SpeedrunnerMod.options().main.doomMode = newValue);
        KILL_GHAST_UPON_FIREBALL = new BooleanOption("options.kill_ghast_upon_fireball", new TranslatableText("options.kill_ghast_upon_fireball.tooltip"), gameOptions -> SpeedrunnerMod.options().main.killGhastUponFireball, (gameOptions, newValue) -> SpeedrunnerMod.options().main.killGhastUponFireball = newValue);
        MODIFIED_BLOCK_HARDNESS = new BooleanOption("options.modified_block_hardness", gameOptions -> SpeedrunnerMod.options().main.modifiedBlockHardness, (gameOptions, newValue) -> SpeedrunnerMod.options().main.modifiedBlockHardness = newValue);
        STRONGHOLD_COUNT = new DoubleOption("options.stronghold_count", 64.0D, 128.0D, 8.0F, gameOptions -> (double)SpeedrunnerMod.options().main.strongholdCount, (gameOptions, newValue) -> SpeedrunnerMod.options().main.strongholdCount = newValue.intValue(), (gameOptions, option) -> new LiteralText(I18n.translate("options.stronghold_count") + ": " + (int)option.get(gameOptions)));
        DRAGON_PERCH_TIME = new DoubleOption("options.dragon_perch_time", 20.0D, 90.0D, 1.0F, gameOptions -> (double)SpeedrunnerMod.options().main.dragonPerchTime, (gameOptions, newValue) -> SpeedrunnerMod.options().main.dragonPerchTime = newValue.intValue(), (gameOptions, option) -> SpeedrunnerMod.options().main.dragonPerchTime <= 20 ? new LiteralText(I18n.translate("options.dragon_perch_time") + ": OFF") : new LiteralText(I18n.translate("options.dragon_perch_time") + ": " + (int)option.get(gameOptions) + "s"));
        AUTO_CREATE_WORLD = new BooleanOption("options.auto_create_world", new TranslatableText("options.auto_create_world.tooltip"), gameOptions -> SpeedrunnerModClient.clOptions().autoCreateWorld, (gameOptions, newValue) -> SpeedrunnerModClient.clOptions().autoCreateWorld = newValue);
        WORLD_DIFFICULTY = new CyclingOption("options.world_difficulty", (gameOptions, newValue) -> SpeedrunnerModClient.clOptions().worldDifficulty = CLModOptions.WorldDifficulty.byId((SpeedrunnerModClient.clOptions().worldDifficulty.getId() + newValue) % 5), (gameOptions, option) -> {
            option.setTooltip(MinecraftClient.getInstance().textRenderer.wrapLines(new TranslatableText("options.world_difficulty.tooltip"), 200));
            return option.getGenericLabel(new TranslatableText(SpeedrunnerModClient.clOptions().worldDifficulty.getTranslationKey()));
        });
        ALLOW_CHEATS = new BooleanOption("options.allow_cheats", new TranslatableText("options.allow_cheats.tooltip"), gameOptions -> SpeedrunnerModClient.clOptions().allowCheats, (gameOptions, newValue) -> SpeedrunnerModClient.clOptions().allowCheats = newValue);
        MOB_SPAWNING_RATE = new CyclingOption("options.mob_spawning_rate", (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.mobSpawningRate = ModOptions.Advanced.MobSpawningRate.byId((SpeedrunnerMod.options().advanced.mobSpawningRate.getId() + newValue) % 3), (gameOptions, option) -> {
            option.setTooltip(MinecraftClient.getInstance().textRenderer.wrapLines(new TranslatableText("options.mob_spawning_rate.tooltip"), 200));
            return option.getGenericLabel(new TranslatableText(SpeedrunnerMod.options().advanced.mobSpawningRate.getTranslationKey()));
        });
        MODIFIED_FOODS = new BooleanOption("options.modified_foods", new TranslatableText("options.modified_foods.tooltip"), gameOptions -> SpeedrunnerMod.options().advanced.modifiedFoods, (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.modifiedFoods = newValue);
        MODIFIED_ITEM_EFFECTS = new BooleanOption("options.modified_item_effects", new TranslatableText("options.modified_item_effects.tooltip"), gameOptions -> SpeedrunnerMod.options().advanced.modifiedItemEffects, (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.modifiedItemEffects = newValue);
        MAKE_ORES_MORE_COMMON = new BooleanOption("options.make_ores_more_common", new TranslatableText("options.make_ores_more_common.tooltip"), gameOptions -> SpeedrunnerMod.options().advanced.makeOresMoreCommon, (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.makeOresMoreCommon = newValue);
        MOB_SPAWNER_SPAWN_DURATION = new DoubleOption("options.mob_spawner_spawn_duration", 40.0D, 80.0D, 5.0F, gameOptions -> (double)SpeedrunnerMod.options().advanced.mobSpawnerSpawnDuration, (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.mobSpawnerSpawnDuration = newValue.intValue(), (gameOptions, option) -> new LiteralText(I18n.translate("options.mob_spawner_spawn_duration") + ": " + (int)option.get(gameOptions) + "s"));
        STRONGHOLD_DISTANCE = new DoubleOption("options.stronghold_distance", 3.0D, 32.0D, 1.0F, gameOptions -> (double)SpeedrunnerMod.options().advanced.strongholdDistance, (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.strongholdDistance = newValue.intValue(), (gameOptions, option) -> new LiteralText(I18n.translate("options.stronghold_distance") + ": " + (int)option.get(gameOptions)));
        MOD_BUTTON_TYPE = new CyclingOption("options.mod_button_type", (gameOptions, newValue) -> SpeedrunnerModClient.clOptions().modButtonType = CLModOptions.ModButtonType.byId((SpeedrunnerModClient.clOptions().modButtonType.getId() + newValue) % 3), (gameOptions, option) -> option.getGenericLabel(new TranslatableText(SpeedrunnerModClient.clOptions().modButtonType.getTranslationKey())));
        DEBUG_MODE = new BooleanOption("options.debug_mode", new TranslatableText("options.debug_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().advanced.debugMode, (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.debugMode = newValue);
    }
}