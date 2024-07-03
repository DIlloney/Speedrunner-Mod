package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.option.ModOptions;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Item util methods, used for other item classes.
 */
public class ItemUtil {

    /**
     * See {@link net.dillon.speedrunnermod.item.SpeedrunnersEyeItem} and {@link net.dillon.speedrunnermod.item.InfernoEyeItem} for more.
     */
    @Author(Authors.KWPUGH)
    public static void findStructureAndShoot(World world, PlayerEntity player, ItemStack itemstack, TagKey<Structure> type) {
        BlockPos playerpos = player.getBlockPos();
        ServerWorld serverWorld = (ServerWorld)world;
        BlockPos locpos = serverWorld.locateStructure(type, playerpos, 100, false);

        EyeOfEnderEntity finderentity = new EyeOfEnderEntity(world, player.getX(), player.getBodyY(0.5D), player.getZ());
        finderentity.setItem(itemstack);
        finderentity.initTargetPos(locpos);
        world.spawnEntity(finderentity);

        if (player instanceof ServerPlayerEntity) {
            Criteria.USED_ENDER_EYE.trigger((ServerPlayerEntity)player, locpos);
        }

        world.syncWorldEvent(null, 1003, player.getBlockPos(), 0);
    }

    /**
     * Adds the tooltips for a {@code State-Of-The-Art} item.
     */
    public static void stateOfTheArtItem(List<Text> tooltip) {
        tooltip.add(Text.translatable("item.speedrunnermod.state_of_the_art.tooltip").formatted(Formatting.RED));
        if (options().main.stateOfTheArtItems) {
            tooltip.add(Text.translatable("item.speedrunnermod.state_of_the_art.tooltip.enabled").formatted(Formatting.GREEN).formatted(Formatting.ITALIC));
        } else {
            tooltip.add(Text.translatable("item.speedrunnermod.state_of_the_art.tooltip.disabled").formatted(Formatting.RED).formatted(Formatting.ITALIC));
        }
    }

    @Author(Authors.KWPUGH)
    public static float getDistance(int x1, int z1, int x2, int z2) {
        int i = x2 - x1;
        int j = z2 - z1;

        return MathHelper.sqrt((float) (i * i + j * j));
    }

    /**
     * Returns a specific type of formatting.
     */
    public static Formatting toFormatting(Formatting actionbarOn, Formatting actionbarOff) {
        if (ModOptions.ItemMessages.isActionbar()) {
            return actionbarOn;
        } else {
            return actionbarOff;
        }
    }
}