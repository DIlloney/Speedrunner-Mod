package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.option.ClientModOptions;
import net.dillon8775.speedrunnermod.tag.ModConfiguredFeatureTags;
import net.dillon8775.speedrunnermod.util.ItemUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * An eye of ender that locates nether fortresses and bastions.
 */
public class InfernoEyeItem extends Item {
    private String structureType = "Fortress";
    private TagKey<ConfiguredStructureFeature<?, ?>> type = ModConfiguredFeatureTags.FORTRESSES;

    public InfernoEyeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld)world;
            if (player.isSneaking() && serverWorld.getRegistryKey().equals(World.NETHER)) {
                if (structureType.equals("Fortress")) {
                    structureType = "Bastion";
                    type = ModConfiguredFeatureTags.BASTIONS;
                } else if (structureType.equals("Bastion")) {
                    structureType = "Fortress";
                    type = ModConfiguredFeatureTags.FORTRESSES;
                }

                player.sendMessage((new TranslatableText("item.speedrunnermod.eye_of_inferno.looking_for", structureType).formatted(ClientModOptions.isActionbar() ? Formatting.RED : Formatting.WHITE)), ClientModOptions.isActionbar());
                return TypedActionResult.success(itemStack);
            }
        }

        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            if (serverWorld.getRegistryKey().equals(World.OVERWORLD) || serverWorld.getRegistryKey().equals(World.END)) {
                return TypedActionResult.consume(itemStack);
            }
        }

        if (!player.isSneaking()) {
            if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld)world;

                serverWorld.getRegistryKey(); {
                    ItemUtil.findStructureAndShoot(world, player, itemStack, structureType, hand, type);

                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                    player.sendMessage((new TranslatableText("item.speedrunnermod.eye_of_inferno.located", structureType).formatted(ClientModOptions.isActionbar() ? Formatting.RED : Formatting.WHITE)), ClientModOptions.isActionbar());
                    return TypedActionResult.success(itemStack);
                }
            }
        }

        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (SpeedrunnerModClient.clientOptions().itemTooltips) {
            tooltip.add(new TranslatableText("item.speedrunnermod.eye_of_inferno.tooltip"));
        }
        tooltip.add(new TranslatableText("item.speedrunnermod.eye_of_inferno.looking_for.tooltip", structureType));
    }
}