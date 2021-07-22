package com.dilloney.speedrunnermod.client;

import com.dilloney.speedrunnermod.config.ConfigFileManager;
import com.dilloney.speedrunnermod.config.ConfigurationOptions;
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

        ConfigurationOptions currentConfig = ConfigFileManager.get();

        builder.setSavingRunnable(() -> {
            ConfigFileManager.set(currentConfig);
        });

        ConfigCategory speedrunnermodoptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.options.config"));
        ConfigCategory speedrunnermodinfo = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.info"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        speedrunnermodoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.mod_difficulty"), currentConfig.difficulty).setDefaultValue(1).setMin(1).setMax(4).setTooltip(new TranslatableText("option.speedrunnermod.mod_difficulty.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.difficulty = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_loot_tables"), currentConfig.modifiedLootTables).setDefaultValue(currentConfig.modifiedLootTables).setTooltip(new TranslatableText("option.speedrunnermod.modified_loot_tables.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.modifiedLootTables = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_block_hardness_values"), currentConfig.modifiedBlockHardness).setDefaultValue(currentConfig.modifiedBlockHardness).setTooltip(new TranslatableText("option.speedrunnermod.modified_block_hardness_values.hover")).setSaveConsumer(newValue -> currentConfig.modifiedBlockHardness = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.make_structures_more_common"), currentConfig.makeStructuresMoreCommon).setDefaultValue(currentConfig.makeStructuresMoreCommon).setTooltip(new TranslatableText("option.speedrunnermod.make_structures_more_common.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.makeStructuresMoreCommon = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_world_generation"), currentConfig.modifiedWorldGeneration).setDefaultValue(currentConfig.modifiedWorldGeneration).setTooltip(new TranslatableText("option.speedrunnermod.modified_world_generation.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.modifiedWorldGeneration = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.combine_fortress_and_bastion"), currentConfig.combineFortressAndBastion).setDefaultValue(currentConfig.combineFortressAndBastion).setTooltip(new TranslatableText("option.speedrunnermod.combine_fortress_and_bastion.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.combineFortressAndBastion = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.kill_ghast_upon_fireball"), currentConfig.killGhastUponFireball).setDefaultValue(currentConfig.killGhastUponFireball).setTooltip(new TranslatableText("option.speedrunnermod.kill_ghast_upon_fireball.hover")).setSaveConsumer(newValue -> currentConfig.killGhastUponFireball = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.doom_mode"), currentConfig.doomMode).setDefaultValue(currentConfig.doomMode).setTooltip(new TranslatableText("option.speedrunnermod.doom_mode.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.doomMode = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.icarus_mode"), currentConfig.iCarusMode).setDefaultValue(currentConfig.iCarusMode).setTooltip(new TranslatableText("option.speedrunnermod.icarus_mode.hover")).setSaveConsumer(newValue -> currentConfig.iCarusMode = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.fog"), currentConfig.fog).setDefaultValue(currentConfig.fog).setSaveConsumer(newValue -> currentConfig.fog = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.default_particles"), currentConfig.defaultParticles).setDefaultValue(currentConfig.defaultParticles).setTooltip(new TranslatableText("option.speedrunnermod.default_particles.hover")).setSaveConsumer(newValue -> currentConfig.defaultParticles = newValue).build());
        speedrunnermodoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.nether_biome_particles"), currentConfig.netherBiomeParticles).setDefaultValue(currentConfig.netherBiomeParticles).requireRestart().setSaveConsumer(newValue -> currentConfig.netherBiomeParticles = newValue).build());
        speedrunnermodinfo.addEntry(entryBuilder.startTextField(new TranslatableText("info.speedrunnermod.wiki"), "https://sites.google.com/view/speedrunnermod/home").build());
        speedrunnermodinfo.addEntry(entryBuilder.startTextField(new TranslatableText("info.speedrunnermod.discord"), "https://discord.gg/Qu8utnCwkq").build());
        speedrunnermodinfo.addEntry(entryBuilder.startTextField(new TranslatableText("info.speedrunnermod.curseforge"), "https://www.curseforge.com/minecraft/mc-mods/speedrunner-mod").build());

        return builder;
    }
}
