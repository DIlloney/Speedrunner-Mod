package net.dilloney.speedrunnermod.option;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
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

    public static final CyclingOption<Boolean> COMMON_STRUCTURES = CyclingOption.create("options.common_structures", new TranslatableText("options.common_structures.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getCommonStructures(), (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.setCommonStructures(newValue));
    public static final CyclingOption<Boolean> COMMON_BIOMES = CyclingOption.create("options.common_biomes", new TranslatableText("options.common_biomes.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getCommonBiomes(), (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.setCommonBiomes(newValue));
    public static final CyclingOption<Boolean> ICARUS_MODE = CyclingOption.create("options.icarus_mode", new TranslatableText("options.icarus_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getiCarusMode(), (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.setiCarusMode(newValue));
    public static final CyclingOption<Boolean> INFINITY_PEARL_MODE = CyclingOption.create("options.infini_pearl_mode", new TranslatableText("options.infini_pearl_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getInfinityPearlMode(), (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.setInfiniPearlMode(newValue));
    public static final CyclingOption<Boolean> FOG = CyclingOption.create("options.fog", gameOptions -> SpeedrunnerModClient.clOptions().getFog(), (gameOptions, option, newValue) -> {
        SpeedrunnerModClient.clOptions().setFog(newValue);
        MinecraftClient.getInstance().worldRenderer.reload();
    });
    public static final CyclingOption<Boolean> TIMER = CyclingOption.create("options.timer", new TranslatableText("options.timer.tooltip"), gameOptions -> SpeedrunnerModClient.clOptions().getTimer(), (gameOptions, option, newValue) -> SpeedrunnerModClient.clOptions().setTimer(newValue));
    public static final CyclingOption<Boolean> DOOM_MODE = CyclingOption.create("options.doom_mode", new TranslatableText("options.doom_mode.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getDoomMode(), (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.setDoomMode(newValue));
    public static final CyclingOption<Boolean> MODIFY_BLOCK_HARDNESS = CyclingOption.create("options.modify_block_hardness", new TranslatableText("options.modify_block_hardness.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getModifyBlockHardness(), (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.setModifyBlockHardness(newValue));
    public static final CyclingOption<Boolean> KILL_GHAST_UPON_FIREBALL = CyclingOption.create("options.kill_ghast_upon_fireball", new TranslatableText("options.kill_ghast_upon_fireball.tooltip"), gameOptions -> SpeedrunnerMod.options().main.getKillGhastUponFireball(), (gameOptions, option, newValue) -> SpeedrunnerMod.options().main.setKillGhastUponFireball(newValue));
    public static final DoubleOption STRONGHOLD_COUNT = new DoubleOption("options.stronghold_count", 64.0D, 128.0D, 8.0F, gameOptions -> (double)SpeedrunnerMod.options().main.getStrongholdCount(), (gameOptions, newValue) -> SpeedrunnerMod.options().main.setStrongholdCount(newValue.intValue()), (gameOptions, option) -> new LiteralText(I18n.translate("options.stronghold_count") + ": " + (int)option.get(gameOptions)), (client) -> client.textRenderer.wrapLines(new TranslatableText("options.stronghold_count.tooltip"), 200));
    public static final DoubleOption DRAGON_PERCH_TIME = new DoubleOption("options.dragon_perch_time", 20.0D, 90.0D, 1.0F, gameOptions -> (double)SpeedrunnerMod.options().main.getDragonPerchTime(), (gameOptions, newValue) -> SpeedrunnerMod.options().main.setDragonPerchTime(newValue.intValue()), (gameOptions, option) -> SpeedrunnerMod.options().main.getDragonPerchTime() <= 20 ? new LiteralText(I18n.translate("options.dragon_perch_time") + ": OFF") : new LiteralText(I18n.translate("options.dragon_perch_time") + ": " + (int)option.get(gameOptions) + "s"), (client) -> client.textRenderer.wrapLines(new TranslatableText("options.dragon_perch_time.tooltip"), 200));
    public static final CyclingOption<Boolean> AUTO_CREATE_WORLD = CyclingOption.create("options.auto_create_world", new TranslatableText("options.auto_create_world.tooltip"), gameOptions -> SpeedrunnerModClient.clOptions().getAutoCreateWorld(), (gameOptions, option, newValue) -> SpeedrunnerModClient.clOptions().setAutoCreateWorld(newValue));
    public static final CyclingOption<clModOptions.WorldDifficulty> WORLD_DIFFICULTY = CyclingOption.create("options.world_difficulty", clModOptions.WorldDifficulty.values(), (worldDifficulty) -> new TranslatableText(worldDifficulty.getTranslationKey()), (gameOptions) -> SpeedrunnerModClient.clOptions().worldDifficulty, (gameOptions, option, worldDifficulty) -> SpeedrunnerModClient.clOptions().worldDifficulty = worldDifficulty).tooltip(client -> (worldDifficulty) -> client.textRenderer.wrapLines(new TranslatableText("options.world_difficulty.tooltip"), 200));
    public static final CyclingOption<ModOptions.Advanced.MobSpawningRate> MOB_SPAWNING_RATE = CyclingOption.create("options.mob_spawning_rate", ModOptions.Advanced.MobSpawningRate.values(), (mobSpawningRate) -> new TranslatableText(mobSpawningRate.getTranslationKey()), (gameOptions) -> SpeedrunnerMod.options().advanced.mobSpawningRate, (gameOptions, option, mobSpawningRate) -> SpeedrunnerMod.options().advanced.mobSpawningRate = mobSpawningRate).tooltip(client -> (worldDifficulty) -> client.textRenderer.wrapLines(new TranslatableText("options.mob_spawning_rate.tooltip"), 200));
    public static final CyclingOption<Boolean> MODIFY_BIOMES = CyclingOption.create("options.modify_biomes", new TranslatableText("options.modify_biomes.tooltip"), gameOptions -> SpeedrunnerMod.options().advanced.getModifyBiomes(), (gameOptions, option, newValue) -> SpeedrunnerMod.options().advanced.setModifyBiomes(newValue));
    public static final DoubleOption STRONGHOLD_DISTANCE = new DoubleOption("options.stronghold_distance", 3.0D, 32.0D, 1.0F, gameOptions -> (double)SpeedrunnerMod.options().advanced.getStrongholdDistance(), (gameOptions, newValue) -> SpeedrunnerMod.options().advanced.setStrongholdDistance(newValue.intValue()), (gameOptions, option) -> new LiteralText(I18n.translate("options.stronghold_distance") + ": " + (int)option.get(gameOptions)), (client) -> client.textRenderer.wrapLines(new TranslatableText("options.stronghold_distance.tooltip"), 200));
    public static final DoubleOption GAMMA = new DoubleOption("options.gamma", 0.0D, 5.0D, 0.5F, (gameOptions) -> gameOptions.gamma, (gameOptions, gamma) -> gameOptions.gamma = gamma, (gameOptions, option) -> {
        double d = option.getRatio(option.get(gameOptions));
        int i = (int)(d * 500.0D);
        if (i == 0) {
            return option.getGenericLabel(new TranslatableText("options.gamma.min"));
        } else if (i == 50) {
            return option.getGenericLabel(new TranslatableText("options.gamma.default"));
        } else if (i == 100) {
            return option.getGenericLabel(new TranslatableText("options.gamma.max"));
        } else if (i == 300) {
            return option.getGenericLabel(new TranslatableText("options.gamma.brighter"));
        } else {
            return i == 500 ? option.getGenericLabel(new TranslatableText("options.gamma.maximum")) : option.getGenericLabel(i);
        }
    });
}