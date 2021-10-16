package net.dilloney.speedrunnermod.client;

import net.dilloney.speedrunnermod.option.ModOptions;
import net.dilloney.speedrunnermod.option.OptionsFileManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
class ModOptionsScreen {

    protected static ConfigBuilder openScreen() {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new TranslatableText("speedrunnermod.title.options"));

        ModOptions options = OptionsFileManager.getMain();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.setSavingRunnable(() -> {
            OptionsFileManager.setMain(options);
        });

        ConfigCategory speedrunnermodoptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.title.options"));

        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.make_structures_more_common"), options.makeStructuresMoreCommon).setDefaultValue(options.makeStructuresMoreCommon).setTooltip(new TranslatableText("options.make_structures_more_common.tooltip")).requireRestart().setSaveConsumer(newValue -> options.makeStructuresMoreCommon = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.icarus_mode"), options.iCarusMode).setDefaultValue(options.iCarusMode).setTooltip(new TranslatableText("options.icarus_mode.tooltip")).setSaveConsumer(newValue -> options.iCarusMode = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.infini_pearl_mode"), options.infiniPearlMode).setDefaultValue(options.infiniPearlMode).setTooltip(new TranslatableText("options.infini_pearl_mode.tooltip")).setSaveConsumer(newValue -> options.infiniPearlMode = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.fog"), options.fog).setDefaultValue(options.fog).setTooltip(new TranslatableText("options.fog.tooltip")).setSaveConsumer(newValue -> options.fog = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.doom_mode"), options.doomMode).setDefaultValue(options.doomMode).setTooltip(new TranslatableText("options.doom_mode.tooltip")).requireRestart().setSaveConsumer(newValue -> options.doomMode = newValue).build());
        return builder;
    }
}