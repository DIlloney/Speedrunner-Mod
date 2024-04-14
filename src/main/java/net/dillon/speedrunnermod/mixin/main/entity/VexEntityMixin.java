package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(VexEntity.class)
public class VexEntityMixin extends HostileEntity {

    public VexEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop() {
        if (attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(attackingPlayer) * 36;
        }
        return super.getXpToDrop();
    }

    /**
     * Modifies the vexes attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createVexAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 7.0D : 14.0D;
        final double genericAttackDamage = DOOM_MODE ? 3.0D : 4.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
    }

    /**
     * Disables vexes from {@code noClipping} in {@code doom mode.}
     */
    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/VexEntity;noClip:Z"))
    private void setNoClip(VexEntity vex, boolean value) {
        vex.noClip = !DOOM_MODE;
    }

    /**
     * Increases the damage dealt to themselves when decaying.
     */
    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/VexEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 1)
    private float amount(float amount) {
        return SpeedrunnerMod.getVexDecayDamageMultiplier();
    }

    /**
     * Makes vexes take fall damage from doom mode.
     */
    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return !DOOM_MODE;
    }
}