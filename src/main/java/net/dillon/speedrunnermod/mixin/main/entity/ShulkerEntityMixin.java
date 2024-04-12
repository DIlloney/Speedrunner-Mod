package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(ShulkerEntity.class)
public abstract class ShulkerEntityMixin extends GolemEntity {
    @Shadow
    abstract boolean isClosed();
    @Shadow
    abstract void spawnNewShulker();

    public ShulkerEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop(PlayerEntity player) {
        if (player != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
        }
        return super.getXpToDrop(player);
    }

    /**
     * Modifies the shulker's maximum health.
     */
    @ModifyArg(method = "createShulkerAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
    private static double genericMaxHealth(double baseValue) {
        return DOOM_MODE ? 32.0D : 20.0D;
    }

    /**
     * Prevents shulkers from teleporting, and allows them to be shot with arrows, even when closed.
     */
    @Overwrite
    public boolean damage(DamageSource source, float amount) {
        Entity entity2;
        if (this.isClosed()) {
            entity2 = source.getSource();
            if (DOOM_MODE) {
                if (entity2 instanceof PersistentProjectileEntity) {
                    return false;
                }
            } else {
                if (entity2 instanceof PersistentProjectileEntity && this.random.nextFloat() < 0.25F) {
                    return false;
                }
            }
        }

        if (!super.damage(source, amount)) {
            return false;
        } else {
            if (source.isProjectile()) {
                entity2 = source.getSource();
                if (entity2 != null && entity2.getType() == EntityType.SHULKER_BULLET) {
                    this.spawnNewShulker();
                }
            }

            return true;
        }
    }
}