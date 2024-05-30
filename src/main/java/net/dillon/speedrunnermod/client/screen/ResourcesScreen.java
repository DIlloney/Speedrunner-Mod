package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;

@Environment(EnvType.CLIENT)
public class ResourcesScreen extends AbstractModScreen {

    public ResourcesScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_RESOURCES);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_MODS, (button) -> {
            this.client.setScreen(new ModsScreen(this.parent, MinecraftClient.getInstance().options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_TUTORIALS, (button) -> {
            this.client.setScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_WIKI, (button) -> {
            this.openLink(ModLinks.WIKI, true);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());

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
}