package net.dillon8775.speedrunnermod.mixin.main.worldgen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.world.gen.chunk.StrongholdConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Makes strongholds generate according to the {@code Speedrunner Mod's} configuration options.
 */
@Mixin(StrongholdConfig.class)
public class StrongholdSettings {
    @Shadow @Final
    private int distance;
    @Shadow @Final
    private int count;

    @Overwrite
    public int getDistance() {
        return SpeedrunnerMod.options().advanced.strongholdDistance;
    }

    @Overwrite
    public int getSpread() {
        return SpeedrunnerMod.options().advanced.strongholdSpread;
    }

    @Overwrite
    public int getCount() {
        return SpeedrunnerMod.options().advanced.strongholdCount;
    }
}