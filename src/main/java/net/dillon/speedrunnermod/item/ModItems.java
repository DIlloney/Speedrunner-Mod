package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.render.SpeedrunnerShieldRenderer;
import net.dillon.speedrunnermod.entity.ModBoatTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * {@link SpeedrunnerMod} items.
 */
public class ModItems {
    public static final Item SPEEDRUNNER_INGOT = new Item(
            new Item.Settings().group(ItemGroup.MATERIALS)) {

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            if (options().client.itemTooltips) {
                tooltip.add(new TranslatableText("item.speedrunnermod.speedrunner_ingot.tooltip.line1").formatted(Formatting.GRAY));
                tooltip.add(new TranslatableText("item.speedrunnermod.speedrunner_ingot.tooltip.line2").formatted(Formatting.GRAY));
                tooltip.add(new TranslatableText("item.speedrunnermod.speedrunner_ingot.tooltip.line3").formatted(Formatting.GRAY));
            }
        }
    };
    public static final Item SPEEDRUNNER_NUGGET = new Item(
            new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item RAW_SPEEDRUNNER = new Item(
            new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item SPEEDRUNNER_SWORD = new SpeedrunnerSwordItem(
            5, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item SPEEDRUNNER_SHOVEL = new ShovelItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 4, -3.0F,
            new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item SPEEDRUNNER_PICKAXE = new PickaxeItem(
            ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 3, -2.8F,
            new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item SPEEDRUNNER_AXE = new AxeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 8, -3.05F,
            new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item SPEEDRUNNER_HOE = new HoeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F,
            new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item SPEEDRUNNER_HELMET = new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.HEAD,
            new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item SPEEDRUNNER_CHESTPLATE = new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.CHEST,
            new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item SPEEDRUNNER_LEGGINGS = new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.LEGS,
            new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item SPEEDRUNNER_BOOTS = new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentSlot.FEET,
            new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item SPEEDRUNNER_BOW = new SpeedrunnerBowItem(new Item.Settings());
    public static final Item SPEEDRUNNER_CROSSBOW = new SpeedrunnerCrossbowItem(new Item.Settings());
    public static final Item SPEEDRUNNER_SHEARS = new SpeedrunnerShearsItem(new Item.Settings());
    public static final Item SPEEDRUNNER_FLINT_AND_STEEL = new FlintAndSteelItem(
            new Item.Settings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(128));
    public static final Item SPEEDRUNNER_SHIELD = new SpeedrunnerShieldItem(new Item.Settings()) {

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            if (options().client.itemTooltips) {
                tooltip.add(new TranslatableText("item.speedrunnermod.speedrunner_shield.tooltip").formatted(Formatting.GRAY));
            }
        }
    };
    public static final Item GOLDEN_SPEEDRUNNER_SWORD = new SpeedrunnerSwordItem(
            4, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item GOLDEN_SPEEDRUNNER_SHOVEL = new ShovelItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 2.5F, -3.0F,
            new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item GOLDEN_SPEEDRUNNER_PICKAXE = new PickaxeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 2, -2.8F,
            new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item GOLDEN_SPEEDRUNNER_AXE = new AxeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 7, -3.0F,
            new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item GOLDEN_SPEEDRUNNER_HOE = new HoeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 0, -0.5F,
            new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item GOLDEN_SPEEDRUNNER_HELMET = new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.HEAD,
            new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item GOLDEN_SPEEDRUNNER_CHESTPLATE = new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.CHEST,
            new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item GOLDEN_SPEEDRUNNER_LEGGINGS = new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.LEGS,
            new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item GOLDEN_SPEEDRUNNER_BOOTS = new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentSlot.FEET,
            new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item SPEEDRUNNER_BULK = new Item(
            new Item.Settings().group(ItemGroup.FOOD).rarity(Rarity.RARE).food(ModFoodComponents.SPEEDRUNNER_BULK)) {

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            if (options().client.itemTooltips) {
                tooltip.add(new TranslatableText("item.speedrunnermod.speedrunner_bulk.tooltip.line1"));
                tooltip.add(new TranslatableText("item.speedrunnermod.speedrunner_bulk.tooltip.line2"));
                tooltip.add(new TranslatableText("item.speedrunnermod.speedrunner_bulk.tooltip.line3"));
            }
        }
    };
    public static final Item ROTTEN_SPEEDRUNNER_BULK = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.ROTTEN_SPEEDRUNNER_BULK));
    public static final Item COOKED_FLESH = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.COOKED_FLESH));
    public static final Item PIGLIN_PORK = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.PIGLIN_PORK));
    public static final Item COOKED_PIGLIN_PORK = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.COOKED_PIGLIN_PORK));
    public static final Item GOLDEN_PIGLIN_PORK = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_PIGLIN_PORK));
    public static final Item GOLDEN_BEEF = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_BEEF));
    public static final Item GOLDEN_PORKCHOP = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_PORKCHOP));
    public static final Item GOLDEN_MUTTON = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_MUTTON));
    public static final Item GOLDEN_CHICKEN = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_CHICKEN));
    public static final Item GOLDEN_RABBIT = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_RABBIT));
    public static final Item GOLDEN_COD = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_COD));
    public static final Item GOLDEN_SALMON = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_SALMON));
    public static final Item GOLDEN_BREAD = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_BREAD));
    public static final Item GOLDEN_POTATO = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_POTATO));
    public static final Item GOLDEN_BEETROOT = new Item(
            new Item.Settings().group(ItemGroup.FOOD).food(ModFoodComponents.GOLDEN_BEETROOT));
    public static final Item IGNEOUS_ROCK = new Item(
            new Item.Settings().group(ItemGroup.MATERIALS)) {

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            if (options().client.itemTooltips) {
                tooltip.add(new TranslatableText("item.speedrunnermod.igneous_rock.tooltip").formatted(Formatting.GRAY));
            }
        }
    };
    public static final Item SPEEDRUNNER_STICK = new Item(
            new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item SPEEDRUNNER_BOAT = new SpeedrunnerBoatItem(ModBoatTypes.SPEEDRUNNER, true, new Item.Settings());
    public static final Item CRIMSON_BOAT = new SpeedrunnerBoatItem(ModBoatTypes.CRIMSON, false, new Item.Settings());
    public static final Item WARPED_BOAT = new SpeedrunnerBoatItem(ModBoatTypes.WARPED, false, new Item.Settings());
    public static final Item WITHER_BONE = new Item(
            new Item.Settings().group(ItemGroup.MISC)) {

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            if (options().client.itemTooltips) {
                tooltip.add(new TranslatableText("item.speedrunnermod.wither_bone.tooltip").formatted(Formatting.GRAY));
            }
        }
    };
    public static final Item WITHER_SWORD = new WitherSwordItem(new Item.Settings());
    public static final Item ANNUL_EYE = new AnnulEyeItem(new Item.Settings());
    public static final Item SPEEDRUNNERS_EYE = new SpeedrunnersEyeItem(new Item.Settings());
    public static final Item INFERNO_EYE = new InfernoEyeItem(new Item.Settings());
    public static final Item PIGLIN_AWAKENER = new PiglinAwakenerItem(new Item.Settings());
    public static final Item BLAZE_SPOTTER = new BlazeSpotterItem(new Item.Settings());
    public static final Item RAID_ERADICATOR = new RaidEradicatorItem(new Item.Settings());
    public static final Item ENDER_THRUSTER = new EnderThrusterItem(new Item.Settings());
    public static final Item DRAGONS_SWORD = new DragonsSwordItem(new Item.Settings());
    public static final Item DRAGONS_PEARL = new DragonsPearlItem(new Item.Settings());

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_ingot"), SPEEDRUNNER_INGOT);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_nugget"), SPEEDRUNNER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "raw_speedrunner"), RAW_SPEEDRUNNER);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_sword"), SPEEDRUNNER_SWORD);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_shovel"), SPEEDRUNNER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_pickaxe"), SPEEDRUNNER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_axe"), SPEEDRUNNER_AXE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_hoe"), SPEEDRUNNER_HOE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_helmet"), SPEEDRUNNER_HELMET);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_chestplate"), SPEEDRUNNER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_leggings"), SPEEDRUNNER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_boots"), SPEEDRUNNER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_bow"), SPEEDRUNNER_BOW);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_crossbow"), SPEEDRUNNER_CROSSBOW);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_shears"), SPEEDRUNNER_SHEARS);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_flint_and_steel"), SPEEDRUNNER_FLINT_AND_STEEL);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_shield"), SPEEDRUNNER_SHIELD);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_speedrunner_sword"), GOLDEN_SPEEDRUNNER_SWORD);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_speedrunner_shovel"), GOLDEN_SPEEDRUNNER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_speedrunner_pickaxe"), GOLDEN_SPEEDRUNNER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_speedrunner_axe"), GOLDEN_SPEEDRUNNER_AXE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_speedrunner_hoe"), GOLDEN_SPEEDRUNNER_HOE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_speedrunner_helmet"), GOLDEN_SPEEDRUNNER_HELMET);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_speedrunner_chestplate"), GOLDEN_SPEEDRUNNER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_speedrunner_leggings"), GOLDEN_SPEEDRUNNER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_speedrunner_boots"), GOLDEN_SPEEDRUNNER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_bulk"), SPEEDRUNNER_BULK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "rotten_speedrunner_bulk"), ROTTEN_SPEEDRUNNER_BULK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "cooked_flesh"), COOKED_FLESH);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "piglin_pork"), PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "cooked_piglin_pork"), COOKED_PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_piglin_pork"), GOLDEN_PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_beef"), GOLDEN_BEEF);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_porkchop"), GOLDEN_PORKCHOP);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_mutton"), GOLDEN_MUTTON);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_chicken"), GOLDEN_CHICKEN);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_rabbit"), GOLDEN_RABBIT);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_cod"), GOLDEN_COD);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_salmon"), GOLDEN_SALMON);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_bread"), GOLDEN_BREAD);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_potato"), GOLDEN_POTATO);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_beetroot"), GOLDEN_BEETROOT);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "igneous_rock"), IGNEOUS_ROCK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_stick"), SPEEDRUNNER_STICK);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_boat"), SPEEDRUNNER_BOAT);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "crimson_boat"), CRIMSON_BOAT);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "warped_boat"), WARPED_BOAT);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "wither_bone"), WITHER_BONE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "wither_sword"), WITHER_SWORD);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "annul_eye"), ANNUL_EYE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunners_eye"), SPEEDRUNNERS_EYE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "inferno_eye"), INFERNO_EYE);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "piglin_awakener"), PIGLIN_AWAKENER);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "blaze_spotter"), BLAZE_SPOTTER);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "raid_eradicator"), RAID_ERADICATOR);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "ender_thruster"), ENDER_THRUSTER);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "dragons_sword"), DRAGONS_SWORD);
        Registry.register(Registry.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "dragons_pearl"), DRAGONS_PEARL);

        info("Initialized items.");
    }

    @Environment(EnvType.CLIENT)
    public static void clinit() {
        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (entity.getActiveItem() != stack) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 15.0F;
        });
        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW, new Identifier("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);

        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (CrossbowItem.isCharged(stack)) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(stack);
        });
        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("charged"), (stack, world, entity, seed) -> entity != null && CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("firework"), (stack, world, entity, seed) -> entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F);

        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_SHIELD, new Identifier("blocking"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
        BuiltinItemRendererRegistry.INSTANCE.register(SPEEDRUNNER_SHIELD, new SpeedrunnerShieldRenderer());
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            if (atlasTexture.getId() == SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE) {
                registry.register(new Identifier(SpeedrunnerMod.MOD_ID, "entity/speedrunner_shield_base"));
            }
        });

        info("Initialized custom item models.");
    }
}