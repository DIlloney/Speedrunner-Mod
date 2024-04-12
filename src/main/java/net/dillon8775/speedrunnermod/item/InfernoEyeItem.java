<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.option.ModOptions;
import net.dillon8775.speedrunnermod.tag.ModStructureTags;
import net.dillon8775.speedrunnermod.util.ItemUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * An eye of ender that locates nether fortresses and bastions.
 */
public class InfernoEyeItem extends Item {
    private String structureType = "Fortress";
    private TagKey<Structure> type = ModStructureTags.FORTRESSES;

    public InfernoEyeItem(Settings settings) {
        super(settings.group(ItemGroup.MISC).fireproof());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (world.getRegistryKey() == World.NETHER) {
                if (player.isSneaking()) {
                    if (structureType.equals("Fortress")) {
                        structureType = "Bastion";
                        type = ModStructureTags.BASTIONS;
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.HOSTILE, 2.0F, 1.0F);
                    } else if (structureType.equals("Bastion")) {
                        structureType = "Fortress";
                        type = ModStructureTags.FORTRESSES;
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.HOSTILE, 2.0F, 1.0F);
                    }

                    player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.looking_for", structureType).formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                } else {
                    ItemUtil.findStructureAndShoot(world, player, itemStack, type);
                    player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.located", structureType).formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }
                }

                player.incrementStat(Stats.USED.getOrCreateStat(this));
                player.swingHand(hand, true);
                return TypedActionResult.success(itemStack);
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.wrong_dimension").formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
            }
        }

        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.eye_of_inferno.tooltip"));
        }
        tooltip.add(Text.translatable("item.speedrunnermod.eye_of_inferno.looking_for.tooltip", structureType).formatted(Formatting.BOLD));
    }
=======
package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.option.ModOptions;
import net.dillon8775.speedrunnermod.tag.ModStructureTags;
import net.dillon8775.speedrunnermod.util.ItemUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * An eye of ender that locates nether fortresses and bastions.
 */
public class InfernoEyeItem extends Item {
    private String structureType = "Fortress";
    private TagKey<Structure> type = ModStructureTags.FORTRESSES;

    public InfernoEyeItem(Settings settings) {
        super(settings.group(ItemGroup.MISC).fireproof());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (world.getRegistryKey() == World.NETHER) {
                if (player.isSneaking()) {
                    if (structureType.equals("Fortress")) {
                        structureType = "Bastion";
                        type = ModStructureTags.BASTIONS;
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.HOSTILE, 2.0F, 1.0F);
                    } else if (structureType.equals("Bastion")) {
                        structureType = "Fortress";
                        type = ModStructureTags.FORTRESSES;
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.HOSTILE, 2.0F, 1.0F);
                    }

                    player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.looking_for", structureType).formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                } else {
                    ItemUtil.findStructureAndShoot(world, player, itemStack, type);
                    player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.located", structureType).formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }
                }

                player.incrementStat(Stats.USED.getOrCreateStat(this));
                player.swingHand(hand, true);
                return TypedActionResult.success(itemStack);
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.wrong_dimension").formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
            }
        }

        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.eye_of_inferno.tooltip"));
        }
        tooltip.add(Text.translatable("item.speedrunnermod.eye_of_inferno.looking_for.tooltip", structureType).formatted(Formatting.BOLD));
    }
>>>>>>> Stashed changes
}