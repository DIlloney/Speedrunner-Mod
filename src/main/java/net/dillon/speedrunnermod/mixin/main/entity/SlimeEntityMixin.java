package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin extends MobEntity {

    public SlimeEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
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
     * Increases the time it takes for slimes to make a full jump.
     */
    @Overwrite
    public int getTicksUntilNextJump() {
        return SpeedrunnerMod.getSlimeJumpTime();
    }

    /**
     * Decreases the amount of damage that magma cubes do.
     */
    @Overwrite
    public float getDamageAmount() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * SpeedrunnerMod.getSlimeDamageMultiplier();
    }
}