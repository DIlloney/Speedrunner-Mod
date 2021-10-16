package net.dilloney.speedrunnermod.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.client.ModMenuScreen;
import net.dilloney.speedrunnermod.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
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
import net.minecraft.text.LiteralText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.*;
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

        @Shadow GameOptions options;

        @Inject(method = "close", at = @At("HEAD"))
        private void writeOptions(CallbackInfo info) {
            options.write();
        }

        @Inject(method = "openScreen", at = @At("HEAD"))
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
    }

    @Mixin(OptionsScreen.class)
    public static class OptionsScreenMixin extends Screen {

        public OptionsScreenMixin(Text title) {
            super(title);
        }

        @Inject(method = "init", at = @At("TAIL"))
        private void init(CallbackInfo callbackInfo) {
            this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 144 - 6, 310, 20, new TranslatableText("speedrunnermod.title"), (button) -> {
                this.client.openScreen(new ModMenuScreen(this));
            }));
        }
    }

    @Mixin(AbstractClientPlayerEntity.class)
    public static class AbstractClientPlayerEntityMixin {

        @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean getSpeed(ItemStack stack, Item item) {
            return stack.isOf(Items.BOW) || stack.isOf(ModItems.SPEEDRUNNER_BOW);
        }
    }

    @Mixin(PlayerEntityRenderer.class)
    public static class PlayerEntityRendererMixin {

        @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
        private static void getArmPose(AbstractClientPlayerEntity abstractClientPlayerEntity, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
            ItemStack stack = abstractClientPlayerEntity.getStackInHand(hand);
            if (!abstractClientPlayerEntity.handSwinging && stack.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(stack) || !abstractClientPlayerEntity.handSwinging && stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW) && CrossbowItem.isCharged(stack)) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            }
        }
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
            return stack.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(stack) || stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW) && CrossbowItem.isCharged(stack);
        }

        @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
        private boolean renderFirstPersonItem(ItemStack stack, Item item) {
            return stack.isOf(Items.CROSSBOW) || stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW);
        }
    }

    @Mixin(DoubleOption.class)
    public static class DoubleOptionMixin {

        @Shadow @Final @Mutable
        BiFunction<GameOptions, DoubleOption, Text> displayStringGetter;

        @Shadow @Mutable
        double min, max;

        @Inject(method = "<init>", at = @At("RETURN"))
        private void init(String key, double min, double max, float step, Function<GameOptions, Double> getter, BiConsumer<GameOptions, Double> setter, BiFunction<GameOptions, DoubleOption, Text> displayStringGetter, Function<MinecraftClient, List<OrderedText>> function, CallbackInfo info) {
            if (key.equals("options.gamma")) {
                this.min = SpeedrunnerModClient.minBrightness;
                this.max = SpeedrunnerModClient.maxBrightness;
                this.displayStringGetter = this::displayStringGetter;
            }
        }

        private Text displayStringGetter(GameOptions gameOptions, DoubleOption doubleOption) {
            double threshold = 0.025;
            return new TranslatableText("options.gamma").append(": ").append(abs(gameOptions.gamma) < threshold ? new TranslatableText("options.gamma.min") : abs(gameOptions.gamma - 1) < threshold ? new TranslatableText("options.gamma.max") : new LiteralText(Math.round(gameOptions.gamma * 100) + "%"));
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
            CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
            Entity entity = camera.getFocusedEntity();
            float y;
            if (cameraSubmersionType == CameraSubmersionType.WATER) {
                y = 192.0F;
                if (entity instanceof ClientPlayerEntity) {
                    ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
                    y *= Math.max(0.25F, clientPlayerEntity.getUnderwaterVisibility());
                    Biome biome = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
                    if (biome.getCategory() == Biome.Category.SWAMP) {
                        y *= 0.85F;
                    }
                }

                RenderSystem.setShaderFogStart(-8.0F);
                RenderSystem.setShaderFogEnd(y * 0.5F);
            } else {
                float ab;
                if (cameraSubmersionType == CameraSubmersionType.LAVA) {
                    if (entity.isSpectator()) {
                        y = -8.0F;
                        ab = viewDistance * 0.5F;
                    } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                        y = 0.0F;
                        ab = 25.0F;
                    } else {
                        y = 0.25F;
                        ab = 1.0F;
                    }
                } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
                    int m = ((LivingEntity)entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
                    float n = MathHelper.lerp(Math.min(1.0F, (float)m / 20.0F), viewDistance, 5.0F);
                    if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                        y = 0.0F;
                        ab = n * 0.8F;
                    } else {
                        y = n * 0.25F;
                        ab = n;
                    }
                } else if (cameraSubmersionType == CameraSubmersionType.POWDER_SNOW) {
                    if (entity.isSpectator()) {
                        y = -8.0F;
                        ab = viewDistance * 0.5F;
                    } else {
                        y = 0.0F;
                        ab = 2.0F;
                    }
                } else if (thickFog) {
                    y = viewDistance * 0.05F;
                    if (!SpeedrunnerMod.OPTIONS.fog) {
                        ab = 1024;
                    } else {
                        ab = Math.min(viewDistance, 192.0F) * 0.5F;
                    }
                } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                    y = 0.0F;
                    ab = viewDistance;
                } else {
                    y = viewDistance * 0.75F;
                    if (!SpeedrunnerMod.OPTIONS.fog) {
                        ab = 1024;
                    } else {
                        ab = viewDistance;
                    }
                }

                RenderSystem.setShaderFogStart(y);
                RenderSystem.setShaderFogEnd(ab);
            }
        }
    }
}
