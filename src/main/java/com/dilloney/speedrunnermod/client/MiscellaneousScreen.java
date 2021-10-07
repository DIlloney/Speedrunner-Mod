package com.dilloney.speedrunnermod.client;

import com.dilloney.speedrunnermod.option.OptionsFileManager;
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

import static com.dilloney.speedrunnermod.SpeedrunnerMod.*;

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
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 8, 150, 20, new TranslatableText("speedrunnermod.reset_structure_settings"), (buttonWidget) -> {
            if (OPTIONS.makeStructuresMoreCommon) {
                this.client.openScreen(new ResetStructureSettingsConfirmScreen(this));
            } else {
                this.client.openScreen(StructureOptionsScreen.openWarningScreen().build());
            }
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                MiscellaneousScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.reset_structure_settings.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.reset_structure_settings.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height / 8, 150, 20, new TranslatableText("speedrunnermod.old_structure_settings"), (buttonWidget) -> {
            if (OPTIONS.makeStructuresMoreCommon) {
                this.client.openScreen(new UseOldStructureSettingsConfirmScreen(this));
            } else {
                this.client.openScreen(StructureOptionsScreen.openWarningScreen().build());
            }
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                MiscellaneousScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.old_structure_settings.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.old_structure_settings.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 5, 150, 20, new TranslatableText("speedrunnermod.make_world_structures"), (buttonWidget) -> {
            if (OPTIONS.makeStructuresMoreCommon) {
                this.client.openScreen(new WorldIsStructuresConfirmScreen(this));
            } else {
                this.client.openScreen(StructureOptionsScreen.openWarningScreen().build());
            }
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                MiscellaneousScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.make_world_structures.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.make_world_structures.tooltip"));
            }
        }));
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
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    private void resetStructureSettings() {
        WORLD_OPTIONS.setVillageSpacing(16);
        WORLD_OPTIONS.setVillageSeparation(9);
        WORLD_OPTIONS.setDesertPyramidSpacing(10);
        WORLD_OPTIONS.setDesertPyramidSeparation(8);
        WORLD_OPTIONS.setIglooSpacing(32);
        WORLD_OPTIONS.setIglooSeparation(8);
        WORLD_OPTIONS.setJunglePyramidSpacing(32);
        WORLD_OPTIONS.setJunglePyramidSeparation(8);
        WORLD_OPTIONS.setSwampHutSpacing(32);
        WORLD_OPTIONS.setSwampHutSeparation(8);
        WORLD_OPTIONS.setPillagerOutpostSpacing(32);
        WORLD_OPTIONS.setPillagerOutpostSeparation(8);
        WORLD_OPTIONS.setMonumentSpacing(32);
        WORLD_OPTIONS.setMonumentSeparation(5);
        WORLD_OPTIONS.setEndCitySpacing(7);
        WORLD_OPTIONS.setEndCitySeparation(6);
        WORLD_OPTIONS.setMansionSpacing(80);
        WORLD_OPTIONS.setMansionSeparation(20);
        WORLD_OPTIONS.setRuinedPortalSpacing(9);
        WORLD_OPTIONS.setRuinedPortalSeparation(8);
        WORLD_OPTIONS.setShipwreckSpacing(10);
        WORLD_OPTIONS.setShipwreckSeparation(8);
        WORLD_OPTIONS.setOceanRuinSpacing(20);
        WORLD_OPTIONS.setOceanRuinSeparation(8);
        WORLD_OPTIONS.setBastionSpacing(9);
        WORLD_OPTIONS.setBastionSeparation(8);
        WORLD_OPTIONS.setFortressSpacing(8);
        WORLD_OPTIONS.setFortressSeparation(7);
        OptionsFileManager.saveWorld();
        OptionsFileManager.loadWorld();
    }

    private void applyOldStructureSettings() {
        WORLD_OPTIONS.setVillageSpacing(12);
        WORLD_OPTIONS.setVillageSeparation(9);
        WORLD_OPTIONS.setDesertPyramidSpacing(10);
        WORLD_OPTIONS.setDesertPyramidSeparation(8);
        WORLD_OPTIONS.setIglooSpacing(18);
        WORLD_OPTIONS.setIglooSeparation(14);
        WORLD_OPTIONS.setShipwreckSpacing(10);
        WORLD_OPTIONS.setShipwreckSeparation(8);
        WORLD_OPTIONS.setRuinedPortalSpacing(7);
        WORLD_OPTIONS.setRuinedPortalSeparation(6);
        WORLD_OPTIONS.setBastionSpacing(10);
        WORLD_OPTIONS.setBastionSeparation(8);
        WORLD_OPTIONS.setFortressSpacing(11);
        WORLD_OPTIONS.setFortressSeparation(9);
        OptionsFileManager.saveWorld();
        OptionsFileManager.loadWorld();
    }

    private void applyWorldIsStructuresSettings() {
        WORLD_OPTIONS.setVillageSpacing(5);
        WORLD_OPTIONS.setVillageSeparation(3);
        WORLD_OPTIONS.setDesertPyramidSpacing(8);
        WORLD_OPTIONS.setDesertPyramidSeparation(3);
        WORLD_OPTIONS.setIglooSpacing(2);
        WORLD_OPTIONS.setIglooSeparation(1);
        WORLD_OPTIONS.setJunglePyramidSpacing(3);
        WORLD_OPTIONS.setJunglePyramidSeparation(1);
        WORLD_OPTIONS.setSwampHutSpacing(2);
        WORLD_OPTIONS.setSwampHutSeparation(1);
        WORLD_OPTIONS.setPillagerOutpostSpacing(8);
        WORLD_OPTIONS.setPillagerOutpostSeparation(6);
        WORLD_OPTIONS.setMonumentSpacing(5);
        WORLD_OPTIONS.setMonumentSeparation(2);
        WORLD_OPTIONS.setEndCitySpacing(3);
        WORLD_OPTIONS.setEndCitySeparation(1);
        WORLD_OPTIONS.setMansionSpacing(2);
        WORLD_OPTIONS.setMansionSeparation(1);
        WORLD_OPTIONS.setRuinedPortalSpacing(6);
        WORLD_OPTIONS.setRuinedPortalSeparation(3);
        WORLD_OPTIONS.setShipwreckSpacing(6);
        WORLD_OPTIONS.setShipwreckSeparation(2);
        WORLD_OPTIONS.setOceanRuinSpacing(3);
        WORLD_OPTIONS.setOceanRuinSeparation(2);
        WORLD_OPTIONS.setBastionSpacing(5);
        WORLD_OPTIONS.setBastionSeparation(3);
        WORLD_OPTIONS.setFortressSpacing(4);
        WORLD_OPTIONS.setFortressSeparation(2);
        OptionsFileManager.saveWorld();
        OptionsFileManager.loadWorld();
    }

    class ResetStructureSettingsConfirmScreen extends ConfirmScreen {

        public ResetStructureSettingsConfirmScreen(Screen parent) {
            super((t) -> {
                if (t) {
                    resetStructureSettings();
                    LOGGER.info("Reset structure settings!");
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
                    LOGGER.info("Applied old structure settings!");
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
                    LOGGER.info("Applied world is structures setting!");
                    MinecraftClient.getInstance().scheduleStop();
                }
                MinecraftClient.getInstance().openScreen(parent);

            }, new TranslatableText("speedrunnermod.make_world_structures"), new TranslatableText("speedrunnermod.make_world_structures.desc"), new TranslatableText("speedrunnermod.proceed_and_exit"), new TranslatableText("speedrunnermod.cancel"));
        }
    }
}