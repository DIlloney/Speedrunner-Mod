package net.dillon8775.speedrunnermod.mixin.main.world;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(StrongholdGenerator.PortalRoom.class)
public class StrongholdPortalRoomMixin {

    @ModifyConstant(method = "generate", constant = @Constant(floatValue = 0.9F))
    private float changeEyeChance(float constant) {
        return DOOM_MODE ? 0.99F : 0.6F;
    }
}