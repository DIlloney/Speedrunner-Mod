package net.dilloney.speedrunnermod.client.timer.data;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;

import java.util.HashMap;

@Environment(EnvType.CLIENT)
public class Runs extends HashMap<String, SingleRun> {

    public SingleRun solveItem(MinecraftServer server, long ticks) {
        String key = getRunKey(server);
        SingleRun run = this.get(key);
        if (run == null) {
            run = new SingleRun(ticks);
            this.put(key, run);
            return run;
        }
        return this.get(key);
    }

    private static String getRunKey(MinecraftServer server) {
        String levelPath = server.getSavePath(WorldSavePath.ROOT).normalize().toString();
        long seed = server.getSaveProperties().getGeneratorOptions().getSeed();
        return levelPath + ":" + seed;
    }
}