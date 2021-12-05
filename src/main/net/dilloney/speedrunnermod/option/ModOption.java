package net.dilloney.speedrunnermod.option;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
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

    public static final BooleanOption COMMON_STRUCTURES = new BooleanOption("options.common_structures", new TranslatableText("options.common_structures.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getCommonStructures(), (gameOptions, newValue) -> SpeedrunnerMod.options().main.setCommonStructures(newValue));
    public static final BooleanOption COMMON_BIOMES = new BooleanOption("options.common_biomes", new TranslatableText("options.common_biomes.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getCommonBiomes(), (gameOptions, newValue) -> SpeedrunnerMod.options().main.setCommonBiomes(newValue));
    public static final BooleanOption ICARUS_MODE = new BooleanOption("options.icarus_mode", new TranslatableText("options.icarus_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getiCarusMode(), (gameOptions, newValue) -> SpeedrunnerMod.options().main.setiCarusMode(newValue));
    public static final BooleanOption INFINITY_PEARL_MODE = new BooleanOption("options.infini_pearl_mode", new TranslatableText("options.infini_pearl_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getInfinityPearlMode(), (gameOptions, newValue) -> SpeedrunnerMod.options().main.setInfiniPearlMode(newValue));
    public static final BooleanOption FOG = new BooleanOption("options.fog", gameOptions -> SpeedrunnerModClient.clOptions().getFog(), (gameOptions, newValue) -> {
        SpeedrunnerModClient.clOptions().setFog(newValue);
        MinecraftClient.getInstance().worldRenderer.reload();
    });
    public static final BooleanOption TIMER = new BooleanOption("options.timer", new TranslatableText("options.timer.tooltip"), gameOptions -> SpeedrunnerModClient.clOptions().getTimer(), (gameOptions, newValue) -> SpeedrunnerModClient.clOptions().setTimer(newValue));
    public static final BooleanOption DOOM_MODE = new BooleanOption("options.doom_mode", new TranslatableText("options.doom_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getDoomMode(), (gameOptions, newValue) -> SpeedrunnerMod.options().main.setDoomMode(newValue));
    public static final BooleanOption MODIFY_BLOCK_HARDNESS = new BooleanOption("options.modify_block_hardness", new TranslatableText("options.modify_block_hardness.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getModifyBlockHardness(), (gameOptions, newValue) -> SpeedrunnerMod.options().main.setModifyBlockHardness(newValue));
    public static final BooleanOption KILL_GHAST_UPON_FIREBALL = new BooleanOption("options.kill_ghast_upon_fireball", new TranslatableText("options.kill_ghast_upon_fireball.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getKillGhastUponFireball(), (gameOptions, newValue) -> SpeedrunnerMod.options().main.setKillGhastUponFireball(newValue));
    public static final DoubleOption STRONGHOLD_COUNT = new DoubleOption("options.stronghold_count", 64.0D, 128.0D, 8.0F, gameOptions -> (double)SpeedrunnerMod.options().main.getStrongholdCount(), (gameOptions, newValue) -> SpeedrunnerMod.options().main.setStrongholdCount(newValue.intValue()), (gameOptions, option) -> {
        option.setTooltip(MinecraftClient.getInstance().textRenderer.wrapLines(new TranslatableText("options.stronghold_count.tooltip"), 200));
        return new LiteralText(I18n.translate("options.stronghold_count") + ": " + (int)option.get(gameOptions));
    });
    public static final DoubleOption DRAGON_PERCH_TIME = new DoubleOption("options.dragon_perch_time", 20.0D, 90.0D, 1.0F, gameOptions -> (double)SpeedrunnerMod.options().main.getDragonPerchTime(), (gameOptions, newValue) -> SpeedrunnerMod.options().main.setDragonPerchTime(newValue.intValue()), (gameOptions, option) -> {
        option.setTooltip(MinecraftClient.getInstance().textRenderer.wrapLines(new TranslatableText("options.dragon_perch_time.tooltip"), 200));
        return SpeedrunnerMod.options().main.getDragonPerchTime() <= 20 ? new LiteralText(I18n.translate("options.dragon_perch_time") + ": OFF") : new LiteralText(I18n.translate("options.dragon_perch_time") + ": " + (int)option.get(gameOptions) + "s");
    });
    public static final BooleanOption AUTO_CREATE_WORLD = new BooleanOption("options.auto_create_world", new TranslatableText("options.auto_create_world.tooltip"), gameOptions -> SpeedrunnerModClient.clOptions().getAutoCreateWorld(), (gameOptions, newValue) -> SpeedrunnerModClient.clOptions().setAutoCreateWorld(newValue));
    public static final CyclingOption WORLD_DIFFICULTY = new CyclingOption("options.world_difficulty", (gameOptions, newValue) -> SpeedrunnerModClient.clOptions().worldDifficulty = clModOptions.WorldDifficulty.byId((SpeedrunnerModClient.clOptions().worldDifficulty.getId() + newValue) % 5), (gameOptions, option) -> {
        option.setTooltip(MinecraftClient.getInstance().textRenderer.wrapLines(new TranslatableText("options.world_difficulty.tooltip"), 200));
        return option.getGenericLabel(new TranslatableText(SpeedrunnerModClient.clOptions().worldDifficulty.getTranslationKey()));
    });
    public static final CyclingOption MOB_SPAWNING_RATE = new CyclingOption("options.mob_spawning_rate", (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.mobSpawningRate = ModOptions.Advanced.MobSpawningRate.byId((SpeedrunnerMod.options().advanced.mobSpawningRate.getId() + newValue) % 3), (gameOptions, option) -> {
        option.setTooltip(MinecraftClient.getInstance().textRenderer.wrapLines(new TranslatableText("options.mob_spawning_rate.tooltip"), 200));
        return option.getGenericLabel(new TranslatableText(SpeedrunnerMod.options().advanced.mobSpawningRate.getTranslationKey()));
    });
    public static final BooleanOption MODIFY_BIOMES = new BooleanOption("options.modify_biomes", new TranslatableText("options.modify_biomes.tooltip"), gameOptions -> SpeedrunnerMod.options().advanced.getModifyBiomes(), (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.setModifyBiomes(newValue));
    public static final BooleanOption COMMON_ORES = new BooleanOption("options.common_ores", new TranslatableText("options.common_ores.tooltip"), gameOptions -> SpeedrunnerMod.options().advanced.getCommonOres(), (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.setCommonOres(newValue));
    public static final DoubleOption STRONGHOLD_DISTANCE = new DoubleOption("options.stronghold_distance", 3.0D, 32.0D, 1.0F, gameOptions -> (double)SpeedrunnerMod.options().advanced.getStrongholdDistance(), (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.setStrongholdDistance(newValue.intValue()), (gameOptions, option) -> {
        option.setTooltip(MinecraftClient.getInstance().textRenderer.wrapLines(new TranslatableText("options.stronghold_distance.tooltip"), 200));
        return new LiteralText(I18n.translate("options.stronghold_distance") + ": " + (int)option.get(gameOptions));
    });
    public static final DoubleOption GAMMA = new DoubleOption("options.gamma", 0.0D, 5.0D, 0.5F, (gameOptions) -> gameOptions.gamma, (gameOptions, gamma) -> gameOptions.gamma = gamma, (gameOptions, option) -> {
        double d = option.getRatio(option.get(gameOptions));
        int i = (int)(d * 500.0D);
        if (i == 0) {
            return option.getGenericLabel(new TranslatableText("options.gamma.min"));
        } else if (i == 100) {
            return option.getGenericLabel(new TranslatableText("options.gamma.max"));
        } else if (i == 300) {
            return option.getGenericLabel(new TranslatableText("options.gamma.brighter"));
        } else {
            return i == 500 ? option.getGenericLabel(new TranslatableText("options.gamma.maximum")) : option.getGenericLabel(i);
        }
    });
}