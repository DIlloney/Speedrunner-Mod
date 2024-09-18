package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class ResetOptionsConfirmScreen extends AbstractModScreen {

    public ResetOptionsConfirmScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_OPTIONS_RESET);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.RESET_CONFIRM, (buttonWidget) -> {
            resetOptions();
            ModOptions.saveConfig();
            info("Successfully reset all options. Restart the game to take full effect.");
            this.client.setScreen(new ResetOptionsScreen(this.parent, MinecraftClient.getInstance().options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.NOT_NOW, (buttonWidget) -> {
            this.close();
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());
    }

    /**
     * Resets all instances of every option in the speedrunner mod.
     */
    public static void resetOptions() {
        for (BooleanOption option : OptionManager.getBooleanOptions()) {
            option.reset();
        }
        for (IntegerOption option : OptionManager.getIntegerOptions()) {
            System.out.println(option.getCurrentValue());
            option.reset();
            SpeedrunnerMod.error(String.valueOf(option.getCurrentValue()));
        }
        for (IntegerListOption option : OptionManager.getIntegerListOptions()) {
            option.reset();
        }
        for (DoubleOption option : OptionManager.getDoubleOptions()) {
            option.reset();
        }
        for (DoubleListOption option : OptionManager.getDoubleListOptions()) {
            option.reset();
        }

        options().main.structureSpawnRates = StructureSpawnRate.COMMON;
        options().main.mobSpawningRate = MobSpawningRate.HIGH;
        options().client.itemMessages = ItemMessages.ACTIONBAR;
        options().client.gameMode = GameMode.SURVIVAL;
        options().client.difficulty = Difficulty.EASY;
    }

    @Override
    public void close() {
        this.client.setScreen(new ModOptionsScreen(this.parent, MinecraftClient.getInstance().options));
    }

    @Override
    public void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.reset_options_confirm"), this.width / 2, 110, 16777215);
    }

    @Override
    protected int columns() {
        return 2;
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
}