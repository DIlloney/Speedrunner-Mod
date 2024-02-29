package net.dillon8775.speedrunnermod.mixin.client.screen;

import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {
    @Shadow @Final
    private List<ButtonWidget> buttons;

    private DeathScreenMixin(Text title) {
        super(title);
    }

    /**
     * Adds a reset button to the death screen.
     */
    @Inject(method = "init", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private void addResetButton(CallbackInfo ci) {
        if (SpeedrunnerModClient.clientOptions().fastWorldCreation) {
            this.buttons.add(this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 120, 200, 20, new TranslatableText("speedrunnermod.reset_world"), (button) -> {
                if (this.client.inGameHud != null) {
                    this.client.inGameHud.getChatHud().clear(false);
                }
                this.client.world.disconnect();
                this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
                this.client.setScreen(CreateWorldScreen.create(this));
            })));
        }
    }
}