<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StrongholdGenerator.PortalRoom.class)
public class StrongholdPortalRoomMixin {

    @ModifyConstant(method = "generate", constant = @Constant(floatValue = 0.9F))
    private float changeEyeChance(float constant) {
        return SpeedrunnerMod.getEnderEyeChance();
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StrongholdGenerator.PortalRoom.class)
public class StrongholdPortalRoomMixin {

    @ModifyConstant(method = "generate", constant = @Constant(floatValue = 0.9F))
    private float changeEyeChance(float constant) {
        return SpeedrunnerMod.getEnderEyeChance();
    }
>>>>>>> Stashed changes
}