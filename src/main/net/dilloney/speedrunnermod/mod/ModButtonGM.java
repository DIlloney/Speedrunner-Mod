package net.dilloney.speedrunnermod.mod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(GameMenuScreen.class)
public class ModButtonGM extends Screen {

    public ModButtonGM(Text title) {
        super(title);
    }

    @Shadow @Final
    private boolean showMenu;

    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (showMenu) {
            ButtonWidget createWorldButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 4 - 120 - 2, this.height / 4 + 72 + -16, 20, 20, new LiteralText(""), (buttonWidget) -> {
                buttonWidget.active = false;
                if (this.client.inGameHud != null) {
                    this.client.inGameHud.getChatHud().clear(false);
                }
                this.client.world.disconnect();
                this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
                this.client.openScreen(CreateWorldScreen.create(this));
            }));
            createWorldButton.active = SpeedrunnerMod.OPTIONS.autoCreateWorld;
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (showMenu) {
            RenderSystem.setShaderTexture(0, SpeedrunnerMod.SPEEDRUNNER_BOOTS);
            drawTexture(matrices, this.width / 2 - 4 - 118 - 2, this.height / 4 + 72 + -16 + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        }
    }
}