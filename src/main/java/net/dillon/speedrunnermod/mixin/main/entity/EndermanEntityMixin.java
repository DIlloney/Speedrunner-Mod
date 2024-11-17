package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin extends HostileEntity {

    public EndermanEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop(ServerWorld world) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 10 + EnchantmentHelper.getEquipmentLevel(ItemUtil.enchantment((EndermanEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 48;
        }
        return super.getXpToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code enderman} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createEndermanAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 60.0D : 25.0D;
        final double genericMovementSpeed = 0.30000001192092896D;
        final double genericAttackDamage = DOOM_MODE ? 8.0D : 4.0D;
        final double genericFollowRange = DOOM_MODE ? 64.0D : 12.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed).
                add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.FOLLOW_RANGE, genericFollowRange);
    }
}