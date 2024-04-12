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
public class MainOptionsScreen extends AbstractModScreen {

    public MainOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_OPTIONS_MAIN);
    }

    @Override
    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSingleOptionEntry(ModListOptions.STRUCTURE_SPAWN_RATES);
        this.list.addSingleOptionEntry(ModListOptions.FASTER_BLOCK_BREAKING);
        this.list.addSingleOptionEntry(ModListOptions.BLOCK_BREAKING_MULTIPLIER);
        this.list.addSingleOptionEntry(ModListOptions.DRAGON_PERCH_TIME);
        this.list.addSingleOptionEntry(ModListOptions.LEADERBOARDS_MODE);
        this.list.addSingleOptionEntry(ModListOptions.DOOM_MODE);
        this.list.addSingleOptionEntry(ModListOptions.ICARUS_MODE);
        this.list.addSingleOptionEntry(ModListOptions.INFINI_PEARL_MODE);
        this.list.addSingleOptionEntry(ModListOptions.BETTER_VILLAGER_TRADES);
        this.list.addSingleOptionEntry(ModListOptions.BETTER_FOODS);
        this.list.addSingleOptionEntry(ModListOptions.BETTER_BIOMES);
        this.list.addSingleOptionEntry(ModListOptions.CUSTOM_BIOMES_AND_CUSTOM_BIOME_FEATURES);
        this.list.addSingleOptionEntry(ModListOptions.STACK_UNSTACKABLES);
        this.list.addSingleOptionEntry(ModListOptions.FIREPROOF_ITEMS);
        this.list.addSingleOptionEntry(ModListOptions.THROWABLE_FIREBALLS);
        this.list.addSingleOptionEntry(ModListOptions.FALL_DAMAGE);
        this.list.addSingleOptionEntry(ModListOptions.KINETIC_DAMAGE);
        this.list.addSingleOptionEntry(ModListOptions.STRONGHOLD_COUNT);
        this.list.addSingleOptionEntry(ModListOptions.STRONGHOLD_DISTANCE);
        this.list.addSingleOptionEntry(ModListOptions.STRONGHOLD_SPREAD);
        this.list.addSingleOptionEntry(ModListOptions.STRONGHOLD_PORTAL_ROOM_COUNT);
        this.list.addSingleOptionEntry(ModListOptions.STRONGHOLD_LIBRARY_COUNT);
        this.list.addSingleOptionEntry(ModListOptions.NETHER_PORTAL_COOLDOWN);
        this.list.addSingleOptionEntry(ModListOptions.GLOBAL_NETHER_PORTALS);
        this.list.addSingleOptionEntry(ModListOptions.LAVA_BOATS);
        this.list.addSingleOptionEntry(ModListOptions.NETHER_WATER);
        this.list.addSingleOptionEntry(ModListOptions.COMMON_ORES);
        this.list.addSingleOptionEntry(ModListOptions.BETTER_ANVIL);
        this.list.addSingleOptionEntry(ModListOptions.ANVIL_COST_LIMIT);
        this.list.addSingleOptionEntry(ModListOptions.HIGHER_ENCHANTMENT_LEVELS);
        this.list.addSingleOptionEntry(ModListOptions.ARROWS_DESTROY_BEDS);
        this.list.addSingleOptionEntry(ModListOptions.MOB_SPAWNING_RATE);
        this.list.addSingleOptionEntry(ModListOptions.FASTER_SPAWNERS);
        this.list.addSingleOptionEntry(ModListOptions.KILL_GHAST_ON_FIREBALL);
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
}