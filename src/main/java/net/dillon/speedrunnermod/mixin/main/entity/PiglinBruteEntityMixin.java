package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(PiglinBruteEntity.class)
public abstract class PiglinBruteEntityMixin extends AbstractPiglinEntity {

    public PiglinBruteEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop(PlayerEntity player) {
        if (player != null) {
            this.experiencePoints = 20 + EnchantmentHelper.getLooting(player) * 72;
        }
        return super.getXpToDrop(player);
    }

    /**
     * Modifies the piglin brute's maximum health.
     */
    @ModifyArg(method = "createPiglinBruteAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", ordinal = 0), index = 1)
    private static double genericMaxHealth(double baseValue) {
        return DOOM_MODE ? 25.0D : 50.0D;
    }
}