package net.dillon8775.speedrunnermod.client.gui.screen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ResetOptionsScreen extends GameOptionsScreen {
    private final Screen parent;

    public ResetOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, new LiteralText(""));
        this.parent = parent;
    }

    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 + 126;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.restart_now"), (buttonWidget) -> {
            if (this.client.isInSingleplayer()) {
                this.client.world.disconnect();
                this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
            } else {
                this.client.disconnect();
            }
            SpeedrunnerMod.LOGGER.info("Closing game! Re-open to flush changes.");
            this.client.scheduleStop();
        }));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.restart_later"), (buttonWidget) -> {
            this.client.setScreen(this.parent);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.reset_options_successful.line1"), this.width / 2, 110, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.reset_options_successful.line2"), this.width / 2, 130, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}