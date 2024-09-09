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
 * The Speedrunner Mod's {@code client options screen.}
 */
@Environment(EnvType.CLIENT)
public class ClientOptionsScreen extends AbstractModScreen {

    public ClientOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_OPTIONS_CLIENT);
    }

    /**
     * All of the {@code client-side speedrunner mod options.}
     */
    private SimpleOption<?>[] clientOptions(GameOptions gameOptions) {
        return new SimpleOption[]{
                ModListOptions.FOG,
                gameOptions.getGamma(),
                ModListOptions.ITEM_TOOLTIPS,
                ModListOptions.TEXTURE_TOOLTIPS,
                ModListOptions.ITEM_MESSAGES,
                ModListOptions.SHOW_DEATH_CORDS,
                ModListOptions.CONFIRM_MESSAGES,
                ModListOptions.PANORAMA,
                ModListOptions.SOCIAL_BUTTONS
        };
    }

    @Override
    protected void init() {
        this.optionList = this.addDrawableChild(new OptionListWidget(this.client, this.width, this));
        this.optionList.addAll(clientOptions(this.gameOptions));
        if (!options().advanced.applyFogMixin) {
            OptionListWidget.WidgetEntry widget = this.optionList.children().getFirst();
            widget.widgets.getFirst().active = false;
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