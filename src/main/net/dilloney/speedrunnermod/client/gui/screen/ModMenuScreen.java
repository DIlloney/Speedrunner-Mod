package net.dilloney.speedrunnermod.client.gui.screen;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ModMenuScreen extends GameOptionsScreen {
    private final GameOptions options;

    public ModMenuScreen(Screen parent, GameOptions options) {
        super(parent, options, SpeedrunnerMod.SPEEDRUNNER_MOD_TITLE);
        this.options = options;
    }

    protected void init() {
        super.init();
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("menu.options"), (buttonWidget) -> {
            this.client.openScreen(new ModOptionsScreen(this, this.options));
        }));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.leaderboards.title"), (buttonWidget) -> {
            this.client.openScreen(new LeaderboardsScreen(this, this.options));
        }));
        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.resources.title"), (buttonWidget) -> {
            this.client.openScreen(new ResourcesScreen(this, this.options));
        }));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.socials.title"), (buttonWidget) -> {
            this.client.openScreen(new SocialsScreen(this, this.options));
        }));
        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.advanced"), (buttonWidget) -> {
            this.client.openScreen(new AdvancedOptionsScreen(this, this.options));
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.openScreen(this.parent);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}