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
class StructureOptionsScreen {

    protected static ConfigBuilder openScreen() {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new TranslatableText("speedrunnermod.structure_settings"));

        ModOptions.WorldOptions options = OptionsFileManager.getWorld();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.setSavingRunnable(() -> {
            OptionsFileManager.setWorld(options);
        });

        ConfigCategory structureoptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.structure_settings"));

        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.village.spacing"), options.villageSpacing).setDefaultValue(options.villageSpacing).setMin(5).setMax(32).requireRestart().setSaveConsumer(newValue -> options.villageSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.village.separation"), options.villageSeparation).setDefaultValue(options.villageSeparation).setMin(3).setMax(12).requireRestart().setSaveConsumer(newValue -> options.villageSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.desert_pyramid.spacing"), options.desertPyramidSpacing).setDefaultValue(options.desertPyramidSpacing).setMin(8).setMax(32).requireRestart().setSaveConsumer(newValue -> options.desertPyramidSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.desert_pyramid.separation"), options.desertPyramidSeparation).setDefaultValue(options.desertPyramidSeparation).setMin(3).setMax(10).requireRestart().setSaveConsumer(newValue -> options.desertPyramidSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.igloo.spacing"), options.iglooSpacing).setDefaultValue(options.iglooSpacing).setMin(2).setMax(32).requireRestart().setSaveConsumer(newValue -> options.iglooSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.igloo.separation"), options.iglooSeparation).setDefaultValue(options.iglooSeparation).setMin(1).setMax(14).requireRestart().setSaveConsumer(newValue -> options.iglooSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.jungle_pyramid.spacing"), options.junglePyramidSpacing).setDefaultValue(options.junglePyramidSpacing).setMin(3).setMax(32).requireRestart().setSaveConsumer(newValue -> options.junglePyramidSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.jungle_pyramid.separation"), options.junglePyramidSeparation).setDefaultValue(options.junglePyramidSeparation).setMin(1).setMax(14).requireRestart().setSaveConsumer(newValue -> options.junglePyramidSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.swamp_hut.spacing"), options.swampHutSpacing).setDefaultValue(options.swampHutSpacing).setMin(2).setMax(32).requireRestart().setSaveConsumer(newValue -> options.swampHutSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.swamp_hut.separation"), options.swampHutSeparation).setDefaultValue(options.swampHutSeparation).setMin(1).setMax(8).requireRestart().setSaveConsumer(newValue -> options.swampHutSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.pillager_outpost.spacing"), options.pillagerOutpostSpacing).setDefaultValue(options.pillagerOutpostSpacing).setMin(8).setMax(32).requireRestart().setSaveConsumer(newValue -> options.pillagerOutpostSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.pillager_outpost.separation"), options.pillagerOutpostSeparation).setDefaultValue(options.pillagerOutpostSeparation).setMin(6).setMax(8).requireRestart().setSaveConsumer(newValue -> options.pillagerOutpostSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.monument.spacing"), options.monumentSpacing).setDefaultValue(options.monumentSpacing).setMin(5).setMax(32).requireRestart().setSaveConsumer(newValue -> options.monumentSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.monument.separation"), options.monumentSeparation).setDefaultValue(options.monumentSeparation).setMin(2).setMax(5).requireRestart().setSaveConsumer(newValue -> options.monumentSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.end_city.spacing"), options.endCitySpacing).setDefaultValue(options.endCitySpacing).setMin(3).setMax(20).requireRestart().setSaveConsumer(newValue -> options.endCitySpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.end_city.separation"), options.endCitySeparation).setDefaultValue(options.endCitySeparation).setMin(1).setMax(11).requireRestart().setSaveConsumer(newValue -> options.endCitySeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.mansion.spacing"), options.mansionSpacing).setDefaultValue(options.mansionSpacing).setMin(2).setMax(80).requireRestart().setSaveConsumer(newValue -> options.mansionSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.mansion.separation"), options.mansionSeparation).setDefaultValue(options.mansionSeparation).setMin(1).setMax(20).requireRestart().setSaveConsumer(newValue -> options.mansionSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.ruined_portal.spacing"), options.ruinedPortalSpacing).setDefaultValue(options.ruinedPortalSpacing).setMin(6).setMax(40).requireRestart().setSaveConsumer(newValue -> options.ruinedPortalSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.ruined_portal.separation"), options.ruinedPortalSeparation).setDefaultValue(options.ruinedPortalSeparation).setMin(3).setMax(15).requireRestart().setSaveConsumer(newValue -> options.ruinedPortalSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.shipwreck.spacing"), options.shipwreckSpacing).setDefaultValue(options.shipwreckSpacing).setMin(6).setMax(24).requireRestart().setSaveConsumer(newValue -> options.shipwreckSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.shipwreck.separation"), options.shipwreckSeparation).setDefaultValue(options.shipwreckSeparation).setMin(2).setMax(10).requireRestart().setSaveConsumer(newValue -> options.shipwreckSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.ocean_ruin.spacing"), options.oceanRuinSpacing).setDefaultValue(options.oceanRuinSpacing).setMin(3).setMax(20).requireRestart().setSaveConsumer(newValue -> options.oceanRuinSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.ocean_ruin.separation"), options.oceanRuinSeparation).setDefaultValue(options.oceanRuinSeparation).setMin(2).setMax(8).requireRestart().setSaveConsumer(newValue -> options.oceanRuinSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.bastion.spacing"), options.bastionSpacing).setDefaultValue(options.bastionSpacing).setMin(5).setMax(27).requireRestart().setSaveConsumer(newValue -> options.bastionSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.bastion.separation"), options.bastionSeparation).setDefaultValue(options.bastionSeparation).setMin(3).setMax(10).requireRestart().setSaveConsumer(newValue -> options.bastionSeparation = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.fortress.spacing"), options.fortressSpacing).setDefaultValue(options.fortressSpacing).setMin(4).setMax(27).requireRestart().setSaveConsumer(newValue -> options.fortressSpacing = newValue).build());
        structureoptions.addEntry(entryBuilder.startIntField(new TranslatableText("option.speedrunnermod.fortress.separation"), options.fortressSeparation).setDefaultValue(options.fortressSeparation).setMin(2).setMax(10).requireRestart().setSaveConsumer(newValue -> options.fortressSeparation = newValue).build());
        return builder;
    }

    protected static ConfigBuilder openWarningScreen() {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new TranslatableText("speedrunnermod.structures"));

        ModOptions.WorldOptions options = OptionsFileManager.getWorld();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.setSavingRunnable(() -> {
            OptionsFileManager.setWorld(options);
        });

        ConfigCategory structureoptions = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.options.structures"));


        structureoptions.addEntry(entryBuilder.startTextDescription(new LiteralText("You need to turn ON makeStructuresMoreCommon before modifying the structure generation!")).build());
        return builder;
    }
}