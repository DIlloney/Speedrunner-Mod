package net.dilloney.speedrunnermod.mod;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.structure.RuinedPortalStructurePiece;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.RuinedPortalFeature;
import net.minecraft.world.gen.feature.RuinedPortalFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
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

/**
 * This class fixes anything that the mod adds, and needs to be implemented, by use, into the game using {@link Mixin}s.
 * <h2> "block" package </h2>
 * <p> {@linkplain NetherPortalFix}, {@linkplain BeehiveBlockFix}, {@linkplain PumpkinBlockFix}, {@linkplain TntBlockFix}, {@linkplain TripwireBlockFix}. </p>
 * <h2> "enchantment" package </h2>
 * <p> {@linkplain EfficiencyEnchantmentFix} </p>
 * <h2> "entity" package </h2>
 * <p> {@linkplain SheepFix}, {@linkplain SnowGolemFix}, {@linkplain PlayerFix}, {@linkplain InventoryFix} </p>
 * <h2> "item" package </h2>
 * <p> {@linkplain ItemPredicateFix}, {@linkplain CrossbowFix} </p>
 * <h2> "recipe" package </h2>
 * <p> {@linkplain InfinityPearlModeFix} </p>
 * <h2> "world" package </h2>
 * <p> {@linkplain RuinedPortalGenerationFix} </p>
 */
public class ModFixes {

    @Mixin(AbstractFireBlock.class)
    public static class NetherPortalFix {

        @Overwrite
        private static boolean method_30033(World world, BlockPos blockPos, Direction direction) {
            return SpeedrunnerMod.fixNetherPortalBaseBlocks(world, blockPos, direction);
        }
    }

    @Mixin(BeehiveBlock.class)
    public static class BeehiveBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;", ordinal = 0))
        private Item onUse(ItemStack stack) {
            return SpeedrunnerMod.fixBeehiveBlock(stack);
        }
    }

    @Mixin(PumpkinBlock.class)
    public static class PumpkinBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item onUse(ItemStack stack) {
            return SpeedrunnerMod.fixPumpkinBlock(stack);
        }
    }

    @Mixin(TntBlock.class)
    public static class TntBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item onUse(ItemStack stack) {
            return SpeedrunnerMod.fixTntBlock(stack);
        }
    }

    @Mixin(TripwireBlock.class)
    public static class TripwireBlockFix {

        @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item onBreak(ItemStack stack) {
            return SpeedrunnerMod.fixTripwireBlock(stack);
        }
    }

    @Mixin(EfficiencyEnchantment.class)
    public static class EfficiencyEnchantmentFix {

        @Redirect(method = "isAcceptableItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item isAcceptableItem(ItemStack stack) {
            return SpeedrunnerMod.fixEfficiencyEnchantment(stack);
        }
    }

    @Mixin(SheepEntity.class)
    public static class SheepFix {

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item interactMob(ItemStack stack) {
            return SpeedrunnerMod.fixShearingSheep(stack);
        }
    }

    @Mixin(SnowGolemEntity.class)
    public static class SnowGolemFix {

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item interactMob(ItemStack stack) {
            return SpeedrunnerMod.fixShearingSnowGolem(stack);
        }
    }

    @Mixin(PlayerEntity.class)
    public abstract static class PlayerFix {

        @Shadow
        abstract ItemCooldownManager getItemCooldownManager();

        @Inject(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getItemCooldownManager()Lnet/minecraft/entity/player/ItemCooldownManager;"))
        private void disableShield(CallbackInfo ci) {
            this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 80);
        }

        @Redirect(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item damageShield(ItemStack stack) {
            return SpeedrunnerMod.fixShields(stack);
        }
    }

    @Mixin(value = MobEntity.class, priority = 999)
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
    @Mixin(ItemPredicate.class)
    public static class ItemPredicateFix {

        @ModifyVariable(method = "test", at = @At("HEAD"))
        public ItemStack test(ItemStack stack) {
            return SpeedrunnerMod.fixSpeedrunnerShears(stack);
        }
    }

    @Mixin(CrossbowItem.class)
    public static class CrossbowFix {

        @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private static Item tickMovement(ItemStack stack) {
            return SpeedrunnerMod.fixCrossbowItem(stack);
        }
    }

    @Mixin(ShapelessRecipe.class)
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

    @Mixin(RuinedPortalFeature.Start.class)
    public static class RuinedPortalGenerationFix extends StructureStart<RuinedPortalFeatureConfig> {

        public RuinedPortalGenerationFix(StructureFeature<RuinedPortalFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
            super(feature, chunkX, chunkZ, box, references, seed);
        }

        @Overwrite
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, RuinedPortalFeatureConfig ruinedPortalFeatureConfig) {
            RuinedPortalStructurePiece.Properties properties = new RuinedPortalStructurePiece.Properties();
            RuinedPortalStructurePiece.VerticalPlacement verticalPlacement6;
            if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.DESERT) {
                verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
                properties.airPocket = false;
                properties.mossiness = 0.0F;
            } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.JUNGLE) {
                verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
                properties.airPocket = this.random.nextFloat() < 0.5F;
                properties.mossiness = 0.8F;
                properties.overgrown = true;
                properties.vines = true;
            } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.SWAMP) {
                verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_OCEAN_FLOOR;
                properties.airPocket = false;
                properties.mossiness = 0.5F;
                properties.vines = true;
            } else {
                boolean bl2;
                if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.MOUNTAIN) {
                    bl2 = this.random.nextFloat() < 0.5F;
                    verticalPlacement6 = bl2 ? RuinedPortalStructurePiece.VerticalPlacement.IN_MOUNTAIN : RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
                    properties.airPocket = bl2 || this.random.nextFloat() < 0.5F;
                } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.OCEAN) {
                    verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_OCEAN_FLOOR;
                    properties.airPocket = false;
                    properties.mossiness = 0.8F;
                } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.NETHER) {
                    verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.IN_NETHER;
                    properties.airPocket = this.random.nextFloat() < 0.5F;
                    properties.mossiness = 0.0F;
                    properties.replaceWithBlackstone = true;
                } else {
                    bl2 = this.random.nextFloat() < 0.5F;
                    verticalPlacement6 = bl2 ? RuinedPortalStructurePiece.VerticalPlacement.UNDERGROUND : RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
                    properties.airPocket = bl2 || this.random.nextFloat() < 0.5F;
                }
            }

            Identifier identifier2;
            if (this.random.nextFloat() < 0.05F) {
                identifier2 = new Identifier(RuinedPortalFeature.RARE_PORTAL_STRUCTURE_IDS[this.random.nextInt(RuinedPortalFeature.RARE_PORTAL_STRUCTURE_IDS.length)]);
            } else {
                identifier2 = new Identifier(RuinedPortalFeature.COMMON_PORTAL_STRUCTURE_IDS[this.random.nextInt(RuinedPortalFeature.COMMON_PORTAL_STRUCTURE_IDS.length)]);
            }

            Structure structure = structureManager.getStructureOrBlank(identifier2);
            BlockRotation blockRotation = (BlockRotation) Util.getRandom(BlockRotation.values(), this.random);
            BlockMirror blockMirror = this.random.nextFloat() < 0.5F ? BlockMirror.NONE : BlockMirror.FRONT_BACK;
            BlockPos blockPos = new BlockPos(structure.getSize().getX() / 2, 0, structure.getSize().getZ() / 2);
            BlockPos blockPos2 = (new ChunkPos(i, j)).getStartPos();
            BlockBox blockBox = structure.method_27267(blockPos2, blockRotation, blockPos, blockMirror);
            Vec3i vec3i = blockBox.getCenter();
            int k = vec3i.getX();
            int l = vec3i.getZ();
            int m = chunkGenerator.getHeight(k, l, RuinedPortalStructurePiece.getHeightmapType(verticalPlacement6)) - 1;
            int n = RuinedPortalFeature.method_27211(this.random, chunkGenerator, verticalPlacement6, properties.airPocket, m, blockBox.getBlockCountY(), blockBox);
            BlockPos blockPos3 = new BlockPos(blockPos2.getX(), n, blockPos2.getZ());
            if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.MOUNTAIN || ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.OCEAN || ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.STANDARD) {
                properties.cold = RuinedPortalFeature.method_27209(blockPos3, biome);
            }

            this.children.add(new RuinedPortalStructurePiece(blockPos3, verticalPlacement6, properties, identifier2, structure, blockRotation, blockMirror, blockPos));
            this.setBoundingBoxFromChildren();
        }
    }
}