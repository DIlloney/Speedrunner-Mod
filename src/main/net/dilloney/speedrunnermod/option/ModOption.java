package net.dilloney.speedrunnermod.option;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ModOption {

    public static final CyclingOption<Boolean> MAKE_STRUCTURES_MORE_COMMON = CyclingOption.create("options.make_structures_more_common", new TranslatableText("options.make_structures_more_common.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getMakeStructuresMoreCommon(), (gameOptions, option, newValue) -> SpeedrunnerMod.OPTIONS.setMakeStructuresMoreCommon(newValue));
    public static final CyclingOption<Boolean> ICARUS_MODE = CyclingOption.create("options.icarus_mode", new TranslatableText("options.icarus_mode.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getiCarusMode(), (gameOptions, option, newValue) -> SpeedrunnerMod.OPTIONS.setiCarusMode(newValue));
    public static final CyclingOption<Boolean> INFINITY_PEARL_MODE = CyclingOption.create("options.infini_pearl_mode", new TranslatableText("options.infini_pearl_mode.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getInfinityPearlMode(), (gameOptions, option, newValue) -> SpeedrunnerMod.OPTIONS.setInfiniPearlMode(newValue));
    public static final CyclingOption<Boolean> FOG = CyclingOption.create("options.fog", new TranslatableText("options.fog.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getFog(), (gameOptions, option, newValue) -> SpeedrunnerMod.OPTIONS.setFog(newValue));
    public static final CyclingOption<Boolean> TIMER = CyclingOption.create("options.timer", new TranslatableText("options.timer.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getTimer(), (gameOptions, option, newValue) -> SpeedrunnerMod.OPTIONS.setTimer(newValue));
    public static final CyclingOption<Boolean> DOOM_MODE = CyclingOption.create("options.doom_mode", new TranslatableText("options.doom_mode.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getDoomMode(), (gameOptions, option, newValue) -> SpeedrunnerMod.OPTIONS.setDoomMode(newValue));
    public static final CyclingOption<Boolean> MAKE_BIOMES_MORE_COMMON = CyclingOption.create("options.make_biomes_more_common", new TranslatableText("options.make_biomes_more_common.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getMakeBiomesMoreCommon(), (gameOptions, option, newValue) -> SpeedrunnerMod.OPTIONS.setMakeBiomesMoreCommon(newValue));
    public static final CyclingOption<Boolean> KILL_GHAST_UPON_FIREBALL = CyclingOption.create("options.kill_ghast_upon_fireball", new TranslatableText("options.kill_ghast_upon_fireball.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getKillGhastUponFireball(), (gameOptions, option, newValue) -> SpeedrunnerMod.OPTIONS.setKillGhastUponFireball(newValue));
    public static final DoubleOption STRONGHOLD_COUNT = new DoubleOption("options.stronghold_count", 64.0D, 128.0D, 8.0F, gameOptions -> (double)SpeedrunnerMod.OPTIONS.getStrongholdCount(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setStrongholdCount(newValue.intValue()), (gameOptions, option) -> {
        return new LiteralText(I18n.translate("options.stronghold_count") + ": " + (int)option.get(gameOptions));
    }, (client) -> {
        return client.textRenderer.wrapLines(new TranslatableText("options.stronghold_count.tooltip"), 200);
    });
    public static final DoubleOption DRAGON_PERCH_TIME = new DoubleOption("options.dragon_perch_time", 21.0D, 90.0D, 1.0F, gameOptions -> (double)SpeedrunnerMod.OPTIONS.getDragonPerchTime(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setDragonPerchTime(newValue.intValue()), (gameOptions, option) -> {
        return new LiteralText(I18n.translate("options.dragon_perch_time") + ": " + (int)option.get(gameOptions) + "s");
    }, (client) -> {
        return client.textRenderer.wrapLines(new TranslatableText("options.dragon_perch_time.tooltip"), 200);
    });
    public static final CyclingOption<Boolean> AUTO_CREATE_WORLD = CyclingOption.create("options.auto_create_world", new TranslatableText("options.auto_create_world.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getAutoCreateWorld(), (gameOptions, option, newValue) -> SpeedrunnerMod.OPTIONS.setAutoCreateWorld(newValue));
    public static final CyclingOption<ModOptions.WorldDifficulty> WORLD_DIFFICULTY = CyclingOption.create("options.world_difficulty", ModOptions.WorldDifficulty.values(), (worldDifficulty) -> new TranslatableText(worldDifficulty.getTranslationKey()), (gameOptions) -> SpeedrunnerMod.OPTIONS.worldDifficulty, (gameOptions, option, worldDifficulty) -> SpeedrunnerMod.OPTIONS.worldDifficulty = worldDifficulty).tooltip(client -> (worldDifficulty) -> client.textRenderer.wrapLines(new TranslatableText("options.world_difficulty.tooltip"), 200));
}