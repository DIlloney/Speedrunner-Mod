package net.dillon8775.speedrunnermod.client.gui.screen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
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

@Environment(EnvType.CLIENT)
public class RestartRequiredScreen extends GameOptionsScreen {
    private final Screen parent;
    public static ModOptions.Main.StructureSpawnRate currentStructureSpawnRate;
    public static boolean currentBetterBiomes;
    public static boolean currentDoomMode;
    public static int currentStrongholdCount;
    public static boolean currentCustomBiomes;
    public static boolean currentCommonOres;
    public static int currentStrongholdDistance;
    public static boolean currentGenerateSpeedrunnerTrees;
    public static boolean currentNetherStrongholds;

    public RestartRequiredScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.restart_required"));
        this.parent = parent;
    }

    protected void init() {
        int height = this.height / 6 + 126;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50 - 105, height, 100, 20, new TranslatableText("speedrunnermod.restart_now"), (buttonWidget) -> {
            if (this.client.isInSingleplayer()) {
                this.client.world.disconnect();
                this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
            } else {
                this.client.disconnect();
            }
            SpeedrunnerMod.LOGGER.info("Closing game! Relaunch to apply changes.");
            this.client.scheduleStop();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50, height, 100, 20, new TranslatableText("speedrunnermod.revert_changes"), (buttonWidget) -> {
            SpeedrunnerMod.options().main.structureSpawnRate = currentStructureSpawnRate;
            SpeedrunnerMod.options().main.betterBiomes = currentBetterBiomes;
            SpeedrunnerMod.options().main.doomMode = currentDoomMode;
            SpeedrunnerMod.options().main.strongholdCount = currentStrongholdCount;
            SpeedrunnerMod.options().advanced.commonOres = currentCommonOres;
            SpeedrunnerMod.options().advanced.strongholdDistance = currentStrongholdDistance;
            ModOptions.saveConfig();
            SpeedrunnerMod.LOGGER.info("Flushed changes to Speedrunner Mod.");
            this.client.setScreen(this.parent);
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50 + 105, height, 100, 20, new TranslatableText("speedrunnermod.restart_later"), (buttonWidget) -> {
            this.client.setScreen(this.parent);
        }));
    }

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
        currentStrongholdCount = SpeedrunnerMod.options().main.strongholdCount;
        currentCustomBiomes = SpeedrunnerMod.options().advanced.customBiomes;
        currentCommonOres = SpeedrunnerMod.options().advanced.commonOres;
        currentStrongholdDistance = SpeedrunnerMod.options().advanced.strongholdDistance;
        currentGenerateSpeedrunnerTrees = SpeedrunnerMod.options().advanced.generateSpeedrunnerTrees;
    }

    public static boolean needsRestart() {
        return currentStructureSpawnRate != SpeedrunnerMod.options().main.structureSpawnRate ||
                currentBetterBiomes != SpeedrunnerMod.options().main.betterBiomes ||
                currentDoomMode != SpeedrunnerMod.options().main.doomMode ||
                currentStrongholdCount != SpeedrunnerMod.options().main.strongholdCount ||
                currentCustomBiomes != SpeedrunnerMod.options().advanced.customBiomes ||
                currentCommonOres != SpeedrunnerMod.options().advanced.commonOres ||
                currentStrongholdDistance != SpeedrunnerMod.options().advanced.strongholdDistance ||
                currentGenerateSpeedrunnerTrees != SpeedrunnerMod.options().advanced.generateSpeedrunnerTrees;
    }
}