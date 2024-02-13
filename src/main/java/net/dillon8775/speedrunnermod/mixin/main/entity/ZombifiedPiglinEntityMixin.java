package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ZombifiedPiglinEntity.class)
public class ZombifiedPiglinEntityMixin extends ZombieEntity {

    public ZombifiedPiglinEntityMixin(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getXpToDrop(PlayerEntity player) {
        this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
        return super.getXpToDrop(player);
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createZombifiedPiglinAttributes() {
        final double zombieSpawnReinforcements = 0.0D;
        final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.33000000427232513D : 0.23000000427232513D;
        final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 7.0D : 2.0D;
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS, zombieSpawnReinforcements)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (!super.tryAttack(target)) {
            return false;
        } else {
            if (SpeedrunnerMod.options().main.doomMode && target instanceof PlayerEntity) {
                ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
            }

            return true;
        }
    }
}