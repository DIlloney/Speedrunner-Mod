package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.ClientModOptions;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

@Environment(EnvType.CLIENT)
public class RestartRequiredScreen extends GameOptionsScreen {
    private final Screen parent;
    public static ModOptions.StructureSpawnRates currentStructureSpawnRates;
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
    public static boolean currentGenerateSpeedrunnerTrees;
    public static ClientModOptions.Panorama currentPanorama;

    public RestartRequiredScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_RESTART_REQUIRED);
        this.parent = parent;
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50 - 105, height, 100, 20, ModTexts.RESTART_NOW, (buttonWidget) -> {
            if (this.client.isInSingleplayer()) {
                this.client.world.disconnect();
                this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
            } else {
                this.client.disconnect();
            }
            info("Closing game! Re-launch to apply changes.");
            this.client.scheduleStop();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50, height, 100, 20, ModTexts.REVERT_CHANGES, (buttonWidget) -> {
            revertChanges();
            ModOptions.saveConfig();
            info("Changes reverted.");
            this.client.setScreen(this.parent);
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50 + 105, height, 100, 20, ModTexts.NOT_NOW, (buttonWidget) -> {
            this.close();
        }));
    }

    @Override
    public void close() {
        ModOptions.saveConfig();
        ClientModOptions.saveClientConfig();
        info("Saved changes.");
        this.client.setScreen(this.parent);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.restart_required.line1"), this.width / 2, 110, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.restart_required.line2"), this.width / 2, 130, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    public static void getCurrentOptions() {
        currentStructureSpawnRates = SpeedrunnerMod.options().structureSpawnRates;
        currentBetterBiomes = SpeedrunnerMod.options().betterBiomes;
        currentDoomMode = SpeedrunnerMod.options().doomMode;
        currentBetterVillagerTrades = SpeedrunnerMod.options().betterVillagerTrades;
        currentCustomBiomes = SpeedrunnerMod.options().customBiomes;
        currentCommonOres = SpeedrunnerMod.options().commonOres;
        currentStrongholdDistance = SpeedrunnerMod.options().strongholdDistance;
        currentStrongholdSpread = SpeedrunnerMod.options().strongholdSpread;
        currentStrongholdCount = SpeedrunnerMod.options().strongholdCount;
        currentStrongholdPortalRoomCount = SpeedrunnerMod.options().strongholdPortalRoomCount;
        currentStrongholdLibraryCount = SpeedrunnerMod.options().strongholdLibraryCount;
        currentGenerateSpeedrunnerTrees = SpeedrunnerMod.options().generateSpeedrunnerTrees;
        currentPanorama = SpeedrunnerModClient.clientOptions().panorama;
    }

    public static boolean needsRestart() {
        return currentStructureSpawnRates != SpeedrunnerMod.options().structureSpawnRates ||
                currentBetterBiomes != SpeedrunnerMod.options().betterBiomes ||
                currentDoomMode != SpeedrunnerMod.options().doomMode ||
                currentBetterVillagerTrades != SpeedrunnerMod.options().betterVillagerTrades ||
                currentCustomBiomes != SpeedrunnerMod.options().customBiomes ||
                currentCommonOres != SpeedrunnerMod.options().commonOres ||
                currentStrongholdDistance != SpeedrunnerMod.options().strongholdDistance ||
                currentStrongholdSpread != SpeedrunnerMod.options().strongholdSpread ||
                currentStrongholdCount != SpeedrunnerMod.options().strongholdCount ||
                currentStrongholdPortalRoomCount != SpeedrunnerMod.options().strongholdPortalRoomCount ||
                currentStrongholdLibraryCount != SpeedrunnerMod.options().strongholdLibraryCount ||
                currentGenerateSpeedrunnerTrees != SpeedrunnerMod.options().generateSpeedrunnerTrees ||
                currentPanorama != SpeedrunnerModClient.clientOptions().panorama;
    }

    private static void revertChanges() {
        SpeedrunnerMod.options().structureSpawnRates = currentStructureSpawnRates;
        SpeedrunnerMod.options().betterBiomes = currentBetterBiomes;
        SpeedrunnerMod.options().doomMode = currentDoomMode;
        SpeedrunnerMod.options().betterVillagerTrades = currentBetterVillagerTrades;
        SpeedrunnerMod.options().customBiomes = currentCustomBiomes;
        SpeedrunnerMod.options().commonOres = currentCommonOres;
        SpeedrunnerMod.options().strongholdDistance = currentStrongholdDistance;
        SpeedrunnerMod.options().strongholdSpread = currentStrongholdSpread;
        SpeedrunnerMod.options().strongholdCount = currentStrongholdCount;
        SpeedrunnerMod.options().strongholdPortalRoomCount = currentStrongholdPortalRoomCount;
        SpeedrunnerMod.options().strongholdLibraryCount = currentStrongholdLibraryCount;
        SpeedrunnerMod.options().generateSpeedrunnerTrees = currentGenerateSpeedrunnerTrees;
        SpeedrunnerModClient.clientOptions().panorama = currentPanorama;
    }
}