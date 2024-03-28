package net.dillon8775.speedrunnermod.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

/**
 * Better than iron, worse than diamond, deals more damage to withers and giants.
 */
public class SpeedrunnerSwordItem extends SwordItem {

    public SpeedrunnerSwordItem(int attackDamage, Settings settings) {
        super(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, attackDamage, -2.4F, settings.group(ItemGroup.COMBAT));
    }

    /**
     * Deals more damage to withers, and giants under certain conditions.
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof WitherEntity) {
            target.damage(DamageSource.player((PlayerEntity)attacker), getAttackDamage() * 4.45F);
        } else if (target instanceof GiantEntity giant) {
            if (!(giant.getHealth() <= 150)) {
                target.damage(DamageSource.player((PlayerEntity)attacker), getAttackDamage() * 2.25F);
            }
        }
        return super.postHit(stack, target, attacker);
    }
}