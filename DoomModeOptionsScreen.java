package com.dilloney.speedrunnermod.client;

import com.dilloney.speedrunnermod.option.ModOptions;
import com.dilloney.speedrunnermod.option.OptionsFileManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
class DoomModeOptionsScreen {

    protected static ConfigBuilder openScreen() {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new TranslatableText("speedrunnermod.doom_mode"));

        ModOptions options = OptionsFileManager.getMain();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.setSavingRunnable(() -> {
            OptionsFileManager.setMain(options);
        });

        ConfigCategory doommodeoptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.doom_mode"));

        doommodeoptions.addEntry(entryBuilder.startTextDescription(new LiteralText("Doom Mode has been removed from this version, but it will be re-added in v1.3.3.")).build());
        return builder;
    }

    protected static ConfigBuilder openWarningScreen() {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new TranslatableText("speedrunnermod.doom_mode"));

        ModOptions options = OptionsFileManager.getMain();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.setSavingRunnable(() -> {
            OptionsFileManager.setMain(options);
        });

        ConfigCategory doommodeoptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.doom_mode"));

        doommodeoptions.addEntry(entryBuilder.startTextDescription(new LiteralText("Doom Mode has been removed from this version, but it will be re-added in v1.3.3.")).build());
        return builder;
    }
}