package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ButtonSide;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

import java.io.File;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Toggleable switches to enable/disable certain {@code State-Of-The-Art} items individually.
 */
@Environment(EnvType.CLIENT)
public class StateOfTheArtItemsOptionsScreen extends AbstractModScreen {

    public StateOfTheArtItemsOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_STATE_OF_THE_ART_ITEMS);
    }

    /**
     * Returns the {@code State-Of-The-Art item} options for the screen.
     */
    private SimpleOption<?>[] stateOfTheArtItems() {
        return new SimpleOption[]{
                ModListOptions.stateOfTheArtItem("annul_eye"),
                ModListOptions.stateOfTheArtItem("blaze_spotter"),
                ModListOptions.stateOfTheArtItem("dragons_pearl"),
                ModListOptions.stateOfTheArtItem("dragons_sword"),
                ModListOptions.stateOfTheArtItem("ender_thruster"),
                ModListOptions.stateOfTheArtItem("piglin_awakener"),
                ModListOptions.stateOfTheArtItem("raid_eradicator")
        };
    }

    @Override
    protected void init() {
        this.initializeOptionListWidget();

        this.optionList.addSingleOptionEntry(ModListOptions.STATE_OF_THE_ART_ITEMS);
        this.optionList.addAll(stateOfTheArtItems());

        this.deactivateButton(1, ButtonSide.LEFT, options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue());
        this.deactivateButton(1, ButtonSide.RIGHT, options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue());
        this.deactivateButton(2, ButtonSide.LEFT, options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue());
        this.deactivateButton(2, ButtonSide.RIGHT, options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue());
        this.deactivateButton(3, ButtonSide.LEFT, options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue());
        this.deactivateButton(3, ButtonSide.RIGHT, options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue());
        this.deactivateButton(4, ButtonSide.LEFT, options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue());

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