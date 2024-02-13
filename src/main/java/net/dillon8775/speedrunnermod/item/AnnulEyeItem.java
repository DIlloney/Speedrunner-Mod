package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.option.ClientModOptions;
import net.dillon8775.speedrunnermod.util.ItemUtil;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * An eye of ender that locates the exact distance of the stronghold, in meters/blocks, and tells it to the player.
 */
public class AnnulEyeItem extends Item {
    public AnnulEyeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (world instanceof ServerWorld) {
            BlockPos blockPos = ((ServerWorld)world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld)world, StructureFeature.STRONGHOLD, player.getBlockPos(), 100, false);
            BlockPos playerpos = player.getBlockPos();
            if (blockPos != null) {
                EyeOfEnderEntity eyeOfEnderEntity = new EyeOfEnderEntity(world, player.getX(), player.getBodyY(0.5D), player.getZ());
                eyeOfEnderEntity.setItem(itemStack);
                eyeOfEnderEntity.initTargetPos(blockPos);
                world.spawnEntity(eyeOfEnderEntity);
                if (player instanceof ServerPlayerEntity) {
                    Criteria.USED_ENDER_EYE.trigger((ServerPlayerEntity)player, blockPos);
                }

                int structureDistance = MathHelper.floor(ItemUtil.getDistance(playerpos.getX(), playerpos.getZ(), blockPos.getX(), blockPos.getZ()));
                String structureType = "Stronghold";
                player.sendMessage(new TranslatableText("item.speedrunnermod.eye_of_annul.blocks_away", structureType, structureDistance).formatted(ClientModOptions.isActionbar() ? Formatting.LIGHT_PURPLE : Formatting.WHITE), ClientModOptions.isActionbar());

                world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.HOSTILE, 0.5F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                world.syncWorldEvent((PlayerEntity)null, 1003, player.getBlockPos(), 0);
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                player.incrementStat(Stats.USED.getOrCreateStat(this));
                player.swingHand(hand, true);
                return TypedActionResult.success(itemStack);
            }
        }

        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (SpeedrunnerModClient.clientOptions().itemTooltips) {
            tooltip.add(new TranslatableText("item.speedrunnermod.eye_of_annul.tooltip.line1"));
            tooltip.add(new TranslatableText("item.speedrunnermod.eye_of_annul.tooltip.line2"));
        }
    }
}