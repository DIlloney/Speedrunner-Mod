package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.world.gen.chunk.StrongholdConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Makes strongholds generate according to the {@code Speedrunner Mod's} configuration options.
 */
@Mixin(StrongholdConfig.class)
public class StrongholdConfigMixin {

    @Overwrite
    public int getDistance() {
        return SpeedrunnerMod.options().strongholdDistance;
    }

    @Overwrite
    public int getSpread() {
        return SpeedrunnerMod.options().strongholdSpread;
    }

    @Overwrite
    public int getCount() {
        return SpeedrunnerMod.options().strongholdCount;
    }
}