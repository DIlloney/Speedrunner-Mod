package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

import java.io.File;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * The Speedrunner Mod's {@code "fast world creation"} options, which set the settings of each new world created.
 */
@Environment(EnvType.CLIENT)
public class FastWorldCreationOptionsScreen extends AbstractModScreen {

    public FastWorldCreationOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_FAST_WORLD_CREATION);
    }

    /**
     * All of the {@code fast world creation options.}
     */
    private SimpleOption<?>[] fwcOptions() {
        return new SimpleOption[]{
                ModListOptions.FAST_WORLD_CREATION,
                ModListOptions.GAMEMODE,
                ModListOptions.DIFFICULTY,
                ModListOptions.ALLOW_CHEATS};
    }

    @Override
    protected void init() {
        this.optionList = this.addDrawableChild(new OptionListWidget(this.client, this.width, this));
        this.optionList.addAll(fwcOptions());
        if (!options().client.fastWorldCreation) {
            for (int i = 0; i < this.optionList.children().size(); i++) {
                OptionListWidget.WidgetEntry widget = this.optionList.children().get(i);
                if (i != 0) {
                    widget.widgets.getFirst().active = false;
                }
                widget.widgets.get(1).active = false;
            }
        }
        this.addSelectableChild(this.optionList);
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
}