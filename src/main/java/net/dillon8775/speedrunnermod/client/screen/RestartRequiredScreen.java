<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class RestartRequiredScreen extends AbstractModScreen {
    public static ModOptions.StructureSpawnRates currentStructureSpawnRates;
    public static boolean currentLeaderboardsMode;
    public static boolean currentBetterBiomes;
    public static boolean currentDoomMode;
    public static boolean currentBetterVillagerTrades;
    public static boolean currentCustomBiomes;
    public static boolean currentCommonOres;
    public static int currentStrongholdDistance;
    public static int currentStrongholdSpread;
    public static int currentStrongholdCount;
    public static int currentStrongholdPortalRoomCount;
    public static int currentStrongholdLibraryCount;
    public static ModOptions.Panorama currentPanorama;
    public static boolean currentConfirmMessages;
    public static boolean currentStackUnstackables;

    public RestartRequiredScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_RESTART_REQUIRED);
    }

    @Override
    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), this.getButtonsHeight(), 100, 20, ModTexts.RESTART_NOW, (buttonWidget) -> {
            this.quitWorld();
            info("Closing game! Re-launch to apply changes.");
            this.client.scheduleStop();
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsMiddle(), this.getButtonsHeight(), 100, 20, ModTexts.REVERT_CHANGES, (buttonWidget) -> {
            revertChanges();
            ModOptions.saveConfig();
            info("Changes reverted.");
            this.client.setScreen(this.parent);
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), this.getButtonsHeight(), 100, 20, ModTexts.NOT_NOW, (buttonWidget) -> {
            this.close();
        }));
    }

    @Override
    public void close() {
        ModOptions.saveConfig();
        info("Saved changes.");
        this.client.setScreen(this.parent);
    }

    @Override
    public void renderCustomText(MatrixStack matrices) {
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.restart_required.line1"), this.width / 2, 110, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.restart_required.line2"), this.width / 2, 130, 16777215);
    }

    @Override
    protected int getButtonsHeight() {
        return this.height / 6 + 126;
    }

    @Override
    protected int columns() {
        return 3;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }

    public static void getCurrentOptions() {
        currentStructureSpawnRates = options().main.structureSpawnRates;
        currentLeaderboardsMode = options().main.leaderboardsMode;
        currentBetterBiomes = options().main.betterBiomes;
        currentDoomMode = options().main.doomMode;
        currentBetterVillagerTrades = options().main.betterVillagerTrades;
        currentCustomBiomes = options().main.customBiomes;
        currentCommonOres = options().main.commonOres;
        currentStrongholdDistance = options().main.strongholdDistance;
        currentStrongholdSpread = options().main.strongholdSpread;
        currentStrongholdCount = options().main.strongholdCount;
        currentStrongholdPortalRoomCount = options().main.strongholdPortalRoomCount;
        currentStrongholdLibraryCount = options().main.strongholdLibraryCount;
        currentPanorama = options().client.panorama;
        currentConfirmMessages = options().client.confirmMessages;
        currentStackUnstackables = options().main.stackUnstackables;
    }

    public static boolean needsRestart() {
        return currentStructureSpawnRates != options().main.structureSpawnRates ||
                currentLeaderboardsMode != options().main.leaderboardsMode ||
                currentBetterBiomes != options().main.betterBiomes ||
                currentDoomMode != options().main.doomMode ||
                currentBetterVillagerTrades != options().main.betterVillagerTrades ||
                currentCustomBiomes != options().main.customBiomes ||
                currentCommonOres != options().main.commonOres ||
                currentStrongholdDistance != options().main.strongholdDistance ||
                currentStrongholdSpread != options().main.strongholdSpread ||
                currentStrongholdCount != options().main.strongholdCount ||
                currentStrongholdPortalRoomCount != options().main.strongholdPortalRoomCount ||
                currentStrongholdLibraryCount != options().main.strongholdLibraryCount ||
                currentPanorama != options().client.panorama ||
                currentConfirmMessages != options().client.confirmMessages ||
                currentStackUnstackables != options().main.stackUnstackables;
    }

    private static void revertChanges() {
        options().main.structureSpawnRates = currentStructureSpawnRates;
        options().main.leaderboardsMode = currentLeaderboardsMode;
        options().main.betterBiomes = currentBetterBiomes;
        options().main.doomMode = currentDoomMode;
        options().main.betterVillagerTrades = currentBetterVillagerTrades;
        options().main.customBiomes = currentCustomBiomes;
        options().main.commonOres = currentCommonOres;
        options().main.strongholdDistance = currentStrongholdDistance;
        options().main.strongholdSpread = currentStrongholdSpread;
        options().main.strongholdCount = currentStrongholdCount;
        options().main.strongholdPortalRoomCount = currentStrongholdPortalRoomCount;
        options().main.strongholdLibraryCount = currentStrongholdLibraryCount;
        options().main.stackUnstackables = currentStackUnstackables;
        options().client.panorama = currentPanorama;
        options().client.confirmMessages = currentConfirmMessages;
    }
=======
package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class RestartRequiredScreen extends AbstractModScreen {
    public static ModOptions.StructureSpawnRates currentStructureSpawnRates;
    public static boolean currentLeaderboardsMode;
    public static boolean currentBetterBiomes;
    public static boolean currentDoomMode;
    public static boolean currentBetterVillagerTrades;
    public static boolean currentCustomBiomes;
    public static boolean currentCommonOres;
    public static int currentStrongholdDistance;
    public static int currentStrongholdSpread;
    public static int currentStrongholdCount;
    public static int currentStrongholdPortalRoomCount;
    public static int currentStrongholdLibraryCount;
    public static ModOptions.Panorama currentPanorama;
    public static boolean currentConfirmMessages;
    public static boolean currentStackUnstackables;

    public RestartRequiredScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_RESTART_REQUIRED);
    }

    @Override
    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), this.getButtonsHeight(), 100, 20, ModTexts.RESTART_NOW, (buttonWidget) -> {
            this.quitWorld();
            info("Closing game! Re-launch to apply changes.");
            this.client.scheduleStop();
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsMiddle(), this.getButtonsHeight(), 100, 20, ModTexts.REVERT_CHANGES, (buttonWidget) -> {
            revertChanges();
            ModOptions.saveConfig();
            info("Changes reverted.");
            this.client.setScreen(this.parent);
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), this.getButtonsHeight(), 100, 20, ModTexts.NOT_NOW, (buttonWidget) -> {
            this.close();
        }));
    }

    @Override
    public void close() {
        ModOptions.saveConfig();
        info("Saved changes.");
        this.client.setScreen(this.parent);
    }

    @Override
    public void renderCustomText(MatrixStack matrices) {
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.restart_required.line1"), this.width / 2, 110, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.restart_required.line2"), this.width / 2, 130, 16777215);
    }

    @Override
    protected int getButtonsHeight() {
        return this.height / 6 + 126;
    }

    @Override
    protected int columns() {
        return 3;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }

    public static void getCurrentOptions() {
        currentStructureSpawnRates = options().main.structureSpawnRates;
        currentLeaderboardsMode = options().main.leaderboardsMode;
        currentBetterBiomes = options().main.betterBiomes;
        currentDoomMode = options().main.doomMode;
        currentBetterVillagerTrades = options().main.betterVillagerTrades;
        currentCustomBiomes = options().main.customBiomes;
        currentCommonOres = options().main.commonOres;
        currentStrongholdDistance = options().main.strongholdDistance;
        currentStrongholdSpread = options().main.strongholdSpread;
        currentStrongholdCount = options().main.strongholdCount;
        currentStrongholdPortalRoomCount = options().main.strongholdPortalRoomCount;
        currentStrongholdLibraryCount = options().main.strongholdLibraryCount;
        currentPanorama = options().client.panorama;
        currentConfirmMessages = options().client.confirmMessages;
        currentStackUnstackables = options().main.stackUnstackables;
    }

    public static boolean needsRestart() {
        return currentStructureSpawnRates != options().main.structureSpawnRates ||
                currentLeaderboardsMode != options().main.leaderboardsMode ||
                currentBetterBiomes != options().main.betterBiomes ||
                currentDoomMode != options().main.doomMode ||
                currentBetterVillagerTrades != options().main.betterVillagerTrades ||
                currentCustomBiomes != options().main.customBiomes ||
                currentCommonOres != options().main.commonOres ||
                currentStrongholdDistance != options().main.strongholdDistance ||
                currentStrongholdSpread != options().main.strongholdSpread ||
                currentStrongholdCount != options().main.strongholdCount ||
                currentStrongholdPortalRoomCount != options().main.strongholdPortalRoomCount ||
                currentStrongholdLibraryCount != options().main.strongholdLibraryCount ||
                currentPanorama != options().client.panorama ||
                currentConfirmMessages != options().client.confirmMessages ||
                currentStackUnstackables != options().main.stackUnstackables;
    }

    private static void revertChanges() {
        options().main.structureSpawnRates = currentStructureSpawnRates;
        options().main.leaderboardsMode = currentLeaderboardsMode;
        options().main.betterBiomes = currentBetterBiomes;
        options().main.doomMode = currentDoomMode;
        options().main.betterVillagerTrades = currentBetterVillagerTrades;
        options().main.customBiomes = currentCustomBiomes;
        options().main.commonOres = currentCommonOres;
        options().main.strongholdDistance = currentStrongholdDistance;
        options().main.strongholdSpread = currentStrongholdSpread;
        options().main.strongholdCount = currentStrongholdCount;
        options().main.strongholdPortalRoomCount = currentStrongholdPortalRoomCount;
        options().main.strongholdLibraryCount = currentStrongholdLibraryCount;
        options().main.stackUnstackables = currentStackUnstackables;
        options().client.panorama = currentPanorama;
        options().client.confirmMessages = currentConfirmMessages;
    }
>>>>>>> Stashed changes
}