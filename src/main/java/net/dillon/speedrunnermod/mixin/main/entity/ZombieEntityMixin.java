package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin extends HostileEntity {

    public ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "getXpToDrop", at = @At("HEAD"))
    private void getExperiencePoints(PlayerEntity player, CallbackInfoReturnable<Integer> cir) {
        if (player != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
        }
    }

    /**
     * Modifies the zombie's attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createZombieAttributes() {
        final double genericFollowRange = DOOM_MODE ? 50.0D : 25.0D;
        final double genericMovementSpeed = DOOM_MODE ? 0.33000000417232513D : 0.23000000417232513D;
        final double genericAttackDamage = DOOM_MODE ? 7.0D : 2.0D;
        final double genericArmor = DOOM_MODE ? 2.0D : 1.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.GENERIC_ARMOR, genericArmor)
                .add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    /**
     * A thing for {@code doom mode.} >:)
     */
    @Override
    public boolean tryAttack(Entity target) {
        if (!super.tryAttack(target)) {
            return false;
        } else {
            if (DOOM_MODE && target instanceof PlayerEntity) {
                ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TickCalculator.seconds(10), 0));
            }

            return true;
        }
    }
}