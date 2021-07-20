package com.dilloney.speedrunnermod.client;

import com.dilloney.speedrunnermod.config.ConfigFileManager;
import com.dilloney.speedrunnermod.config.ModConfigOptions;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;

@Config(name = "speedrunnermod")
@Environment(EnvType.CLIENT)
public class ClothConfigScreen {

    public static ConfigBuilder getConfigBuilder() {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new TranslatableText("options.speedrunnermod"));

        ModConfigOptions currentConfig = ConfigFileManager.get();

        builder.setSavingRunnable(() -> {
            ConfigFileManager.set(currentConfig);
        });

        ConfigCategory speedrunnermod = builder.getOrCreateCategory(new TranslatableText("options.speedrunnermod"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        speedrunnermod.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.mod_difficulty"), currentConfig.difficulty).setDefaultValue(1).setMin(1).setMax(4).setTooltip(new TranslatableText("option.speedrunnermod.mod_difficulty.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.difficulty = newValue).build());
        speedrunnermod.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_loot_tables"), currentConfig.modifiedLootTables).setDefaultValue(currentConfig.modifiedLootTables).setTooltip(new TranslatableText("option.speedrunnermod.modified_loot_tables.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.modifiedLootTables = newValue).build());
        speedrunnermod.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_block_hardness_values"), currentConfig.modifiedBlockHardness).setDefaultValue(currentConfig.modifiedBlockHardness).setTooltip(new TranslatableText("option.speedrunnermod.modified_block_hardness_values.hover")).setSaveConsumer(newValue -> currentConfig.modifiedBlockHardness = newValue).build());
        speedrunnermod.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.make_structures_more_common"), currentConfig.makeStructuresMoreCommon).setDefaultValue(currentConfig.makeStructuresMoreCommon).setTooltip(new TranslatableText("option.speedrunnermod.make_structures_more_common.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.makeStructuresMoreCommon = newValue).build());
        speedrunnermod.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_world_generation"), currentConfig.modifiedWorldGeneration).setDefaultValue(currentConfig.modifiedWorldGeneration).setTooltip(new TranslatableText("option.speedrunnermod.modified_world_generation.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.modifiedWorldGeneration = newValue).build());
        speedrunnermod.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.combine_fortress_and_bastion"), currentConfig.combineFortressAndBastion).setDefaultValue(currentConfig.combineFortressAndBastion).setTooltip(new TranslatableText("option.speedrunnermod.combine_fortress_and_bastion.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.combineFortressAndBastion = newValue).build());
        speedrunnermod.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.kill_ghast_upon_fireball"), currentConfig.killGhastUponFireball).setDefaultValue(currentConfig.killGhastUponFireball).setTooltip(new TranslatableText("option.speedrunnermod.kill_ghast_upon_fireball.hover")).setSaveConsumer(newValue -> currentConfig.killGhastUponFireball = newValue).build());
        speedrunnermod.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.enable_challenge_mode"), currentConfig.enableChallengeMode).setDefaultValue(currentConfig.enableChallengeMode).setTooltip(new TranslatableText("option.speedrunnermod.enable_challenge_mode.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.enableChallengeMode = newValue).build());
        speedrunnermod.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.icarus_enabled"), currentConfig.iCarusEnabled).setDefaultValue(currentConfig.iCarusEnabled).setTooltip(new TranslatableText("option.speedrunnermod.icarus_enabled.hover")).setSaveConsumer(newValue -> currentConfig.iCarusEnabled = newValue).build());
        speedrunnermod.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.disable_fog"), currentConfig.disableFog).setDefaultValue(currentConfig.disableFog).setSaveConsumer(newValue -> currentConfig.disableFog = newValue).build());
        speedrunnermod.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.default_particles"), currentConfig.defaultParticles).setDefaultValue(currentConfig.defaultParticles).setTooltip(new TranslatableText("option.speedrunnermod.default_particles.hover")).setSaveConsumer(newValue -> currentConfig.defaultParticles = newValue).build());
        speedrunnermod.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.nether_biome_particles"), currentConfig.netherBiomeParticles).setDefaultValue(currentConfig.netherBiomeParticles).requireRestart().setSaveConsumer(newValue -> currentConfig.netherBiomeParticles = newValue).build());

        return builder;
    }
}
