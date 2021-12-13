package net.dilloney.speedrunnermod.client.gui.screen.option;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
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
public class ModOptionsScreen extends GameOptionsScreen {
    private static final Option[] FIRST_OPTIONS;
    private static final Option[] SECOND_OPTIONS;
    private ButtonListWidget list;

    public ModOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.options"));
    }

    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSingleOptionEntry(ModOption.MAKE_STRUCTURES_MORE_COMMON);
        this.list.addSingleOptionEntry(ModOption.MAKE_BIOMES_MORE_COMMON);
        this.list.addAll(FIRST_OPTIONS);
        this.list.addSingleOptionEntry(ModOption.MODIFIED_BLOCK_HARDNESS);
        this.list.addAll(SECOND_OPTIONS);
        this.children.add(this.list);
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            SpeedrunnerMod.saveConfig();
            SpeedrunnerModClient.saveClientConfig();
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
        SpeedrunnerModClient.saveClientConfig();
        SpeedrunnerMod.LOGGER.info("Flushed changes to the Speedrunner Mod");
        super.onClose();
    }

    static {
        FIRST_OPTIONS = new Option[]{ModOption.ICARUS_MODE, ModOption.INFINITY_PEARL_MODE, ModOption.FOG, ModOption.GAMMA, ModOption.TIMER, ModOption.DOOM_MODE};
        SECOND_OPTIONS = new Option[]{ModOption.KILL_GHAST_UPON_FIREBALL, ModOption.STRONGHOLD_COUNT, ModOption.DRAGON_PERCH_TIME, ModOption.AUTO_CREATE_WORLD, ModOption.WORLD_DIFFICULTY};
    }
}