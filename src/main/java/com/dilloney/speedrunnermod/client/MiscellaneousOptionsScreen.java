package com.dilloney.speedrunnermod.client;

import com.dilloney.speedrunnermod.option.ModOptions;
import com.dilloney.speedrunnermod.option.OptionsFileManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
class MiscellaneousOptionsScreen {

    protected static ConfigBuilder openScreen() {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new TranslatableText("speedrunnermod.miscellaneous_options"));

        ModOptions.MiscOptions options = OptionsFileManager.getMisc();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.setSavingRunnable(() -> {
            OptionsFileManager.setMisc(options);
        });

        ConfigCategory miscellaneousoptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.miscellaneous_options"));

        miscellaneousoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.modified_biome_generation"), options.modifiedBiomeGeneration).setDefaultValue(options.modifiedBiomeGeneration).setTooltip(new TranslatableText("options.modified_biome_generation.tooltip")).requireRestart().setSaveConsumer(newValue -> options.modifiedBiomeGeneration = newValue).build());
        miscellaneousoptions.addEntry(entryBuilder.startIntField(new TranslatableText("options.stronghold_count"), options.strongholdCount).setDefaultValue(options.strongholdCount).setMin(64).setMax(256).setTooltip(new TranslatableText("options.stronghold_count.tooltip")).requireRestart().setSaveConsumer(newValue -> options.strongholdCount = newValue).build());
        miscellaneousoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.kill_ghast_upon_fireball"), options.killGhastUponFireball).setDefaultValue(options.killGhastUponFireball).setTooltip(new TranslatableText("options.kill_ghast_upon_fireball.tooltip")).setSaveConsumer(newValue -> options.killGhastUponFireball = newValue).build());
        miscellaneousoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.speedrunner_items_give_status_effects"), options.speedrunnerItemsGiveStatusEffects).setDefaultValue(options.speedrunnerItemsGiveStatusEffects).setTooltip(new TranslatableText("options.speedrunner_items_give_status_effects.tooltip")).setSaveConsumer(newValue -> options.speedrunnerItemsGiveStatusEffects = newValue).build());
        miscellaneousoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.allow_entities_to_use_speedrunner_bows_and_crossbows"), options.allowEntitiesToUseSpeedrunnerBowAndCrossbow).setDefaultValue(options.allowEntitiesToUseSpeedrunnerBowAndCrossbow).setTooltip(new TranslatableText("options.allow_entities_to_use_speedrunner_bows_and_crossbows.tooltip")).setSaveConsumer(newValue -> options.allowEntitiesToUseSpeedrunnerBowAndCrossbow = newValue).build());
        return builder;
    }
}