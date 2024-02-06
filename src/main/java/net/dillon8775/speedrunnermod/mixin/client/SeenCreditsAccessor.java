package net.dillon8775.speedrunnermod.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Unused accessor.
 */
@Environment(EnvType.CLIENT)
@Mixin(ServerPlayerEntity.class)
public interface SeenCreditsAccessor {
    @Accessor("seenCredits")
    boolean seenCredits();
}