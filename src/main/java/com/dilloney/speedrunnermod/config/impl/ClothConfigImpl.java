package com.dilloney.speedrunnermod.config.impl;

import com.dilloney.speedrunnermod.config.DefaultModConfig;
import com.dilloney.speedrunnermod.config.ModConfigManager;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;

@Config(name = "speedrunnermod")
@Environment(EnvType.CLIENT)
public class ClothConfigImpl {

    public static Screen screen;

    public ClothConfigImpl(Screen parent) {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TranslatableText("options.speedrunnermod"));

        DefaultModConfig currentConfig = ModConfigManager.get();

        builder.setSavingRunnable(() -> {
            ModConfigManager.set(currentConfig);
        });

        ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("options.speedrunnermod.general"));
        ConfigCategory world = builder.getOrCreateCategory(new TranslatableText("options.speedrunnermod.world"));
        ConfigCategory client = builder.getOrCreateCategory(new TranslatableText("options.speedrunnermod.client"));
        ConfigCategory misc = builder.getOrCreateCategory(new TranslatableText("options.speedrunnermod.misc"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.mod_difficulty"), currentConfig.difficulty).setDefaultValue(1).setMin(1).setMax(4).setTooltip(new TranslatableText("option.speedrunnermod.mod_difficulty.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.difficulty = newValue).build());
        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_loot_tables"), currentConfig.modifiedLootTables).setDefaultValue(currentConfig.modifiedLootTables).setTooltip(new TranslatableText("option.speedrunnermod.modified_loot_tables.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.modifiedLootTables = newValue).build());
        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_block_hardness_values"), currentConfig.modifiedBlockHardnessValues).setDefaultValue(currentConfig.modifiedBlockHardnessValues).setTooltip(new TranslatableText("option.speedrunnermod.modified_block_hardness_values.hover")).setSaveConsumer(newValue -> currentConfig.modifiedBlockHardnessValues = newValue).build());

        world.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.make_structures_more_common"), currentConfig.makeStructuresMoreCommon).setDefaultValue(currentConfig.makeStructuresMoreCommon).setTooltip(new TranslatableText("option.speedrunnermod.make_structures_more_common.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.makeStructuresMoreCommon = newValue).build());
        world.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.modified_world_generation"), currentConfig.modifiedWorldGeneration).setDefaultValue(currentConfig.modifiedWorldGeneration).setTooltip(new TranslatableText("option.speedrunnermod.modified_world_generation.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.modifiedWorldGeneration = newValue).build());
        world.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.combine_fortress_and_bastion"), currentConfig.combineFortressAndBastion).setDefaultValue(currentConfig.combineFortressAndBastion).setTooltip(new TranslatableText("option.speedrunnermod.combine_fortress_and_bastion.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.combineFortressAndBastion = newValue).build());

        client.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.disable_fog"), currentConfig.disableFog).setDefaultValue(currentConfig.disableFog).setTooltip(new TranslatableText("option.speedrunnermod.disable_fog.hover")).setSaveConsumer(newValue -> currentConfig.disableFog = newValue).build());
        client.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.default_particles"), currentConfig.defaultParticles).setDefaultValue(currentConfig.defaultParticles).setTooltip(new TranslatableText("option.speedrunnermod.default_particles.hover")).setSaveConsumer(newValue -> currentConfig.defaultParticles = newValue).build());
        client.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.nether_biome_particles"), currentConfig.netherBiomeParticles).setDefaultValue(currentConfig.netherBiomeParticles).setTooltip(new TranslatableText("option.speedrunnermod.nether_biome_particles.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.netherBiomeParticles = newValue).build());

        misc.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.kill_ghast_upon_fireball"), currentConfig.killGhastUponFireball).setDefaultValue(currentConfig.killGhastUponFireball).setTooltip(new TranslatableText("option.speedrunnermod.kill_ghast_upon_fireball.hover")).setSaveConsumer(newValue -> currentConfig.killGhastUponFireball = newValue).build());
        misc.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.speedrunnermod.enable_challenge_mode"), currentConfig.enableChallengeMode).setDefaultValue(currentConfig.enableChallengeMode).setTooltip(new TranslatableText("option.speedrunnermod.enable_challenge_mode.hover")).requireRestart().setSaveConsumer(newValue -> currentConfig.enableChallengeMode = newValue).build());

        screen = builder.build();
    }

    public Screen getScreen() {
        return screen;
    }
}
