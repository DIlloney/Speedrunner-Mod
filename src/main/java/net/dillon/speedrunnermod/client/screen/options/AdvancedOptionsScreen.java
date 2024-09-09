package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

import java.io.File;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

public class AdvancedOptionsScreen extends AbstractModScreen {

    public AdvancedOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_ADVANCED_OPTIONS);
    }

    private SimpleOption<?>[] advancedOptions() {
        return new SimpleOption[]{
                ModListOptions.SHOW_RESET_BUTTON,
                ModListOptions.HIGHER_BREATH_TIME,
                ModListOptions.GENERATE_SPEEDRUNNER_WOOD,
                ModListOptions.ENDER_EYE_BREAKING_COOLDOWN,
                ModListOptions.APPLY_FOG_MIXIN,
                ModListOptions.FIREBALL_EXPLOSION_POWER
        };
    }

    @Override
    protected void init() {
        this.optionList = this.addDrawableChild(new OptionListWidget(this.client, this.width, this));
        this.optionList.addSingleOptionEntry(ModListOptions.MODIFIED_STRONGHOLD_GENERATION);
        this.optionList.addSingleOptionEntry(ModListOptions.MODIFIED_STRONGHOLD_Y_GENERATION);
        this.optionList.addSingleOptionEntry(ModListOptions.MODIFIED_NETHER_FORTRESS_GENERATION);
        this.optionList.addAll(advancedOptions());
        this.optionList.addSingleOptionEntry(ModListOptions.SPEEDRUNNERS_WASTELAND_BIOME_WEIGHT);
        this.optionList.addSingleOptionEntry(ModListOptions.LONGER_DRAGON_PERCH_STAY_TIME);
        this.optionList.addSingleOptionEntry(ModListOptions.DECREASED_ZOMBIFIED_PIGLIN_SCARE_DISTANCE);
        this.optionList.addSingleOptionEntry(ModListOptions.PIGLIN_AWAKENER_PIGLIN_COUNT);
        this.optionList.addSingleOptionEntry(ModListOptions.ICARUS_FIREWORKS_INVENTORY_SLOT);
        this.optionList.addSingleOptionEntry(ModListOptions.INFINI_PEARL_INVENTORY_SLOT);
        this.optionList.addSingleOptionEntry(ModListOptions.DRAGON_KILLS_NEARBY_HOSTILE_ENTITIES);
        this.optionList.addSingleOptionEntry(ModListOptions.DRAGON_IMMUNITY_FROM_GIANT_AND_WITHER);

        for (int i = 0; i < this.optionList.children().size(); i++) {
            OptionListWidget.WidgetEntry widget = this.optionList.children().get(i);
            if (i == 5 && !options().main.throwableFireballs) {
                widget.widgets.get(1).active = false;
            }
            if (i == 9 && !options().main.stateOfTheArtItems || i == 10 && !options().main.iCarusMode || i == 11 && !options().main.infiniPearlMode || i == 13 && !options().main.doomMode) {
                widget.widgets.getFirst().active = false;
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