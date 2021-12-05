package net.dilloney.speedrunnermod.mixin;

import net.dilloney.speedrunnermod.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        private static boolean shouldLightPortalAt(World world, BlockPos pos, Direction direction) {
            if (!isOverworldOrNether(world)) {
                return false;
            } else {
                BlockPos.Mutable mutable = pos.mutableCopy();
                boolean bl = false;
                Direction[] var5 = Direction.values();
                int var6 = var5.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    Direction direction2 = var5[var7];
                    if (world.getBlockState(mutable.set(pos).move(direction2)).isOf(Blocks.OBSIDIAN) || world.getBlockState(mutable.set(pos).move(direction2)).isOf(Blocks.CRYING_OBSIDIAN)) {
                        bl = true;
                        break;
                    }
                }

                if (!bl) {
                    return false;
                } else {
                    Direction.Axis axis = direction.getAxis().isHorizontal() ? direction.rotateYCounterclockwise().getAxis() : Direction.Type.HORIZONTAL.randomAxis(world.random);
                    return AreaHelper.getNewPortal(world, pos, axis).isPresent();
                }
            }
        }

        private static boolean isOverworldOrNether(World world) {
            return world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER;
        }
    }

    @Mixin(net.minecraft.block.BeehiveBlock.class)
    public static class BeehiveBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
        private boolean onUse(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
        }
    }

    @Mixin(net.minecraft.block.PumpkinBlock.class)
    public static class PumpkinBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onUse(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
        }
    }

    @Mixin(net.minecraft.block.TntBlock.class)
    public static class TntBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onUse(ItemStack stack, Item item) {
            return stack.isOf(Items.FLINT_AND_STEEL) || stack.isOf(ModItems.SPEEDRUNNER_FLINT_AND_STEEL) || stack.isOf(Items.FIRE_CHARGE);
        }
    }

    @Mixin(net.minecraft.block.TripwireBlock.class)
    public static class TripwireBlockFix {

        @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onBreak(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
        }
    }

    @Mixin(net.minecraft.enchantment.EfficiencyEnchantment.class)
    public static class EfficiencyEnchantmentFix {

        @Redirect(method = "isAcceptableItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean isAcceptableItem(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
        }
    }

    @Mixin(net.minecraft.entity.passive.SheepEntity.class)
    public static class SheepFix {

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean interactMob(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
        }
    }

    @Mixin(net.minecraft.entity.passive.SnowGolemEntity.class)
    public static class SnowGolemFix {

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean interactMob(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
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

        @Redirect(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean damageShield(ItemStack stack, Item item) {
            return stack.isOf(Items.SHIELD) || stack.isOf(ModItems.SPEEDRUNNER_SHIELD);
        }
    }

    @Mixin(net.minecraft.entity.LivingEntity.class)
    public static class InventoryFix {

        @Redirect(method = "getPreferredEquipmentSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 2))
        private static boolean getPreferredEquipmentSlot(ItemStack stack, Item item) {
            return stack.isOf(Items.SHIELD) || stack.isOf(ModItems.SPEEDRUNNER_SHIELD);
        }
    }

    @Deprecated
    @Mixin(net.minecraft.predicate.item.ItemPredicate.class)
    public static class ItemPredicateFix {

        @ModifyVariable(method = "test", at = @At("HEAD"))
        private ItemStack fixSpeedrunnerShears(ItemStack stack) {
            if (stack.getItem().getDefaultStack().isOf(ModItems.SPEEDRUNNER_SHEARS)) {
                ItemStack itemStack = new ItemStack(Items.SHEARS);
                itemStack.setCount(stack.getCount());
                itemStack.setNbt(stack.getOrCreateNbt());
                return itemStack;
            }

            return stack;
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

    @Mixin(net.minecraft.world.gen.feature.RuinedPortalFeature.class)
    public static class RuinedPortalGenerationFix {

        @ModifyVariable(method = "addPieces", at = @At(value = "STORE", ordinal = 0), index = 1)
        private static RuinedPortalStructurePiece.VerticalPlacement addPieces(RuinedPortalStructurePiece.VerticalPlacement verticalPlacement) {
            return RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
        }
    }
}