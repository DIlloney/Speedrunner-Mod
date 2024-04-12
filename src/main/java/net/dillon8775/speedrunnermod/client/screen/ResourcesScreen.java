<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
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

        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.MENU_MODS, (button) -> {
            this.client.setScreen(new ModsScreen(this.parent, MinecraftClient.getInstance().options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.MENU_TUTORIALS, (button) -> {
            this.client.setScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.MENU_WIKI, (button) -> {
            this.openLink(SpeedrunnerMod.WIKI_LINK, true);
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

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
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

        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.MENU_MODS, (button) -> {
            this.client.setScreen(new ModsScreen(this.parent, MinecraftClient.getInstance().options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.MENU_TUTORIALS, (button) -> {
            this.client.setScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.MENU_WIKI, (button) -> {
            this.openLink(SpeedrunnerMod.WIKI_LINK, true);
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