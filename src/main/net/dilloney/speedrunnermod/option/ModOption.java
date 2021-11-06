package net.dilloney.speedrunnermod.option;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
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

    public static final BooleanOption MAKE_STRUCTURES_MORE_COMMON = new BooleanOption("options.make_structures_more_common", new TranslatableText("options.make_structures_more_common.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getMakeStructuresMoreCommon(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setMakeStructuresMoreCommon(newValue));
    public static final BooleanOption ICARUS_MODE = new BooleanOption("options.icarus_mode", new TranslatableText("options.icarus_mode.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getiCarusMode(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setiCarusMode(newValue));
    public static final BooleanOption INFINITY_PEARL_MODE = new BooleanOption("options.infini_pearl_mode", new TranslatableText("options.infini_pearl_mode.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getInfinityPearlMode(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setInfiniPearlMode(newValue));
    public static final BooleanOption FOG = new BooleanOption("options.fog", new TranslatableText("options.fog.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getFog(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setFog(newValue));
    public static final BooleanOption TIMER = new BooleanOption("options.timer", new TranslatableText("options.timer.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getTimer(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setTimer(newValue));
    public static final BooleanOption DOOM_MODE = new BooleanOption("options.doom_mode", new TranslatableText("options.doom_mode.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getDoomMode(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setDoomMode(newValue));
    public static final BooleanOption MAKE_BIOMES_MORE_COMMON = new BooleanOption("options.make_biomes_more_common", new TranslatableText("options.make_biomes_more_common.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getMakeBiomesMoreCommon(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setMakeBiomesMoreCommon(newValue));
    public static final BooleanOption KILL_GHAST_UPON_FIREBALL = new BooleanOption("options.kill_ghast_upon_fireball", new TranslatableText("options.kill_ghast_upon_fireball.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getKillGhastUponFireball(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setKillGhastUponFireball(newValue));
    public static final DoubleOption STRONGHOLD_COUNT = new DoubleOption("options.stronghold_count", 64.0D, 128.0D, 8.0F, gameOptions -> (double)SpeedrunnerMod.OPTIONS.getStrongholdCount(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setStrongholdCount(newValue.intValue()), (gameOptions, option) -> {
        option.setTooltip(MinecraftClient.getInstance().textRenderer.wrapLines(new TranslatableText("options.stronghold_count.tooltip"), 200));
        return new LiteralText(I18n.translate("options.stronghold_count") + ": " + (int)option.get(gameOptions));
    });
    public static final DoubleOption DRAGON_PERCH_TIME = new DoubleOption("options.dragon_perch_time", 21.0D, 90.0D, 1.0F, gameOptions -> (double)SpeedrunnerMod.OPTIONS.getDragonPerchTime(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setDragonPerchTime(newValue.intValue()), (gameOptions, option) -> {
        option.setTooltip(MinecraftClient.getInstance().textRenderer.wrapLines(new TranslatableText("options.dragon_perch_time.tooltip"), 200));
        return new LiteralText(I18n.translate("options.dragon_perch_time") + ": " + (int)option.get(gameOptions) + "s");
    });
    public static final BooleanOption AUTO_CREATE_WORLD = new BooleanOption("options.auto_create_world", new TranslatableText("options.auto_create_world.tooltip"), gameOptions -> SpeedrunnerMod.OPTIONS.getAutoCreateWorld(), (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.setAutoCreateWorld(newValue));
    public static final CyclingOption WORLD_DIFFICULTY = new CyclingOption("options.world_difficulty", (gameOptions, newValue) -> SpeedrunnerMod.OPTIONS.worldDifficulty = ModOptions.WorldDifficulty.byId((SpeedrunnerMod.OPTIONS.worldDifficulty.getId() + newValue) % 5), (gameOptions, option) -> {
        option.setTooltip(MinecraftClient.getInstance().textRenderer.wrapLines(new TranslatableText("options.world_difficulty.tooltip"), 200));
        return option.getGenericLabel(new TranslatableText(SpeedrunnerMod.OPTIONS.worldDifficulty.getTranslationKey()));
    });
}