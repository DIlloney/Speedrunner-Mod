package net.dilloney.speedrunnermod.mixin;

import com.google.common.base.MoreObjects;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.client.ModMenuScreen;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.item.SpeedrunnerCrossbowItem;
import net.dilloney.speedrunnermod.util.UniqueItemRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.DoubleOption;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.FluidTags;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.lang.Math.abs;

/**
 * This class contains everything that needs to be changed in the game by using {@link Mixin}s. Client-side only.
 * <p> Please read {@link ModMixins} for more information. </p>
 * <p> {@linkplain MinecraftClientMixin} </p>
 * <p> {@linkplain OptionsScreenMixin} </p>
 * <p> {@linkplain AbstractClientPlayerEntityMixin} </p>
 * <p> {@linkplain PlayerEntityRendererMixin}, {@linkplain HeldItemRendererMixin} </p>
 * <p> {@linkplain BackgroundRendererMixin} </p>
 * @author Dilloney
 */
@Environment(EnvType.CLIENT)
public class ModMixinsClient {

    @Mixin(MinecraftClient.class)
    public static class MinecraftClientMixin {

        @Shadow
        private GameOptions options;

        @Inject(at = @At("HEAD"), method = "close")
        private void close(CallbackInfo info) {
            options.write();
        }

        @Inject(at = @At("HEAD"), method = "openScreen")
        private void openScreen(Screen screen, CallbackInfo info) {
            if (screen != null && screen.getClass().getSimpleName().equals("SodiumOptionsGUI")) {
                try {
                    List<?> optionPages = (List<?>) get(screen, "me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI", "pages");
                    List<?> optionGroups = (List<?>) get(optionPages.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionPage", "groups");
                    List<?> options = (List<?>) get(optionGroups.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionGroup", "options");
                    Object sliderControl = get(options.get(1), "me.jellysquid.mods.sodium.client.gui.options.OptionImpl", "control");
                    Class<?> sliderControlClass = Class.forName("me.jellysquid.mods.sodium.client.gui.options.control.SliderControl");
                    setInt(sliderControl, sliderControlClass, "min", (int) (SpeedrunnerModClient.minBrightness * 100));
                    setInt(sliderControl, sliderControlClass, "max", (int) (SpeedrunnerModClient.maxBrightness * 100));
                }
                catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private Object get(Object instance, String className, String name) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
            Field f = Class.forName(className).getDeclaredField(name);
            f.setAccessible(true);
            return f.get(instance);
        }

        private void setInt(Object instance, Class<?> clazz, String field, int value) throws NoSuchFieldException, IllegalAccessException {
            Field f = clazz.getDeclaredField(field);
            f.setAccessible(true);
            f.setInt(instance, value);
        }

        @Mixin(MinecraftClient.class)
        public interface GameOptionsAccessor {
            @Accessor("options")
            GameOptions getGameOptions();
        }
    }

    @Mixin(OptionsScreen.class)
    public static class OptionsScreenMixin extends Screen {

        public OptionsScreenMixin(Text title) {
            super(title);
        }

        @Inject(method = "init", at = @At("TAIL"))
        private void init(CallbackInfo callbackInfo) {
            this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 144 - 6, 310, 20, new TranslatableText("speedrunnermod.title"), (button) -> {
                this.client.openScreen(new ModMenuScreen(this));
            }));
        }
    }

    @Mixin(AbstractClientPlayerEntity.class)
    public static class AbstractClientPlayerEntityMixin {

        @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item getSpeed(ItemStack stack) {
            return UniqueItemRegistry.BOW.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(PlayerEntityRenderer.class)
    public static class PlayerEntityRendererMixin {

        @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
        private static void getArmPose(AbstractClientPlayerEntity abstractClientPlayerEntity, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
            ItemStack stack = abstractClientPlayerEntity.getStackInHand(hand);
            if (!abstractClientPlayerEntity.handSwinging && UniqueItemRegistry.CROSSBOW.isItemInRegistry(stack.getItem()) && CrossbowItem.isCharged(stack)) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            }
        }
    }

    @Mixin(HeldItemRenderer.class)
    public abstract static class HeldItemRendererMixin {

        @Shadow
        private ItemStack mainHand;

        @Shadow
        private ItemStack offHand;

        @Shadow
        private float equipProgressMainHand;

        @Shadow
        private float prevEquipProgressMainHand;

        @Shadow
        private float equipProgressOffHand;

        @Shadow
        private float prevEquipProgressOffHand;

        @Shadow
        abstract void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light);

        @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item renderFirstPersonItem(ItemStack stack) {
            return UniqueItemRegistry.CROSSBOW.getDefaultItem(stack.getItem());
        }

        /**
         * Allows modded items to be rendered correctly.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void renderItem(float tickDelta, MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, ClientPlayerEntity player, int light) {
            float f = player.getHandSwingProgress(tickDelta);
            Hand hand = (Hand)MoreObjects.firstNonNull(player.preferredHand, Hand.MAIN_HAND);
            float g = MathHelper.lerp(tickDelta, player.prevPitch, player.pitch);
            boolean bl = true;
            boolean bl2 = true;
            ItemStack itemStack3;
            if (player.isUsingItem()) {
                itemStack3 = player.getActiveItem();
                if (itemStack3.getItem() == Items.BOW || itemStack3.getItem() == Items.CROSSBOW || itemStack3.getItem() == ModItems.SPEEDRUNNER_BOW || itemStack3.getItem() == ModItems.SPEEDRUNNER_CROSSBOW) {
                    bl = player.getActiveHand() == Hand.MAIN_HAND;
                    bl2 = !bl;
                }

                Hand hand2 = player.getActiveHand();
                if (hand2 == Hand.MAIN_HAND) {
                    ItemStack itemStack2 = player.getOffHandStack();
                    if (itemStack2.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemStack2) || itemStack2.getItem() == ModItems.SPEEDRUNNER_CROSSBOW && SpeedrunnerCrossbowItem.isCharged(itemStack2)) {
                        bl2 = false;
                    }
                }
            } else {
                itemStack3 = player.getMainHandStack();
                ItemStack itemStack4 = player.getOffHandStack();
                if (itemStack3.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemStack3) || itemStack3.getItem() == ModItems.SPEEDRUNNER_CROSSBOW && SpeedrunnerCrossbowItem.isCharged(itemStack3)) {
                    bl2 = !bl;
                }

                if (itemStack4.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemStack4) || itemStack4.getItem() == ModItems.SPEEDRUNNER_CROSSBOW && SpeedrunnerCrossbowItem.isCharged(itemStack4)) {
                    bl = !itemStack3.isEmpty();
                    bl2 = !bl;
                }
            }

            float h = MathHelper.lerp(tickDelta, player.lastRenderPitch, player.renderPitch);
            float i = MathHelper.lerp(tickDelta, player.lastRenderYaw, player.renderYaw);
            matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion((player.getPitch(tickDelta) - h) * 0.1F));
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((player.getYaw(tickDelta) - i) * 0.1F));
            float m;
            float l;
            if (bl) {
                l = hand == Hand.MAIN_HAND ? f : 0.0F;
                m = 1.0F - MathHelper.lerp(tickDelta, this.prevEquipProgressMainHand, this.equipProgressMainHand);
                this.renderFirstPersonItem(player, tickDelta, g, Hand.MAIN_HAND, l, this.mainHand, m, matrices, vertexConsumers, light);
            }

            if (bl2) {
                l = hand == Hand.OFF_HAND ? f : 0.0F;
                m = 1.0F - MathHelper.lerp(tickDelta, this.prevEquipProgressOffHand, this.equipProgressOffHand);
                this.renderFirstPersonItem(player, tickDelta, g, Hand.OFF_HAND, l, this.offHand, m, matrices, vertexConsumers, light);
            }

            vertexConsumers.draw();
        }
    }

    @Mixin(DoubleOption.class)
    public static class DoubleOptionMixin {

        @Shadow @Final @Mutable
        private BiFunction<GameOptions, DoubleOption, Text> displayStringGetter;

        @Shadow @Mutable
        private double min, max;

        @Inject(at = @At("RETURN"), method = "<init>")
        private void init(String key, double min, double max, float step, Function<GameOptions, Double> getter, BiConsumer<GameOptions, Double> setter, BiFunction<GameOptions, DoubleOption, Text> displayStringGetter, CallbackInfo info) {
            if (key.equals("options.gamma")) {
                this.min = SpeedrunnerModClient.minBrightness;
                this.max = SpeedrunnerModClient.maxBrightness;
                this.displayStringGetter = this::displayStringGetter;
            }
        }

        private Text displayStringGetter(GameOptions gameOptions, DoubleOption doubleOption) {
            double threshold = 0.025;
            return new TranslatableText("options.gamma").append(": ").append(abs(gameOptions.gamma) < threshold     ? new TranslatableText("options.gamma.min") : abs(gameOptions.gamma - 1) < threshold ? new TranslatableText("options.gamma.max") : new LiteralText(Math.round(gameOptions.gamma * 100) + "%"));
        }
    }

    @Mixin(BackgroundRenderer.class)
    public static class BackgroundRendererMixin {

        /**
         * Implements the Fog option into the game.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
            FluidState fluidState = camera.getSubmergedFluidState();
            Entity entity = camera.getFocusedEntity();
            float s;
            if (fluidState.isIn(FluidTags.WATER)) {
                s = 1.0F;
                s = 0.05F;
                if (entity instanceof ClientPlayerEntity) {
                    ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
                    s -= clientPlayerEntity.getUnderwaterVisibility() * clientPlayerEntity.getUnderwaterVisibility() * 0.03F;
                    Biome biome = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
                    if (biome.getCategory() == Biome.Category.SWAMP) {
                        s += 0.005F;
                    }
                }

                RenderSystem.fogDensity(s);
                RenderSystem.fogMode(GlStateManager.FogMode.EXP2);
            } else {
                float v;
                if (fluidState.isIn(FluidTags.LAVA)) {
                    if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                        s = 0.0F;
                        v = 25.0F;
                    } else {
                        s = 0.25F;
                        v = 1.0F;
                    }
                } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
                    int k = ((LivingEntity)entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
                    float l = MathHelper.lerp(Math.min(1.0F, (float)k / 20.0F), viewDistance, 5.0F);
                    if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                        s = 0.0F;
                        v = l * 0.8F;
                    } else {
                        s = l * 0.25F;
                        v = l;
                    }
                } else if (thickFog) {
                    s = viewDistance * 0.05F;
                    if (!SpeedrunnerMod.OPTIONS.fog) {
                        v = 1024;
                    } else {
                        v = Math.min(viewDistance, 192.0F) * 0.5F;
                    }
                } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                    s = 0.0F;
                    v = viewDistance;
                } else {
                    s = viewDistance * 0.75F;
                    if (!SpeedrunnerMod.OPTIONS.fog) {
                        v = 1024;
                    } else {
                        v = viewDistance;
                    }
                }

                RenderSystem.fogStart(s);
                RenderSystem.fogEnd(v);
                RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
                RenderSystem.setupNvFogDistance();
            }
        }
    }
}
