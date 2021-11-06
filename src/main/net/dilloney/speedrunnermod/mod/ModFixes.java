package net.dilloney.speedrunnermod.mod;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
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
 * <p> {@linkplain ItemPredicateFix} </p>
 * <h2> "recipe" package </h2>
 * <p> {@linkplain InfinityPearlModeFix} </p>
 * <h2> "world" package </h2>
 * <p> {@linkplain RuinedPortalGenerationFix} </p>
 */
public class ModFixes {

    @Mixin(AbstractFireBlock.class)
    public static class NetherPortalFix {

        @Overwrite
        private static boolean shouldLightPortalAt(World world, BlockPos pos, Direction direction) {
            return SpeedrunnerMod.fixNetherPortalBaseBlocks(world, pos, direction);
        }
    }

    @Mixin(BeehiveBlock.class)
    public static class BeehiveBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
        private boolean onUse(ItemStack stack, Item item) {
            return SpeedrunnerMod.fixBeehiveBlock(stack);
        }
    }

    @Mixin(PumpkinBlock.class)
    public static class PumpkinBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onUse(ItemStack stack, Item item) {
            return SpeedrunnerMod.fixPumpkinBlock(stack);
        }
    }

    @Mixin(TntBlock.class)
    public static class TntBlockFix {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onUse(ItemStack stack, Item item) {
            return SpeedrunnerMod.fixTntBlock(stack);
        }
    }

    @Mixin(TripwireBlock.class)
    public static class TripwireBlockFix {

        @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onBreak(ItemStack stack, Item item) {
            return SpeedrunnerMod.fixTripwireBlock(stack);
        }
    }

    @Mixin(EfficiencyEnchantment.class)
    public static class EfficiencyEnchantmentFix {

        @Redirect(method = "isAcceptableItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean isAcceptableItem(ItemStack stack, Item item) {
            return SpeedrunnerMod.fixEfficiencyEnchantment(stack);
        }
    }

    @Mixin(SheepEntity.class)
    public static class SheepFix {

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean interactMob(ItemStack stack, Item item) {
            return SpeedrunnerMod.fixShearingSheep(stack);
        }
    }

    @Mixin(SnowGolemEntity.class)
    public static class SnowGolemFix {

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean interactMob(ItemStack stack, Item item) {
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

        @Redirect(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean damageShield(ItemStack stack, Item item) {
            return SpeedrunnerMod.fixShields(stack);
        }
    }

    @Mixin(LivingEntity.class)
    public static class InventoryFix {

        @Redirect(method = "getPreferredEquipmentSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 2))
        private static boolean getPreferredEquipmentSlot(ItemStack stack, Item item) {
            return stack.isOf(Items.SHIELD) || stack.isOf(ModItems.SPEEDRUNNER_SHIELD);
        }
    }

    @Deprecated
    @Mixin(ItemPredicate.class)
    public static class ItemPredicateFix {

        @ModifyVariable(method = "test", at = @At("HEAD"))
        public ItemStack makeSpeedrunnerItemsWorkLikeVanillaItems(ItemStack stack) {
            return SpeedrunnerMod.fixSpeedrunnerShears(stack);
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

        public RuinedPortalGenerationFix(StructureFeature<RuinedPortalFeatureConfig> feature, ChunkPos pos, int references, long seed) {
            super(feature, pos, references, seed);
        }

        @Overwrite
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, RuinedPortalFeatureConfig ruinedPortalFeatureConfig, HeightLimitView heightLimitView) {
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
            BlockPos blockPos2 = chunkPos.getStartPos();
            BlockBox blockBox = structure.calculateBoundingBox(blockPos2, blockRotation, blockPos, blockMirror);
            BlockPos blockPos3 = blockBox.getCenter();
            int i = blockPos3.getX();
            int j = blockPos3.getZ();
            int k = chunkGenerator.getHeight(i, j, RuinedPortalStructurePiece.getHeightmapType(verticalPlacement6), heightLimitView) - 1;
            int l = RuinedPortalFeature.getFloorHeight(this.random, chunkGenerator, verticalPlacement6, properties.airPocket, k, blockBox.getBlockCountY(), blockBox, heightLimitView);
            BlockPos blockPos4 = new BlockPos(blockPos2.getX(), l, blockPos2.getZ());
            if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.MOUNTAIN || ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.OCEAN || ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.STANDARD) {
                properties.cold = RuinedPortalFeature.isColdAt(blockPos4, biome);
            }

            this.addPiece(new RuinedPortalStructurePiece(structureManager, blockPos4, verticalPlacement6, properties, identifier2, structure, blockRotation, blockMirror, blockPos));
        }
    }
}