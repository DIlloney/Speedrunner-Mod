<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;

@Environment(EnvType.CLIENT)
public class ExternalScreen extends AbstractModScreen {

    protected ExternalScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_EXTERNAL);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.CURSEFORGE, (buttonWidget) -> {
            this.openLink(ModLinks.CURSEFORGE_LINK, false);
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.MODRINTH, (buttonWidget) -> {
            this.openLink(ModLinks.MODRINTH_LINK, false);
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.GITHUB, (buttonWidget) -> {
            this.openLink(ModLinks.GITHUB_LINK, false);
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.DISCORD, (buttonWidget) -> {
            this.openLink(ModLinks.DISCORD_LINK, false);
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.DISCORD_TOOLTIP, 200), x, y)));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.WEBPAGE, (buttonWidget) -> {
            this.openLink(ModLinks.WEBPAGE_LINK, true);
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.WEBPAGE_TOOLTIP, 200), x, y)));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.YOUTUBE, (buttonWidget) -> {
            this.openLink(ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK, true);
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.MOD_SHOWCASE_VIDEO, (buttonWidget) -> {
            this.openLink(ModLinks.MOD_SHOWCASE_VIDEO_LINK, true);
        }));

        super.init();
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
=======
package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;

@Environment(EnvType.CLIENT)
public class ExternalScreen extends AbstractModScreen {

    protected ExternalScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_EXTERNAL);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.CURSEFORGE, (buttonWidget) -> {
            this.openLink(ModLinks.CURSEFORGE_LINK, false);
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.MODRINTH, (buttonWidget) -> {
            this.openLink(ModLinks.MODRINTH_LINK, false);
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.GITHUB, (buttonWidget) -> {
            this.openLink(ModLinks.GITHUB_LINK, false);
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.DISCORD, (buttonWidget) -> {
            this.openLink(ModLinks.DISCORD_LINK, false);
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.DISCORD_TOOLTIP, 200), x, y)));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.WEBPAGE, (buttonWidget) -> {
            this.openLink(ModLinks.WEBPAGE_LINK, true);
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.WEBPAGE_TOOLTIP, 200), x, y)));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.YOUTUBE, (buttonWidget) -> {
            this.openLink(ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK, true);
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.MOD_SHOWCASE_VIDEO, (buttonWidget) -> {
            this.openLink(ModLinks.MOD_SHOWCASE_VIDEO_LINK, true);
        }));

        super.init();
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
>>>>>>> Stashed changes
}