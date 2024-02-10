package net.dillon8775.speedrunnermod.client.gui.screen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.option.ClientModOptions;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

@Environment(EnvType.CLIENT)
public class SafeBootScreen extends Screen {

    public SafeBootScreen() {
        super(new TranslatableText("speedrunnermod.title.safe_mode"));
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50 - 105, height, 100, 20, new TranslatableText("speedrunnermod.fix_and_restart"), (buttonWidget) -> {
            fixOptions();
            info("Fixing options! Re-launch to apply changes.");
            this.client.scheduleStop();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50, height, 100, 20, new TranslatableText("speedrunnermod.close_game"), (buttonWidget) -> {
            info("Closing game! No changes were made.");
            this.client.scheduleStop();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50 + 105, height, 100, 20, new TranslatableText("speedrunnermod.proceed_anyway"), (buttonWidget) -> {
            info("Proceeding. Due to corrupt options, you may experience issues. Re-launch the game to fix options.");
            this.client.setScreen(new TitleScreen(false));
        }));
    }

    @Override
    public void onClose() {
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.options.error.line1"), this.width / 2, 100, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.options.error.line2"), this.width / 2, 120, 16777215);

        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int farRightSide = rightSide + 267;
        int height = this.height - 24;
        drawCenteredText(matrices, this.textRenderer, SpeedrunnerMod.VERSION, farRightSide, height, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    private static void fixOptions() {
        if (ModOptions.Broken.isStructureSpawnRateBroken) {
            SpeedrunnerMod.options().main.structureSpawnRate = ModOptions.Main.StructureSpawnRate.COMMON;
        }

        if (ModOptions.Broken.isMaxItemCountBroken) {
            SpeedrunnerMod.options().main.maxItemCount = 64;
        }

        if (ModOptions.Broken.isMobSpawningRateBroken) {
            SpeedrunnerMod.options().advanced.mobSpawningRate = ModOptions.Advanced.MobSpawningRate.HIGH;
        }

        if (ClientModOptions.Broken.isPanoramaBroken) {
            SpeedrunnerModClient.clientOptions().panorama = ClientModOptions.Panorama.SPEEDRUNNER_MOD;
        }

        if (ClientModOptions.Broken.isItemMessagesBroken) {
            SpeedrunnerModClient.clientOptions().itemMessages = ClientModOptions.ItemMessages.CHAT;
        }

        if (ClientModOptions.Broken.isModButtonTypeBroken) {
            SpeedrunnerModClient.clientOptions().modButtonType = ClientModOptions.ModButtonType.LOGO;
        }

        if (ClientModOptions.Broken.isGameModeBroken) {
            SpeedrunnerModClient.clientOptions().worldSettings.gameMode = ClientModOptions.GameMode.SURVIVAL;
        }

        if (ClientModOptions.Broken.isDifficultyBroken) {
            SpeedrunnerModClient.clientOptions().worldSettings.difficulty = ClientModOptions.Difficulty.EASY;
        }

        ModOptions.saveConfig();
        ModOptions.saveConfig();
    }
}