package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

@Environment(EnvType.CLIENT)
public class ResetOptionsScreen extends AbstractModScreen {

    public ResetOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.RESTART_NOW, (buttonWidget) -> {
            this.quitWorld();
            info("Closing game! Re-launch to apply changes.");
            this.client.scheduleStop();
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.RESTART_LATER, (buttonWidget) -> {
            this.client.setScreen(this.parent);
        }));
    }

    @Override
    public void renderCustomText(MatrixStack matrices) {
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.reset_options_successful.line1"), this.width / 2, 110, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.reset_options_successful.line2"), this.width / 2, 130, 16777215);
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
        return false;
    }
}