package net.dillon8775.speedrunnermod.client.gui.screen.features.ores_and_worldgen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.client.gui.screen.FeaturesScreen;
import net.dillon8775.speedrunnermod.client.gui.screen.text.ModScreenTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SpeedrunnersWastelandBiomeScreen extends GameOptionsScreen {
    private final Screen parent;
    protected final LiteralText pageNumber = new LiteralText("§lPage:§r 1/6");

    public SpeedrunnersWastelandBiomeScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.features.ores_and_worldgen.speedrunners_wasteland"));
        this.parent = parent;
    }

    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int farLeftSide = leftSide - 80;
        int farRightSide = rightSide + 80;
        int middle = this.width / 2 - 75;
        int height = this.height - 29;

        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, ModScreenTexts.NEXT, (buttonWidget) -> {
            this.client.setScreen(new SpeedrunnerOresScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, ScreenTexts.DONE, (button) -> {
            this.client.setScreen(new FeaturesScreen(this.parent, MinecraftClient.getInstance().options));
        }));
    }

    public void onClose() {
        this.client.setScreen(new FeaturesScreen(this.parent, MinecraftClient.getInstance().options));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.features.ores_and_worldgen.speedrunners_wasteland.line1"), this.width / 2, 70, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.features.ores_and_worldgen.speedrunners_wasteland.line2"), this.width / 2, 90, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.features.ores_and_worldgen.speedrunners_wasteland.line3"), this.width / 2, 110, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.features.ores_and_worldgen.speedrunners_wasteland.line4"), this.width / 2, 130, 16777215);

        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int farRightSide = rightSide + 273;
        int height = this.height - 24;
        drawCenteredText(matrices, this.textRenderer, this.pageNumber, farRightSide, height, 16777215);

        int middle = this.width / 2 - 80;
        height = this.height / 6 + 126;
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/features/ores_and_worldgen/speedrunners_wasteland_biome.png"));
        drawTexture(matrices, middle, height, 0.0F, 0.0F, 160, 90, 160, 90);
        super.render(matrices, mouseX, mouseY, delta);
    }
}