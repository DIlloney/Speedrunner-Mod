package net.dillon8775.speedrunnermod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.screen.options.ModOptionsScreen;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class ModMenuScreen extends GameOptionsScreen {
    private final Screen parent;
    private ButtonWidget doomModeButton;

    public ModMenuScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE);
        this.parent = parent;
    }

    @Override
    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;

        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, ModTexts.MENU_OPTIONS, (button) -> {
            RestartRequiredScreen.getCurrentOptions();
            this.client.setScreen(new ModOptionsScreen(this, MinecraftClient.getInstance().options));
        }));

        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, ModTexts.MENU_FEATURES, (button) -> {
            this.client.setScreen(new FeaturesScreen(this, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, ModTexts.MENU_RESOURCES, (button) -> {
            this.client.setScreen(new ResourcesScreen(this, MinecraftClient.getInstance().options));
        }));

        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, ModTexts.MENU_EXTERNAL, (button) -> {
            this.client.setScreen(new ExternalScreen(this, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, ModTexts.MENU_CREDITS, (button) -> {
            this.client.setScreen(new CreditsScreen(this, MinecraftClient.getInstance().options));
        }));

        doomModeButton = this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, ModTexts.MENU_DOOM_MODE, (button) -> {
            if (DoomModeScreen.doomModeButtonAlreadyClicked > 0) {
                this.client.setScreen(new DoomModeScreen.ScreenFive(this, MinecraftClient.getInstance().options));
            } else {
                this.client.setScreen(new DoomModeScreen(this, MinecraftClient.getInstance().options));
            }
        }));
        doomModeButton.visible = SpeedrunnerMod.options().doomMode;

        height += doomModeButton.visible ? 24 : 0;
        this.addDrawableChild(new ButtonWidget(doomModeButton.visible ? leftSide : rightSide, height, 150, 20, ModTexts.EASIER_SPEEDRUNNING_MOD, (button) -> {
            Util.getOperatingSystem().open("https://sites.google.com/view/dillon8775/easier-speedrunning-mod");
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.setScreen(this.parent);
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);

        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int farRightSide = rightSide + 267;
        int height = this.height - 24;
        drawCenteredText(matrices, this.textRenderer, SpeedrunnerMod.VERSION, farRightSide, height, 16777215);

        int middle = this.width / 2 - 69;
        height = 15;
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/speedrunner_mod.png"));
        drawTexture(matrices, middle, height, 0.0F, 0.0F, 129, 16, 129, 16);
        super.render(matrices, mouseX, mouseY, delta);
    }
}