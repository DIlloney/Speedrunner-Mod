package net.dillon.speedrunnermod.client.screen.features;

import net.dillon.speedrunnermod.client.screen.features.blocks_and_items.*;
import net.dillon.speedrunnermod.client.screen.features.doom_mode.*;
import net.dillon.speedrunnermod.client.screen.features.miscellaneous.*;
import net.dillon.speedrunnermod.client.screen.features.ores_and_worldgen.*;
import net.dillon.speedrunnermod.client.screen.features.tools_and_armor.*;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Used to create {@link net.dillon.speedrunnermod.SpeedrunnerMod} features screens.
 */
@Environment(EnvType.CLIENT)
public abstract class AbstractFeatureScreen extends GameOptionsScreen {
    protected GameOptions options = MinecraftClient.getInstance().options;
    protected final Screen parent;
    private final boolean renderImage;
    private final boolean renderCraftingRecipe;
    private Screen category1Screen;
    private Screen category2Screen;
    private Screen category3Screen;
    private Text category1Text;
    private Text category2Text;
    private Text category3Text;
    private boolean hasFourthCategory;
    @Nullable
    private Screen category4Screen;
    @Nullable
    private Text category4Text;

    protected AbstractFeatureScreen(Screen parent, GameOptions options, Text title, boolean renderImage, boolean renderCraftingRecipe) {
        super(parent, options, title);
        this.parent = parent;
        this.renderImage = renderImage;
        this.renderCraftingRecipe = renderCraftingRecipe;
    }

    protected AbstractFeatureScreen(Screen parent, GameOptions options, Text title, boolean renderImage, boolean renderCraftingRecipe, Screen category1Screen, Text category1Text, Screen category2Screen, Text category2Text, Screen category3Screen, Text category3Text, boolean hasFourthCategory, @Nullable Screen category4Screen, @Nullable Text category4Text) {
        super(parent, options, title);
        this.parent = parent;
        this.renderImage = renderImage;
        this.renderCraftingRecipe = renderCraftingRecipe;
        this.category1Screen = category1Screen;
        this.category2Screen = category2Screen;
        this.category3Screen = category3Screen;
        this.category1Text = category1Text;
        this.category2Text = category2Text;
        this.category3Text = category3Text;
        this.hasFourthCategory = hasFourthCategory;
        this.category4Screen = category4Screen;
        this.category4Text = category4Text;
    }

    @Override
    protected void init() {
        int width = this.getButtonsWidth();
        int height = this.getButtonsHeight();

        if (this.getScreenType() == ScreenType.STARTER) {
            this.addDrawableChild(ButtonWidget.builder(ModTexts.NEXT, button -> this.client.setScreen(this.getNextScreen())).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.close()).dimensions(width, height, 150, 20).build());
        } else if (this.getScreenType() == ScreenType.NORMAL) {
            this.addDrawableChild(ButtonWidget.builder(ModTexts.NEXT, button -> this.client.setScreen(this.getNextScreen())).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ModTexts.PREVIOUS, button -> this.client.setScreen(this.getPreviousScreen())).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.close()).dimensions(width, height, 150, 20).build());
        } else if (this.getScreenType() == ScreenType.FINAL) {
            this.addDrawableChild(ButtonWidget.builder(this.category1Text, button -> this.client.setScreen(this.category1Screen)).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(this.category2Text, button -> this.client.setScreen(this.category2Screen)).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(this.category3Text, button -> this.client.setScreen(this.category3Screen)).dimensions(width, height, 150, 20).build());

            if (hasFourthCategory) {
                height += 24;
                this.addDrawableChild(ButtonWidget.builder(this.category4Text, button -> this.client.setScreen(this.category4Screen)).dimensions(width, height, 150, 20).build());
            }

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ModTexts.PREVIOUS, button -> this.client.setScreen(this.getPreviousScreen())).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.close()).dimensions(width, height, 150, 20).build());
        }
    }

    @Override
    public void close() {
        if (this.getScreenCategory() == ScreenCategory.BLOCKS_AND_ITEMS) {
            this.client.setScreen(new BlocksAndItemsScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategory.TOOLS_AND_ARMOR) {
            this.client.setScreen(new ToolsAndArmorScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategory.ORES_AND_WORLDGEN) {
            this.client.setScreen(new OresAndWorldgenScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategory.MISCELLANEOUS) {
            this.client.setScreen(new MiscellaneousScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategory.DOOM_MODE) {
            this.client.setScreen(new DoomModeScreen(this.parent, MinecraftClient.getInstance().options));
        } else {
            this.client.setScreen(new FeaturesScreen(this.parent, MinecraftClient.getInstance().options));
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 15, 16777215);

        List<OrderedText> screenText = this.client.textRenderer.wrapLines(this.textToDisplay(), 396);
        int textHeight = 100 - (screenText.size() - 2) * 10;
        textHeight = Math.max(textHeight, 70);
        for (OrderedText text : screenText) {
            context.drawCenteredTextWithShadow(this.textRenderer, text, this.width / 2, textHeight, 16777215);
            textHeight += 15;
        }

        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int farRightSide = rightSide + 273;
        int height = this.height - 24;
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("§lPage:§r " + this.getPageNumber() + "/" + this.getMaxPages()), farRightSide, height, 16777215);

        super.render(context, mouseX, mouseY, delta);
        if (this.renderImage) {
            if (screenText.size() <= 8) {
                context.drawTexture(this.getImage(), this.getImageX(), this.getImageY(), 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
            } else {
                throw new IllegalArgumentException();
            }
        }

        if (this.renderCraftingRecipe) {
            context.drawTexture(this.getCraftingRecipeImage(), this.getCraftingRecipeImageX(), this.getCraftingRecipeImageY(), 0.0F, 0.0F, this.getCraftingRecipeImageWidth(), this.getCraftingRecipeImageHeight(), this.getCraftingRecipeImageWidth(), this.getCraftingRecipeImageHeight());
        }

        this.renderCustomImage(context);
    }

    /**
     * An easier way to open a link in a mod screen.
     */
    protected void openLink(String link, boolean trusted) {
        this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(link);
            }
            this.client.setScreen(this);
        }, link, trusted));
    }

    /**
     * Gets the maximum amount of pages for each category.
     */
    private int getMaxPages() {
        switch (this.getScreenCategory()) {
            case BLOCKS_AND_ITEMS -> {
                return calculateMaxPages(ScreenCategory.BLOCKS_AND_ITEMS);
            }
            case TOOLS_AND_ARMOR -> {
                return calculateMaxPages(ScreenCategory.TOOLS_AND_ARMOR);
            }
            case ORES_AND_WORLDGEN -> {
                return calculateMaxPages(ScreenCategory.ORES_AND_WORLDGEN);
            }
            case MISCELLANEOUS -> {
                return calculateMaxPages(ScreenCategory.MISCELLANEOUS);
            }
            case DOOM_MODE -> {
                return calculateMaxPages(ScreenCategory.DOOM_MODE);
            }
            default -> {
                return 0;
            }
        }
    }

    /**
     * Gets the page number of feature screens.
     */
    private Screen page(int pageNumber) {
        switch (this.getScreenCategory()) {
            case BLOCKS_AND_ITEMS -> {
                return determineScreen(pageNumber, ScreenCategory.BLOCKS_AND_ITEMS);
            }
            case TOOLS_AND_ARMOR -> {
                return determineScreen(pageNumber, ScreenCategory.TOOLS_AND_ARMOR);
            }
            case ORES_AND_WORLDGEN -> {
                return determineScreen(pageNumber, ScreenCategory.ORES_AND_WORLDGEN);
            }
            case MISCELLANEOUS -> {
                return determineScreen(pageNumber, ScreenCategory.MISCELLANEOUS);
            }
            case DOOM_MODE -> {
                return determineScreen(pageNumber, ScreenCategory.DOOM_MODE);
            }
            default -> {
                return new FeaturesScreen(this.parent, this.options);
            }
        }
    }

    /**
     * Determine the screen to go to, based on the page number.
     */
    @ChatGPT
    private Screen determineScreen(int pageNumber, ScreenCategory category) {
        for (AbstractFeatureScreen screen : allFeatureScreens()) {
            if (screen.getPageNumber() == pageNumber && screen.getScreenCategory() == category) {
                return screen;
            }
        }
        return new FeaturesScreen(this.parent, this.options);
    }

    /**
     * Calculates the total amount of pages that are in a {@link ScreenCategory}.
     */
    private int calculateMaxPages(ScreenCategory category) {
        int i = 0;
        for (AbstractFeatureScreen screen : allFeatureScreens()) {
            if (screen.getScreenCategory() == category) {
                i++;
            }
        }
        return i;
    }

    /**
     * The list of all feature screens.
     */
    protected List<AbstractFeatureScreen> allFeatureScreens() {
        return List.of(new BlazeSpotterScreen(parent, options),
                new DragonsPearlScreen(parent, options),
                new EnderThrusterScreen(parent, options),
                new EyeOfAnnulScreen(parent, options),
                new EyeOfInfernoScreen(parent, options),
                new GoldenFoodItemsScreen(parent, options),
                new IgneousRocksScreen(parent, options),
                new MoreBoatsScreen(parent, options),
                new PiglinAwakenerScreen(parent, options),
                new RaidEradicatorScreen(parent, options),
                new RetiredSpeedrunnerScreen(parent, options),
                new SpeedrunnerBlocksScreen(parent, options),
                new SpeedrunnerBulkScreen(parent, options),
                new SpeedrunnerIngotsScreen(parent, options),
                new SpeedrunnerNuggetsScreen(parent, options),
                new SpeedrunnersEyeScreen(parent, options),
                new SpeedrunnersWorkbenchScreen(parent, options),
                new SpeedrunnerWoodScreen(parent, options),

                new BasicsScreen(parent, options),
                new BossesScreen(parent, options),
                new DoomBlocksScreen(parent, options),
                new GiantScreen(parent, options),
                new OtherThingsToKnowScreen(parent, options),

                new FasterBlockBreakingScreen(parent, options),
                new FogKeyScreen(parent, options),
                new ICarusModeScreen(parent, options),
                new InfiniPearlModeScreen(parent, options),
                new MoreScreen(parent, options),
                new NoMorePiglinBrutesScreen(parent, options),
                new PiglinBarteringScreen(parent, options),
                new PiglinPorkScreen(parent, options),
                new ResetKeyScreen(parent, options),
                new TripledDropsScreen(parent, options),
                new CommonOresScreen(parent, options),
                new ExperienceOresScreen(parent, options),
                new FortressesBastionsAndStrongholdsScreen(parent, options),
                new IgneousOresScreen(parent, options),
                new SpeedrunnerOresScreen(parent, options),
                new SpeedrunnersWastelandBiomeScreen(parent, options),
                new StructuresScreen(parent, options),

                new CooldownEnchantmentScreen(parent, options),
                new DashEnchantmentScreen(parent, options),
                new DragonsSwordScreen(parent, options),
                new GoldenSpeedrunnerArmorScreen(parent, options),
                new SpeedrunnerArmorScreen(parent, options),
                new WitherSwordScreen(parent, options));
    }

    /**
     * Gets the category to display on the feature screen.
     */
    private String linesCategory() {
        if (this.getScreenCategory() == ScreenCategory.BLOCKS_AND_ITEMS) {
            return ".blocks_and_items.";
        } else if (this.getScreenCategory() == ScreenCategory.TOOLS_AND_ARMOR) {
            return ".tools_and_armor.";
        } else if (this.getScreenCategory() == ScreenCategory.ORES_AND_WORLDGEN) {
            return ".ores_and_worldgen.";
        } else if (this.getScreenCategory() == ScreenCategory.DOOM_MODE) {
            return ".doom_mode.";
        } else {
            return ".miscellaneous.";
        }
    }

    /**
     * Returns the text to display on the feature screen.
     */
    private Text textToDisplay() {
        return Text.translatable("speedrunnermod.features" + this.linesCategory() + this.linesKey() + ".text");
    }

    /**
     * Gets the buttons width on the screen.
     * <p>You should only {@link Override} this method if you use {@link AbstractFeatureScreen#renderCustomImage(DrawContext)}.</p>
     */
    protected int getButtonsWidth() {
        return this.renderCraftingRecipe ? this.width / 2 - 175 : this.width / 2 - 75;
    }

    /**
     * Gets the height of the buttons on the screen.
     */
    protected int getButtonsHeight() {
        if (this.getScreenType() == ScreenType.FINAL) {
            return hasFourthCategory ? this.height / 6 + 115 : this.height / 6 + 135;
        } else {
            return this.height / 6 + 150;
        }
    }

    /**
     * Gets the X placement of the feature image, if present.
     */
    protected int getImageX() {
        return this.width / 2 - 15;
    }

    /**
     * Gets the Y placement of the feature image, if present.
     */
    protected int getImageY() {
        return 160;
    }

    /**
     * Gets the X placement of the crafting recipe image, if present.
     */
    protected int getCraftingRecipeImageX() {
        return this.width / 2 + 10;
    }

    /**
     * Gets the Y placement of the crafting recipe image, if present.
     */
    protected int getCraftingRecipeImageY() {
        return 205;
    }

    /**
     * Gets the width of the crafting recipe image, if present.
     */
    protected int getCraftingRecipeImageWidth() {
        return 171;
    }

    /**
     * Gets the height of the crafting recipe image, if present.
     */
    protected int getCraftingRecipeImageHeight() {
        return 78;
    }

    /**
     * Render a custom image on a feature screen.
     */
    protected void renderCustomImage(DrawContext context) {
    }

    /**
     * Gets the {@code next screen} that should be displayed when clicking the {@code "Next"} button.
     */
    @Nullable
    protected Screen getNextScreen() {
        return this.page(this.getPageNumber() + 1);
    }

    /**
     * <p>Gets the {@code previous screen}, which goes back to the screen displayed before.</p>
     * <i>On {@link ScreenType#STARTER} pages, there may not be a previous screen.</i>
     */
    @Nullable
    protected Screen getPreviousScreen() {
        return this.page(this.getPageNumber() - 1);
    }

    /**
     * Gets the key of the main feature on the feature screen.
     */
    @NotNull
    protected abstract String linesKey();

    /**
     * Returns the page number of an {@link AbstractFeatureScreen}.
     */
    protected abstract int getPageNumber();

    /**
     * Displays an image of the item on the screen.
     */
    protected abstract Identifier getImage();

    /**
     * Gets the {@code width} of the rendered image.
     */
    protected abstract int getImageWidth();

    /**
     * Gets the {@code height} of the rendered image.
     */
    protected abstract int getImageHeight();

    /**
     * Displays a {@code crafting recipe} of the item on the screen.
     */
    protected abstract Identifier getCraftingRecipeImage();

    /**
     * Gets the category that the feature screen is in.
     */
    @NotNull
    protected abstract ScreenCategory getScreenCategory();

    /**
     * Gets the type of feature screen.
     */
    @NotNull
    protected abstract ScreenType getScreenType();
}