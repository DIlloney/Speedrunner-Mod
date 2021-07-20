package com.dilloney.speedrunnermod.items;

import com.dilloney.speedrunnermod.materials.ModToolMaterials;
import com.dilloney.speedrunnermod.registry.ModItemGroup;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.function.Consumer;

public final class SpeedrunnerItem extends Item {

    public SpeedrunnerItem(Settings settings) {
        super(settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 1.5F;
    }

    public static final class SpeedrunnerBow extends BowItem {

        public SpeedrunnerBow(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON).maxCount(1).maxDamage(768));
        }

        @Override
        public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
            if (user instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity) user;
                boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
                ItemStack itemStack = playerEntity.getArrowType(stack);
                if (!itemStack.isEmpty() || bl) {
                    if (itemStack.isEmpty()) {
                        itemStack = new ItemStack(Items.ARROW);
                    }

                    int i = this.getMaxUseTime(stack) - remainingUseTicks;
                    float f = getPullProgress(i);
                    if ((double) f >= 0.1D) {
                        boolean bl2 = bl && itemStack.getItem() == Items.ARROW;
                        if (!world.isClient) {
                            ArrowItem arrowItem = (ArrowItem) ((ArrowItem) (itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW));
                            PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);

                            persistentProjectileEntity.setCustomName(new LiteralText("Arrow"));

                            persistentProjectileEntity.setProperties(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 3.5F, 1.0F);
                            if (f == 1.0F) {
                                persistentProjectileEntity.setCritical(true);
                            }

                            int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                            if (j > 0) {
                                persistentProjectileEntity.setDamage(
                                        persistentProjectileEntity.getDamage() + (double) j * 0.5D + this.getBaseDamage());
                            } else {
                                persistentProjectileEntity.setDamage(this.getBaseDamage());
                            }

                            int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                            if (k > 0) {
                                persistentProjectileEntity.setPunch(k);
                            }

                            if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                                persistentProjectileEntity.setOnFireFor(100);
                            }

                            stack.damage(1, (LivingEntity) playerEntity, (Consumer) ((p) -> {
                                ((LivingEntity) p).sendToolBreakStatus(playerEntity.getActiveHand());
                            }));
                            if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.getItem() == Items.SPECTRAL_ARROW || itemStack.getItem() == Items.TIPPED_ARROW)) { persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                            }

                            world.spawnEntity(persistentProjectileEntity);
                        }

                        world.playSound((PlayerEntity) null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                        if (!bl2 && !playerEntity.getAbilities().creativeMode) {
                            itemStack.decrement(1);
                            if (itemStack.isEmpty()) {
                                playerEntity.getInventory().removeOne(itemStack);
                            }
                        }

                        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                    }
                }
            }
        }

        @Override
        public int getEnchantability() {
            return 11;
        }

        @Override
        public boolean canRepair(ItemStack stack, ItemStack ingredient) {
            return ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE.getRepairIngredient().test(ingredient);
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }


        public float getBaseDamage() {
            return 3.0F;
        }
    }

    public static final class SpeedrunnerCrossbow extends CrossbowItem {

        public SpeedrunnerCrossbow(Item.Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).rarity(Rarity.COMMON).maxCount(1).maxDamage(652));
        }

        @Override
        public boolean isUsedOnRelease(ItemStack stack) {
            return stack.getItem() == ModItems.SPEEDRUNNER_CROSSBOW;
        }

        @Override
        public int getMaxUseTime(ItemStack stack) {
            return getPullTime(stack) + 3;
        }

        @Override
        public boolean canRepair(ItemStack stack, ItemStack ingredient) {
            return ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE.getRepairIngredient().test(ingredient);
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }

        @Override
        public int getEnchantability() {
            return 17;
        }

        public static int getPullTime(ItemStack stack) {
            int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
            return i == 0 ? 20 : 20 - 5 * i;
        }
    }

    public static final class SpeedrunnerFlintAndSteel extends FlintAndSteelItem {

        public SpeedrunnerFlintAndSteel(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON).maxCount(1).maxDamage(128));
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }
    }

    public static final class SpeedrunnerShears extends ShearsItem {

        public SpeedrunnerShears(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON).maxCount(1).maxDamage(476));
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            if (!state.isOf(Blocks.COBWEB) && !state.isIn(BlockTags.LEAVES)) {
                if (state.isIn(BlockTags.WOOL)) {
                    return 7.5F;
                } else {
                    return !state.isOf(Blocks.VINE) && !state.isOf(Blocks.GLOW_LICHEN) ? super.getMiningSpeedMultiplier(stack, state) : 2.0F;
                }
            } else {
                return 17.0F;
            }
        }
    }

    public static final class SpeedrunnerFishingRod extends FishingRodItem {

        public SpeedrunnerFishingRod(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).rarity(Rarity.COMMON).maxCount(1).maxDamage(128));
        }

        @Override
        public int getEnchantability() {
            return 17;
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }
    }

    public static final class SpeedrunnerCarrotOnAStick extends OnAStickItem {

        public SpeedrunnerCarrotOnAStick(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).rarity(Rarity.COMMON).maxDamage(50), EntityType.PIG, 3);
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }
    }

    public static final class SpeedrunnerWarpedFungusOnAStick extends OnAStickItem {

        public SpeedrunnerWarpedFungusOnAStick(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).rarity(Rarity.COMMON).maxDamage(150), EntityType.STRIDER, 1);
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }
    }
}
