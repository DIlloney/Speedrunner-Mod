package net.dillon8775.speedrunnermod.mixin.main.item;

import net.dillon8775.speedrunnermod.util.TickCalculator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.FireChargeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * Gives the fire charge item a greater purpose
 */
@Mixin(FireChargeItem.class)
public class FirechargeItemMixin extends Item {

    public FirechargeItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (options().main.throwableFireballs) {
            ItemStack stack = player.getStackInHand(hand);
            if (!world.isClient) {
                Vec3d lookVec = player.getRotationVec(1.0F);
                double speed = 1.5;
                FireballEntity fireball = new FireballEntity(world, player, lookVec.x * speed, lookVec.y * speed, lookVec.z * speed, options().advanced.throwableFireballsExplosionPower);
                fireball.updatePosition(player.getX(), player.getEyeY(), player.getZ());
                fireball.setOwner(player);
                world.spawnEntity(fireball);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                player.getItemCooldownManager().set(this, TickCalculator.seconds(1));
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }

                return TypedActionResult.success(stack);
            }
        }

        return super.use(world, player, hand);
    }
}