package net.dilloney.speedrunnermod.mixin;

import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.util.UniqueItemRegistry;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.structure.RuinedPortalStructurePiece;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class ModFixes {

    @Mixin(net.minecraft.block.AbstractFireBlock.class)
    public static class NetherPortalFix {

        @Overwrite
        public static boolean method_30033(World world, BlockPos blockPos, Direction direction) {
            if (!method_30366(world)) {
                return false;
            } else {
                BlockPos.Mutable mutable = blockPos.mutableCopy();
                boolean bl = false;
                Direction[] var5 = Direction.values();
                int var6 = var5.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    Direction direction2 = var5[var7];
                    if (world.getBlockState(mutable.set(blockPos).move(direction2)).isOf(Blocks.OBSIDIAN) || world.getBlockState(mutable.set(blockPos).move(direction2)).isOf(Blocks.CRYING_OBSIDIAN)) {
                        bl = true;
                        break;
                    }
                }

                if (!bl) {
                    return false;
                } else {
                    Direction.Axis axis = direction.getAxis().isHorizontal() ? direction.rotateYCounterclockwise().getAxis() : Direction.Type.HORIZONTAL.randomAxis(world.random);
                    return AreaHelper.method_30485(world, blockPos, axis).isPresent();
                }
            }
        }

        private static boolean method_30366(World world) {
            return world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER;
        }
    }

    @Mixin(net.minecraft.block.BeehiveBlock.class)
    public static class BeehiveBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;", ordinal = 0))
        private Item onUse(ItemStack stack) {
            return UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(net.minecraft.block.PumpkinBlock.class)
    public static class PumpkinBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item onUse(ItemStack stack) {
            return UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(net.minecraft.block.TntBlock.class)
    public static class TntBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item onUse(ItemStack stack) {
            return UniqueItemRegistry.TNT_BLOCK_IGNITERS.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(net.minecraft.block.TripwireBlock.class)
    public static class TripwireBlockFix {

        @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item onBreak(ItemStack stack) {
            return  UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(net.minecraft.enchantment.EfficiencyEnchantment.class)
    public static class EfficiencyEnchantmentFix {

        @Redirect(method = "isAcceptableItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item isAcceptableItem(ItemStack stack) {
            return  UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(net.minecraft.entity.passive.SheepEntity.class)
    public static class SheepFix {

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item interactMob(ItemStack stack) {
            return UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(net.minecraft.entity.passive.SnowGolemEntity.class)
    public static class SnowGolemFix {

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item interactMob(ItemStack stack) {
            return UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(net.minecraft.entity.player.PlayerEntity.class)
    public abstract static class PlayerFix {

        @Shadow
        abstract ItemCooldownManager getItemCooldownManager();

        @Inject(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getItemCooldownManager()Lnet/minecraft/entity/player/ItemCooldownManager;"))
        private void disableShield(CallbackInfo ci) {
            this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 80);
        }

        @Redirect(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item damageShield(ItemStack stack) {
            return UniqueItemRegistry.SHIELD.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(value = net.minecraft.entity.mob.MobEntity.class, priority = 999)
    public static class InventoryFix {

        @Overwrite
        public static EquipmentSlot getPreferredEquipmentSlot(ItemStack stack) {
            Item item = stack.getItem();
            if (item != Blocks.CARVED_PUMPKIN.asItem() && (!(item instanceof BlockItem) || !(((BlockItem)item).getBlock() instanceof AbstractSkullBlock))) {
                if (item instanceof ArmorItem) {
                    return ((ArmorItem)item).getSlotType();
                } else if (item == Items.ELYTRA) {
                    return EquipmentSlot.CHEST;
                } else {
                    return item == Items.SHIELD || item == ModItems.SPEEDRUNNER_SHIELD ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                }
            } else {
                return EquipmentSlot.HEAD;
            }
        }
    }

    @Deprecated
    @Mixin(net.minecraft.predicate.item.ItemPredicate.class)
    public static class ItemPredicateFix {

        @ModifyVariable(method = "test", at = @At("HEAD"))
        private ItemStack test(ItemStack stack) {
            if (stack.getItem().getDefaultStack().getItem() == ModItems.SPEEDRUNNER_SHEARS) {
                ItemStack itemStack = new ItemStack(Items.SHEARS);
                itemStack.setCount(stack.getCount());
                itemStack.setTag(stack.getOrCreateTag());
                return itemStack;
            }

            return stack;
        }
    }

    @Mixin(net.minecraft.item.CrossbowItem.class)
    public static class CrossbowFix {

        @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private static Item tickMovement(ItemStack stack) {
            return UniqueItemRegistry.CROSSBOW.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(net.minecraft.recipe.ShapelessRecipe.class)
    public static class InfinityPearlModeFix {
        @Shadow @Final
        private Identifier id;

        @Inject(method = "matches", at = @At("HEAD"), cancellable = true)
        private void matches(CraftingInventory craftingInventory, World world, CallbackInfoReturnable<Boolean> cir) {
            if (id.toString().equals("minecraft:ender_eye") || id.toString().equals("speedrunnermod:inferno_eye") || id.toString().equals("speedrunnermod:annul_eye")) {
                for (int i = 0; i < craftingInventory.size(); i++) {
                    ItemStack itemStack = craftingInventory.getStack(i);
                    if (itemStack.hasEnchantments()) {
                        cir.setReturnValue(false);
                    }
                }
            }
        }
    }

    @Mixin(net.minecraft.world.gen.feature.RuinedPortalFeature.Start.class)
    public static class RuinedPortalGenerationFix {

        @ModifyVariable(method = "init", at = @At(value = "STORE", ordinal = 0), index = 8)
        private RuinedPortalStructurePiece.VerticalPlacement init(RuinedPortalStructurePiece.VerticalPlacement verticalPlacement) {
            return RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
        }
    }
}