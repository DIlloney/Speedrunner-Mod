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
import net.minecraft.client.option.SimpleOption;

import java.io.File;

@Environment(EnvType.CLIENT)
public class FastWorldCreationOptionsScreen extends AbstractModScreen {

    public FastWorldCreationOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_FAST_WORLD_CREATION);
    }

    @Override
    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addAll(fwcOptions());
        this.addSelectableChild(this.list);
        this.configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModOptions.CONFIG);

        super.init();
    }

    private static SimpleOption<?>[] fwcOptions() {
        return new SimpleOption[]{ModListOptions.FAST_WORLD_CREATION, ModListOptions.GAMEMODE, ModListOptions.DIFFICULTY, ModListOptions.ALLOW_CHEATS};
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
}