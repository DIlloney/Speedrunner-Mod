package net.dilloney.speedrunnermod.client.gui.widget;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.client.gui.screen.ModMenuScreen;
import net.dilloney.speedrunnermod.option.CLModOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ModButtonTS extends Screen {
    @Shadow @Final
    private boolean doBackgroundFade;
    @Shadow
    private long backgroundFadeStart;
    private static final Text CREATE_WORLD_BUTTON_DESCRIPTION = new TranslatableText("speedrunnermod.create_world_button.desc");
    private static final Text CREATE_WORLD_BUTTON_ERROR = new TranslatableText("speedrunnermod.create_world_button.error");
    private ButtonWidget createWorldButton;

    public ModButtonTS(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (SpeedrunnerModClient.clOptions().autoCreateWorld) {
            createWorldButton = this.addButton(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 48, 20, 20, new LiteralText(""), (buttonWidget) -> {
                this.client.openScreen(CreateWorldScreen.method_31130(this));
            }));
        }

        if (SpeedrunnerModClient.clOptions().modButtonType == CLModOptions.ModButtonType.LOGO) {
            this.addButton(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 48 + 24 * 2, 20, 20, new LiteralText(""), (buttonWidget) -> {
                this.client.openScreen(new ModMenuScreen(this, MinecraftClient.getInstance().options));
            }));
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (SpeedrunnerModClient.clOptions().autoCreateWorld) {
            this.client.getTextureManager().bindTexture(new Identifier("speedrunnermod:textures/item/speedrunner_boots.png"));
            drawTexture(matrices, (this.width / 2) - 122, this.height / 4 + 48 + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            if (createWorldButton.isHovered() && hasShiftDown()) {
                if (SpeedrunnerModClient.clOptions().autoCreateWorld) {
                    drawCenteredText(matrices, this.textRenderer, CREATE_WORLD_BUTTON_DESCRIPTION, this.width / 2 - 114, this.height / 4 + 35, 16777215);
                } else {
                    drawCenteredText(matrices, this.textRenderer, CREATE_WORLD_BUTTON_ERROR, this.width / 2 - 114, this.height / 4 + 35, 16777215);
                }
            }
        }

        if (SpeedrunnerModClient.clOptions().modButtonType == CLModOptions.ModButtonType.LOGO) {
            this.client.getTextureManager().bindTexture(new Identifier("speedrunnermod:textures/item/speedrunner_ingot.png"));
            drawTexture(matrices, (this.width / 2) - 122, this.height / 4 + 48 + 24 * 2 + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        }
    }

    @Inject(method = "render", at = @At("TAIL"), cancellable = true)
    private void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(g * 255.0F) << 24;
        drawStringWithShadow(matrices, this.textRenderer, "Speedrunner Mod " + SpeedrunnerMod.MOD_VERSION, 2, this.height - 20, 16777215 | l);
    }
}