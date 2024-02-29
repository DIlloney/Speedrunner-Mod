package net.dillon8775.speedrunnermod.client.screen.options;

import net.dillon8775.speedrunnermod.client.screen.RestartRequiredScreen;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.ClientModOptions;
import net.dillon8775.speedrunnermod.option.ModListOptions;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;

import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

@Environment(EnvType.CLIENT)
public class MainOptionsScreen extends GameOptionsScreen {
    private final Screen parent;
    private static final Option[] OPTIONS;
    private ButtonListWidget list;

    public MainOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_OPTIONS_MAIN);
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSingleOptionEntry(ModListOptions.STRUCTURE_SPAWN_RATE);
        this.list.addSingleOptionEntry(ModListOptions.FASTER_BLOCK_BREAKING);
        this.list.addAll(OPTIONS);
        this.addSelectableChild(this.list);
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ModTexts.SAVE, (button) -> {
            this.close();
        }));
    }

    @Override
    public void close() {
        ModOptions.saveConfig();
        ClientModOptions.saveClientConfig();
        if (RestartRequiredScreen.needsRestart()) {
            this.client.setScreen(new RestartRequiredScreen(this.parent, MinecraftClient.getInstance().options));
        } else {
            info("Saved changes");
            this.client.setScreen(new ModOptionsScreen(this.parent, MinecraftClient.getInstance().options));
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.list.render(matrices, mouseX, mouseY, delta);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 12, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
        List<OrderedText> list = getHoveredButtonTooltip(this.list, mouseX, mouseY);
        if (list != null) {
            this.renderOrderedTooltip(matrices, list, mouseX, mouseY);
        }
    }

    static {
        OPTIONS = new Option[]{
                ModListOptions.BETTER_BIOMES,
                ModListOptions.ICARUS_MODE,
                ModListOptions.FOG,

                Option.GAMMA,
                ModListOptions.INFINITY_PEARL_MODE,

                ModListOptions.ITEM_TOOLTIPS,
                ModListOptions.DOOM_MODE,

                ModListOptions.DRAGON_PERCH_TIME,
                ModListOptions.KILL_GHAST_ON_FIREBALL,

                ModListOptions.FALL_DAMAGE,
                ModListOptions.FIREPROOF_ITEMS,

                ModListOptions.BETTER_VILLAGER_TRADES,
        };
    }
}