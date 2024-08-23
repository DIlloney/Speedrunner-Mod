package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Copied over from Minecraft's original {@link net.minecraft.client.gui.widget.OptionListWidget}, this allows you to create a list of buttons, that aren't options.
 */
@ChatGPT(Credit.FULL_CREDIT)
@Environment(EnvType.CLIENT)
public class CustomButtonListWidget extends ElementListWidget<CustomButtonListWidget.ModWidgetEntry> {

    /**
     * Create a new {@link CustomButtonListWidget}.
     */
    public CustomButtonListWidget(MinecraftClient minecraftClient, int i, int j, int k, int l) {
        super(minecraftClient, i, j, k, l);
        this.centerListVertically = false;
    }

    /**
     * Adds a "row" of buttons to the list.
     */
    public void addRow(ClickableWidget firstButton, @Nullable ClickableWidget secondButton) {
        List<ClickableWidget> buttons = new ArrayList<>();
        firstButton.setX(this.width / 2 - 155);
        buttons.add(firstButton);
        if (secondButton != null) {
            secondButton.setX(this.width / 2 + 5);
            buttons.add(secondButton);
        }
        this.addEntry(ModWidgetEntry.create(buttons));
    }

    /**
     * <p>Adds a single button to the list of buttons.</p>
     * This button will take up a whole "row" space.
     */
    public int addSingleButton(ClickableWidget button) {
        button.setX(this.width / 2 - 155);
        button.setWidth(310);
        List<ClickableWidget> buttons = new ArrayList<>();
        buttons.add(button);
        return this.addEntry(ModWidgetEntry.create(buttons));
    }

    /**
     * Adds a whole {@link List} of buttons to the screen.
     */
    public void addAll(List<ClickableWidget> buttons) {
        for (int i = 0; i < buttons.size(); i += 2) {
            this.addRow(buttons.get(i), i < buttons.size() - 1 ? buttons.get(i + 1) : null);
        }
    }

    @Override
    public int getRowWidth() {
        return 400;
    }

    @Environment(value= EnvType.CLIENT)
    protected static class ModWidgetEntry extends ElementListWidget.Entry<CustomButtonListWidget.ModWidgetEntry> {
        private final List<ClickableWidget> widgets;

        private ModWidgetEntry(List<ClickableWidget> widgets) {
            this.widgets = widgets;
        }

        public static ModWidgetEntry create(List<ClickableWidget> widgets) {
            return new ModWidgetEntry(widgets);
        }

        @Override
        public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            for (ClickableWidget widget : widgets) {
                widget.setY(y);
                widget.render(context, mouseX, mouseY, tickDelta);
            }
        }

        @Override
        public List<? extends Element> children() {
            return widgets;
        }

        @Override
        public List<? extends Selectable> selectableChildren() {
            return widgets;
        }
    }
}