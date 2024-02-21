package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.BrokenModOptions;
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
import static net.dillon8775.speedrunnermod.SpeedrunnerMod.warn;

@Environment(EnvType.CLIENT)
public class SafeBootScreen extends Screen {

    public SafeBootScreen() {
        super(ModTexts.TITLE_SAFE_BOOT);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50 - 105, height, 100, 20, ModTexts.FIX_AND_RESTART, (buttonWidget) -> {
            fixOptions();
            info("Fixing options! Re-launch to apply changes.");
            this.client.scheduleStop();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50, height, 100, 20, ModTexts.CLOSE_GAME, (buttonWidget) -> {
            info("Closing game! No changes were made.");
            this.client.scheduleStop();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50 + 105, height, 100, 20, ModTexts.PROCEED_ANYWAY, (buttonWidget) -> {
            info("Proceeding. Due to corrupt options, you may experience issues. Re-launch the game to fix options.");
            this.client.setScreen(new TitleScreen(false));
        }));
    }

    @Override
    public void onClose() {
        warn("Cannot close screen! Please select an option.");
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
        if (BrokenModOptions.structureSpawnRates) {
            SpeedrunnerMod.options().structureSpawnRates = ModOptions.StructureSpawnRates.COMMON;
        }

        if (BrokenModOptions.strongholdPortalRoomCount) {
            SpeedrunnerMod.options().strongholdPortalRoomCount = 3;
        }

        if (BrokenModOptions.strongholdLibraryCount) {
            SpeedrunnerMod.options().strongholdLibraryCount = 2;
        }

        if (BrokenModOptions.mobSpawningRate) {
            SpeedrunnerMod.options().mobSpawningRate = ModOptions.MobSpawningRate.HIGH;
        }

        if (BrokenModOptions.panorama) {
            SpeedrunnerModClient.clientOptions().panorama = ClientModOptions.Panorama.SPEEDRUNNER_MOD;
        }

        if (BrokenModOptions.itemMessages) {
            SpeedrunnerModClient.clientOptions().itemMessages = ClientModOptions.ItemMessages.CHAT;
        }

        if (BrokenModOptions.modButtonType) {
            SpeedrunnerModClient.clientOptions().modButtonType = ClientModOptions.ModButtonType.LOGO;
        }

        if (BrokenModOptions.gameMode) {
            SpeedrunnerModClient.clientOptions().gameMode = ClientModOptions.GameMode.SURVIVAL;
        }

        if (BrokenModOptions.difficulty) {
            SpeedrunnerModClient.clientOptions().difficulty = ClientModOptions.Difficulty.EASY;
        }

        ModOptions.saveConfig();
        ClientModOptions.saveClientConfig();
    }
}