package net.dillon8775.speedrunnermod.client.gui.screen.option;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.gui.screen.RestartRequiredScreen;
import net.dillon8775.speedrunnermod.option.CLModOptions;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.TranslatableText;

import java.util.List;

@Environment(EnvType.CLIENT)
public class AdvancedOptionsScreen extends GameOptionsScreen {
    private static final Option[] OPTIONS;
    private static final Option[] OPTIONS2;
    private ButtonListWidget list;
    private final Screen parent;

    public AdvancedOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.options.advanced"));
        this.parent = parent;
    }

    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSingleOptionEntry(ModOption.GENERATE_SPEEDRUNNER_TREES);
        this.list.addAll(OPTIONS);
        this.list.addSingleOptionEntry(ModOption.MOB_SPAWNER_MAX_SPAWN_DURATION);
        this.list.addAll(OPTIONS2);
        this.addSelectableChild(this.list);
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            ModOptions.saveConfig();
            CLModOptions.saveClientConfig();
            if (RestartRequiredScreen.needsRestart()) {
                this.client.setScreen(new RestartRequiredScreen(this.parent, MinecraftClient.getInstance().options));
            } else {
                this.client.setScreen(new ModOptionsScreen(this.parent, MinecraftClient.getInstance().options));
                SpeedrunnerMod.LOGGER.info("Flushed changes to the Speedrunner Mod");
            }
        }));
    }

    public void onClose() {
        ModOptions.saveConfig();
        CLModOptions.saveClientConfig();
        if (RestartRequiredScreen.needsRestart()) {
            this.client.setScreen(new RestartRequiredScreen(this.parent, MinecraftClient.getInstance().options));
        } else {
            SpeedrunnerMod.LOGGER.info("Flushed changes to the Speedrunner Mod");
            this.client.setScreen(new ModOptionsScreen(this.parent, MinecraftClient.getInstance().options));
        }
    }

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
        OPTIONS = new Option[]{ModOption.CUSTOM_BIOMES, ModOption.COMMON_ORES, ModOption.ALLOW_BOATS_IN_LAVA, ModOption.ALLOW_WATER_IN_NETHER, ModOption.BETTER_FOODS, ModOption.ITEM_BUFFS, ModOption.BLOCK_PARTICLES, ModOption.STRONGHOLD_DISTANCE, ModOption.ITEM_MESSAGES, ModOption.MOB_SPAWNING_RATE, ModOption.MOD_BUTTON_TYPE, ModOption.SOCIAL_BUTTONS};
        OPTIONS2 = new Option[]{ModOption.GLOBAL_NETHER_PORTALS};
    }
}