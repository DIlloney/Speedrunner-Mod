package net.dilloney.speedrunnermod.timer.data;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class BestSplits extends AbstractRun {

    private long specialMin(long n1, long n2) {
        if (n1 == UNINITIALIZED && n2 == UNINITIALIZED) return UNINITIALIZED;
        if (n1 == UNINITIALIZED) return n2;
        if (n2 == UNINITIALIZED) return n1;
        return Math.min(n1, n2);
    }

    public void tryRun(AbstractRun run) {
        finishedSplitTicks = specialMin(finishedSplitTicks, run.finishedSplitTicks);
        finishedRealTime = specialMin(finishedRealTime, run.finishedRealTime);
    }
}