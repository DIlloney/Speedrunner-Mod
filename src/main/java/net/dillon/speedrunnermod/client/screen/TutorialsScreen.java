package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class TutorialsScreen extends AbstractModScreen {
    protected ButtonWidget bastionRoutesButton, netherFortressesButton, microlensingButton, blindTravelButton, oneCyclingButton, pieChartButton, f3MenuButton, buriedTreasureButton, otherUsefulTricksButton;

    public TutorialsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_TUTORIALS);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.bastionRoutesButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes"), (button) -> {
            this.client.setScreen(new BastionRoutesScreen(this.parent, MinecraftClient.getInstance().options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.netherFortressesButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.nether_fortresses"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=pmx9LyUvLTk", false);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.microlensingButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing"), (button) -> {
            this.client.setScreen(new MicrolensingScreen(this.parent, MinecraftClient.getInstance().options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.blindTravelButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.blind_travel"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=Ou58P7e-ZY0", false);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.oneCyclingButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.one_cycling"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=JaVyuTyDxxs", false);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.pieChartButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.pie_chart"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=ENgEBHIifm8", false);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.f3MenuButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.f3_menu"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=-fSr7P5LQJY", false);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.buriedTreasureButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.buried_treasure"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=_dyD8ZwagDg", false);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.otherUsefulTricksButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.other_useful_tricks"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=TvvApbI6fis", false);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            this.close();
        }).dimensions(this.width / 2 - 100, this.height - 29, 200, 20).build());
    }

    @Override
    protected void renderTooltips(MatrixStack matrices, int mouseX, int mouseY) {
        if (this.bastionRoutesButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.tooltip"), 200), mouseX, mouseY);
        }
        if (this.netherFortressesButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.menu.resources.tutorials.nether_fortresses.tooltip"), 200), mouseX, mouseY);
        }
        if (this.microlensingButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing.tooltip"), 200), mouseX, mouseY);
        }
        if (this.blindTravelButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.menu.resources.tutorials.blind_travel.tooltip"), 200), mouseX, mouseY);
        }
        if (this.oneCyclingButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.menu.resources.tutorials.one_cycling.tooltip"), 200), mouseX, mouseY);
        }
        if (this.pieChartButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.menu.resources.tutorials.pie_chart.tooltip"), 200), mouseX, mouseY);
        }
        if (this.buriedTreasureButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.menu.resources.tutorials.buried_treasure.tooltip"), 200), mouseX, mouseY);
        }
        if (this.otherUsefulTricksButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.menu.resources.tutorials.other_useful_tricks.tooltip"), 200), mouseX, mouseY);
        }
        super.renderTooltips(matrices, mouseX, mouseY);
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

    private static class BastionRoutesScreen extends AbstractModScreen {

        public BastionRoutesScreen(Screen parent, GameOptions options) {
            super(parent, options, Text.translatable("speedrunnermod.title.resources.tutorials.bastion_routes"));
        }

        @Override
        protected void init() {
            int height = this.getButtonsHeight();

            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.treasure"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=np6fc_z9LUY", false);
            }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.bridge"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=Dts81nEnzuQ", false);
            }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.stables"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=WIN-ZtUJfIc", false);
            }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.housing"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=S0G5asEjrgk", false);
            }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

            this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
                this.close();
            }).dimensions(this.width / 2 - 100, this.height - 29, 200, 20).build());
        }

        @Override
        public void close() {
            this.client.setScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
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

    private static class MicrolensingScreen extends AbstractModScreen {

        public MicrolensingScreen(Screen parent, GameOptions options) {
            super(parent, options, Text.translatable("speedrunnermod.title.resources.tutorials.microlensing"));
        }

        @Override
        protected void init() {
            int height = this.getButtonsHeight();

            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing.bastion"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=jvTfMLPnMSw", false);
            }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing.fortress"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=Kl_-L9XbJko", false);
            }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

            this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
                this.close();
            }).dimensions(this.width / 2 - 100, this.height - 29, 200, 20).build());
        }

        @Override
        public void close() {
            this.client.setScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
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
}