package net.dillon.speedrunnermod.mixin.main.block;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public abstract class BedBlockMixin {

    /**
     * Cancels the original explosion method, and creates a new one, increasing the explosion power with beds in the end.
     */
    @Inject(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;Lnet/minecraft/util/math/Vec3d;FZLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"), cancellable = true)
    private void increaseExplosionPower(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        cir.cancel();
        Vec3d vec3d = pos.toCenterPos();
        world.createExplosion(null, DamageSource.badRespawnPoint(vec3d), null, vec3d, SpeedrunnerMod.getBedBlockExplosionPower(world), true, World.ExplosionSourceType.BLOCK);
        cir.setReturnValue(ActionResult.SUCCESS);
    }
}