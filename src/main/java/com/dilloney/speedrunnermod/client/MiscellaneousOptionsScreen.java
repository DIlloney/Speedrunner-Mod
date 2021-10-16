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
class MiscellaneousOptionsScreen {

    protected static ConfigBuilder openScreen() {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new TranslatableText("speedrunnermod.miscellaneous"));

        ModOptions.MiscOptions options = OptionsFileManager.getMisc();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.setSavingRunnable(() -> {
            OptionsFileManager.setMisc(options);
        });

        ConfigCategory miscellaneousoptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.miscellaneous"));

        miscellaneousoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.make_biomes_more_common"), options.makeBiomesMoreCommon).setDefaultValue(options.makeBiomesMoreCommon).setTooltip(new TranslatableText("options.make_biomes_more_common.tooltip")).requireRestart().setSaveConsumer(newValue -> options.makeBiomesMoreCommon = newValue).build());
        miscellaneousoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.modified_saturation_and_hunger_values"), options.modifiedSaturationAndHungerValues).setDefaultValue(options.modifiedSaturationAndHungerValues).setTooltip(new TranslatableText("options.modified_saturation_and_hunger_values.tooltip")).requireRestart().setSaveConsumer(newValue -> options.modifiedSaturationAndHungerValues = newValue).build());
        miscellaneousoptions.addEntry(entryBuilder.startIntField(new TranslatableText("options.stronghold_count"), options.strongholdCount).setDefaultValue(options.strongholdCount).setMin(64).setMax(256).setTooltip(new TranslatableText("options.stronghold_count.tooltip")).requireRestart().setSaveConsumer(newValue -> options.strongholdCount = newValue).build());
        miscellaneousoptions.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.kill_ghast_upon_fireball"), options.killGhastUponFireball).setDefaultValue(options.killGhastUponFireball).setTooltip(new TranslatableText("options.kill_ghast_upon_fireball.tooltip")).setSaveConsumer(newValue -> options.killGhastUponFireball = newValue).build());
        miscellaneousoptions.addEntry(entryBuilder.startIntField(new TranslatableText("options.ender_eyes_lifespan"), options.enderEyesLifespan).setDefaultValue(options.enderEyesLifespan).setMin(40).setMax(120).setTooltip(new TranslatableText("options.ender_eyes_lifespan.tooltip")).setSaveConsumer(newValue -> options.enderEyesLifespan = newValue).build());
        return builder;
    }
}
