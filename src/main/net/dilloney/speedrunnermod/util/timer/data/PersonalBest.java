package net.dilloney.speedrunnermod.util.timer.data;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class PersonalBest extends AbstractRun {

    public void tryRun(AbstractRun otherRun) {
        if (otherRun.finishedSplitTicks == UNINITIALIZED || (finishedSplitTicks != UNINITIALIZED && finishedSplitTicks <= otherRun.finishedSplitTicks)) return;
        finishedSplitTicks = otherRun.finishedSplitTicks;
        finishedRealTime = otherRun.finishedRealTime;
    }
}