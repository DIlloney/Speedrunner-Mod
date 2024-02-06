package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(VexEntity.class)
public class VexEntityMixin extends HostileEntity {
    @Shadow
    boolean alive;
    @Shadow
    int lifeTicks;

    public VexEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
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
    public static DefaultAttributeContainer.Builder createVexAttributes() {
        final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 7.0D : 14.0D;
        final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 3.0D : 4.0D;
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
    }

    @Overwrite
    public void tick() {
        this.noClip = !SpeedrunnerMod.options().main.doomMode;
        super.tick();
        this.noClip = false;
        this.setNoGravity(true);
        if (this.alive && --this.lifeTicks <= 0) {
            this.lifeTicks = 20;
            final float amount = SpeedrunnerMod.options().main.doomMode ? 100.0F : 1.0F;
            this.damage(DamageSource.STARVE, amount);
        }
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return !SpeedrunnerMod.options().main.doomMode;
    }
}