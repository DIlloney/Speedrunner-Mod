package net.dillon8775.speedrunnermod.client.gui.screen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
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
    public static ModOptions.Main.StructureSpawnRate currentStructureSpawnRate;
    public static boolean currentBetterBiomes;
    public static boolean currentDoomMode;
    public static boolean currentCustomBiomes;
    public static boolean currentCommonOres;
    public static int currentStrongholdDistance;
    public static int currentStrongholdSpread;
    public static int currentStrongholdCount;
    public static boolean currentGenerateSpeedrunnerTrees;
    public static ClientModOptions.Panorama currentPanorama;

    public RestartRequiredScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.restart_required"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50 - 105, height, 100, 20, new TranslatableText("speedrunnermod.restart_now"), (buttonWidget) -> {
            if (this.client.isInSingleplayer()) {
                this.client.world.disconnect();
                this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
            } else {
                this.client.disconnect();
            }
            info("Closing game! Re-launch to apply changes.");
            this.client.scheduleStop();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50, height, 100, 20, new TranslatableText("speedrunnermod.revert_changes"), (buttonWidget) -> {
            revertChanges();
            ModOptions.saveConfig();
            info("Changes reverted.");
            this.client.setScreen(this.parent);
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50 + 105, height, 100, 20, new TranslatableText("speedrunnermod.restart_later"), (buttonWidget) -> {
            this.client.setScreen(this.parent);
        }));
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
        currentStructureSpawnRate = SpeedrunnerMod.options().main.structureSpawnRate;
        currentBetterBiomes = SpeedrunnerMod.options().main.betterBiomes;
        currentDoomMode = SpeedrunnerMod.options().main.doomMode;
        currentCustomBiomes = SpeedrunnerMod.options().advanced.customBiomes;
        currentCommonOres = SpeedrunnerMod.options().advanced.commonOres;
        currentStrongholdDistance = SpeedrunnerMod.options().advanced.strongholdDistance;
        currentStrongholdSpread = SpeedrunnerMod.options().advanced.strongholdSpread;
        currentStrongholdCount = SpeedrunnerMod.options().advanced.strongholdCount;
        currentGenerateSpeedrunnerTrees = SpeedrunnerMod.options().advanced.generateSpeedrunnerTrees;
        currentPanorama = SpeedrunnerModClient.clientOptions().panorama;
    }

    public static boolean needsRestart() {
        return currentStructureSpawnRate != SpeedrunnerMod.options().main.structureSpawnRate ||
                currentBetterBiomes != SpeedrunnerMod.options().main.betterBiomes ||
                currentDoomMode != SpeedrunnerMod.options().main.doomMode ||
                currentCustomBiomes != SpeedrunnerMod.options().advanced.customBiomes ||
                currentCommonOres != SpeedrunnerMod.options().advanced.commonOres ||
                currentStrongholdDistance != SpeedrunnerMod.options().advanced.strongholdDistance ||
                currentStrongholdSpread != SpeedrunnerMod.options().advanced.strongholdSpread ||
                currentStrongholdCount != SpeedrunnerMod.options().advanced.strongholdCount ||
                currentGenerateSpeedrunnerTrees != SpeedrunnerMod.options().advanced.generateSpeedrunnerTrees ||
                currentPanorama != SpeedrunnerModClient.clientOptions().panorama;
    }

    private static void revertChanges() {
        SpeedrunnerMod.options().main.structureSpawnRate = currentStructureSpawnRate;
        SpeedrunnerMod.options().main.betterBiomes = currentBetterBiomes;
        SpeedrunnerMod.options().main.doomMode = currentDoomMode;
        SpeedrunnerMod.options().advanced.commonOres = currentCommonOres;
        SpeedrunnerMod.options().advanced.strongholdDistance = currentStrongholdDistance;
        SpeedrunnerMod.options().advanced.strongholdSpread = currentStrongholdSpread;
        SpeedrunnerMod.options().advanced.strongholdCount = currentStrongholdCount;
        SpeedrunnerMod.options().advanced.generateSpeedrunnerTrees = currentGenerateSpeedrunnerTrees;
        SpeedrunnerModClient.clientOptions().panorama = currentPanorama;
    }
}