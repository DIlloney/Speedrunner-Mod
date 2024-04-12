<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.ModListOptions;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.option.GameOptions;

import java.io.File;

@Environment(EnvType.CLIENT)
public class ClientOptionsScreen extends AbstractModScreen {

    public ClientOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_OPTIONS_CLIENT);
    }

    @Override
    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSingleOptionEntry(ModListOptions.FOG);
        this.list.addSingleOptionEntry(this.options.getGamma());
        this.list.addSingleOptionEntry(ModListOptions.ITEM_TOOLTIPS);
        this.list.addSingleOptionEntry(ModListOptions.ITEM_MESSAGES);
        this.list.addSingleOptionEntry(ModListOptions.SHOW_DEATH_CORDS);
        this.list.addSingleOptionEntry(ModListOptions.CONFIRM_MESSAGES);
        this.list.addSingleOptionEntry(ModListOptions.PANORAMA);
        this.list.addSingleOptionEntry(ModListOptions.MOD_BUTTON_TYPE);
        this.list.addSingleOptionEntry(ModListOptions.SOCIAL_BUTTONS);
        this.addSelectableChild(this.list);
        this.configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModOptions.CONFIG);

        super.init();
    }

    @Override
    protected int columns() {
        return 3;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean isOptionsScreen() {
        return true;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
=======
package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.ModListOptions;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.option.GameOptions;

import java.io.File;

@Environment(EnvType.CLIENT)
public class ClientOptionsScreen extends AbstractModScreen {

    public ClientOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_OPTIONS_CLIENT);
    }

    @Override
    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSingleOptionEntry(ModListOptions.FOG);
        this.list.addSingleOptionEntry(this.options.getGamma());
        this.list.addSingleOptionEntry(ModListOptions.ITEM_TOOLTIPS);
        this.list.addSingleOptionEntry(ModListOptions.ITEM_MESSAGES);
        this.list.addSingleOptionEntry(ModListOptions.SHOW_DEATH_CORDS);
        this.list.addSingleOptionEntry(ModListOptions.CONFIRM_MESSAGES);
        this.list.addSingleOptionEntry(ModListOptions.PANORAMA);
        this.list.addSingleOptionEntry(ModListOptions.MOD_BUTTON_TYPE);
        this.list.addSingleOptionEntry(ModListOptions.SOCIAL_BUTTONS);
        this.addSelectableChild(this.list);
        this.configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModOptions.CONFIG);

        super.init();
    }

    @Override
    protected int columns() {
        return 3;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean isOptionsScreen() {
        return true;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
>>>>>>> Stashed changes
}