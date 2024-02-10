package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.UUID;

@Mixin(ShulkerEntity.class)
public abstract class ShulkerEntityMixin extends GolemEntity {
    @Shadow
    @Final
    private static UUID COVERED_ARMOR_BONUS_ID;
    @Shadow
    private static final EntityAttributeModifier COVERED_ARMOR_BONUS = new EntityAttributeModifier(COVERED_ARMOR_BONUS_ID, "Covered armor bonus", 10.0D, EntityAttributeModifier.Operation.ADDITION);
    @Shadow
    abstract boolean isClosed();
    @Shadow
    abstract void spawnNewShulker();

    public ShulkerEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getXpToDrop(PlayerEntity player) {
        this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
        if (this.experiencePoints > 0) {
            int i = this.experiencePoints;

            int j;
            for(j = 0; j < this.armorItems.size(); ++j) {
                if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                    i += 1 + this.random.nextInt(3);
                }
            }

            for(j = 0; j < this.handItems.size(); ++j) {
                if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                    i += 1 + this.random.nextInt(3);
                }
            }

            return i;
        } else {
            return this.experiencePoints;
        }
    }

    @ModifyArg(method = "createShulkerAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
    private static double genericMaxHealth(double baseValue) {
        return SpeedrunnerMod.options().main.doomMode ? 32.0D : 20.0D;
    }

    @Overwrite
    public boolean damage(DamageSource source, float amount) {
        Entity entity2;
        if (this.isClosed()) {
            entity2 = source.getSource();
            if (SpeedrunnerMod.options().main.doomMode) {
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