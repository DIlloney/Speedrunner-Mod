package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(GhastEntity.class)
public class GhastEntityMixin extends FlyingEntity {

    public GhastEntityMixin(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop(PlayerEntity player) {
        this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
        return super.getXpToDrop(player);
    }

    /**
     * Modifies the ghast's attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createGhastAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 20.0D : 5.0D;
        final double genericFollowRange = DOOM_MODE ? 100.0D : 50.0D;
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
    }
}