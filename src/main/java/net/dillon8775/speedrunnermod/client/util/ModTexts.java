package net.dillon8775.speedrunnermod.client.util;

import net.dillon8775.speedrunnermod.client.screen.features.ScreenCategories;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

/**
 * All of the {@link TranslatableText}s for the {@code speedrunner mod}.
 */
@Environment(EnvType.CLIENT)
public class ModTexts {
    public static final Text BLANK = new LiteralText("");
    public static final Text OK = new TranslatableText("speedrunnermod.ok");
    public static final Text SAVE = new TranslatableText("speedrunnermod.save");
    public static final Text NEXT = new TranslatableText("speedrunnermod.next");
    public static final Text PREVIOUS = new TranslatableText("speedrunnermod.previous");
    public static final Text BACK = new TranslatableText("speedrunnermod.back");

    public static final Text TITLE = new TranslatableText("speedrunnermod.title");
    public static final Text MENU_OPTIONS_ACTION_NEEDED = new TranslatableText("speedrunnermod.leaderboards.action_needed");
    public static final Text MENU_OPTIONS_SAFE = new TranslatableText("speedrunnermod.leaderboards.safe");
    public static final Text TITLE_OPTIONS_MAIN = new TranslatableText("speedrunnermod.title.options.main");
    public static final Text TITLE_OPTIONS_CLIENT = new TranslatableText("speedrunnermod.title.options.client");
    public static final Text MENU_OPTIONS_RESET = new TranslatableText("speedrunnermod.menu.options.reset");
    public static final Text TITLE_OPTIONS_RESET = new TranslatableText("speedrunnermod.title.options.reset");
    public static final Text MENU_OPTIONS = new TranslatableText("speedrunnermod.menu.options.main");
    public static final Text MENU_OPTIONS_CLIENT = new TranslatableText("speedrunnermod.menu.options.client");
    public static final Text MENU_FAST_WORLD_CREATION = new TranslatableText("speedrunnermod.menu.options.fast_world_creation");
    public static final Text TITLE_FAST_WORLD_CREATION = new TranslatableText("speedrunnermod.title.options.fast_world_creation");
    public static final Text MENU_CREDITS = new TranslatableText("speedrunnermod.menu.credits");
    public static final Text TITLE_CREDITS = new TranslatableText("speedrunnermod.title.credits");
    public static final Text MENU_EXTERNAL = new TranslatableText("speedrunnermod.menu.external").formatted(Formatting.RED);
    public static final Text TITLE_EXTERNAL = new TranslatableText("speedrunnermod.external");
    public static final Text MENU_FEATURES_TOOLTIP = new TranslatableText("speedrunnermod.menu.features.tooltip");
    public static final Text TITLE_FEATURES = new TranslatableText("speedrunnermod.title.features");
    public static final Text MENU_RESOURCES = new TranslatableText("speedrunnermod.menu.resources");
    public static final Text MENU_RESOURCES_TOOLTIP = new TranslatableText("speedrunnermod.menu.resources.tooltip");
    public static final Text TITLE_RESOURCES = new TranslatableText("speedrunnermod.title.resources");
    public static final Text MENU_MODS = new TranslatableText("speedrunnermod.menu.resources.mods");
    public static final Text TITLE_MODS = new TranslatableText("speedrunnermod.title.resources.mods");
    public static final Text MENU_TUTORIALS = new TranslatableText("speedrunnermod.menu.resources.tutorials");
    public static final Text TITLE_TUTORIALS = new TranslatableText("speedrunnermod.title.resources.tutorials");
    public static final Text TITLE_RESTART_REQUIRED = new TranslatableText("speedrunnermod.title.restart_required");
    public static final Text TITLE_SAFE_BOOT = new TranslatableText("speedrunnermod.title.safe_mode");
    public static final Text TITLE_SPEEDRUN_IGT_MISSING = new TranslatableText("speedrunnermod.title.speedrun_igt_missing");
    public static final Text ENABLE_DOOM_MODE = new TranslatableText("speedrunnermod.doom_mode.enable").formatted(Formatting.RED);
    public static final Text MENU_DOOM_MODE = new TranslatableText("speedrunnermod.menu.doom_mode");
    public static final Text TITLE_DOOM_MODE = new TranslatableText("speedrunnermod.title.doom_mode");

    public static final Text EASIER_SPEEDRUNNING_MOD = new TranslatableText("speedrunnermod.the_easier_speedrunning_mod");
    public static final Text EASIER_SPEEDRUNNING_MOD_TOOLTIP = new TranslatableText("speedrunnermod.the_easier_speedrunning_mod.tooltip");

    public static final Text CURSEFORGE = new TranslatableText("speedrunnermod.menu.external.curseforge").formatted(Formatting.GOLD);
    public static final Text MODRINTH = new TranslatableText("speedrunnermod.menu.external.modrinth").formatted(Formatting.GREEN);
    public static final Text GITHUB = new TranslatableText("speedrunnermod.menu.external.github").formatted(Formatting.GRAY);
    public static final Text DISCORD = new TranslatableText("speedrunnermod.menu.external.discord").formatted(Formatting.BLUE);
    public static final Text DISCORD_TOOLTIP = new TranslatableText("speedrunnermod.menu.external.discord.tooltip");
    public static final Text WEBPAGE = new TranslatableText("speedrunnermod.menu.external.webpage").formatted(Formatting.LIGHT_PURPLE);
    public static final Text WEBPAGE_TOOLTIP = new TranslatableText("speedrunnermod.menu.external.webpage.tooltip");
    public static final Text YOUTUBE = new TranslatableText("speedrunnermod.menu.external.youtube").formatted(Formatting.RED);
    public static final Text MOD_SHOWCASE_VIDEO = new TranslatableText("speedrunnermod.menu.external.mod_showcase_video").formatted(Formatting.AQUA);
    public static final Text MENU_LEADERBOARDS = new TranslatableText("speedrunnermod.menu.external.leaderboards").formatted(Formatting.GREEN);
    public static final Text MENU_LEADERBOARD_VIEW = new TranslatableText("speedrunnermod.menu.leaderboards.view");
    public static final Text MENU_LEADERBOARD_SPREADSHEET = new TranslatableText("speedrunnermod.menu.leaderboards.spreadsheet");
    public static final Text TITLE_LEADERBOARDS = new TranslatableText("speedrunnermod.title.leaderboards").formatted(Formatting.GREEN);
    public static final Text TITLE_INELIGIBLE_OPTIONS = new TranslatableText("speedrunnermod.title.ineligible_options");

    public static final Text MENU_OPEN_OPTIONS_FILE = new TranslatableText("speedrunnermod.menu.open_options_file");

    public static final Text SODIUM = new TranslatableText("speedrunnermod.title.resources.mods.sodium");
    public static final Text SODIUM_TOOLTIP = new TranslatableText("speedrunnermod.title.resources.mods.sodium.tooltip");
    public static final Text LITHIUM = new TranslatableText("speedrunnermod.title.resources.mods.lithium");
    public static final Text LITHIUM_TOOLTIP = new TranslatableText("speedrunnermod.title.resources.mods.lithium.tooltip");
    public static final Text PHOSPHOR = new TranslatableText("speedrunnermod.title.resources.mods.phosphor");
    public static final Text PHOSPHOR_TOOLTIP = new TranslatableText("speedrunnermod.title.resources.mods.phosphor.tooltip");
    public static final Text SPEEDRUN_IGT = new TranslatableText("speedrunnermod.title.resources.mods.speedrunigt");
    public static final Text SPEEDRUN_IGT_TOOLTIP = new TranslatableText("speedrunnermod.title.resources.mods.speedrunigt.tooltip");
    public static final Text LAZYDFU = new TranslatableText("speedrunnermod.title.resources.mods.lazydfu");
    public static final Text LAZYDFU_TOOLTIP = new TranslatableText("speedrunnermod.title.resources.mods.lazydfu.tooltip");
    public static final Text KRYPTON = new TranslatableText("speedrunnermod.title.resources.mods.krypton");
    public static final Text KRYPTON_TOOLTIP = new TranslatableText("speedrunnermod.title.resources.mods.krypton.tooltip");
    public static final Text OPTIFINE = new TranslatableText("speedrunnermod.title.resources.mods.optifine");
    public static final Text OPTIFINE_TOOLTIP = new TranslatableText("speedrunnermod.title.resources.mods.optifine.tooltip");

    public static final Text RESET = new TranslatableText("speedrunnermod.reset");
    public static final Text RESET_CONFIRM = new TranslatableText("speedrunnermod.reset_confirm");
    public static final Text NOT_NOW = new TranslatableText("speedrunnermod.not_now");

    public static final Text RESTART_NOW = new TranslatableText("speedrunnermod.restart_now");
    public static final Text RESTART_LATER = new TranslatableText("speedrunnermod.restart_later");
    public static final Text REVERT_CHANGES = new TranslatableText("speedrunnermod.revert_changes");

    public static final Text FIX_AND_RESTART = new TranslatableText("speedrunnermod.fix_and_restart");
    public static final Text DOWNLOAD_AND_INSTALL = new TranslatableText("speedrunnermod.download_and_install");
    public static final Text CLOSE_GAME = new TranslatableText("speedrunnermod.close_game");
    public static final Text PROCEED_ANYWAY = new TranslatableText("speedrunnermod.proceed_anyway");
    public static final Text DISABLE_LEADERBOARDS_MODE_AND_RESTART = new TranslatableText("speedrunnermod.disable_leaderboards_mode_and_restart");
    public static final Text IGNORE = new TranslatableText("speedrunnermod.ignore").formatted(Formatting.RED);
    public static final Text VIEW_INELIGIBLE_OPTIONS = new TranslatableText("speedrunnermod.view_ineligible_options");
    public static final Text VISIT_SUBMISSION_PAGE = new TranslatableText("speedrunnermod.visit_submission_page");

    public static final String fB = "§b";
    public static final String fBfL = "§b§l";
    public static final String fR = "§r";

    public static LiteralText fText(String formattedText) {
        return new LiteralText(formattedText);
    }

    public static MutableText featuresText() {
        return new TranslatableText("speedrunnermod.menu.features").formatted(Formatting.AQUA);
    }

    public static MutableText wikiText(boolean coloured) {
        return new TranslatableText("speedrunnermod.menu.resources.wiki").formatted(coloured ? Formatting.LIGHT_PURPLE : Formatting.WHITE);
    }

    public static TranslatableText featureTitleText(ScreenCategories category, String lang) {
        return new TranslatableText("speedrunnermod.title.features." + category.toString().toLowerCase() + "." + lang);
    }
}