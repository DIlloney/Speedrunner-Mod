package net.dillon.speedrunnermod.client.screen.features;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.screen.BaseModScreen;
import net.dillon.speedrunnermod.client.screen.features.blocks_and_items.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.client.screen.features.miscellaneous.TripledDropsScreen;
import net.dillon.speedrunnermod.client.screen.features.ores_and_worldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon.speedrunnermod.client.screen.features.tools_and_armor.SpeedrunnerArmorScreen;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.List;

/**
 * Used to create {@link net.dillon.speedrunnermod.SpeedrunnerMod} features screens.
 */
@Environment(EnvType.CLIENT)
public abstract class AbstractFeatureScreen extends BaseModScreen {
    protected final Screen parent;
    private final boolean renderImage;
    private final boolean renderCraftingRecipe;
    private boolean renderCustomImage = false;
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

    protected AbstractFeatureScreen(Screen parent, GameOptions options, Text title, boolean renderImage, boolean renderCraftingRecipe, boolean renderCustomImage) {
        super(parent, options, title);
        this.parent = parent;
        this.renderImage = renderImage;
        this.renderCraftingRecipe = renderCraftingRecipe;
        this.renderCustomImage = renderCustomImage;
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

    protected AbstractFeatureScreen(Screen parent, GameOptions options, Text title, boolean renderImage, boolean renderCraftingRecipe, boolean renderCustomImage, Screen category1Screen, Text category1Text, Screen category2Screen, Text category2Text, Screen category3Screen, Text category3Text, boolean hasFourthCategory, @Nullable Screen category4Screen, @Nullable Text category4Text) {
        super(parent, options, title);
        this.parent = parent;
        this.renderImage = renderImage;
        this.renderCraftingRecipe = renderCraftingRecipe;
        this.renderCustomImage = renderCustomImage;
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
        } else if (this.getScreenType() == ScreenType.END) {
            height = this.height / 6 + 115;
            int middle = this.width / 2 - 75;
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

        if (this.getDownscaledImage() != null) {
            this.renderFullResolutionDownscaledImage(context);
        }
        this.renderCustomImage(context);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_LEFT) {
            this.client.setScreen(this.getPreviousScreen());  
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_RIGHT) {
            this.client.setScreen(this.getNextScreen());
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    /**
     * Gets the maximum amount of pages for each category.
     */
    protected int getMaxPages() {
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
        for (AbstractFeatureScreen screen : this.allFeatureScreens()) {
            if (screen.getPageNumber() == pageNumber && screen.getScreenCategory() == category) {
                return screen;
            }
        }
        return new FeaturesScreen(this.parent, this.options);
    }

    /**
     * Calculates the total amount of pages that are in a {@link ScreenCategory}.
     */
    protected int calculateMaxPages(ScreenCategory category) {
        int i = 0;
        for (AbstractFeatureScreen screen : this.allFeatureScreens()) {
            if (screen.getScreenCategory() == category) {
                i++;
            }
        }
        return i;
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
        return this.renderCraftingRecipe || this.renderCustomImage ? this.width / 2 - 175 : this.width / 2 - 75;
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
     * Renders a 1920x1080 image cut down to 240x135 on the screen.
     */
    protected void renderFullResolutionDownscaledImage(DrawContext context) {
        context.drawTexture(this.getDownscaledImage(), this.width / 2, 170, 0.0F, 0.0F, 240, 135, 240, 135);
    }

    /**
     * <p>Gets the downscaled image location.</p>
     * As long as this returns {@code null}, the image will not be rendered on the screen.
     */
    protected Identifier getDownscaledImage() {
        return null;
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
     * Gets the {@code width} of the rendered image.
     */
    protected int getImageWidth() {
        return 0;
    }

    /**
     * Gets the {@code height} of the rendered image.
     */
    protected int getImageHeight() {
        return 0;
    }

    /**
     * Gets the key of the main feature on the feature screen.
     */
    @NotNull
    public abstract String linesKey();

    /**
     * Returns the page number of an {@link AbstractFeatureScreen}.
     */
    public abstract int getPageNumber();

    /**
     * Displays an image of the item on the screen.
     */
    protected abstract Identifier getImage();

    /**
     * Displays a {@code crafting recipe} of the item on the screen.
     */
    protected abstract Identifier getCraftingRecipeImage();

    /**
     * Gets the category that the feature screen is in.
     */
    @NotNull
    public abstract ScreenCategory getScreenCategory();

    /**
     * Gets the type of feature screen.
     */
    @NotNull
    protected abstract ScreenType getScreenType();
}