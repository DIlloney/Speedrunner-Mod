package net.dillon.speedrunnermod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CreditsScreen extends AbstractModScreen {

    public CreditsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_CREDITS);
    }

    @Override
    public void renderCustomObjects(MatrixStack matrices) {
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/credits.png"));
        drawTexture(matrices, this.width / 2 - 159, this.height / 6 + 18, 0.0F, 0.0F, 320, 180, 320, 180);
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