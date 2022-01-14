package net.dilloney.speedrunnermod.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.item.SpeedrunnerCrossbowItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.OrderedText;
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

@Environment(EnvType.CLIENT)
public class ModMixinsClient {

    @Mixin(AbstractClientPlayerEntity.class)
    public static class AbstractClientPlayerEntityMixin {

        @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean getSpeed(ItemStack stack, Item item) {
            return stack.isOf(Items.BOW) || stack.isOf(ModItems.SPEEDRUNNER_BOW);
        }
    }

    @Mixin(BackgroundRenderer.class)
    public static class BackgroundRendererMixin {

        @Overwrite
        public static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
            CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
            Entity entity = camera.getFocusedEntity();
            float f;
            if (cameraSubmersionType == CameraSubmersionType.WATER) {
                f = 192.0F;
                if (entity instanceof ClientPlayerEntity) {
                    ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
                    f *= Math.max(0.25F, clientPlayerEntity.getUnderwaterVisibility());
                    Biome biome = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
                    if (biome.getCategory() == Biome.Category.SWAMP) {
                        f *= 0.85F;
                    }
                }

                RenderSystem.setShaderFogStart(-8.0F);
                RenderSystem.setShaderFogEnd(f * 0.5F);
            } else {
                float clientPlayerEntity;
                final int fog = 2147483647;
                final float lavaVisionDistance = 35.0F;
                if (cameraSubmersionType == CameraSubmersionType.LAVA) {
                    if (entity.isSpectator()) {
                        f = -8.0F;
                        clientPlayerEntity = viewDistance * 0.5F;
                    } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                        f = 0.0F;
                        clientPlayerEntity = lavaVisionDistance;
                    } else {
                        f = 0.25F;
                        clientPlayerEntity = 1.0F;
                    }
                } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
                    int biome = ((LivingEntity)entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
                    float g = MathHelper.lerp(Math.min(1.0F, (float)biome / 20.0F), viewDistance, 5.0F);
                    if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                        f = 0.0F;
                        clientPlayerEntity = g * 0.8F;
                    } else {
                        f = g * 0.25F;
                        clientPlayerEntity = g;
                    }
                } else if (cameraSubmersionType == CameraSubmersionType.POWDER_SNOW) {
                    if (entity.isSpectator()) {
                        f = -8.0F;
                        clientPlayerEntity = viewDistance * 0.5F;
                    } else {
                        f = 0.0F;
                        clientPlayerEntity = 2.0F;
                    }
                } else if (thickFog) {
                    f = viewDistance * 0.05F;
                    if (!SpeedrunnerModClient.clOptions().fog) {
                        clientPlayerEntity = fog;
                    } else {
                        clientPlayerEntity = Math.min(viewDistance, 192.0F) * 0.5F;
                    }
                } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                    f = 0.0F;
                    clientPlayerEntity = viewDistance;
                } else {
                    float biome = MathHelper.clamp(viewDistance / 10.0F, 4.0F, 64.0F);
                    f = viewDistance - biome;
                    if (!SpeedrunnerModClient.clOptions().fog) {
                        clientPlayerEntity = fog;
                    } else {
                        clientPlayerEntity = viewDistance;
                    }
                }

                RenderSystem.setShaderFogStart(f);
                RenderSystem.setShaderFogEnd(clientPlayerEntity);
            }

        }
    }

    @Mixin(DoubleOption.class)
    public static class DoubleOptionMixin {
        @Shadow @Final @Mutable
        private BiFunction<GameOptions, DoubleOption, Text> displayStringGetter;
        @Shadow @Final @Mutable
        protected double min;
        @Shadow @Final @Mutable
        protected float step;
        @Shadow @Mutable
        protected double max;

        @Inject(method = "<init>", at = @At("RETURN"))
        private void init(String key, double min, double max, float step, Function<GameOptions, Double> getter, BiConsumer<GameOptions, Double> setter, BiFunction<GameOptions, DoubleOption, Text> displayStringGetter, Function<MinecraftClient, List<OrderedText>> tooltipsGetter, CallbackInfo ci) {
            if (key.equals("options.gamma")) {
                this.min = 1.0D;
                this.max = 5.0D;
                this.step = 0.1F;
                this.displayStringGetter = this::displayStringGetter;
            }
        }

        private Text displayStringGetter(GameOptions gameOptions, DoubleOption doubleOption) {
            return new TranslatableText("options.gamma").append(": ").append(gameOptions.gamma == 0 ? new TranslatableText("options.gamma.min") : gameOptions.gamma == 1 ? new TranslatableText("options.gamma.max") : new LiteralText(Math.round(gameOptions.gamma * 100) + "%"));
        }
    }

    @Mixin(MinecraftClient.class)
    public static class MinecraftClientMixin {
        private final long SAVE_INTERVAL = 10000;

        @Shadow
        private GameOptions options;
        private long lastSaveTime = 0;

        @Inject(at = @At("HEAD"), method = "close")
        private void close(CallbackInfo info) {
            options.write();
        }

        @Inject(at = @At("HEAD"), method = "setScreen")
        private void setScreen(Screen screen, CallbackInfo info) {
            if (screen instanceof GameMenuScreen && System.currentTimeMillis() - lastSaveTime > SAVE_INTERVAL) {
                lastSaveTime = System.currentTimeMillis();
            }

            if (screen != null && screen.getClass().getSimpleName().equals("SodiumOptionsGUI")) {
                try {
                    List<?> optionPages = (List<?>) get(screen, "me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI", "pages");
                    List<?> optionGroups = (List<?>) get(optionPages.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionPage", "groups");
                    List<?> options = (List<?>) get(optionGroups.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionGroup", "options");
                    Object sliderControl = get(options.get(2), "me.jellysquid.mods.sodium.client.gui.options.OptionImpl", "control");
                    Class<?> sliderControlClass = Class.forName("me.jellysquid.mods.sodium.client.gui.options.control.SliderControl");
                    setInt(sliderControl, sliderControlClass, "min", (int) (1.0D * 100));
                    setInt(sliderControl, sliderControlClass, "max", (int) (5.0D * 100));
                    setInt(sliderControl, sliderControlClass, "interval", 10);
                } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException ex) {
                    SpeedrunnerModClient.logException(ex, "an exception occurred during the manipulation of the sodium options gui");
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
    }

    @Mixin(MinecraftClient.class)
    public interface GameOptionsAccessor {
        @Accessor("options")
        GameOptions getGameOptions();
    }

    @Mixin(HeldItemRenderer.class)
    public static class HeldItemRendererMixin {

        @Redirect(method = "getHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private static boolean getHandRenderType(ItemStack stack, Item item, ClientPlayerEntity player) {
            return stack.isOf(Items.BOW) || stack.isOf(ModItems.SPEEDRUNNER_BOW) || stack.isOf(Items.CROSSBOW) || stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW);
        }

        @Redirect(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private static boolean getUsingItemHandRenderType(ItemStack stack, Item item) {
            return stack.isOf(Items.BOW) || stack.isOf(ModItems.SPEEDRUNNER_BOW) || stack.isOf(Items.CROSSBOW) || stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW);
        }

        @Redirect(method = "isChargedCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private static boolean isChargedCrossbow(ItemStack stack, Item item) {
            return stack.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(stack) || stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW) && SpeedrunnerCrossbowItem.isCharged(stack);
        }

        @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
        private boolean renderFirstPersonItem(ItemStack stack, Item item) {
            return stack.isOf(Items.CROSSBOW) || stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW);
        }
    }

    @Mixin(PlayerEntityRenderer.class)
    public static class PlayerEntityRendererMixin {

        @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
        private static void getArmPose(AbstractClientPlayerEntity abstractClientPlayerEntity, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
            ItemStack stack = abstractClientPlayerEntity.getStackInHand(hand);
            if (!abstractClientPlayerEntity.handSwinging && stack.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(stack) || !abstractClientPlayerEntity.handSwinging && stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW) && SpeedrunnerCrossbowItem.isCharged(stack)) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            }
        }
    }

    @Mixin(ServerPlayerEntity.class)
    public interface SeenCreditsAccessor {
        @Accessor("seenCredits")
        boolean seenCredits();
    }
}