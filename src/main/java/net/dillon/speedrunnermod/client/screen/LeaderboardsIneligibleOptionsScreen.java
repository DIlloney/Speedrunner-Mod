package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
class LeaderboardsIneligibleOptionsScreen extends AbstractModScreen {
    private final Screen parent;
    public static boolean fromInitialBoot = true;

    protected LeaderboardsIneligibleOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_INELIGIBLE_OPTIONS);
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(ModTexts.OK, (button) -> {
            this.close();
        }).dimensions(this.width / 2 - 100, this.height - 29, 200, 20).build());
    }

    @Override
    public void close() {
        if (fromInitialBoot) {
            this.client.setScreen(new LeaderboardsSafeScreen(this.parent, this.options));
        } else {
            this.client.setScreen(new LeaderboardsIneligibleScreen(this.parent, this.options));
        }
    }

    @ChatGPT
    @Override
    public void renderCustomText(MatrixStack matrices) {
        boolean longList = Leaderboards.ineligibleOptions.size() > 12;
        int textHeight = longList ? 35 : 50;
        for (int i = 0; i < Leaderboards.ineligibleOptions.size(); i++) {
            drawCenteredText(matrices, this.textRenderer, Leaderboards.ineligibleOptions.get(i), this.width / 2, textHeight, 16777215);
            textHeight = longList ? textHeight + 10 : textHeight + 20;
        }
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return true;
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