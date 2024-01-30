package net.dillon8775.speedrunnermod.client.gui.screen;

import net.dillon8775.speedrunnermod.client.gui.screen.features.blocks_and_items.SpeedrunnerIngotsScreen;
import net.dillon8775.speedrunnermod.client.gui.screen.features.miscellaneous.ResetKeyScreen;
import net.dillon8775.speedrunnermod.client.gui.screen.features.ores_and_worldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon8775.speedrunnermod.client.gui.screen.features.tools_and_armor.SpeedrunnerArmorScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class FeaturesScreen extends GameOptionsScreen {
    private final Screen parent;

    public FeaturesScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.features"));
        this.parent = parent;
    }

    protected void init() {
        super.init();

        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;

        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.features.blocks_and_items"), (buttonWidget) -> {
            this.client.setScreen(new SpeedrunnerIngotsScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.features.tools_and_armor"), (buttonWidget) -> {
            this.client.setScreen(new SpeedrunnerArmorScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.features.ores_and_worldgen"), (buttonWidget) -> {
            this.client.setScreen(new SpeedrunnersWastelandBiomeScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.features.miscellaneous"), (buttonWidget) -> {
            this.client.setScreen(new ResetKeyScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.setScreen(this.parent);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}