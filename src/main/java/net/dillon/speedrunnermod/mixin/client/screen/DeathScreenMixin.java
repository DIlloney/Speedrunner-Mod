package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {
    @Shadow @Final
    private List<ButtonWidget> buttons;

    private DeathScreenMixin(Text title) {
        super(title);
    }

    /**
     * Adds a {@code reset button} to the death screen.
     */
    @Inject(method = "init", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private void addResetButton(CallbackInfo ci) {
        if (options().client.fastWorldCreation && options().advanced.showResetButton) {
            this.buttons.add(this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.new_run"), button -> {
                if (this.client.inGameHud != null) {
                    this.client.inGameHud.getChatHud().clear(false);
                }
                this.client.world.disconnect();
                this.client.disconnect(new MessageScreen(Text.translatable("speedrunnermod.menu.generating_new_world")));
                CreateWorldScreen.create(this.client, this);
            }).dimensions(this.width / 2 - 100, this.height / 4 + 120, 200, 20).build()));
        }
    }

    /**
     * Displays the players death coordinates on the death screen.
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void displayDeathCords(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (options().client.showDeathCords) {
            context.drawCenteredTextWithShadow(this.textRenderer, SpeedrunnerMod.deathCords(this.client.player.getX(), this.client.player.getY(), this.client.player.getZ()), this.width / 2, 115, 0xFFFFFF);
        }
    }
}