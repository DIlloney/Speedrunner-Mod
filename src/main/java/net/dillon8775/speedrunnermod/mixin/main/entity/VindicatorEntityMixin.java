package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(VindicatorEntity.class)
public abstract class VindicatorEntityMixin extends IllagerEntity {

    public VindicatorEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
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

    @Overwrite
    public static DefaultAttributeContainer.Builder createVindicatorAttributes() {
        final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 48.0D : 12.0D;
        final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 20.0D : 24.0D;
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange).add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D);
    }

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