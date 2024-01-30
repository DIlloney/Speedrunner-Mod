package net.dillon8775.speedrunnermod.client.gui.screen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.gui.screen.option.ModOptionsScreen;
import net.dillon8775.speedrunnermod.option.CLModOptions;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ResetOptionsConfirmScreen extends GameOptionsScreen {
    private final Screen parent;

    public ResetOptionsConfirmScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.options.reset"));
        this.parent = parent;
    }

    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 + 126;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.reset"), (buttonWidget) -> {
            resetOptions();
            ModOptions.saveConfig();
            CLModOptions.saveClientConfig();
            SpeedrunnerMod.LOGGER.info("Successfully reset all speedrunner mod options. Restart game to take full effect.");
            this.client.setScreen(new ResetOptionsScreen(this.parent, MinecraftClient.getInstance().options));
        }));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.restart_later"), (buttonWidget) -> {
            this.client.setScreen(new ModOptionsScreen(this.parent, MinecraftClient.getInstance().options));
        }));
    }

    public void onClose() {
        this.client.setScreen(new ModOptionsScreen(this.parent, MinecraftClient.getInstance().options));
    }

    private static void resetOptions() {
        SpeedrunnerMod.options().main.structureSpawnRate = ModOptions.Main.StructureSpawnRate.COMMON;
        SpeedrunnerMod.options().main.betterBiomes = true;
        SpeedrunnerMod.options().main.iCarusMode = false;
        SpeedrunnerMod.options().main.infiniPearlMode = false;
        SpeedrunnerMod.options().main. doomMode = false;
        SpeedrunnerMod.options().main.fasterBlockBreaking = true;
        SpeedrunnerMod.options().main.killGhastOnFireball = false;
        SpeedrunnerMod.options().main.strongholdCount = 128;
        SpeedrunnerMod.options().main.dragonPerchTime = 30;
        SpeedrunnerMod.options().advanced.mobSpawningRate = ModOptions.Advanced.MobSpawningRate.HIGH;
        SpeedrunnerMod.options().advanced.customBiomes = true;
        SpeedrunnerMod.options().advanced.betterFoods = true;
        SpeedrunnerMod.options().advanced.itemBuffs = true;
        SpeedrunnerMod.options().advanced.commonOres = true;
        SpeedrunnerMod.options().advanced.mobSpawnerMaxSpawnDuration = 40;
        SpeedrunnerMod.options().advanced.strongholdDistance = 4;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.reset_options_confirm"), this.width / 2, 110, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}