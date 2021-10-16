package net.dilloney.speedrunnermod.client;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.option.OptionsFileManager;
import net.dilloney.speedrunnermod.util.ModHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.TooltipSupplier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
class MiscellaneousScreen extends Screen {
    private final Screen parent;
    private ButtonListWidget list;

    protected MiscellaneousScreen(Screen parent) {
        super(new TranslatableText("speedrunnermod.miscellaneous"));
        this.parent = parent;
    }

    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        ButtonWidget reset_structure_settings = this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 40, 150, 20, new TranslatableText("speedrunnermod.reset_structure_settings"), (buttonWidget) -> {
            this.client.openScreen(new ResetStructureSettingsConfirmScreen(this));
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                MiscellaneousScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.reset_structure_settings.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.reset_structure_settings.tooltip"));
            }
        }));
        reset_structure_settings.active = SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon;
        ButtonWidget old_structure_settings = this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, 40, 150, 20, new TranslatableText("speedrunnermod.old_structure_settings"), (buttonWidget) -> {
            this.client.openScreen(new UseOldStructureSettingsConfirmScreen(this));
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                MiscellaneousScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.old_structure_settings.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.old_structure_settings.tooltip"));
            }
        }));
        old_structure_settings.active =  SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon && !ModHelper.getOldStructureSettings();
        ButtonWidget make_world_structures = this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 65, 150, 20, new TranslatableText("speedrunnermod.make_world_structures"), (buttonWidget) -> {
            this.client.openScreen(new WorldIsStructuresConfirmScreen(this));
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                MiscellaneousScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.make_world_structures.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.make_world_structures.tooltip"));
            }
        }));
        make_world_structures.active =  SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon && !ModHelper.getMakeWorldStructureSettings();
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height - 27, 150, 20, new TranslatableText("speedrunnermod.miscellaneous_options.title"), (buttonWidget) -> {
            this.client.openScreen(MiscellaneousOptionsScreen.openScreen().build());
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height - 27, 150, 20, ScreenTexts.DONE, (button) -> {
            this.client.openScreen(this.parent);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.list.render(matrices, mouseX, mouseY, delta);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 12, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    private void resetStructureSettings() {
        SpeedrunnerMod.WORLD_OPTIONS.setVillageSpacing(16);
        SpeedrunnerMod.WORLD_OPTIONS.setVillageSeparation(9);
        SpeedrunnerMod.WORLD_OPTIONS.setDesertPyramidSpacing(10);
        SpeedrunnerMod.WORLD_OPTIONS.setDesertPyramidSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setIglooSpacing(32);
        SpeedrunnerMod.WORLD_OPTIONS.setIglooSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setJunglePyramidSpacing(32);
        SpeedrunnerMod.WORLD_OPTIONS.setJunglePyramidSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setSwampHutSpacing(32);
        SpeedrunnerMod.WORLD_OPTIONS.setSwampHutSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setPillagerOutpostSpacing(32);
        SpeedrunnerMod.WORLD_OPTIONS.setPillagerOutpostSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setMonumentSpacing(32);
        SpeedrunnerMod.WORLD_OPTIONS.setMonumentSeparation(5);
        SpeedrunnerMod.WORLD_OPTIONS.setEndCitySpacing(7);
        SpeedrunnerMod.WORLD_OPTIONS.setEndCitySeparation(6);
        SpeedrunnerMod.WORLD_OPTIONS.setMansionSpacing(80);
        SpeedrunnerMod.WORLD_OPTIONS.setMansionSeparation(20);
        SpeedrunnerMod.WORLD_OPTIONS.setRuinedPortalSpacing(9);
        SpeedrunnerMod.WORLD_OPTIONS.setRuinedPortalSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setShipwreckSpacing(10);
        SpeedrunnerMod.WORLD_OPTIONS.setShipwreckSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setOceanRuinSpacing(20);
        SpeedrunnerMod.WORLD_OPTIONS.setOceanRuinSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setBastionSpacing(9);
        SpeedrunnerMod.WORLD_OPTIONS.setBastionSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setFortressSpacing(8);
        SpeedrunnerMod.WORLD_OPTIONS.setFortressSeparation(7);
        OptionsFileManager.saveWorld();
        OptionsFileManager.loadWorld();
    }

    private void applyOldStructureSettings() {
        SpeedrunnerMod.WORLD_OPTIONS.setVillageSpacing(12);
        SpeedrunnerMod.WORLD_OPTIONS.setVillageSeparation(9);
        SpeedrunnerMod.WORLD_OPTIONS.setDesertPyramidSpacing(10);
        SpeedrunnerMod.WORLD_OPTIONS.setDesertPyramidSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setIglooSpacing(18);
        SpeedrunnerMod.WORLD_OPTIONS.setIglooSeparation(14);
        SpeedrunnerMod.WORLD_OPTIONS.setShipwreckSpacing(10);
        SpeedrunnerMod.WORLD_OPTIONS.setShipwreckSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setRuinedPortalSpacing(7);
        SpeedrunnerMod.WORLD_OPTIONS.setRuinedPortalSeparation(6);
        SpeedrunnerMod.WORLD_OPTIONS.setBastionSpacing(10);
        SpeedrunnerMod.WORLD_OPTIONS.setBastionSeparation(8);
        SpeedrunnerMod.WORLD_OPTIONS.setFortressSpacing(11);
        SpeedrunnerMod.WORLD_OPTIONS.setFortressSeparation(9);
        OptionsFileManager.saveWorld();
        OptionsFileManager.loadWorld();
    }

    private void applyWorldIsStructuresSettings() {
        SpeedrunnerMod.WORLD_OPTIONS.setVillageSpacing(5);
        SpeedrunnerMod.WORLD_OPTIONS.setVillageSeparation(3);
        SpeedrunnerMod.WORLD_OPTIONS.setDesertPyramidSpacing(8);
        SpeedrunnerMod.WORLD_OPTIONS.setDesertPyramidSeparation(3);
        SpeedrunnerMod.WORLD_OPTIONS.setIglooSpacing(2);
        SpeedrunnerMod.WORLD_OPTIONS.setIglooSeparation(1);
        SpeedrunnerMod.WORLD_OPTIONS.setJunglePyramidSpacing(3);
        SpeedrunnerMod.WORLD_OPTIONS.setJunglePyramidSeparation(1);
        SpeedrunnerMod.WORLD_OPTIONS.setSwampHutSpacing(2);
        SpeedrunnerMod.WORLD_OPTIONS.setSwampHutSeparation(1);
        SpeedrunnerMod.WORLD_OPTIONS.setPillagerOutpostSpacing(8);
        SpeedrunnerMod.WORLD_OPTIONS.setPillagerOutpostSeparation(6);
        SpeedrunnerMod.WORLD_OPTIONS.setMonumentSpacing(5);
        SpeedrunnerMod.WORLD_OPTIONS.setMonumentSeparation(2);
        SpeedrunnerMod.WORLD_OPTIONS.setEndCitySpacing(3);
        SpeedrunnerMod.WORLD_OPTIONS.setEndCitySeparation(1);
        SpeedrunnerMod.WORLD_OPTIONS.setMansionSpacing(2);
        SpeedrunnerMod.WORLD_OPTIONS.setMansionSeparation(1);
        SpeedrunnerMod.WORLD_OPTIONS.setRuinedPortalSpacing(6);
        SpeedrunnerMod.WORLD_OPTIONS.setRuinedPortalSeparation(3);
        SpeedrunnerMod.WORLD_OPTIONS.setShipwreckSpacing(6);
        SpeedrunnerMod.WORLD_OPTIONS.setShipwreckSeparation(2);
        SpeedrunnerMod.WORLD_OPTIONS.setOceanRuinSpacing(3);
        SpeedrunnerMod.WORLD_OPTIONS.setOceanRuinSeparation(2);
        SpeedrunnerMod.WORLD_OPTIONS.setBastionSpacing(5);
        SpeedrunnerMod.WORLD_OPTIONS.setBastionSeparation(3);
        SpeedrunnerMod.WORLD_OPTIONS.setFortressSpacing(4);
        SpeedrunnerMod.WORLD_OPTIONS.setFortressSeparation(2);
        OptionsFileManager.saveWorld();
        OptionsFileManager.loadWorld();
    }

    class ResetStructureSettingsConfirmScreen extends ConfirmScreen {

        public ResetStructureSettingsConfirmScreen(Screen parent) {
            super((t) -> {
                if (t) {
                    resetStructureSettings();
                    SpeedrunnerMod.LOGGER.info("Reset structure settings!");
                    MinecraftClient.getInstance().scheduleStop();
                }
                MinecraftClient.getInstance().openScreen(parent);

            }, new TranslatableText("speedrunnermod.reset_structure_settings"), new TranslatableText("speedrunnermod.reset_structure_settings.desc"), new TranslatableText("speedrunnermod.proceed_and_exit"), new TranslatableText("speedrunnermod.cancel"));
        }
    }

    class UseOldStructureSettingsConfirmScreen extends ConfirmScreen {

        public UseOldStructureSettingsConfirmScreen(Screen parent) {
            super((t) -> {
                if (t) {
                    applyOldStructureSettings();
                    SpeedrunnerMod.LOGGER.info("Applied old structure settings!");
                    MinecraftClient.getInstance().scheduleStop();
                }
                MinecraftClient.getInstance().openScreen(parent);

            }, new TranslatableText("speedrunnermod.old_structure_settings"), new TranslatableText("speedrunnermod.use_old_structure_settings.desc"), new TranslatableText("speedrunnermod.proceed_and_exit"), new TranslatableText("speedrunnermod.cancel"));
        }
    }

    class WorldIsStructuresConfirmScreen extends ConfirmScreen {

        public WorldIsStructuresConfirmScreen(Screen parent) {
            super((t) -> {
                if (t) {
                    applyWorldIsStructuresSettings();
                    SpeedrunnerMod.LOGGER.info("Applied world is structures setting!");
                    MinecraftClient.getInstance().scheduleStop();
                }
                MinecraftClient.getInstance().openScreen(parent);

            }, new TranslatableText("speedrunnermod.make_world_structures"), new TranslatableText("speedrunnermod.make_world_structures.desc"), new TranslatableText("speedrunnermod.proceed_and_exit"), new TranslatableText("speedrunnermod.cancel"));
        }
    }
}