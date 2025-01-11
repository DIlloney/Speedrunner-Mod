package net.dillon.speedrunnermod.client.screen.features.toolsandarmor;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

@Environment(EnvType.CLIENT)
public class CooldownEnchantmentScreen extends AbstractFeatureScreen {

    public CooldownEnchantmentScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor.cooldown_enchantment"), true, false);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "cooldown_enchantment";
    }

    @Override
    public int getPageNumber() {
        return 6;
    }

    @Override
    protected Identifier getImage() {
        return ofSpeedrunnerMod("textures/gui/features/items/enchanted_book.png");
    }

    @Override
    protected int getImageWidth() {
        return 32;
    }

    @Override
    protected int getImageHeight() {
        return 28;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}