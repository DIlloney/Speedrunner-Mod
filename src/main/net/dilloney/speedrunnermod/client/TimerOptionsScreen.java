package net.dilloney.speedrunnermod.client;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.dilloney.speedrunnermod.option.ModOptions;
import net.dilloney.speedrunnermod.option.OptionsFileManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class TimerOptionsScreen {

    protected static ConfigBuilder openScreen() {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new TranslatableText("speedrunnermod.timer"));

        ModOptions.TimerOptions options = OptionsFileManager.TimerFileManager.getTimer();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.setSavingRunnable(() -> {
            OptionsFileManager.TimerFileManager.setTimer(options);
        });

        ConfigCategory timeroptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.timer"));
        timeroptions.addEntry(entryBuilder.startIntField(new TranslatableText("toptions.xoffset"), options.xOffset).setDefaultValue(options.xOffset).setMin(1).requireRestart().setSaveConsumer(newValue -> options.xOffset = newValue).build());
        timeroptions.addEntry(entryBuilder.startIntField(new TranslatableText("toptions.yoffset"), options.yOffset).setDefaultValue(options.yOffset).setMin(1).requireRestart().setSaveConsumer(newValue -> options.yOffset = newValue).build());
        timeroptions.addEntry(entryBuilder.startDoubleField(new TranslatableText("toptions.background_transparency"), options.backgroundTransparency).setDefaultValue(options.backgroundTransparency).setMin(0.0).requireRestart().setSaveConsumer(newValue -> options.backgroundTransparency = newValue).build());
        timeroptions.addEntry(entryBuilder.startColorField(new TranslatableText("toptions.background_color"), options.backgroundColor).setDefaultValue(options.backgroundColor).requireRestart().setSaveConsumer(newValue -> options.backgroundColor = newValue).build());
        timeroptions.addEntry(entryBuilder.startColorField(new TranslatableText("toptions.game_time_label_color"), options.gameTimeLabelColor).setDefaultValue(options.gameTimeLabelColor).requireRestart().setSaveConsumer(newValue -> options.gameTimeLabelColor = newValue).build());
        timeroptions.addEntry(entryBuilder.startColorField(new TranslatableText("toptions.real_time_label_color"), options.realTimeLabelColor).setDefaultValue(options.realTimeLabelColor).requireRestart().setSaveConsumer(newValue -> options.realTimeLabelColor = newValue).build());
        timeroptions.addEntry(entryBuilder.startColorField(new TranslatableText("toptions.overworld_split_color"), options.overworldSplitColor).setDefaultValue(options.overworldSplitColor).requireRestart().setSaveConsumer(newValue -> options.overworldSplitColor = newValue).build());
        timeroptions.addEntry(entryBuilder.startColorField(new TranslatableText("toptions.nether_split_color"), options.netherSplitColor).setDefaultValue(options.netherSplitColor).requireRestart().setSaveConsumer(newValue -> options.netherSplitColor = newValue).build());
        timeroptions.addEntry(entryBuilder.startColorField(new TranslatableText("toptions.stronghold_split_color"), options.strongholdSplitColor).setDefaultValue(options.strongholdSplitColor).requireRestart().setSaveConsumer(newValue -> options.strongholdSplitColor = newValue).build());
        timeroptions.addEntry(entryBuilder.startColorField(new TranslatableText("toptions.finished_split_color"), options.finishedSplitColor).setDefaultValue(options.finishedSplitColor).requireRestart().setSaveConsumer(newValue -> options.finishedSplitColor = newValue).build());
        timeroptions.addEntry(entryBuilder.startColorField(new TranslatableText("toptions.seed_color"), options.seedColor).setDefaultValue(options.seedColor).requireRestart().setSaveConsumer(newValue -> options.seedColor = newValue).build());
        timeroptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("toptions.bold_text"), options.boldText).setDefaultValue(options.boldText).setSaveConsumer(newValue -> options.boldText = newValue).build());
        timeroptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("toptions.show_seed"), options.showSeed).setDefaultValue(options.showSeed).requireRestart().setSaveConsumer(newValue -> options.showSeed = newValue).build());
        timeroptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("toptions.show_compare_splits"), options.showCompareSplits).setDefaultValue(options.showCompareSplits).requireRestart().setSaveConsumer(newValue -> options.showCompareSplits = newValue).build());
        timeroptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("toptions.use_best_splits"), options.useBestSplits).setDefaultValue(options.useBestSplits).requireRestart().setSaveConsumer(newValue -> options.useBestSplits = newValue).build());
        return builder;
    }

    protected static ConfigBuilder openWarningScreen() {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new TranslatableText("speedrunnermod.timer"));

        ModOptions.TimerOptions options = OptionsFileManager.TimerFileManager.getTimer();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.setSavingRunnable(() -> {
            OptionsFileManager.TimerFileManager.setTimer(options);
        });

        ConfigCategory timeroptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.timer"));
        timeroptions.addEntry(entryBuilder.startTextDescription(new LiteralText("You need to turn ON the Timer before modifying the settings!")).build());
        return builder;
    }
}