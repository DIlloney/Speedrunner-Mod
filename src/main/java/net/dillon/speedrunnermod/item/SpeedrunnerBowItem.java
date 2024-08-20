package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.render.ModRenderers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * The {@link SpeedrunnerMod} bow, which charges faster, shoots farther, does more damage, and has more durability.
 */
public class SpeedrunnerBowItem extends BowItem {

    public SpeedrunnerBowItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(768));
    }

    /**
     * See comments inside method for changes.
     */
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity)user;
        ItemStack itemStack = playerEntity.getProjectileType(stack);
        if (itemStack.isEmpty()) {
            return;
        }
        int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
        float f = getPullProgress(i);
        if ((double)f < 0.1) {
            return;
        }
        List<ItemStack> list = BowItem.load(stack, itemStack, playerEntity);
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld)world;
            if (!list.isEmpty()) {
                this.shootAll(serverWorld, playerEntity, playerEntity.getActiveHand(), stack, list, f * 3.0f, 1.0f, f == 1.0f, null);
            }
        }
        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + f * 0.5f);
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    /**
     * See {@link BowItem} and {@link ModRenderers#init()} for more.
     */
    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 15.0F;
        if ((f = (f * f + f * 1.5F) / 2.5F) > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 54000;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_bow.tooltip.line1").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_bow.tooltip.line2").formatted(Formatting.GRAY));
        }
    }
}