<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PhantomEntity.class)
public class PhantomEntityMixin extends FlyingEntity {

    public PhantomEntityMixin(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop() {
        if (attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(attackingPlayer) * 32;
        }
        return super.getXpToDrop();
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PhantomEntity.class)
public class PhantomEntityMixin extends FlyingEntity {

    public PhantomEntityMixin(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop() {
        if (attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(attackingPlayer) * 32;
        }
        return super.getXpToDrop();
    }
>>>>>>> Stashed changes
}