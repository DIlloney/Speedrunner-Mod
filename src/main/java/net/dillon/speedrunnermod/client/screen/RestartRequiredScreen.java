package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class RestartRequiredScreen extends AbstractModScreen {
    public static boolean currentLeaderboardsMode;
    public static boolean currentBetterBiomes;
    public static boolean currentDoomMode;
    public static boolean currentBetterVillagerTrades;
    public static boolean currentCustomBiomesAndCustomBiomeFeatures;
    public static boolean currentPanorama;
    public static boolean currentConfirmMessages;
    public static boolean currentModifiedStrongholdGeneration;
    public static boolean currentModifiedStrongholdYGeneration;
    public static boolean currentModifiedNetherFortressGeneration;
    public static boolean currentTerraBlenderSurfaceRuleDataMixin;
    public static boolean currentBackgroundRendererMixin;
    public static boolean currentSimpleOptionMixin;
    public static boolean currentLogoDrawerMixin;
    public static boolean currentRenderLayersMixin;
    public static int currentStrongholdDistance;
    public static int currentStrongholdSpread;
    public static int currentStrongholdCount;
    public static int currentStrongholdPortalRoomCount;
    public static int currentStrongholdLibraryCount;
    public static int currentSpeedrunnersWastelandBiomeWeight;

    public RestartRequiredScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_RESTART_REQUIRED);
    }

    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(ModTexts.RESTART_NOW, (buttonWidget) -> {
            this.quitWorld();
            info("Closing game! Re-launch to apply changes.");
            this.client.scheduleStop();
        }).dimensions(this.getButtonsLeftSide(), this.getButtonsHeight(), 100, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.REVERT_CHANGES, (buttonWidget) -> {
            revertChanges();
            ModOptions.saveConfig();
            info("Changes reverted.");
            this.client.setScreen(this.parent);
        }).dimensions(this.getButtonsMiddle(), this.getButtonsHeight(), 100, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.NOT_NOW, (buttonWidget) -> {
            this.close();
        }).dimensions(this.getButtonsRightSide(), this.getButtonsHeight(), 100, 20).build());
    }

    @Override
    public void close() {
        ModOptions.saveConfig();
        info("Saved changes.");
        this.client.setScreen(this.parent);
    }

    @Override
    public void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.restart_required.line1"), this.width / 2, 110, 16777215);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.restart_required.line2"), this.width / 2, 130, 16777215);
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
        currentLeaderboardsMode = options().main.leaderboardsMode.getCurrentValue();
        currentBetterBiomes = options().main.betterBiomes.getCurrentValue();
        currentDoomMode = options().main.doomMode.getCurrentValue();
        currentBetterVillagerTrades = options().main.betterVillagerTrades.getCurrentValue();
        currentCustomBiomesAndCustomBiomeFeatures = options().main.customBiomesAndCustomBiomeFeatures.getCurrentValue();
        currentPanorama = options().client.customPanorama.getCurrentValue();
        currentConfirmMessages = options().client.confirmMessages.getCurrentValue();
        currentModifiedStrongholdGeneration = options().advanced.modifiedStrongholdGeneration.getCurrentValue();
        currentModifiedStrongholdYGeneration = options().advanced.modifiedStrongholdYGeneration.getCurrentValue();
        currentModifiedNetherFortressGeneration = options().advanced.modifiedNetherFortressGeneration.getCurrentValue();
        currentTerraBlenderSurfaceRuleDataMixin = options().mixins.terraBlenderSurfaceRuleDataMixin.getCurrentValue();
        currentBackgroundRendererMixin = options().mixins.backgroundRendererMixin.getCurrentValue();
        currentSimpleOptionMixin = options().mixins.simpleOptionMixin.getCurrentValue();
        currentLogoDrawerMixin = options().mixins.logoDrawerMixin.getCurrentValue();
        currentRenderLayersMixin = options().mixins.renderLayersMixin.getCurrentValue();
        currentStrongholdDistance = options().main.strongholdDistance.getCurrentValue();
        currentStrongholdSpread = options().main.strongholdSpread.getCurrentValue();
        currentStrongholdCount = options().main.strongholdCount.getCurrentValue();
        currentStrongholdPortalRoomCount = options().main.strongholdPortalRoomCount.getCurrentValue();
        currentStrongholdLibraryCount = options().main.strongholdLibraryCount.getCurrentValue();
        currentSpeedrunnersWastelandBiomeWeight = options().advanced.speedrunnersWastelandBiomeWeight.getCurrentValue();
    }

    public static boolean needsRestart() {
        return currentLeaderboardsMode != options().main.leaderboardsMode.getCurrentValue() ||
                currentBetterBiomes != options().main.betterBiomes.getCurrentValue() ||
                currentDoomMode != options().main.doomMode.getCurrentValue() ||
                currentBetterVillagerTrades != options().main.betterVillagerTrades.getCurrentValue() ||
                currentCustomBiomesAndCustomBiomeFeatures != options().main.customBiomesAndCustomBiomeFeatures.getCurrentValue() ||
                currentPanorama != options().client.customPanorama.getCurrentValue() ||
                currentConfirmMessages != options().client.confirmMessages.getCurrentValue() ||
                currentModifiedStrongholdGeneration != options().advanced.modifiedStrongholdGeneration.getCurrentValue() ||
                currentModifiedStrongholdYGeneration != options().advanced.modifiedStrongholdYGeneration.getCurrentValue() ||
                currentModifiedNetherFortressGeneration != options().advanced.modifiedNetherFortressGeneration.getCurrentValue() ||
                currentTerraBlenderSurfaceRuleDataMixin != options().mixins.terraBlenderSurfaceRuleDataMixin.getCurrentValue() ||
                currentBackgroundRendererMixin != options().mixins.backgroundRendererMixin.getCurrentValue() ||
                currentSimpleOptionMixin != options().mixins.simpleOptionMixin.getCurrentValue() ||
                currentLogoDrawerMixin != options().mixins.logoDrawerMixin.getCurrentValue() ||
                currentRenderLayersMixin != options().mixins.renderLayersMixin.getCurrentValue() ||
                currentStrongholdDistance != options().main.strongholdDistance.getCurrentValue() ||
                currentStrongholdSpread != options().main.strongholdSpread.getCurrentValue() ||
                currentStrongholdCount != options().main.strongholdCount.getCurrentValue() ||
                currentStrongholdPortalRoomCount != options().main.strongholdPortalRoomCount.getCurrentValue() ||
                currentStrongholdLibraryCount != options().main.strongholdLibraryCount.getCurrentValue() ||
                currentSpeedrunnersWastelandBiomeWeight != options().advanced.speedrunnersWastelandBiomeWeight.getCurrentValue();
    }

    private static void revertChanges() {
        options().main.leaderboardsMode.set(currentLeaderboardsMode);
        options().main.betterBiomes.set(currentBetterBiomes);
        options().main.doomMode.set(currentDoomMode);
        options().main.betterVillagerTrades.set(currentBetterVillagerTrades);
        options().main.customBiomesAndCustomBiomeFeatures.set(currentCustomBiomesAndCustomBiomeFeatures);
        options().client.customPanorama.set(currentPanorama);
        options().client.confirmMessages.set(currentConfirmMessages);
        options().advanced.modifiedStrongholdGeneration.set(currentModifiedStrongholdGeneration);
        options().advanced.modifiedStrongholdYGeneration.set(currentModifiedStrongholdYGeneration);
        options().advanced.modifiedNetherFortressGeneration.set(currentModifiedNetherFortressGeneration);
        options().mixins.terraBlenderSurfaceRuleDataMixin.set(currentTerraBlenderSurfaceRuleDataMixin);
        options().mixins.backgroundRendererMixin.set(currentBackgroundRendererMixin);
        options().mixins.simpleOptionMixin.set(currentSimpleOptionMixin);
        options().mixins.logoDrawerMixin.set(currentLogoDrawerMixin);
        options().mixins.renderLayersMixin.set(currentRenderLayersMixin);
        options().main.strongholdDistance.set(currentStrongholdDistance);
        options().main.strongholdSpread.set(currentStrongholdSpread);
        options().main.strongholdCount.set(currentStrongholdCount);
        options().main.strongholdPortalRoomCount.set(currentStrongholdPortalRoomCount);
        options().main.strongholdLibraryCount.set(currentStrongholdLibraryCount);
        options().advanced.speedrunnersWastelandBiomeWeight.set(currentSpeedrunnersWastelandBiomeWeight);
    }
}