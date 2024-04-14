package net.dillon.speedrunnermod.client.screen.features.miscellaneous;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.dillon.speedrunnermod.client.screen.features.blocks_and_items.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.client.screen.features.ores_and_worldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon.speedrunnermod.client.screen.features.tools_and_armor.SpeedrunnerArmorScreen;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class MoreScreen extends AbstractFeatureScreen {

    public MoreScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.miscellaneous.more").formatted(Formatting.AQUA), 10, false, false);
    }

    @Override
    protected void init() {
        int middle = this.width / 2 - 75;
        int height = this.height / 6 + 115;

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.more"), button -> {
            this.openLink(SpeedrunnerMod.WIKI_LINK, true);
        }).dimensions(middle, height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.blocks_and_items"), button -> {
            this.client.setScreen(new SpeedrunnerIngotsScreen(this.parent, MinecraftClient.getInstance().options));
        }).dimensions(middle, height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.tools_and_armor"), button -> {
            this.client.setScreen(new SpeedrunnerArmorScreen(this.parent, MinecraftClient.getInstance().options));
        }).dimensions(middle, height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.ores_and_worldgen"), button -> {
            this.client.setScreen(new SpeedrunnersWastelandBiomeScreen(this.parent, MinecraftClient.getInstance().options));
        }).dimensions(middle, height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.PREVIOUS, button -> {
            this.client.setScreen(new TripledDropsScreen(this.parent, MinecraftClient.getInstance().options));
        }).dimensions(middle, height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.close()).dimensions(middle, height, 150, 20).build());
    }

    @Override
    protected @NotNull String linesKey() {
        return "more";
    }

    @Override
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected int getImageWidth() {
        return 0;
    }

    @Override
    protected int getImageHeight() {
        return 0;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.MISCELLANEOUS;
    }

    @Override
    protected int getScreenLines() {
        return 2;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FINAL;
    }
}