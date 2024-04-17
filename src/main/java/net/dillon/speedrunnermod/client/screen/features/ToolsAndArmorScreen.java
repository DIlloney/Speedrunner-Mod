package net.dillon.speedrunnermod.client.screen.features;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.CustomButtonListWidget;
import net.dillon.speedrunnermod.client.screen.features.tools_and_armor.*;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class ToolsAndArmorScreen extends AbstractModScreen {

    public ToolsAndArmorScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor"));
    }

    @Override
    protected void init() {
        this.buttonList = this.addDrawableChild(new CustomButtonListWidget(this.client, this.width, this.height - 64, 32, 25));

        this.clearButtons();

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "speedrunner_armor").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerArmorScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "golden_speedrunner_armor").copy().formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new GoldenSpeedrunnerArmorScreen(this.parent, this.options));
        }).build());

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "dash_enchantment").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new DashEnchantmentScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "cooldown_enchantment").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new CooldownEnchantmentScreen(this.parent, this.options));
        }).build());

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "dragons_sword").copy().formatted(Formatting.LIGHT_PURPLE), (button) -> {
            this.client.setScreen(new DragonsSwordScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "wither_sword").copy().formatted(Formatting.DARK_GRAY), (button) -> {
            this.client.setScreen(new WitherSwordScreen(this.parent, this.options));
        }).build());

        super.init();
    }

    @Override
    protected void doneButtonFunction() {
        this.close();
    }

    @Override
    public void close() {
        this.client.setScreen(new FeaturesScreen(this.parent, this.options));
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}