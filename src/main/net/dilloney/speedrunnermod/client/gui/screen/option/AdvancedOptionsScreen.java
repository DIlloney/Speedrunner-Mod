package net.dilloney.speedrunnermod.client.gui.screen.option;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.Option;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.TranslatableText;

import java.util.List;

@Environment(EnvType.CLIENT)
public class AdvancedOptionsScreen extends GameOptionsScreen {
    private static final Option[] OPTIONS;
    private ButtonListWidget list;

    public AdvancedOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.advanced"));
    }

    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSingleOptionEntry(ModOption.COMBINE_FORTRESSES_AND_BASTIONS);
        this.list.addAll(OPTIONS);
        this.children.add(this.list);
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            SpeedrunnerMod.saveConfig();
            SpeedrunnerMod.LOGGER.info("Flushed changes to the Speedrunner Mod");
            this.client.openScreen(this.parent);
        }));
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

    public void onClose() {
        SpeedrunnerMod.saveConfig();
        SpeedrunnerMod.LOGGER.info("Flushed changes to the Speedrunner Mod");
        super.onClose();
    }

    static {
        OPTIONS = new Option[]{ModOption.MOB_SPAWNING_RATE, ModOption.MODIFY_BIOMES, ModOption.COMMON_ORES, ModOption.STRONGHOLD_DISTANCE, ModOption.MOD_BUTTON_TYPE};
    }
}