package net.dillon8775.speedrunnermod.client.screen.features;

import net.dillon8775.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon8775.speedrunnermod.client.screen.features.tools_and_armor.*;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class ToolsAndArmorScreen extends AbstractModScreen {

    public ToolsAndArmorScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor"));
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "speedrunner_armor").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerArmorScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "golden_speedrunner_armor").copy().formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new GoldenSpeedrunnerArmorScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "dash_enchantment").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new DashEnchantmentScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "cooldown_enchantment").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new CooldownEnchantmentScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "dragons_sword").copy().formatted(Formatting.LIGHT_PURPLE), (button) -> {
            this.client.setScreen(new DragonsSwordScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.TOOLS_AND_ARMOR, "wither_sword").copy().formatted(Formatting.DARK_GRAY), (button) -> {
            this.client.setScreen(new WitherSwordScreen(this.parent, this.options));
        }));

        this.addDrawableChild(new ButtonWidget(this.getButtonsMiddle(), this.getDoneButtonsHeight(), 200, 20, ScreenTexts.DONE, (button) -> {
            this.close();
        }));
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