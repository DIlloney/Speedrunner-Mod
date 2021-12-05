package net.dilloney.speedrunnermod.client.timer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Debounce {
    private final long intervalMs;
    private long lastCalled;

    Debounce(long intervalMs) {
        this.intervalMs = intervalMs;
        lastCalled = System.currentTimeMillis();
    }

    public boolean boing() {
        long currentTime = System.currentTimeMillis();
        if (lastCalled + intervalMs < currentTime) {
            lastCalled = currentTime;
            return true;
        }
        return false;
    }
}