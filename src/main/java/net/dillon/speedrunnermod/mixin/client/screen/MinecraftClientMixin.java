package net.dillon.speedrunnermod.mixin.client.screen;

import com.google.common.collect.Lists;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.SpeedrunnerModClient;
import net.dillon.speedrunnermod.client.screen.LeaderboardsSafeScreen;
import net.dillon.speedrunnermod.client.screen.SafeBootScreen;
import net.dillon.speedrunnermod.client.screen.SpeedrunIGTMissingScreen;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.QuickPlay;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.SpeedrunnerMod.warn;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow
    public abstract void setScreen(@Nullable Screen screen);
    @Shadow
    protected abstract void createInitScreens(List<Function<Runnable, Screen>> list);

    /**
     * @author Dillon8775
     * @reason Adds the {@code Safe Mode} feature.
     * <p>If the speedrunner mod detects broken options, then the game will load into the {@link SafeBootScreen}.</p>
     */
    @Overwrite
    private Runnable onInitFinished(@Nullable MinecraftClient.LoadingContext loadingContext) {
        ArrayList<Function<Runnable, Screen>> list = new ArrayList<Function<Runnable, Screen>>();
        this.createInitScreens(list);
        Runnable runnable = () -> {
            if (loadingContext != null && loadingContext.quickPlayData().isEnabled()) {
                QuickPlay.startQuickPlay(MinecraftClient.getInstance(), loadingContext.quickPlayData(), loadingContext.realmsClient());
            } else {
                if (SpeedrunnerMod.safeBoot) {
                    this.setScreen(new SafeBootScreen(null, MinecraftClient.getInstance().options));
                    warn("Booted into safe mode, due to corrupt options. It is recommended that you fix these options before proceeding.");
                } else if (!Leaderboards.isEligibleForLeaderboardRuns() && options().main.leaderboardsMode.getCurrentValue()) {
                    this.setScreen(new LeaderboardsSafeScreen(null, MinecraftClient.getInstance().options));
                    warn("You have invalid options set for the leaderboards, you must fix these if you want to submit a speedrun to the leaderboards.");
                } else if (options().main.leaderboardsMode.getCurrentValue() && SpeedrunnerModClient.speedrunIGTMissing) {
                    this.setScreen(new SpeedrunIGTMissingScreen(null, MinecraftClient.getInstance().options));
                    warn("SpeedrunIGT mod is missing, please download to submit speedruns.");
                } else {
                    this.setScreen(new TitleScreen(true));
                }
            }
        };
        for (Function<Runnable, Screen> function : Lists.reverse(list)) {
            Screen screen = function.apply(runnable);
            runnable = () -> this.setScreen(screen);
        }
        return runnable;
    }
}