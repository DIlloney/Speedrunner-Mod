package net.dillon.speedrunnermod.client.util;

import net.dillon.speedrunnermod.client.screen.features.ScreenCategories;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * All of the {@link Text#translatable(String)}s for the {@code speedrunner mod}.
 */
@Environment(EnvType.CLIENT)
public class ModTexts {
    public static final Text BLANK = Text.literal("");
    public static final Text OK = Text.translatable("speedrunnermod.ok");
    public static final Text SAVE = Text.translatable("speedrunnermod.save");
    public static final Text NEXT = Text.translatable("speedrunnermod.next");
    public static final Text PREVIOUS = Text.translatable("speedrunnermod.previous");
    public static final Text BACK = Text.translatable("speedrunnermod.back");
    public static final Text ON = Text.literal("ON");
    public static final Text OFF = Text.literal("OFF");

    public static final Text TITLE = Text.translatable("speedrunnermod.title");
    public static final Text MENU_OPTIONS_ACTION_NEEDED = Text.translatable("speedrunnermod.leaderboards.action_needed");
    public static final Text MENU_OPTIONS_SAFE = Text.translatable("speedrunnermod.leaderboards.safe");
    public static final Text TITLE_OPTIONS_MAIN = Text.translatable("speedrunnermod.title.options.main");
    public static final Text TITLE_OPTIONS_CLIENT = Text.translatable("speedrunnermod.title.options.client");
    public static final Text MENU_OPTIONS_RESET = Text.translatable("speedrunnermod.menu.options.reset");
    public static final Text TITLE_OPTIONS_RESET = Text.translatable("speedrunnermod.title.options.reset");
    public static final Text MENU_OPTIONS = Text.translatable("speedrunnermod.menu.options.main");
    public static final Text MENU_OPTIONS_CLIENT = Text.translatable("speedrunnermod.menu.options.client");
    public static final Text MENU_FAST_WORLD_CREATION = Text.translatable("speedrunnermod.menu.options.fast_world_creation");
    public static final Text TITLE_FAST_WORLD_CREATION = Text.translatable("speedrunnermod.title.options.fast_world_creation");
    public static final Text MENU_CREDITS = Text.translatable("speedrunnermod.menu.credits");
    public static final Text TITLE_CREDITS = Text.translatable("speedrunnermod.title.credits");
    public static final Text MENU_EXTERNAL = Text.translatable("speedrunnermod.menu.external").formatted(Formatting.RED);
    public static final Text TITLE_EXTERNAL = Text.translatable("speedrunnermod.external");
    public static final Text MENU_FEATURES_TOOLTIP = Text.translatable("speedrunnermod.menu.features.tooltip");
    public static final Text MENU_FEATURES = Text.translatable("speedrunnermod.menu.features").formatted(Formatting.AQUA);
    public static final Text TITLE_FEATURES = Text.translatable("speedrunnermod.title.features");
    public static final Text MENU_WIKI = Text.translatable("speedrunnermod.menu.resources.wiki").formatted(Formatting.LIGHT_PURPLE);
    public static final Text MENU_RESOURCES = Text.translatable("speedrunnermod.menu.resources");
    public static final Text MENU_RESOURCES_TOOLTIP = Text.translatable("speedrunnermod.menu.resources.tooltip");
    public static final Text TITLE_RESOURCES = Text.translatable("speedrunnermod.title.resources");
    public static final Text MENU_MODS = Text.translatable("speedrunnermod.menu.resources.mods").formatted(Formatting.AQUA);
    public static final Text TITLE_MODS = Text.translatable("speedrunnermod.title.resources.mods");
    public static final Text MENU_TUTORIALS = Text.translatable("speedrunnermod.menu.resources.tutorials").formatted(Formatting.BLUE);
    public static final Text TITLE_TUTORIALS = Text.translatable("speedrunnermod.title.resources.tutorials");
    public static final Text TITLE_RESTART_REQUIRED = Text.translatable("speedrunnermod.title.restart_required");
    public static final Text TITLE_SAFE_BOOT = Text.translatable("speedrunnermod.title.safe_mode");
    public static final Text TITLE_SPEEDRUN_IGT_MISSING = Text.translatable("speedrunnermod.title.speedrun_igt_missing");
    public static final Text ENABLE_DOOM_MODE = Text.translatable("speedrunnermod.doom_mode.enable").formatted(Formatting.RED);
    public static final Text MENU_DOOM_MODE = Text.translatable("speedrunnermod.menu.doom_mode");
    public static final Text TITLE_DOOM_MODE = Text.translatable("speedrunnermod.title.doom_mode");

    public static final Text EASIER_SPEEDRUNNING_MOD = Text.translatable("speedrunnermod.the_easier_speedrunning_mod");
    public static final Text EASIER_SPEEDRUNNING_MOD_TOOLTIP = Text.translatable("speedrunnermod.the_easier_speedrunning_mod.tooltip");

    public static final Text CURSEFORGE = Text.translatable("speedrunnermod.menu.external.curseforge").formatted(Formatting.GOLD);
    public static final Text MODRINTH = Text.translatable("speedrunnermod.menu.external.modrinth").formatted(Formatting.GREEN);
    public static final Text GITHUB = Text.translatable("speedrunnermod.menu.external.github").formatted(Formatting.GRAY);
    public static final Text DISCORD = Text.translatable("speedrunnermod.menu.external.discord").formatted(Formatting.BLUE);
    public static final Text DISCORD_TOOLTIP = Text.translatable("speedrunnermod.menu.external.discord.tooltip");
    public static final Text WEBPAGE = Text.translatable("speedrunnermod.menu.external.webpage").formatted(Formatting.LIGHT_PURPLE);
    public static final Text WEBPAGE_TOOLTIP = Text.translatable("speedrunnermod.menu.external.webpage.tooltip");
    public static final Text YOUTUBE = Text.translatable("speedrunnermod.menu.external.youtube").formatted(Formatting.RED);
    public static final Text MOD_SHOWCASE_VIDEO = Text.translatable("speedrunnermod.menu.external.mod_showcase_video").formatted(Formatting.AQUA);
    public static final Text MENU_LEADERBOARDS = Text.translatable("speedrunnermod.menu.external.leaderboards").formatted(Formatting.GREEN);
    public static final Text MENU_LEADERBOARD_VIEW = Text.translatable("speedrunnermod.menu.leaderboards.view");
    public static final Text MENU_LEADERBOARD_SPREADSHEET = Text.translatable("speedrunnermod.menu.leaderboards.spreadsheet");
    public static final Text TITLE_LEADERBOARDS = Text.translatable("speedrunnermod.title.leaderboards").formatted(Formatting.GREEN);
    public static final Text TITLE_INELIGIBLE_OPTIONS = Text.translatable("speedrunnermod.title.ineligible_options");

    public static final Text MENU_OPEN_OPTIONS_FILE = Text.translatable("speedrunnermod.menu.open_options_file");

    public static final Text SODIUM = Text.translatable("speedrunnermod.title.resources.mods.sodium").formatted(Formatting.GREEN);
    public static final Text SODIUM_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.sodium.tooltip");
    public static final Text LITHIUM = Text.translatable("speedrunnermod.title.resources.mods.lithium").formatted(Formatting.AQUA);
    public static final Text LITHIUM_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.lithium.tooltip");
    public static final Text PHOSPHOR = Text.translatable("speedrunnermod.title.resources.mods.phosphor").formatted(Formatting.YELLOW);
    public static final Text PHOSPHOR_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.phosphor.tooltip");
    public static final Text SPEEDRUN_IGT = Text.translatable("speedrunnermod.title.resources.mods.speedrunigt").formatted(Formatting.GREEN);
    public static final Text SPEEDRUN_IGT_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.speedrunigt.tooltip");
    public static final Text LAZYDFU = Text.translatable("speedrunnermod.title.resources.mods.lazydfu").formatted(Formatting.BLUE);
    public static final Text LAZYDFU_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.lazydfu.tooltip");
    public static final Text KRYPTON = Text.translatable("speedrunnermod.title.resources.mods.krypton").formatted(Formatting.GRAY);
    public static final Text KRYPTON_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.krypton.tooltip");
    public static final Text OPTIFINE = Text.translatable("speedrunnermod.title.resources.mods.optifine").formatted(Formatting.RED);
    public static final Text OPTIFINE_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.optifine.tooltip");

    public static final Text RESET = Text.translatable("speedrunnermod.reset");
    public static final Text RESET_CONFIRM = Text.translatable("speedrunnermod.reset_confirm");
    public static final Text NOT_NOW = Text.translatable("speedrunnermod.not_now");

    public static final Text RESTART_NOW = Text.translatable("speedrunnermod.restart_now");
    public static final Text RESTART_LATER = Text.translatable("speedrunnermod.restart_later");
    public static final Text REVERT_CHANGES = Text.translatable("speedrunnermod.revert_changes");

    public static final Text FIX_AND_RESTART = Text.translatable("speedrunnermod.fix_and_restart");
    public static final Text DOWNLOAD_AND_INSTALL = Text.translatable("speedrunnermod.download_and_install");
    public static final Text CLOSE_GAME = Text.translatable("speedrunnermod.close_game");
    public static final Text PROCEED_ANYWAY = Text.translatable("speedrunnermod.proceed_anyway");
    public static final Text DISABLE_LEADERBOARDS_MODE_AND_RESTART = Text.translatable("speedrunnermod.disable_leaderboards_mode_and_restart");
    public static final Text IGNORE = Text.translatable("speedrunnermod.ignore").formatted(Formatting.RED);
    public static final Text VIEW_INELIGIBLE_OPTIONS = Text.translatable("speedrunnermod.view_ineligible_options");
    public static final Text VISIT_SUBMISSION_PAGE = Text.translatable("speedrunnermod.visit_submission_page");

    public static final String fB = "§b";
    public static final String fBfL = "§b§l";
    public static final String fR = "§r";

    public static Text fText(String formattedText) {
        return Text.literal(formattedText);
    }

    public static Text featureTitleText(ScreenCategories category, String lang) {
        return Text.translatable("speedrunnermod.title.features." + category.toString().toLowerCase() + "." + lang);
    }
}