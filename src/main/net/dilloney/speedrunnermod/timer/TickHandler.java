package net.dilloney.speedrunnermod.timer;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.mod.ModFeaturesClient;
import net.dilloney.speedrunnermod.timer.data.DataStorage;
import net.dilloney.speedrunnermod.timer.data.SingleRun;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Environment(EnvType.CLIENT)
public class TickHandler {

    private static final long DEBOUNCE_PERSIST_MS = 2000L;
    private static final long DEBOUNCE_SERVER_QUERY_MS = 500L;

    private final MinecraftClient minecraftClient;
    private final Debounce persistDebounce;
    private final Debounce serverQueryDebounce;
    private final DataStorage store;
    private final Executor executor;
    private final Runnable lambda;

    public TickHandler(MinecraftClient client, DataStorage store) {
        this.store = store;
        persistDebounce = new Debounce(DEBOUNCE_PERSIST_MS);
        serverQueryDebounce = new Debounce(DEBOUNCE_SERVER_QUERY_MS);
        minecraftClient = client;
        executor = Executors.newSingleThreadExecutor();
        lambda = store::persist;
    }

    private static long getGameTicks(PlayerEntity player) {
        return player.world.getTime();
    }

    private static String timeLabel(long ms) {return ms < 0 ? "--" : timeString(ms);}

    private static String timeString(long ms) {
        if (ms < 1000 * 60 * 60) {
            return DurationFormatUtils.formatDuration(ms, "mm:ss.SSS");
        }
        return DurationFormatUtils.formatDuration(ms, "HH:mm:ss.SSS");
    }

    public void tick() {
        if (minecraftClient == null) return;
        PlayerEntity player = minecraftClient.player;
        if (player == null) return;
        MinecraftServer server = minecraftClient.getServer();
        if (server == null) return;
        ServerPlayerEntity serverPlayer = getServerPlayer(server);
        if (serverPlayer == null || getGameTicks(player) <= 0) return;

        SingleRun run = updateRunData(player, server, serverPlayer);
        render(minecraftClient, server, run);
    }

    @Nullable
    private ServerPlayerEntity getServerPlayer(MinecraftServer server) {
        List<ServerPlayerEntity> playerList = server.getPlayerManager().getPlayerList();
        return playerList.size() == 1 ? playerList.get(0) : null;
    }

    private SingleRun updateRunData(PlayerEntity player, MinecraftServer server, ServerPlayerEntity serverPlayer) {
        long ticks = TickHandler.getGameTicks(player);
        long currentTime = System.currentTimeMillis();

        if (persistDebounce.boing()) executor.execute(lambda);

        SingleRun run = store.getRuns().solveItem(server, ticks);

        run.ticks = ticks;
        run.startTimestamp = Math.min(currentTime - (ticks * 50), run.startTimestamp);

        if (!serverQueryDebounce.boing()) return run;

        boolean seenCredits = false;

        if (serverPlayer instanceof ModFeaturesClient.SeenCreditsAccessor) {
            seenCredits = ((ModFeaturesClient.SeenCreditsAccessor)serverPlayer).seenCredits();
        }

        if (!run.isFinished() && seenCredits) {
            run.finishedSplitTicks = ticks;
            run.finishedRealTime = run.getRealTimeDuration();
            store.getBestSplits().tryRun(run);
            store.getPersonalBest().tryRun(run);
        }

        return run;
    }

    private String timeLabel(long gameTime, long realTime) {
        return String.format("%s | %s", timeLabel(gameTime), timeLabel(realTime));
    }

    private String finishedLabel(long finished) {
        return String.format("%s: %s", "Finished", timeLabel(finished));
    }

    private void render(MinecraftClient client, MinecraftServer server, SingleRun run) {

        final TextRenderer textRenderer = client.textRenderer;
        Hud hud = new Hud(textRenderer, 5, 5);

        if (((ModFeaturesClient.GameOptionsAccessor)client).getGameOptions().debugEnabled) {
            return;
        }

        String timeLabel = timeLabel(run.getGameTime(), run.getRealTimeDuration());
        String finishedSplitLabel = finishedLabel(run.getFinished());
        String seedLabel = String.format("Seed: %s", server.getSaveProperties().getGeneratorOptions().getSeed());

        if (SpeedrunnerMod.OPTIONS.timer) {
            if (run.isFinished()) {
                hud.print(timeLabel, 0x29DB87).println(finishedSplitLabel, 10, 0xFFA500);
            } else {
                hud.print(timeLabel, 0x29DB87);
            }

            if (run.isFinished()) {
                hud.insertSpace(5).println(seedLabel, 10, 0x00FF22);
            }

            hud.render(4, 0x000011, 0);
        }
    }
}