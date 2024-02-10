package net.dillon8775.speedrunnermod.client.gui.screen.features.blocks_and_items;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.client.gui.screen.FeaturesScreen;
import net.dillon8775.speedrunnermod.client.gui.screen.features.miscellaneous.ResetKeyScreen;
import net.dillon8775.speedrunnermod.client.gui.screen.features.ores_and_worldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon8775.speedrunnermod.client.gui.screen.features.tools_and_armor.SpeedrunnerArmorScreen;
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
public class GoldenFoodItemsScreen extends GameOptionsScreen {
    private final Screen parent;
    protected final LiteralText pageNumber = new LiteralText("§lPage:§r 12/12");

    public GoldenFoodItemsScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.features.blocks_and_items.golden_food_items"));
        this.parent = parent;
    }

    protected void init() {
        int leftSide = this.width / 2 - 155;
        int farLeftSide = leftSide - 40;
        int height = this.height / 6 + 115;

        this.addDrawableChild(new ButtonWidget(farLeftSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.features.tools_and_armor"), (buttonWidget) -> {
            this.client.setScreen(new SpeedrunnerArmorScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(farLeftSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.features.ores_and_worldgen"), (buttonWidget) -> {
            this.client.setScreen(new SpeedrunnersWastelandBiomeScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(farLeftSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.features.miscellaneous"), (buttonWidget) -> {
            this.client.setScreen(new ResetKeyScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(farLeftSide, height, 150, 20, ModScreenTexts.PREVIOUS, (buttonWidget) -> {
            this.client.setScreen(new SpeedrunnerBulkScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(farLeftSide, height, 150, 20, ScreenTexts.DONE, (buttonWidget) -> {
            this.client.setScreen(new FeaturesScreen(this.parent, MinecraftClient.getInstance().options));
        }));
    }

    public void onClose() {
        this.client.setScreen(new FeaturesScreen(this.parent, MinecraftClient.getInstance().options));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.features.blocks_and_items.golden_food_items.line1"), this.width / 2, 80, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.features.blocks_and_items.golden_food_items.line2"), this.width / 2, 100, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("speedrunnermod.features.blocks_and_items.golden_food_items.line3"), this.width / 2, 120, 16777215);

        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int farRightSide = rightSide + 273;
        int height = this.height - 24;
        drawCenteredText(matrices, this.textRenderer, this.pageNumber, farRightSide, height, 16777215);

        leftSide = this.width / 2 - 155;
        rightSide = leftSide + 200;
        height = this.height / 6 + 126;
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/features/blocks_and_items/golden_food_items.png"));
        drawTexture(matrices, rightSide, height, 0.0F, 0.0F, 160, 90, 160, 90);
        super.render(matrices, mouseX, mouseY, delta);
    }
}