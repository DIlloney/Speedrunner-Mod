package net.dillon.speedrunnermod.client.screen.features;

import net.dillon.speedrunnermod.client.screen.features.blocks_and_items.*;
import net.dillon.speedrunnermod.client.screen.features.doom_mode.*;
import net.dillon.speedrunnermod.client.screen.features.miscellaneous.*;
import net.dillon.speedrunnermod.client.screen.features.ores_and_worldgen.*;
import net.dillon.speedrunnermod.client.screen.features.tools_and_armor.*;
import net.dillon.speedrunnermod.client.util.ModTexts;
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
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Used to create {@link net.dillon.speedrunnermod.SpeedrunnerMod} features screens.
 */
@Environment(EnvType.CLIENT)
public abstract class AbstractFeatureScreen extends GameOptionsScreen {
    protected final Screen parent;
    private final int pageNumber;
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

    protected AbstractFeatureScreen(Screen parent, GameOptions options, Text title, int pageNumber, boolean renderImage, boolean renderCraftingRecipe) {
        super(parent, options, title);
        this.parent = parent;
        this.pageNumber = pageNumber;
        this.renderImage = renderImage;
        this.renderCraftingRecipe = renderCraftingRecipe;
    }

    protected AbstractFeatureScreen(Screen parent, GameOptions options, Text title, int pageNumber, boolean renderImage, boolean renderCraftingRecipe, Screen category1Screen, Text category1Text, Screen category2Screen, Text category2Text, Screen category3Screen, Text category3Text, boolean hasFourthCategory, @Nullable Screen category4Screen, @Nullable Text category4Text) {
        super(parent, options, title);
        this.parent = parent;
        this.pageNumber = pageNumber;
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
        if (this.getScreenCategory() == ScreenCategories.BLOCKS_AND_ITEMS) {
            this.client.setScreen(new BlocksAndItemsScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategories.TOOLS_AND_ARMOR) {
            this.client.setScreen(new ToolsAndArmorScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategories.ORES_AND_WORLDGEN) {
            this.client.setScreen(new OresAndWorldgenScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategories.MISCELLANEOUS) {
            this.client.setScreen(new MiscellaneousScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategories.DOOM_MODE) {
            this.client.setScreen(new DoomModeScreen(this.parent, MinecraftClient.getInstance().options));
        } else {
            this.client.setScreen(new FeaturesScreen(this.parent, MinecraftClient.getInstance().options));
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 15, 16777215);

        if (this.getScreenLines() == 2) {
            context.drawCenteredTextWithShadow(this.textRenderer, this.getFirstLineText(), this.width / 2, 100, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, this.getSecondLineText(), this.width / 2, 120, 16777215);
        } else if (this.getScreenLines() == 3) {
            context.drawCenteredTextWithShadow(this.textRenderer, this.getFirstLineText(), this.width / 2, 90, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, this.getSecondLineText(), this.width / 2, 110, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, this.getThirdLineText(), this.width / 2, 130, 16777215);
        } else if (this.getScreenLines() == 4) {
            context.drawCenteredTextWithShadow(this.textRenderer, this.getFirstLineText(), this.width / 2, 80, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, this.getSecondLineText(), this.width / 2, 100, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, this.getThirdLineText(), this.width / 2, 120, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, this.getFourthLineText(), this.width / 2, 140, 16777215);
        } else if (this.getScreenLines() == 5) {
            context.drawCenteredTextWithShadow(this.textRenderer, this.getFirstLineText(), this.width / 2, 70, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, this.getSecondLineText(), this.width / 2, 90, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, this.getThirdLineText(), this.width / 2, 110, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, this.getFourthLineText(), this.width / 2, 130, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, this.getFifthLineText(), this.width / 2, 150, 16777215);
        }

        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int farRightSide = rightSide + 273;
        int height = this.height - 24;
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("§lPage:§r " + this.pageNumber + "/" + this.getMaxPages()), farRightSide, height, 16777215);

        if (renderImage) {
            context.drawTexture(this.getImage(), this.getImageX(), this.getImageY(), 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
        }

        if (renderCraftingRecipe) {
            context.drawTexture(this.getCraftingRecipeImage(), this.getCraftingRecipeImageX(), this.getCraftingRecipeImageY(), 0.0F, 0.0F, this.getCraftingRecipeImageWidth(), this.getCraftingRecipeImageHeight(), this.getCraftingRecipeImageWidth(), this.getCraftingRecipeImageHeight());
        }

        this.renderCustomImage(context);
        super.render(context, mouseX, mouseY, delta);
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
        if (this.getScreenCategory() == ScreenCategories.BLOCKS_AND_ITEMS) {
            return 18;
        } else if (this.getScreenCategory() == ScreenCategories.TOOLS_AND_ARMOR) {
            return 6;
        } else if (this.getScreenCategory() == ScreenCategories.ORES_AND_WORLDGEN) {
            return 7;
        } else if (this.getScreenCategory() == ScreenCategories.DOOM_MODE) {
            return 5;
        } else {
            return 10;
        }
    }

    /**
     * Gets the page number of every screen.
     */
    private Screen getPageNumbers(int pageNumber) {
        GameOptions options = MinecraftClient.getInstance().options;
        if (this.getScreenCategory() == ScreenCategories.BLOCKS_AND_ITEMS) {
            return switch (pageNumber) {
                case 1 -> new SpeedrunnerIngotsScreen(this.parent, options);
                case 2 -> new SpeedrunnerNuggetsScreen(this.parent, options);
                case 3 -> new SpeedrunnerBlocksScreen(this.parent, options);
                case 4 -> new SpeedrunnerWoodScreen(this.parent, options);
                case 5 -> new MoreBoatsScreen(this.parent, options);
                case 6 -> new IgneousRocksScreen(this.parent, options);
                case 7 -> new EyeOfInfernoScreen(this.parent, options);
                case 8 -> new EyeOfAnnulScreen(this.parent, options);
                case 9 -> new SpeedrunnersEyeScreen(this.parent, options);
                case 10 -> new DragonsPearlScreen(this.parent, options);
                case 11 -> new PiglinAwakenerScreen(this.parent, options);
                case 12 -> new BlazeSpotterScreen(this.parent, options);
                case 13 -> new EnderThrusterScreen(this.parent, options);
                case 14 -> new RaidEradicatorScreen(this.parent, options);
                case 15 -> new SpeedrunnerBulkScreen(this.parent, options);
                case 16 -> new SpeedrunnersWorkbenchScreen(this.parent, options);
                case 17 -> new RetiredSpeedrunnerScreen(this.parent, options);
                case 18 -> new GoldenFoodItemsScreen(this.parent, options);
                default -> new FeaturesScreen(this.parent, options);
            };
        } else if (this.getScreenCategory() == ScreenCategories.TOOLS_AND_ARMOR) {
            return switch (pageNumber) {
                case 1 -> new SpeedrunnerArmorScreen(this.parent, options);
                case 2 -> new GoldenSpeedrunnerArmorScreen(this.parent, options);
                case 3 -> new DragonsSwordScreen(this.parent, options);
                case 4 -> new DashEnchantmentScreen(this.parent, options);
                case 5 -> new CooldownEnchantmentScreen(this.parent, options);
                case 6 -> new WitherSwordScreen(this.parent, options);
                default -> new FeaturesScreen(this.parent, options);
            };
        } else if (this.getScreenCategory() == ScreenCategories.ORES_AND_WORLDGEN) {
            return switch (pageNumber) {
                case 1 -> new SpeedrunnersWastelandBiomeScreen(this.parent, options);
                case 2 -> new SpeedrunnerOresScreen(this.parent, options);
                case 3 -> new ExperienceOresScreen(this.parent, options);
                case 4 -> new IgneousOresScreen(this.parent, options);
                case 5 -> new CommonOresScreen(this.parent, options);
                case 6 -> new StructuresScreen(this.parent, options);
                case 7 -> new FortressesBastionsAndStrongholdsScreen(this.parent, options);
                default -> new FeaturesScreen(this.parent, options);
            };
        } else if (this.getScreenCategory() == ScreenCategories.DOOM_MODE) {
            return switch (pageNumber) {
                case 1 -> new BasicsScreen(this.parent, options);
                case 2 -> new BossesScreen(this.parent, options);
                case 3 -> new GiantScreen(this.parent, options);
                case 4 -> new DoomBlocksScreen(this.parent, options);
                case 5 -> new OtherThingsToKnowScreen(this.parent, options);
                default -> new FeaturesScreen(this.parent, options);
            };
        } else {
            return switch (pageNumber) {
                case 1 -> new ResetKeyScreen(this.parent, options);
                case 2 -> new FogKeyScreen(this.parent, options);
                case 3 -> new FasterBlockBreakingScreen(this.parent, options);
                case 4 -> new ICarusModeScreen(this.parent, options);
                case 5 -> new InfiniPearlModeScreen(this.parent, options);
                case 6 -> new PiglinBarteringScreen(this.parent, options);
                case 7 -> new PiglinPorkScreen(this.parent, options);
                case 8 -> new NoMorePiglinBrutesScreen(this.parent, options);
                case 9 -> new TripledDropsScreen(this.parent, options);
                case 10 -> new MoreScreen(this.parent, options);
                default -> new FeaturesScreen(this.parent, options);
            };
        }
    }

    /**
     * Gets the category to display on the feature screen.
     */
    private String linesCategory() {
        if (this.getScreenCategory() == ScreenCategories.BLOCKS_AND_ITEMS) {
            return ".blocks_and_items.";
        } else if (this.getScreenCategory() == ScreenCategories.TOOLS_AND_ARMOR) {
            return ".tools_and_armor.";
        } else if (this.getScreenCategory() == ScreenCategories.ORES_AND_WORLDGEN) {
            return ".ores_and_worldgen.";
        } else if (this.getScreenCategory() == ScreenCategories.DOOM_MODE) {
            return ".doom_mode.";
        } else {
            return ".miscellaneous.";
        }
    }

    private Text getFirstLineText() {
        return Text.translatable("speedrunnermod.features" + this.linesCategory() + this.linesKey() + ".line1");
    }

    private Text getSecondLineText() {
        return Text.translatable("speedrunnermod.features" + this.linesCategory() + this.linesKey() + ".line2");
    }

    private Text getThirdLineText() {
        return Text.translatable("speedrunnermod.features" + this.linesCategory() + this.linesKey() + ".line3");
    }

    private Text getFourthLineText() {
        return Text.translatable("speedrunnermod.features" + this.linesCategory() + this.linesKey() + ".line4");
    }

    private Text getFifthLineText() {
        return Text.translatable("speedrunnermod.features" + this.linesCategory() + linesKey() + ".line5");
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
        return this.getPageNumbers(this.pageNumber + 1);
    }

    /**
     * Gets the {@code previous screen}, which goes back to the screen displayed before.
     * <p><i>On {@link ScreenType#STARTER} pages, there may not be a previous screen.</i></p>
     */
    @Nullable
    protected Screen getPreviousScreen() {
        return this.getPageNumbers(this.pageNumber - 1);
    }

    /**
     * Gets the key of the main feature on the feature screen.
     */
    @NotNull
    protected abstract String linesKey();

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
    protected abstract ScreenCategories getScreenCategory();

    /**
     * Determines how many lines will be displayed on a screen.
     */
    protected abstract int getScreenLines();

    /**
     * Gets the type of feature screen.
     */
    @NotNull
    protected abstract ScreenType getScreenType();
}