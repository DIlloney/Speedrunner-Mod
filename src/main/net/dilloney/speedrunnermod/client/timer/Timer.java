package net.dilloney.speedrunnermod.client.timer;

import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.client.timer.data.DataStorage;
import net.dilloney.speedrunnermod.client.timer.data.SingleRun;
import net.dilloney.speedrunnermod.mixin.client.ModMixinsClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Environment(EnvType.CLIENT)
public class Timer {

    private static final long DEBOUNCE_PERSIST_MS = 2000L;
    private static final long DEBOUNCE_SERVER_QUERY_MS = 500L;

    private final MinecraftClient minecraftClient;
    private final Debounce persistDebounce;
    private final Debounce serverQueryDebounce;
    private final DataStorage store;
    private final Executor executor;
    private final Runnable lambda;

    public Timer(MinecraftClient client, DataStorage store) {
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
        long ticks = Timer.getGameTicks(player);
        long currentTime = System.currentTimeMillis();

        if (persistDebounce.boing()) executor.execute(lambda);

        SingleRun run = store.getRuns().solveItem(server, ticks);

        run.ticks = ticks;
        run.startTimestamp = Math.min(currentTime - (ticks * 50), run.startTimestamp);

        if (!serverQueryDebounce.boing()) return run;

        boolean seenCredits = false;

        if (serverPlayer instanceof ModMixinsClient.SeenCreditsAccessor) {
            seenCredits = ((ModMixinsClient.SeenCreditsAccessor)serverPlayer).seenCredits();
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

        if (((ModMixinsClient.GameOptionsAccessor)client).getGameOptions().debugEnabled) {
            return;
        }

        String timeLabel = timeLabel(run.getGameTime(), run.getRealTimeDuration());
        String finishedSplitLabel = finishedLabel(run.getFinished());
        String seedLabel = String.format("Seed: %s", server.getSaveProperties().getGeneratorOptions().getSeed());

        if (SpeedrunnerModClient.clOptions().timer) {
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

    public static void init() {
        if (SpeedrunnerModClient.clOptions().timer) {
            MinecraftClient client = MinecraftClient.getInstance();
            File configDir = FabricLoader.getInstance().getConfigDir().toFile();
            DataStorage store = DataStorage.of(new File(configDir, "speedrunnermod-timer_storage.json"));
            store.refreshBests("");
            Timer tickHandler = new Timer(client, store);
            HudRenderCallback.EVENT.register((__, ___) -> tickHandler.tick());
        }
    }
}