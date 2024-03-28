package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;

@Environment(EnvType.CLIENT)
public class ModsScreen extends AbstractModScreen {

    public ModsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_MODS);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.SODIUM, (buttonWidget) ->  {
            this.openLink(ModLinks.SODIUM_MOD_LINK, false);
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.SODIUM_TOOLTIP, 200), x, y)));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.LITHIUM, (buttonWidget) -> {
            this.openLink(ModLinks.LITHIUM_MOD_LINK, false);
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.LITHIUM_TOOLTIP, 200), x, y)));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.PHOSPHOR, (buttonWidget) -> {
            this.openLink(ModLinks.PHOSPHOR_MOD_LINK, false);
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.PHOSPHOR_TOOLTIP, 200), x, y)));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.SPEEDRUN_IGT, (buttonWidget) -> {
            this.openLink(ModLinks.SPEEDRUN_IGT_MOD_LINK, false);
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.SPEEDRUN_IGT_TOOLTIP, 200), x, y)));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.LAZYDFU, (buttonWidget) -> {
            this.openLink(ModLinks.LAZYDFU_MOD_LINK, false);
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.LAZYDFU_TOOLTIP, 200), x, y)));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.KRYPTON, (buttonWidget) -> {
            this.openLink(ModLinks.KRYPTON_MOD_LINK, false);
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.KRYPTON_TOOLTIP, 200), x, y)));

        height += 24;
        ButtonWidget optifine = this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.OPTIFINE, (buttonWidget) -> {}, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.OPTIFINE_TOOLTIP, 200), x, y)));
        optifine.active = false;

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            this.close();
        }));
    }

    @Override
    public void close() {
        this.client.setScreen(new ResourcesScreen(this.parent, MinecraftClient.getInstance().options));
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