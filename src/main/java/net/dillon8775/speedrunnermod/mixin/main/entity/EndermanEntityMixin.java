package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin extends HostileEntity {

    public EndermanEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getXpToDrop(PlayerEntity player) {
        this.experiencePoints = 10 + EnchantmentHelper.getLooting(player) * 48;
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
    public static DefaultAttributeContainer.Builder createEndermanAttributes() {
        final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 60.0D : 25.0D;
        final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
        final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 64.0D : 12.0D;
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
    }
}