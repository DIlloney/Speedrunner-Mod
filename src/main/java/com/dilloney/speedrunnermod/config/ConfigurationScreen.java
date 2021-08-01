package com.dilloney.speedrunnermod.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Config(name = "speedrunnermod")
@Environment(EnvType.CLIENT)
public class ConfigurationScreen {

    public static ConfigBuilder getConfigBuilder() {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new LiteralText("Speedrunner Mod"));

        ConfigurationOptions currentConfig = ConfigurationFileManager.get();

        builder.setSavingRunnable(() -> {
            ConfigurationFileManager.set(currentConfig);
        });

        ConfigCategory speedrunnermodoptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.options.config.general"));
        ConfigCategory speedrunnermodclientoptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.options.config.client"));
        ConfigCategory speedrunnermodinfo = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.info"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        speedrunnermodoptions.addEntry(entryBuilder.startIntSlider(new TranslatableText("option.speedrunnermod.mod_difficulty"), currentConfig.difficulty, 1, 4).setDefaultValue(1).setMin(1).setMax(4).setTooltip(new TranslatableText("option.speedrunnermod.mod_difficulty.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.difficulty = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.make_structures_more_common"), currentConfig.makeStructuresMoreCommon).setDefaultValue(currentConfig.makeStructuresMoreCommon).setTooltip(new TranslatableText("option.speedrunnermod.make_structures_more_common.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.makeStructuresMoreCommon = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_world_generation"), currentConfig.modifiedWorldGeneration).setDefaultValue(currentConfig.modifiedWorldGeneration).setTooltip(new TranslatableText("option.speedrunnermod.modified_world_generation.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.modifiedWorldGeneration = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_block_hardness_values"), currentConfig.modifiedBlockHardness).setDefaultValue(currentConfig.modifiedBlockHardness).setTooltip(new TranslatableText("option.speedrunnermod.modified_block_hardness_values.hover")).setSaveConsumer(newValue -> currentConfig.modifiedBlockHardness = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_loot_tables"), currentConfig.modifiedLootTables).setDefaultValue(currentConfig.modifiedLootTables).setTooltip(new TranslatableText("option.speedrunnermod.modified_loot_tables.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.modifiedLootTables = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.kill_ghast_upon_fireball"), currentConfig.killGhastUponFireball).setDefaultValue(currentConfig.killGhastUponFireball).setTooltip(new TranslatableText("option.speedrunnermod.kill_ghast_upon_fireball.hover")).setSaveConsumer(newValue -> currentConfig.killGhastUponFireball = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.doom_mode"), currentConfig.doomMode).setDefaultValue(currentConfig.doomMode).setTooltip(new TranslatableText("option.speedrunnermod.doom_mode.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.doomMode = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.icarus_mode"), currentConfig.iCarusMode).setDefaultValue(currentConfig.iCarusMode).setTooltip(new TranslatableText("option.speedrunnermod.icarus_mode.hover")).setSaveConsumer(newValue -> currentConfig.iCarusMode = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.infini_pearl_mode"), currentConfig.infiniPearlMode).setDefaultValue(currentConfig.infiniPearlMode).setTooltip(new TranslatableText("option.speedrunnermod.infini_pearl_mode.hover")).setSaveConsumer(newValue -> currentConfig.infiniPearlMode = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.manhunt_mode"), currentConfig.manhuntMode).setDefaultValue(currentConfig.manhuntMode).setTooltip(new TranslatableText("option.speedrunnermod.manhunt_mode.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.manhuntMode = newValue).build());
        speedrunnermodclientoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.fog"), currentConfig.fog).setDefaultValue(currentConfig.fog).setTooltip(new TranslatableText("option.speedrunnermod.fog.hover")).setSaveConsumer(newValue -> currentConfig.fog = newValue).build());
        speedrunnermodclientoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.particles"), currentConfig.particles).setDefaultValue(currentConfig.particles).setTooltip(new TranslatableText("option.speedrunnermod.particles.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.particles = newValue).build());
        speedrunnermodclientoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.custom_music"), currentConfig.customMusic).setDefaultValue(currentConfig.customMusic).setTooltip(new TranslatableText("option.speedrunnermod.custom_music.hover")).setSaveConsumer(newValue -> currentConfig.customMusic = newValue).build());
        speedrunnermodinfo.addEntry(entryBuilder.startTextField(new TranslatableText("info.speedrunnermod.mod_version"), "v1.2.0 (August 1st, 2021)").build());
        speedrunnermodinfo.addEntry(entryBuilder.startTextField(new TranslatableText("info.speedrunnermod.wiki"), "https://sites.google.com/view/speedrunnermod/home").build());
        speedrunnermodinfo.addEntry(entryBuilder.startTextField(new TranslatableText("info.speedrunnermod.discord"), "https://discord.gg/Qu8utnCwkq").build());
        speedrunnermodinfo.addEntry(entryBuilder.startTextField(new TranslatableText("info.speedrunnermod.curseforge"), "https://www.curseforge.com/minecraft/mc-mods/speedrunner-mod").build());

        return builder;
    }
}
