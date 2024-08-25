package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;

@Environment(EnvType.CLIENT)
public class ResourcesScreen extends AbstractModScreen {
    protected ButtonWidget questionsAndIssuesButton, suggestionsAndFeedbackButton;

    public ResourcesScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_RESOURCES);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_MODS, (button) -> {
            this.client.setScreen(new ModsScreen(this.parent, MinecraftClient.getInstance().options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.questionsAndIssuesButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.QUESTIONS_AND_ISSUES, (button) -> {
            this.openLink(ModLinks.QUESTIONS_AND_ISSUES, true);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_WIKI, (button) -> {
            this.openLink(ModLinks.WIKI, true);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.suggestionsAndFeedbackButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.SUGGESTIONS_AND_FEEDBACK, (button) -> {
            this.openLink(ModLinks.SUGGESTIONS_AND_FEEDBACK, true);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_TUTORIALS, (button) -> {
            this.client.setScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.questionsAndIssuesButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.QUESTIONS_AND_ISSUES_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.suggestionsAndFeedbackButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.SUGGESTIONS_AND_FEEDBACK_TOOLTIP, 200), mouseX, mouseY);
        }
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