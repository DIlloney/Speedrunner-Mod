package net.dilloney.speedrunnermod.client;

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
        this.addButton(new ButtonWidget(this.width / 2 - 155, 40, 150, 20, new TranslatableText("speedrunnermod.options.title"), (buttonWidget) -> this.client.openScreen(new ModOptionsScreen(this, this.options))));
        this.addButton(new ButtonWidget(this.width / 2 + 5, 40, 150, 20, new TranslatableText("speedrunnermod.leaderboards.title"), (buttonWidget) -> this.client.openScreen(new LeaderboardsScreen(this, this.options))));
        this.addButton(new ButtonWidget(this.width / 2 - 155, 65, 150, 20, new TranslatableText("speedrunnermod.resources.title"), (buttonWidget) -> this.client.openScreen(new ResourcesScreen(this, this.options))));
        this.addButton(new ButtonWidget(this.width / 2 + 5, 65, 150, 20, new TranslatableText("speedrunnermod.socials.title"), (buttonWidget) -> this.client.openScreen(new SocialsScreen(this, this.options))));
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> this.client.openScreen(this.parent)));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 12, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}