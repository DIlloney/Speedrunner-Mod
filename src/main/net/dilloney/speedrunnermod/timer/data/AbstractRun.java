package net.dilloney.speedrunnermod.timer.data;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public abstract class AbstractRun {
    public static long UNINITIALIZED = -1;
    public long finishedSplitTicks = UNINITIALIZED;
    public long finishedRealTime = UNINITIALIZED;

    public boolean isFinished() {
        return finishedSplitTicks != UNINITIALIZED;
    }

    public long getFinished() {
        return finishedSplitTicks * 50;
    }
}