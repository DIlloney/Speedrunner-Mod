package net.dillon.speedrunnermod.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Hoglin;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Used in {@link net.dillon.speedrunnermod.mixin.main.entity.giant.GiantEntityMixin}
 */
public interface Giant {

    static boolean tryAttack(LivingEntity attacker, LivingEntity target) {
        float f = (float)attacker.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        float g = !attacker.isBaby() && (int)f > 0 ? f / 2.0f + (float)attacker.getWorld().random.nextInt((int)f) : f;
        DamageSource damageSource = attacker.getDamageSources().mobAttack(attacker);
        boolean bl = target.damage(attacker.getDamageSources().mobAttack(attacker), g);
        if (bl) {
            World world = attacker.getWorld();
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld)world;
                EnchantmentHelper.onTargetDamaged(serverWorld, target, damageSource);
            }
            if (!attacker.isBaby()) {
                Hoglin.knockback(attacker, target);
            }
        }
        return bl;
    }

    static void knockback(LivingEntity attacker, LivingEntity target) {
        double e;
        double d = attacker.getAttributeValue(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);
        double f = d - (e = target.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
        if (f <= 0.0) {
            return;
        }
        double g = target.getX() - attacker.getX();
        double h = target.getZ() - attacker.getZ();
        float i = attacker.getWorld().random.nextInt(21) - 10;
        double j = f * (double)(attacker.getWorld().random.nextFloat() * 0.5f + 0.2f);
        Vec3d vec3d = new Vec3d(g, 0.0, h).normalize().multiply(j).rotateY(i);
        double k = f * (double)attacker.getWorld().random.nextFloat() * 0.5;
        target.addVelocity(vec3d.x, k, vec3d.z);
        target.velocityModified = true;
    }
}